package com.eason.transfer.openapi.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.eason.transfer.openapi.core.api.dao"})
public class ServiceTransferOpenCoreApplication {

    @Bean
    @LoadBalanced //在注册中心里进行查找微服务,负载均衡
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceTransferOpenCoreApplication.class, args);
    }

}
