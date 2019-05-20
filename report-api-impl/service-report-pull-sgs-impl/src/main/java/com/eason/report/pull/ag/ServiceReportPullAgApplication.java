package com.eason.report.pull.ag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceReportPullAgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceReportPullAgApplication.class, args);
    }

}
