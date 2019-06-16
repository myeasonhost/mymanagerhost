package com.eason.report.pull.core.mysqlDao.dao;


import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DtLotteryConfigDao extends JpaRepository<DtLotteryConfigPo, Integer>{

    @Query(value = "SELECT * FROM dt_lottery_config t WHERE t.code='DSGF' AND t.state=50",nativeQuery = true)
    List<DtLotteryConfigPo> findDsDfConfig();

    @Query(value = "SELECT * FROM dt_lottery_config t WHERE t.code='DSJD' AND t.state=50",nativeQuery = true)
    List<DtLotteryConfigPo> findDsJdConfig();

}
