package com.eason.transfer.openapi.core.client.vo;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("UserMoneyResponse")
public class UserMoneyResponse extends Response {
    private BigDecimal  moey;
    private String result;	//返回信息
}
