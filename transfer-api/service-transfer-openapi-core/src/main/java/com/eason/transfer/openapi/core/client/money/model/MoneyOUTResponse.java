package com.eason.transfer.openapi.core.client.money.model;

import com.eason.transfer.openapi.core.api.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("moneyOUTResponse")
public class MoneyOUTResponse extends Response {

    private static final long serialVersionUID = 1L;

    private String message;
    private String status;

}
