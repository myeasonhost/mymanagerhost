package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("ChargePayResponse")
public class ChargePayResponse extends Response {
    /** 支付订单号 */
    private Long id;
    /** 商户ID */
    private String siteId;
    /** 订单ID */
    private String orderId;
    /** 用户名（用户ID） */
    private String userId;
    private Integer chargeMatchId;
    private Integer bankId;
    private String amount;
    private String bankType;
    private String bankName;
    private String bankCard;
    private String userName;
    /** 订单过期时间 */
    private String timeout;
    private Date payTime;
    private String result;

}
