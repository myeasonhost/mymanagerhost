package com.eason.transfer.openapi.zb.api.zhubo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@REntity
public class RUser implements Serializable {
    @RId
    private String id;
    private String username;
    private String nickName;
    private String avatar;
}
