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
import com.aiiju.pojo.Item;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.editor.DateEditor;
import com.aiiju.store.service.ItemService;

/**
 * 
 * @ClassName: ItemController
 * @Description: 账目控制器
 * @author 小飞
 * @date 2017年3月22日 下午1:46:19
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private static Logger logger = Logger.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
            binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd HH:mm:ss"));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result get(Long id) {
        try {
            return this.itemService.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result gets(Long accountId,@RequestParam(value = "currentNum", defaultValue = "1") Integer currentNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
        	
            return this.itemService.getAllByAccountId(accountId,currentNum,pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
    
    @RequestMapping("/listByDate")
    @ResponseBody
    public Result listByDate(Long accountId,String date,@RequestParam(value = "currentNum", defaultValue = "1") Integer currentNum,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        try {
            return this.itemService.getPageByDate(accountId, date, currentNum, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(Item item) {
        try {

            return this.itemService.save(item);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Item item) {
        try {
            return this.itemService.update(item);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deletes(String operatorId, Long id) {
        try {
            return this.itemService.deleteById(operatorId, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
}
