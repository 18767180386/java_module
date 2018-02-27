package com.aiiju.store.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.DateUtil;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.mapper.DealDetailMapper;
import com.aiiju.mapper.DealMapper;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealDetail;
import com.aiiju.pojo.ErpDeal;
import com.aiiju.pojo.Shop;
import com.aiiju.store.service.DealService;
import com.alibaba.druid.util.DaemonThreadFactory;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: DealServiceImpl
 * @Description: 交易流水ServiceImpl
 * @author 小飞
 * @date 2016年11月14日 下午4:06:59
 *
 */
@Service("dealService")
public class DealServiceImpl implements DealService {

    @Autowired
    private DealMapper dealMapper;

    @Autowired
    private DealDetailMapper dealDetailMapper;
    
    @Autowired
	private ShopMapper shopMapper;

    @Override
    public Boolean saveDeal(Deal deal) {
        Boolean rt = false;
        // 保存流水单信息
        this.dealMapper.add(deal);
        // 保存其详情
        List<DealDetail> details = deal.getDetails();
        this.dealDetailMapper.insertBatch(details);
        rt = true;
        return rt;
    }

    @Override
    public Boolean updateDeal(Deal deal) {
        Boolean rt = false;
        this.dealMapper.update(deal);
        rt = true;
        return rt;
    }

    @Override
    public Result getById(Long id) {
        Deal deal = this.dealMapper.getById(id);
        // 格式化操作员
        if (deal.getOperatorName() == null) {
            deal.setOperatorName("");
        }
        // 格式化商品编号
        List<DealDetail> details = deal.getDetails();
        for (int i = 0; i < details.size(); i++) {
            if (StringUtils.isBlank(details.get(i).getCode())) {
                details.get(i).setCode("");
            }
        }
        return Result.success(deal);
    }

    @Override
    public Result getDetailBySerialNum(String serialNum) {
        Deal deal = this.dealMapper.getDetailBySerialNum(serialNum);
        // 格式化操作员
        if (deal.getOperatorName() == null) {
            deal.setOperatorName("");
        }
        // 格式化商品编号
        List<DealDetail> details = deal.getDetails();
        for (int i = 0; i < details.size(); i++) {
            if (StringUtils.isBlank(details.get(i).getCode())) {
                details.get(i).setCode("");
            }
        }
        return Result.success(deal);
    }

    @Override
    public Result getAllByStoreId(String storeId, String operatorId, Integer currentNum, Integer pageSize,String serialNum) {
        List<Map<String, Object>> source = null;
        Map<String, Object> params = new HashMap<>();
        if (!StringUtils.isBlank(operatorId)) {
            params.put("operatorId", operatorId);
        }
        params.put("storeId", storeId);
        params.put("serialNum", serialNum);
        params.put("index", (currentNum - 1) * pageSize);
        params.put("pageSize", pageSize);
        source = this.dealMapper.getPageByStoreId(params);

        // 转换格式
        List<Map<String, Object>> rt = this.parseDataA(source,params);
        return Result.success(rt);
    }

    private List<Map<String, Object>> parseDataA(List<Map<String, Object>> source,Map<String, Object> params) {
        List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();
        if (source == null || source.size() == 0) {
            return rt;
        }
        // 日期分组
        SortedMap<String, List<Map<String, Object>>> dataMap = new TreeMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> temp = null;
        for (Map<String, Object> data : source) {
            String createDate = data.get("dealDate").toString();
            if (dataMap.get(createDate) != null) {
                dataMap.get(createDate).add(data);
            } else {
                temp = new ArrayList<Map<String, Object>>();
                temp.add(data);
                dataMap.put(createDate, temp);
            }
        }
        // 转换格式
        SortedMap<String, Object> rtMap = null;

        for (Map.Entry<String, List<Map<String, Object>>> entry : dataMap.entrySet()) {
            rtMap = new TreeMap<String, Object>();
            rtMap.put("date", entry.getKey());
            params.put("dealDate", entry.getKey());
	         rtMap.put("sum", dealMapper.getPageByStoreId_dayTotal(params));
	         rtMap.put("list", entry.getValue());
	         rt.add(rtMap);
        }
        return rt;
    }
    
    
    @Override
    public Result summarize(String storeId) {
        // 总收入
        BigDecimal totalIncome = this.dealMapper.getTotalIncome(storeId);
        Map<String, String> params = new HashMap<>();
        params.put("storeId", storeId);
        // 近7天
        params.put("start", DateUtil.getAnyDate(-6));
        params.put("end", DateUtil.getAnyDate(0));
        BigDecimal sevenDay = this.dealMapper.getSummarize(params);
        // 近30天
        params.put("start", DateUtil.getAnyDate(-29));
        BigDecimal thirtyDay = this.dealMapper.getSummarize(params);
        // 今天
        params.put("today", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        BigDecimal today = this.dealMapper.getSummarizeByToday(params);

        // 封装结果
        Map<String, Object> rt = new HashMap<>();
        rt.put("totalIncome", totalIncome != null ? totalIncome : "0.00");
        rt.put("sevenDay", sevenDay != null ? sevenDay : "0.00");
        rt.put("thirtyDay", thirtyDay != null ? thirtyDay : "0.00");
        rt.put("today", today != null ? today : "0.00");
        rt.put("zfb", "0.00");
        rt.put("wx", "0.00");
        rt.put("qq", "0.00");
        rt.put("xj", "0.00");
        rt.put("tk", "0.00");
        rt.put("pa", "0.00"); // 平安银行
        rt.put("erp", "0.00"); // erp会员余额
        // 支付宝 微信 现金 当月收入
        params.put("start", DateUtil.getFirstDayOnMonth());
        params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        List<Deal> typeList = this.dealMapper.getSummarizeByType(params);
        
        BigDecimal bd = new BigDecimal("0.00");
        
        if (typeList != null && typeList.size() > 0) {
            for (Deal deal : typeList) {
                if ("0".equals(deal.getPayType())) {
                    rt.put("xj", deal.getPrice().toString());
                } else if ("1".equals(deal.getPayType())) {
                    rt.put("zfb", deal.getPrice().toString());
                } else if ("2".equals(deal.getPayType())) {
                    rt.put("wx", deal.getPrice().toString());
                } else if ("3".equals(deal.getPayType())) {
                    rt.put("qq", deal.getPrice().toString());
                } else if ("4".equals(deal.getPayType())||"5".equals(deal.getPayType())) {
                	bd = bd.add(deal.getPrice());
                } else if (null == deal.getPayType()) {
                    rt.put("tk", deal.getPrice().abs().toString());
                }else if ("6".equals(deal.getPayType())) {
                    rt.put("erp", deal.getPrice().toString());
                }
            }
        }
        // 当月 退款汇总
        // BigDecimal tk = this.dealMapper.getRefundSummarize(params);
        // if (tk != null) {
        // rt.put("tk", tk.abs().toString());
        // }
        rt.put("pa", bd.toString());
        return Result.success(rt);
    }

    @Override
    public Result getIncomeDetail(String storeId, String type, Integer currentNum, Integer pageSize) {
        List<Map<String, Object>> source = null;
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("index", (currentNum - 1) * pageSize);
        params.put("pageSize", pageSize);

        switch (type) {
            case "0":
                params.put("deal_date", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
                source = this.dealMapper.getPageByToday(params);
                break;
            case "7":
                params.put("start", DateUtil.getAnyDate(-7));
                params.put("end", DateUtil.getAnyDate(-1));
                source = this.dealMapper.getPageByDay(params);
                break;
            case "30":
                params.put("start", DateUtil.getAnyDate(-30));
                params.put("end", DateUtil.getAnyDate(-1));
                source = this.dealMapper.getPageByDay(params);
                break;
            case "zfb":
                params.put("payType", "1");
                params.put("start", DateUtil.getFirstDayOnMonth());
                params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
                source = this.dealMapper.getPageByType(params);
                break;
            case "wx":
                params.put("payType", "2");
                params.put("start", DateUtil.getFirstDayOnMonth());
                params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
                source = this.dealMapper.getPageByType(params);
                break;
            case "qq":
                params.put("payType", "3");
                params.put("start", DateUtil.getFirstDayOnMonth());
                params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
                source = this.dealMapper.getPageByType(params);
                break;
            case "xj":
                params.put("payType", "0");
                params.put("start", DateUtil.getFirstDayOnMonth());
                params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
                source = this.dealMapper.getPageByType(params);
                break;
            case "pa":
                params.put("start", DateUtil.getFirstDayOnMonth());
                params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
                source = this.dealMapper.getPAPageByType(params);
                break;
        }

        // 转换格式
        List<Map<String, Object>> rt = this.parseDataB(source,params);
        return Result.success(rt);
    }
    private List<Map<String, Object>> parseDataB(List<Map<String, Object>> source,Map<String, Object> params) {
        List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();
        if (source == null || source.size() == 0) {
            return rt;
        }
        // 日期分组
        SortedMap<String, List<Map<String, Object>>> dataMap = new TreeMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> temp = null;
        for (Map<String, Object> data : source) {
            String createDate = data.get("dealDate").toString();
            if (dataMap.get(createDate) != null) {
                dataMap.get(createDate).add(data);
            } else {
                temp = new ArrayList<Map<String, Object>>();
                temp.add(data);
                dataMap.put(createDate, temp);
            }
        }
        // 转换格式
        SortedMap<String, Object> rtMap = null;

        for (Map.Entry<String, List<Map<String, Object>>> entry : dataMap.entrySet()) {
            rtMap = new TreeMap<String, Object>();
            rtMap.put("date", entry.getKey());
            params.put("dealDate", entry.getKey());
	         rtMap.put("sum", dealMapper.getPageByType_dayTotal(params));
	         rtMap.put("list", entry.getValue());
	         rt.add(rtMap);
        }
        return rt;
    }
  // private List<Map<String, Object>> parseData1(List<Map<String, Object>> source,Map<String, Object> params) {}

    @Override
    public Result getRefundList(String storeId, Integer currentNum, Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("index", (currentNum - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("start", DateUtil.getFirstDayOnMonth());
        params.put("end", DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> source = this.dealMapper.getRefundByPage(params);
        List<Map<String, Object>> rt = this.parseDataC(source,params);
        return Result.success(rt);
    }

    private List<Map<String, Object>> parseDataC(List<Map<String, Object>> source,Map<String, Object> params) {
        List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();
        if (source == null || source.size() == 0) {
            return rt;
        }
        // 日期分组
        SortedMap<String, List<Map<String, Object>>> dataMap = new TreeMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> temp = null;
        for (Map<String, Object> data : source) {
            String createDate = data.get("dealDate").toString();
            if (dataMap.get(createDate) != null) {
                dataMap.get(createDate).add(data);
            } else {
                temp = new ArrayList<Map<String, Object>>();
                temp.add(data);
                dataMap.put(createDate, temp);
            }
        }
        // 转换格式
        SortedMap<String, Object> rtMap = null;

        for (Map.Entry<String, List<Map<String, Object>>> entry : dataMap.entrySet()) {
            rtMap = new TreeMap<String, Object>();
            rtMap.put("date", entry.getKey());
            params.put("dealDate", entry.getKey());
	         rtMap.put("sum", dealMapper.getRefundByPage_dayTotal(params));
	         rtMap.put("list", entry.getValue());
	         rt.add(rtMap);
        }
        return rt;
    }
    
    
	@Override
	public void updateprintBatch(List<Deal> taskList) {
		  this.dealMapper.updateprintBatch(taskList);
		
	}

	@Override
	public List<Deal> getPrintedStatusList() {
		List<Deal> dealList = this.dealMapper.getPrintedStatus();
		
		return dealList;
	}


	@Override
	public Result queryDealStatistics(String storeId, String role,String queryStoreId, String operatorId, String date, String payType, String tradeType,
			Integer currentNum, Integer pageSize,String printSerial) {
		/**
		 *  1,如果queryStoreId 为空，则根据 storeId，role 查询该用户所属的所有店铺
		 *  2,如果operatorId为空，则根据前一步获得的queryStoreId的值查询所有店员的操作者编号
		 *  3,date的枚举值为：近七天：nearly7，今日：today，昨日：yesterday，本周：thisweek，
		 *  本月：thismonth，上月：lastweek,自定义时间段： yyyy-MM-dd,yyyy-MM-dd （自定义时间段用逗号分隔）;默认值为nearly7
		 *  4,payType  如果为空，则无此项查询条件
		 *  5,tradeType 如果为空，则无此项查询条件
		 */
		StringBuffer sb = new StringBuffer();
		List<Shop> shopList = new ArrayList<Shop>();
		String queryStoreIdStr = ""; 
		if(queryStoreId==null||"".equals(queryStoreId)){
			System.out.println("查询用户权限下的所有店铺");
			if("0".equals(role)){
				//超级管理员    查询 Shop 和  ReputationShop 的  parent_store_id  为 storeId的数据
				 shopList = shopMapper.getSwitchListByParentStoreId(storeId); 
			
			}else {
		    	Shop shop = shopMapper.getShopListByStoreId(storeId);
		    	shopList.add(shop);
				//店长    查询 Shop 和  ReputationShop 的  store_id  为 storeId的数据
			}
			
			System.out.println("shopList.size="+shopList.size());
			 for (Shop shop : shopList) {
				 sb.append(shop.getStoreId()+",");
				}
			 queryStoreIdStr = sb.substring(0, sb.length()-1);
			 System.out.println("查询店铺queryStoreIdStr="+queryStoreIdStr);
			}else{
				queryStoreIdStr = queryStoreId;
				System.out.println("查询店铺queryStoreId="+queryStoreId);
			}

		Map<String,String> timeMap = getDateByStr(date);
		
		String startTime = timeMap.get("startTime");
		String endTime = timeMap.get("endTime");

		String queryTradeType = formatTradeType(tradeType);
		
		System.out.println("queryTradeType="+queryTradeType);
		
		String queryPayType = formatQueryPayType(payType,queryTradeType);
		

		System.out.println("查询条件如下：queryStoreId=【"+queryStoreIdStr+"】,operatorId=【"+operatorId+"】,startTime=【"+startTime+"】,endTime=【"+endTime+"】,queryPayType=【"+queryPayType+"】");
		
		int index = ( (currentNum - 1) * pageSize);

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("queryStoreIdS", queryStoreIdStr);
		param.put("operatorId", operatorId);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("queryPayType", queryPayType);
//		param.put("queryTradeType", queryTradeTypeS);
		param.put("index",index);
		param.put("pageSize", pageSize);
		param.put("printSerial", printSerial);
		
		List<Map<String, Object>> source = null;
		String payTotalCount ="";
		String refundTotalCount ="";
		String totalMoney ="";
		
		if(printSerial!=null&&printSerial.trim().length()>0){
			source = dealMapper.queryDealStatisticsBySerialNum(param);
			System.out.println("查询出一共："+source.size());
			 payTotalCount = dealMapper.queryDealPayCountBySerialNum(param);
			 refundTotalCount = dealMapper.queryDealRefundCountBySerialNum(param);
			 totalMoney = dealMapper.queryDealTotalMoneyBySerialNum(param);
		}else{
			 source = dealMapper.queryDealStatistics(param);
				System.out.println("查询出一共："+source.size());
				 payTotalCount = dealMapper.queryDealPayCount(param);
				 refundTotalCount = dealMapper.queryDealRefundCount(param);
				 totalMoney = dealMapper.queryDealTotalMoney(param);
				
		
		}
		
		
		

		
		if(totalMoney==null||"".equals(totalMoney)){
			totalMoney="0";
		}
		
        // 转换格式
	
		 List<Map<String, Object>> rt = this.parseDataD(source,param);
	      
		 JSONObject ob =new JSONObject();	 
		 ob.put("payTotalCount",payTotalCount);
		 ob.put("refundTotalCount", refundTotalCount);
		 ob.put("totalMoney", totalMoney);
	
        return Result.build(200, "成功", rt, ob.toString());
	}
	
    private List<Map<String, Object>> parseDataD(List<Map<String, Object>> source,Map<String, Object> params) {
        List<Map<String, Object>> rt = new ArrayList<Map<String, Object>>();
        if (source == null || source.size() == 0) {
            return rt;
        }
        // 日期分组
        SortedMap<String, List<Map<String, Object>>> dataMap = new TreeMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> temp = null;
        for (Map<String, Object> data : source) {
            String createDate = data.get("dealDate").toString();
            if (dataMap.get(createDate) != null) {
                dataMap.get(createDate).add(data);
            } else {
                temp = new ArrayList<Map<String, Object>>();
                temp.add(data);
                dataMap.put(createDate, temp);
            }
        }
        // 转换格式
        SortedMap<String, Object> rtMap = null;

        for (Map.Entry<String, List<Map<String, Object>>> entry : dataMap.entrySet()) {
            rtMap = new TreeMap<String, Object>();
            rtMap.put("date", entry.getKey());
            params.put("dealDate", entry.getKey());
	         rtMap.put("sum", dealMapper.queryDealStatistics_dayTotal(params));
	         rtMap.put("list", entry.getValue());
	         rt.add(rtMap);
        }
        return rt;
    }
	
	
	
	public Map<String,String> getDateByStr(String str){
		
		Map<String,String> map = new HashMap<String,String>();
		
		if("nearly7".equals(str)){
			map.put("startTime", DateUtil.getAnyDate(-7));
			map.put("endTime", DateUtil.getAnyDate(0));
		}else if("today".equals(str)){
			map.put("startTime", DateUtil.getAnyDate(0));
			map.put("endTime", DateUtil.getAnyDate(0));
		}else if("yesterday".equals(str)){
			map.put("startTime", DateUtil.getAnyDate(-1));
			map.put("endTime", DateUtil.getAnyDate(-1));
		}else if("thisweek".equals(str)){
			map.put("startTime", DateUtil.getThisWeekStartAndEnd().get("startTime"));
			map.put("endTime", DateUtil.getAnyDate(0));
		}else if("thismonth".equals(str)){
			map.put("startTime", DateUtil.getThisMonthStartAndEndDay().get("startTime"));
			map.put("endTime", DateUtil.getThisMonthStartAndEndDay().get("endTime"));
		}else if("lastweek".equals(str)){
			map.put("startTime", DateUtil.getLastWeekStartAndEnd().get("startTime"));
			map.put("endTime", DateUtil.getLastWeekStartAndEnd().get("endTime"));
		}else if(str.contains(",")){
			map.put("startTime", str.split(",")[0]);
			map.put("endTime", str.split(",")[1]);
		}else{
			System.out.println("警告！没有日期条件，将查询默认最近7天数据");
			map.put("startTime", DateUtil.getAnyDate(-7));
			map.put("endTime", DateUtil.getAnyDate(0));
		}
		return map;
	}
//	receTypeDescMap.put("00", "现金");
//	receTypeDescMap.put("11", "二维码台卡-支付宝");
//	receTypeDescMap.put("12", "支付宝扫码");
//	receTypeDescMap.put("13", "支付宝条码");
//	
//	receTypeDescMap.put("21", "二维码台卡-微信");
//	receTypeDescMap.put("22", "微信扫码");
//	receTypeDescMap.put("23", "微信条码");
//	
//	receTypeDescMap.put("31", "二维码台卡-qq钱包");
//	receTypeDescMap.put("32", "qq钱包扫码");
//	receTypeDescMap.put("33", "qq钱包条码");
	
	
	
	public String formatQueryPayType(String payType,String queryTradeType ){
		StringBuffer sb = new StringBuffer();
		
		if(queryTradeType.equals("1")){  
			
			if(payType==null||"".equals(payType)){
				sb.append("'00','11','12','13','21','22','23','31','32','33','41','42','43','51','52','61',");
			}
			if(payType.contains("二维码台卡")){
				sb.append("'11','21','31',");
			}
			if(payType.contains("支付宝")){
				sb.append("'12','13',");
			}
			if(payType.contains("微信")){
				sb.append("'22','23',");
			}
			if(payType.contains("现金")){
				sb.append("'00',");
			}	
			if(payType.contains("银行")){
				sb.append("'41','42','43','51','52',");
			}
			if(payType.contains("会员余额")){
				sb.append("'61',");
			}
			
		}
		
		if(queryTradeType.equals("2")){  
			
			if(payType==null||"".equals(payType)){
				sb.append("'0','1','2','3','4','5',");
			}
//			if(payType.contains("二维码台卡")){
//				sb.append("");
//			}
			if(payType.contains("支付宝")){
				sb.append("'1',");
			}
			if(payType.contains("微信")){
				sb.append("'2',");
			}
			if(payType.contains("现金")){
				sb.append("'0',");
			}
			if(payType.contains("银行")){
				sb.append("'4','5',");
			}
			if(payType.contains("会员余额")){
				sb.append("'6',");
			}
			
		}
		
	if(queryTradeType.equals("1,2")){	
			
			
		if(payType==null||"".equals(payType)){
			sb.append("'00','11','12','13','21','22','23','31','32','33','41','42','43','51','52','61','0','1','2','3','4','5','6',");
		}
		if(payType.contains("二维码台卡")){
			sb.append("'11','21','31',");
		}
		if(payType.contains("支付宝")){
			sb.append("'12','13','1',");
		}
		if(payType.contains("微信")){
			sb.append("'22','23','2',");
		}
		if(payType.contains("现金")){
			sb.append("'00','0',");
		}
		if(payType.contains("银行")){
			sb.append("'41','42','43','51','52','4','5',");
		}
		if(payType.contains("会员余额")){
			sb.append("'61','6',");
		}
	}
		

		String returnStr = "";
		if(sb.toString().length()>1){
			returnStr = sb.substring(0, sb.length()-1)	;
		}else{
			returnStr = sb.toString();
		}
		
		return returnStr;
	}
	
	public String formatTradeType(String tradeType){
		StringBuffer sb = new StringBuffer();
		if(tradeType==null||"".equals(tradeType)){
			sb.append("1,2");
		}else{
			sb.append(tradeType);
		}
		return sb.toString();
	}



	@Override
	public Result getDealByGiveDateAndStoreID(String storeId,String startDate, String endDate,String page ,String pageSize) {
		
		// 判断日期的输入
		if(StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
			return Result.build(401, "请输入日期");
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		boolean matches = pattern.matcher(startDate).matches() && pattern.matcher(endDate).matches();
		if(!matches){
			return Result.build(401, "输入的日期格式有误");
		}
//		Long time = System.currentTimeMillis();
//		System.out.println(time);
		// 如果没有给数值；默认是 1000条数据
		// 如果没有给数值；默认是 1000条数据
		if (pageSize == null) {
			pageSize = "100";
		}
		if (page == null) {
			page = "0";
		}
		
		
		//查询数据库 获取数据
		
//		Date d = new Date();
		
		Date start = new Date(Long.valueOf(startDate));
		Date end = new Date(Long.valueOf(endDate));
		
		List<Deal> source = null;
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("start", start);
        params.put("end", end);
        params.put("page", Integer.parseInt(page));
        params.put("pageSize", Integer.parseInt(pageSize));

		List<ErpDeal> rsource = new ArrayList<ErpDeal>();
		source = this.dealMapper.getDealByGiveDateAndStoreID(params);
		

		if (source != null && source.size() > 0) {
			for (Deal deal : source) {

				ErpDeal ed = ErpDeal.formatToErpDealToOneCatty(deal);
			    if(ed!=null){
			    	rsource.add(ed);
			    }
			}
		}
        
        // 转化成json 数据
//         String json = JsonUtils.objectToJson(rsource);
//         
//         json =  json.replaceAll("priceDeail", "price").replaceAll("createDateDeail", "createDate");
//         
         
         return Result.success(rsource);
	}

	
}
