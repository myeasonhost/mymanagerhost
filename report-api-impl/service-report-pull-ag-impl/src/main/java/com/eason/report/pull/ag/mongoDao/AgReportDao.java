package com.eason.report.pull.ag.mongoDao;

import com.eason.report.pull.ag.util.AgModel;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigInteger;
import java.util.List;
@Repository
public interface AgReportDao extends MongoRepository<AgModel, BigInteger>, PagingAndSortingRepository<AgModel, BigInteger> {
    @Query("{'userId':{$in:?#{[0]}}}")
    Page<AgModel> findByUserIds(List<Integer> userIds, Pageable pageable);
    @Query("{'roomType': {$in:?#{[0]}}}")
    Page<AgModel> findByChargedRoom(List<String> roomTypes, Pageable pageable);
    @Query("{'roomType': ?#{[0]}}")
    Page<AgModel> findByRoomType(String roomType, Pageable pageable);
    @Query("{'roomId': ?#{[0]}}")
    AgModel findByRoomId(Integer roomId);
    @Query("{'zbId': ?#{[0]}}")
    AgModel findByZbId(Integer zbId);
}
