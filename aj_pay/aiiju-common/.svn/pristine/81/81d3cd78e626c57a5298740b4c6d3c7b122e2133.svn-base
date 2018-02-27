package com.aiiju.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: NoteUtil 
 * @Description: 短信发送工具
 * @author 小飞 
 * @date 2016年12月29日 下午7:49:43 
 *
 */
public class NoteUtil {
	
	private static Logger logger = LoggerFactory.getLogger(NoteUtil.class);
	
	
	private NoteUtil() {}

	public static String sendNote(String phone, String message) {
		//设置参数
		SortedMap<String,String> param = new TreeMap<>();
		param.put("appkey", "5579266332934220");
	//	param.put("keysecret", "93e48b89d6eb1323f32d855b1318939b");
		param.put("message", message);
		param.put("msg_sign", "爱聚收银记账");
		param.put("phone", phone);
		param.put("timestamp", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		param.put("type", "tz");
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			sb.append(entry.getKey()).append(entry.getValue());
		}
		
		String sign = getSign(sb.toString());
		param.put("sign", sign);
		//调用短信API
		String codeResult = HttpClientUtil.doPost("http://scrm.ecbao.cn/openapi/sms/single_send", param);
		System.out.println("短信调用返回："+codeResult);
		return codeResult;
	}
	
	public static String getShortUrl(String url) {
		//设置参数
		SortedMap<String,String> param = new TreeMap<>();
		param.put("appkey", "5579266332934220");
		param.put("timestamp", DateUtil.formatDate(new Date(), "yyyy-M-dd HH:mm:ss"));
		param.put("url", url);
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			sb.append(entry.getKey()).append(entry.getValue());
		}
		String sign = getSign(sb.toString());
		param.put("sign", sign);
		//长转短地址API

		String codeResult = HttpClientUtil.doPost("http://scrm.ecbao.cn/openapi/sms/shortUrlAdd.json", param);
		
		System.out.println("codeResult="+codeResult);
		   JSONObject json = 	JSONObject.fromObject(codeResult);
		   Integer code =  (Integer) json.get("code");
		   
		   if(code==0){
			   JSONObject data =  (JSONObject) json.get("data"); 
			   String shortUrl = data.getString("url");
			   System.out.println("短地址："+shortUrl);
			   return shortUrl;
			   
		   }else{
			   System.out.println("长地址转短地址错误");
			   return null;
		   }		
	}
	
	/**
	 * 调用scrm接口 通用函数
	 * @param url   调用地址
	 * @param map	调用scrm接口参数
	 * @param type	发请求是是否携带通用参数  ；1 携带appkey和timestamp 
	 * @return  
	 */
	public static String getScrm(String url,Map<String, ? extends Object> map,int  type) {
		//设置参数
		SortedMap<String,String> param = new TreeMap<>();
		param.putAll((Map< String, String>) map);
		if(type==1){
			param.put("appkey", "5579266332934220");
			param.put("timestamp", DateUtil.formatDate(new Date(), "yyyy-M-dd HH:mm:ss"));
		}
		
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : param.entrySet()) {
			sb.append(entry.getKey()).append(entry.getValue());
		}
		String sign = getSign(sb.toString());
		param.put("sign", sign);

		String codeResult = HttpClientUtil.doPost(url, param);
		
		   JSONObject json = 	JSONObject.fromObject(codeResult);
		   String code =   json.getString("code");
		   
		   if(!"0".equals(code)){
			   
			   logger.error("----------------远程调用scrm接口错误-----------------");
			   logger.error("----------url:"+url+"---------param:"+param+"-----------");
			   logger.error("--------------json:"+json+"--------------");
			   
		   }		
		   return codeResult;
	}
	
	public static String getSign(String sb){
		
		String sign = sb.toString()+"keysecret"+"93e48b89d6eb1323f32d855b1318939b";
		//System.out.println("签名数据："+sign);
		String returnsign = MD5Util.MD5Encode(sign);
		//System.out.println("MD5签名后数据："+returnsign);
		return returnsign;
	}
	

	
     public static void main(String[] args) throws UnsupportedEncodingException {
    //	 String data ="\u53d1\u9001\u6210\u529f";
//    		String data = "\u77ed\u4fe1\u53d1\u9001\u8fc7\u4e8e\u9891\u7e41,\u8bf7\u7b49\u5f85\u4e00\u5206\u949f\u540e\u5728";
//    		
//    		System.out.println(data);
    		
 		//  String error =unicode2String(data);
 		   
 		//   System.out.println("error="+error);
    	 
//    	 String url = "http://16794ui705.iok.la:32542/user/inviteUI?shopName=宗介&fromPhone=18257128735&toPhone=15000306929";
//    	 
//    	
//    	 String INVITE_URL = "https://store.ecbao.cn/user/inviteUI" ;
//    	 
    	 String codeResult = NoteUtil.sendNote("18257128735", "您的验证码为"+12323+",请及时处理" );
//    	 
//    	 
     //	 String codeResult1 =  getShortUrl(url);
     	System.out.println("转换地址返回的数据："+codeResult);
     	

     
	}
     
     /**
      * unicode 转字符串
      */
//     public static String unicode2String(String unicode) {
//    	 
//    	 System.out.println("要轉化的unicode="+unicode);
//      
//         StringBuffer sb = new StringBuffer();
//      
//         if(unicode.contains(",")){
//        	 
//        	 System.out.println("包含，");
//        	 
//        	 String[] unicodes = unicode.split(",");
//        	 
//        	 for (String string2 : unicodes) {
//        		 
//        	       String[] hex = string2.split("\\u");
//        	       
//        	         for (int i = 1; i < hex.length; i++) {
//        	      
//        	             // 转换出每一个代码点
//        	             int data = Integer.parseInt(hex[i], 16);
//        	            System.out.println("字符1："+(char) data);
//        	             // 追加成string
//        	             sb.append((char) data);
//        	         }
//        	      
//				
//			}
//        	 
//         }else{
//        	 System.out.println("不包含，");
//    		 
//  	       String[] hex = unicode.split("\\u");
//  	       
//  	         for (int i = 1; i < hex.length; i++) {
//  	      
//  	             // 转换出每一个代码点
//  	             int data = Integer.parseInt(hex[i], 16);
//  	            System.out.println("字符2："+(char) data);
//  	             // 追加成string
//  	             sb.append((char) data);
//  	         }
//  	      
//			
//  	      
//         }
//         
//  
//         return sb.toString();
//     }
     
}
