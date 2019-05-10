package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.cache.ZbTRoomConf;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface RoomConfDao extends MongoRepository<ZbTRoomConf, BigInteger>{
    ZbTRoomConf findByRoomId(Integer roomId);
    ZbTRoomConf findByZbId(Integer zbId);
}
