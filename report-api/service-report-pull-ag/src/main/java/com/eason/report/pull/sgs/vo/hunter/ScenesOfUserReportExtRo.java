package com.eason.report.pull.sgs.vo.hunter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("result")
public class ScenesOfUserReportExtRo implements Serializable{

    @XStreamAlias("Code")
    private String code;
    @XStreamAlias("PageData")
    class PageData{

        @XStreamAsAttribute
        @XStreamAlias("ItemTotal")
        private String itemTotal;
        @XStreamAsAttribute
        @XStreamAlias("perpage")
        private String perpage;
        @XStreamAsAttribute
        @XStreamAlias("NumPage")
        private String numPage;

    }

        @XStreamAsAttribute
        @XStreamAlias("TotalBulletOutNum")
        private String totalBulletOutNum;
        @XStreamAsAttribute
        @XStreamAlias("TotalCost")
        private String totalCost;
        @XStreamAsAttribute
        @XStreamAlias("TotalEarn")
        private String totalEarn;
        @XStreamAsAttribute
        @XStreamAlias("TotalJpDraw")
        private String totalJpDraw;
        @XStreamAsAttribute
        @XStreamAlias("TotalMission")
        private String totalMission;
        @XStreamAsAttribute
        @XStreamAlias("TotalWB")
        private String totalWB;
        @XStreamAsAttribute
        @XStreamAlias("TotalBombDraw")
        private String totalBombDraw;
        @XStreamAsAttribute
        @XStreamAlias("TotalSceneEx")
        private String totalSceneEx;
        @XStreamAsAttribute
        @XStreamAlias("TotalJackpotComm")
        private String totalJackpotComm;
        @XStreamAsAttribute
        @XStreamAlias("TotalBillHitNum")
        private String totalBillHitNum;

        @XStreamAlias("Items")
        class Items{

            @XStreamAsAttribute
            @XStreamAlias("SceneId")
            private String sceneId;
            @XStreamAsAttribute
            @XStreamAlias("StartTime")
            private String startTime;
            @XStreamAsAttribute
            @XStreamAlias("EndTime")
            private String endTime;
            @XStreamAsAttribute
            @XStreamAlias("BulletOutNum")
            private String bulletOutNum;
            @XStreamAsAttribute
            @XStreamAlias("Cost")
            private String cost;
            @XStreamAsAttribute
            @XStreamAlias("Earn")
            private String earn;
            @XStreamAsAttribute
            @XStreamAlias("RoomId")
            private String roomId;
            @XStreamAsAttribute
            @XStreamAlias("UserName")
            private String userName;
            @XStreamAsAttribute
            @XStreamAlias("JpDraw")
            private String jpDraw;
            @XStreamAsAttribute
            @XStreamAlias("Mission")
            private String mission;
            @XStreamAsAttribute
            @XStreamAlias("WB")
            private String WB;
            @XStreamAsAttribute
            @XStreamAlias("SceneEx")
            private String sceneEx;
            @XStreamAsAttribute
            @XStreamAlias("JackPotComm")
            private String jackPotComm;
            @XStreamAsAttribute
            @XStreamAlias("BombDraw")
            private String bombDraw;
            @XStreamAsAttribute
            @XStreamAlias("Remark")
            private String remark;
            @XStreamAsAttribute
            @XStreamAlias("OperType")
            private String operType;
            @XStreamAsAttribute
            @XStreamAlias("BillHitNum")
            private String billHitNum;



    }


}
