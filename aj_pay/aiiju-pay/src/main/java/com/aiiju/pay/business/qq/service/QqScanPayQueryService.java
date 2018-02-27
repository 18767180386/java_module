package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.pay_query_protocol.QqOrderQueryReqData;
/**
 * 
 * @ClassName: ScanPayQueryService 
 * @Description: 付款码支付订单查询
 * @author 小飞 
 * @date 2017年2月8日 上午10:28:43 
 *
 */
public class QqScanPayQueryService extends BaseService {

	public QqScanPayQueryService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}

	public String request(QqOrderQueryReqData scanPayQueryReqData) throws Exception {
        String responseString = sendPost(QqConfigure.PAY_QUERY_API,scanPayQueryReqData);

        return responseString;
    }
}
