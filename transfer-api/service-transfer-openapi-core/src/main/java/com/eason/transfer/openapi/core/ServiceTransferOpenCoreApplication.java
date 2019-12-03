package com.eason.transfer.openapi.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages= {"com.eason"})
@MapperScan(basePackages = {"com.eason.transfer.openapi.core.api.dao","com.eason.transfer.openapi.core.client"})
public class ServiceTransferOpenCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferOpenCoreApplication.class, args);
    }

}
