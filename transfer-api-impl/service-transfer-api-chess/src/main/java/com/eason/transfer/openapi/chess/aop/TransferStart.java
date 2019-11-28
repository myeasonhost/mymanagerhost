package com.eason.transfer.openapi.chess.aop;

import java.lang.annotation.*;

/**
 * @Author eason
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransferStart {
    boolean chess() default true;
}
