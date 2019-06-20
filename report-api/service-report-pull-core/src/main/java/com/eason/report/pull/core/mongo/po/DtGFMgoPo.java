package com.eason.report.pull.core.mongo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "dt_guangfang_lottery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtGFMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private Long id;
    private String nid;
    private Integer lid;
    private Integer uid;
    private String userName;
    private String top1;
    private String top2;
    private String top3;
    private String top4;
    private Integer proportion1;
    private Integer proportion2;
    private Integer proportion3;
    private Integer proportion4;
    private Integer proportion5;
    private String pan;
    private Long issue;
    private Long mgId;
    private Long traceId;
    private Integer singleNum;
    private Integer multiple;
    private BigDecimal modes;
    private String odd;
    private BigDecimal amount;
    private BigDecimal amount1;
    private BigDecimal amount2;
    private BigDecimal amount3;
    private BigDecimal amount4;
    private BigDecimal amount5;
    private BigDecimal wins;
    private BigDecimal win1;
    private BigDecimal win2;
    private BigDecimal win3;
    private BigDecimal win4;
    private BigDecimal win5;
    private String hitDetail;
    private String proxyIp;
    private String uIp;
    private String serverIp;
    private String hashValue;
    private String code;
    private String updateTime;
    private String drawTime;
    private String sendPrizeTime;
    private String addTime;
    private String jiesuanTime;
    private String cancelTime;
    private Integer isTake;
    private Integer isCancel;
    private Integer isJiesuan;
    private Integer isPay;
    private Integer cancelAdminId;
    private Date betTime;
    private Byte winLoseType;
    private Date reportTime;

}
