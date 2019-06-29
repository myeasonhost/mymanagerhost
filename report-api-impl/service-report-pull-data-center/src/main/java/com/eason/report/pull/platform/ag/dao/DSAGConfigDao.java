package com.eason.report.pull.platform.ag.dao;


import com.eason.report.pull.platform.ag.po.DsAGGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DSAGConfigDao extends JpaRepository<DsAGGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ds_ag_game_config t WHERE t.state=50 and t.code=:code",nativeQuery = true)
    List<DsAGGameConfigPo> findConfig(String code);

}
