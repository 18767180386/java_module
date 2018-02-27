package com.aiiju.pay.business.qq.protocol.reverse_protocol;
/**
 * 
 * @ClassName: QqReverseResData 
 * @Description: 撤销 返回
 * @author 小飞 
 * @date 2017年2月7日 下午5:40:36 
 *
 */
public class QqReverseResData {

	 //协议层
    private String return_code = "";
    private String return_msg = "";
    
    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private String mch_id = "";
    private String sub_mch_id = "";
    private String result_code = "";
    private String err_code = "";
    private String err_code_des = "";
    private String sign = "";
    private String appid = "";
    private String nonce_str = "";

    private String recall = "";

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

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getRecall() {
		return recall;
	}

	public void setRecall(String recall) {
		this.recall = recall;
	}
    
}