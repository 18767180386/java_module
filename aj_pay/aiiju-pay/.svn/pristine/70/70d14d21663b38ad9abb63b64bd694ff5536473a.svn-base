package com.aiiju.pay.business.qq.protocol.pay_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.wx.common.RandomStringGenerator;
import com.aiiju.pay.business.wx.common.Signature;
/**
 * 
 * @ClassName: QqPayReqData 
 * @Description: 统一下单 请求
 * @author 小飞 
 * @date 2017年2月7日 下午4:33:47 
 *
 */
public class QqPayReqData {

	private String appid = "";
    private String mch_id = "";
    private String sub_mch_id="";
    private String nonce_str = "";
    private String sign = "";
    private String body = "";
    private String attach = "";
    private String out_trade_no = "";
    private String fee_type = "CNY";
    private int total_fee = 0;
    private String spbill_create_ip = "";
    private String notify_url = "";
    private String trade_type = "";
    
    public QqPayReqData(String subMchId,String notify_url,String trade_type,String body,String attach,String outTradeNo,int totalFee,String spBillCreateIP) {

       //qq钱包支付分配的商户号ID
       setMch_id(QqConfigure.getMchid());
       
       setSub_mch_id(subMchId);
       
       setNotify_url(notify_url);
       
       setTrade_type(trade_type);
       
       //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
       setBody(body);

       //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
       setAttach(attach);

       //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
       setOut_trade_no(outTradeNo);

       //订单总金额，单位为“分”，只能整数
       setTotal_fee(totalFee);

       //订单生成的机器IP
       setSpbill_create_ip(spBillCreateIP);

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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
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
