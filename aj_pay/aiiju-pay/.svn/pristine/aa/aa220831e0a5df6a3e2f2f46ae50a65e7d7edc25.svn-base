package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.pay_protocol.QqPayReqData;
/**
 * 
 * @ClassName: QqGZHPayService 
 * @Description: 公众号支付
 * @author 小飞 
 * @date 2017年2月8日 上午10:35:49 
 *
 */
public class QqGZHPayService extends BaseService {

	public QqGZHPayService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}
	
	public String request(QqPayReqData payReqData) throws Exception {

        String responseString = sendPost(QqConfigure.UNIFIED_PAY_API,payReqData);

        return responseString;
    }

}
