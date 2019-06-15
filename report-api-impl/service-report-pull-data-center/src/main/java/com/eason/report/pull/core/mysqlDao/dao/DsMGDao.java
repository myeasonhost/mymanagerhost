package com.eason.report.pull.core.mysqlDao.dao;


import com.eason.report.pull.core.mysqlDao.po.DsMgGamePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface DsMGDao extends JpaRepository<DsMgGamePo, String>{

  @Query(value = "select COALESCE(max(transTime),now()) from ds_mg_game ",nativeQuery = true)
  Timestamp getMaxId();

  DsMgGamePo findByColId(String colid);

  @Procedure(procedureName = "ds_mg_site_pull")
  Integer sitePull(Integer siteId, String prex, String startId, String endId);

  @Procedure(procedureName = "ds_mg_audit_report")
  String createAuditAndReport(Integer siteId, String startId, String endId);

}
