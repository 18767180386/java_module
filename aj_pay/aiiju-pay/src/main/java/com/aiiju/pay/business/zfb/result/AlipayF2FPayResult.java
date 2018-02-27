package com.aiiju.pay.business.zfb.result;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.alipay.api.response.AlipayTradePayResponse;

/**
 * 
 * @ClassName: AlipayF2FPayResult 
 * @Description: 当面付支付结果接口（条码支付）
 * @author 小飞 
 * @date 2016年12月3日 上午10:01:19 
 *
 */
public class AlipayF2FPayResult implements Result {
	
    private TradeStatus tradeStatus;
    private AlipayTradePayResponse response;

    public AlipayF2FPayResult(AlipayTradePayResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(AlipayTradePayResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public AlipayTradePayResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
