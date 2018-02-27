package com.aiiju.pay.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.util.JsonUtils;
import com.aiiju.pay.constant.TradeConstant;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealDetail;

/**
 * 
 * @ClassName: CommonBuilder
 * @Description: （一般通用的）创建流水
 * @author zong
 *
 */
public class CommonBuilder {

	private static Deal prototype = new Deal();
	
	 /**
     * 创建订单
     * @return
     */
    public static Deal buildOrder(HttpServletRequest req, String payTypeDetail, String outTradeNo,String tradeType) {
        Deal deal = createOrder(req, payTypeDetail, outTradeNo,tradeType);// tradeType  交易类型，1收款，2退款
        return deal;
    }
	
    private static Deal createOrder(HttpServletRequest req, String payTypeDetail, String outTradeNo,String tradeType) {
        // 流水信息
    	
    	String storeId = req.getParameter("storeId");
    	
        Deal deal = (Deal) prototype.clone();
        deal.setTradeType(tradeType);
        deal.setPrice(new BigDecimal(req.getParameter("price")));
        deal.setSerialNum(outTradeNo);
        deal.setSellNum(outTradeNo.substring(outTradeNo.indexOf("XS")));
        deal.setPayType(payTypeDetail.substring(0, 1));
        deal.setReceTypeDesc(TradeConstant.getReceTypeDescMap().get(payTypeDetail));
        deal.setPayTypeDetail(payTypeDetail);
        deal.setSumPrice(new BigDecimal(req.getParameter("sumPrice")));
        deal.setPrivType(req.getParameter("privType"));
        deal.setPrivPrice(new BigDecimal(req.getParameter("privPrice")));
        deal.setShouldPrice(new BigDecimal(req.getParameter("shouldPrice")));
        deal.setActualPrice(new BigDecimal(req.getParameter("actualPrice")));
        deal.setChangePrice(new BigDecimal(req.getParameter("changePrice")));
        deal.setRoundPrice(new BigDecimal(req.getParameter("roundPrice")));
        deal.setCanRefundFee(deal.getPrice());
        deal.setOperatorId(req.getParameter("operatorId"));
        deal.setOperatorName(req.getParameter("operatorName"));
        deal.setDealDate(new Date());
        deal.setStoreId(storeId);
     
        deal.setStatus(Byte.parseByte("2"));// 流水订单状态 1：完成 2：进行中 3：失败
        deal.setRemark(req.getParameter("remark"));
        deal.setTradeStatus("未支付");
        deal.setMemberPhone(req.getParameter("memberPhone"));
        deal.setCreateDate(new Date());
        deal.setParentStoreId(req.getParameter("parentStoreId"));
        // 商品详情
        if (StringUtils.isBlank(req.getParameter("detail"))) {
            List<DealDetail> details = new ArrayList<DealDetail>();
            DealDetail detail = new DealDetail();
//            detail.setGoodsName(TradeConstant.getReceTypeDescMap().get(payType) + "收款");
            detail.setGoodsName("无码商品");
            detail.setNum(new BigDecimal(1));
            detail.setPrice(deal.getPrice());
            detail.setSerialNum(deal.getSerialNum());
            detail.setCreateDate(deal.getCreateDate());
            details.add(detail);
            deal.setDetails(details);
        } else {
            List<DealDetail> details = JsonUtils.jsonToList(req.getParameter("detail"), DealDetail.class);
            
            for (DealDetail dealDetail : details) {
            	dealDetail.setCreateDate(deal.getCreateDate());
			}
            deal.setDetails(details);
        }
        return deal;
    }
}
