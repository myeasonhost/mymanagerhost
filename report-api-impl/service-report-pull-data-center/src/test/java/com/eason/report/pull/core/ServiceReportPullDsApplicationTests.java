package com.eason.report.pull.core;

import com.eason.report.pull.core.mongo.mgo.DtGFMgo;
import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullDsApplicationTests {
//    @Autowired
//    private DSGFPullAPIService dsGFPullAPIService;
//    @Autowired
//    private MdtJDPullAPIImpl jdPullAPIImpl;
//    @Autowired
//    private MGPullAPIImpl mgPullAPI;

    @Test
    public void contextLoads() throws Exception {
//        dsGFPullAPIService.getPullBet();
//        gfPullAPIImpl.getPullBet();
//        jdPullAPIImpl.getPullBet(659712L,1000); //659712
//         jdPullAPIImpl.getPullBet();
//        mgPullAPI.getPullBet("2019-05-27 00:21:10",30);
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
