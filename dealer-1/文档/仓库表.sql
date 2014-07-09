/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.0.95-log : Database - yindudb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yindudb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yindudb`;

/*Table structure for table `rep` */

DROP TABLE IF EXISTS `rep`;

CREATE TABLE `rep` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '商品库存ID',
  `SKUID` bigint(20) default NULL COMMENT 'SKUID',
  `REPOSITORY_CODE` bigint(10) default NULL COMMENT '仓库编码',
  `COUNT` int(11) default NULL COMMENT '库存数量',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='SKU库存量';

/*Data for the table `rep` */

insert  into `rep`(`ID`,`SKUID`,`REPOSITORY_CODE`,`COUNT`) values (1,5,0,2);

/*Table structure for table `rep_opt_record` */

DROP TABLE IF EXISTS `rep_opt_record`;

CREATE TABLE `rep_opt_record` (
  `SERIAL_NO` bigint(20) NOT NULL auto_increment COMMENT '库存量操作流水ID',
  `INPUT_REP_CODE` bigint(20) default NULL COMMENT '入库仓库编码',
  `OUTPUT_REP_CODE` bigint(20) default NULL COMMENT '出库仓库编码',
  `SKUID` bigint(20) default NULL COMMENT 'SKUID',
  `OPT_TYPE` varchar(2) default NULL COMMENT '操作类型:01. 进货入库 02. 退货给供货商  03. 销售给普通用户 04. 销售给代理商  05.普通用户退货入平台库  06 代理商退货入平台库 07 代理商销售给普通用户 08 普通用户退货入代理商库 09 仓库间调拨',
  `COUNT` int(11) default NULL COMMENT '操作数量',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '操作日期',
  `OPT_ID` bigint(20) default NULL COMMENT '操作员',
  PRIMARY KEY  (`SERIAL_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='SKU库存操作记录表';

/*Data for the table `rep_opt_record` */

insert  into `rep_opt_record`(`SERIAL_NO`,`INPUT_REP_CODE`,`OUTPUT_REP_CODE`,`SKUID`,`OPT_TYPE`,`COUNT`,`CREATE_TIME`,`OPT_ID`) values (1,0,NULL,5,'01',2,'2014-06-30 17:24:46',1);

/*Table structure for table `rep_sell_detail` */

DROP TABLE IF EXISTS `rep_sell_detail`;

CREATE TABLE `rep_sell_detail` (
  `RID` bigint(20) NOT NULL auto_increment COMMENT '详情ID',
  `ENTITY_ID` bigint(20) default NULL COMMENT '实体ID',
  `REP_OPT_ID` bigint(20) default NULL COMMENT '库存量操作记录ID',
  `ORDER_ID` bigint(20) default NULL COMMENT '订单ID',
  `OPT_TYPE` varchar(2) default NULL COMMENT '操作类型： 03. 销售给普通用户 04. 销售给代理商  05.普通用户退货入平台库  06 代理商退货入平台库 07 代理商销售给普通用户 08 普通用户退货入代理商库',
  `OPTER_ID` bigint(20) default NULL COMMENT '操作员ID',
  `OPT_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '操作时间',
  `ORDER_DOMAIN` varchar(2) default NULL COMMENT '01:表示 orderid取自order_recode  02：表示orderid取自com_busi_flow',
  PRIMARY KEY  (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品销售详细';

/*Data for the table `rep_sell_detail` */

/*Table structure for table `rep_stock_detail` */

DROP TABLE IF EXISTS `rep_stock_detail`;

CREATE TABLE `rep_stock_detail` (
  `DETAIL_ID` bigint(20) NOT NULL auto_increment COMMENT '详情ID',
  `REP_OPT_ID` bigint(20) default NULL COMMENT '库存量操作记录ID',
  `ENTITY_ID` bigint(20) default NULL COMMENT '实体ID',
  `OPT_TYPE` varchar(2) default NULL COMMENT '操作类型：01. 进货入库 02. 退货给供货商',
  `OPTER_ID` bigint(20) default NULL COMMENT '操作员ID',
  `OPT_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY  (`DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品进货详细';

/*Data for the table `rep_stock_detail` */

insert  into `rep_stock_detail`(`DETAIL_ID`,`REP_OPT_ID`,`ENTITY_ID`,`OPT_TYPE`,`OPTER_ID`,`OPT_TIME`) values (1,1,1,'01',1,'2014-06-30 17:24:50'),(2,1,2,'01',1,'2014-06-30 17:24:50');

/*Table structure for table `repository` */

DROP TABLE IF EXISTS `repository`;

CREATE TABLE `repository` (
  `REP_CODE` bigint(20) NOT NULL auto_increment COMMENT '仓库编码',
  `REP_NAME` varchar(32) default NULL COMMENT '仓库名称',
  `DEPT_ID` varchar(15) default NULL COMMENT '关联部门',
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `PROVINCE` varchar(10) default NULL COMMENT '省份',
  `AREA` varchar(10) default NULL COMMENT '地市',
  `COUNTY` varchar(10) default NULL COMMENT '区县',
  `STREET` varchar(10) default NULL COMMENT '街道',
  `REMARK` varchar(64) default NULL COMMENT '备注',
  `USER_ID` bigint(20) default NULL COMMENT '用户ID',
  PRIMARY KEY  (`REP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='仓库repository';

/*Data for the table `repository` */

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL default '1',
  PRIMARY KEY  (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `sequence` */

/*Table structure for table `sku_entity` */

DROP TABLE IF EXISTS `sku_entity`;

CREATE TABLE `sku_entity` (
  `ENTITY_ID` bigint(20) NOT NULL auto_increment COMMENT '实体ID',
  `SKUID` bigint(20) default NULL COMMENT '商品SKUID',
  `REPOSITORY_CODE` bigint(20) default NULL COMMENT '仓库编码',
  `IMEI` varchar(50) default NULL COMMENT '固有串号',
  `SERIAL` varchar(50) default NULL COMMENT '序列号',
  `COST_PRICE` decimal(8,2) default NULL COMMENT '成本价格',
  `SELL_PRICE` decimal(8,2) default NULL COMMENT '最终销售价格',
  `STATUS` varchar(2) default NULL COMMENT '状态, 01:在平台库， 02 在代理商库，03 已销售给普通用户,04:已损坏',
  `MODIFY_TIME` datetime default NULL COMMENT '状态最后修改时间',
  `SERVICE_START` datetime default NULL COMMENT '维保开始时间',
  `SERVICE_END` datetime default NULL COMMENT '维保结束时间',
  `TARGET_REPCODE` bigint(20) default NULL COMMENT '目标仓库',
  PRIMARY KEY  (`ENTITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品实体表';

/*Data for the table `sku_entity` */

insert  into `sku_entity`(`ENTITY_ID`,`SKUID`,`REPOSITORY_CODE`,`IMEI`,`SERIAL`,`COST_PRICE`,`SELL_PRICE`,`STATUS`,`MODIFY_TIME`,`SERVICE_START`,`SERVICE_END`,`TARGET_REPCODE`) values (1,5,0,'12101000000',NULL,NULL,NULL,'01','2014-06-30 17:24:46',NULL,NULL,0),(2,5,0,'12101000001',NULL,NULL,NULL,'01','2014-06-30 17:24:46',NULL,NULL,0);

ALTER TABLE `hw_goods_info` ADD COLUMN `AGENT_PRICE` BIGINT(20) NULL COMMENT '代理商进货价格' AFTER `STATUS`, ADD COLUMN `SERVICE_MONTH` INT(11) NULL COMMENT '保修月数' AFTER `AGENT_PRICE`, ADD COLUMN `IS_RANGEPHONE` VARCHAR(1) NULL COMMENT '是否合约机' AFTER `SERVICE_MONTH`, ADD COLUMN `RANGE_PRICE` BIGINT(20) NULL COMMENT '合约价' AFTER `IS_RANGEPHONE`; 
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
