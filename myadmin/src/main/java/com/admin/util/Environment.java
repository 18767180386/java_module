package com.admin.util;

import org.springframework.web.context.ContextLoader;


public final class Environment {
    public static final String getContext() {
    	return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
    }
}
