package com.eason.report.pull.ag.vo.common;

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
public class SumOrdersRo implements Serializable{
    @XStreamAlias("info")
    private String info;
    @XStreamImplicit(itemFieldName="row")
    private List<SumOrdersList> sumOrdersList;

    @XStreamAlias("row")
    class SumOrdersList{
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
        @XStreamAlias("currency")
        private String currency;
        @XStreamAsAttribute
        @XStreamAlias("wagersTotal")
        private String wagersTotal;
    }
}
