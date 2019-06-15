package com.eason.report.pull.core.mysqlDao.dao;


import com.eason.report.pull.core.mysqlDao.config.MdtLotteryConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MdtLotteryConfigDao extends JpaRepository<MdtLotteryConfigPo, Integer>{

    @Query(value = "SELECT * FROM mdt_lottery_config t WHERE t.code='MDT-GF' AND t.state=50",nativeQuery = true)
    List<MdtLotteryConfigPo> findDsDfConfig();

    @Query(value = "SELECT * FROM mdt_lottery_config t WHERE t.code='MDT-JD' AND t.state=50",nativeQuery = true)
    List<MdtLotteryConfigPo> findJdConfig();

}
