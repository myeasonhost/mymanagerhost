package com.eason.transfer.openapi.core.sdk.zb.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("BannerResponseVo")
public class BannerResponseVo extends Response {
    private Integer id; 	//BannerID
    private String title; 	//Banner标题
    private String thumb_img_url; 	//Banner图片地址
    private String url; 	//跳转地址
    private Integer type;  //Banner类型（1=最新、3=最热）
}
