package com.eason.report.pull.core.dao;


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
    String createAuditAndReportForMG(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ky_audit_report")
    String createAuditAndReportForKY(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_lmg_audit_report")
    String createAuditAndReportForDSLMG(Integer siteId, String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_pt_audit_report")
    String createAuditAndReportForDSPT(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_sgs_audit_report")
    String createAuditAndReportForDSSGS(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_bbin_audit_report")
    String createAuditAndReportForDSBBIN(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_agin_audit_report")
    String createAuditAndReportForDSAGAgin(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_yoplay_audit_report")
    String createAuditAndReportForDSAGYoplay(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_xin_audit_report")
    String createAuditAndReportForDSAGXin(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_sport_audit_report")
    String createAuditAndReportForDSAGSport(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_hunter_audit_report")
    String createAuditAndReportForDSAGHunter(Integer siteId, String type, String startId, String endId);

}
