package com.eason.report.pull.sgs.vo.common;

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
public class SumOrdersDaysRo implements Serializable{

    @XStreamAlias("info")
    private String info;
    @XStreamImplicit(itemFieldName="row")
    private List<SumOrdersDaysXinRoList> SumOrdersDaysRoList;

    @XStreamAlias("row")
    class SumOrdersDaysXinRoList{

        @XStreamAsAttribute
        @XStreamAlias("RoundDate")
        private String RoundDate;
        @XStreamAsAttribute
        @XStreamAlias("netAmount")
        private String netAmount;
        @XStreamAsAttribute
        @XStreamAlias("betAmount")
        private String betAmount;
        @XStreamAsAttribute
        @XStreamAlias("validBetAmount")
        private String validBetAmount;
        @XStreamAsAttribute
        @XStreamAlias("wagersTotal")
        private String wagersTotal;
        @XStreamAsAttribute
        @XStreamAlias("currency")
        private String currency;

    }
}
