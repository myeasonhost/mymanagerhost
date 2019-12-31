package com.eason.transfer.openapi.core.sdk.zb.model;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("Bannerrequest")
public class Bannerrequest extends Request {
    private static final long serialVersionUID = 1L;
    String category; //房间分类：1=最热（默认）、2=收藏、3=最新、4=付费、5=游戏</br>
}
