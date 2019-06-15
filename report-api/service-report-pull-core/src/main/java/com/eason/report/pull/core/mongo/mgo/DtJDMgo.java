package com.eason.report.pull.core.mongo.mgo;


import com.eason.report.pull.core.mongo.po.DtJDMgoPo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DtJDMgo extends MongoRepository<DtJDMgoPo, BigInteger>, PagingAndSortingRepository<DtJDMgoPo, BigInteger>{
////    @Query("{'userId':{$in:?#{[0]}}}")
////    Page<AgModel> findByUserIds(List<Integer> userIds, Pageable pageable);
////    @Query("{'roomType': {$in:?#{[0]}}}")
////    Page<AgModel> findByChargedRoom(List<String> roomTypes, Pageable pageable);
////    @Query("{'roomType': ?#{[0]}}")
////    Page<AgModel> findByRoomType(String roomType, Pageable pageable);
////    @Query("{'roomId': ?#{[0]}}")
////    AgModel findByRoomId(Integer roomId);
////    @Query("{'zbId': ?#{[0]}}")
////    AgModel findByZbId(Integer zbId);
        @Query("{'siteId':?#{[0]},'id':{'$gte':?#{[1]},'$lte':?#{[2]}}}")
        DtJDMgoPo findAllBySiteId(Integer siteId, Long startId, Long endId);
}
