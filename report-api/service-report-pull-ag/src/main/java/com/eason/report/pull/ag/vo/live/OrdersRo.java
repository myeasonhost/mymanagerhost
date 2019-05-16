package com.eason.report.pull.ag.vo.live;

import com.eason.report.pull.ag.vo.hunter.ScenesOfUserReportExtRo;
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
public class OrdersRo implements Serializable{

    @XStreamAlias("info")
    private String info;
    @XStreamAlias("addition")
    class addition{
        @XStreamAlias("total")
        private String total;
        @XStreamAlias("num_per_page")
        private String numPerPage;
        @XStreamAlias("currentpage")
        private String currentPage;
        @XStreamAlias("perpage")
        private String perPage;

    }
    @XStreamImplicit(itemFieldName="row")
    private List<OrdersRo> OrdersRoList;

    @XStreamAlias("row")
    class OrdersRoList{

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
