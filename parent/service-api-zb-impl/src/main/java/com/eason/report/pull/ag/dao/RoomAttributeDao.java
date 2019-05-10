package com.eason.report.pull.ag.dao;

import com.eason.report.pull.ag.po.ZbTRoomAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAttributeDao extends JpaRepository<ZbTRoomAttribute, Integer> {
    ZbTRoomAttribute findByAttributeKey(String attributeKey);
}
