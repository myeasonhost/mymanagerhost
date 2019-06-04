package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.po.AuditTotalPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface MGAuditTotalDao extends JpaRepository<AuditTotalPo, String>{

    @Procedure(procedureName = "ds_mg_site_pull")
    Integer sitePull(Integer siteId, String prex, String startId, String endId);

    @Procedure(procedureName = "ds_mg_audit_report")
    String createAuditAndReport(Integer siteId, String startId, String endId);


}
