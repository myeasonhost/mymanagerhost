package com.eason.report.pull.platform.ag.model.yoplay;

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
public class YoplayModel {
    @XStreamAsAttribute
    @XStreamAlias("billNo")
    private String billNo;
    @XStreamAsAttribute
    @XStreamAlias("agentId")
    private String agentId;
    @XStreamAsAttribute
    @XStreamAlias("username")
    private String username;
    @XStreamAsAttribute
    @XStreamAlias("playName")
    private String playName;
    @XStreamAsAttribute
    @XStreamAlias("billTime")
    private Date billTime;
    @XStreamAsAttribute
    @XStreamAlias("reckonTime")
    private Date reckonTime;
    @XStreamAsAttribute
    @XStreamAlias("slotType")
    private String slotType;
    @XStreamAsAttribute
    @XStreamAlias("currency")
    private String currency;
    @XStreamAsAttribute
    @XStreamAlias("gameType")
    private String gameType;
    @XStreamAsAttribute
    @XStreamAlias("betIp")
    private String betIp;
    @XStreamAsAttribute
    @XStreamAlias("account")
    private BigDecimal account;
    @XStreamAsAttribute
    @XStreamAlias("cusAccount")
    private BigDecimal cusAccount;
    @XStreamAsAttribute
    @XStreamAlias("validAccount")
    private BigDecimal validAccount;
    @XStreamAsAttribute
    @XStreamAlias("accountBase")
    private BigDecimal accountBase;
    @XStreamAsAttribute
    @XStreamAlias("accountBonus")
    private BigDecimal accountBonus;
    @XStreamAsAttribute
    @XStreamAlias("cusAccountBase")
    private BigDecimal cusAccountBase;
    @XStreamAsAttribute
    @XStreamAlias("cusAccountBonus")
    private BigDecimal cusAccountBonus;
    @XStreamAsAttribute
    @XStreamAlias("srcAmount")
    private BigDecimal srcAmount;
    @XStreamAsAttribute
    @XStreamAlias("dstAmount")
    private BigDecimal dstAmount;
    @XStreamAsAttribute
    @XStreamAlias("gameCode")
    private String gameCode;
    @XStreamAsAttribute
    @XStreamAlias("winLossType")
    private Byte winLossType;
    @XStreamAsAttribute
    @XStreamAlias("flag")
    private String flag;
    @XStreamAsAttribute
    @XStreamAlias("platformType")
    private String platformType;
    @XStreamAsAttribute
    @XStreamAlias("remark")
    private String remark;
    @XStreamAsAttribute
    @XStreamAlias("createTime")
    private Date createTime;
    @XStreamAsAttribute
    @XStreamAlias("updateTime")
    private Date updateTime;

}
