package com.eason.report.pull.platform.pt.dao;


import com.eason.report.pull.platform.pt.dao.po.DsPtGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DSPTConfigDao extends JpaRepository<DsPtGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ds_pt_game_config t WHERE t.state=50",nativeQuery = true)
    List<DsPtGameConfigPo> findConfig();

}
