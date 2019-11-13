package com.eason.transfer.openapi.core.client.money.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("moneyBalanceGetResponse")
public class MoneyBalanceGetResponse extends Response {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String money;

}
