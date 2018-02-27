package com.admin.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/15.
 */
public class CommonParams {
    private static String httpkey="zhiyebao_key";
    public static Map<String,String> getBaseParams(Map<String,Object>  params)
    {

        String param = JSON.toJSONString(params);
        String sign=getHttpSign(param);
        Map<String,String> paraPost = new HashMap<String,String>();
        // paraPost.put("method", method);
        paraPost.put("sign",sign);
        paraPost.put("args", param);
        // HLog.w("post_params","base_url"+HttpManager.BASEURLPOST+"--"+paraPost.toString());
        return  paraPost;
    }

    public static  String getHttpSign(String params) {
        String sign = "";
        try {
            String param = params;
            sign = MdUtils.md5("args" + param + httpkey).toLowerCase();
        } catch (Exception e) {

        }
        return sign;
    }
}
