package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayNotifyResponse extends Response {
    private String result;	//返回信息
}
