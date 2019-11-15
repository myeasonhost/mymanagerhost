package com.eason.transfer.openapi.user.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectService {

    @Before("@annotation(transferStart)")
    public void beforeTest(JoinPoint point, TransferStart transferStart) throws Throwable {
        System.out.println("beforeTest:" + transferStart.chess());
    }

    @After("@annotation(transferStart)")
    public void afterTest(JoinPoint point, TransferStart transferStart) {
        System.out.println("afterTest:" + transferStart.chess());
    }

}
