package com.eason.report.pull.ds.mysqlDao;


import com.eason.report.pull.ds.mysqlDao.po.AuditTotalPo;
import com.eason.report.pull.ds.mysqlDao.po.DtGuangfangLotteryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTotalDao extends JpaRepository<AuditTotalPo, Long>{


}
