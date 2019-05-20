package com.eason.report.pull.h8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceReportPullH8Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceReportPullH8Application.class, args);
    }

}
