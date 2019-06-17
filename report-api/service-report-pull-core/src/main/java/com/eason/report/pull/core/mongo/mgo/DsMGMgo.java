package com.eason.report.pull.core.mongo.mgo;


import com.eason.report.pull.core.mongo.po.MGMgoPo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DsMGMgo extends MongoRepository<MGMgoPo, BigInteger>, PagingAndSortingRepository<MGMgoPo, BigInteger>{
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
        @Query("{'siteId':?#{[0]},'transTime':{'$gte':?#{[1]},'$lte':?#{[2]}}}")
        MGMgoPo findAllBySiteId(Integer siteId, String startTime, String endTime);

}