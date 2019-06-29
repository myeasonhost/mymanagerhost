package com.eason.report.pull.platform.ag.mgo;

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

@Document(collection = "ds_ag_agin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSAGAginMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
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
