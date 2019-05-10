package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.po.ZbTQvodConfigs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QconfigDao extends JpaRepository<ZbTQvodConfigs, Integer> {
    @Query("select config FROM ZbTQvodConfigs config where config.aliasName=?1")
    ZbTQvodConfigs findByConfig(String aliasName);
}
