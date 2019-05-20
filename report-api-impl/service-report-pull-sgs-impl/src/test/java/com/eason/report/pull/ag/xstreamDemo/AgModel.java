package com.eason.report.pull.ag.xstreamDemo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("row")
public class AgModel {
   /* @XStreamAsAttribute
    @XStreamAlias("info")
    private String info;*/
    @XStreamAsAttribute
    @XStreamAlias("billNo")
    private String billNo;
    @XStreamAsAttribute
    @XStreamAlias("playName")
    private String playName;
    @XStreamAsAttribute
    @XStreamAlias("gameCode")
    private String gameCode;
    @XStreamAsAttribute
    @XStreamAlias("netAmount")
    private String netAmount;
    @XStreamAsAttribute
    @XStreamAlias("betTime")
    private String betTime;
    @XStreamAsAttribute
    @XStreamAlias("betAmount")
    private String betAmount;
    @XStreamAsAttribute
    @XStreamAlias("validBetAmount")
    private String validBetAmount;
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
    private String recalcuTime;

}
