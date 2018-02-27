package com.aiiju.pay.business.qq.business;

import org.slf4j.LoggerFactory;

import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayReqData;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayResData;
import com.aiiju.pay.business.qq.service.QqQrCodePayService;
import com.aiiju.pay.business.wx.common.Log;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.common.Util;
/**
 * 
 * @ClassName: QqQrCodePayBusiness 
 * @Description: 扫码支付
 * @author 小飞 
 * @date 2017年2月8日 上午11:01:34 
 *
 */
public class QqQrCodePayBusiness {

	private static Log log = new Log(LoggerFactory.getLogger(QqQrCodePayBusiness.class));
	
	private QqQrCodePayService qrCodePayService;
	
	public QqQrCodePayBusiness() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.qrCodePayService = new QqQrCodePayService();
	}
	
	public String run(QqPayReqData payReqData) throws Exception {
		 //接受API返回
        String payServiceResponseString;
        
        long costTimeStart = System.currentTimeMillis();
        log.i("支付API返回的数据如下：");
        payServiceResponseString = this.qrCodePayService.request(payReqData);
        
        long costTimeEnd = System.currentTimeMillis();
        long totalTimeCost = costTimeEnd - costTimeStart;
        log.i("api请求总耗时：" + totalTimeCost + "ms");

        //打印回包数据
        log.i(payServiceResponseString);
        
        QqPayResData qrCodePayResData = (QqPayResData)Util.getObjectFromXML(payServiceResponseString, QqPayResData.class);
        
        if( qrCodePayResData == null || qrCodePayResData.getResult_code() == null) {
        	log.e("【请求失败】请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问");
        	return null;
        }
        
        if (qrCodePayResData.getResult_code().equals("FAIL")) {
        	//注意：一般这里返回FAIL是出现系统级参数错误，请检测Post给API的数据是否规范合法
            log.e("【请求失败】请求API系统返回失败，请检测Post给API的数据是否规范合法");
            return null;
        } else {
        	log.i("请求API系统成功返回数据");
        	 if (!Signature.checkIsSignValidFromResponseString(payServiceResponseString)) {
                 log.e("【请求失败】请求API返回的数据签名验证失败，有可能数据被篡改了");
                 return null;
             }
        	
             if (qrCodePayResData.getResult_code().equals("SUCCESS")) {
            	 return qrCodePayResData.getCode_url();
             }
        	
        }
		return null;
	}
}
