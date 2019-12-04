package com.eason.transfer.openapi.core.sdk.index.user.model;


import com.eason.transfer.openapi.core.common.request.Request;
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
