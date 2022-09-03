package com.eason.transfer.openapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages= {"com.eason"})
@MapperScan(basePackages = {"com.eason.transfer.openapi.core.api.dao","com.eason.transfer.openapi.pay.mapper"})
public class ServiceTransferOpenAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferOpenAPIApplication.class, args);
    }

}
