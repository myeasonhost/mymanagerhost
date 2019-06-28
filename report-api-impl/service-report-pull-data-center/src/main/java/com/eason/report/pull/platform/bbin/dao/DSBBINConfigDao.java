package com.eason.report.pull.platform.bbin.dao;


import com.eason.report.pull.platform.bbin.dao.po.DsBbinGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DSBBINConfigDao extends JpaRepository<DsBbinGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ds_bbin_game_config t WHERE t.state=50",nativeQuery = true)
    List<DsBbinGameConfigPo> findConfig();

}
