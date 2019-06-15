package com.eason.report.pull.core.mysqlDao.dao;


import com.eason.report.pull.core.mysqlDao.po.DsGameTypePo;
import com.eason.report.pull.core.mysqlDao.vo.DsGameTypeVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DsGameTypeDao extends JpaRepository<DsGameTypePo, Long>{

    @Query(value = "SELECT p.game_name AS parentName,t.id, t.game_name AS gameName, " +
            "t.out_game_code AS outGameCode, t.parent_id AS parentId, t.fk_live_id AS fkLiveId " +
            " FROM ds_game_type t " +
            "LEFT OUTER JOIN ds_game_type p ON  t.parent_id=p.id WHERE t.parent_id IN (:ids) ORDER BY t.id DESC",nativeQuery = true)
    List<DsGameTypeVo> findByGameTypeList(String ids);
}
