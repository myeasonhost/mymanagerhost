package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.request.Request;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("PayNotifyRequest")
public class PayNotifyRequest extends Request {
    private Long notifyId;
    private String siteId ;
    private String userId ;
    private String orderId;
    private String amount;
    private String finishTime;

}
