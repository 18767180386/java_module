package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
 * @ClassName: ShopAccountValidator
 * @Description: 账本校验器
 * @author 小飞
 * @date 2016年12月29日 下午4:04:50
 *
 */
public class ShopAccountValidator extends AbsValidator {

    @Override
    public Result checkSave(HttpServletRequest req) {
        String name = req.getParameter("name");
        if (StringUtils.isBlank(name)) {
            return Result.build(400, "name值为空");
        }
        
        String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        
        String color = req.getParameter("color");
        if (StringUtils.isBlank(color)) {
            return Result.build(400, "color值为空");
        }
        return Result.success();
    }
    
    @Override
    public Result checkList(HttpServletRequest req) {
        String operatorId = req.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }
        String currentNum = req.getParameter("currentNum");
        if (StringUtils.isBlank(currentNum)) {
            return Result.build(400, "currentNum值为空");
        }
        String pageSize = req.getParameter("pageSize");
        if (StringUtils.isBlank(pageSize)) {
            return Result.build(400, "pageSize值为空");
        }
        return checkStoreId(req);
    }
    
    public Result checkShare(HttpServletRequest req) {
        String operatorId = req.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }
        return checkId(req);
    }
    
    public Result checkUnShare(HttpServletRequest req) {
        String operatorId = req.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }
        return checkId(req);
    }
    
    public Result checkAccountUserList(HttpServletRequest req) {
        String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        return checkId(req);
    }
    
    public Result checkSetBudget(HttpServletRequest req) {
        String id = req.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return Result.build(400, "id值为空");
        }
        String budgetType = req.getParameter("budgetType");
        if (StringUtils.isBlank(budgetType)) {
            return Result.build(400, "budgetType值为空");
        }
        String budgetMoney = req.getParameter("budgetMoney");
        if (StringUtils.isBlank(budgetMoney)) {
            return Result.build(400, "budgetMoney值为空");
        }
        return Result.success();
    }
    
    public Result checkSetDefault(HttpServletRequest req) {
    //	 String id = req.getParameter("id");
  //    System.out.println("checkSetDefault校验器id="+id);
        return Result.success();
    }
    
    public Result checkGetBudget(HttpServletRequest req) {
  //    String id = req.getParameter("id");
   //   System.out.println("checkGetBudget校验器id="+id);
       return Result.success();
   }
   
}
