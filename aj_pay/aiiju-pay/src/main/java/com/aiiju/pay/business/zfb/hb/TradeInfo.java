package com.aiiju.pay.business.zfb.hb;

/**
 * 
 * @ClassName: TradeInfo 
 * @Description: 交易信息
 * @author 小飞 
 * @date 2016年12月3日 上午10:10:17 
 *
 */
public interface TradeInfo {
    // 获取交易状态
    public HbStatus getStatus();

    // 获取交易时间
    public double getTimeConsume();
}
