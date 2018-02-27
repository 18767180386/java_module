package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import net.sf.json.JSONObject;




public class TestAPILocal{
	
	//PRINTER_SN：打印机编号9位,查看机身贴纸
	//KEY：网站上绑定打印机，自动生成KEY，详细咨询客服人员
	public static final String PRINTER_SN = "kdt2100749";//"0101";//"kdt1010236";//"915500404";//"abc1234";//
	public static final String KEY = "008c6";//"eed90" ;//"Q2RaD3lp";//"abc1234";//

	public static final String IP = "http://open.printcenter.cn:8080";
	public static final String HOSTNAME = "/";
	
	//***执行main函数即可测试***  
	public static void main(String[] args) throws Exception{
		
		//==================方法1.打印订单==================
		//***接口返回值有如下几种***
		//{"responseCode":0,"msg":"服务器接收订单成功","orderindex":"xxxxxxxxxxxxxxxxxx"}
		//{"responseCode":1,"msg":"打印机编号错误"};
		//{"responseCode":2,"msg":"服务器处理订单失败"};
		//{"responseCode":3,"msg":"打印内容太长"};
		//{"responseCode":4,"msg":"请求参数错误"};
		//String method1 =queryPrinterStatus(PRINTER_SN,KEY);
		//System.out.println(method1);
		
		String method1 = print(PRINTER_SN,KEY,"1");//第三个参数为打印次（联）数,默认为1
		System.out.println(method1);
		JSONObject resultjson = JSONObject.fromObject(method1);
		
		if(resultjson.has("orderindex")){
			String orderid= resultjson.getString("orderindex");
			String method2 = queryOrderState(PRINTER_SN,KEY,orderid);
			//System.out.println(method2);
		}
		/**/		
		/**/
		//===========方法2.查询某订单是否打印成功=============
		//***返回的状态有如下几种***
	 	//{"responseCode":0,"msg":"已打印"};
		//{"responseCode":0,"msg":"未打印"};
		//{"responseCode":1,"msg":"请求参数错误"};
		//{"responseCode":2,"msg":"没有找到该索引的订单"};
		
		//String orderindex = "1460968128653926118661";//订单索引，从方法1返回值中获取
		//String method2 = queryOrderState(PRINTER_SN,KEY,orderindex);
		//System.out.println(orderindex+"="+method2);
		
		
		//===========方法3.查询指定打印机某天的订单详情============
		//***返回的状态有如下几种*** (print:已打印,waiting:未打印)
	 	//{"responseCode":0,"print":"xx","waiting":"xx"};
		//{"responseCode":1,"msg":"请求参数错误"};
		
		//String date = "2015-03-06";//注意时间格式为"yyyy-MM-dd"
		//String method3 = queryOrderInfoByDate(PRINTER_SN,KEY,date);
		//System.out.println(method3);
		
		
		
		//===========方法4.查询打印机的状态==========================
		//***返回的状态有如下几种***
	    //{"responseCode":0,"msg":"离线"};
	    //{"responseCode":0,"msg":"在线,纸张正常"};
	    //{"responseCode":0,"msg":"在线,缺纸"};
		//{"responseCode":1,"msg":"请求参数错误"};
		
		//String method4 = queryPrinterStatus(PRINTER_SN,KEY);
		//System.out.println(method4);
		//zhougang {"responseCode":"1","msg":"online"} {"responseCode":"3","msg":"offline"}
	}
	
	
	
	
	
	//=====================以下是函数实现部分================================================
	
	//方法1：(参数1：打印机编号,参数2：KEY,参数3:打印次数)
	private static String print(String sn,String strkey,String printtimes) throws Exception{
		//标签说明："<BR>"为换行符,"<CB></CB>"为居中放大,"<B></B>"为放大,"<C></C>"为居中,<L></L>字体变高
		//"<QR></QR>"为二维码,"<CODE>"为条形码,后面接12个数字
		String[] orders={"西红柿鸡蛋","炒饭西红柿鸡蛋炒饭蛋炒饭","炒饭西红柿鸡蛋炒蛋炒饭","鸡蛋炒饭","番茄蛋炒饭","西红柿蛋炒饭","西红柿鸡蛋炒饭","西红柿鸡蛋炒冷饭","西红柿鸡蛋炒(饭)","西红柿鸡蛋狂炒饭","西红柿鸡蛋饭hot"};
		String[] prices={"1.09","10.09","10.09","100.09","1000.09","1000.09","100.09","100.09","100.09","100.09","100.09"};
		String[] sl={"1","10","10","1","1","1","10","10","100","100","100"};
		String hc="\r\n";
		StringBuffer mess=new StringBuffer("");
		mess.append("<B>测试打印</B>\r\n");
//		mess.append("收货人：").append("你妹妹").append(" ").append("18998111555").append(hc);
//		mess.append("地址：").append("美国省").append("洛杉矶市").append("贫民区").append(hc);
//		mess.append("皇后大道东边的北边的西边的南边的后面的前方的1009号三单元999室".substring(0,20)).append(hc);
//		mess.append("皇后大道东边的北边的西边的南边的后面的前方的1009号三单元999室".substring(20)).append(hc);
//		mess.append("运费：").append("100.50").append(hc);
//		mess.append("扫码来源：").append("hanjingqunale?").append(hc);
//		mess.append("附言：").append("都给我来最好的啊").append(hc);
		mess.append("原价:").append("3000.00").append("总数:").append("13").append("优惠:").append("100.01").append(hc);
		mess.append("名称　　　　         单价  数量 \r\n");
		mess.append("--------------------------------\r\n");
		

		for(int i=0;i<orders.length;i++){
			int xh=i;String ordertitle=orders[i];String price=prices[i];String ordernum=sl[i];
			String xhstr= xh<=9?xh+". ":xh+".";
			mess.append(xhstr).append(ordertitle);
			if(EnChValidate.getChineseLength(ordertitle)>16){
				mess.append(hc);
				for(int j=0;j<21;j++){
					mess.append(" "); 				
				}				
			}else{
				for(int j=EnChValidate.getChineseLength(ordertitle);j<18;j++){
					mess.append(" "); 				
				}
			}
			mess.append(price);
			for(int j=EnChValidate.getChineseLength(price);j<8;j++){
				mess.append(" "); 				
			}
			mess.append(ordernum).append(hc); 
			//if(i%3==1)mess.append("我是很长的产品规格").append(";我是更长的买家留言，比长城还长，虽然只有万分之一的客户才能写到这么变态的长留言，但是韩靖说最好还是做，完美一点，我觉得这样也对，但是我为什么不开心呢，还有你是谁啊？又一个妹妹吗？").append(hc); 
		}
		

		//System.out.println(content);
		//System.out.println(bcontent);
		//System.out.println(content.equals(bcontent));
		//if(1==1) return "";
		String content = mess.toString();
		//content = bcontent;
		content += "备注：加辣\r\n";
		content += "--------------------------------\r\n";
		content += "合计：xx.0元\r\n";
		content += "送货地点：广州市南沙区xx路xx号\r\n";
		content += "联系电话：13888888888888\r\n";
		content += "订餐时间：2014-08-08 08:08:08\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		content +="\r\n";
		//content += "<QR>http://www.dzist.com</QR>";
		
	   //通过POST请求，发送打印信息到服务器
	   HttpPost post = new HttpPost(IP+HOSTNAME+"/addOrder");
       HttpClient client = new DefaultHttpClient();
       client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
       client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
       
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("deviceNo",sn));
		nvps.add(new BasicNameValuePair("printContent",content));
		nvps.add(new BasicNameValuePair("key",strkey));
		nvps.add(new BasicNameValuePair("times",printtimes));
		
	    InputStream is = null;
       try
       {
    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
       	   HttpResponse response = client.execute(post);
           int statecode = response.getStatusLine().getStatusCode();
           if(statecode == 200){
	           HttpEntity httpentity = response.getEntity(); 
 	           String strentity = null;
 	            if (httpentity != null){
 	            	is = httpentity.getContent();
 	            	byte[] b = new byte[1024]; 
 	            	int length = 0;
 	            	StringBuilder sb = new StringBuilder();
 	            	while((length=is.read(b))!= -1){
 	            		sb.append(new String(b,0,length));
 	            	}
 	            	strentity = sb.toString();
 	            	return strentity;
 	            }
 	            else{
 	            	 return null;
 	            }
             }else{
            	 return null;
             }
           
       }
       catch (Exception e)
       {
    	   e.printStackTrace();
       }
       finally{
    	   if(is!=null){
    		   try {
				is.close();
			   }
    		   catch (IOException e) {
				e.printStackTrace();
			   }
    	   }
    	   if(post !=null){
    		   post.abort();
    	   }
    	   
       }
       return null;
	  
	}

	
	//方法2 (参数1：打印机编号,参数2：KEY,参数3:订单索引)
	private static String queryOrderState(String sn,String strkey,String strorderindex){
		   HttpPost post = new HttpPost(IP+HOSTNAME+"/queryOrder");
	       HttpClient client = new DefaultHttpClient();
	       client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
	       client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
	       
	       List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	       nvps.add(new BasicNameValuePair("deviceNo",sn));
		   nvps.add(new BasicNameValuePair("key",strkey));
		   nvps.add(new BasicNameValuePair("orderindex",strorderindex));
		   InputStream is = null;
	       try
	       {
	    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
	       	   HttpResponse response = client.execute(post);
	           int statecode = response.getStatusLine().getStatusCode();
	           if(statecode == 200){
		           HttpEntity httpentity = response.getEntity(); 
	 	           String strentity = null;
	 	            if (httpentity != null){
	 	            	is = httpentity.getContent();
	 	            	byte[] b = new byte[1024]; 
	 	            	int length = 0;
	 	            	StringBuilder sb = new StringBuilder();
	 	            	while((length=is.read(b))!= -1){
	 	            		sb.append(new String(b,0,length));
	 	            	}
	 	            	strentity = sb.toString();
	 	            	return strentity;
	 	            }
	 	            else{
	 	            	 return null;
	 	            }
	             }else{
	            	 return null;
	             }
	           
	       }
	       catch (Exception e)
	       {
	    	   e.printStackTrace();
	       }
	       finally{
	    	   if(is!=null){
	    		   try {
					is.close();
	    		   } 
	    		   catch (IOException e) {
					e.printStackTrace();
			 	   }
	    	   }
	    	   if(post !=null){
	    		   post.abort();
	    	   }
	       }
	       return null;
	}
	
	
	//方法3：(参数1：打印机编号,参数2：KEY,参数3:日期)
	private static String queryOrderInfoByDate(String sn,String strkey,String strdate){
		   HttpPost post = new HttpPost(IP+HOSTNAME+"/queryOrderInfoAction");
	       HttpClient client = new DefaultHttpClient();
	       client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
	       client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
	       
	       List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	       nvps.add(new BasicNameValuePair("sn",sn));
		   nvps.add(new BasicNameValuePair("key",strkey));
		   nvps.add(new BasicNameValuePair("date",strdate));
		   InputStream is = null;
	       try
	       {
	    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
	       	   HttpResponse response = client.execute(post);
	           int statecode = response.getStatusLine().getStatusCode();
	           if(statecode == 200){
		           HttpEntity httpentity = response.getEntity(); 
	 	           String strentity = null;
	 	            if (httpentity != null){
	 	            	is = httpentity.getContent();
	 	            	byte[] b = new byte[1024]; 
	 	            	int length = 0;
	 	            	StringBuilder sb = new StringBuilder();
	 	            	while((length=is.read(b))!= -1){
	 	            		sb.append(new String(b,0,length));
	 	            	}
	 	            	strentity = sb.toString();
	 	            	return strentity;
	 	            }
	 	            else{
	 	            	 return null;
	 	            }
	             }else{
	            	 return null;
	             }
	           
	       }
	       catch (Exception e)
	       {
	    	   e.printStackTrace();
	       }
	       finally{
	    	   if(is!=null){
	    		  try {
					is.close();
				  } 
	    		  catch (IOException e) {
					e.printStackTrace();
				  }
	    	   }
	    	   if(post !=null){
	    		   post.abort();
	    	   }
	       }
	       return null;
	}
	
	
	//方法4：(参数1：打印机编号,参数2：KEY)
	private static String queryPrinterStatus(String sn,String strkey){
		   HttpPost post = new HttpPost(IP+HOSTNAME+"/queryPrinterStatus");
	       HttpClient client = new DefaultHttpClient();
	       client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
	       client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
	       
	       List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	       nvps.add(new BasicNameValuePair("deviceNo",sn));
		   nvps.add(new BasicNameValuePair("key",strkey));
		   InputStream is = null;
	       try
	       {
	    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
	       	   HttpResponse response = client.execute(post);
	           int statecode = response.getStatusLine().getStatusCode();
	           if(statecode == 200){
		           HttpEntity httpentity = response.getEntity(); 
	 	           String strentity = null;
	 	            if (httpentity != null){
	 	            	is = httpentity.getContent();
	 	            	byte[] b = new byte[1024]; 
	 	            	int length = 0;
	 	            	StringBuilder sb = new StringBuilder();
	 	            	while((length=is.read(b))!= -1){
	 	            		sb.append(new String(b,0,length));
	 	            	}
	 	            	strentity = sb.toString();
	 	            	return strentity;
	 	            }
	 	            else{
	 	            	 return null;
	 	            }
	             }else{
	            	 return null;
	             }
	           
	       }
	       catch (Exception e)
	       {
	    	   e.printStackTrace();
	       }
	       finally{
	    	   if(is!=null){
	    		   try {
					 is.close();
				   } 
	    		   catch (IOException e) {
					 e.printStackTrace();
				   }
	    	   }
	    	   if(post !=null){
	    		   post.abort();
	    	   }
	       }
	       return null;
	       
	}
	
    
}

