package com.aiiju.pay.service;

import com.aiiju.pay.business.zfb.builder.AlipayTradeCreateRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradePayRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradePrecreateRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradeQueryRequestBuilder;
import com.aiiju.pay.business.zfb.builder.AlipayTradeRefundRequestBuilder;
import com.aiiju.pay.business.zfb.result.AlipayF2FCreateResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FPayResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FPrecreateResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FQueryResult;
import com.aiiju.pay.business.zfb.result.AlipayF2FRefundResult;


/**
 * 
 * @ClassName: AlipayTradeService 
 * @Description: 当面付Service
 * @author 小飞 
 * @date 2016年12月2日 下午4:17:13 
 *
 */
public interface AlipayTradeService {
	
	/**
	 * 当面付2.0流程支付
	 * @param builder
	 * @return
	 */
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder);

    /**
     * 当面付2.0消费查询
     * @param builder
     * @return
     */
    public AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder);

    /**
     * 当面付2.0消费退款
     * @param builder
     * @return
     */
    public AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder);

    /**
     * 当面付2.0预下单(生成二维码)
     * @param builder
     * @return
     */
    public AlipayF2FPrecreateResult tradePrecreate(AlipayTradePrecreateRequestBuilder builder);
    
    /**
     * 统一收单交易创建
     * @return
     */
    public AlipayF2FCreateResult tradeCreate(AlipayTradeCreateRequestBuilder builder);
}
