package com.aiiju.pay.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiiju.common.pojo.Result;
import com.aiiju.mapper.DealDetailMapper;
import com.aiiju.mapper.DealMapper;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.JPushService;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealDetail;

/**
 * 
 * @ClassName: DealServiceImpl
 * @Description: 交易流水ServiceImpl
 * @author 小飞
 * @date 2016年11月14日 下午4:06:59
 *
 */
@Service("dealService")
public class DealServiceImpl implements DealService {

    @Autowired
    private DealMapper dealMapper;

    @Autowired
    private DealDetailMapper dealDetailMapper;

    @Autowired
    private JPushService jpushService;

    @Override
    public Result saveDeal(Deal deal, String type) {

    	try {
    		   // 保存流水单信息
            this.dealMapper.add(deal);
            
            
            System.out.println("----保存流水后：seller_id="+deal.getSellerId());
            
            String serialnum = deal.getSerialNum();
            // 保存其详情
            List<DealDetail> details = deal.getDetails();
            for (DealDetail dealDetail : details) {
                if (StringUtils.isBlank(dealDetail.getUnit())) {//替换版本时为了预防老版本报错添加的判断
                    dealDetail.setUnit("件");
                }
                dealDetail.setSerialNum(deal.getSerialNum());
            }
            this.dealDetailMapper.insertBatch(details);
           
            if ("cash".equals(type)) {
                // 推送消息
                this.jpushService.sendPayNotice(deal);
            }
            return Result.build(200, "操作成功", serialnum);
            
		} catch (Exception e) {
			 return Result.build(500, "保存流水单信息失败","");
		}
     
     
        
        
       
    }

    
//    // 20170824  收款后再推送，此时只有更新订单
//    @Override
//    public Boolean updateDealNew(Deal deal) {
//        Boolean rt = false;
//        this.dealMapper.update(deal);
//        // 推送消息
//        rt = true;
//        return rt;
//    }
    
    @Override
    public Boolean updateDeal(Deal deal) {
        Boolean rt = false;
        this.dealMapper.update(deal);
        // 推送消息
        this.jpushService.sendPayNotice(deal);
        rt = true;
        return rt;
    }

    @Override
    public Result saveRefundDeal(Deal deal, String type) {
        if ("cash".equals(type)) {
            return this.saveCashRefundDeal(deal);
        } else {
            return this.saveInterfaceRefundDeal(deal);
        }
    }

    private Result saveInterfaceRefundDeal(Deal deal) {
        Deal payDeal = this.dealMapper.getBySerialNum(deal.getOriginalSerialNum());
        if (payDeal == null) {
            return Result.build(401, "订单号不存在");
        }
        if ("已全额退款".equals(payDeal.getTradeStatus())) {
            return Result.build(401, "该订单号已全额退款");
        }
        if (payDeal.getCanRefundFee().subtract(deal.getPrice().abs()).doubleValue() < 0) {
            return Result.build(401, "该订单号退款金额大于可退金额");
        }
        this.dealMapper.addRefund(deal);
        return Result.success();
    }

    private Result saveCashRefundDeal(Deal refundDeal) {
        Deal payDeal = this.dealMapper.getBySerialNum(refundDeal.getOriginalSerialNum());
        if (payDeal == null) {
            return Result.build(401, "订单号不存在");
        }
        if ("已全额退款".equals(payDeal.getTradeStatus())) {
            return Result.build(401, "该订单号已全额退款");
        }
        BigDecimal subtract = payDeal.getCanRefundFee().subtract(refundDeal.getPrice().abs());
        if (subtract.doubleValue() < 0) {
            return Result.build(401, "该订单号退款金额大于可退金额");
        }
        if (subtract.doubleValue() > 0) {
            payDeal.setTradeStatus("已部分退款");
        } else {
            payDeal.setTradeStatus("已全额退款");
        }
        payDeal.setCanRefundFee(subtract);
        //添加退款订单
        refundDeal.setStatus(Byte.parseByte("1"));// 流水订单状态 1：完成 2：进行中 3：失败
        this.dealMapper.addRefund(refundDeal);
        //修改原订单状态
        this.dealMapper.updateOldDeal(payDeal);
        // 推送
        this.jpushService.sendRefundNotice(refundDeal);
        return Result.success();
    }

    @Override
    public void updateRefundDeal(String refundSerialNum, String tradeNo) {
        // 修改退款订单状态
        Deal refundDeal = this.dealMapper.getBySerialNum(refundSerialNum);
        refundDeal.setStatus(Byte.valueOf("1"));
        refundDeal.setTradeNo(tradeNo);
        refundDeal.setTradeStatus("已退款");
        this.dealMapper.update(refundDeal);
        
        System.out.println("退款后：updateRefundDeal（），成功更新当前订单的交易状态：已退款");
        
        // 修改原支付订单状态
        Deal payDeal = this.dealMapper.getBySerialNum(refundDeal.getOriginalSerialNum());
        System.out.println("退款后：updateRefundDeal（），欲修改原支付订单状态："+payDeal);
        System.out.println("退款后：updateRefundDeal（），欲修改原支付订单状态：payDeal.getCanRefundFee()="+payDeal.getCanRefundFee());
        System.out.println("退款后：updateRefundDeal（），欲修改原支付订单状态：refundDeal.getPrice().abs()="+refundDeal.getPrice().abs());
        BigDecimal subtract = payDeal.getCanRefundFee().subtract(refundDeal.getPrice().abs());
        System.out.println("退款后：updateRefundDeal（），欲修改原支付订单状态：subtract="+subtract);
        if (subtract.doubleValue() > 0) {
            payDeal.setTradeStatus("已部分退款");
        } else {
            payDeal.setTradeStatus("已全额退款");
        }
        System.out.println("退款后：updateRefundDeal（），欲修改原支付订单状态：getTradeStatus="+payDeal.getTradeStatus());
        payDeal.setCanRefundFee(subtract);
        this.dealMapper.updateOldDeal(payDeal);
        System.out.println("退款后：updateRefundDeal（），欲修改原支付订单状态：updateOldDeal(payDeal);payDeal="+payDeal);
        // 推送
        this.jpushService.sendRefundNotice(refundDeal);
    }
    
    @Override
    public Deal getDealBySerialNum(String serialNum) {
        return this.dealMapper.getBySerialNum(serialNum);
    }
}
