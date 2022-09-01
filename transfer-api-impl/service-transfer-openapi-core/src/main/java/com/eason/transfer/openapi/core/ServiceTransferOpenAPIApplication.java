package com.eason.transfer.openapi.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.eason.transfer.openapi"})
public class ServiceTransferOpenAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferOpenAPIApplication.class, args);
    }

}
