package com.eason.transfer.openapi.zb.api.room.model;

import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@REntity
public class RRoom implements Serializable {
    @RId
    private String id;

    private String roomName;

    private String roomBgImage;

    private RZhubo rZhubo;

    private Timestamp startTime;

}
