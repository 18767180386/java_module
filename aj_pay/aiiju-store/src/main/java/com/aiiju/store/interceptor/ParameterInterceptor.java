package com.aiiju.store.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.common.validator.Validator;
import com.aiiju.store.validator.ValidatorFactory;

/**
 * 
 * @ClassName: ParameterInterceptor
 * @Description: 参数校验拦截器
 * @author 小飞
 * @date 2016年12月29日 下午5:42:54
 *
 */
public class ParameterInterceptor implements HandlerInterceptor {
    
    private static String[] filterUrls = { "/sso", "/note", "/shop", "/user/inviteUI", "/user/inviteUser", "/user/inviteResult","/user/checkInviteByNote", "member", "/image", "/css",
    "/js" };
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {

    	
  
          
          
        String uri = req.getRequestURI();
     
        for (String url : filterUrls) {
            if (uri.contains(url)) {// 获取验证码、登录注册相关、创建店铺，邀请店员，放行
                return true;
            }
        }

//        String[] tmp = uri.split("/");// ,类,方法
//        if (tmp.length != 3) {
//            Result rt = Result.build(400, "URL不正确");
//            getWriter(resp).write(JsonUtils.objectToJson(rt));
//            return false;
//        }
//
//        Validator validator = ValidatorFactory.getValidator(tmp[1]);
//        if (validator != null) {
//            Result result = null;
//            Map<String, Class> map = ValidatorFactory.getMap();
//            Class clazz = map.get(tmp[1]);
//            String methodName = "check" + tmp[2].substring(0,1).toUpperCase() + tmp[2].substring(1);
//            Method method = clazz.getMethod(methodName, HttpServletRequest.class);
//            result = (Result) method.invoke(validator, req); 
//         
//            if (result != null) {
//                if (result.getStatus() != 200) {
//                    getWriter(resp).write(JsonUtils.objectToJson(result));
//                    return false;
//                }
//            }
//        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception e) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }

    private PrintWriter getWriter(HttpServletResponse resp) {
        resp.setContentType("text/html;charset=UTF-8");
        try {
            return resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
