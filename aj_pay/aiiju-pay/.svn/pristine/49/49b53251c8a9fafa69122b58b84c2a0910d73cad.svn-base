package com.aiiju.pay.business.qq.business;

import org.slf4j.LoggerFactory;

import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayReqData;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayResData;
import com.aiiju.pay.business.qq.service.QqGZHPayService;
import com.aiiju.pay.business.wx.common.Log;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.common.Util;

/**
 * 
 * @ClassName: QqGZHPayBussiness 
 * @Description: qq钱包公众号支付
 * @author 小飞 
 * @date 2017年2月8日 上午10:59:19 
 *
 */
public class QqGZHPayBussiness {

	private static Log log = new Log(LoggerFactory.getLogger(QqGZHPayBussiness.class));
	
	private QqGZHPayService gzhPayService;
	
	public QqGZHPayBussiness() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.gzhPayService = new QqGZHPayService();
	}
	
	public String run(QqPayReqData payReqData) throws Exception {
		 //接受API返回
       String payServiceResponseString;
       
       payServiceResponseString = this.gzhPayService.request(payReqData);
       
       //打印回包数据
       log.i(payServiceResponseString);
       
       QqPayResData gzhPayResData = (QqPayResData)Util.getObjectFromXML(payServiceResponseString, QqPayResData.class);
       
       if (gzhPayResData == null || gzhPayResData.getResult_code() == null) {
       	log.e("【请求失败】请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
       	return null;
       }
       
       if (gzhPayResData.getResult_code().equals("FAIL")) {
       	//注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
           log.e("【请求失败】请求API系统返回失败，请检测Post给API的数据是否规范合法");
           return null;
       } else {
       	log.i("请求API系统成功返回数据");
       	 if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString)) {
                log.e("【请求失败】请求API返回的数据签名验证失败，有可能数据被篡改了");
                return null;
            }
            if (gzhPayResData.getResult_code().equals("SUCCESS")) {
           	 return gzhPayResData.getPrepay_id();
            }
       }
		return null;
	}
}
