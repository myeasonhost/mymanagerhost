package com.eason.report.pull.mg;

import com.eason.report.pull.sgs.activemqDemo.ActivemqService;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullMgApplicationTests {
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
        stringRedisTemplate10.boundHashOps("mg_pull_config").put("endTime", new Date());

    }

    @Test
    public void testActivemq(){
        activemqService.sendMsg("123456789");
    }

}
