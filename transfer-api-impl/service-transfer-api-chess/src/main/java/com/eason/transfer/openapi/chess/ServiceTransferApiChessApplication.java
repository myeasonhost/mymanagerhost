package com.eason.transfer.openapi.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
public class ServiceTransferApiChessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferApiChessApplication.class, args);
    }

}
