package com.eason.transfer.openapi.zb.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftPo {
    private Integer id;
    private String giftName;     //礼物名称
    private String giftImg;       //礼物图片
    private BigDecimal giftPrice;     //礼物价格
    private String specialStyle;//特效方式
}
