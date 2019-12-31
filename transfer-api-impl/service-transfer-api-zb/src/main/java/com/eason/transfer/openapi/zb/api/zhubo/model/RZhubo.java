package com.eason.transfer.openapi.zb.api.zhubo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@REntity
public class RZhubo extends RUser{

    private Integer fansNum;

    private Boolean isFans;


}