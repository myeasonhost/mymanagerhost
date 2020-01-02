package com.eason.transfer.openapi.core.sdk.user.model;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("GiftRequest")
public class GiftRequest extends Request {
    private String giftName;     //礼物名称
    private String giftImg;       //礼物图片
    private BigDecimal giftPrice;     //礼物价格
    private String specialStyle;//特效方式
}
