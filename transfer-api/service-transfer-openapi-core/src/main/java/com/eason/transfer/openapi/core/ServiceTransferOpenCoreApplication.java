package com.eason.transfer.openapi.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages={"com.eason.transfer.openapi.core.api.dao"})
public class ServiceTransferOpenCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferOpenCoreApplication.class, args);
    }

}
