package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.refund_query_protocol.QqRefundQueryReqData;
/**
 * 
 * @ClassName: QqRefundQueryService 
 * @Description: 退款查询
 * @author 小飞 
 * @date 2017年2月8日 上午10:33:27 
 *
 */
public class QqRefundQueryService extends BaseService {

	public QqRefundQueryService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}
	
	public String request(QqRefundQueryReqData refundQueryReqData) throws Exception {

        String responseString = sendPost(QqConfigure.REFUND_QUERY_API,refundQueryReqData);

        return responseString;
    }

}
