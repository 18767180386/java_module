package com.aiiju.store.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.validator.AbsValidator;

/**
 * 
 * @ClassName: GoodsTypeValidator
 * @Description: 商品类型校验器
 * @author 小飞
 * @date 2016年12月29日 下午4:34:51
 *
 */
public class GoodsTypeValidator extends AbsValidator {

    @Override
    public Result checkSave(HttpServletRequest req) {
        String storeId = req.getParameter("storeId");
        if (StringUtils.isBlank(storeId)) {
            return Result.build(400, "storeId值为空");
        }
        String name = req.getParameter("name");
        if (StringUtils.isBlank(name)) {
            return Result.build(400, "name值为空");
        }
        return Result.success();
    }

    public Result checkListWithGoods(HttpServletRequest req) {
        return this.checkStoreId(req);
    }
    public Result checkGetRelationParentGoodsList(HttpServletRequest req) {
        return Result.success();
    }
    public Result checkSaveOrDeleteRelationGoods(HttpServletRequest req) {
        return Result.success();
    }
    
}
