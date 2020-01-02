package com.eason.transfer.openapi.core.sdk.zb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelationFriendsVo {
    private Integer id;
    private Integer UserId;
    private Date relationTime;
    private Integer relationUserId;
}
