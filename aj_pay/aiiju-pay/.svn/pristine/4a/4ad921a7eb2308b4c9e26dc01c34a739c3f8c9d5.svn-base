package com.aiiju.pay.business.qq.service;

import com.aiiju.pay.business.qq.common.QqConfigure;
import com.aiiju.pay.business.qq.protocol.reverse_protocol.QqReverseReqData;
/**
 * 
 * @ClassName: QqReverseService 
 * @Description: 付款码撤销
 * @author 小飞 
 * @date 2017年2月8日 上午10:30:35 
 *
 */
public class QqReverseService extends BaseService {

	public QqReverseService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super();
	}
	
	public String request(QqReverseReqData reverseReqData) throws Exception {
        String responseString = sendPost(QqConfigure.REVERSE_API,reverseReqData);

        return responseString;
    }

}
