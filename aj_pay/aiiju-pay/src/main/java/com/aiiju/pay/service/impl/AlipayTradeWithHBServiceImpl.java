package com.aiiju.pay.service.impl;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.aiiju.pay.business.zfb.builder.AlipayTradeCreateRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradePayRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradeQueryRequestBuilder;
import com.aiiju.pay.business.zfb.result.AlipayF2FCreateResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FPayResult;
import com.aiiju.pay.constant.ResultConstant;
import com.aiiju.pay.service.impl.hb.HbListener;
import com.aiiju.pay.service.impl.hb.TradeListener;
import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

/**
 * 
 * @ClassName: AlipayTradeWithHBServiceImpl
 * @Description: 集成交易保障的当面付ServiceImpl
 * @author 小飞
 * @date 2017年1月16日 下午2:23:21
 *
 */
// @Service("alipayTradeWithHBService")
public class AlipayTradeWithHBServiceImpl extends AbsAlipayTradeService {

	private TradeListener listener = new HbListener();

	private AlipayTradePayResponse getResponse(AlipayTradePayRequest request, final String outTradeNo,final long beforeCall) {
		try {
			AlipayTradePayResponse response = client.execute(request);
			if (response != null) {
				log.info(response.getBody());
			}
			return response;

		} catch (AlipayApiException e) {
			// 获取异常真实原因
			Throwable cause = e.getCause();

			if (cause instanceof ConnectException
					|| cause instanceof NoRouteToHostException) {
				// 建立连接异常
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						listener.onConnectException(outTradeNo, beforeCall);
					}
				});

			} else if (cause instanceof SocketException) {
				// 报文上送异常
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						listener.onSendException(outTradeNo, beforeCall);
					}
				});

			} else if (cause instanceof SocketTimeoutException) {
				// 报文接收异常
				executorService.submit(new Runnable() {
					@Override
					public void run() {
						listener.onReceiveException(outTradeNo, beforeCall);
					}
				});
			}

			e.printStackTrace();
			return null;
		}
	}

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
		final long beforeCall = System.currentTimeMillis();
		AlipayTradePayResponse response = (AlipayTradePayResponse) getResponse(request, outTradeNo, beforeCall);

		AlipayF2FPayResult result = new AlipayF2FPayResult(response);
		if (response != null
				&& ResultConstant.SUCCESS.equals(response.getCode())) {
			// 支付交易明确成功
			result.setTradeStatus(TradeStatus.SUCCESS);
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					listener.onPayTradeSuccess(outTradeNo, beforeCall);
				}
			});

		} else if (response != null
				&& ResultConstant.PAYING.equals(response.getCode())) {
			// 返回支付中，同步交易耗时
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					listener.onPayInProgress(outTradeNo, beforeCall);
				}
			});
			// 返回用户处理中，则轮询查询交易是否成功，如果查询超时，则调用撤销
			AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
					.setAppAuthToken(appAuthToken).setOutTradeNo(outTradeNo);
			AlipayTradeQueryResponse loopQueryResponse = loopQueryResult(queryBuiler);
			return checkQueryAndCancel(outTradeNo, appAuthToken, result,
					loopQueryResponse);

		} else if (tradeError(response)) {
			// 系统错误，同步交易耗时
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					listener.onPayFailed(outTradeNo, beforeCall);
				}
			});
			// 系统错误，则查询一次交易，如果交易没有支付成功，则调用撤销
			AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
					.setAppAuthToken(appAuthToken).setOutTradeNo(outTradeNo);
			AlipayTradeQueryResponse queryResponse = tradeQuery(queryBuiler);
			return checkQueryAndCancel(outTradeNo, appAuthToken, result,
					queryResponse);

		} else {
			// 其他情况表明该订单支付明确失败
			result.setTradeStatus(TradeStatus.FAILED);

			executorService.submit(new Runnable() {
				@Override
				public void run() {
					listener.onPayFailed(outTradeNo, beforeCall);
				}
			});
		}

		return result;
	}

	@Override
	public AlipayF2FCreateResult tradeCreate(AlipayTradeCreateRequestBuilder builder) {
		// TODO Auto-generated method stub
		return null;
	}

}
