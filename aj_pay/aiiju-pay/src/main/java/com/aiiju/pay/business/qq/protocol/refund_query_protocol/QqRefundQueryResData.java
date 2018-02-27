package com.aiiju.pay.business.qq.protocol.refund_query_protocol;

/**
 * 
 * @ClassName: QqRefundQueryResData
 * @Description: 退款查询 返回
 * @author 小飞
 * @date 2017年2月8日 上午9:53:20
 *
 */
public class QqRefundQueryResData {

	// 协议层
	private String return_code = "";
	private String return_msg = "";

	// 协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
	private String result_code = "";
	private String err_code = "";
	private String err_code_des = "";
	private String appid = "";
	private String mch_id = "";
	private String nonce_str = "";
	private String sign = "";
	
	// 这里要用对象来装，因为有可能出现多个数据
	private String transaction_id = "";
	private String out_trade_no = "";
	private int total_fee = 0;
	private int cash_fee = 0;
	private String fee_type = "";
	private String out_refund_no_$n = "";
	private String refund_id_$n = "";
    private String refund_channel_$n = "";
    private int refund_fee_$n = 0;
    private int coupon_refund_fee_$n = 0;
    private int  cash_refund_fee_$n = 0;
    private String refund_status_$n = "";
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getOut_refund_no_$n() {
		return out_refund_no_$n;
	}
	public void setOut_refund_no_$n(String out_refund_no_$n) {
		this.out_refund_no_$n = out_refund_no_$n;
	}
	public String getRefund_id_$n() {
		return refund_id_$n;
	}
	public void setRefund_id_$n(String refund_id_$n) {
		this.refund_id_$n = refund_id_$n;
	}
	public String getRefund_channel_$n() {
		return refund_channel_$n;
	}
	public void setRefund_channel_$n(String refund_channel_$n) {
		this.refund_channel_$n = refund_channel_$n;
	}
	public int getRefund_fee_$n() {
		return refund_fee_$n;
	}
	public void setRefund_fee_$n(int refund_fee_$n) {
		this.refund_fee_$n = refund_fee_$n;
	}
	public int getCoupon_refund_fee_$n() {
		return coupon_refund_fee_$n;
	}
	public void setCoupon_refund_fee_$n(int coupon_refund_fee_$n) {
		this.coupon_refund_fee_$n = coupon_refund_fee_$n;
	}
	public int getCash_refund_fee_$n() {
		return cash_refund_fee_$n;
	}
	public void setCash_refund_fee_$n(int cash_refund_fee_$n) {
		this.cash_refund_fee_$n = cash_refund_fee_$n;
	}
	public String getRefund_status_$n() {
		return refund_status_$n;
	}
	public void setRefund_status_$n(String refund_status_$n) {
		this.refund_status_$n = refund_status_$n;
	}
    
}
