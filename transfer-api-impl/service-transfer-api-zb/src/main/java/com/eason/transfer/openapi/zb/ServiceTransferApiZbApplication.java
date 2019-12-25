package com.eason.transfer.openapi.zb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.eason.transfer.openapi.zb.api"})
public class ServiceTransferApiZbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferApiZbApplication.class, args);
    }

}
