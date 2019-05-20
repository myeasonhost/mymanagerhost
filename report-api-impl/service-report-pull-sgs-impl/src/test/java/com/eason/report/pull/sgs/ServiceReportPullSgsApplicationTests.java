package com.eason.report.pull.sgs;

import com.eason.report.pull.ag.activemqDemo.ActivemqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullSgsApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate10;
    @Autowired
    private StringRedisTemplate stringRedisTemplate10;

    @Autowired
    private ActivemqService activemqService;


    @Test
    public void contextLoads() throws Exception {

    }

    @Test
    public void testRedis() {
        String  pullUrl=stringRedisTemplate10.boundHashOps("sgs").get("pullUrl").toString();
        System.out.println(pullUrl);


    }

    @Test
    public void testActivemq(){
        activemqService.sendMsg("123456789");
    }

}
