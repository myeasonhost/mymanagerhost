package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ChargeFinishResponse")
public class ChargeFinishResponse extends Response {
    /** 支付订单号 */
    private Long id;

    /** 商户ID */
    private String siteId;

    /** 订单ID */
    private String orderId;

    /** 用户名（用户ID） */
    private String userId;

    /** 产品名 */
    private String productName;

    private String status;

    /** 订单过期时间 */
    private String timeout;

    private String result;

}
