/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.33-community : Database - softwarestation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`softwarestation` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `softwarestation`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL COMMENT '金额',
  `accType` int(11) DEFAULT NULL COMMENT '账目类型',
  `explanation` varchar(200) DEFAULT NULL COMMENT '备注说明',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

/*Data for the table `account` */

insert  into `account`(`id`,`price`,`accType`,`explanation`,`createTime`) values (1,12,3,'充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值充值','2010-06-21 15:06:03'),(3,233,2,'2221','2010-06-21 11:17:34'),(4,10,6,'测试测试','2010-06-21 14:35:25');

/*Table structure for table `accounttype` */

DROP TABLE IF EXISTS `accounttype`;

CREATE TABLE `accounttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '类型名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

/*Data for the table `accounttype` */

insert  into `accounttype`(`id`,`name`,`createTime`) values (2,'UC推广','2010-06-21 10:41:08'),(3,'大头推广','2010-06-21 10:41:20'),(4,'棒棒3','2010-06-21 10:52:29'),(5,'qq','2010-06-21 11:29:54'),(6,'测试一下','2010-06-21 13:36:48');

/*Table structure for table `activelog` */

DROP TABLE IF EXISTS `activelog`;

CREATE TABLE `activelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softwareId` int(11) DEFAULT NULL COMMENT '查看的软件',
  `price` double DEFAULT NULL COMMENT '收入单价(单位元)',
  `number` int(11) DEFAULT NULL COMMENT '激活数',
  `activeTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

/*Data for the table `activelog` */

insert  into `activelog`(`id`,`softwareId`,`price`,`number`,`activeTime`) values (2,34,11,5,'2010-06-21 00:00:00'),(3,NULL,1,4,'2010-06-21 00:00:00'),(4,30,0.3,0,'2010-06-22 00:00:00'),(6,31,11,0,'2010-06-21 00:00:00'),(7,34,1,5,'2010-06-22 00:00:00');

/*Table structure for table `clicklog` */

DROP TABLE IF EXISTS `clicklog`;

CREATE TABLE `clicklog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softwareId` int(11) DEFAULT NULL COMMENT '查看的软件',
  `clickTime` datetime DEFAULT NULL COMMENT '查看时间',
  `number` int(11) DEFAULT NULL COMMENT '数量（查询出来的）每一次点击，数量+1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `clicklog` */

insert  into `clicklog`(`id`,`softwareId`,`clickTime`,`number`) values (1,34,'2010-06-21 00:00:00',12),(2,32,'2010-06-21 00:00:00',23),(3,34,'2010-06-22 00:00:00',10);

/*Table structure for table `downloadlog` */

DROP TABLE IF EXISTS `downloadlog`;

CREATE TABLE `downloadlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softwareId` int(11) DEFAULT NULL COMMENT '下载的软件',
  `downloadTime` datetime DEFAULT NULL COMMENT '下载时间',
  `downloadPrice` double DEFAULT NULL COMMENT '下载时的单价',
  `number` int(11) DEFAULT NULL COMMENT '数量（查询出来的）每一次下载，数量+1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `downloadlog` */

insert  into `downloadlog`(`id`,`softwareId`,`downloadTime`,`downloadPrice`,`number`) values (1,34,'2010-06-21 00:00:00',NULL,5),(2,34,'2010-06-22 00:00:00',NULL,8),(3,NULL,'2010-06-21 00:00:00',NULL,7);

/*Table structure for table `extension` */

DROP TABLE IF EXISTS `extension`;

CREATE TABLE `extension` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '文件扩展名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312;

/*Data for the table `extension` */

insert  into `extension`(`id`,`name`) values (1,'.jar'),(2,'.jad'),(4,'.nth'),(5,'.ota'),(6,'.apk'),(7,'.exe'),(8,'.sis');

/*Table structure for table `extensionos` */

DROP TABLE IF EXISTS `extensionos`;

CREATE TABLE `extensionos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneOs` int(11) DEFAULT NULL COMMENT '平台',
  `extension` int(11) DEFAULT NULL COMMENT '扩展名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

/*Data for the table `extensionos` */

insert  into `extensionos`(`id`,`phoneOs`,`extension`) values (1,6,2),(2,6,1),(3,7,6),(5,5,6),(6,2,7),(7,4,8);

/*Table structure for table `phonebrand` */

DROP TABLE IF EXISTS `phonebrand`;

CREATE TABLE `phonebrand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL COMMENT '名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `phonebrand` */

insert  into `phonebrand`(`id`,`NAME`,`createTime`) values (1,'诺基亚','2010-06-13 10:17:34'),(2,'三星','2010-06-13 10:17:39'),(3,'波导','2010-06-13 10:17:45');

/*Table structure for table `phonemodel` */

DROP TABLE IF EXISTS `phonemodel`;

CREATE TABLE `phonemodel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL COMMENT '名称',
  `seriesId` int(11) DEFAULT NULL COMMENT '系列id',
  `brandId` int(11) DEFAULT NULL COMMENT '品牌id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;

/*Data for the table `phonemodel` */

insert  into `phonemodel`(`id`,`NAME`,`seriesId`,`brandId`,`createTime`) values (3,'5632',NULL,3,'2010-06-13 11:39:35'),(4,'N97',1,1,'2010-06-21 16:42:30'),(5,'M95',1,1,'2010-06-21 16:42:30');

/*Table structure for table `phoneos` */

DROP TABLE IF EXISTS `phoneos`;

CREATE TABLE `phoneos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL COMMENT '名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

/*Data for the table `phoneos` */

insert  into `phoneos`(`id`,`NAME`,`createTime`) values (2,'windows mobile','2010-06-23 16:20:45'),(3,'S60三版','2010-06-13 10:49:33'),(4,'S60二版','2010-06-23 16:47:47'),(5,'S60五版','2010-06-23 15:38:36'),(6,'JAVA','2010-06-23 15:34:42'),(7,'Android','2010-06-23 15:38:12');

/*Table structure for table `phoneseries` */

DROP TABLE IF EXISTS `phoneseries`;

CREATE TABLE `phoneseries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL COMMENT '名称',
  `osId` int(11) DEFAULT NULL COMMENT '操作系统',
  `brandId` int(11) DEFAULT NULL COMMENT '品牌id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gb2312;

/*Data for the table `phoneseries` */

insert  into `phoneseries`(`id`,`NAME`,`osId`,`brandId`,`createTime`) values (1,'N97,M95',3,1,'2010-06-21 16:42:30');

/*Table structure for table `software` */

DROP TABLE IF EXISTS `software`;

CREATE TABLE `software` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softwareInfoId` int(11) DEFAULT NULL COMMENT '软件信息id',
  `click` int(11) DEFAULT NULL COMMENT '点击数',
  `active` int(11) DEFAULT NULL COMMENT '激活数',
  `download` int(11) DEFAULT NULL COMMENT '下载次数',
  `downloadPath` varchar(200) DEFAULT NULL COMMENT '下载地址',
  `createTime` datetime DEFAULT NULL COMMENT '更新时间',
  `price` double DEFAULT NULL COMMENT '收入单价(单位元)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=gb2312;

/*Data for the table `software` */

insert  into `software`(`id`,`softwareInfoId`,`click`,`active`,`download`,`downloadPath`,`createTime`,`price`) values (1,5,0,NULL,0,'[yulezu]1276421106421.gif','2010-06-13 17:25:06',0),(2,5,0,NULL,0,'[yulezu]1276421106421.gif','2010-06-13 17:25:06',0),(3,6,0,NULL,0,'[yulezu]1276421390812.gif','2010-06-13 17:29:50',0),(4,6,0,NULL,0,'[yulezu]1276421390843.gif','2010-06-13 17:29:50',0),(5,6,0,NULL,0,'[yulezu]1276421390859.gif','2010-06-13 17:29:50',0),(6,9,0,NULL,0,'[yulezu]1276736544328.gif','2010-06-17 09:02:24',0),(7,9,0,NULL,0,'[yulezu]1276736558921.gif','2010-06-17 09:02:38',0),(8,11,0,NULL,0,'[yulezu]1276736935828.gif','2010-06-17 09:08:55',0),(9,12,0,NULL,0,'[yulezu]1276737419923.gif','2010-06-17 09:16:59',0),(10,12,0,NULL,0,'jsp-api.jar','2010-06-17 09:16:59',0),(11,12,0,NULL,0,'[yulezu]1276737419938.gif','2010-06-17 09:16:59',0),(41,23,0,NULL,0,'antlr-2.7.6.jar','2010-06-17 15:43:19',0),(49,28,0,NULL,0,'[yulezu]1276821765312.sql','2010-06-18 08:42:45',0),(50,28,0,NULL,1,'[yulezu]1276821765343.jsp','2010-06-18 08:42:45',0),(51,5,0,NULL,1,'[yulezu]1276822717828.swf','2010-06-18 08:58:37',0),(52,30,0,NULL,1,'[yulezu]1276823776718.jar','2010-06-18 09:16:16',0),(55,32,2,NULL,1,'[yulezu]1276847170500.java','2010-06-18 15:46:10',0),(72,32,1,NULL,0,'[yulezu]1276910597718.xml','2010-06-19 09:22:34',0),(73,32,1,NULL,1,'[yulezu]1276910622015.fne','2010-06-19 09:23:17',0),(83,34,2,NULL,1,'PK176208.jar','2010-06-19 10:17:19',0),(84,34,0,NULL,0,'PK176220.jar','2010-06-19 10:17:19',0),(85,34,5,NULL,1,'PK208208.jar','2010-06-19 10:17:19',0),(86,34,4,NULL,1,'PK240320.jar','2010-06-19 10:17:19',0),(90,34,2,NULL,1,'PK240320.jar','2010-06-19 10:32:57',0),(91,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,35,0,NULL,0,'dom4j-1.6.1.jar','2010-06-23 13:58:11',NULL),(93,35,0,NULL,0,'HandcentSmsV3.0.4.apk','2010-06-23 13:59:32',NULL),(94,35,0,NULL,0,'myisampack.exe','2010-06-23 16:22:08',NULL);

/*Table structure for table `softwareinfo` */

DROP TABLE IF EXISTS `softwareinfo`;

CREATE TABLE `softwareinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '分类',
  `description` varchar(2000) DEFAULT NULL COMMENT '简介/描述',
  `imgPath` varchar(200) DEFAULT NULL COMMENT '截图地址',
  `click` int(11) DEFAULT NULL COMMENT '点击数',
  `safety` varchar(20) DEFAULT NULL COMMENT '安全',
  `traffic` double DEFAULT NULL COMMENT '资费',
  `createTime` datetime DEFAULT NULL COMMENT '更新时间',
  `prompt` varchar(200) DEFAULT NULL COMMENT '提示',
  `recommend` int(11) DEFAULT NULL COMMENT '是否推荐0.否，1.是',
  `isShow` int(11) DEFAULT NULL COMMENT '是否显示0.否，1.是',
  `plusFine` int(11) DEFAULT NULL COMMENT '是否加精0.否，1.是',
  `promotionWay` int(1) DEFAULT NULL COMMENT '推广方式0.提成，1.免费',
  `number` int(11) DEFAULT NULL COMMENT '软件个数',
  `isRename` int(11) DEFAULT NULL COMMENT '是否允许改名',
  `active` int(11) DEFAULT NULL COMMENT '查询后的总激活数',
  `download` int(11) DEFAULT NULL COMMENT '查询后的总下载次数',
  `allprice` double(10,2) DEFAULT NULL COMMENT '查询后的总金额',
  `producer` varchar(200) DEFAULT NULL COMMENT '生产者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=gb2312;

/*Data for the table `softwareinfo` */

insert  into `softwareinfo`(`id`,`name`,`type`,`description`,`imgPath`,`click`,`safety`,`traffic`,`createTime`,`prompt`,`recommend`,`isShow`,`plusFine`,`promotionWay`,`number`,`isRename`,`active`,`download`,`allprice`,`producer`) values (4,'测试',5,'测试测试','[yulezu]1276419334640.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-22 00:00:00','无提示',1,1,1,0,0,0,NULL,NULL,NULL,NULL),(5,'测试2',5,'测试测试测试','[yulezu]1276421106421.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-13 17:25:06','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(6,'ddd',5,'sdfsdf','[yulezu]1276421390750.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-13 17:29:50','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(7,'ttt',5,'gtght','[yulezu]1276734235406.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-17 08:23:55','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(8,'4444',5,'frgfg','[yulezu]1276734439531.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-17 08:27:19','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(9,'eeec',5,'dsdsf','[yulezu]1276736506296.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-17 09:01:46','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(10,'xcvxc',5,'cvxcv','[yulezu]1276736706953.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-17 09:05:06','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(11,'xzcx',5,'zxczx','[yulezu]1276736935765.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-17 09:08:55','无提示',1,1,1,1,0,NULL,NULL,NULL,NULL,NULL),(12,'sdf',5,'sdfsd','[yulezu]1276737419829.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-22 00:00:00','无提示',1,1,1,0,3,0,NULL,NULL,NULL,NULL),(13,'xzxczxc',5,'zxczxczx','[yulezu]1276737637626.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-21 16:23:33','无提示',1,1,1,0,0,1,NULL,NULL,NULL,NULL),(23,'test',5,'test','[yulezu]1276760599421.jpg',0,'无病毒/无暗扣/无插件',0,'2010-06-17 15:43:19','无提示',0,1,0,0,1,0,NULL,NULL,NULL,NULL),(25,'测试一下',5,'测试一下','[yulezu]1276820963484.jpg',0,'无病毒/无暗扣/无插件',0,'2010-06-21 17:30:59','无提示',1,1,1,0,0,1,NULL,NULL,NULL,NULL),(27,'随碟附送的',5,'sdfdsfsdsd','[yulezu]1276821624234.jpg',0,'无病毒/无暗扣/无插件',0,'2010-06-18 08:40:24','无提示',1,1,1,0,2,1,NULL,NULL,NULL,NULL),(28,'sdfsdfsd',5,'fsdfsd','[yulezu]1276821765218.jpg',0,'无病毒/无暗扣/无插件',0,'2010-06-18 08:42:45','无提示',1,1,1,0,2,1,NULL,NULL,NULL,NULL),(30,'我来测试修改软件信息',5,'我是来测试修改软件信息，没有其他的意思','[yulezu]1276823776640.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-18 09:16:16','需要签证后才能安装',1,1,1,0,1,0,NULL,NULL,NULL,NULL),(31,'我来测试增加后再修改',1,'我来测试增加','[yulezu]1276843279343.JPG',0,'无病毒/无暗扣/无插件',0,'2010-06-19 09:26:27','无提示',1,1,1,0,2,1,NULL,NULL,NULL,NULL),(32,'该测试啦',1,'该测试啦','[yulezu]1276847170406.JPG',0,'无病毒/无暗扣/无插件',0,'2010-06-23 13:47:21','无提示',1,1,1,0,3,NULL,NULL,NULL,NULL,'本站自主开发'),(34,'111',2,'111','[yulezu]1276913838937.gif',12,'无病毒/无暗扣/无插件',1,'2010-06-19 10:33:52','需要签证后才能安装',0,0,0,0,5,0,NULL,NULL,NULL,NULL),(35,'聊天程序',3,'sssss','[yulezu]1277272691609.bmp',0,'无病毒/无暗扣/无插件',0,'2010-06-23 16:22:08','无提示',1,1,1,0,3,0,NULL,NULL,NULL,'自主开发'),(36,'ddd',3,'dfdfd','[yulezu]1277284649187.gif',0,'无病毒/无暗扣/无插件',0,'2010-06-23 17:17:29','无提示',1,1,1,1,NULL,0,NULL,NULL,NULL,'sss');

/*Table structure for table `softwareos` */

DROP TABLE IF EXISTS `softwareos`;

CREATE TABLE `softwareos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `softwareId` int(11) DEFAULT NULL COMMENT '软件',
  `phoneOsId` int(11) DEFAULT NULL COMMENT '支持平台',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=457 DEFAULT CHARSET=gb2312;

/*Data for the table `softwareos` */

insert  into `softwareos`(`id`,`softwareId`,`phoneOsId`) values (3,22,2),(4,22,3),(5,23,2),(6,23,3),(7,24,2),(8,24,3),(9,25,2),(10,25,3),(11,33,7),(12,33,4),(13,34,7),(14,34,4),(15,35,6),(16,35,3),(17,36,6),(18,36,3),(19,37,7),(20,37,6),(21,37,4),(22,38,7),(23,38,6),(24,39,7),(25,39,6),(26,40,6),(27,41,7),(28,41,6),(34,47,7),(35,47,3),(36,48,6),(37,48,3),(38,49,6),(39,49,5),(40,50,6),(41,50,5),(42,51,7),(43,51,6),(44,52,6),(294,83,7),(295,83,6),(296,83,5),(297,83,4),(298,83,3),(299,83,2),(300,84,7),(301,84,6),(302,84,5),(303,84,4),(304,84,3),(305,84,2),(306,85,7),(307,85,6),(308,85,5),(309,85,4),(310,85,3),(311,85,2),(312,86,7),(313,86,6),(314,86,5),(315,86,4),(316,86,3),(317,86,2),(318,90,7),(319,90,6),(320,90,5),(321,90,4),(322,90,3),(323,90,2),(372,88,7),(373,88,6),(374,88,5),(375,88,4),(376,88,3),(377,88,2),(378,89,7),(379,89,6),(380,89,5),(381,89,4),(382,89,3),(383,89,2),(384,91,7),(385,91,6),(386,91,5),(387,91,4),(388,91,3),(389,91,2),(426,9,4),(427,10,7),(428,11,7),(429,91,2),(430,91,3),(431,55,7),(432,72,6),(433,73,6),(434,73,5),(454,92,6),(455,93,7),(456,94,2);

/*Table structure for table `softwaretype` */

DROP TABLE IF EXISTS `softwaretype`;

CREATE TABLE `softwaretype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL COMMENT '软件分类名称',
  `zindex` int(11) DEFAULT NULL COMMENT '序号（默认为id号）',
  `isShow` int(11) DEFAULT NULL COMMENT '是否显示0.否，1.是',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `isWrap` int(11) DEFAULT NULL COMMENT '是否换行0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;

/*Data for the table `softwaretype` */

insert  into `softwaretype`(`id`,`NAME`,`zindex`,`isShow`,`createTime`,`isWrap`) values (1,'系统',1,1,'2010-06-13 10:22:07',0),(2,'常用',2,1,'2010-06-13 10:22:50',0),(3,'聊天',3,1,'2010-06-13 10:22:59',0),(5,'通讯',4,1,'2010-06-17 15:52:14',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
