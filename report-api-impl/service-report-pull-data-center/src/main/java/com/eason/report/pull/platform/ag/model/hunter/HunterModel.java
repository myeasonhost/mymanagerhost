package com.eason.report.pull.platform.ag.model.hunter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("Items")
public class HunterModel {
    @XStreamAsAttribute
    @XStreamAlias("BillId")
    private String billNo;
    @XStreamAsAttribute
    @XStreamAlias("agentId")
    private String agentId;
    @XStreamAsAttribute
    @XStreamAlias("siteId")
    private Integer siteId;
    @XStreamAsAttribute
    @XStreamAlias("UserName")
    private String username;
    @XStreamAsAttribute
    @XStreamAlias("playName")
    private String playName;
    @XStreamAsAttribute
    @XStreamAlias("RoomId")
    private Integer roomId;
    @XStreamAsAttribute
    @XStreamAlias("SceneId")
    private String sceneId;
    @XStreamAsAttribute
    @XStreamAlias("StartTime")
    private Long startTime;
    @XStreamAsAttribute
    @XStreamAlias("EndTime")
    private Long endTime;
    @XStreamAsAttribute
    @XStreamAlias("BulletOutNum")
    private Integer bulletOutNum;
    @XStreamAsAttribute
    @XStreamAlias("RoomBet")
    private BigDecimal roomBet;
    @XStreamAsAttribute
    @XStreamAlias("Cost")
    private BigDecimal cost;
    @XStreamAsAttribute
    @XStreamAlias("Earn")
    private BigDecimal earn;
    @XStreamAsAttribute
    @XStreamAlias("win")
    private BigDecimal win;
    @XStreamAsAttribute
    @XStreamAlias("winLossType")
    private Byte winLossType;
    @XStreamAsAttribute
    @XStreamAlias("JpDraw")
    private BigDecimal jpDraw;
    @XStreamAsAttribute
    @XStreamAlias("SceneEx")
    private BigDecimal sceneEx;
    @XStreamAsAttribute
    @XStreamAlias("JackPotComm")
    private BigDecimal jackPotComm;
    @XStreamAsAttribute
    @XStreamAlias("BombDraw")
    private BigDecimal bombDraw;
    @XStreamAsAttribute
    @XStreamAlias("Remark")
    private String remark;
    @XStreamAsAttribute
    @XStreamAlias("OperType")
    private Integer operType;
    @XStreamAsAttribute
    @XStreamAlias("BillHitNum")
    private Integer billHitNum;
    @XStreamAsAttribute
    @XStreamAlias("BillType")
    private Integer billType;
    @XStreamAsAttribute
    @XStreamAlias("Mission")
    private Integer mission;
    @XStreamAsAttribute
    @XStreamAlias("WB")
    private Integer wb;
    @XStreamAsAttribute
    @XStreamAlias("createTime")
    private Date createTime;
    @XStreamAsAttribute
    @XStreamAlias("updateTime")
    private Date updateTime;

}
