package com.eason.report.pull.platform.ag.model.sport;

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
public class SportModel {
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
    @XStreamAlias("flag")
    private String flag;
    @XStreamAsAttribute
    @XStreamAlias("odds")
    private String odds;
    @XStreamAsAttribute
    @XStreamAlias("competition")
    private String competition;
    @XStreamAsAttribute
    @XStreamAlias("market")
    private String market;
    @XStreamAsAttribute
    @XStreamAlias("selection")
    private String selection;
    @XStreamAsAttribute
    @XStreamAlias("simplified_result")
    private String simplifiedResult;
    @XStreamAsAttribute
    @XStreamAlias("sport")
    private String sport;
    @XStreamAsAttribute
    @XStreamAlias("category")
    private String category;
    @XStreamAsAttribute
    @XStreamAlias("extbillno")
    private String extbillno;
    @XStreamAsAttribute
    @XStreamAlias("thirdbillno")
    private String thirdbillno;
    @XStreamAsAttribute
    @XStreamAlias("bettype")
    private String bettype;
    @XStreamAsAttribute
    @XStreamAlias("system")
    private String system;
    @XStreamAsAttribute
    @XStreamAlias("live")
    private String live;
    @XStreamAsAttribute
    @XStreamAlias("current_score")
    private String currentScore;
    @XStreamAsAttribute
    @XStreamAlias("winLossType")
    private Byte winLossType;
    @XStreamAsAttribute
    @XStreamAlias("createTime")
    private Date createTime;
    @XStreamAsAttribute
    @XStreamAlias("updateTime")
    private Date updateTime;

}
