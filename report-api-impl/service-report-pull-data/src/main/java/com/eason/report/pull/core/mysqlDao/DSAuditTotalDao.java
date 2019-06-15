package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.po.AuditTotalPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface DSAuditTotalDao extends JpaRepository<AuditTotalPo, Long>{

    @Procedure(procedureName = "ds_gf_audit_report")
    String createAuditAndReportForDSGF(Integer siteId, Long startId, Long endId);

    @Procedure(procedureName = "ds_jd_site_pull")
    Integer sitePullForDSJD(Integer siteId,String prex,Long startId,Long endId);

    @Procedure(procedureName = "ds_jd_audit_report")
    String createAuditAndReportForDSJD(Integer siteId, Long startId, Long endId);

    @Procedure(procedureName = "mdt_jd_site_pull")
    Integer sitePullForMdtJD(Integer siteId,String prex,Long startId,Long endId);

    @Procedure(procedureName = "mdt_jd_audit_report")
    String createAuditAndReportForMdtJD(Integer siteId, Long startId, Long endId);

}
