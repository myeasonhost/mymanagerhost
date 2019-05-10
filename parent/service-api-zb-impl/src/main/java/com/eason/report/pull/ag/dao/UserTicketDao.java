package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.cache.ZbTUserTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;

public interface UserTicketDao extends MongoRepository<ZbTUserTicket, BigInteger> {
    @Query("{'planId':?#{[0]},'userId':?#{[1]}}")
    ZbTUserTicket findByPlanIdAndUserId(Integer planId,Integer userId);
}
