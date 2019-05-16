package com.eason.report.pull.ag.vo.sport;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgSportOrdersExRo implements Serializable{
    @XStreamAlias("result")
    private String result;

    class OrderResponseVo{
        @XStreamAlias("info")
        private String info;
        @XStreamAlias("row")
        private String row;
        @XStreamAlias("addition")
        private String addition;

    }
     class Data{

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
}
