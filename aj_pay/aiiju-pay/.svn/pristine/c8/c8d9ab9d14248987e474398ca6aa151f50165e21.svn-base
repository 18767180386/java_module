package com.aiiju.pay.controller.cash;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.RandomUtil;
import com.aiiju.pay.bo.DealBuilder;
import com.aiiju.pay.service.DealService;
import com.aiiju.pay.service.MPointsDetailService;
import com.aiiju.pojo.Deal;
import com.aiiju.pojo.DealDetail;

/**
 * 
 * @ClassName: CashController
 * @Description: 现金支付
 * @author 小飞
 * @date 2016年12月3日 下午3:53:07
 *
 */
@Controller
@RequestMapping("/cash")
public class CashController {

    private static Logger logger = Logger.getLogger(CashController.class);

    @Autowired
    private DealService dealService;
    
    
    @Autowired
    private MPointsDetailService mPointsDetailService;
    

    @RequestMapping("/pay")
    @ResponseBody
    public Result pay(HttpServletRequest req) {
        String storeId = req.getParameter("storeId");
        String outTradeNo = storeId + RandomUtil.generateCode();
        // 保存订单
        Deal deal = DealBuilder.buildCashOrder(req, outTradeNo, storeId,"1");
       // List<DealDetail> list = deal.getDetails();
        deal.setStatus(Byte.parseByte("1"));// 流水订单状态 1：完成 2：进行中 3：失败
        try {
            // 保存

        	Result rt = this.dealService.saveDeal(deal, "cash");
        	
        	mPointsDetailService.saveMPointsDetail(deal);
        	
        	return rt;
//            if (rt) {
//                return Result.success(rt);
//            } else {
//                return Result.build(500, "此订单保存失败,请重新创建");
//            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, "系统内部错误");
        }
    }

    @RequestMapping("/refund")
    @ResponseBody
    public Result refund(HttpServletRequest req) {
        String storeId = req.getParameter("storeId");
        String outTradeNo = storeId + RandomUtil.generateRefundCode();
        // 保存退款订单
        Deal refundDeal = DealBuilder.buildCashRefundOrder(req, outTradeNo, storeId,"2","");
        try {
            // 保存
            Result rt = this.dealService.saveRefundDeal(refundDeal, "cash");
            if (rt.getStatus() != 200) {
                return rt;
            }
            return Result.build(10000, "现金退款成功!", true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, "系统内部错误");
        }
    }
}
