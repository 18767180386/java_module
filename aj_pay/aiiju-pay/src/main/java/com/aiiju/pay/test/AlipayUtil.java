package com.aiiju.pay.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

/**
 * 
 * @ClassName: AlipayUtil 
 * @Description: 支付宝工具类
 * @author 小飞 
 * @date 2016年11月29日 下午1:51:45 
 *
 */
public class AlipayUtil {

	public static final String PID = "2088801006306668";
	public static final String APP_ID = "2016110202477341";
	public static final String APP_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKaf3u1yscM+Q0KJ Eq2r7LgLJnrnK0CuxkDUtsu2EfRdBQZPT/vq8AlRHCto7UgVOIdGRJPTsbB/JHEj 0bNpuU0zSMC/G6perVlzUnQcwimylHpoHlv4hVc9DC6KjaHyAF9ctwWjE9A+5HkU 9KmZx++wGq8e/MCI6HgZqKivvYCRAgMBAAECgYA+1DmzP3RYMroZ9KXeZt2z6EBy R4i/syd+ercSyWyrwAeNAYsfas9oM/VzSPVwINBX8d8Z/tEZxFdxchg4lr0QC2vz 2tMMyjcZG6NoiZWUfK5uED3jZgWKT8eiiMySYZFvIqQwVWYqOwbAMBcODjyhQkfn vxw4KZxDxsdI69dJIQJBAM/POn/TwZEHbf+SBUDtmHvqRz+1dH8ux2Y+1SkOeIfv aJ5C7DaKmLBjqV+K2p0tU4mzbjbvgxabrzJku07ZzVcCQQDNQ6nQKEFXgumEh0oC t9RmyOr83A/1jr9aorpBw9T7+BgZqgUabDb21Q/IZkOemKBOFphurprJMI0zuhwW YghXAkBgnQKgjU2P5LliOXwRzKS475nCZ9VKj/AKfCnUFbM517d25Lw3O0Zzs1H6 Zm7u5jTRb0dNaRsz9puEPUrMBPyvAkApoYuTeFaoVRjflEDihD5ECoP+fPOPrkAJ Ne/o3rsXz52zttQKI+CR92yYDIWyaOo6bELGUAt8uqgo0ZpYv9FJAkEApPUjP61D MyYW+T60J5Njj0E3LjTZuKttyRC5p5P/8P3GIqs4xps+TwhwVEDfy2h51GtvJz2F UM8s1Vw9Crsvww==";
	public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	private static final String API_URL = "https://openapi.alipay.com/gateway.do";
	public static final String MCLOUD_API_DOMAIN = "http://mcloudmonitor.com/gateway.do";
	public static final String CHARSET = "UTF-8";
	/**
	 * 异步通知
	 */
	public static final String NOTICE_URL = "https://trade.ecbao.cn/zfbpay/notifyURL";
	//测试
	//public static final String NOTICE_URL = "http://16794ui705.iok.la:29890/zfbpay/notifyURL";
	/**
	 * 授权回调URL
	 */
	public static final String AUTH_CALL_URL = "https://trade.ecbao.cn/zfbauth/getToken";
	/**
	 * 第三方应用授权URI
	 */
	public static final String APP_AUTH_URI = "https://openauth.alipay.com/oauth2/appToAppAuth.htm";
	/**
	 * 用户授权
	 */
	public static final String USER_AUTH_URI = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm";
	/**
	 * notify_id验证
	 */
	public static final String NOTIFY_ID_VERIFY_URL = "https://mapi.alipay.com/gateway.do";
	
	
	//=============测试=============
//	public static final String PID = "2088801006306668";
//	public static final String APP_ID = "2016103100782590";
//	public static final String APP_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKaf3u1yscM+Q0KJ Eq2r7LgLJnrnK0CuxkDUtsu2EfRdBQZPT/vq8AlRHCto7UgVOIdGRJPTsbB/JHEj 0bNpuU0zSMC/G6perVlzUnQcwimylHpoHlv4hVc9DC6KjaHyAF9ctwWjE9A+5HkU 9KmZx++wGq8e/MCI6HgZqKivvYCRAgMBAAECgYA+1DmzP3RYMroZ9KXeZt2z6EBy R4i/syd+ercSyWyrwAeNAYsfas9oM/VzSPVwINBX8d8Z/tEZxFdxchg4lr0QC2vz 2tMMyjcZG6NoiZWUfK5uED3jZgWKT8eiiMySYZFvIqQwVWYqOwbAMBcODjyhQkfn vxw4KZxDxsdI69dJIQJBAM/POn/TwZEHbf+SBUDtmHvqRz+1dH8ux2Y+1SkOeIfv aJ5C7DaKmLBjqV+K2p0tU4mzbjbvgxabrzJku07ZzVcCQQDNQ6nQKEFXgumEh0oC t9RmyOr83A/1jr9aorpBw9T7+BgZqgUabDb21Q/IZkOemKBOFphurprJMI0zuhwW YghXAkBgnQKgjU2P5LliOXwRzKS475nCZ9VKj/AKfCnUFbM517d25Lw3O0Zzs1H6 Zm7u5jTRb0dNaRsz9puEPUrMBPyvAkApoYuTeFaoVRjflEDihD5ECoP+fPOPrkAJ Ne/o3rsXz52zttQKI+CR92yYDIWyaOo6bELGUAt8uqgo0ZpYv9FJAkEApPUjP61D MyYW+T60J5Njj0E3LjTZuKttyRC5p5P/8P3GIqs4xps+TwhwVEDfy2h51GtvJz2F UM8s1Vw9Crsvww==";
//	public static final String ALIPAY_PUBLIC_KEY ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
//	private static final String API_URL = "https://openapi.alipaydev.com/gateway.do";
//	public static final String CHARSET = "UTF-8";
//	public static final String NOTICE_URL = "http://jx8996.gicp.net/zfbpay/notifyURL";
//	public static final String AUTH_CALL_URL = "http://jx8996.gicp.net/zfbauth/getToken";
//	public static final String APP_AUTH_URI = "https://openauth.alipaydev.com/oauth2/appToAppAuth.htm";
//	public static final String USER_AUTH_URI = "https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm";
//	public static final String NOTIFY_ID_VERIFY_URL = "https://mapi.alipaydev.com/gateway.do";
	//============================
	
	/**
	 *  支付宝API调用对象
	 */
	private static AlipayClient alipayClient = new DefaultAlipayClient(API_URL,APP_ID,APP_PRIVATE_KEY,"json",CHARSET,ALIPAY_PUBLIC_KEY,"RSA");
	
	/**
	 * 当面付最大查询次数和查询间隔（毫秒）
	 */
	public static  int MAX_QUERY_RETRY = 5;
	public static  int QUERY_DURATION = 5000;
	
	/**
	 * 当面付最大撤销次数和撤销间隔（毫秒）
	 */
	public static  int MAX_CANCEL_RETRY = 3;
	public static  int CANCEL_DURATION = 2000;
	
	/**
	 * 交易保障线程第一次调度延迟和调度间隔（秒）
	 */
	public static int HEARTBEAT_DELAY = 5;
	public static int HEARTBEAT_DURATION = 900;
	
	public static AlipayClient getAlipayClient() {
		return alipayClient;
	}
	
	
	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient =  AlipayUtil.getAlipayClient();
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" +
		"    \"out_trade_no\":\"11256303XS14930235106299165780\"," +
		"    \"trade_no\":\"2017042421001004340282728168\"" +
		"  }");
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		
		
		
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
}
