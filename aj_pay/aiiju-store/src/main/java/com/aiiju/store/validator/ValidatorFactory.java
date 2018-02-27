package com.aiiju.store.validator;

import java.util.HashMap;
import java.util.Map;

import com.aiiju.common.validator.Validator;

/**
 * 
 * @ClassName: ValidatorFactory
 * @Description: 校验器工厂
 * @author 小飞
 * @date 2016年12月29日 下午5:28:21
 *
 */
@SuppressWarnings("rawtypes")
public class ValidatorFactory {

    private static final Map<String, Class> map;

    static {
        map = new HashMap<String, Class>();
        map.put("user", UserValidator.class);
        map.put("shop", ShopValidator.class);
        map.put("income", IncomeValidator.class);
        map.put("goodsType", GoodsTypeValidator.class);
        map.put("goods", GoodsValidator.class);
        map.put("discount", DiscountValidator.class);
        map.put("member", MemberValidator.class);
        map.put("mcard", MCardValidator.class);
        map.put("account", ShopAccountValidator.class);
        map.put("item", ItemValidator.class);
        map.put("category", CategoryValidator.class);
        map.put("reputation", MerchantValidator.class);
        map.put("merchant", MerchantValidator.class);
        
    }

    private ValidatorFactory() {

    }

    public static Validator getValidator(String param) {
        Validator validator = null;
        Class clazz = map.get(param);
        if (clazz != null) {
            try {
                return (Validator) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return validator;
    }

    public static Map<String, Class> getMap() {
        return map;
    }
    
}
