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

@Document(collection = "ds_ag_xin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSAGXinMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String billNo;
    private String agentId;
    private String username;
    private String playerName;
    private Date billTime;
    private Date reckonTime;
    private String slotType;
    private String currency;
    private String gameType;
    private String betIp;
    private BigDecimal account;
    private BigDecimal cusAccount;
    private BigDecimal validAccount;
    private BigDecimal accountBase;
    private BigDecimal accountBonus;
    private BigDecimal cusAccountBase;
    private BigDecimal cusAccountBonus;
    private BigDecimal srcAmount;
    private BigDecimal dstAmount;
    private Byte winLossType;
    private String flag;
    private String platformType;
    private String deviceType;
    private String exttxid;
    private Date createTime;
    private Date updateTime;

}
