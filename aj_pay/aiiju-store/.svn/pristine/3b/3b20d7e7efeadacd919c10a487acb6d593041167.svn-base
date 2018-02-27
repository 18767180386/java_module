package com.test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.aiiju.pojo.DealDetail;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestHttp {

	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		String s = UUID.randomUUID().toString();//用来生成数据库的主键id非常不错..
		System.out.println(s);
		System.out.println(s.length());
		
		System.out.println("20161205210010041".length());
		
		
		
		int totalcount =0;
 		BigDecimal totalsum = new BigDecimal("0.00") ; //￥101111.99
 		System.out.println(totalcount);
 		
 		List list =new ArrayList();
 		
 		 JSONObject json = new JSONObject();
 		
          for (int i=0;i<5;i++) {
        	
        	  list.add("你好");
        	//  totalsum=totalsum.add(new BigDecimal("2.01"));
		   }
		System.out.println(list);
		
		json.put("str", list);
		
	List<String> newlist = 	(List<String>) json.get("str");
	
		JSONArray a=  (JSONArray) json.get("str");
	//String[] straa =newlist.toArray(a);
	//System.out.println("a="+a.t);
		
		
		 
	
	 for (Object object : a) {
		System.out.println(object);
	}
	
		
//		while(true){
//			
//		System.out.println(queryPrinterStatus());	;
//			String orderindex=	addOrder();
//		//	queryOrder(orderindex);
//		
//			try {
//				Thread.sleep(1000000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		
		
		

      
	}
	
	
	
	public static String addOrder(){
		String url ="http://open.printcenter.cn:8080/addOrder";
	
		
		
		String param="deviceNo=kdt2100749&key=008c6&printContent="+TestChineseEnglisth.template("")+"&times=1";
		
		  String sr=HttpRequest.sendPost(url, param);
	        try {
				System.out.println(new String(sr.getBytes(),"utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        return JSONObject.fromObject(sr).getString("orderindex");
	}
	public static void queryOrder(String orderindex){
		String url ="http://open.printcenter.cn:8080/queryOrder";
		String param="deviceNo=kdt2100749&key=008c6&orderindex="+orderindex;
		  String sr=HttpRequest.sendPost(url, param);
	        try {
				System.out.println(new String(sr.getBytes(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	public static boolean queryPrinterStatus(){
		String url ="http://open.printcenter.cn:8080/queryPrinterStatus";
		String param="deviceNo=kdt2100749&key=008c6";
		  String sr=HttpRequest.sendPost(url, param);
	        try {
				System.out.println(new String(sr.getBytes(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	     return     JSONObject.fromObject(sr).getString("msg").contains("离线")?false:true;
	        
	}

}
