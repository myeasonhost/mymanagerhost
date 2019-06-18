package com.eason.report.pull.core.mysqlDao;


import com.eason.report.pull.core.po.DtJingdianLotteryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DtJDDao extends JpaRepository<DtJingdianLotteryPo, Long>{

  @Query(value = "select COALESCE(max(id),0) from dt_jingdian_lottery",nativeQuery = true)
  Long getMaxId();

  DtJingdianLotteryPo findByNid(String nid);

}
