package com.aiiju.store.controller.rest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.SignPay;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.SignPayService;

/** 
 * @ClassName SignController
 * @Description
 * @author zong
 * @CreateDate 2017年8月8日 下午3:16:30
 */
@Controller
@RequestMapping("/sign")
public class SignPayController {
	
    private static Logger logger = Logger.getLogger(SignPayController.class);
	
	@Autowired
	private SignPayService signService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd HH:mm:ss"));
    }
	
    /**
     * 
     * @Description    通道签约状态查询
     * @param storeId
     * @param parentStoreId
     * @param shopType
     * @return      
     * @return Result
     */
    @RequestMapping("/getSignStatus")
    @ResponseBody
    public Result getSignStatus(SignPay signPay) {
        try {
        	
            return this.signService.getSignStatus(signPay);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    /**
     * 
     * @Description    收款开关查询
     * @param storeId
     * @param parentStoreId
     * @param shopType
     * @return      
     * @return Result
     */
    @RequestMapping("/getSwitchStatus")
    @ResponseBody
    public Result getSwitchStatus(SignPay signPay) {
        try {
        	
            return this.signService.getSwitchStatus(signPay);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    /**
     * 
     * @Description    收款开关更新
     * @param storeId
     * @param parentStoreId
     * @param shopType
     * @return      
     * @return Result
     */
    @RequestMapping("/modifyPaySwitch")
    @ResponseBody
    public Result modifyPaySwitch(SignPay signPay) {
        try {
        	
            return this.signService.modifyPaySwitch(signPay);
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
}
