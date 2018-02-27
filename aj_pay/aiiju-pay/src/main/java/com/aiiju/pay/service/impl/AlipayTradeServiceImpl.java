package com.aiiju.pay.service.impl;

import org.springframework.stereotype.Service;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.aiiju.pay.business.zfb.builder.AlipayTradePayRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradeQueryRequestBuilder;
import com.aiiju.pay.business.zfb.result.AlipayF2FPayResult;
import com.aiiju.pay.constant.ResultConstant;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
/**
 * 
 * @ClassName: AlipayTradeServiceImpl 
 * @Description: 当面付ServiceImpl
 * @author 小飞 
 * @date 2016年12月3日 上午10:20:47 
 *
 */
@Service("alipayTradeService")
public class AlipayTradeServiceImpl extends AbsAlipayTradeService {

	/**
	 *  商户可以直接使用的pay方法
	 */
    @Override
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder) {

        final String outTradeNo = builder.getOutTradeNo();

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        // 设置平台参数
        request.setNotifyUrl(builder.getNotifyUrl());
        String appAuthToken = builder.getAppAuthToken();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());

        // 设置业务参数
        request.setBizContent(builder.toJsonString());
        log.info("trade.pay bizContent:" + request.getBizContent());

        // 首先调用支付api
        AlipayTradePayResponse response = (AlipayTradePayResponse) getResponse(client, request);

        AlipayF2FPayResult result = new AlipayF2FPayResult(response);
        if (response != null && ResultConstant.SUCCESS.equals(response.getCode())) {
            // 支付交易明确成功
            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (response != null && ResultConstant.PAYING.equals(response.getCode())) {
            // 返回用户处理中，则轮询查询交易是否成功，如果查询超时，则调用撤销
            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                                                            .setAppAuthToken(appAuthToken)
                                                            .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse loopQueryResponse = loopQueryResult(queryBuiler);
            return checkQueryAndCancel(outTradeNo, appAuthToken, result, loopQueryResponse);

        } else if (tradeError(response)) {
            // 系统错误，则查询一次交易，如果交易没有支付成功，则调用撤销
            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                                                            .setAppAuthToken(appAuthToken)
                                                            .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse queryResponse = tradeQuery(queryBuiler);
            return checkQueryAndCancel(outTradeNo, appAuthToken, result, queryResponse);

        } else {
            // 其他情况表明该订单支付明确失败
            result.setTradeStatus(TradeStatus.FAILED);
        }

        return result;
    }

}
