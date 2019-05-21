package com.eason.report.pull.ds;

import com.eason.report.pull.ds.api.GFPullAPIImpl;
import com.eason.report.pull.ds.api.JDPullAPIImpl;
import com.eason.report.pull.ds.model.MsgModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {
    @Autowired
    private GFPullAPIImpl gfPullAPIImpl;
    @Autowired
    private JDPullAPIImpl jdPullAPIImpl;


    @Test
    public void contextLoads() throws Exception {
        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet();

    }

}
