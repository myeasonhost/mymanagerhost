package com.eason.transfer.openapi.core.annotation;

import java.lang.annotation.*;


/**
 * 服务提供方发布服务的注解
 *
 * @author eason
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethodCategory {

    String cateEnName() default "";

    String cateCnName() default "";

    String cateDescription() default "";

    boolean isDeleted() default true;

}