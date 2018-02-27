package com.aiiju.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 在ERP系统中使用签名的加密算法（把参数进行运算，得到一个结果）
 * @ClassName: signTool  
 * @Description: 
 * @author 天明
 * @date 2017年7月17日上午11:14:37
 */
public class signTool {
	

	public static String sign(Map<String, String> params)  {
		if(params != null && params.size() > 0){
			
			// 进行排序
			Set<String> keySet = params.keySet();
			TreeSet<String> set = new TreeSet<String>();
			set.addAll(keySet);
			
			// 进行字符串的拼接 +ecbao
			StringBuilder sb = new StringBuilder();
			for (String string : set) {
				sb.append(string);
				sb.append("=");
				sb.append(params.get(string));
			}
			sb.append("ecbao");
		//	System.out.println(sb.toString());
			
//			String result = MD5Utils.md5(sb.toString());
			String result = MD5Util.MD5Encode(sb.toString());
			
			return result;
		}else{
			return null;
		}
		
	}


	public String timeFromate(String time) {
		Date fromat = null;
		try {
			fromat = fromat(time, null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(fromat != null){
			String str = String.valueOf(fromat.getTime());
			return str;
		}
		
		return null;
	}
	
	/**
	 * 格式化指定的日期
	 * 
	 * add by hongen 2012-11-16
	 * 
	 * @param date         要格式化数据字符串
	 * @param dateFormat   日期格式：默认-yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Date fromat(    String date, String dateFormat)
			throws ParseException {

		// 如果要格式化的日期为空，则返回空。
		if (date == null || "".equals(date)) {
			return null;
		}

		// 如果未指定格式，默认：yyyy-MM-dd HH:mm:ss
		if (dateFormat == null || "".equals(dateFormat.trim())) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}

		// 实例化日期格式化工具
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.parse(date);
	}
	 /**
     * 通知ERP系统 有新的   item 同步商品，order订单，refund退款 等变更
     * @param storeId
     * @param whichSync
     */
	public static void ajsyNotify(String storeId, String whichSync) {
		Map<String, String> notifyIDMap = new HashMap<>();
		notifyIDMap.put("storeId", storeId);
		notifyIDMap.put("whichSync", whichSync);

		String signResult = signTool.sign(notifyIDMap);
		notifyIDMap.put("sign", signResult);
		// 验证notify_id
		String doPostStr = HttpClientUtil.doPost("http://121.199.182.2/dsb/auther_ajsy/ajsyNotify", notifyIDMap);
		System.out.println(doPostStr);
	}

	 /**
     * 获取会员支付条码 (测试用到)
     * @param storeId
     * @param whichSync
     */
	public static String getBarCode() {
		Map<String, String> requestMap = new HashMap<>();
	//	notifyIDMap.put("storeId", storeId);
		requestMap.put("vip_id", "1077847101");
		requestMap.put("visit_id", "159929");
	//	http://121.199.182.2/aj_erp/apiManage/Pay/getBarCode?visit_id=159929
		String signResult = signTool.sign(requestMap);
		
		System.out.println(signResult);
		requestMap.put("sign", "9f1bdb11559da04a3e92211bfb32da1a");
		// 验证notify_id
		String doPostStr = HttpClientUtil.doPost("http://121.199.182.2/aj_erp/apiManage/Pay/getBarCode", requestMap);
		
		JSONObject json = JSON.parseObject(doPostStr);
		
		String data = json.getString("data");
		System.out.println(json.toString());
		return data;
	}
	
	 /**
     * 获取会员支付条码 (测试用到)
     * @param storeId
     * @param whichSync
     */
	public static JSONObject balancePay(String price,String tid,String vipCode) {
		Map<String, String> requestMap = new HashMap<>();

		requestMap.put("plat_from", "0");
		requestMap.put("price", price);
		requestMap.put("tid", tid);
		requestMap.put("vipCode", vipCode);
		String signResult = signTool.sign(requestMap);
		
		System.out.println(signResult);
		requestMap.put("sign", signResult);
		// 验证notify_id
		String doPostStr = HttpClientUtil.doPost("http://121.199.182.2/aj_erp/apiManage/Ajsy/doBalancePay", requestMap);
		
		JSONObject json = JSON.parseObject(doPostStr);
		
		String data = json.getString("data");
		System.out.println(json.toString());
		return json;
	}
	
	
	 /**
     * 通知ERP系统 有新的   item 同步商品，order订单，refund退款 等变更
     * @param storeId
     * @param whichSync
     */
	public static void getMemberInfoByBarCode(String storeId, String whichSync) {
		Map<String, String> notifyIDMap = new HashMap<>();
	//	notifyIDMap.put("storeId", storeId);
		
		String vipCode = getBarCode();
		System.out.println("getBarCode()="+vipCode);
		notifyIDMap.put("vipCode", vipCode);

		String signResult = signTool.sign(notifyIDMap);
		
		System.out.println(signResult);
		notifyIDMap.put("sign", signResult);
		// 验证notify_id
		String doPostStr = HttpClientUtil.doPost("http://121.199.182.2/aj_erp/apiManage/Ajsy/vipInfo", notifyIDMap);
		System.out.println(doPostStr);
	}
	
	public static void main(String[] args) {
		
		getMemberInfoByBarCode("", "");
		
	}
	
}
