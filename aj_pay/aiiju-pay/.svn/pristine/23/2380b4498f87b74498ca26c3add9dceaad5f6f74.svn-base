package com.aiiju.pay.business.qq.protocol.refund_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.wx.common.RandomStringGenerator;
import com.aiiju.pay.business.wx.common.Signature;

/**
 * 
 * @ClassName: QqRefundReqData 
 * @Description: 退款 请求
 * @author 小飞 
 * @date 2017年2月7日 下午5:46:43 
 *
 */
public class QqRefundReqData {

	private String appid = "";
    private String mch_id = "";
    private String sub_mch_id = "";
    private String nonce_str = "";
    private String sign = "";
    private String transaction_id = "";
    private String out_trade_no = "";
    private String out_refund_no = "";
    private int refund_fee = 0;
    private String op_user_id = "";
    private String op_user_passwd;
    
    public QqRefundReqData(String subMchId,String transactionID,String outTradeNo,String outRefundNo,int refundFee){

        setMch_id(QqConfigure.getMchid());
        
        setSub_mch_id(subMchId);

        setTransaction_id(transactionID);

        //商户系统自己生成的唯一的订单号
        setOut_trade_no(outTradeNo);

        setOut_refund_no(outRefundNo);


        setRefund_fee(refundFee);

        setOp_user_id(QqConfigure.getMchid());
        
        setOp_user_passwd(QqConfigure.getPasswd());

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中

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
	public String getSub_mch_id() {
		return sub_mch_id;
	}
	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
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
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
    
	public String getOp_user_passwd() {
		return op_user_passwd;
	}

	public void setOp_user_passwd(String op_user_passwd) {
		this.op_user_passwd = op_user_passwd;
	}

	public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
