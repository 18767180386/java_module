package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayReqData;
/**
 * 
 * @ClassName: QqQrCodePayService 
 * @Description: 扫码付款
 * @author 小飞 
 * @date 2017年2月8日 上午10:34:19 
 *
 */
public class QqQrCodePayService extends BaseService {

	public QqQrCodePayService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}
	
	public String request(QqPayReqData payReqData) throws Exception {
        String responseString = sendPost(QqConfigure.UNIFIED_PAY_API,payReqData);

        return responseString;
    }

}
