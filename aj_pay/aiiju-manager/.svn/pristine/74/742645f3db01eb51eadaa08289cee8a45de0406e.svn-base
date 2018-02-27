package com.aiiju.pojo;

import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONObject;

/** 
 * @ClassName Order
 * @Description
 * @author zong
 * @CreateDate 2017年8月3日 下午7:34:08
 */
public class Order {

	private String openId; //商户门店open_id
	private String openKey;//商户门店open_id
	private String outNo; //开发者流水号
	private String pmtTag; //收单机构标签
	private String pmtName; //收单机构名称
	private String ordName;  //订单名称（描述）
	private Integer originalAmount;  //原始交易金额（以分为单位，没有小数点）
	private Integer discountAmount; //折扣金额（以分为单位，没有小数点）
	private Integer ignoreAmount; //抹零金额（以分为单位，没有小数点）
	private Integer tradeAmount; //实际交易金额（以分为单位，没有小数点）
    private String tradeNo;    //交易号（收单机构交易号，可为空）
    private String remark;  //订单备注
    private String authCode; //条码支付的授权码（条码抢扫手机扫到的一串数字）
    private String tag;//订单标记，订单附加数据
    private String jumpUrl;//公众号/服务窗支付必填参数，支付结果跳转地址
    private String notifyUrl;  //异步通知地址
    private String ordMctId;  //商户流水号（从1 开始自增长不重复）
    private String ordShopId; //门店流水号（从1 开始自增长不重复）
    private String ordNo;  //订单号
    private String ordType; //订单类型（1 消费，2 辙单）
    private String tradeAccount ;// 交易帐号（银行卡号、支付宝帐号、微信帐号等，某些收单机构没有此数据）
    private String tradeQrcode ;// 二维码字符串
    private String tradePayTime ;// 付款完成时间（以收单机构为准）
    private String payStatus ;// 订单状态（1 交易成功，2 待支付，4 已取消，9 等待用户输入密码确认）
    private String jsapiPayUrl; //公众号订单支付地址，如果为非公众号订单，此  参数为空
      //微信高级参数
    private JSONObject goodsTag ; //商品标记，代金券或立减优惠功能的参数其它说明详见微信说明
    private String limitPay ; //limit_pay=no_credit，限制用户不能使用信用卡支付  
    // 支付宝高级参数
   
    private JSONObject goodsDetail; //订单包含的商品列表信息，Json 格式，其它说明详见支付宝商品明细说明
    private JSONObject extendParams; // 业务扩展参数，详见支付宝商品明细说明
   
    private String refundOriginalOutNo  ; //原始订单的开发者交易流水号
    private String refundOutNo  ; //新退款订单的开发者流水号，同一门店内唯

    private String refundOrdName; //退款订单名称，可以为空
    private String refundAmount; //退款金额（以分为单位，没有小数点）
//    private String tradeAccount;//交易帐号（收单机构交易的银行卡号，手机号等，可为空）
    private String RefundTradeNo ;//交易号（收单机构交易号，可为空）
    private String tradeResult ;//收单机构原始交易信息，请转换为json 数据
    private String  tmlToken ;//终端令牌，终端上线后获得的令牌
    private String refundRemark;//退款备注
    private String shopPass ;////主管密码，对密码进行sha1 加密，默认为123456
    private String  refundStatus ;// 退款状态
    
    
    
    
    
    // 平台交易信息对应平安订单信息
    public static Order dealToParOrder(Deal deal,Order order){
    	
    	//----------下订单参数：----------------------------
    	
//    	order.setOpenId(""); //商户门店open_id
//    	order.setOpenKey("");//商户门店open_key
    	order.setOutNo(deal.getSerialNum()); //开发者流水号  必填项
//    	order.setPmtTag(""); //付款方式编号举例如：Cash,Alipay,Weixin,Baifubao,Diy，其中Cash 为现金，Diy 为自定义，注意大小写，首字母大写  必填项
//    	order.setPmtName(""); //商户自定义付款方式名称，当pmt_tag为Diy 时，pmt_name 不能为空。
    	
    	StringBuffer sb = new StringBuffer();
    	List<DealDetail> details = deal.getDetails();
    	
    	   for (DealDetail dealDetail : details) {
    		   sb.append(dealDetail.getGoodsName()+",");
			}
    	
    	if(sb.toString().endsWith(",")){
    		order.setOrdName(sb.toString().substring(0, sb.toString().length()-1));  //订单名称（描述）
    	}else{
    		order.setOrdName(sb.toString());  //订单名称（描述）
    	}

    	
    	BigDecimal base = new BigDecimal("100");
    	//sumPrice
    	order.setOriginalAmount(base.multiply(deal.getSumPrice()).intValue());  //原始交易金额（以分为单位，没有小数点） 必填项
    	//privPrice
    	
    	order.setDiscountAmount(base.multiply(deal.getPrivPrice()).intValue()); //折扣金额（以分为单位，没有小数点）
    	//roundPrice
    	order.setIgnoreAmount(base.multiply(deal.getRoundPrice()).intValue()); //抹零金额（以分为单位，没有小数点）
        //price
    	order.setTradeAmount(base.multiply(deal.getPrice()).intValue()); //实际交易金额（以分为单位，没有小数点） 必填项

    	order.setTradeAccount (null);// 交易帐号（银行卡号、支付宝帐号、微信帐号等，某些收单机构没有此数据）
        order.setTradeNo(null);    //交易号（收单机构交易号，可为空）
        order.setRemark("订单备注");  //订单备注
        order.setTag("订单标记，订单附加数据");//订单标记，订单附加数据    
//        order.setNotifyUrl("异步通知地址");  //异步通知地址
        
         //支付方式：主扫、条码支付（商家扫用户手机）
//        order.setAuthCode(""); //条码支付的授权码（条码抢扫手机扫到的一串数字）
        
         //  支付方式：微信公众号、支付宝服务窗支付
//        order.setJumpUrl("");//公众号/服务窗支付必填参数，支付结果跳转地址
        //微信高级参数
//        order.setGoodsTag(null);
//        order.setLimitPay(null);
//         //支付宝高级参数
//        order.setGoodsDetail(null);
//        order.setExtendParams(null);
//        
        
        
        
        //------------------提交后返回：	------------------
        
//        order.setOrdMctId(deal.getSerialNum());  //商户流水号（从1 开始自增长不重复）
//        order.setOrdShopId(deal.getSerialNum()); //门店流水号（从1 开始自增长不重复）
//        order.setOrdNo(deal.getSerialNum());  //订单号
//        order.setOrdType("1"); //订单类型（1 消费，2 辙单）
//       
//        order.setTradeQrcode ("");// 二维码字符串
//        order.setTradePayTime ("");// 付款完成时间（以收单机构为准）
//        order.setStatus ("");// 订单状态（1 交易成功，2 待支付，4 已取消，9 等待用户输入密码确认）
//        order.setTradeResult ("");// 4000 收单机构原始交易数据
//        order.setJsapiPayUrl(""); //公众号订单支付地址，如果为非公众号订单，此  参数为空
//    	
    	
    	
    	
    	return order;
    }
    
    
    
    
    
    
    
	public JSONObject getGoodsTag() {
		return goodsTag;
	}







	public void setGoodsTag(JSONObject goodsTag) {
		this.goodsTag = goodsTag;
	}







	public String getLimitPay() {
		return limitPay;
	}







	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}







	public JSONObject getGoodsDetail() {
		return goodsDetail;
	}







	public void setGoodsDetail(JSONObject goodsDetail) {
		this.goodsDetail = goodsDetail;
	}







	public JSONObject getExtendParams() {
		return extendParams;
	}







	public void setExtendParams(JSONObject extendParams) {
		this.extendParams = extendParams;
	}







	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenKey() {
		return openKey;
	}
	public void setOpenKey(String openKey) {
		this.openKey = openKey;
	}
	public String getOutNo() {
		return outNo;
	}
	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}
	public String getPmtTag() {
		return pmtTag;
	}
	
	
	
	public void setPmtTag(String pmtTag) {
		this.pmtTag = pmtTag;
	}




	public String getPmtName() {
		return pmtName;
	}
	public void setPmtName(String pmtName) {
		this.pmtName = pmtName;
	}
	public String getOrdName() {
		return ordName;
	}
	public void setOrdName(String ordName) {
		this.ordName = ordName;
	}
	public Integer getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(Integer originalAmount) {
		this.originalAmount = originalAmount;
	}
	public Integer getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Integer getIgnoreAmount() {
		return ignoreAmount;
	}
	public void setIgnoreAmount(Integer ignoreAmount) {
		this.ignoreAmount = ignoreAmount;
	}
	public Integer getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Integer tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getJumpUrl() {
		return jumpUrl;
	}
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getOrdMctId() {
		return ordMctId;
	}
	public void setOrdMctId(String ordMctId) {
		this.ordMctId = ordMctId;
	}
	public String getOrdShopId() {
		return ordShopId;
	}
	public void setOrdShopId(String ordShopId) {
		this.ordShopId = ordShopId;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getOrdType() {
		return ordType;
	}
	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}
	public String getTradeAccount() {
		return tradeAccount;
	}
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}
	public String getTradeQrcode() {
		return tradeQrcode;
	}
	public void setTradeQrcode(String tradeQrcode) {
		this.tradeQrcode = tradeQrcode;
	}
	public String getTradePayTime() {
		return tradePayTime;
	}
	public void setTradePayTime(String tradePayTime) {
		this.tradePayTime = tradePayTime;
	}

	
	public String getTradeResult() {
		return tradeResult;
	}
	public void setTradeResult(String tradeResult) {
		this.tradeResult = tradeResult;
	}
	public String getJsapiPayUrl() {
		return jsapiPayUrl;
	}
	public void setJsapiPayUrl(String jsapiPayUrl) {
		this.jsapiPayUrl = jsapiPayUrl;
	}







	public String getPayStatus() {
		return payStatus;
	}







	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}







	public String getRefundOriginalOutNo() {
		return refundOriginalOutNo;
	}







	public void setRefundOriginalOutNo(String refundOriginalOutNo) {
		this.refundOriginalOutNo = refundOriginalOutNo;
	}







	public String getRefundOutNo() {
		return refundOutNo;
	}







	public void setRefundOutNo(String refundOutNo) {
		this.refundOutNo = refundOutNo;
	}







	public String getRefundOrdName() {
		return refundOrdName;
	}







	public void setRefundOrdName(String refundOrdName) {
		this.refundOrdName = refundOrdName;
	}







	public String getRefundAmount() {
		return refundAmount;
	}







	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}







	public String getRefundTradeNo() {
		return RefundTradeNo;
	}







	public void setRefundTradeNo(String refundTradeNo) {
		RefundTradeNo = refundTradeNo;
	}







	public String getTmlToken() {
		return tmlToken;
	}







	public void setTmlToken(String tmlToken) {
		this.tmlToken = tmlToken;
	}







	public String getRefundRemark() {
		return refundRemark;
	}







	public void setRefundRemark(String refundRemark) {
		this.refundRemark = refundRemark;
	}







	public String getShopPass() {
		return shopPass;
	}







	public void setShopPass(String shopPass) {
		this.shopPass = shopPass;
	}







	public String getRefundStatus() {
		return refundStatus;
	}







	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
    
    
    
    
    
}
