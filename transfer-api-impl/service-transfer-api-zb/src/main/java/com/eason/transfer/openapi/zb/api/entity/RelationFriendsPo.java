package com.eason.transfer.openapi.zb.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationFriendsPo {
    private Integer id;
    private String UserId;
    private Date relationTime;
    private String relationUserId;
}
