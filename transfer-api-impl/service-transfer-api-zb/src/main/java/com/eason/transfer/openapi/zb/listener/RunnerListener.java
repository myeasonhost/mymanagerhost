package com.eason.transfer.openapi.zb.listener;

import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonNode;
import org.redisson.api.RExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.config.RedissonNodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
@Slf4j
public class RunnerListener implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void run(String... args)  {
//        RExecutorService e = redissonClient.getExecutorService("myExecutor");
//        e.execute(new ExecutorServiceExamples.RunnableTask());
//        e.submit(new ExecutorServiceExamples.CallableTask());
//
//        e.shutdown();
    }

}
