package com.aiiju.store.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiiju.common.pojo.Result;
import com.aiiju.pojo.Category;
import com.aiiju.store.constant.WebConstant;
import com.aiiju.store.service.CategoryService;
/**
 * 
* @ClassName: CategoryController 
* @Description: 类目控制器
* @author 小飞 
* @date 2017年3月22日 上午10:19:29
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    private static Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    @ResponseBody
    public Result list(String storeId) {
        try {
            return this.categoryService.getAllByStoreId(storeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(Category category) {
        try {
            return this.categoryService.save(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        try {
            return this.categoryService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.build(500, WebConstant.SYS_ERROR);
        }
    }
}
