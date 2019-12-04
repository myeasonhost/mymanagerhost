/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.7.26-log : Database - myeason
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myeason` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myeason`;

/*Table structure for table `api_access_log` */

DROP TABLE IF EXISTS `api_access_log`;

CREATE TABLE `api_access_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `INVOKE_METHOD` varchar(100) DEFAULT NULL COMMENT '方法名称',
  `METHOD_VER` varchar(20) DEFAULT NULL COMMENT '方法版本号',
  `RESULT_TYPE` int(2) DEFAULT NULL COMMENT '返回值类型（1：正常、2：异常：3：参数错误）',
  `RESULT_CODE` varchar(50) DEFAULT NULL COMMENT '返回错误编码',
  `RESULT_MSG` varchar(200) DEFAULT NULL COMMENT '错误信息',
  `DEVICE_MANUFACTURER` varchar(50) DEFAULT NULL COMMENT '设备厂商',
  `DEVICE_MODEL` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `SYSTEM_VER` varchar(50) DEFAULT NULL COMMENT '系统版本号',
  `APP_VER` varchar(50) DEFAULT NULL COMMENT 'app版本号',
  `VISIT_DATE` datetime DEFAULT NULL COMMENT '调用接口时间',
  `IP` varchar(40) DEFAULT NULL COMMENT '调用接口的IP',
  `INVOKE_PARAM` varchar(1000) DEFAULT NULL COMMENT '调用接口的参数',
  `VISIT_TIME_COST` int(10) DEFAULT NULL COMMENT '接口响应时间',
  `EXCEPTION` varchar(500) DEFAULT NULL COMMENT '异常消息',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='接口调用记录表';

/*Data for the table `api_access_log` */

/*Table structure for table `api_method` */

DROP TABLE IF EXISTS `api_method`;

CREATE TABLE `api_method` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `METHOD_TYPE` int(11) DEFAULT NULL COMMENT '方法类别',
  `METHOD` varchar(100) DEFAULT NULL COMMENT '方法名称',
  `METHOD_NAME` varchar(100) DEFAULT NULL COMMENT '方法中文名称',
  `METHOD_MEMO` varchar(200) DEFAULT NULL COMMENT '方法备注',
  `INVOKE_MIN_MAX_NUM` int(18) DEFAULT NULL COMMENT '一分钟可调用次数',
  `INVOKE_DAY_MAX_NUM` int(18) DEFAULT NULL COMMENT '一天可调用次数',
  `AUTH_LEVEL` int(2) DEFAULT NULL COMMENT '调用等级（0：不需要appkey，1：需要appkey，2：需要appkey&sessionkey，3：需要appkey&sessionkey（每次使用都需要登录授权））',
  `IS_UPDATED` int(2) DEFAULT NULL COMMENT '是否更新类接口(1是;0否)',
  `XML_RESULT` varchar(5000) DEFAULT NULL COMMENT 'XML数据格式示例',
  `JSON_RESULT` varchar(5000) DEFAULT NULL COMMENT 'JSON数据格式示例',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `UPDATE_BY` int(18) DEFAULT NULL COMMENT '更新人ID',
  `CREATE_BY` int(18) DEFAULT NULL COMMENT '创建人ID',
  `IS_EXTRAS` tinyint(1) DEFAULT NULL COMMENT '是否增值接口（0：开放，1：增值 默认是0）',
  `IS_OPEN` int(2) DEFAULT NULL COMMENT '是否对外开放接口',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_22` (`METHOD_TYPE`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='方法表';

/*Data for the table `api_method` */

insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (1,1,'user.demo','demo测试','demo测试',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (2,1,'user.demo2','demo测试','demo测试',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (3,1,'user.info.register','用户注册','用户注册',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (4,1,'user.info.login','用户登录','用户登录',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (5,2,'img.index.swiper.get','图片首页轮播','图片首页轮播',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (6,1,'user.info.get','获取用户信息','获取用户信息',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (7,3,'money.balance.get','获取钱包余额','获取钱包余额',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (8,3,'money.balance.transfer','平台转账接口','平台转账接口',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (9,2,'user.game.enterPlay','进入游戏','进入游戏',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (10,3,'img.index.notice.get','消息首页轮播','消息首页轮播',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (11,4,'chess.lc.user.login','龙城棋牌登录','龙城棋牌登录',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (12,4,'chess.lc.user.transferIn','龙城棋牌转入上分','龙城棋牌转入上分',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (13,4,'chess.lc.user.transferOut','龙城棋牌转出下分','龙城棋牌转出下分',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (14,4,'chess.lc.user.queryBalance','龙城棋牌查询余额','龙城棋牌查询余额',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (15,4,'chess.lc.user.queryOrderStatus','龙城棋牌查询订单状态','龙城棋牌查询订单状态',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (16,4,'chess.lc.user.queryPlayerStatus','龙城棋牌查询玩家在线状态','龙城棋牌查询玩家在线状态',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (17,4,'chess.lc.report.getBetRecordList','龙城棋牌查询投注明细记录','龙城棋牌查询投注明细记录',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (18,4,'chess.lc.report.getWalletList','龙城棋牌查询用户账户流水','龙城棋牌查询用户账户流水',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (19,4,'chess.lc.report.getUserGameTypeReport','龙城棋牌用户游戏报表','龙城棋牌用户游戏报表',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `api_method`(`ID`,`METHOD_TYPE`,`METHOD`,`METHOD_NAME`,`METHOD_MEMO`,`INVOKE_MIN_MAX_NUM`,`INVOKE_DAY_MAX_NUM`,`AUTH_LEVEL`,`IS_UPDATED`,`XML_RESULT`,`JSON_RESULT`,`CREATE_TIME`,`UPDATE_TIME`,`UPDATE_BY`,`CREATE_BY`,`IS_EXTRAS`,`IS_OPEN`) values (20,4,'chess.lc.report.getUserGameKindReport','龙城棋牌平台汇总报表','龙城棋牌平台汇总报表',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `api_method_category` */

DROP TABLE IF EXISTS `api_method_category`;

CREATE TABLE `api_method_category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `CATE_EN_NAME` varchar(100) DEFAULT NULL COMMENT '方法类别英文名称',
  `CATE_CN_NAME` varchar(100) DEFAULT NULL COMMENT '类别中文名称',
  `CATE_DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '类别描述',
  `IS_DELETED` tinyint(1) DEFAULT NULL COMMENT '是否删除(0:未删除;1:已删除)',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='方法类别表';

/*Data for the table `api_method_category` */

insert  into `api_method_category`(`ID`,`CATE_EN_NAME`,`CATE_CN_NAME`,`CATE_DESCRIPTION`,`IS_DELETED`) values (1,'UserAPI','用户API','用户API',0);
insert  into `api_method_category`(`ID`,`CATE_EN_NAME`,`CATE_CN_NAME`,`CATE_DESCRIPTION`,`IS_DELETED`) values (2,'ImgAPI','图片API','图片API',0);
insert  into `api_method_category`(`ID`,`CATE_EN_NAME`,`CATE_CN_NAME`,`CATE_DESCRIPTION`,`IS_DELETED`) values (3,'MoneyAPI','钱包API','钱包API',0);
insert  into `api_method_category`(`ID`,`CATE_EN_NAME`,`CATE_CN_NAME`,`CATE_DESCRIPTION`,`IS_DELETED`) values (4,'ChessAPI','棋牌API','棋牌API',0);

/*Table structure for table `api_method_cfg` */

DROP TABLE IF EXISTS `api_method_cfg`;

CREATE TABLE `api_method_cfg` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `METHOD_ID` int(11) DEFAULT NULL COMMENT '方法ID',
  `VER` varchar(10) DEFAULT NULL COMMENT '版本号',
  `CFG_VALUE` varchar(300) DEFAULT NULL COMMENT '配置值',
  `IS_DELETED` tinyint(1) DEFAULT NULL COMMENT '删除标识。1：删除 0：未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_BY` int(11) DEFAULT NULL COMMENT '创建者id',
  `UPDATE_BY` int(11) DEFAULT NULL COMMENT '更新者id',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_24` (`METHOD_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='方法配置表';

/*Data for the table `api_method_cfg` */

insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (1,1,'1.0.0','local;demoServiceImpl;test;com.eason.transfer.openapi.core.client.model.DemoRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (2,2,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;login;com.eason.transfer.openapi.core.sdk.chess.model.LoginRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (3,3,'1.0.0','local;userServiceImpl;register;com.eason.transfer.openapi.core.client.user.model.UserInfoRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (4,4,'1.0.0','local;userServiceImpl;login;com.eason.transfer.openapi.core.client.user.model.UserLoginRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (5,5,'1.0.0','local;imgServiceImpl;getIndexSwiper;com.eason.transfer.openapi.core.client.img.model.ImgRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (6,6,'1.0.0','local;userServiceImpl;getUserInfo;com.eason.transfer.openapi.core.client.user.model.UserInfoGetRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (7,7,'1.0.0','local;moneyServiceImpl;getBalance;com.eason.transfer.openapi.core.client.money.model.MoneyBalanceGetRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (8,8,'1.0.0','local;moneyServiceImpl;transfer;com.eason.transfer.openapi.core.client.money.model.MoneyINRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (9,9,'1.0.0','local;userServiceImpl;playGame;com.eason.transfer.openapi.core.client.user.model.PlayGameRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (10,10,'1.0.0','local;imgServiceImpl;getIndexNotice;com.eason.transfer.openapi.core.client.img.model.NoticeRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (11,11,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;login;com.eason.transfer.openapi.core.sdk.chess.model.LoginRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (12,12,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;transferIn;com.eason.transfer.openapi.core.sdk.chess.model.TransferInRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (13,13,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;transferOut;com.eason.transfer.openapi.core.sdk.chess.model.TransferOutRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (14,14,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;queryBalance;com.eason.transfer.openapi.core.sdk.chess.model.QueryBalanceRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (15,15,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;queryOrderStatus;com.eason.transfer.openapi.core.sdk.chess.model.OrderStatusRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (16,16,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;queryPlayerStatus;com.eason.transfer.openapi.core.sdk.chess.model.PlayerStatusRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (17,17,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;getBetRecordList;com.eason.transfer.openapi.core.sdk.chess.model.PullBetRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (18,18,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IUserService;getWalletList;com.eason.transfer.openapi.core.sdk.chess.model.WalletListRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (19,19,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IReportService;getUserGameTypeReport;com.eason.transfer.openapi.core.sdk.chess.model.ReportUserGameTypeRequest',0,NULL,NULL,NULL,NULL);
insert  into `api_method_cfg`(`ID`,`METHOD_ID`,`VER`,`CFG_VALUE`,`IS_DELETED`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`) values (20,20,'1.0.0','remote;com.eason.transfer.openapi.core.sdk.chess.IReportService;getUserGameKindReport;com.eason.transfer.openapi.core.sdk.chess.model.ReportUserGameKindRequest',0,NULL,NULL,NULL,NULL);

/*Table structure for table `api_method_param` */

DROP TABLE IF EXISTS `api_method_param`;

CREATE TABLE `api_method_param` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `METHOD_ID` int(11) DEFAULT NULL COMMENT '方法ID',
  `PARAM_NAME` varchar(20) DEFAULT NULL COMMENT '参数名称',
  `PARAM_TYPE` varchar(100) DEFAULT NULL COMMENT '参数类型',
  `IS_NECESSARY` tinyint(1) DEFAULT NULL COMMENT '是否必须(1是,0否)',
  `EXAMPLE` varchar(100) DEFAULT NULL COMMENT '示例值',
  `DEFAULT_VALUE` varchar(100) DEFAULT NULL COMMENT '默认值',
  `PARAM_DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '参数描述',
  `PARAM_CLASS` tinyint(1) DEFAULT NULL COMMENT '参数类别(1应用级参数;0返回结果属性)',
  `IS_OBJECT` tinyint(1) DEFAULT NULL COMMENT '是否自定义对象(1是,0否)',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_23` (`METHOD_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='方法参数表';

/*Data for the table `api_method_param` */

insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (1,1,'param','java.lang.String',1,NULL,'\"aaa\"',NULL,1,NULL,'2019-09-12 17:13:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (10,2,'username','java.lang.String',1,NULL,'\"aaa\"',NULL,1,NULL,'2019-10-08 14:36:36');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (2,3,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-10-08 16:02:27');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (3,3,'password','java.lang.String',1,NULL,NULL,'密码',1,NULL,'2019-10-08 16:16:41');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (5,3,'realName','java.lang.String',1,NULL,NULL,'真实姓名',1,NULL,'2019-10-08 16:17:23');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (6,3,'phoneNum','java.lang.String',1,NULL,NULL,'手机号码 ',1,NULL,'2019-10-08 16:18:14');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (7,3,'email','java.lang.String',0,NULL,NULL,'电子邮箱',1,NULL,'2019-10-08 16:18:30');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (8,3,'wechat','java.lang.String',0,NULL,NULL,'微信号',1,NULL,'2019-10-08 16:20:20');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (9,3,'invite','java.lang.String',0,NULL,NULL,'推荐人',1,NULL,'2019-10-08 16:21:13');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (4,3,'surePassword','java.lang.String',1,NULL,NULL,'确认密码',1,NULL,'2019-10-08 16:16:41');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (11,4,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-10-09 18:25:07');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (12,4,'password','java.lang.String',1,NULL,NULL,'密码',1,NULL,'2019-10-10 16:30:41');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (13,5,'type','java.lang.String',1,NULL,NULL,'图片类型',1,NULL,'2019-10-11 14:56:49');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (17,8,'action','java.lang.String',1,NULL,NULL,'转账类型',1,NULL,'2019-10-23 15:24:07');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (14,7,'type','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-10-22 17:59:41');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (16,8,'transMethod','java.lang.String',1,NULL,NULL,'转账实现方法',1,NULL,'2019-10-23 15:23:43');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (19,9,'live','java.lang.String',1,NULL,NULL,'游戏编码',1,NULL,'2019-10-25 14:28:44');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (18,8,'credit','java.lang.String',1,NULL,NULL,'转账金额',1,NULL,'2019-10-23 15:24:39');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (20,10,'type','java.lang.String',1,NULL,NULL,'消息类型',1,NULL,'2019-10-28 11:34:37');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (21,11,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (22,11,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (23,11,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (24,11,'gameType','java.lang.String',1,NULL,NULL,'游戏类型',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (25,12,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (26,12,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (27,12,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (28,12,'money','java.lang.Double',1,NULL,NULL,'金额',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (29,13,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (30,13,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (31,13,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (32,13,'money','java.lang.Double',1,NULL,NULL,'金额',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (33,14,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (34,14,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (35,14,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (36,15,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (37,15,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (38,15,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (39,15,'orderId','java.lang.Double',1,NULL,NULL,'订单号',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (40,16,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (41,16,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (42,16,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (43,17,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (44,17,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (45,17,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (46,17,'startTime','java.lang.String',1,NULL,NULL,'投注开始日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (47,17,'endTime','java.lang.String',1,NULL,NULL,'投注结束日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (48,17,'page','java.lang.Integer',1,NULL,NULL,'第几页',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (49,17,'pageSize','java.lang.Integer',1,NULL,NULL,'显示多少行',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (50,18,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (51,18,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (52,18,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (53,18,'startTime','java.lang.String',1,NULL,NULL,'投注开始日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (54,18,'endTime','java.lang.String',1,NULL,NULL,'投注结束日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (55,18,'page','java.lang.Integer',1,NULL,NULL,'第几页',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (56,18,'pageSize','java.lang.Integer',1,NULL,NULL,'显示多少行',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (57,19,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (58,19,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (59,19,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (60,19,'startTime','java.lang.String',1,NULL,NULL,'投注开始日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (61,19,'endTime','java.lang.String',1,NULL,NULL,'投注结束日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (62,19,'page','java.lang.Integer',1,NULL,NULL,'第几页',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (63,19,'pageSize','java.lang.Integer',1,NULL,NULL,'显示多少行',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (64,20,'username','java.lang.String',1,NULL,NULL,'用户名',1,NULL,'2019-12-03 19:32:05');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (65,20,'siteId','java.lang.String',1,NULL,NULL,'站点ID',1,NULL,'2019-12-03 19:32:33');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (66,20,'loginType','java.lang.String',1,NULL,NULL,'平台类型',1,NULL,'2019-12-03 19:32:54');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (67,20,'startTime','java.lang.String',1,NULL,NULL,'投注开始日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (68,20,'endTime','java.lang.String',1,NULL,NULL,'投注结束日期',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (69,20,'page','java.lang.Integer',1,NULL,NULL,'第几页',1,NULL,'2019-12-03 19:33:15');
insert  into `api_method_param`(`ID`,`METHOD_ID`,`PARAM_NAME`,`PARAM_TYPE`,`IS_NECESSARY`,`EXAMPLE`,`DEFAULT_VALUE`,`PARAM_DESCRIPTION`,`PARAM_CLASS`,`IS_OBJECT`,`CREATE_TIME`) values (70,20,'pageSize','java.lang.Integer',1,NULL,NULL,'显示多少行',1,NULL,'2019-12-03 19:33:15');

/*Table structure for table `app_info` */

DROP TABLE IF EXISTS `app_info`;

CREATE TABLE `app_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `APP_NAME` varchar(50) DEFAULT NULL COMMENT 'app名称',
  `app_image` varchar(300) DEFAULT NULL COMMENT 'app图标',
  `APP_KEY` varchar(50) DEFAULT NULL COMMENT 'app key',
  `APP_SECRET` varchar(50) DEFAULT NULL COMMENT 'app secret',
  `APP_USER_TABLE` varchar(30) DEFAULT NULL COMMENT '对应的用户表名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_BY` int(11) DEFAULT NULL COMMENT '创建者id',
  `UPDATE_BY` int(11) DEFAULT NULL COMMENT '修改者id',
  `LOWEST_VERSION` varchar(50) DEFAULT NULL COMMENT '最低版本',
  `IS_DELETED` tinyint(1) DEFAULT NULL COMMENT '删除标识。1：删除 0：未删除',
  `LAST_VERSION` varchar(50) DEFAULT NULL COMMENT '最新版本',
  `CONTENT` varchar(500) DEFAULT NULL COMMENT '新版本描述',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='APP信息表';

/*Data for the table `app_info` */

insert  into `app_info`(`ID`,`APP_NAME`,`app_image`,`APP_KEY`,`APP_SECRET`,`APP_USER_TABLE`,`CREATE_TIME`,`UPDATE_TIME`,`CREATE_BY`,`UPDATE_BY`,`LOWEST_VERSION`,`IS_DELETED`,`LAST_VERSION`,`CONTENT`) values (1,'EASON',NULL,'1040','asdfghjkqwertyu',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
