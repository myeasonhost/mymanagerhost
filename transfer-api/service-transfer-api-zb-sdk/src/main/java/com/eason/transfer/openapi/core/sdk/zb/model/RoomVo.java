package com.eason.transfer.openapi.core.sdk.zb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("RoomVo")
public class RoomVo implements Serializable {

    private String id;
    private String planSeqNo;
    private String roomName;
    private String roomBgImage;
    private String username;
    private String nickName;
    private String avatar;
    private String startTime;
    private Long viewCount;
    private Long newFans;
    private Long giftCount;
    private Integer userCount;

}
