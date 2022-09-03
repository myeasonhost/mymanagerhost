package com.eason.transfer.openapi.pay.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 【请填写功能名称】对象 mb_pay_order
 *
 * @author doctor
 * @date 2022-09-03
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("mb_pay_order")
public class MbPayOrder implements Serializable {

    private static final long serialVersionUID=1L;


    /** 支付订单号 */
    @TableId(value = "id")
    private Long id;

    /** 商户ID */
    private String siteId;

    /** 商户订单号 */
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

    private Date payTime;

    /** 转账时间 */
    private Date transferTime;

    private Date applyTime;

    private Date timeoutTime;

    private Date cancelTime;

    private Date finishTime;

    /** 完成后回调通知地址 */
    private String notifyUrl;

    /** 完成后同步跳转地址 */
    private String redirectUrl;

    /** 官方收银台地址 */
    private String cashierUrl;

    /** 收款码地址 */
    private String qrcodeUrl;

    /** 支付凭证 */
    private String payimageUrl;

    /** 备注 */
    private String remark;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}
