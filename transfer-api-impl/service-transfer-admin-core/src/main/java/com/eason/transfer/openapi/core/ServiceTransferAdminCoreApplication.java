package com.eason.transfer.openapi.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = {"com.eason"})
public class ServiceTransferAdminCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferAdminCoreApplication.class, args);
    }

}
