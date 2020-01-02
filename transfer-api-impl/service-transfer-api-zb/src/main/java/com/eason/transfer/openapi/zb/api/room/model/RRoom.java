package com.eason.transfer.openapi.zb.api.room.model;

import com.eason.transfer.openapi.zb.api.zhubo.model.RUser;
import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RCascadeType;
import org.redisson.api.annotation.RCascade;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

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

    @RCascade({RCascadeType.ALL})
    private RZhubo rZhubo;

    @RCascade({RCascadeType.ALL})
    private List<RUser> userList;

    private long viewCount;

    private long newFans;

    private long giftCount;

    private Timestamp startTime;

}
