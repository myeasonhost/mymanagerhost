package com.eason.report.pull.platform.lmg.dao;


import com.eason.report.pull.platform.lmg.dao.po.DsLmgGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LMGConfigDao extends JpaRepository<DsLmgGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ds_lmg_game_config t WHERE t.state=50",nativeQuery = true)
    List<DsLmgGameConfigPo> findConfig();

}
