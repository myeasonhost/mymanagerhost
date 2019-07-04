package com.eason.report.pull.platform.ag.model.xin;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("row")
public class XinModel {
    @XStreamAsAttribute
    @XStreamAlias("billno")
    private String billNo;
    @XStreamAsAttribute
    @XStreamAlias("productid")
    private String agentId;
    @XStreamAsAttribute
    @XStreamAlias("username")
    private String username;
    @XStreamAsAttribute
    @XStreamAlias("playName")
    private String playName;
    @XStreamAsAttribute
    @XStreamAlias("billtime")
    private Date billTime;
    @XStreamAsAttribute
    @XStreamAlias("reckontime")
    private Date reckonTime;
    @XStreamAsAttribute
    @XStreamAlias("slottype")
    private String slotType;
    @XStreamAsAttribute
    @XStreamAlias("currency")
    private String currency;
    @XStreamAsAttribute
    @XStreamAlias("gametype")
    private String gameType;
    @XStreamAsAttribute
    @XStreamAlias("betIP")
    private String betIp;
    @XStreamAsAttribute
    @XStreamAlias("account")
    private BigDecimal account;
    @XStreamAsAttribute
    @XStreamAlias("cus_account")
    private BigDecimal cusAccount;
    @XStreamAsAttribute
    @XStreamAlias("valid_account")
    private BigDecimal validAccount;
    @XStreamAsAttribute
    @XStreamAlias("account_base")
    private BigDecimal accountBase;
    @XStreamAsAttribute
    @XStreamAlias("account_bonus")
    private BigDecimal accountBonus;
    @XStreamAsAttribute
    @XStreamAlias("cus_account_base")
    private BigDecimal cusAccountBase;
    @XStreamAsAttribute
    @XStreamAlias("cus_account_bonus")
    private BigDecimal cusAccountBonus;
    @XStreamAsAttribute
    @XStreamAlias("src_amount")
    private BigDecimal srcAmount;
    @XStreamAsAttribute
    @XStreamAlias("dst_amount")
    private BigDecimal dstAmount;
    @XStreamAsAttribute
    @XStreamAlias("winLossType")
    private Byte winLossType;
    @XStreamAsAttribute
    @XStreamAlias("flag")
    private String flag;
    @XStreamAsAttribute
    @XStreamAlias("devicetype")
    private String deviceType;
    @XStreamAsAttribute
    @XStreamAlias("exttxid")
    private String exttxid;
    @XStreamAsAttribute
    @XStreamAlias("platformtype")
    private String platformType;
    @XStreamAsAttribute
    @XStreamAlias("createTime")
    private Date createTime;
    @XStreamAsAttribute
    @XStreamAlias("updateTime")
    private Date updateTime;

}
