package com.eason.report.pull.platform.ky.mgo;

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

@Document(collection = "ky_chess_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KyMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String gameId;
    private String account;
    private Integer serverId;
    private Integer kindId;
    private Integer tableId;
    private Integer chairId;
    private Integer userCount;
    private String cardValue;
    private BigDecimal cellScore;
    private BigDecimal allBet;
    private BigDecimal profit;
    private BigDecimal revenue;
    private Byte winLossType;
    private Date gameStartTime;
    private Date gameEndTime;
    private Integer channelId;
    private Date createTime;
    private Date updateTime;

}
