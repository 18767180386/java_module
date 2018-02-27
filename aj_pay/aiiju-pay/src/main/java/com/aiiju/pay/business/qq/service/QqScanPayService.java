package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqScanPayReqData;
/**
 * 
 * @ClassName: QqScanPayService 
 * @Description: 付款码支付
 * @author 小飞 
 * @date 2017年2月8日 上午10:27:28 
 *
 */
public class QqScanPayService extends BaseService {

	public QqScanPayService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}

	public String request(QqScanPayReqData scanPayReqData) throws Exception {

        String responseString = sendPost(QqConfigure.SCAN_PAY_API,scanPayReqData);

        return responseString;
    }
}
