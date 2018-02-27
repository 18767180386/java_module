package com.aiiju.pay.business.qq.protocol.pay_query_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.wx.common.RandomStringGenerator;
import com.aiiju.pay.business.wx.common.Signature;
/**
 * 
 * @ClassName: OrderQueryReqData 
 * @Description: 订单查询 请求
 * @author 小飞 
 * @date 2017年2月7日 下午5:19:59 
 *
 */
public class QqOrderQueryReqData {

	// 每个字段具体的意思请查看API文档
	private String appid = "";
	private String mch_id = "";
	private String sub_mch_id = "";
	private String transaction_id = "";
	private String out_trade_no = "";
	private String nonce_str = "";
	private String sign = "";

	public QqOrderQueryReqData(String subMchId, String transactionID,
			String outTradeNo) {

		setMch_id(QqConfigure.getMchid());

		setSub_mch_id(subMchId);

		setTransaction_id(transactionID);

		// 商户系统自己生成的唯一的订单号
		setOut_trade_no(outTradeNo);

		// 随机字符串，不长于32 位
		setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

		// 根据API给的签名规则进行签名
		String sign = Signature.getSign(toMap());
		setSign(sign);// 把签名数据设置到Sign这个属性中

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

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object obj;
			try {
				obj = field.get(this);
				if (obj != null) {
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
