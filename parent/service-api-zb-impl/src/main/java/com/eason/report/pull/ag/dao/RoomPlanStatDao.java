package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.po.ZbTRoomPlanStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPlanStatDao extends JpaRepository<ZbTRoomPlanStat, Integer> {
    ZbTRoomPlanStat findByPlanId(Integer planId);
}
