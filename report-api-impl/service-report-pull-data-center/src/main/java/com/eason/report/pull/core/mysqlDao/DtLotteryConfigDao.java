package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.mysqlDao.config.DtLotteryConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DtLotteryConfigDao extends JpaRepository<DtLotteryConfigPo, Integer>{


}
