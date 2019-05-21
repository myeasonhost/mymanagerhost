package com.eason.report.pull.ds.mysqlDao;


import com.eason.report.pull.ds.po.DtGuangfangLotteryPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DtGFDao extends JpaRepository<DtGuangfangLotteryPo, String>{

  @Query(value = "select COALESCE(max(id),0) from dt_guangfang_lottery",nativeQuery = true)
  Long getMaxId();

  DtGuangfangLotteryPo findByNid(String nid);

}
