package com.eason.transfer.openapi.core.client.vo;

import com.eason.transfer.openapi.core.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransMoneyResponse extends Response {
    private String result;	//返回信息
}
