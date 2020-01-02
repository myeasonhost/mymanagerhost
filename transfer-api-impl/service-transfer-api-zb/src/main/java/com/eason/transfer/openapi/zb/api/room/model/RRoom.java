package com.eason.transfer.openapi.zb.api.room.model;

import com.eason.transfer.openapi.zb.api.zhubo.model.RUser;
import com.eason.transfer.openapi.zb.api.zhubo.model.RZhubo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RCascadeType;
import org.redisson.api.RList;
import org.redisson.api.annotation.RCascade;
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
    private Long id;

    private String zbSeqNo;
    @RIndex
    private Integer status;

    private String roomName;

    private String roomBgImage;

    private String imUrl;

    private String liveUrl;

    @RCascade({RCascadeType.PERSIST,RCascadeType.DELETE})
    private RZhubo rZhubo;

    @RCascade({RCascadeType.PERSIST,RCascadeType.DELETE})
    private RList<RUser> userList;

    private RAtomicLong viewCount;

    private RAtomicLong newFans;

    private RAtomicLong giftCount;

    private Timestamp startTime;

}
