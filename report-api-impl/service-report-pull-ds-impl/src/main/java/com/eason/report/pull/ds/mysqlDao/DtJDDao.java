package com.eason.report.pull.ds.mysqlDao;


import com.eason.report.pull.ds.po.DtJingdianLotteryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DtJDDao extends JpaRepository<DtJingdianLotteryPo, String>{

  @Query(value = "select COALESCE(max(id),0) from dt_jingdian_lottery",nativeQuery = true)
  Long getMaxId();

  DtJingdianLotteryPo findByNid(String nid);
}
