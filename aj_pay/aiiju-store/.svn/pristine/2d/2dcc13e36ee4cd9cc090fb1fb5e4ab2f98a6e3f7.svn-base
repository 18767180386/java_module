package com.aiiju.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.NoteUtil;
import com.aiiju.common.util.signTool;
import com.aiiju.dao.JedisClient;
import com.aiiju.mapper.ShopMapper;
import com.aiiju.pojo.MCard;
import com.aiiju.pojo.Member;
import com.aiiju.store.service.ScrmService;
import com.aiiju.store.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.sf.jsqlparser.expression.operators.arithmetic.Addition;

/**
 * 
* @ClassName: ScrmServiceImpl 
* @Description: scrm对接接口
* @author qiyu
* @date 2017年3月22日 上午10:14:06
 */
@Service("scrmService")
public class ScrmServiceImpl implements ScrmService {
	
	private static Logger logger = LoggerFactory.getLogger(ScrmServiceImpl.class);
	
	
	@Autowired
	private ShopMapper shopMapper;
	
    @Autowired
    private JedisClient jedisClient;
	

	@Override
	public Result getScrmMemberInfo(Map<String, Object> map) {
		
		String query = map.get("query")==null?null:map.get("query").toString();
		String shopId = map.get("shop_id")==null?null:map.get("shop_id").toString();
		if(StringUtil.isBlank(query,shopId)){
			return Result.build(400, "请求失败：缺少参数 query或shopId",logger);
		}
		
		//调用secm接口 获取会员卡信息-
		String scrmJson = NoteUtil.getScrm("https://scrm.ecbao.cn/openapi/MemberCard/query", map,1);
		if(scrmJson==null){
			return Result.build(401, "请求失败：远程调用scrm接口错误",logger);
		}
		JSONObject json = JSONObject.parseObject(scrmJson);
		String code =   json.getString("code");
		if(!"0".equals(code)){
			return Result.build(401,json.getString("data") );
		}
		
		
		JSONArray jsonArray = json.getJSONArray("data");
		
		//封装到实体集合中
		List<MCard> list = new ArrayList<>();
		Map<String,Object> resultMap = new HashMap<>();
		String phone="";
		if(jsonArray!=null && jsonArray.size()>0){
			MCard mCard = null;
			for (Object obj : jsonArray) {
				mCard= new MCard();
				JSONObject jsonObject  = (JSONObject) obj;
				JSONObject cardObject = jsonObject.getJSONObject("card");
				//判断该会员卡是否可用，而且拥有折扣和卡号 
				if("true".equals(cardObject.getString("is_discount"))&&!StringUtil.isBlank(cardObject.get("discount"))){
					
					//卡号
					JSONObject holder = jsonObject.getJSONObject("holder");
					if(holder==null||StringUtil.isBlank(holder.get("card_code"))){
						 continue;
					}
					mCard.setCode(holder.getString("card_code"));
					
					if(StringUtil.isBlank(phone)){
						phone=holder.getString("phone");
					}
					
					//当没有会员卡名字默认scrm会员卡
					JSONObject baseInfo = cardObject.getJSONObject("base_info");
					if(baseInfo!=null&&!StringUtil.isBlank(baseInfo.get("title"))){
						mCard.setName(baseInfo.getString("title"));
					}else{
						mCard.setName("scrm会员卡");
					}
					
					//折扣
					mCard.setDiscount(cardObject.getString("discount"));
					
					list.add(mCard);
				}
			}
			resultMap.put("list", list);
			resultMap.put("phone", phone);
		}
		
		return   Result.success(resultMap);
	}
	
	
	@Override
	public Result getRelativeScrm(Map<String, Object> map) {
		
		if(StringUtil.isBlank(map.get("storeId"))){
			return Result.build(400, "缺少参数：storeId", logger);
		}
		
		String gelativeScrm = shopMapper.getRelativeScrm(map);
		return Result.success(gelativeScrm);
	}





	@Override
	public Result getRelativeScrmShopList(Map<String, Object> map) {
		String pStoreId = map.get("pStoreId")==null?null:map.get("pStoreId").toString();   //操作店铺的父店铺storeId
		String scrmId = map.get("scrmId")==null?null:map.get("scrmId").toString();   //SCRM公司id
		String role = map.get("role")==null?null:map.get("role").toString();   //店铺角色
		if(StringUtil.isBlank(pStoreId,scrmId,role)){
			return Result.build(400, "缺少参数：pStoreId或scrmId或role", logger);
		}
		if(!role.equals("0")&&!role.equals("1")){
			return Result.build(401, "请求失败，用户没有该权限", logger);
		}
		
		List<Map<String, Object>> resultList = shopMapper.getRelativeScrmShopList(map);
		
		return Result.success(resultList);
	}


	@Override
	public Result updateUnwrapScrm(Map<String, Object> map) {
		String scrmId = map.get("scrmId")==null?null:map.get("scrmId").toString();//SCRM公司id
		String storeIds = map.get("storeIds")==null?null:map.get("storeIds").toString();   //剩下绑定的店铺storeIds
		String requtestTime = map.get("requtestTime")==null?null:map.get("requtestTime").toString();
    	String sign = map.get("sign")==null?null:map.get("sign").toString();
    	
    	if(storeIds==null){
    		return Result.build(400, "缺少参数：storeIds", logger);
    	}
    	if(StringUtil.isBlank(scrmId,requtestTime,sign)){
    		return Result.build(400, "缺少参数：scrmId、requtestTime或sign", logger);
    	}
		
		if(isOutOfTime(requtestTime)){
    		return Result.build(401, "请求已经超时",logger);
    	}
		
    	// 判断签名
		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("scrmId", scrmId);
		signMap.put("storeIds", storeIds);
		signMap.put("requtestTime",requtestTime);
		String signResult = signTool.sign(signMap);
		if(!sign.equalsIgnoreCase(signResult)){
			return Result.build(401, "sign 签名有误",logger);
		}
		
		
		//scrm 只会把解绑后，还绑定的storeIds传了过来，先与他同步，有多出来的店铺就把他解绑
		//当storeIds长度小于0,不执行同步
		if(storeIds.trim().length()>0){
			map.put("storeIds", storeIds.split(","));
			map.put("relativeScrm", scrmId);
			shopMapper.updateShopListSCRM(map); //同步
		}
		
		//storeIds长度小于0，说明全部解绑 ,去掉 storeIds 参数
		if(storeIds.trim().length()<=0){
			map.put("storeIds", null);
		}
		String unnecessarystoreIds = shopMapper.getUnnecessaryScrm(map); //多余的绑定店铺，需要解绑
		if(!StringUtil.isBlank(unnecessarystoreIds)){
			map.put("relativeScrm", null);
			map.put("storeIds", unnecessarystoreIds.split(","));
			shopMapper.updateShopListSCRM(map);
		}
		return  Result.success();
	}


	@Override
	public Result updateBindScrm(Map<String, Object> map) {
		String scrmId = map.get("scrmId")==null?null:map.get("scrmId").toString();//SCRM公司id
		String storeIds = map.get("storeIds")==null?null:map.get("storeIds").toString();   //新增绑定的店铺storeIds
		String pStoreId = map.get("pStoreId")==null?null:map.get("pStoreId").toString();   //操作店铺的父店铺storeId
		String role = map.get("role")==null?null:map.get("role").toString();   //操作人权限
		String rId = map.get("rId")==null?null:map.get("rId").toString();				//scrm的token
		
		
		if(StringUtil.isBlank(storeIds,scrmId,rId,role)){
			return Result.build(400, "缺少参数：storeIds、scrmId、rId、role或pStoreId", logger);
		}
		if(!role.equals("0")&&!role.equals("1")){
			return Result.build(401, "请求失败，用户没有该权限", logger);
		}
		
		
		
		//存到缓存中，scrm回调时使用
		jedisClient.setex("SCRMPSTOREID:"+rId, 120, pStoreId);
		jedisClient.setex("SCRMSCRMID:"+rId, 120, scrmId);
		jedisClient.setex("SCRMSTOREIDS:"+rId, 120, storeIds);
		
		return Result.success();
	}


	@Override
	public Result updateScrmGrant(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String requtestTime = map.get("requtestTime")==null?null:map.get("requtestTime").toString();
    	String rId = map.get("rId")==null?null:map.get("rId").toString();
    	String sign = map.get("sign")==null?null:map.get("sign").toString();
    	
    	// 判断请求是否超时 
    	if(isOutOfTime(requtestTime)){
    		return Result.build(401, "请求已经超时",logger);
    	}
    	if(StringUtil.isBlank(rId,sign)){
    		return Result.build(401, "缺少参数 sign或rId",logger);
    	}
    	
    	// 判断签名
		Map<String, String> signMap = new HashMap<String, String>();
		signMap.put("rId", rId);
		signMap.put("requtestTime",requtestTime);
		String signResult = signTool.sign(signMap);
		
		if(!sign.equalsIgnoreCase(signResult)){
			return Result.build(401, "sign 签名有误",logger);
		}
		
		
		String pStoreId = jedisClient.get("SCRMPSTOREID:"+rId);
		String scrmId = jedisClient.get("SCRMSCRMID:"+rId);
		String storeIds = jedisClient.get("SCRMSTOREIDS:"+rId);
		
		if(StringUtil.isBlank(pStoreId,scrmId,storeIds)){
			return Result.build(401, "没有授权信息，获取授权的店铺的数量为0",logger,pStoreId+":"+scrmId+":"+storeIds);
		}
		
		
		//绑定SCRM
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("storeIds", storeIds.split(","));
		paramMap.put("relativeScrm", scrmId);
		paramMap.put("pStoreId", pStoreId);
		shopMapper.updateShopListSCRM(paramMap);
		
		
		
		//查询绑定店铺和其父店铺的信息，返回
		List<Map<String,Object>> grantShop = new ArrayList();
		List<Map<String,Object>> pshop = new ArrayList();
		
		List<Map<String, Object>> scrmList = shopMapper.getScrmList (paramMap);
		
		
		//用mark等于1 区分，sql中返回的数据
		if(scrmList!=null&&scrmList.size()>0){
			for (Map<String, Object> shop : scrmList) {
				if(shop.get("mark")!=null&&"1".equals(shop.get("mark").toString())){
					pshop.add(shop);
					scrmList.remove(shop);
					break;
				}
			}
			grantShop=scrmList;
		}
		
		jedisClient.delList("SCRMPSTOREID:"+rId,"SCRMSCRMID:"+rId,"SCRMSTOREIDS:"+rId);//删除缓存
		
		
		resultMap.put("grantShop", grantShop);
		resultMap.put("pshop", pshop);
		
		return Result.success(resultMap);
	}

    /**
     * 判断发出请求的时间是否已经超时（true 表示超时 FALSE 表示没有超时）
     * 如果时间超过5分钟，就是超时，没有超过 就是没有超时
     * @return TRUE表示超时，FALSE表示没有超时 
     */
    public boolean isOutOfTime(Object requestTime){
    	if(StringUtil.isBlank(requestTime)){
    		return true;
    	}
    	if(!Pattern.matches("[0-9]+", requestTime.toString())){
    		return true;
    	}
    	long nowTime = new Date().getTime();
    	long   postTime =Long.valueOf(requestTime.toString());
    	if(nowTime -postTime < 1000*60*5 && nowTime -postTime > -1000*60*5){
    		return false;
    	}
		return true;
    }
    
    
    public static void main(String[] args) {
    	boolean outOfTime = new ScrmServiceImpl().isOutOfTime("1506501394211");
    	System.out.println(outOfTime);
	}
}
