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

@Document(collection = "ds_ag_sport")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSAGSportMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String billNo;
    private String agentId;
    private String username;
    private String playName;
    private Date billTime;
    private Date reckonTime;
    private String currency;
    private String gameType;
    private String betIp;
    private BigDecimal account;
    private BigDecimal cusAccount;
    private BigDecimal validAccount;
    private String flag;
    private String odds;
    private String competition;
    private String market;
    private String selection;
    private String simplifiedResult;
    private String sport;
    private String category;
    private String extbillno;
    private String thirdbillno;
    private String bettype;
    private String system;
    private String live;
    private String currentScore;
    private Byte winLossType;
    private Date createTime;
    private Date updateTime;

}
