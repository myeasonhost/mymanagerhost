/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.7.26-log : Database - chess
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chess` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `chess`;

/*Table structure for table `t_chess_game` */

DROP TABLE IF EXISTS `t_chess_game`;

CREATE TABLE `t_chess_game` (
  `GameID` varchar(50) NOT NULL COMMENT '游戏局号',
  `site_id` varchar(50) DEFAULT NULL COMMENT '站点ID',
  `Account` varchar(100) DEFAULT NULL COMMENT '玩家帐号',
  `ServerID` int(11) DEFAULT NULL COMMENT '房间 ID',
  `KindID` int(11) DEFAULT NULL COMMENT '游戏 ID',
  `TableID` bigint(20) DEFAULT NULL COMMENT '桌子号',
  `ChairID` int(11) DEFAULT NULL COMMENT '椅子号',
  `UserCount` int(11) DEFAULT NULL COMMENT '玩家数量',
  `CardValue` varchar(255) DEFAULT NULL COMMENT '手牌公共牌',
  `CellScore` decimal(18,2) DEFAULT NULL COMMENT '有效下注',
  `AllBet` decimal(18,2) DEFAULT NULL COMMENT '总下注',
  `Profit` decimal(18,2) DEFAULT NULL COMMENT '盈利',
  `Revenue` decimal(18,2) DEFAULT NULL COMMENT '抽水',
  `GameStartTime` timestamp NULL DEFAULT NULL COMMENT '游戏开始时间',
  `GameEndTime` timestamp NULL DEFAULT NULL COMMENT '游戏结束时间',
  `ChannelID` int(11) DEFAULT NULL COMMENT '渠道 ID',
  `LineCode` varchar(100) DEFAULT NULL COMMENT '玩家所属站点',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`GameID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='龙城棋牌注单明细';

/*Data for the table `t_chess_game` */

/*Table structure for table `t_chess_game_config` */

DROP TABLE IF EXISTS `t_chess_game_config`;

CREATE TABLE `t_chess_game_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` varchar(50) DEFAULT NULL COMMENT '代理号',
  `game_kind` varchar(50) DEFAULT NULL COMMENT '棋牌编码',
  `game_kind_name` varchar(50) DEFAULT NULL COMMENT '棋牌名称',
  `site_id` varchar(50) DEFAULT NULL COMMENT '站点ID',
  `DesKey` varchar(50) DEFAULT NULL COMMENT '平台提供',
  `Md5Key` varchar(50) DEFAULT NULL COMMENT '平台提供',
  `walletUrl` varchar(50) DEFAULT NULL COMMENT '钱包地址',
  `transferUrl` varchar(100) DEFAULT NULL COMMENT '接入地址',
  `pullUrl` varchar(100) DEFAULT NULL COMMENT '拉取地址',
  `length` tinyint(4) DEFAULT NULL COMMENT '拉取长度，单位（分钟）',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1=正常，2=维护中，3=废弃）',
  `initStartId` timestamp NULL DEFAULT NULL COMMENT '初始化水位',
  `info` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `agentId_unique` (`agent_id`),
  UNIQUE KEY `gameKind_unique` (`game_kind`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='棋牌游戏配置表';

/*Data for the table `t_chess_game_config` */

insert  into `t_chess_game_config`(`id`,`agent_id`,`game_kind`,`game_kind_name`,`site_id`,`DesKey`,`Md5Key`,`walletUrl`,`transferUrl`,`pullUrl`,`length`,`status`,`initStartId`,`info`) values (1,'91208','LC','龙城棋牌','HF_1000','273859B4ADE935A2','C0BA1D7016A6CFA1',NULL,'https://api.lc8889.com:189/channelHandle','https://record.lc8889.com:190/getRecordHandle',30,1,'2019-11-27 10:20:18','注单是以游戏派奖时间为准；拉取当前时间 30 秒 钟之前数据,建议拉取区间为 1-5 分钟，最大不能超过 60 分钟');

/*Table structure for table `t_chess_game_kind` */

DROP TABLE IF EXISTS `t_chess_game_kind`;

CREATE TABLE `t_chess_game_kind` (
  `KingID` int(11) NOT NULL COMMENT '游戏编码',
  `KindName` varchar(50) DEFAULT NULL COMMENT '游戏名称',
  `ParentKindID` int(11) DEFAULT NULL COMMENT '父类编码',
  `image` varchar(100) DEFAULT NULL COMMENT '游戏图片',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1=正常，2=维护中，3=废弃）',
  `info` varchar(100) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`KingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='棋牌游戏类型表';

/*Data for the table `t_chess_game_kind` */

insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (0,'大厅',NULL,NULL,1,NULL,'admin','2019-11-18 14:06:45',NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (220,'炸金花',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (510,'激光炮捕鱼',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (600,'21 点',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (610,'斗地主',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (620,'德州扑克',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (630,'十三水',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (640,'跑得快',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (720,'二八杠',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (730,'抢庄牌九',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (740,'二人麻将',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (830,'抢庄牛牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (860,'三公',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (870,'通比牛牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (890,'看四张抢庄牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (900,'龙虎斗',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (910,'百家乐',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (930,'百人牛牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (950,'红黑大战',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (970,'吹牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (980,'对战牛牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (990,'抢庄五选三',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (2201,'炸金花新手房',220,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (2202,'炸金花普通房',220,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (2203,'炸金花高手房',220,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (2204,'炸金花大师房',220,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3600,'德州扑克新手房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3601,'德州扑克普通房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3602,'德州扑克高手房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3603,'德州扑克大师房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3700,'德州扑克初学乍练房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3701,'德州扑克略有小成房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3702,'德州扑克得心应手房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (3703,'德州扑克炉火纯青房',620,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (5101,'新手海岸',510,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (5102,'渔村港口',510,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (5103,'海底世界',510,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (5104,'加勒比宝藏',510,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (5105,'东海龙宫',510,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6001,'21 点新手房',600,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6002,'21 点普通房',600,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6003,'21 点高手房',600,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6004,'21 点大师房',600,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6101,'斗地主新手房',610,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6102,'斗地主普通房',610,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6103,'斗地主高手房',610,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6104,'斗地主大师房',610,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6105,'斗地主宗师房',610,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6106,'斗地主至尊场',610,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6301,'十三水常规场新手房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6302,'十三水常规场普通房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6303,'十三水常规场高手房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6304,'十三水常规场大师房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6305,'十三水极速场新手房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6306,'十三水极速场普通房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6307,'十三水极速场高手房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6308,'十三水极速场大师房',630,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6401,'跑得快新手场',640,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6402,'跑得快普通场',640,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6403,'跑得快高手场',640,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6404,'跑得快大师场',640,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (6405,'跑得快宗师场',640,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7201,'二八杠新手房',720,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7202,'二八杠普通房',720,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7203,'二八杠高手房',720,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7204,'二八杠大师房',720,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7205,'二八杠至尊房',720,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7301,'抢庄牌九新手房',730,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7302,'抢庄牌九普通房',730,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7303,'抢庄牌九高手房',730,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7304,'抢庄牌九大师房',730,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (7305,'抢庄牌九宗师房',730,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8100,'癞子牛牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8200,'百人骰宝',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8240,'3+2 炸金花',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8250,'十点半',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8260,'踩雷红包',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8270,'疯狂6张牛牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8280,'幸运水果机',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8300,'抢庄 21 点',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8301,'抢庄牛牛新手房',830,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8302,'抢庄牛牛普通房',830,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8303,'抢庄牛牛高手房',830,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8304,'抢庄牛牛大师房',830,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8305,'抢庄牛牛宗师房',830,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8306,'抢庄牛牛至尊房',830,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8400,'看牌抢庄三公',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8500,'欢乐斗牛',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8601,'三公新手房',860,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8602,'三公普通房',860,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8603,'三公高手房',860,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8604,'三公大师房',860,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8605,'三公宗师房',860,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8701,'通比牛牛新手房',870,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8702,'通比牛牛普通房',870,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8703,'通比牛牛高手房',870,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8704,'通比牛牛大师房',870,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8705,'通比牛牛宗师房',870,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8901,'看四张抢庄牛新手房',890,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8902,'看四张抢庄牛普通房',890,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8903,'看四张抢庄牛高手房',890,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8904,'看四张抢庄牛大师房',890,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8905,'看四张抢庄牛宗师房',890,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (8906,'看四张抢庄牛王者房',890,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9001,'龙虎新手房',900,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9002,'龙虎普通房',900,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9003,'龙虎高手房',900,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9004,'龙虎大师房',900,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9101,'百家乐新手房',910,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9102,'百家乐普通房',910,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9103,'百家乐高手房',910,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9104,'百家乐大师房',910,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9301,'百人牛牛新手场',930,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9302,'百人牛牛普通场',930,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9303,'百人牛牛高手场',930,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9304,'百人牛牛大师场',930,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9501,'红黑大战新手场',950,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9502,'红黑大战普通场',950,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9503,'红黑大战高手场',950,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9504,'红黑大战大师场',950,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9505,'红黑大战宗师场',950,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9901,'抢庄五选三新手场',990,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9902,'抢庄五选三普通场',990,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9903,'抢庄五选三高手场',990,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9904,'抢庄五选三大师场',990,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (9905,'抢庄五选三宗师场',990,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (81001,'癞子牛牛新手场',8100,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (81002,'癞子牛牛普通场',8100,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (81003,'癞子牛牛高手场',8100,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (81004,'癞子牛牛大师场',8100,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (81005,'癞子牛牛宗师场',8100,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (81006,'癞子牛牛至尊场',8100,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82001,'百人骰宝新手场',8200,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82002,'百人骰宝普通场',8200,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82003,'百人骰宝高手场',8200,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82004,'百人骰宝大师场',8200,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82401,'3+2炸金花新手场',8240,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82402,'3+2炸金花普通场',8240,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82403,'3+2炸金花高手场',8240,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82404,'3+2炸金花大师场',8240,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82405,'3+2炸金花宗师场',8240,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82406,'3+2炸金花至尊场',8240,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82501,'十点半新手场',8250,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82502,'十点半普通场',8250,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82503,'十点半高手场',8250,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82504,'十点半大师场',8250,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82505,'十点半宗师场',8250,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82506,'十点半至尊场',8250,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82601,'凤鸣朝阳',8260,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82602,'千禧临门',8260,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82603,'花开富贵',8260,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82604,'威凤祥麟',8260,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82605,'金玉满堂',8260,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82701,'疯狂6张牛牛新手场',8270,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82702,'疯狂6张牛牛普通场',8270,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82703,'疯狂6张牛牛高手场',8270,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82704,'疯狂6张牛牛大师场',8270,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82705,'疯狂6张牛牛宗师场',8270,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82706,'疯狂6张牛牛至尊场',8270,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (82801,'幸运水果机',8280,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (83001,'抢庄21点新手场',8300,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (83002,'抢庄21点普通场',8300,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (83003,'抢庄21点高手场',8300,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (83004,'抢庄21点大师场',8300,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (83005,'抢庄21点宗师场',8300,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (84001,'看牌抢庄三公新手场',8400,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (84002,'看牌抢庄三公普通场',8400,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (84003,'看牌抢庄三公高手场',8400,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (84004,'看牌抢庄三公大师场',8400,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (84005,'看牌抢庄三公宗师场',8400,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (85001,'欢乐斗牛新手场',8500,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (85002,'欢乐斗牛普通场',8500,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (85003,'欢乐斗牛高手场',8500,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into `t_chess_game_kind`(`KingID`,`KindName`,`ParentKindID`,`image`,`status`,`info`,`create_by`,`create_time`,`update_by`,`update_time`) values (85004,'欢乐斗牛大师场',8500,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_chess_user` */

DROP TABLE IF EXISTS `t_chess_user`;

CREATE TABLE `t_chess_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(100) DEFAULT NULL COMMENT '平台用户名',
  `chess_account` varchar(100) DEFAULT NULL COMMENT '棋牌用户名',
  `site_id` varchar(50) DEFAULT NULL COMMENT '站点ID',
  `status` tinyint(4) DEFAULT NULL COMMENT '用户状态（1=正常，2=禁用）',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_chess_user` */

/*Table structure for table `t_chess_wallet_correction_record` */

DROP TABLE IF EXISTS `t_chess_wallet_correction_record`;

CREATE TABLE `t_chess_wallet_correction_record` (
  `record_id` varchar(255) NOT NULL COMMENT 'ID',
  `site_id` varchar(50) DEFAULT NULL COMMENT '站点ID',
  `account` varchar(100) DEFAULT NULL COMMENT '用户账号',
  `game_kind_A` varchar(50) DEFAULT NULL COMMENT 'A平台类型',
  `opt_type` varchar(10) DEFAULT NULL COMMENT '类型（IN=转入，OUT=转出）',
  `game_kind_B` varchar(50) DEFAULT NULL COMMENT 'B平台类型',
  `opt_amount` varchar(100) DEFAULT NULL COMMENT '操作金额',
  `balance` decimal(18,2) DEFAULT NULL COMMENT '总余额',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态（1=正在处理中、2=成功、3=失败）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `status_log` varchar(500) DEFAULT NULL COMMENT '状态变更日志',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='冲正记录明细';

/*Data for the table `t_chess_wallet_correction_record` */

/*Table structure for table `t_report_audit_total` */

DROP TABLE IF EXISTS `t_report_audit_total`;

CREATE TABLE `t_report_audit_total` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `site_id` varchar(50) DEFAULT NULL COMMENT '站点ID',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `game_kind` varchar(50) DEFAULT NULL COMMENT '平台类型',
  `game_type` varchar(50) DEFAULT NULL COMMENT '游戏类型',
  `game_room` varchar(50) DEFAULT NULL COMMENT '游戏房间',
  `bet_count` int(11) DEFAULT NULL COMMENT '投注数',
  `bet_amount` decimal(18,2) DEFAULT NULL COMMENT '投注金额',
  `valid_amount` decimal(18,2) DEFAULT NULL COMMENT '有效金额',
  `pay_amount` decimal(18,2) DEFAULT NULL COMMENT '输赢金额',
  `bet_time` date DEFAULT NULL COMMENT '投注时间（日期）',
  `target_id` varchar(100) DEFAULT NULL COMMENT '投注起始时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `info` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_bet_time` (`bet_time`,`site_id`,`username`,`game_kind`,`game_type`,`game_room`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审计表';

/*Data for the table `t_report_audit_total` */

/*Table structure for table `t_report_day_user` */

DROP TABLE IF EXISTS `t_report_day_user`;

CREATE TABLE `t_report_day_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `site_id` varchar(50) DEFAULT NULL COMMENT '站点ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `game_kind` varchar(50) DEFAULT NULL COMMENT '平台类型',
  `bet_counts` int(11) DEFAULT NULL COMMENT '投注总数',
  `bet_amounts` decimal(18,2) DEFAULT NULL COMMENT '投注总金额',
  `valid_amounts` decimal(18,2) DEFAULT NULL COMMENT '有效总金额',
  `pay_amounts` decimal(18,2) DEFAULT NULL COMMENT '输赢总金额',
  `bet_time` date DEFAULT NULL COMMENT '投注时间，按天统计',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_username` (`username`,`game_kind`,`bet_time`,`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表总计表';

/*Data for the table `t_report_day_user` */

/* Procedure structure for procedure `insert_total_report` */

/*!50003 DROP PROCEDURE IF EXISTS  `insert_total_report` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `insert_total_report`(IN ds_type VARCHAR(100),IN startId VARCHAR(100),IN endId VARCHAR(100),OUT result VARCHAR(2000))
label: BEGIN
   DECLARE v_t_report_day_user VARCHAR(2000);
   DECLARE cnt_2 VARCHAR(2000);
   DECLARE cnt_3 VARCHAR(2000);
   DECLARE cnt_4 VARCHAR(2000);
   SET @b_1 = 0;
   SET @b_2 = 0;
   SET @b_3 = 0;
   SET @c_1 = 0;
   SET @c_2 = 0;
   SET @c_3 = 0;
   
   SET @Param=CONCAT("call insert_total_report('",ds_type,"','",startId,"','",endId,"',@result);SELECT @result;");
   
   SET @cnt_2=CONCAT("SELECT COALESCE(SUM(audit.bet_count),0) AS COUNT,COALESCE(SUM(audit.bet_amount),0) AS bet_amount,COALESCE(SUM(audit.pay_amount),0) AS pay_amount INTO @b_1,@b_2,@b_3 
	FROM t_report_audit_total audit 
	WHERE audit.game_kind in ('",ds_type,"') AND audit.bet_time>=DATE_FORMAT('",startId,"','%Y-%m-%d') AND audit.bet_time<=DATE_FORMAT('",endId,"','%Y-%m-%d')");
   PREPARE cnt_2 FROM @cnt_2; 
   EXECUTE cnt_2;
   DEALLOCATE PREPARE cnt_2;
   SET result=CONCAT("(1)audit表数据count=",@b_1,",bet_amount=",@b_2,",wins=",@b_3,",水平线=【",startId,"-",endId,"】");
	
   SET @cnt_3=CONCAT("SELECT COALESCE(SUM(ds_report.bet_counts),0) AS COUNT,COALESCE(SUM(ds_report.bet_amounts),0) AS bet_amount,COALESCE(SUM(ds_report.pay_amounts),0) AS pay_amount INTO @c_1,@c_2,@c_3 
	FROM t_report_day_user ds_report 
	WHERE ds_report.game_kind in ('",ds_type,"') AND ds_report.bet_time>=DATE_FORMAT('",startId,"','%Y-%m-%d') AND ds_report.bet_time<=DATE_FORMAT('",endId,"','%Y-%m-%d')");
   PREPARE cnt_3 FROM @cnt_3; 
   EXECUTE cnt_3;
   DEALLOCATE PREPARE cnt_3;
   SET result=CONCAT(result,"\r\n(2)t_report_day_user表数据count=",@c_1,",bet_amount=",@c_2,",wins=",@c_3);
   
   IF (@b_1!=@c_1 OR @b_2!=@c_2 OR @b_3!=@c_3) THEN
	SET v_t_report_day_user=CONCAT("INSERT INTO t_report_day_user(username,bet_counts,bet_amounts,pay_amounts,valid_amounts,site_id,game_kind,
		bet_time,create_time) 
		SELECT audit.username,SUM(audit.bet_count) AS bet_counts,SUM(audit.bet_amount) AS bet_amounts,SUM(audit.pay_amount) AS pay_amounts,SUM(audit.valid_amount) AS valid_amounts,
		audit.site_id AS site_id,
		audit.game_kind AS game_kind,
		audit.bet_time, NOW() AS create_time
		FROM t_report_audit_total audit 
		WHERE audit.game_kind in ('",ds_type,"') AND audit.bet_time>=DATE_FORMAT('",startId,"','%Y-%m-%d') AND audit.bet_time<=DATE_FORMAT('",endId,"','%Y-%m-%d') 
		GROUP BY audit.username,audit.game_kind,audit.site_id
		ORDER BY bet_time
		ON DUPLICATE KEY
		UPDATE bet_counts= VALUES(bet_counts),
			bet_amounts= VALUES(bet_amounts),
			pay_amounts= VALUES(pay_amounts),
			valid_amounts= VALUES(valid_amounts),
			bet_time= VALUES(bet_time),
			game_kind= VALUES(game_kind),
			update_time = NOW()");
	
	SET @v_t_report_day_user=v_t_report_day_user;
	PREPARE s4 FROM @v_t_report_day_user; 
	EXECUTE s4;
	DEALLOCATE PREPARE s4;
	SET result=CONCAT(result,"\r\n(3)audit表与t_report_day_user表不匹配,统计表插入或者更新操作完成");
   END IF;
   
 -- CALL admin_log(siteId,@Param,result);
 
END */$$
DELIMITER ;

/* Procedure structure for procedure `t_chess_audit_report` */

/*!50003 DROP PROCEDURE IF EXISTS  `t_chess_audit_report` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `t_chess_audit_report`(IN agentId INT,IN ds_type VARCHAR(100),IN startId VARCHAR(100),IN endId VARCHAR(100),OUT result VARCHAR(2000))
label: BEGIN
   DECLARE v_audit_sql VARCHAR(2000);
   DECLARE cnt_0 VARCHAR(2000);
   DECLARE cnt_1 VARCHAR(2000);
   DECLARE cnt_2 VARCHAR(2000);
   DECLARE info VARCHAR(2000);
   SET @a_1 = 0;
   SET @a_2 = 0;
   SET @a_3 = 0;
   SET @a_4 = 0;
   SET @b_1 = 0;
   SET @b_2 = 0;
   SET @b_3 = 0;
   SET @a_4 = 0;
   SET @Param=CONCAT("call t_chess_audit_report(",agentId,",'",ds_type,"','",startId,"','",endId,"',@result);SELECT @result;");
   
   SET result=CONCAT("(1)平台('",ds_type,"'),开始审计报表统计,水平线=【",startId,"-",endId,"】");
   SET @cnt_1=CONCAT("SELECT COALESCE(COUNT(*),0) AS COUNT,COALESCE(sum(lc.AllBet),0) AS bet_amount,COALESCE(SUM(lc.CellScore),0) AS valid_amount,COALESCE(SUM(lc.Profit),0) AS pay_amount INTO @a_1,@a_2,@a_3,@a_4
	FROM t_chess_game lc 
	WHERE lc.ChannelID=",agentId," AND lc.GameEndTime>='",startId,"' AND lc.GameEndTime<='",endId,"'");
   PREPARE cnt_1 FROM @cnt_1;
   EXECUTE cnt_1;
   DEALLOCATE PREPARE cnt_1;
   
   SET result=CONCAT(result," \r\n(2)source表数据count=",@a_1,",bet_amount=",@a_2,",valid_amount=",@a_3,",wins=",@a_4);
   
   SET @cnt_2=CONCAT("SELECT COALESCE(SUM(bet_count),0) AS COUNT,COALESCE(SUM(audit.bet_amount),0) AS bet_amount,COALESCE(SUM(audit.valid_amount),0) AS valid_amount,COALESCE(SUM(audit.pay_amount),0) AS pay_amount INTO @b_1,@b_2,@b_3,@b_4 
	FROM t_report_audit_total audit 
	WHERE audit.game_kind in ('",ds_type,"') AND audit.target_id>='",startId,"' AND audit.target_id<='",endId,"'");
   PREPARE cnt_2 FROM @cnt_2; 
   EXECUTE cnt_2;
   DEALLOCATE PREPARE cnt_2;
   SET result=CONCAT(result," \r\n(3)audit表数据count=",@b_1,",bet_amount=",@b_2,",valid_amount=",@a_3,",wins=",@b_4);
  
   IF (@a_1!=@b_1 OR @a_2!=@b_2 OR @a_3!=@b_3 OR @a_4!=@b_4) THEN
	SET v_audit_sql=CONCAT("INSERT INTO t_report_audit_total(site_id,game_kind,game_type,game_room,target_id,username,bet_time,bet_count,bet_amount,valid_amount,pay_amount,create_time) 
			SELECT lc.site_id AS site_id,'",ds_type,"' AS game_kind,lc.KindID AS game_type,lc.ServerID AS game_room,MIN(lc.GameEndTime) AS target_id,
			lc.Account AS username,DATE_FORMAT(lc.GameEndTime,'%Y-%m-%d') AS bet_time,
			COALESCE(COUNT(*),0) AS bet_count,
			SUM(lc.AllBet) AS bet_amount, 
			SUM(lc.CellScore) AS valid_amount, 
			SUM(lc.Profit) AS pay_amount,NOW()
			FROM t_chess_game lc
			WHERE DATE_FORMAT(lc.GameEndTime,'%Y-%m-%d')>=DATE_FORMAT('",startId,"','%Y-%m-%d') 
			AND DATE_FORMAT(lc.GameEndTime,'%Y-%m-%d')<=DATE_FORMAT('",endId,"','%Y-%m-%d') AND lc.ChannelID=",agentId,"
			GROUP BY lc.ServerID,lc.Account,lc.site_id
			ORDER BY lc.GameEndTime
			ON DUPLICATE KEY
			UPDATE game_kind = VALUES(game_kind),target_id = VALUES(target_id),
			bet_count = VALUES(bet_count),bet_time = VALUES(bet_time),username = VALUES(username),
			pay_amount = VALUES(pay_amount),bet_amount = VALUES(bet_amount),valid_amount = VALUES(valid_amount),
			game_type = VALUES(game_type),update_time = NOW()");
	SET @SQL_audit=v_audit_sql;
	PREPARE s1 FROM @SQL_audit; 
	EXECUTE s1; 
	DEALLOCATE PREPARE s1;
	SET result=CONCAT(result," \r\n(4)source表与audit表不匹配,audit表插入或者更新操作完成 ");
   END IF;
   -- CALL admin_log(siteId,@Param,result);
   CALL insert_total_report(ds_type,startId,endId,info);
   SET result=CONCAT(result,"\r",info);
  
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
