package com.eason.report.pull.core;

import com.eason.report.pull.core.mongo.po.DtGFMgoPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceReportPullMgApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() throws Exception {
        mongoTemplate.findAllAndRemove(query(where("id")
                .gte(39178821).lte(39208545)),DtGFMgoPo.class);
    }

    @Test
    public void testRedis() throws Exception{

    }

}
