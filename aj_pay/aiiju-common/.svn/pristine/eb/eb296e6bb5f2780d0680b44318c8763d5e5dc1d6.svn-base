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

public class ErpMemBalanceUtil {
	

	public static String sign(Map<String, String> params)  {
		if(params != null && params.size() > 0){
			
			// 进行排序
			Set<String> keySet = params.keySet();
			TreeSet<String> set = new TreeSet<String>();
			set.addAll(keySet);
			StringBuilder sb = new StringBuilder();
			for (String string : set) {
				sb.append(string);
				sb.append("=");
				sb.append(params.get(string)==null?"":params.get(string));
			}
			sb.append("ecbao");
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
     * 获取会员支付条码 (测试用到)
     * @param storeId
     * @param whichSync
     */
	public static String getBarCode() {
		Map<String, String> requestMap = new HashMap<>();
		//测试
		requestMap.put("vip_id", "1077847101");
		requestMap.put("visit_id", "159929");
//		//正式
//		requestMap.put("vip_id", "964288814");
//		requestMap.put("visit_id", "180579");
		String signResult = ErpMemBalanceUtil.sign(requestMap);
		
		System.out.println(signResult);
		requestMap.put("sign", signResult);
		// 验证notify_id
		String doPostStr = HttpClientUtil.doPost(PropertiesUtil.getPropertyByKey("aj_erp_url")+"/Pay/getBarCode", requestMap);
		
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
	public static JSONObject balancePay(String price,String tid,String vipCode, String storeId) {
		Map<String, String> requestMap = new HashMap<>();

		requestMap.put("plat_from", "0");  //平台类型，0-门店，1-微商城
		requestMap.put("price", price);
		requestMap.put("tid", tid);//平台订单号
		requestMap.put("vipCode", vipCode);//条形码
		
		requestMap.put("visit_id", "159929");
		
		String signResult = ErpMemBalanceUtil.sign(requestMap);
		
		System.out.println(signResult);
		requestMap.put("sign", signResult);
		// 验证notify_id
		
		//因无测试服务器，用正式服务器测试账号的请求指向erp测试(只用于lssg服务器)
//		String url = PropertiesUtil.getPropertyByKey("aj_erp_url");
//		if("10000012".equals(storeId)){
//			url ="http://121.199.182.2/aj_erp/apiManage";
//		}
//		
//		String doPostStr = HttpClientUtil.doPost(url+"/Ajsy/doBalancePay", requestMap);
		//这是正式代码
		String doPostStr = HttpClientUtil.doPost(PropertiesUtil.getPropertyByKey("aj_erp_url")+"/Ajsy/doBalancePay", requestMap);
		JSONObject json = JSON.parseObject(doPostStr);
		
		String data = json.getString("data");
		System.out.println(json.toString());
		return json;
	}
	
	
	 /**
     * 获取会员条形码（测试用到）
     * @param storeId
     * @param whichSync
     */
	public static void getMemberInfoByBarCode(String barCode) {
		Map<String, String> notifyIDMap = new HashMap<>();
	//	notifyIDMap.put("storeId", storeId);
		
	
		notifyIDMap.put("vipCode", barCode);

		String signResult = ErpMemBalanceUtil.sign(notifyIDMap);
		
		System.out.println(signResult);
		notifyIDMap.put("sign", signResult);
		// 验证notify_id
		
		
		
		String doPostStr = HttpClientUtil.doPost(PropertiesUtil.getPropertyByKey("aj_erp_url")+"/Ajsy/vipInfo", notifyIDMap);
		System.out.println(doPostStr);
	}
	
	
	 /**
     * 获取会员信息 (erp)
     * @param storeId
     * @param whichSync
     */
	public static JSONObject getErpMemberInfo(String vipCode,String visitId) {
		
		Map<String, String> requestMap = new HashMap<>();
			requestMap.put("vipCode", vipCode);
		requestMap.put("visit_id", visitId);//公司id
		String signResult = ErpMemBalanceUtil.sign(requestMap);
		
		System.out.println(signResult);
		requestMap.put("sign", signResult);
		// 验证notify_id
		
		//因无测试服务器，用正式服务器测试账号的请求指向erp测试(只用于lssg服务器)
//		String url = PropertiesUtil.getPropertyByKey("aj_erp_url");
//		if("159929".equals(visitId)){
//			url ="http://121.199.182.2/aj_erp/apiManage";
//		}
//		
//		String doPostStr = HttpClientUtil.doPost(url+"/Ajsy/vipInfo", requestMap);
		
		//这是正式代码
		String doPostStr = HttpClientUtil.doPost(PropertiesUtil.getPropertyByKey("aj_erp_url")+"/Ajsy/vipInfo", requestMap);
		
		JSONObject json = JSON.parseObject(doPostStr);

		return json;
	}
	
	public static void main(String[] args) {
		
		
		balancePay("11.00","10000012XS15066626134002903210","18867792018","159929");
		
	}
	
}
