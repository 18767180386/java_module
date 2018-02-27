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
import com.aiiju.pojo.MCard;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.MCardService;

/**
 * 
 * @ClassName: MCardController
 * @Description: 会员卡控制器
 * @author 小飞
 * @date 2016年11月17日 上午11:19:42
 *
 */
@Controller
@RequestMapping("/mcard")
public class MCardController {

    private static Logger logger = Logger.getLogger(MCardController.class);

    @Autowired
    private MCardService mcardService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd"));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result get(Long id) {
        try {
            return this.mcardService.getWithObjectById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result gets(String storeId, Integer currentNum, Integer pageSize) {
        try {
            return this.mcardService.getAllByStoreId(storeId, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(MCard mcard) {
        try {
            return this.mcardService.save(mcard);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(MCard mcard) {
        try {
            return this.mcardService.update(mcard);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        try {
            return this.mcardService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
}
