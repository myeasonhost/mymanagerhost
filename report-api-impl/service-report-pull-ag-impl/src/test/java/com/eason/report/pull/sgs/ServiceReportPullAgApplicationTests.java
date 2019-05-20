package com.eason.report.pull.sgs;

import com.eason.report.pull.sgs.activemqDemo.ActivemqService;
import com.eason.report.pull.ag.api.CommonAPIImpl;
import com.eason.report.pull.sgs.vo.common.SumOrdersVo;
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

    @Autowired
    private ActivemqService activemqService;


    @Test
    public void contextLoads() throws Exception {
        //"enddate=2019-05-09 00:10:00&cagent=CS2&startdate=2019-05-09 00:00:00" +
        //            "&key=208c5d78b7b95fe29f401e270de22c98
        SumOrdersVo vo=new SumOrdersVo();
        vo.setCAgent("CS2");
        vo.setStartDate("2019-05-09 00:00:00");
        vo.setEndDate("2019-05-09 00:10:00");
        commonAPI.getSumOrders(vo);

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
