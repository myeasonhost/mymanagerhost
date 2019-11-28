package com.eason.transfer.openapi.chess.dao;


import com.eason.transfer.openapi.chess.po.TAuditTotalPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTotalDao extends JpaRepository<TAuditTotalPo, Long>{

    @Procedure(procedureName = "ds_gf_audit_transfer")
    String createAuditAndtransferForDSGF(Integer siteId,String type, Long startId, Long endId);

    @Procedure(procedureName = "ds_jd_audit_transfer")
    String createAuditAndtransferForDSJD(Integer siteId,String type, Long startId, Long endId);

}
