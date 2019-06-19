package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.po.AuditTotalPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTotalDao extends JpaRepository<AuditTotalPo, Long>{

    @Procedure(procedureName = "ds_gf_audit_report")
    String createAuditAndReportForDSGF(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_jd_audit_report")
    String createAuditAndReportForDSJD(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "mdt_jd_audit_report")
    String createAuditAndReportForMdtJD(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_mg_audit_report")
    String createAuditAndReportForMG(Integer siteId,String type, String startId, String endId);

}
