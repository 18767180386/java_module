package com.admin.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    /**
     * 权限类型
     * @return
     */
    String type() default "";
    /**
     * 是否需要写权限
     * @return
     */
    boolean write() default false;
}
