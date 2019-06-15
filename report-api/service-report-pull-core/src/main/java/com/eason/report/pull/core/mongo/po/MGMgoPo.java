package com.eason.report.pull.core.mongo.po;

import com.eason.report.pull.core.mongo.config.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Document(collection = "ds_mg_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MGMgoPo extends BasePo {
    @Id
    @GeneratedValue
    private long id;
    private Integer siteId;
    private String userName;
    private String colId;
    private String agentId;
    private String mbrId;
    private String mbrCode;
    private String transId;
    private Integer winLossType;
    private Integer gameId;
    private String transType;
    private BigDecimal amnt;
    private BigDecimal clrngAmnt;
    private BigDecimal balance;
    private String mgsGameId;
    private String mgsActionId;
    private Timestamp transTime;
    private Integer refTransId;
    private String refTransType;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private String keyB;
    private String memo;

}
