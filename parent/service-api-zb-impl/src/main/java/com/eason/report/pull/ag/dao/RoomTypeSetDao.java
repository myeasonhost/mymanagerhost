package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.po.ZbTRoomTypeSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeSetDao extends JpaRepository<ZbTRoomTypeSet, Integer> {

    ZbTRoomTypeSet findByRoomType(String roomType);
}
