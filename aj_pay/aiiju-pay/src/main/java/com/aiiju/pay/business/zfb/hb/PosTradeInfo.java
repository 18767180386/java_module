package com.aiiju.pay.business.zfb.hb;

/**
 * 
 * @ClassName: PosTradeInfo 
 * @Description: POS交易信息
 * @author 小飞 
 * @date 2016年12月3日 上午10:11:58 
 *
 */
public class PosTradeInfo implements TradeInfo {
    private HbStatus status;
    private String time;
    private int timeConsume;

    private PosTradeInfo() {
    }

    public static PosTradeInfo newInstance(HbStatus status, String time, int timeConsume) {
        PosTradeInfo info = new PosTradeInfo();
        if (timeConsume > 99 || timeConsume < 0) {
            timeConsume = 99;
        }
        info.setTimeConsume(timeConsume);
        info.setStatus(status);
        info.setTime(time);
        return info;
    }

    @Override
    public String toString() {
        return new StringBuilder(status.name())
                .append(time)
                .append(String.format("%02d", timeConsume))
                .toString();
    }

    @Override
    public HbStatus getStatus() {
        return status;
    }

    public void setStatus(HbStatus status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public double getTimeConsume() {
        return (double) timeConsume;
    }

    public void setTimeConsume(int timeConsume) {
        this.timeConsume = timeConsume;
    }
}
