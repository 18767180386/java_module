package com.aiiju.pay.business.zfb.result;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * 
 * @ClassName: AlipayF2FRefundResult 
 * @Description: 当面付 交易撤销接口
 * @author 小飞 
 * @date 2016年12月3日 上午10:04:55 
 *
 */
public class AlipayF2FRefundResult implements Result {
    private TradeStatus tradeStatus;
    private AlipayTradeRefundResponse response;

    public AlipayF2FRefundResult(AlipayTradeRefundResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(AlipayTradeRefundResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public AlipayTradeRefundResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
