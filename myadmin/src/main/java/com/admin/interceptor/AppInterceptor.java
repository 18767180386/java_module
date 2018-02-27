package com.admin.interceptor;

import com.admin.dto.JsonResult;
import com.admin.dto.Result;
import com.admin.service.RedisService;
import com.admin.util.CommonParams;
import com.admin.util.ParameterUtil;
import com.admin.util.Utils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

public class AppInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        //首先进入的方法
        //  System.out.println("preHandle");

        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return false表示拦截，不向下执行
        //return true表示放行\
       // return  true;

        String sign = request.getParameter("sign");
        String args = request.getParameter("args");
        if(Utils.isEmpty(sign))
        {
            String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("", "-001", "签名为空"));
            writeJson(response, reslutJSON);
            return false;
        }

        if(Utils.isEmpty(args))
        {
            String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("", "-002", "参数为空"));
            writeJson(response, reslutJSON);
            return false;
        }

        Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);
        String key="";
        String _args=Utils.urlDecoder(args);
        System.out.println("sign--------"+_args);
        String md5_sign = CommonParams.getHttpSign(_args);
        System.out.println("sign1--------"+md5_sign+"--------"+sign);
        if (sign.equals(md5_sign)) {
            return true;
        } else {
            String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("", "-004", "签名错误"));
            writeJson(response, reslutJSON);
            return false;
        }

        /*
        if(map.containsKey("user_id"))
        {
            key=map.get("user_id").toString();
            String token=redisService.get(key);
            if(token==null || Utils.isEmpty(token) || !token.equals(map.get("token")))
            {
                String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("", "-003", "token过期"));
                writeJson(response, reslutJSON);
                return false;
            }else{
                String md5_sign = CommonParams.getHttpSign(args);
                if (sign.equals(md5_sign)) {
                    return true;
                } else {
                    String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("", "-004", "签名错误"));
                    writeJson(response, reslutJSON);
                    return false;
                }
            }
        }else{
            String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("", "-005", "usser_id不存在"));
            writeJson(response, reslutJSON);
            return false;
        }
        */
    }


    /**
     * 输出json
     *
     * @param response
     * @param ret
     */
    public void writeJson(HttpServletResponse response, String ret) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(ret);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 数据写出
     *
     * @param result
     * @param response
     */
    private void writeResult(Result result, HttpServletResponse response) {
        try {
            String reslutJSON = JSONObject.toJSONString(result);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(reslutJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //返回modelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            System.out.println(name + "=" + value);
        }
        System.out.println("postHandle");
    }

    //执行Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion");
    }
}