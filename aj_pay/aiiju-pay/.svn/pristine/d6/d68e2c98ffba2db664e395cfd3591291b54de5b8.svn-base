package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.refund_protocol.QqRefundReqData;
/**
 * 
 * @ClassName: QqRefundService 
 * @Description: 退款
 * @author 小飞 
 * @date 2017年2月8日 上午10:32:24 
 *
 */
public class QqRefundService extends BaseService {

	public QqRefundService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}

	public String request(QqRefundReqData refundReqData) throws Exception {
		
        String responseString = sendPost(QqConfigure.REFUND_API,refundReqData);

        return responseString;
    }

}
