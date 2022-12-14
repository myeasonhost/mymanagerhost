package com.ds.money;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = {"com.ds.money.dao"})
public class MoneyApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoneyApiApplication.class, args);
    }

}
