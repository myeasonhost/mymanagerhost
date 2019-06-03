package com.eason.report.pull.ds;

import com.eason.report.pull.ds.api.DSGFPullAPIImpl;
import com.eason.report.pull.ds.api.MGPullAPIImpl;
import com.eason.report.pull.ds.mysqlDao.DtGFDao;
import com.eason.report.pull.ds.mysqlDao.MdtJDDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {
    @Autowired
    private DSGFPullAPIImpl gfPullAPIImpl;
//    @Autowired
//    private MdtJDPullAPIImpl jdPullAPIImpl;
    @Autowired
    private MGPullAPIImpl mgPullAPI;

    @Test
    public void contextLoads() throws Exception {
//        gfPullAPIImpl.getPullBet(31301526L,100);
//        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet(659712L,1000); //659712
//         jdPullAPIImpl.getPullBet();
        mgPullAPI.getPullBet("2019-05-27 00:21:10",30);
    }

    @Test
    public void testDao() throws Exception {
//        dtGFDao.sitePull(1020,"888821_TYZ",34369023L,35182641L);

//        String result=mdtJDDao.createAuditAndReport(1020,659712L,660711L);
//        System.out.println(result);

        Reflections reflections=new Reflections(".");
        System.out.println(reflections);

}

}
