package com.aiiju.pay.business.zfb.result;

import com.aiiju.pay.business.zfb.TradeStatus;
import com.alipay.api.response.AlipayTradeQueryResponse;

/**
 * 
 * @ClassName: AlipayF2FQueryResult 
 * @Description: 当面付 交易查询结果接口
 * @author 小飞 
 * @date 2016年12月3日 上午10:04:14 
 *
 */
public class AlipayF2FQueryResult implements Result {
    private TradeStatus tradeStatus;
    private AlipayTradeQueryResponse response;

    public AlipayF2FQueryResult(AlipayTradeQueryResponse response) {
        this.response = response;
    }

    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public void setResponse(AlipayTradeQueryResponse response) {
        this.response = response;
    }

    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    public AlipayTradeQueryResponse getResponse() {
        return response;
    }

    @Override
    public boolean isTradeSuccess() {
        return response != null &&
                TradeStatus.SUCCESS.equals(tradeStatus);
    }
}
