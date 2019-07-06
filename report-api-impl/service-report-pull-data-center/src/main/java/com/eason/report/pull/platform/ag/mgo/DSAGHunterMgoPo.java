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

@Document(collection = "ds_ag_hunter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSAGHunterMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String billNo;
    private String agentId;
    private String username;
    private String playName;
    private Integer roomId;
    private String sceneId;
    private Date startTime;
    private Date endTime;
    private Integer bulletOutNum;
    private BigDecimal roomBet;
    private BigDecimal cost;
    private BigDecimal earn;
    private BigDecimal win;
    private Byte winLossType;
    private BigDecimal jpDraw;
    private BigDecimal sceneEx;
    private BigDecimal jackPotComm;
    private BigDecimal bombDraw;
    private String remark;
    private Integer operType;
    private Integer billHitNum;
    private Integer billType;
    private Date createTime;
    private Date updateTime;
    private Integer mission;
    private Integer wb;

}
