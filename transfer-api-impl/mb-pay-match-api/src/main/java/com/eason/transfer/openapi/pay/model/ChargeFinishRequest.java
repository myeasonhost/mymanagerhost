package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ChargeFinishRequest")
public class ChargeFinishRequest extends Request {
    private String orderId ;
    private String chargeMatchId ;

}
