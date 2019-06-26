package com.eason.report.pull.platform.sgs.dao;


import com.eason.report.pull.platform.sgs.dao.po.DsSgsGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DSSGSConfigDao extends JpaRepository<DsSgsGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ds_sgs_game_config t WHERE t.state=50",nativeQuery = true)
    List<DsSgsGameConfigPo> findConfig();

}
