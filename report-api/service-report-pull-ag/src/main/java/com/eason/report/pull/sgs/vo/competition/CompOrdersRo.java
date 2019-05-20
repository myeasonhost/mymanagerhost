package com.eason.report.pull.sgs.vo.competition;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("result")
public class CompOrdersRo implements Serializable{

    @XStreamAlias("info")
    private String info;
    @XStreamAlias("addition")
    private String addition;
    @XStreamImplicit(itemFieldName="row")
    private List<CompOrdersRoList> CompOrdersRoList;

    @XStreamAlias("row")
    class CompOrdersRoList{

        @XStreamAsAttribute
        @XStreamAlias("billNo")
        private String billNo;
        @XStreamAsAttribute
        @XStreamAlias("cagent")
        private String cAgent;
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
        @XStreamAlias("recalcuTime")
        private String recalcuTime;
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
        @XStreamAlias("compType")
        private String compType;
        @XStreamAsAttribute
        @XStreamAlias("sessionId")
        private String sessionId;
        @XStreamAsAttribute
        @XStreamAlias("vid")
        private String vid;
        @XStreamAsAttribute
        @XStreamAlias("remark")
        private String remark;
        @XStreamAsAttribute
        @XStreamAlias("deviceType")
        private String deviceType;

    }
}
