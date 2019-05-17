package com.eason.report.pull.ag.vo.hunter;

import com.eason.report.pull.ag.vo.competition.CompOrdersRo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("OrderRes")
public class GameOrdersRo implements Serializable{

    @XStreamAlias("Code")
    private String code;
    @XStreamAlias("Total")
    private String total;
    @XStreamAlias("TotalCashPay")
    private String totalCashPay;
    @XStreamAlias("TotalCashBalance")
    private String TotalCashBalance;
    @XStreamAlias("perpage")
    private String perpage;
    @XStreamAlias("numpage")
    private String numpage;
    @XStreamAlias("TotalJackpotComm")
    private String totalJackpotComm;

    @XStreamAlias("Bill")
    class CompOrdersRoList{

        @XStreamAsAttribute
        @XStreamAlias("RoomId")
        private String roomId;
        @XStreamAsAttribute
        @XStreamAlias("RoomBet")
        private String roomBet;
        @XStreamAsAttribute
        @XStreamAlias("UserId")
        private String userId;
        @XStreamAsAttribute
        @XStreamAlias("BombFish")
        private String bombFish;
        @XStreamAsAttribute
        @XStreamAlias("Hunted")
        private String hunted;
        @XStreamAsAttribute
        @XStreamAlias("FishType")
        private String fishType;
        @XStreamAsAttribute
        @XStreamAlias("FishLife")
        private String fishLife;
        @XStreamAsAttribute
        @XStreamAlias("FishId")
        private String fishId;
        @XStreamAsAttribute
        @XStreamAlias("CannonBoost")
        private String cannonBoost;
        @XStreamAsAttribute
        @XStreamAlias("CannonCost")
        private String cannonCost;
        @XStreamAsAttribute
        @XStreamAlias("FishCost")
        private String fishCost;
        @XStreamAsAttribute
        @XStreamAlias("UserCashBefore")
        private String userCashBefore;
        @XStreamAsAttribute
        @XStreamAlias("UserCashDelta")
        private String userCashDelta;
        @XStreamAsAttribute
        @XStreamAlias("UserCashCurrent")
        private String userCashCurrent;
        @XStreamAsAttribute
        @XStreamAlias("SceneId")
        private String sceneId;
        @XStreamAsAttribute
        @XStreamAlias("Time")
        private String time;
        @XStreamAsAttribute
        @XStreamAlias("UserCashPay")
        private String userCashPay;
        @XStreamAsAttribute
        @XStreamAlias("UserCashEarn")
        private String userCashEarn;
        @XStreamAsAttribute
        @XStreamAlias("userName")
        private String UserName;
        @XStreamAsAttribute
        @XStreamAlias("ProductId")
        private String productId;
        @XStreamAsAttribute
        @XStreamAlias("UserType")
        private String userType;
        @XStreamAsAttribute
        @XStreamAlias("BillId")
        private String billId;
        @XStreamAsAttribute
        @XStreamAlias("JackPotComm")
        private String jackPotComm;
        @XStreamAsAttribute
        @XStreamAlias("OperType")
        private String operType;
    }
}
