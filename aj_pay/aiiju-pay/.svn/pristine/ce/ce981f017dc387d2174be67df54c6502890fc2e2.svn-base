package com.aiiju.pay.business.zfb.result;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.alipay.api.response.AlipayTradePrecreateResponse;

/**
 * 
 * @ClassName: AlipayF2FPrecreateResult 
 * @Description: 当面付 预创建结果接口（扫码支付）
 * @author 小飞 
 * @date 2016年12月3日 上午10:02:39 
 *
 */
public class AlipayF2FPrecreateResult implements Result {
    private TradeStatus tradeStatus;
    private AlipayTradePrecreateResponse response;

    public AlipayF2FPrecreateResult(AlipayTradePrecreateResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(AlipayTradePrecreateResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public AlipayTradePrecreateResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
