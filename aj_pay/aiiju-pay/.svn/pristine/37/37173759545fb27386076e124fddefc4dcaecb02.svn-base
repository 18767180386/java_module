package com.aiiju.pay.business.zfb.result;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.alipay.api.response.AlipayTradeCreateResponse;
/**
 * 
 * @ClassName: AlipayF2FCreateResult 
 * @Description: 统一收单付款创建结果
 * @author 小飞 
 * @date 2017年2月10日 上午11:14:05 
 *
 */
public class AlipayF2FCreateResult implements Result {

	private TradeStatus tradeStatus;
	private AlipayTradeCreateResponse response;
	
	public AlipayF2FCreateResult(AlipayTradeCreateResponse response) {
        this.response = response;
    }

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public AlipayTradeCreateResponse getResponse() {
		return response;
	}

	public void setResponse(AlipayTradeCreateResponse response) {
		this.response = response;
	}

	@Override
	public boolean isTradeSuccess() {
		return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
	}

}
