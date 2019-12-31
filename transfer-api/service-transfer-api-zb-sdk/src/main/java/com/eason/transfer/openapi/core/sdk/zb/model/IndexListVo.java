package com.eason.transfer.openapi.core.sdk.zb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XStreamAlias("IndexListVo")
public class IndexListVo {
    private Integer rows;
    private Integer roomId; 		//房间ID
    private Integer zbId; 		//主播ID
    private Integer roomPlanId; 		//房间场次ID
    private String zbNickName; 	//主播昵称
    private String zbHeadImg; 	//主播头像
    private String zbLevel; 	//主播等级
    private String roomTitle;  	//房间标题
    private String roomType;   	//房间类型：'normal=普通房间',game=游戏房间'
    private Integer onlineUser;  //真实在线用户
    private Integer machineUser; //机器用户
    private Integer viewCount; 	//总浏览次数
    private Integer watchCount; 	//总观看次数
    private String roomBackgroundImg;  //房间背景图片
    private Integer roomStatus;  	//直播状态： 1=直播中，2=未开播，3=回放中
    private Timestamp startTime;  	//房间开播时间
    private String gameIcon;  	//游戏图标
    private String playUrl; 	//录像回放地址
    private Integer isCharge; 	//是否收费   0=不收费，1=收费
}
