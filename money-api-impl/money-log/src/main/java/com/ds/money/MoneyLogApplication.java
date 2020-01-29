package com.ds.money;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = {"com.ds.money.dao"})
public class MoneyLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyLogApplication.class, args);
    }

}
