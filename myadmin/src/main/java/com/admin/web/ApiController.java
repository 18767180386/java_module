package com.admin.web;

import com.admin.dto.JsonResult;
import com.admin.entity.RequestCode;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/18.
 */
public class ApiController {


    /**
     *
     *
     * @param response
     * @param
     */
    public void  writeJson(HttpServletResponse response, Object ret)
    {
        String reslutJSON = JSONObject.toJSONString(ret);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(reslutJSON);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
