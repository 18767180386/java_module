package com.aiiju.pay.service;

import com.aiiju.pay.business.zfb.builder.AlipayHeartbeatSynRequestBuilder;
import com.alipay.api.response.MonitorHeartbeatSynResponse;

/**
 * 
 * @ClassName: AlipayMonitorService 
 * @Description: 支付宝交易监听Service
 * @author 小飞 
 * @date 2017年1月16日 下午2:41:57 
 *
 */
public interface AlipayMonitorService {

    // 交易保障接口 https://openhome.alipay.com/platform/document.htm#mobileApp-barcodePay-API-heartBeat

    // 可以提供给系统商/pos厂商使用
    public MonitorHeartbeatSynResponse heartbeatSyn(AlipayHeartbeatSynRequestBuilder builder);
}
