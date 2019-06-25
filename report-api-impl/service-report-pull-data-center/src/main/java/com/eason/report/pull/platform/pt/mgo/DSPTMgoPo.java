package com.eason.report.pull.platform.pt.mgo;

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

@Document(collection = "ds_pt_game")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSPTMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private String gamecode;
    private String agentId;
    private String username;
    private String playername;
    private String windowcode;
    private Integer gameid;
    private String gametype;
    private String gamename;
    private String sessionid;
    private BigDecimal bet;
    private BigDecimal win;
    private BigDecimal progressivebet;
    private BigDecimal progressivewin;
    private BigDecimal balance;
    private BigDecimal currentbet;
    private Integer rnum;
    private Date gamedate;
    private String livenetwork;
    private Integer winLossType;
    private Date createTime;
    private Date updateTime;

}
