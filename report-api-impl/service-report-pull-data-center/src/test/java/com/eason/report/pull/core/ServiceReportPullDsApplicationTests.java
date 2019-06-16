package com.eason.report.pull.core;

import com.eason.report.pull.core.api.DSJDPullAPIImpl;
import com.eason.report.pull.core.manager.DtJDMgr;
import com.eason.report.pull.core.mongo.mgo.DtGFMgo;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {
//    @Autowired
//    private DSGFPullAPIService dsGFPullAPIService;
//    @Autowired
//    private MdtJDPullAPIImpl jdPullAPIImpl;
    @Autowired
    private DtJDMgr dtJDMgr;
    @Autowired
    private DSJDPullAPIImpl pullAPIImpl;
    @Test
    public void contextLoads() throws Exception {
//        dsGFPullAPIService.getPullBet();
//        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet(659712L,1000); //659712
//         jdPullAPIImpl.getPullBet();
//        mgPullAPI.getPullBet("2019-05-27 00:21:10",30);
        pullAPIImpl.getPullBet(1553360930L,5,dtJDMgr.loadConfig().get(0));
    }

    @Autowired
    private DtGFMgo dtGFMgo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Long getMaxId(){
        TypedAggregation<DtGFMgoPo> agg = newAggregation(DtGFMgoPo.class,
                group().max("$id").as("id")
        );
        AggregationResults<DtGFMgoPo> results = mongoTemplate.aggregate(agg,DtGFMgoPo.class);
        DtGFMgoPo po = results.getUniqueMappedResult();
        return po.getId();
    }

    @Test
    public void testDao() throws Exception {
        System.out.println(getMaxId());
    }

}
