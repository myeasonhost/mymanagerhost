package com.eason.transfer.openapi.chess.aop;

import java.lang.annotation.*;

/**
 * 服务提供方发布服务的注解
 * @author eason
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoadConfig {

    String name() default "";
    Class targetMgr();
}