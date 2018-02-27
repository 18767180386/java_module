package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
* @ClassName: ItemValidator 
* @Description: 账目校验器
* @author 小飞 
* @date 2017年3月27日 上午11:15:50
 */
public class ItemValidator extends AbsValidator {

    @Override
    public Result checkSave(HttpServletRequest req) {
        String categoryName = req.getParameter("categoryName");
        if (StringUtils.isBlank(categoryName)) {
            return Result.build(400, "categoryName值为空");
        }
        
        String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        
        String accountId = req.getParameter("accountId");
        if (StringUtils.isBlank(accountId)) {
            return Result.build(400, "accountId值为空");
        }
        
        String imageType = req.getParameter("imageType");
        if (StringUtils.isBlank(imageType)) {
            return Result.build(400, "imageType值为空");
        }
        
        String money = req.getParameter("money");
        if (StringUtils.isBlank(money)) {
            return Result.build(400, "money值为空");
        }
        
        String type = req.getParameter("type");
        if (StringUtils.isBlank(type)) {
            return Result.build(400, "type值为空");
        }
        
        String accountType = req.getParameter("accountType");
        if (StringUtils.isBlank(accountType)) {
            return Result.build(400, "accountType值为空");
        }
        
        String operatorName = req.getParameter("operatorName");
        if (StringUtils.isBlank(operatorName)) {
            return Result.build(400, "operatorName值为空");
        }
        
        String operatorId = req.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }
        
        String createDate = req.getParameter("createDate");
        if (StringUtils.isBlank(createDate)) {
            return Result.build(400, "createDate值为空");
        }
        return Result.success();
    }
    
    public Result checkListByDate(HttpServletRequest req) {
        String accountId = req.getParameter("accountId");
        if (StringUtils.isBlank(accountId)) {
            return Result.build(400, "accountId值为空");
        }
        String date = req.getParameter("date");
        if (StringUtils.isBlank(date)) {
            return Result.build(400, "date值为空");
        }
        return Result.success();
    }
    
    @Override
    public Result checkDelete(HttpServletRequest req) {
        String id = req.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return Result.build(400, "id值为空");
        }
        String operatorId = req.getParameter("operatorId");
        if (StringUtils.isBlank(operatorId)) {
            return Result.build(400, "operatorId值为空");
        }
        return Result.success();
    }
    
}
