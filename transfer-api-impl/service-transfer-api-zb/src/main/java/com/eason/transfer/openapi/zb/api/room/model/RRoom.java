package com.eason.transfer.openapi.zb.api.room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@REntity
public class RRoom implements Serializable {
    @RId
    private String id;

    private String zbSeqNo;
    @RIndex
    private int status;

    private String roomName;

    private String roomBgImage;

    private String imUrl;

    private String liveUrl;

    private String username;

    private String nickName;

    private String avatar;

    private String sessionId;

    private long fansNum;

    private Boolean isFans;

    private Timestamp startTime;

}
