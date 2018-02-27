package com.aiiju.pay.business.wx.business;

import org.slf4j.LoggerFactory;

import com.aiiju.pay.business.wx.common.Log;
import com.aiiju.pay.business.wx.common.Signature;
import com.aiiju.pay.business.wx.common.Util;
import com.aiiju.pay.business.wx.protocol.pay_protocol.PayReqData;
import com.aiiju.pay.business.wx.protocol.pay_protocol.PayResData;
import com.aiiju.pay.business.wx.service.QrCodePayService;

/**
 * 
 * @ClassName: QrCodePayBusiness 
 * @Description: 扫码支付
 * @author 小飞 
 * @date 2016年12月7日 下午7:40:34 
 *
 */
public class QrCodePayBusiness {

	private static Log log = new Log(LoggerFactory.getLogger(QrCodePayBusiness.class));
	
	private QrCodePayService qrCodePayService;
	
	public QrCodePayBusiness() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		this.qrCodePayService = new QrCodePayService();
	}
	
	/**
	 *  直接执行扫描支付业务逻辑
	 * @param qrCodePayReqData
	 * @param resultListener
	 * @throws Exception 
	 */
	public String run(PayReqData payReqData) throws Exception {
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
        
        PayResData qrCodePayResData = (PayResData)Util.getObjectFromXML(payServiceResponseString, PayResData.class);
        
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
