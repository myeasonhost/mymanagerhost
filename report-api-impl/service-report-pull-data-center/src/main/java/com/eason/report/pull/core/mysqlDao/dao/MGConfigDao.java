package com.eason.report.pull.core.mysqlDao.dao;


import com.eason.report.pull.core.mysqlDao.config.MgGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MGConfigDao extends JpaRepository<MgGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ds_mg_game_config t WHERE t.state=50",nativeQuery = true)
    List<MgGameConfigPo> findConfig();

}
