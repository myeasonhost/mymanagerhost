package com.eason.report.pull.platform.ag.model;

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
    private String billNo;
    private String agentId;
    private String username;
    private String playerName;
    private String gameCode;
    private BigDecimal netAmount;
    private Byte winLossType;
    private Date betTime;
    private String gameType;
    private BigDecimal betAmount;
    private BigDecimal validBetAmount;
    private String flag;
    private String playType;
    private String currency;
    private String tableCode;
    private Date recalcuTime;
    private BigDecimal beforeCredit;
    private String betIp;
    private String platformType;
    private String deviceType;
    private String remark;
    private Date createTime;
    private Date updateTime;

}
