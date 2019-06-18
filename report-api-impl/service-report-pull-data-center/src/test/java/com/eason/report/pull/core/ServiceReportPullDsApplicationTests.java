package com.eason.report.pull.core;

import com.eason.report.pull.core.api.MDTJDPullAPIImpl;
import com.eason.report.pull.core.api.MGPullAPIImpl;
import com.eason.report.pull.core.manager.MGMgr;
import com.eason.report.pull.core.manager.MdtJDMgr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {
//    @Autowired
//    private DSGFPullAPIService dsGFPullAPIService;
//    @Autowired
//    private MdtJDPullAPIImpl jdPullAPIImpl;
    @Autowired
    private MGMgr mgMgr;
    @Autowired
    private MGPullAPIImpl pullAPIImpl;
    @Test
    public void contextLoads() throws Exception {
//        dsGFPullAPIService.getPullBet();
//        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet(659712L,1000); //659712
//         jdPullAPIImpl.getPullBet();
//        mgPullAPI.getPullBet("2019-05-27 00:21:10",30);
        pullAPIImpl.getPullBet("2019-06-16 00:21:10",5,mgMgr.loadConfig().get(0));
    }


}
