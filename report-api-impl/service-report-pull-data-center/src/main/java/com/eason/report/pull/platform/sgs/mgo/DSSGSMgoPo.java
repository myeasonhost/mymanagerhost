package com.eason.report.pull.platform.sgs.mgo;

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

@Document(collection = "ds_sgs_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSSGSMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String ugsBetId;
    private String agentId;
    private String txid;
    private String betId;
    private Date betOn;
    private Date betClosedOn;
    private Date betUpdatedOn;
    private Date timestamp0;
    private String roundid;
    private String roundStatus;
    private String userid;
    private String username;
    private BigDecimal riskamt;
    private BigDecimal winamt;
    private BigDecimal winloss;
    private BigDecimal beforebal;
    private BigDecimal postbal;
    private Byte winLossType;
    private String currency;
    private String gameProvider;
    private String gameProviderCode;
    private String gameName;
    private String gameId;
    private String platformType;
    private String ipAddress;
    private String betType;
    private String playType;
    private Byte playerType;
    private BigDecimal turnover;
    private BigDecimal validbet;
    private Date createTime;
    private Date updateTime;

}
