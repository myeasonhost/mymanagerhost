package com.eason.report.pull.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceReportPullBBinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceReportPullBBinApplication.class, args);
    }

}
