package com.admin.custom;

import com.admin.constant.GlobalConstant;
import com.admin.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class UserInfoTag extends TagSupport {
   // private UserInfo user;

    @Override

    public int doStartTag() throws JspException {
        try {
          //  User user=(User)this.pageContext.getRequest().getAttribute(GlobalConstant.USER_LOGIN);
            HttpSession session = pageContext.getSession();
            ServletRequest request = pageContext.getRequest();
                   // User u = (User) session.getAttribute(CommonConstant.USER_CONTEXT);
            JspWriter out = this.pageContext.getOut();
            out.println("<table width='500px' border='1' align='center'>");
            out.println("<tr>");
            out.println("<td width='20%'>Username:</td>");
            out.println("<td>sdsddsds</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Age:</td>");
            out.println("<td>1212</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Email:</td>");
            out.println("<td>sdds</td>");
            out.println("</tr>");
            out.println("</table>");
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;

    }

     //return EVAL_PAGE; // 表示处理完标签后继续执行以下的JSP网页
    // return SKIP_PAGE; //表示不处理接下来的JSP网页

    @Override

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
        //this.user = null;

    }
}