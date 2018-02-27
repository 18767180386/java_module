package com.aiiju.pay.business.wx.common;

/**
 * 
 * @ClassName: Configure 
 * @Description: 配置数据
 * @author 小飞 
 * @date 2016年12月7日 下午3:50:17 
 *
 */
public class Configure {

	private static String key = "YcH152pipqCM0vU8qiuFLbRULBURdPFj";

	//微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID = "wxc4e0baf5b835ef99";
	
	private static String appsecret = "517fa006133024df656aa45accac99ce";

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = "1415979202";

	//受理模式下给子商户分配的子商户号
	private static String subMchID = "";

//	HTTPS证书的本地路径
	private static String certLocalPath = "/usr/java/cert/apiclient_cert.p12";
//	private static String certLocalPath = "D:\\cert\\apiclient_cert.p12";

	//HTTPS证书密码，默认密码等于商户号MCHID
	private static String certPassword = mchID;

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;

	//机器IP
	private static String ip = "";

	//1）被扫支付API
	public static final String SCAN_PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	//2）被扫支付查询API
	public static final String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static final String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static final String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static final  String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static final String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static final String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";
	
	//8)统一下单API
	public static final String UNIFIED_PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	//用户授权URI
	public static final String USER_AUTH_URI = "https://open.weixin.qq.com/connect/oauth2/authorize";
	
	//用户授权回调URL
	public static final String AUTH_CALL_URL = "https://trade.ecbao.cn/wxauth/getToken";
	
	//用户授权获取openid
	public static final String GET_OPEN_ID = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	//支付回调URL
	public static final String NOTIFY_URL = "https://trade.ecbao.cn/wxpay/notifyURL";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.aiiju.pay.business.wx.common.HttpsRequest";

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}

	public static void setSubMchID(String subMchID) {
		Configure.subMchID = subMchID;
	}

	public static void setCertLocalPath(String certLocalPath) {
		Configure.certLocalPath = certLocalPath;
	}

	public static void setCertPassword(String certPassword) {
		Configure.certPassword = certPassword;
	}

	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}
	
	public static String getMchid(){
		return mchID;
	}

	public static String getSubMchid(){
		return subMchID;
	}
	
	public static String getCertLocalPath(){
		return certLocalPath;
	}
	
	public static String getCertPassword(){
		return certPassword;
	}

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}

	public static String getAppsecret() {
		return appsecret;
	}

	public static void setAppsecret(String appsecret) {
		Configure.appsecret = appsecret;
	}

}
