package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.cache.ZbTRoomCron;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface RoomCronDao extends MongoRepository<ZbTRoomCron, BigInteger> {

}
