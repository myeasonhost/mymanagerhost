package com.eason.transfer.openapi.core.client.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("PayNotifyResponse")
public class PayNotifyResponse extends Response {
    private String result;	//返回信息
}
