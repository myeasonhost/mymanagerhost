package com.ruoyi.pay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.ruoyi.common.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 充值订单对象 mb_pay_order
 *
 * @author doctor
 * @date 2022-09-05
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
    @Excel(name = "商户ID")
    private String siteId;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String orderId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 支付匹配ID */
    @Excel(name = "支付匹配ID")
    private String matchId;

    /** 产品名 */
    @Excel(name = "产品名")
    private String productName;

    /** 金额 */
    @Excel(name = "金额")
    private String amount;

    /** 货币汇率 */
    @Excel(name = "货币汇率")
    private String currency;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private String coinAmount;

    /** 支付币种USDT/RMB */
    @Excel(name = "支付币种USDT/RMB")
    private String coinCode;

    /** 0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功 */
    @Excel(name = "0=下单成功，1=支付中,2=玩家确认支付，3=玩家支付取消，4=支付超时，5=支付审核，6=支付成功")
    private String status;

    /** 0=未通知，1=通知成功，2=通知失败 */
    @Excel(name = "0=未通知，1=通知成功，2=通知失败")
    private String notifySucceed;

    /** 通知次数 */
    @Excel(name = "通知次数")
    private Long notifyTimes;

    /** 最后通知时间 */
    @Excel(name = "最后通知时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastNotifyTime;

    /** 下一次通知时间 */
    @Excel(name = "下一次通知时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextNotifyTime;

    /** 订单过期时间（分钟） */
    @Excel(name = "订单过期时间" , readConverterExp = "分=钟")
    private String timeout;

    /** 支付时间 */
    @Excel(name = "支付时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /** 转账时间 */
    @Excel(name = "转账时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferTime;

    /** 审核时间 */
    @Excel(name = "审核时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    /** 过期时间 */
    @Excel(name = "过期时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeoutTime;

    /** 取消时间 */
    @Excel(name = "取消时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

    /** 支付完成时间 */
    @Excel(name = "支付完成时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /** 完成后回调通知地址 */
    @Excel(name = "完成后回调通知地址")
    private String notifyUrl;

    /** 完成后同步跳转地址 */
    @Excel(name = "完成后同步跳转地址")
    private String redirectUrl;

    /** 官方收银台地址 */
    @Excel(name = "官方收银台地址")
    private String cashierUrl;

    /** 收款码地址 */
    @Excel(name = "收款码地址")
    private String qrcodeUrl;

    /** 收款凭证 */
    @Excel(name = "收款凭证")
    private String payimageUrl;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** $column.columnComment */
    private Date createTime;

    /** $column.columnComment */
    private Date updateTime;

    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();
}
