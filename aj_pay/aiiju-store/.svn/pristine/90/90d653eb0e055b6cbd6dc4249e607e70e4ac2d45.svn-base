package com.aiiju.store.controller.rest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.MPoints;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.MPointsDetailService;
import com.aiiju.store.service.MPointsService;

/**
 * 
 * @ClassName: MPointsController
 * @Description: 会员积分控制器
 * @author 宗介
 * @date 2017年5月6日 上午11:19:42
 *
 */
@Controller
@RequestMapping("/mpoints")
public class MPointsController {

    private static Logger logger = Logger.getLogger(MPointsController.class);

    @Autowired
    private MPointsService mpointsService;
    
    @Autowired
    private MPointsDetailService mPointsDetailService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
    	   binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd HH:mm:ss"));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result get(String storeId) {
        try {
            return this.mpointsService.getByStoreId(storeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(MPoints mpoints) {
        try {
            return this.mpointsService.update(mpoints);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    //会员积分列表（获得积分列表）
    @RequestMapping("/getPointslist")
    @ResponseBody
    public Result getGetPointslist(String memberPhone,String storeId,@RequestParam(value = "currentNum", defaultValue = "1") Integer currentNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
        	
            return this.mPointsDetailService.getGetPointslist(memberPhone, storeId, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    //会员积分消耗列表（消耗积分列表）
    @RequestMapping("/usedPointslist")
    @ResponseBody
    public Result getUsedPointslist(String memberPhone,String storeId,@RequestParam(value = "currentNum", defaultValue = "1") Integer currentNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
        	
            return this.mPointsDetailService.getUsedPointslist(memberPhone, storeId, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/getValidPoints")
    @ResponseBody
    public Result getValidPoints(String memberPhone,String storeId) {
        try {
        	
            return this.mPointsDetailService.getValidPoints(memberPhone, storeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
}
