package com.eason.report.pull.core.mongo.mgo;


import com.eason.report.pull.core.config.MGConfigMgoCo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MGConfigMgo extends MongoRepository<MGConfigMgoCo, BigInteger>{
        @Query("{'username':?#{[0]}}")
        List<MGConfigMgoCo> findConfig(String username);
}
