package com.eason.live.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ZbLiveMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZbLiveMediaApplication.class, args);
	}
}
