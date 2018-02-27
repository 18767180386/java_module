package com.aiiju.pay.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.JsonUtils;
import com.aiiju.pay.util.ApiService;
import com.aiiju.pay.validator.DealValidator;
import com.aiiju.pay.validator.DecometerDealValidator;
import com.aiiju.pay.validator.OtherDealValidator;
import com.aiiju.pay.validator.RefundDealValidator;

/**
 * 
 * @ClassName: ParameterInterceptor
 * @Description: 参数校验拦截器
 * @author 小飞
 * @date 2016年12月29日 下午5:42:54
 *
 */     
public class ParameterInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiService apiService;
    
    private static Logger logger = Logger.getLogger(ParameterInterceptor.class);
    
    @Override   
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
    	
    	//System.out.println("GETTOKEN_URL="+GETTOKEN_URL);
      String uri = req.getRequestURI();
      logger.info("请求uri="+uri);
  	  Enumeration paramNames = req.getParameterNames();  
      while (paramNames.hasMoreElements()) {  
          String paramName = (String) paramNames.nextElement(); 
	     
	      String paramValue = req.getParameter(paramName);
	      System.out.println(""+paramName+"："+paramValue);

      }
    	
        // 与支付，订单不相关的请求放行
        String[] filterStr = { "/js", "/image", "/code", "/zfbauth", "/wxauth", "/qqauth",
                "/zfbpay/notifyURL", "/zfbpay/finish", "/wxpay/notifyURL", "/wxpay/finish",
                "/qqpay/notifyURL", "/qqpay/finish","/paCallBack", "/pawx/notifyURL", "/pazfb/notifyURL", "/paCode"};
       
        
        for (String str : filterStr) {
            if (uri.contains(str)) {
                return true;
            }
        }
        
        
        /**
         * 2017年4月13  变更逻辑开始
         */
        if (uri.contains("/zfbpay/pay") || uri.contains("/wxpay/pay") || uri.contains("/qqpay/pay")) {// 台卡支付
           
        }else{
        	
            //校验token
            String token = req.getParameter("token");
//            if (StringUtils.isBlank(token)) {
//                getWriter(resp).write(JsonUtils.objectToJson(Result.build(400, "token值为空,请更新APP到最新版本")));
//                return false;
//            }
//            
//            Map<String,String> params = new HashMap<String,String>();
//            params.put("token", token);
//             Result apiResult = this.apiService.doPost("https://store.ecbao.cn/sso/checkToken", params);
////             Result apiResult = this.apiService.doPost("http://16794ui705.iok.la:32542/sso/checkToken", params);
////            Result apiResult = this.apiService.doPost("http://192.168.4.215:8090/aiiju-store/sso/checkToken", params);
//            if (apiResult.getStatus() != 200) {
//                getWriter(resp).write(JsonUtils.objectToJson(Result.build(500, "服务端系统异常")));
//                return false;
//            }
//            Result rt = JsonUtils.jsonToPojo(apiResult.getMsg(), Result.class);
//            if (rt.getStatus() != 200) {
//                getWriter(resp).write(JsonUtils.objectToJson(rt));
//                return false;
//            }
        
            
        }
        
        /**
         * 2017年4月13  变更逻辑结束
         */
   
        //接口参数校验
        DealValidator validator = null;
        if (uri.contains("/zfbpay/pay") || uri.contains("/wxpay/pay") || uri.contains("/qqpay/pay")) {// 台卡支付
            validator = new DecometerDealValidator();
        } else if (uri.contains("/zfbpay/refund") || uri.contains("/wxpay/refund")
                || uri.contains("/qqpay/refund") || uri.contains("/cash/refund") || uri.contains("/pawx/refund")|| uri.contains("/pazfb/refund")) {
            validator = new RefundDealValidator();
        } else {
            validator = new OtherDealValidator();
        }
        Result result = validator.checkParameter(req);
        if (result.getStatus() == 200) {
            return true;
        }
        getWriter(resp).write(JsonUtils.objectToJson(result));
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception e)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

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
