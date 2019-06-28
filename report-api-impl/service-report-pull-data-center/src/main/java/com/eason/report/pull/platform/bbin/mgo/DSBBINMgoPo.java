package com.eason.report.pull.platform.bbin.mgo;

import com.eason.report.pull.core.mongo.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "ds_bbin_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSBBINMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String wagersId;
    private String agentId;
    private String userName;
    private Date wagersDate;
    private String serialId;
    private String roundNo;
    private String gameType;
    private String gameCode;
    private String result;
    private String resultType;
    private String card;
    private BigDecimal betAmount;
    private BigDecimal payOff;
    private String currency;
    private String exchangeRate;
    private BigDecimal commissionable;
    private Byte winLossType;
    private Date createTime;
    private Date modifiedDate;

}
