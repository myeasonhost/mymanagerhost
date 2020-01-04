package com.eason.transfer.openapi.zb.api.zhubo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RZhubo extends RUser {

    private long fansNum;
    private Boolean isFans;


}
