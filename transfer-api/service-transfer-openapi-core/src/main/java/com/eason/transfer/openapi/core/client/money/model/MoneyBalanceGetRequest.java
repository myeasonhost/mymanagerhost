package com.eason.transfer.openapi.core.client.money.model;


import com.eason.transfer.openapi.core.api.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyBalanceGetRequest extends Request {

    private static final long serialVersionUID = 1L;

    private String type;
}
