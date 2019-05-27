package com.eason.report.pull.ds;

import com.eason.report.pull.ds.api.GFPullAPIImpl;
import com.eason.report.pull.ds.api.JDPullAPIImpl;
import com.eason.report.pull.ds.model.MsgModel;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
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
    @Autowired
    private DtGFDao dtGFDao;

    @Test
    public void contextLoads() throws Exception {
        gfPullAPIImpl.getPullBet(19368357L,10);
//        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet(1479324805L,10);
    }

    @Test
    public void testDao() throws Exception {
        dtGFDao.sitePull(1020,"888821_TYZ",34369023L,35182641L);

        String result=dtGFDao.createAuditAndReport(1020,34369023L,35182641L);
        System.out.println(result);
    }

}
