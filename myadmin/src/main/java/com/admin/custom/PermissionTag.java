package com.admin.custom;

import com.admin.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class PermissionTag  extends RequestContextAwareTag {
    private Logger logger = LogManager.getLogger(this.getClass());
    private int userId;
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserService userService;

    @Override
    public int doStartTagInternal() throws JspException {
        String outStr = "";
        try {
            HttpSession session = pageContext.getSession();
            ServletRequest request = pageContext.getRequest();
            JspWriter writer = pageContext.getOut();

            writer.write(outStr);
            writer.flush();
        } catch (IOException e) {
            logger.error("Get Messages Count IOException-------------->>>>>>>>>>",e);
        }
        return 0;
    }
    @Override
    public void release() {
        super.release();
        //this.user = null;

    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
