package com.eason.report.pull.ds;

import com.eason.report.pull.ds.api.GFPullAPIImpl;
import com.eason.report.pull.ds.api.MdtJDPullAPIImpl;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {
    @Autowired
    private GFPullAPIImpl gfPullAPIImpl;
    @Autowired
    private MdtJDPullAPIImpl jdPullAPIImpl;
    @Autowired
    private DtGFDao dtGFDao;

    @Test
    public void contextLoads() throws Exception {
//        gfPullAPIImpl.getPullBet(19368357L,10);
//        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet(654512L,1000);
         jdPullAPIImpl.getPullBet();
    }

    @Test
    public void testDao() throws Exception {
        dtGFDao.sitePull(1020,"888821_TYZ",34369023L,35182641L);

        String result=dtGFDao.createAuditAndReport(1020,34369023L,35182641L);
        System.out.println(result);
    }

}