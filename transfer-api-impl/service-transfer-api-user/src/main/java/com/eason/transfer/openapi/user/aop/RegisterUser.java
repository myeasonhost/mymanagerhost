package com.eason.transfer.openapi.user.aop;

import java.lang.annotation.*;

/**
 * @Author eason
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterUser {
    boolean chess() default true;
}
