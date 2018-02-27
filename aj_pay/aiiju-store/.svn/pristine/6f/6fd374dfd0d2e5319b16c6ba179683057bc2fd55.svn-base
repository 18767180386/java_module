package com.aiiju.store.controller.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.ScrmService;

/**
 * @ClassName: ErpController  
 * @Description: 和ERP 系统对接相关 代码
 * @author 天明
 * @date 2017年7月13日下午3:58:18
 */
@Controller
@RequestMapping("/scrm")
public class ScrmController {
	
	private static Logger logger = Logger.getLogger(ScrmController.class);
	
	
    @Autowired
    private ScrmService scrmService;
    
	
    /**
     * 获取scrm授权的店铺 ，所谓的获取授权。就是拿到授权店铺的信息。以及总店的信息
     * @param rId 				由scrm系统生成的标志
     * @param requtestTime   	发出请求时的时间
     * @param sign    			签名
     * @return
     */
    @RequestMapping(value = "/getScrmGrant")
    @ResponseBody
    public Result getScrmGrant(@RequestParam Map<String,Object> map ) {
    	
        try {
            return scrmService.updateScrmGrant(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
	
    
    /**
     * 获取scrm会员卡
     * @param query 			手机号或者会员卡号
     * @param shopId   			爱聚收银店铺的storeId
     * @return
     */
    
    @RequestMapping(value = "/getScrmMemberInfo")
    @ResponseBody
    public Result getScrmMemberInfo(@RequestParam Map<String,Object> map ) {
    	try {
            return scrmService.getScrmMemberInfo(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    /**
     * 店铺是否关联scrm公司
     * @param storeId    店铺storeId
     * @return
     */
    @RequestMapping("/getRelativeScrm")
    @ResponseBody
    public Result getRelativeScrm(@RequestParam  Map<String,Object> map) {
        try {
            return this.scrmService.getRelativeScrm(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    /**
	 * 解绑 scrm
	 * @param  storeIds   店铺的id,多个逗号隔开
	 * @return 
	 */
    
    @RequestMapping("/updateUnwrapScrm")
    @ResponseBody
    public Result updateUnwrapScrm(@RequestParam  Map<String,Object> map) {
        try {
            return this.scrmService.updateUnwrapScrm(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    /**
	 * 绑定 scrm
	 * @param  storeIds   店铺的id,多个逗号隔开
	 * @return 
	 */
    @RequestMapping("/updateBindScrm")
    @ResponseBody
    public Result updateBindScrm(@RequestParam  Map<String,Object> map) {
        try {
            return this.scrmService.updateBindScrm(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    /**
     * 店铺列表和scrm绑定状态
     * @param pStoreId    操作店铺的父店铺storeId
     * @param scrmId    SCRM公司id
     * @param role    店铺角色  
     * @return
     */
    @RequestMapping("/getRelativeScrmShopList")
    @ResponseBody
    public Result getRelativeScrmShopList(@RequestParam  Map<String,Object> map) {
        try {
            return this.scrmService.getRelativeScrmShopList(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    
    
   
    
    
   
    

    
    
  
    

}
