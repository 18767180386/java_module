package com.aiiju.pay.service.impl.hb;

/**
 * 
 * @ClassName: TradeListener 
 * @Description: 支付宝交易监听器
 * @author 小飞 
 * @date 2017年1月16日 下午2:31:12 
 *
 */
public interface TradeListener {

    // 支付成功
    public void onPayTradeSuccess(String outTradeNo, long beforeCall);

    // 支付处理中
    public void onPayInProgress(String outTradeNo, long beforeCall);

    // 支付失败
    public void onPayFailed(String outTradeNo, long beforeCall);

    // 建立连接异常
    public void onConnectException(String outTradeNo, long beforeCall);

    // 报文上送异常
    public void onSendException(String outTradeNo, long beforeCall);

    // 报文接收异常
    public void onReceiveException(String outTradeNo, long beforeCall);
}
