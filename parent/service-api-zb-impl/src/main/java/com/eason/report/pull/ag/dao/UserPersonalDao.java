package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.cache.ZbTUserPersonal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface UserPersonalDao extends MongoRepository<ZbTUserPersonal, BigInteger> {
    @Query("{'userId':?#{[0]},'zbId':?#{[1]}}")
    ZbTUserPersonal findByUserIdAndZbId(Integer userId, Integer zbId);

    List<ZbTUserPersonal> findByZbId(Integer zbId);
}
