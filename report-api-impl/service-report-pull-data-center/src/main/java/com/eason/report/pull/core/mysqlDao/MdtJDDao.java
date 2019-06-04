package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.mysqlDao.po.MdtJingdianLotteryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface MdtJDDao extends JpaRepository<MdtJingdianLotteryPo, Long>{

  @Query(value = "select COALESCE(max(id),0) from mdt_jingdian_lottery",nativeQuery = true)
  Long getMaxId();

  MdtJingdianLotteryPo findByNid(String nid);

  @Procedure(procedureName = "mdt_jd_site_pull")
  Integer sitePull(Integer siteId, String prex, Long startId, Long endId);

  @Procedure(procedureName = "mdt_jd_audit_report")
  String createAuditAndReport(Integer siteId, Long startId, Long endId);
}
