package com.aiiju.store.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.DealService;

/**
 * 
 * @ClassName: IncomeController
 * @Description: 收入
 * @author 小飞
 * @date 2016年12月13日 下午2:30:53
 *
 */
@Controller
@RequestMapping("/income")
public class IncomeController {

    private static Logger logger = Logger.getLogger(IncomeController.class);

    @Autowired
    private DealService dealService;

    /**
     * 获取收入
     * 
     * @param storeId
     * @param type
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result list(String storeId) {
        try {
            return this.dealService.summarize(storeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    /**
     * 收入详情
     * 
     * @param type
     * @return
     */
    @RequestMapping("/listByType")
    @ResponseBody
    public Result listByType(String storeId, String type, Integer currentNum, Integer pageSize) {
        try {
            return this.dealService.getIncomeDetail(storeId, type, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/refundList")
    @ResponseBody
    public Result refundList(String storeId, Integer currentNum, Integer pageSize) {
        try {
            return this.dealService.getRefundList(storeId, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
}
