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

@Document(collection = "mdt_jingdian_lottery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MdtJDMgoPo extends BasePo {
    @Id
    private String tid;
    @Indexed(unique=true)
    private long id;
    private String nid;
    private Byte lid;
    private String user;
    private byte userType;
    private String user1;
    private String user2;
    private String user3;
    private String user4;
    private String pan;
    private long qishu;
    private Byte pid;
    private Short oid;
    private String project;
    private String itmes;
    private String content;
    private String odds;
    private int jiner;
    private BigDecimal jiner1;
    private BigDecimal jiner2;
    private BigDecimal jiner3;
    private BigDecimal jiner4;
    private BigDecimal jiner5;
    private BigDecimal jinerb;
    private byte share1;
    private byte share2;
    private byte share3;
    private byte share4;
    private byte share5;
    private BigDecimal winDream;
    private BigDecimal win;
    private BigDecimal win1;
    private BigDecimal win2;
    private BigDecimal win3;
    private BigDecimal win4;
    private BigDecimal win5;
    private BigDecimal bonus;
    private BigDecimal bonus1;
    private BigDecimal bonus2;
    private BigDecimal bonus3;
    private BigDecimal bonus4;
    private BigDecimal bonus5;
    private BigDecimal tuishui;
    private BigDecimal tuishui1;
    private BigDecimal tuishui2;
    private BigDecimal tuishui3;
    private BigDecimal tuishui4;
    private BigDecimal tuishui5;
    private String ip;
    private String hash;
    private byte stataus;
    private String timeDraw;
    private String timeIn;
    private String timeAdd;
    private String timeJiesuan;
    private String timePay;
    private boolean isCancel;
    private String cancelContent;
    private Date betTime;
    private byte winLoseType;
    private Date reportTime;
}
