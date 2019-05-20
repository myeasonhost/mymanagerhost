package com.eason.report.pull.h8.utils;

/**
 * 
*    
* 项目名称：kg-ds-live   
* 类名称：LiveConfig   
* 类描述：  视讯配置信息 
* 创建人：光光   
* 创建时间：2015年5月10日 下午8:34:33   
* 修改人：光光   
* 修改时间：2015年5月10日 下午8:34:33   
* 修改备注：   
* @version    
*
 */
public class LiveConfig {
	
	/**************************************/
	
	
	
	/**************************************/
	
	//状态定义 -50：删除  0： 未启用  50：正常  
	public final static Short DELETE_STATE = -50;
	public final static Short NOT_ENABLE_STATE = 0;//未启用
	public final static Short NORMAL_STATE = 50;
	
	
	/*******************************************/
	//2:AG视讯厅  3:OG视讯厅 11:BBIN视讯厅 12:DS视讯厅 13:M8体育
    
	public final static Integer AG_LIVE_ID = 2;
	public final static Integer OG_LIVE_ID = 3;
	public final static Integer BBIN_LIVE_ID = 11;
	//public final static Integer DS_LIVE_ID = 12;
	public final static Integer M8_LIVE_ID = 13;
	
	/*********************************************/
	//ds视讯厅 游戏类型定义
	public final static Integer DS_LIVE_GAME_TYPE_BACCARAT = 1;//百家乐
	public final static Integer DS_LIVE_GAME_TYPE_DRAGON_TIGER = 2;//龙虎
	public final static Integer DS_LIVE_GAME_TYPE_ROULETTE = 3;//轮盘
	public final static Integer DS_LIVE_GAME_TYPE_BACCARAT_INSURANCE = 4;//保险百家乐
	public final static Integer DS_LIVE_GAME_TYPE_SICBO = 5;//赛宝 
	public final static Integer DS_LIVE_GAME_TYPE_XOC_DIA = 6;//色碟
	
	/************************************************/
	//视讯输赢定义
	public final static Integer LIVE_RESULT_TYPE_LOSE = 1;//输
	public final static Integer LIVE_RESULT_TYPE_WIN = 2;//赢
	public final static Integer LIVE_RESULT_TYPE_HE = 3;//和
	public final static Integer LIVE_RESULT_TYPE_CANCEL = 4;//注单取消
	
}
