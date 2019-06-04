package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.mysqlDao.po.AuditTotalPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTotalDao extends JpaRepository<AuditTotalPo, Long>{


}
