package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
* @ClassName: CategoryValidator 
* @Description: 类目校验器
* @author 小飞 
* @date 2017年3月27日 上午11:14:52
 */
public class CategoryValidator extends AbsValidator {

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
        
        String imageType = req.getParameter("imageType");
        if (StringUtils.isBlank(imageType)) {
            return Result.build(400, "imageType值为空");
        }
        return Result.success();
    }
    
}
