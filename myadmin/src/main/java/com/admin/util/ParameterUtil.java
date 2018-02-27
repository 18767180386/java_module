package com.admin.util;

import com.alibaba.fastjson.JSONObject;

import java.net.URLDecoder;

/**
 * Created by Administrator on 2017/7/15.
 */
public class ParameterUtil {
    @SuppressWarnings("unchecked")
    public static boolean check(String json, @SuppressWarnings("rawtypes") Class clazz) {
        if (null != json) {
            try {
                JSONObject.parseObject(json, clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static Object parseObj(String json, Class clazz) {
        try {
            String _json = URLDecoder.decode(json, "UTF-8");
            Object obj = JSONObject.parseObject(_json, clazz);
            return obj;
        }catch (Exception e)
        {
            return null;
        }
    }
}
