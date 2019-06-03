package com.eason.report.pull.mg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullMgApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate10;
    @Autowired
    private StringRedisTemplate stringRedisTemplate10;


    @Test
    public void contextLoads() throws Exception {
//        mgPullAPIImpl.getPullBet("2019:05:27:00:21:10",30);
//        mgPullAPIImpl.getPullBet();
    }

    @Test
    public void testRedis() throws Exception{
//        stringRedisTemplate10.boundHashOps("mg_pull_config").put("endTime", new Date());
//        dtMGMgr.viewHorTxCall("twderw92d0y9tuuecg541h7egkshpjnejd1bf460d-c1a2-4053-87bc-b882172c4dbf",
//                "2019-05-27 01:51:10","2019-05-27 02:21:10");

//        System.out.println(DateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1558887864371L)));
    }

}
