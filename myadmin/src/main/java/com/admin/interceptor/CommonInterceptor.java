package com.admin.interceptor;

import com.admin.annotation.AuthCheck;
import com.admin.constant.GlobalConstant;
import com.admin.dto.JsonResult;
import com.admin.dto.Result;
import com.admin.entity.User;
import com.admin.util.CommonParams;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017/7/15.
 */
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        User u=(User)session.getAttribute(GlobalConstant.USER_LOGIN);
        if(u!=null){
            //从传入的handler中检查是否有AuthCheck的声明
            HandlerMethod method = (HandlerMethod)handler;
            AuthCheck auth = method.getMethodAnnotation(AuthCheck.class);
            //找到了，取出定义的权限属性，结合身份信息进行检查
            if(auth != null) {
                String type = auth.type();
                boolean write = auth.write();
               // System.out.println(type+"-------"+write);
                //根据type与write，结合session/cookie等身份信息进行检查
                //如果权限检查不通过，可以输出特定信息、进行跳转等操作
                //并且一定要return false，表示被拦截的方法不用继续执行了
               // writeJson(response,"111---------1");
                return  true;
            }else{
                return true;
            }
        }else{
        	  //System.out.println("preHandle---------------false"+request.getParameter("limit"));
           // request.setAttribute("no",500);
            request.getRequestDispatcher("/user/login").include(request, response);
            return false;
        }

      //  return true;
        //return false表示拦截，不向下执行
        //return true表示放行
    }


    /**
     *
     * 输出json
     * @param response
     * @param ret
     */
    public void  writeJson(HttpServletResponse response,String ret)
    {
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

        Enumeration pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            String value=request.getParameter(name);
          //  System.out.println(name + "=" + value);
        }
      //  System.out.println("postHandle");
    }
    //执行Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

        //System.out.println("afterComple
        // tion");
    }


    /**
     *
     * 拦截配置
     */
    private void   interConfig()
    {
        // String method = request.getParameter("method");
        // String time = request.getParameter("time");
        //String args = request.getParameter("param");


        //Map<String, Object> map = (Map<String, Object>) ParameterUtil.parseObj(args, Map.class);

        //for (Map.Entry<String, Object> entry : map.entrySet()) {

        // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        //	_sign +=entry.getKey()+entry.getValue().toString();


        //}
		/*
	    Enumeration pNames=request.getParameterNames();
         while(pNames.hasMoreElements()){
             String name=(String)pNames.nextElement();
             String value=request.getParameter(name);
            // System.out.println(name + "=" + value);
             _sign +=name+value;
         }
         */

        // _sign=_sign+"youhuike_key";

        /*
        String sign = request.getParameter("sign");
        String args=request.getParameter("args");


        String md5_sign = CommonParams.getHttpSign(args);

        if(sign.equals(md5_sign))
        {

            return true;
        }else{
            String reslutJSON = JSONObject.toJSONString(new JsonResult<String>("","-001","参数验证错误"));
            writeJson(response,reslutJSON);
            return false;
        }
        */

        //Result result = new Result();
        // 2.判断time是否超过5分钟
        // long currentime = System.currentTimeMillis();
        // currentime = currentime / 1000;
        // if (currentime - Long.parseLong(time) > 300) {
        // result.setError_code(Response.TIME_ERROR.getErrorCode());
        // result.setError_msg(Response.TIME_ERROR.getMsg());
        // this.writeResult(result, response);
        // return false;
        // }

		/*if (StringUtils.isEmpty(method) || StringUtils.isEmpty(args) || StringUtils.isEmpty(sign)) {
			result.setCode(Response.EMPTY_ERROR.getErrorCode());
			result.setMessage(Response.EMPTY_ERROR.getMsg());
			this.writeResult(result, response);

			return false;
		}*/
        //3.判断sign有效性


        //String new_sign = "method" + method + "param" + args + "ecbao";
        //String md5_sign = MdUtils.md5(new_sign);


		/*if (!md5_sign.equals(sign)) {
			result.setCode(Response.SIGN_ERROR.getErrorCode());
			result.setMessage(Response.SIGN_ERROR.getMsg());
			this.writeResult(result, response);
			return false;
		}*/
        // 4.判断args合法性




        //String[] methods = method.split("/");
        //String request_type = methods[0];
        //WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        //Object bean = webApplicationContext.getBean(request_type + "Service");

        /*
        Enumeration pNames=request.getParameterNames();
        while(pNames.hasMoreElements()){
            String name=(String)pNames.nextElement();
            String value=request.getParameter(name);
            System.out.println("-----------------param----"+name + "=" + value);
        }

        System.out.println(request.getServletPath());

        */


		/*
		if (bean != null) {
			boolean flag = ParameterUtil.check(args, RequestParam.class);
			if (!flag) {
				//result.setCode(Response.PROP_ERROR.getErrorCode());
				result.setMessage(Response.PROP_ERROR.getMsg());

				this.writeResult(result, response);

				return false;
			}
		} else {
			//result.setCode(Response.LOGIC_ERROR.getErrorCode());
			result.setMessage(Response.LOGIC_ERROR.getMsg());
			this.writeResult(result, response);
			return false;
		}
		*/


        //return true;



		/*
        HttpSession session = request.getSession();
        User u=(User)session.getAttribute("userInfo");
        if(u!=null){
        	  System.out.println("preHandle---------------true");

            return true;
        }else{
        	  System.out.println("preHandle---------------false"+request.getParameter("limit"));
            return true;
        }
        */
    }

}