/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.7.26-log : Database - user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `user`;

/*Table structure for table `t_index_notice` */

DROP TABLE IF EXISTS `t_index_notice`;

CREATE TABLE `t_index_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `context` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `type` varchar(50) DEFAULT NULL COMMENT '公告、消息类型',
  `status` tinyint(3) DEFAULT '0' COMMENT '0为启用，1为停用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='消息通知表';

/*Data for the table `t_index_notice` */

insert  into `t_index_notice`(`id`,`title`,`context`,`remark`,`type`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'消息一','MDT秉持【安全、稳健、服务】的经营理念，以【为客户节省经营成本】为己任，通过技术手段全面提升客户留存率和转换率，为商户创造更大价值。多年来深受众多客户及玩家的信任，现诚邀与您携手并进，共赢未来！',NULL,'index',0,'2019-10-28 11:12:28',NULL,NULL,NULL);
insert  into `t_index_notice`(`id`,`title`,`context`,`remark`,`type`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (2,'消息二','真人视讯、棋牌视讯欢迎体验',NULL,'index',0,'2019-10-28 11:12:31',NULL,NULL,NULL);

/*Table structure for table `t_index_swiper` */

DROP TABLE IF EXISTS `t_index_swiper`;

CREATE TABLE `t_index_swiper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `img_src` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `img_link` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图片链接地址',
  `type` varchar(50) DEFAULT NULL,
  `order_by` tinyint(3) DEFAULT NULL COMMENT '排序字段',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` tinyint(3) DEFAULT '0' COMMENT '0为启用，1为停用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='首页轮播表';

/*Data for the table `t_index_swiper` */

insert  into `t_index_swiper`(`id`,`title`,`img_src`,`img_link`,`type`,`order_by`,`remark`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'标题一','http://47.52.140.84:801/appKey00001/index/swiper/0b539b4b77e2ef83f1ef1b07baa8b58b.png',NULL,'index',4,NULL,0,'2019-10-11 14:19:37',NULL,NULL,NULL);
insert  into `t_index_swiper`(`id`,`title`,`img_src`,`img_link`,`type`,`order_by`,`remark`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (2,'标题二','http://47.52.140.84:801/appKey00001/index/swiper/562c4d0d306e7837e53701839e0cd3da.png',NULL,'index',2,NULL,0,'2019-10-11 14:19:37',NULL,NULL,NULL);
insert  into `t_index_swiper`(`id`,`title`,`img_src`,`img_link`,`type`,`order_by`,`remark`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (3,'标题三','http://47.52.140.84:801/appKey00001/index/swiper/75c1528a480545fe9cb2e6bb4cc69537.png',NULL,'index',3,NULL,0,'2019-10-11 14:19:37',NULL,NULL,NULL);
insert  into `t_index_swiper`(`id`,`title`,`img_src`,`img_link`,`type`,`order_by`,`remark`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (4,'标题四','http://47.52.140.84:801/appKey00001/index/swiper/880c5b30b9ca354cfa880f3e5a0c8261.png',NULL,'index',1,NULL,0,'2019-10-11 14:19:37',NULL,NULL,NULL);

/*Table structure for table `t_user_info` */

DROP TABLE IF EXISTS `t_user_info`;

CREATE TABLE `t_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户登录名',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `real_name` varchar(50) NOT NULL COMMENT '真实名称',
  `avatar` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `phone_num` varchar(50) NOT NULL COMMENT '用户手机',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `invite` varchar(50) DEFAULT NULL COMMENT '邀请人',
  `introduction` varchar(500) DEFAULT NULL COMMENT '个人签名',
  `status` tinyint(3) DEFAULT '0' COMMENT '0为启用，1为停用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员表';

/*Data for the table `t_user_info` */

insert  into `t_user_info`(`id`,`username`,`password`,`real_name`,`avatar`,`phone_num`,`email`,`wechat`,`invite`,`introduction`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'eason','e10adc3949ba59abbe56e057f20f883e','eason',NULL,'18672117311','myeasonhost@gmail.com','ludan','1234',NULL,0,'2019-10-09 12:25:42',NULL,NULL,NULL);
insert  into `t_user_info`(`id`,`username`,`password`,`real_name`,`avatar`,`phone_num`,`email`,`wechat`,`invite`,`introduction`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (2,'geye','e10adc3949ba59abbe56e057f20f883e','eason',NULL,'18672111111','11@qq.com','11111','eason',NULL,0,'2019-10-09 16:59:36',NULL,NULL,NULL);
insert  into `t_user_info`(`id`,`username`,`password`,`real_name`,`avatar`,`phone_num`,`email`,`wechat`,`invite`,`introduction`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (3,'eason3','e10adc3949ba59abbe56e057f20f883e','ludan',NULL,'18300000000','11@qq.com','111111','eason',NULL,0,'2019-10-09 17:30:53',NULL,NULL,NULL);
insert  into `t_user_info`(`id`,`username`,`password`,`real_name`,`avatar`,`phone_num`,`email`,`wechat`,`invite`,`introduction`,`status`,`create_time`,`update_time`,`create_by`,`update_by`) values (4,'xiaoming','e10adc3949ba59abbe56e057f20f883e','猫猫',NULL,'17709219599','46016501@qq.com','46016501','123',NULL,0,'2019-11-02 15:42:00',NULL,NULL,NULL);

/*Table structure for table `user_setting` */

DROP TABLE IF EXISTS `user_setting`;

CREATE TABLE `user_setting` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '主键',
  `VALUE` tinyint(4) NOT NULL COMMENT '设置值',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(50) DEFAULT NULL COMMENT '更新人',
  `IS_DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：未删除  1：已删除',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户设置表';

/*Data for the table `user_setting` */

/*Table structure for table `user_token_info` */

DROP TABLE IF EXISTS `user_token_info`;

CREATE TABLE `user_token_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '主键',
  `TOKEN` char(32) NOT NULL COMMENT 'token',
  `PHONE` varchar(40) NOT NULL COMMENT '用户电话',
  `EXPIRY_DATE` datetime DEFAULT NULL COMMENT '截止日期',
  `IS_DELETED` int(11) DEFAULT '0' COMMENT '删除标识。1：删除 0：未删除',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间(最后登录时间)',
  `CREATE_BY` int(11) DEFAULT NULL COMMENT '创建者id',
  `UPDATE_BY` int(11) DEFAULT NULL COMMENT '更新者id',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_52` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户TOKEN表';

/*Data for the table `user_token_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
