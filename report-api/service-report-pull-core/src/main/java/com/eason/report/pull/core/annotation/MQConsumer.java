package com.eason.report.pull.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 服务消费方发布服务的注解
 * @author eason
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface MQConsumer {

    /**
     * （1）服务发现用的唯一标识，用于服务自动寻址;
     * （2）服务名的命名规则：服务名首字母小写
     * @return
     */
    String name() default "";
    String code() default "";
}