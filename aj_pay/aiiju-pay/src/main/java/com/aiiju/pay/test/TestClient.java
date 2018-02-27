package com.aiiju.pay.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

public class TestClient {

	public static void main(String[] args) throws AlipayApiException {
		
		pay();
	
	
	}

	public static  void test1() throws AlipayApiException{
		AlipayClient alipayClient = AlipayUtil.getAlipayClient();
		AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
		request.setBizContent("{" +
		"    \"app_auth_token\":\"201707BBdbe1ab902ed6458c8e4e57ac6290bE66\"" +
		"  }");	
		
		AlipayOpenAuthTokenAppQueryResponse response = alipayClient.execute(request);
		
		System.out.println(response.getStatus());
		System.out.println(response.getBody());
		System.out.println(response.getAuthStart()+"--->"+response.getAuthEnd());

		System.out.println(response.getBody());
		
		if(response.isSuccess()){
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}
		
	}
	
	public static  void test2() throws AlipayApiException{
		AlipayClient alipayClient = AlipayUtil.getAlipayClient();
	AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
	request.setBizContent("{" +
	"    \"grant_type\":\"refresh_token\"," +
	"    \"refresh_token\":\"201707BBdbe1ab902ed6458c8e4e57ac6290bE66\"" +
	"  }");
	AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
	System.out.println("-------------------------------------");
	System.out.println(response.getAppAuthToken());
	System.out.println(response.getAppRefreshToken());
	System.out.println(response.getAuthAppId());
	System.out.println(response.getBody());
	System.out.println(response.getCode());

	System.out.println(response.getExpiresIn());
	System.out.println(response.getMsg());
	System.out.println(response.getReExpiresIn());
	System.out.println(response.getSubCode());
	System.out.println(response.getUserId());
	System.out.println(response.getParams());
	System.out.println("-------------------------------------");
	
	
	}
	
	public static void pay() throws AlipayApiException{
		
		AlipayClient alipayClient = AlipayUtil.getAlipayClient();

		
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		request.setBizContent("{" +
		"\"out_trade_no\":\"20150320010101003\"," +
		"\"seller_id\":\"2088102146225135\"," +
		"\"total_amount\":88.88," +
		"\"discountable_amount\":8.88," +
		"\"subject\":\"Iphone6 16G\"," +
		"\"body\":\"Iphone6 16G\"," +
		"\"buyer_id\":\"208810214622513511\"," +
		"      \"goods_detail\":[{" +
		"        \"goods_id\":\"apple-01\"," +
		"\"goods_name\":\"ipad\"," +
		"\"quantity\":1," +
		"\"price\":2000," +
		"\"goods_category\":\"34543238\"," +
		"\"body\":\"特价手机\"," +
		"\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
		"        }]," +
		"\"operator_id\":\"Yx_001\"," +
		"\"store_id\":\"NJ_001\"," +
		"\"terminal_id\":\"NJ_T_001\"," +
		"\"extend_params\":{" +
		"\"sys_service_provider_id\":\"2088511833207846\"" +
		"    }," +
		"\"timeout_express\":\"90m\"," +
		"\"business_params\":\"{\\\"tableNumber\\\":\\\"xx001”}\"" +
		"  }");
		AlipayTradeCreateResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
		
		
	}
	
	
	
}
