package com.eason.report.pull.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullMgApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate10;

    @Test
    public void contextLoads() throws Exception {
//        mgPullAPIImpl.getPullBet("2019:05:27:00:21:10",30);
//        mgPullAPIImpl.getPullBet();
    }

    @Test
    public void testRedis() throws Exception{

    }

}
