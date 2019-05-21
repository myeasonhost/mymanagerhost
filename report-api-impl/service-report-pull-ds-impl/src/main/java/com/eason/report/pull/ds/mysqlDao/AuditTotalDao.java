package com.eason.report.pull.ds.mysqlDao;


import com.eason.report.pull.ds.po.AuditTotalPo;
import com.eason.report.pull.ds.po.DtGuangfangLotteryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTotalDao extends JpaRepository<AuditTotalPo, String>{


}
