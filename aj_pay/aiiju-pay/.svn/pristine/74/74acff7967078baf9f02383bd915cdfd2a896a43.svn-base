package com.aiiju.pay.service.impl;

import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;

/**
 * 
 * @ClassName: AbsAlipayService 
 * @Description: 父类
 * @author 小飞 
 * @date 2016年12月3日 上午10:22:35 
 *
 */
public abstract class AbsAlipayService {
    protected Logger log =  Logger.getLogger(getClass());

    // 调用AlipayClient的execute方法，进行远程调用
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected AlipayResponse getResponse(AlipayClient client, AlipayRequest request) {
        try {
            AlipayResponse response = client.execute(request);
            if (response != null) {
                log.info(response.getBody());
            }
            return response;

        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
