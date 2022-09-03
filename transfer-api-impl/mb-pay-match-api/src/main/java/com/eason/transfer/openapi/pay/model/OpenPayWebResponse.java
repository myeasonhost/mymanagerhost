package com.eason.transfer.openapi.pay.model;

import com.eason.transfer.openapi.core.common.response.Response;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("OpenPayWebResponse")
public class OpenPayWebResponse extends Response {
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

    /** 金额 */
    private String amount;

    /** 订单币种单位。支持USD */
    private String currency;

    /** 支付金额（订单金额 + 订单币种汇率） */
    private String coinAmount;

    /** 支付币种（USDT） */
    private String coinCode;

    /** 商户配置的接收用户支付的钱包地址 */
    private String coinAddress;

    /** 1=支付中,2=支付成功，3=支付失败，4=支付取消 */
    private String status;

    /** 订单过期时间 */
    private String timeout;

    /** 区块链交易 hash */
    private String hash;

    /** 完成后回调通知地址 */
    private String notifyUrl;

    /** 完成后同步跳转地址 */
    private String redirectUrl;

    /** 官方收银台地址 */
    private String cashierUrl;

    /** 收款码地址 */
    private String qrcodeUrl;

    private String result;
}
