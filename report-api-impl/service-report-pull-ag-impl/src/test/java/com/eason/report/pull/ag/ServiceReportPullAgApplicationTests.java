package com.eason.report.pull.ag;

import com.eason.report.pull.ag.api.CommonAPIImpl;
import com.eason.report.pull.ag.vo.common.SumordersVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullAgApplicationTests {
    @Autowired
    private CommonAPIImpl commonAPI;

    @Autowired
    private RedisTemplate redisTemplate10;
    @Autowired
    private StringRedisTemplate stringRedisTemplate10;

    @Test
    public void contextLoads() {
        //"enddate=2019-05-09 00:10:00&cagent=CS2&startdate=2019-05-09 00:00:00" +
        //            "&key=208c5d78b7b95fe29f401e270de22c98
        SumordersVo vo=new SumordersVo();
        vo.setCAgent("CS2");
        vo.setStartDate("2019-05-09 00:00:00");
        vo.setEndDate("2019-05-09 00:10:00");
        commonAPI.getsumorders(vo);

    }

    @Test
    public void testRedis() {
        String  pullUrl=stringRedisTemplate10.boundHashOps("ag").get("pullUrl").toString();
        System.out.println(pullUrl);


    }

}
