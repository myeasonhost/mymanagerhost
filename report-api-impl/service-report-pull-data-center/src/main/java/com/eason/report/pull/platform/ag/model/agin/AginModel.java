package com.eason.report.pull.platform.ag.model.agin;

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
public class AginModel {
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
    @XStreamAlias("gameCode")
    private String gameCode;
    @XStreamAsAttribute
    @XStreamAlias("netAmount")
    private BigDecimal netAmount;
    @XStreamAsAttribute
    @XStreamAlias("winLossType")
    private Byte winLossType;
    @XStreamAsAttribute
    @XStreamAlias("betTime")
    private Date betTime;
    @XStreamAsAttribute
    @XStreamAlias("gameType")
    private String gameType;
    @XStreamAsAttribute
    @XStreamAlias("betAmount")
    private BigDecimal betAmount;
    @XStreamAsAttribute
    @XStreamAlias("validBetAmount")
    private BigDecimal validBetAmount;
    @XStreamAsAttribute
    @XStreamAlias("flag")
    private String flag;
    @XStreamAsAttribute
    @XStreamAlias("playType")
    private String playType;
    @XStreamAsAttribute
    @XStreamAlias("currency")
    private String currency;
    @XStreamAsAttribute
    @XStreamAlias("tableCode")
    private String tableCode;
    @XStreamAsAttribute
    @XStreamAlias("recalcuTime")
    private Date recalcuTime;
    @XStreamAsAttribute
    @XStreamAlias("beforeCredit")
    private BigDecimal beforeCredit;
    @XStreamAsAttribute
    @XStreamAlias("betIP")
    private String betIp;
    @XStreamAsAttribute
    @XStreamAlias("platformType")
    private String platformType;
    @XStreamAsAttribute
    @XStreamAlias("deviceType")
    private String deviceType;
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
