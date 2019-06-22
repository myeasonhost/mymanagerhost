package com.eason.report.pull.platform.ky.dao;


import com.eason.report.pull.platform.ky.dao.po.KYGameConfigPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KYConfigDao extends JpaRepository<KYGameConfigPo, Integer>{

    @Query(value = "SELECT * FROM ky_game_config t WHERE t.state=50",nativeQuery = true)
    List<KYGameConfigPo> findConfig();

}
