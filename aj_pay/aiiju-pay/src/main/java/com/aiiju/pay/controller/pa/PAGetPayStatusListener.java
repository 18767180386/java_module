package com.aiiju.pay.controller.pa;

import java.util.HashMap;
import java.util.Map;

import com.aiiju.common.pa_utils.shoppay.ShopPayUtils;
import com.aiiju.common.pa_utils.shoppay.TLinx2Util;
import com.aiiju.common.pa_utils.shoppay.TLinxAESCoder;
import com.aiiju.pojo.Order;

import net.sf.json.JSONObject;

/** 
 * @ClassName PaGetPayStatusListener
 * @Description
 * @author zong
 * @CreateDate 2017年8月24日 下午11:47:21
 */
public class PAGetPayStatusListener {
	
	
    //循环调用查询订单支付状态的次数
    private static int loopCount = 3;

    
    private static int firstWaitingTime = 1000;
    
    //每次循环的时间间隔
    private static int waitingTime = 5000;
	
	
	public static String getPayStatus(Order order){
		
		
		Map<String,String> map = new HashMap<String,String>();
		boolean flag = loopSelectPayStatus(order,map);
		
		String returnStatus = map.get("returnStatus");
		
		if("1".equals(returnStatus)||"4".equals(returnStatus)){
			 return returnStatus;
		}
		if("2".equals(returnStatus)||"9".equals(returnStatus)){
			
			System.out.println("多次查询付款状态未果，所以开始取消该交易");
			// 此时还不成功，则发情取消支付
			Integer code = payCancel(order);
			
			
			
			if(code==0){
				System.out.println("发出了取消接口，取消成功");
				return returnStatus;
			}else{
				
				System.out.println("警告：发出了取消接口，但是没有取消成功，So ,再次查询付款状态");
				boolean flag1 = selectPayStatus(order,map);
				String returnStatus_new = map.get("returnStatus");
				return returnStatus_new;
			}
			
		}
		
		return "-1";
	}
	
	
	public static boolean loopSelectPayStatus(Order order,Map<String,String> map){
		
		// 1,先查询一次支付状态
		boolean flag1 = selectPayStatus(order,map);
		
		if(flag1){
			return true;
		}else{
			try {
				 Thread.sleep(firstWaitingTime);
			      for (int i = 0; i < loopCount; i++) {
			            if (selectPayStatus(order,map)) {
			                return true;
			            }
			            Thread.sleep(waitingTime);       
			        }
				return false;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return  false;
		
	}
	
	
	
	public static boolean selectPayStatus(Order order,Map<String,String> map){
		
		JSONObject payStatusJson = ShopPayUtils.queryPayStatus(order);
		
		Integer payStatusErrcode = (Integer) payStatusJson.get("errcode");
	
          
		 if(payStatusErrcode!=0){
			 map.put("returnStatus", "-1");
			 return false;
		 }else{
			 // 成功了
			 JSONObject json =  JSONObject.fromObject(getReturnData(payStatusJson, order));
			 if(json!=null&&json.containsKey("status")){	 
			 String status = json.getString("status");
			 
			 if("1".equals(status)){
				 // 交易成功
				 map.put("returnStatus", "1");
				 System.out.println("当前付款状态：付款成功");
				 return true;
				 
			 }else if("2".equals(status)){
				 // 待支付
				 map.put("returnStatus", "2");
				 System.out.println("当前付款状态：待支付");
				 return false;
				 
			 }else if("4".equals(status)){
				 // 已取消
				 map.put("returnStatus", "4");
				 System.out.println("当前付款状态：交易已取消");
				 return true;
				 
			 }else if("9".equals(status)){
				 // 等待用户输入密码
				 map.put("returnStatus", "9");
				 System.out.println("当前付款状态：等待用户输入密码");
				 return false;
				 
			 }else{
				 map.put("returnStatus", "-1");
				 System.out.println("当前付款状态：未知状态");
				 return false;
			 }
		 
			 }else{
				 map.put("returnStatus", "-1");
				 System.out.println("当前付款状态：接口中没有返回交易状态标示status");
				 return false;
			 }
		 }

	}
	
	
	
	public static Integer payCancel(Order order){
		
		JSONObject payCancelJson = ShopPayUtils.payCancel(order);
		
		Integer payCancelErrcode = (Integer) payCancelJson.get("errcode");
		//String  payCancelMsg = (String) payCancelJson.get("msg");
          
		 if(payCancelErrcode!=0){			

		 }else{
			 // 成功了
			 JSONObject json =  JSONObject.fromObject(getReturnData(payCancelJson, order));
		
		 }
      return payCancelErrcode;
	}
	
	
	
	public static String getReturnData(JSONObject respObject,Order order){
		
		   String open_key = order.getOpenKey();		
	       Object dataStr    = respObject.get("data");
	       String respData = null;
           if ( dataStr != null ) {
               if (TLinx2Util.verifySign(respObject,open_key)) {    // 验签成功

                   /**
                    * 5 AES解密，并hex2bin
                    */
				try {
					respData = TLinxAESCoder.decrypt(dataStr.toString(), open_key);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                   System.out.println("==================响应data内容:" + respData);
               
               } else {
                   System.out.println("=====验签失败=====");
               }
           } else {
               System.out.println("=====没有返回data数据=====");
           }
          return respData ;
	}
	

}
