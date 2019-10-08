package com.eason.transfer.openapi.user.dao;


import com.eason.transfer.openapi.user.po.AuditTotalPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTotalDao extends JpaRepository<AuditTotalPo, Long>{

    @Procedure(procedureName = "ds_gf_audit_transfer")
    String createAuditAndtransferForDSGF(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_jd_audit_transfer")
    String createAuditAndtransferForDSJD(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "mdt_jd_audit_transfer")
    String createAuditAndtransferForMdtJD(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_mg_audit_transfer")
    String createAuditAndtransferForMG(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ky_audit_transfer")
    String createAuditAndtransferForKY(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_lmg_audit_transfer")
    String createAuditAndtransferForDSLMG(Integer siteId, String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_pt_audit_transfer")
    String createAuditAndtransferForDSPT(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_sgs_audit_transfer")
    String createAuditAndtransferForDSSGS(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_bbin_audit_transfer")
    String createAuditAndtransferForDSBBIN(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_agin_audit_transfer")
    String createAuditAndtransferForDSAGAgin(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_yoplay_audit_transfer")
    String createAuditAndtransferForDSAGYoplay(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_xin_audit_transfer")
    String createAuditAndtransferForDSAGXin(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_sport_audit_transfer")
    String createAuditAndtransferForDSAGSport(Integer siteId, String type, String startId, String endId);

    @Procedure(procedureName = "ds_ag_hunter_audit_transfer")
    String createAuditAndtransferForDSAGHunter(Integer siteId, String type, String startId, String endId);

}
