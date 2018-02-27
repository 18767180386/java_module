package com.aiiju.pay.business.wx.service;

import com.aiiju.pay.business.wx.common.Configure;
import com.aiiju.pay.business.wx.protocol.pay_protocol.PayReqData;

/**
 * 
 * @ClassName: QrCodePayService 
 * @Description: 扫码支付
 * @author 小飞 
 * @date 2016年12月7日 下午7:41:17 
 *
 */
public class QrCodePayService extends BaseService{

	public QrCodePayService() throws ClassNotFoundException,IllegalAccessException, InstantiationException {
	}

	/**
	 *  请求支付服务
	 * @param qrCodePayReqData 这个数据对象里面包含了API要求提交的各种数据字段
	 * @return API返回的数据
	 * @throws Exception
	 */
	public String request(PayReqData payReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(Configure.UNIFIED_PAY_API,payReqData);

        return responseString;
    }
}
