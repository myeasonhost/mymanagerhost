package com.eason.report.pull.platform.lmg.mgo;

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

@Document(collection = "ds_lmg_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LMGMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private long id;
    private String agentId;
    private Long sequenceNo;
    private String username;
    private String currency;
    private String liveProductType;
    private String gameType;
    private Integer tableInfoId;
    private Integer shoeInfoId;
    private Integer gameInfoId;
    private String tableName;
    private String bankerResult;
    private String resultList;
    private String pokerList;
    private BigDecimal stakeAmount;
    private BigDecimal validStake;
    private BigDecimal comm;
    private BigDecimal commAmount;
    private BigDecimal winLoss;
    private Integer winLossType;
    private BigDecimal balanceAfter;
    private Date endTime;
    private Date reportDate;
    private String ip;
    private String resultImgName;
    private String hall;
    private String liveMemberReportDetails;
    private Date createTime;
    private Date updateTime;

}
