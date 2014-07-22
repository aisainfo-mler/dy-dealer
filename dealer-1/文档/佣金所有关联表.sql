/*
SQLyog v10.2 
MySQL - 5.0.67-community-nt : Database - yddb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yddb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yddb`;

/*Table structure for table `ai_busi_config` */

DROP TABLE IF EXISTS `ai_busi_config`;

CREATE TABLE `ai_busi_config` (
  `SUB_SYSTEM_ID` varchar(2) collate utf8_bin NOT NULL COMMENT '子系统编码',
  `BUSI_CODE` varchar(10) collate utf8_bin NOT NULL COMMENT '指标编码',
  `BUSITYPE` varchar(3) collate utf8_bin default NULL COMMENT '指标类型(AI_BUSI_TAB_RELA.BUSITYPE)',
  `RANGETAG` varchar(4) collate utf8_bin default NULL COMMENT '范围标记(0:不用指定范围)目前cim_subaddinfo要用到',
  `COLNAME` varchar(15) collate utf8_bin default NULL COMMENT '字段名称(指标表的字段名称，推荐使用大写)',
  `BUSIVAL_FLAG` varchar(1) collate utf8_bin default '0' COMMENT '变量值标记(0:唯一值 1:多值;目前只允许CIM_UBUSIADD_YYYYMM的值是1)',
  `DATA_TYPE` varchar(3) collate utf8_bin NOT NULL COMMENT '数据类型(1.数字 2.字符 3.时间 4.时间字符如YYYYMMDD)',
  `TIME_EXPR` varchar(3) collate utf8_bin default NULL COMMENT '时间类型的计算方法(0:其他数据类型 1:)',
  `BUSI_FLAG` varchar(1) collate utf8_bin default NULL COMMENT '指标运行标示(0:表示程序生成 1:表示存储过程生成，如果是存储过程生成的话，remark中就是存储过程的名称)',
  `STATE` varchar(1) collate utf8_bin NOT NULL default '0' COMMENT '指标状态\n            (0 无效\n             1 有效)',
  `REMARK` varchar(256) collate utf8_bin default NULL COMMENT '指标名称',
  `EFF_TIME` timestamp NULL default NULL COMMENT '生效时间',
  `EXP_TIME` timestamp NULL default NULL COMMENT '失效时间',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`BUSI_CODE`,`SUB_SYSTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='后台指标配置表';

/*Data for the table `ai_busi_config` */

insert  into `ai_busi_config`(`SUB_SYSTEM_ID`,`BUSI_CODE`,`BUSITYPE`,`RANGETAG`,`COLNAME`,`BUSIVAL_FLAG`,`DATA_TYPE`,`TIME_EXPR`,`BUSI_FLAG`,`STATE`,`REMARK`,`EFF_TIME`,`EXP_TIME`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`) values ('1','11',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','3333',NULL,NULL,'m01','2014-07-11 10:44:55',NULL,NULL),('1','111',NULL,NULL,NULL,NULL,'3',NULL,NULL,'1','ssss',NULL,NULL,'m01','2014-07-11 10:47:24','m01','2014-07-17 14:37:02'),('1','12',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','11',NULL,NULL,'m01','2014-07-11 09:24:58',NULL,NULL),('1','1212',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','1111',NULL,NULL,'m01','2014-07-17 14:33:15',NULL,NULL),('1','123456',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','测试指标2',NULL,NULL,'admin','2014-07-08 14:43:59','admin','2014-07-08 16:01:31'),('1','23123',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','菊花节禁忌',NULL,NULL,'m01','2014-07-14 16:13:26','m01','2014-07-14 16:29:06'),('1','2323',NULL,NULL,NULL,NULL,'3',NULL,NULL,'1','123',NULL,NULL,'m01','2014-07-10 13:50:55',NULL,NULL),('1','44444',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','哈哈哈',NULL,NULL,'m01','2014-07-14 16:06:04','m01','2014-07-14 16:28:49'),('1','S0',NULL,NULL,NULL,NULL,'2',NULL,NULL,'0','订单类型',NULL,NULL,'admin','2013-09-02 14:11:02','admin','2013-09-02 16:24:08'),('1','S1',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','套餐费',NULL,NULL,'admin','2013-09-02 16:33:11','admin','2013-09-02 16:40:09'),('1','S2',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','基数1',NULL,NULL,'admin','2013-09-02 16:39:04','admin','2013-09-02 16:43:23'),('1','S3',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','渠道类型',NULL,NULL,'admin','2014-06-20 15:00:31','admin','2014-06-20 15:00:48'),('1','S9',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','晒单',NULL,NULL,'admin','2014-06-17 10:39:18',NULL,NULL),('1','aaa',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','sss',NULL,NULL,'m01','2014-07-17 14:32:48',NULL,NULL),('1','ee',NULL,NULL,NULL,NULL,'3',NULL,NULL,'1','www',NULL,NULL,'m01','2014-07-11 09:29:02',NULL,NULL),('1','eeeee',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','wwwwwww',NULL,NULL,'m01','2014-07-11 10:50:49',NULL,NULL),('1','sss',NULL,NULL,NULL,NULL,'3',NULL,NULL,'1','aaaa',NULL,NULL,'m01','2014-07-11 09:26:43',NULL,NULL),('1','www',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','eeee',NULL,NULL,'m01','2014-07-11 10:49:13',NULL,NULL);

/*Table structure for table `ai_busi_config_his` */

DROP TABLE IF EXISTS `ai_busi_config_his`;

CREATE TABLE `ai_busi_config_his` (
  `SUB_SYSTEM_ID` varchar(2) collate utf8_bin NOT NULL COMMENT '子系统编码',
  `BUSI_CODE` varchar(10) collate utf8_bin NOT NULL COMMENT '指标编码',
  `BUSITYPE` varchar(3) collate utf8_bin default NULL COMMENT '指标类型(AI_BUSI_TAB_RELA.BUSITYPE)',
  `RANGETAG` varchar(4) collate utf8_bin default NULL COMMENT '范围标记(0:不用指定范围)目前cim_subaddinfo要用到',
  `COLNAME` varchar(15) collate utf8_bin default NULL COMMENT '字段名称(指标表的字段名称，推荐使用大写)',
  `BUSIVAL_FLAG` varchar(1) collate utf8_bin default '0' COMMENT '变量值标记(0:唯一值 1:多值;目前只允许CIM_UBUSIADD_YYYYMM的值是1)',
  `DATA_TYPE` varchar(3) collate utf8_bin NOT NULL COMMENT '数据类型(1.数字 2.字符 3.时间 4.时间字符如YYYYMMDD)',
  `TIME_EXPR` varchar(3) collate utf8_bin default NULL COMMENT '时间类型的计算方法(0:其他数据类型 1:)',
  `BUSI_FLAG` varchar(1) collate utf8_bin default NULL COMMENT '指标运行标示(0:表示程序生成 1:表示存储过程生成，如果是存储过程生成的话，remark中就是存储过程的名称)',
  `STATE` varchar(1) collate utf8_bin NOT NULL default '0' COMMENT '指标状态\n            (0 无效\n             1 有效)',
  `REMARK` varchar(256) collate utf8_bin default NULL COMMENT '指标名称',
  `EFF_TIME` timestamp NULL default NULL COMMENT '生效时间',
  `EXP_TIME` timestamp NULL default NULL COMMENT '失效时间',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  `BUSSIN` bigint(20) default NULL,
  `BUSSIN_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `BUSSIN_TYPE` varchar(1) collate utf8_bin default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ai_busi_config_his` */

insert  into `ai_busi_config_his`(`SUB_SYSTEM_ID`,`BUSI_CODE`,`BUSITYPE`,`RANGETAG`,`COLNAME`,`BUSIVAL_FLAG`,`DATA_TYPE`,`TIME_EXPR`,`BUSI_FLAG`,`STATE`,`REMARK`,`EFF_TIME`,`EXP_TIME`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`,`BUSSIN`,`BUSSIN_TIME`,`BUSSIN_TYPE`) values ('1','渠道类型',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','S4',NULL,NULL,'admin','2014-06-20 15:00:31',NULL,NULL,220,'2014-06-20 15:00:48','E'),('1','123456',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','测试指标',NULL,NULL,'admin','2014-07-08 14:43:59',NULL,NULL,226,'2014-07-08 15:31:17','E'),('1','123456',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','测试指标',NULL,NULL,'admin','2014-07-08 14:43:59','admin','2014-07-08 15:31:18',229,'2014-07-08 16:01:07','E'),('1','123456',NULL,NULL,NULL,NULL,'2',NULL,NULL,'1','测试指标2',NULL,NULL,'admin','2014-07-08 14:43:59','admin','2014-07-08 16:01:06',230,'2014-07-08 16:01:32','E'),('1','111',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','fffff',NULL,NULL,'m01','2014-07-11 10:47:24',NULL,NULL,251,'2014-07-14 15:52:27','E'),('1','111',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','wqewq',NULL,NULL,'m01','2014-07-11 10:47:24','m01','2014-07-14 15:52:39',252,'2014-07-14 16:00:52','E'),('1','111',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','qwewqe',NULL,NULL,'m01','2014-07-11 10:47:24','m01','2014-07-14 16:00:52',253,'2014-07-14 16:28:26','E'),('1','12313',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','1213',NULL,NULL,'m01','2014-07-14 16:06:04',NULL,NULL,254,'2014-07-14 16:28:49','E'),('1','45444',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','213213',NULL,NULL,'m01','2014-07-14 16:13:26',NULL,NULL,255,'2014-07-14 16:29:06','E'),('1','111',NULL,NULL,NULL,NULL,'1',NULL,NULL,'1','打断点',NULL,NULL,'m01','2014-07-11 10:47:24','m01','2014-07-14 16:28:26',0,'2014-07-17 14:37:02','E');

/*Table structure for table `ai_mod_config` */

DROP TABLE IF EXISTS `ai_mod_config`;

CREATE TABLE `ai_mod_config` (
  `MOD_ID` int(11) NOT NULL auto_increment COMMENT '模型编号，从SEQ_AI_MOD_CONFIG序列中生成',
  `MOD_TYPE` varchar(2) collate utf8_bin NOT NULL COMMENT '模型类型，在AI_MOD_TYPE中定义',
  `SVC_ID` varchar(4) collate utf8_bin default NULL COMMENT '业务类型，在SYS_PARAM_DETAIL.NAME=''CIM_SVC_ID''定义。',
  `MOD_NAME` varchar(128) collate utf8_bin NOT NULL COMMENT '模型名称',
  `EFF_DATE` date default NULL COMMENT '生效日期',
  `EXP_DATE` date default NULL COMMENT '失效日期',
  `MOD_PRIORITY` int(11) default NULL COMMENT '模型类型优先级，模型的优先级=模型类型优先级*100+PRIORITY。',
  `PRIORITY` int(11) default NULL COMMENT '优先级',
  `AREA_ID` varchar(8) collate utf8_bin default NULL COMMENT '地区编号',
  `MOD_STATE` varchar(1) collate utf8_bin NOT NULL COMMENT '模型状态：0无效，1有效',
  `COMMENTS` text collate utf8_bin COMMENT '模型描述',
  `SUB_SYSTEM_ID` varchar(3) collate utf8_bin NOT NULL COMMENT '子系统编号，在AI_SUB_SYSTEM中定义',
  `CREATOR` varchar(20) collate utf8_bin default NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  `STATE` varchar(2) collate utf8_bin default NULL COMMENT '状态',
  `VERIFY_ID` varchar(15) collate utf8_bin default NULL COMMENT '审核ID',
  `Commitem` varchar(6) collate utf8_bin default NULL COMMENT 'Commitem',
  PRIMARY KEY  (`MOD_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模型定义，模型和公式是多对多关系，通过AI_RULE_MOD_RELATION定义两者关系';

/*Data for the table `ai_mod_config` */

insert  into `ai_mod_config`(`MOD_ID`,`MOD_TYPE`,`SVC_ID`,`MOD_NAME`,`EFF_DATE`,`EXP_DATE`,`MOD_PRIORITY`,`PRIORITY`,`AREA_ID`,`MOD_STATE`,`COMMENTS`,`SUB_SYSTEM_ID`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`,`STATE`,`VERIFY_ID`,`Commitem`) values (1,'0','','首返100 46返30','2012-09-02','2099-09-02',NULL,0,'','1','','1','','2013-08-29 17:05:14','admin','2014-06-20 16:06:04','','','null'),(2,'0','','首返80 46返30','2012-09-02','2099-09-02',NULL,0,'','1','','','','2013-08-29 17:05:00','','0000-00-00 00:00:00','','','000'),(3,'0','','充值现返1.8%','2013-01-01','2099-01-01',NULL,0,'','1','','','','2014-01-07 16:12:52','','0000-00-00 00:00:00','','','000'),(4,'0','','流量王返60','2013-01-01','2099-01-01',NULL,0,'','1','','','','2014-01-14 13:50:04','','0000-00-00 00:00:00','','','000'),(5,'1','','次月返佣导入','2013-01-01','2099-01-01',NULL,0,'','1','','','','2014-02-13 10:48:14','','0000-00-00 00:00:00','','','0003'),(13,'1',NULL,'晒单',NULL,NULL,NULL,NULL,NULL,'1',NULL,'1','admin','2014-06-17 17:10:58',NULL,NULL,NULL,NULL,'0001'),(12,'0',NULL,'22',NULL,NULL,NULL,NULL,NULL,'1',NULL,'1','admin','2014-04-08 14:21:19',NULL,NULL,NULL,NULL,'0001'),(11,'0',NULL,'11',NULL,NULL,NULL,NULL,NULL,'1',NULL,'1','admin','2014-04-08 14:17:50',NULL,NULL,NULL,NULL,NULL),(14,'0',NULL,'首返80 46返30--末梢',NULL,NULL,NULL,NULL,NULL,'1',NULL,'1','admin','2014-06-20 16:06:51',NULL,NULL,NULL,NULL,'0001'),(15,'0',NULL,'测试模型',NULL,NULL,NULL,NULL,NULL,'1',NULL,'1','admin','2014-07-08 15:57:29',NULL,NULL,NULL,NULL,'0001');

/*Table structure for table `ai_mod_config_his` */

DROP TABLE IF EXISTS `ai_mod_config_his`;

CREATE TABLE `ai_mod_config_his` (
  `MOD_ID` int(11) NOT NULL default '0' COMMENT '模型编号，从SEQ_AI_MOD_CONFIG序列中生成',
  `MOD_TYPE` varchar(2) collate utf8_bin NOT NULL COMMENT '模型类型，在AI_MOD_TYPE中定义',
  `SVC_ID` varchar(4) collate utf8_bin default NULL COMMENT '业务类型，在SYS_PARAM_DETAIL.NAME=''CIM_SVC_ID''定义。',
  `MOD_NAME` varchar(128) collate utf8_bin NOT NULL COMMENT '模型名称',
  `EFF_DATE` date default NULL COMMENT '生效日期',
  `EXP_DATE` date default NULL COMMENT '失效日期',
  `MOD_PRIORITY` int(11) default NULL COMMENT '模型类型优先级，模型的优先级=模型类型优先级*100+PRIORITY。',
  `PRIORITY` int(11) default NULL COMMENT '优先级',
  `AREA_ID` varchar(8) collate utf8_bin default NULL COMMENT '地区编号',
  `MOD_STATE` varchar(1) collate utf8_bin NOT NULL COMMENT '模型状态：0无效，1有效',
  `COMMENTS` text collate utf8_bin COMMENT '模型描述',
  `SUB_SYSTEM_ID` varchar(3) collate utf8_bin NOT NULL COMMENT '子系统编号，在AI_SUB_SYSTEM中定义',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  `STATE` varchar(2) collate utf8_bin default NULL COMMENT '状态',
  `VERIFY_ID` varchar(15) collate utf8_bin default NULL COMMENT '审核ID',
  `Commitem` varchar(3) collate utf8_bin default NULL COMMENT 'Commitem',
  `BUSSIN` varchar(45) collate utf8_bin default NULL,
  `BUSSIN_TYPE` varchar(2) collate utf8_bin default NULL,
  `BUSSIN_TIME` timestamp NULL default CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ai_mod_config_his` */

insert  into `ai_mod_config_his`(`MOD_ID`,`MOD_TYPE`,`SVC_ID`,`MOD_NAME`,`EFF_DATE`,`EXP_DATE`,`MOD_PRIORITY`,`PRIORITY`,`AREA_ID`,`MOD_STATE`,`COMMENTS`,`SUB_SYSTEM_ID`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`,`STATE`,`VERIFY_ID`,`Commitem`,`BUSSIN`,`BUSSIN_TYPE`,`BUSSIN_TIME`) values (1,'0','','首返100 46返30','2012-09-02','2099-09-02',NULL,0,'','1',NULL,'','','2013-08-29 17:05:14','','0000-00-00 00:00:00','','','000','225','E','2014-06-20 16:06:05');

/*Table structure for table `ai_rule_busi_rela` */

DROP TABLE IF EXISTS `ai_rule_busi_rela`;

CREATE TABLE `ai_rule_busi_rela` (
  `SUB_SYSTEM_ID` varchar(2) collate utf8_bin NOT NULL COMMENT '指标子系统',
  `BUSI_CODE` varchar(10) collate utf8_bin NOT NULL COMMENT '指标编码',
  `RULE_ID` int(11) NOT NULL COMMENT '规则编码(ai_rule_config.rule_id)',
  `REMARK` varchar(256) collate utf8_bin default NULL COMMENT '描述',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `operation` varchar(2) collate utf8_bin default NULL COMMENT '运算符号',
  `o_value` varchar(10) collate utf8_bin default NULL COMMENT '运算值',
  `datatype` varchar(2) collate utf8_bin default NULL COMMENT '类型',
  `rbrId` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`rbrId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='指标业务规则关系表';

/*Data for the table `ai_rule_busi_rela` */

insert  into `ai_rule_busi_rela`(`SUB_SYSTEM_ID`,`BUSI_CODE`,`RULE_ID`,`REMARK`,`CREATOR`,`CREATE_TIME`,`operation`,`o_value`,`datatype`,`rbrId`) values ('1','S9',1801,NULL,'admin','2014-06-20 15:10:05','<=','5','1',2),('1','S0',1802,NULL,'admin','2014-06-20 16:05:06','==','03','2',12),('1','S1',1802,NULL,'admin','2014-06-20 16:05:06','==','126','1',13),('1','S4',1802,NULL,'admin','2014-06-20 16:05:06','==','111','2',14),('1','S0',1803,NULL,'admin','2014-06-20 16:05:21','==','03','2',15),('1','S1',1803,NULL,'admin','2014-06-20 16:05:21','==','126','1',16),('1','S4',1803,NULL,'admin','2014-06-20 16:05:21','==','11','2',17),('1','123456',1804,NULL,'admin','2014-07-08 15:50:39','==','100','2',20),('1','S0',1804,NULL,'admin','2014-07-08 15:50:39','==','10','2',21),('1','S3',1804,NULL,'admin','2014-07-08 15:50:39','==','12','2',22);

/*Table structure for table `ai_rule_config` */

DROP TABLE IF EXISTS `ai_rule_config`;

CREATE TABLE `ai_rule_config` (
  `RULE_ID` int(11) NOT NULL auto_increment COMMENT '公式编号，从SEQ_AI_RULE_CONFIG序列生成',
  `RULE_NAME` varchar(128) collate utf8_bin NOT NULL COMMENT '公式名称',
  `RULE_TYPE` varchar(2) collate utf8_bin default NULL COMMENT '公式类型，1分量公式，2总量公式。\n            程序先计算分量公式再根据分量计算结果计算总量公式。',
  `MOD_TYPE` varchar(2) collate utf8_bin NOT NULL COMMENT '模型类型，在AI_MOD_TYPE中定义',
  `RULE` text collate utf8_bin COMMENT '公式内容',
  `RULE_RET` text collate utf8_bin COMMENT '公式结果',
  `RULE_STATE` varchar(1) collate utf8_bin NOT NULL COMMENT '规则状态：0禁用，1在用。',
  `COMMENTS` text collate utf8_bin COMMENT '公式描述，前台配置时程序要判断不能为空，且不能小于20字符。',
  `REMARK` text collate utf8_bin COMMENT '备注',
  `SUB_SYSTEM_ID` varchar(3) collate utf8_bin NOT NULL COMMENT '子系统编号',
  `AREA_ID` varchar(8) collate utf8_bin default NULL COMMENT '地区编号',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  `STATE` varchar(2) collate utf8_bin default NULL COMMENT '状态',
  `VERIFY_ID` int(11) default NULL COMMENT '审核ID',
  PRIMARY KEY  (`RULE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1828 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='公式定义';

/*Data for the table `ai_rule_config` */

insert  into `ai_rule_config`(`RULE_ID`,`RULE_NAME`,`RULE_TYPE`,`MOD_TYPE`,`RULE`,`RULE_RET`,`RULE_STATE`,`COMMENTS`,`REMARK`,`SUB_SYSTEM_ID`,`AREA_ID`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`,`STATE`,`VERIFY_ID`) values (2,'66套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 66) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 21:22:53','','0000-00-00 00:00:00','',NULL),(1,'46套餐开卡返30','','0','(\'#{S0}\' == \'03\') && (#{S1} == 46) && (#{S2}!=7)','30','1','','','','','admin','2013-09-02 21:22:54','','0000-00-00 00:00:00','',NULL),(3,'96套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 96) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(4,'126套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 126) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(5,'156套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 156) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(6,'186套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 186) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(7,'226套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 226) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(8,'286套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 286) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(9,'386套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 386) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(10,'586套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 586) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(11,'886套餐开卡返100','','0','(\'#{S0}\' == \'03\') && (#{S1} == 886) && (#{S2}!=7)','100','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(12,'46套餐开卡返30','','0','(\'#{S0}\' == \'03\') && (#{S1} == 46)','30','1','','','','','admin','2013-09-02 21:22:55','','0000-00-00 00:00:00','',NULL),(13,'66套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 66)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(14,'96套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 96)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(15,'126套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 126)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(16,'156套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 156)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(17,'186套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 186)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(18,'226套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 226)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(19,'286套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 286)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(20,'386套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 386)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(21,'586套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 586)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(22,'886套餐开卡返80','','0','(\'#{S0}\' == \'03\') && (#{S1} == 886)','80','1','','','','','admin','2013-09-02 11:03:00','','0000-00-00 00:00:00','',NULL),(1209,'886套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 886) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1208,'586套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 586) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1207,'386套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 386) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1206,'286套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 286) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1205,'226套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 226) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1204,'186套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 186) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1203,'156套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 156) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1202,'126套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 126) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1201,'96套餐开卡返120','','0','(\'#{S0}\' == \'03\') && (#{S1} == 96) && (#{S2} == 7)','120','1','','','','','admin','2014-01-03 11:03:00','','0000-00-00 00:00:00','',NULL),(1800,'充值返1.8%','','0','(\'#{S0}\' == \'01\')','(#{S1}*0.018)','1','','','','','admin','2014-01-07 16:10:48','','0000-00-00 00:00:00','',NULL),(600,'16-26-36开发返60','','0','(\'#{S0}\' == \'03\') && （(#{S1} == 16) || (#{S1} == 26) || (#{S1} == 36)）','60','1','','','','','admin','2014-01-14 13:54:38','','0000-00-00 00:00:00','',NULL),(1801,'晒单返佣',NULL,'1','(\'#{S9}\'<=5)','1','1',NULL,'','1',NULL,'admin','2014-06-17 10:47:51','admin','2014-06-20 15:10:04','1',NULL),(1802,'层级佣金-末梢',NULL,'0','(\'#{S0}\'==\'03\')&&(\'#{S1}\'==126)&&(\'#{S4}\'==\'111\')','20','1',NULL,'','1',NULL,'admin','2014-06-20 16:03:36','admin','2014-06-20 16:05:05','1',NULL),(1803,'层级佣金-二级渠道',NULL,'0','(\'#{S0}\'==\'03\')&&(\'#{S1}\'==126)&&(\'#{S4}\'==\'11\')','20','1',NULL,'','1',NULL,'admin','2014-06-20 16:04:18','admin','2014-06-20 16:05:19','1',NULL),(1804,'测试规则',NULL,'0','(\'#{123456}\'==\'100\')&&(\'#{S0}\'==\'10\')&&(\'#{S3}\'==\'12\')','x*0.1+2','1',NULL,'','1',NULL,'admin','2014-07-08 15:36:30','admin','2014-07-08 15:50:38','1',NULL),(1805,'实施',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 18:51:48',NULL,'2014-07-17 18:51:48','1',NULL),(1806,'战争',NULL,'2',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 18:54:21',NULL,'2014-07-17 18:54:21','1',NULL),(1807,'实施',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 18:56:47',NULL,'2014-07-17 18:56:47','1',NULL),(1808,'xdxx',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 19:00:24',NULL,'2014-07-17 19:00:24','1',NULL),(1809,'wwww',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 19:04:17',NULL,'2014-07-17 19:04:17','1',NULL),(1810,'wwww',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 19:07:54',NULL,'2014-07-17 19:07:54','1',NULL),(1811,'sss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 19:28:10',NULL,'2014-07-17 19:28:10','1',NULL),(1812,'ss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 19:33:57',NULL,'2014-07-17 19:33:57','1',NULL),(1813,'',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-17 19:40:02',NULL,'2014-07-17 19:40:02','1',NULL),(1814,'ssss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-18 13:22:21',NULL,'2014-07-18 13:22:21','1',NULL),(1815,'',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-18 13:40:38',NULL,'2014-07-18 13:40:38','1',NULL),(1816,'',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-18 13:46:40',NULL,'2014-07-18 13:46:40','1',NULL),(1817,'www',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 16:35:29',NULL,'2014-07-21 16:35:29','1',NULL),(1818,'www',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 16:35:36',NULL,'2014-07-21 16:35:36','1',NULL),(1819,'www',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 16:39:01',NULL,'2014-07-21 16:39:01','1',NULL),(1820,'sss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 16:39:35',NULL,'2014-07-21 16:39:35','1',NULL),(1821,'ww',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 21:10:17',NULL,'2014-07-21 21:10:17','1',NULL),(1822,'ssss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 21:15:54',NULL,'2014-07-21 21:15:54','1',NULL),(1823,'sss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 21:16:37',NULL,'2014-07-21 21:16:37','1',NULL),(1824,'ss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 21:17:52',NULL,'2014-07-21 21:17:52','1',NULL),(1825,'dddddd',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 21:22:23',NULL,'2014-07-21 21:22:23','1',NULL),(1826,'',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 21:58:41',NULL,'2014-07-21 21:58:41','1',NULL),(1827,'sss',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'m01','2014-07-21 22:03:36',NULL,'2014-07-21 22:03:36','1',NULL);

/*Table structure for table `ai_rule_config_his` */

DROP TABLE IF EXISTS `ai_rule_config_his`;

CREATE TABLE `ai_rule_config_his` (
  `RULE_ID` int(11) NOT NULL default '0' COMMENT '公式编号，从SEQ_AI_RULE_CONFIG序列生成',
  `RULE_NAME` varchar(128) collate utf8_bin NOT NULL COMMENT '公式名称',
  `RULE_TYPE` varchar(2) collate utf8_bin default NULL COMMENT '公式类型，1分量公式，2总量公式。\n            程序先计算分量公式再根据分量计算结果计算总量公式。',
  `MOD_TYPE` varchar(2) collate utf8_bin NOT NULL COMMENT '模型类型，在AI_MOD_TYPE中定义',
  `RULE` text collate utf8_bin COMMENT '公式内容',
  `RULE_RET` text collate utf8_bin COMMENT '公式结果',
  `RULE_STATE` varchar(1) collate utf8_bin NOT NULL COMMENT '规则状态：0禁用，1在用。',
  `COMMENTS` text collate utf8_bin COMMENT '公式描述，前台配置时程序要判断不能为空，且不能小于20字符。',
  `REMARK` text collate utf8_bin COMMENT '备注',
  `SUB_SYSTEM_ID` varchar(3) collate utf8_bin NOT NULL COMMENT '子系统编号',
  `AREA_ID` varchar(8) collate utf8_bin default NULL COMMENT '地区编号',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  `STATE` varchar(2) collate utf8_bin default NULL COMMENT '状态',
  `VERIFY_ID` int(11) default NULL COMMENT '审核ID',
  `BUSSIN` varchar(45) collate utf8_bin default NULL,
  `BUSSIN_TYPE` varchar(2) collate utf8_bin default NULL,
  `BUSSIN_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ai_rule_config_his` */

insert  into `ai_rule_config_his`(`RULE_ID`,`RULE_NAME`,`RULE_TYPE`,`MOD_TYPE`,`RULE`,`RULE_RET`,`RULE_STATE`,`COMMENTS`,`REMARK`,`SUB_SYSTEM_ID`,`AREA_ID`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`,`STATE`,`VERIFY_ID`,`BUSSIN`,`BUSSIN_TYPE`,`BUSSIN_TIME`) values (1801,'晒单返佣',NULL,'1',NULL,NULL,'1',NULL,NULL,'1',NULL,'admin','2014-06-17 10:47:51',NULL,'2014-06-17 10:47:51','1',NULL,'221','E','2014-06-20 15:10:05'),(1802,'层级佣金-末梢',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'admin','2014-06-20 16:03:36',NULL,'2014-06-20 16:03:36','1',NULL,'222','E','2014-06-20 16:04:28'),(1802,'层级佣金-末梢',NULL,'2',NULL,NULL,'1',NULL,NULL,'1',NULL,'admin','2014-06-20 16:03:36','admin','2014-06-20 16:04:26','1',NULL,'223','E','2014-06-20 16:05:06'),(1803,'层级佣金-二级渠道',NULL,'2',NULL,NULL,'1',NULL,NULL,'1',NULL,'admin','2014-06-20 16:04:18',NULL,'2014-06-20 16:04:18','1',NULL,'224','E','2014-06-20 16:05:21'),(1804,'测试规则',NULL,'0',NULL,NULL,'1',NULL,NULL,'1',NULL,'admin','2014-07-08 15:36:30',NULL,'2014-07-08 15:36:30','1',NULL,'227','E','2014-07-08 15:50:39');

/*Table structure for table `ai_rule_mod_relation` */

DROP TABLE IF EXISTS `ai_rule_mod_relation`;

CREATE TABLE `ai_rule_mod_relation` (
  `RULE_ID` int(11) NOT NULL COMMENT '公式编号',
  `MOD_ID` int(11) NOT NULL COMMENT '模型编号',
  `PRIORITY` int(11) default NULL COMMENT '优先顺序，1最高优先级、2次优先级，依此类推。',
  `EXCLUDE_RULE` varchar(64) collate utf8_bin default NULL COMMENT '公式排斥关系，以逗号分隔。比如本公式和1002、1003公式排斥，则本字段填写1002,1003。',
  `EXP_DATE` timestamp NULL default NULL COMMENT '失效日期',
  `EFF_DATE` timestamp NULL default NULL COMMENT '生效日期',
  `CREATOR` varchar(20) collate utf8_bin NOT NULL COMMENT '创建人',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATOR` varchar(20) collate utf8_bin default NULL COMMENT '修改人',
  `UPDATE_TIME` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`RULE_ID`,`MOD_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='公式与模型对应关系';

/*Data for the table `ai_rule_mod_relation` */

insert  into `ai_rule_mod_relation`(`RULE_ID`,`MOD_ID`,`PRIORITY`,`EXCLUDE_RULE`,`EXP_DATE`,`EFF_DATE`,`CREATOR`,`CREATE_TIME`,`UPDATOR`,`UPDATE_TIME`) values (10,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(9,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(8,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(7,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(6,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(12,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(13,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(14,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(15,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(16,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(17,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(18,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(19,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(20,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(21,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(22,2,NULL,'','2013-09-02 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1201,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1202,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1203,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1204,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1205,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1206,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1207,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1208,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(1,1209,NULL,'','2014-01-03 16:19:32','2019-08-29 17:05:00','','2013-08-29 17:05:00','','0000-00-00 00:00:00'),(5,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(4,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(3,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(2,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(1,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(1800,3,NULL,'','2014-01-01 00:00:00','2019-01-01 00:00:00','','2014-01-07 16:14:21','','0000-00-00 00:00:00'),(600,4,NULL,'','2014-01-01 00:00:00','2019-01-01 00:00:00','','2014-01-14 13:55:32','','0000-00-00 00:00:00'),(1,6,NULL,NULL,NULL,NULL,'admin','2014-04-08 13:59:12',NULL,'0000-00-00 00:00:00'),(1,7,NULL,NULL,NULL,NULL,'admin','2014-04-08 14:11:19',NULL,'0000-00-00 00:00:00'),(4,8,NULL,NULL,NULL,NULL,'admin','2014-04-08 14:15:13',NULL,'0000-00-00 00:00:00'),(1,9,NULL,NULL,NULL,NULL,'admin','2014-04-08 14:16:48',NULL,'0000-00-00 00:00:00'),(4,10,NULL,NULL,NULL,NULL,'admin','2014-04-08 14:17:01',NULL,'0000-00-00 00:00:00'),(3,11,NULL,NULL,NULL,NULL,'admin','2014-04-08 14:17:50',NULL,'0000-00-00 00:00:00'),(4,12,NULL,NULL,NULL,NULL,'admin','2014-04-08 14:21:19',NULL,'0000-00-00 00:00:00'),(1801,13,NULL,NULL,NULL,NULL,'admin','2014-06-17 17:10:58',NULL,'0000-00-00 00:00:00'),(11,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(1201,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(1202,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:06',NULL,'0000-00-00 00:00:00'),(1203,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:07',NULL,'0000-00-00 00:00:00'),(1204,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:07',NULL,'0000-00-00 00:00:00'),(1205,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:07',NULL,'0000-00-00 00:00:00'),(1206,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:09',NULL,'0000-00-00 00:00:00'),(1207,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:09',NULL,'0000-00-00 00:00:00'),(1208,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:09',NULL,'0000-00-00 00:00:00'),(1209,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:09',NULL,'0000-00-00 00:00:00'),(1803,1,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:10',NULL,'0000-00-00 00:00:00'),(1802,14,NULL,NULL,NULL,NULL,'admin','2014-06-20 16:06:51',NULL,'0000-00-00 00:00:00'),(1804,15,NULL,NULL,NULL,NULL,'admin','2014-07-08 15:57:29',NULL,'0000-00-00 00:00:00'),(1802,15,NULL,NULL,NULL,NULL,'admin','2014-07-08 15:57:29',NULL,'0000-00-00 00:00:00');

/*Table structure for table `sequence` */

DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL default '1',
  PRIMARY KEY  (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `sequence` */

/*Table structure for table `sys_param_detail` */

DROP TABLE IF EXISTS `sys_param_detail`;

CREATE TABLE `sys_param_detail` (
  `P_KEY` varchar(45) default NULL,
  `P_VALUE` varchar(45) default NULL,
  `P_DESC` varchar(45) default NULL,
  `P_P_VALUE` varchar(45) default NULL,
  KEY `Index_SYS_PARAM_DETAIL` (`P_KEY`,`P_VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_param_detail` */

insert  into `sys_param_detail`(`P_KEY`,`P_VALUE`,`P_DESC`,`P_P_VALUE`) values ('ORDERTYPE','00','平台售卡',NULL),('ORDERTYPE','01','平台售设备',NULL),('ORDERTYPE','10','代理商售卡',NULL),('ORDERTYPE','11','代理商售设备',NULL),('ORDERSTATUS','01','待支付',NULL),('ORDERSTATUS','02','已付款',NULL),('ORDERSTATUS','03','已发货',NULL),('ORDERSTATUS','04','已完成',NULL),('ORDERSTATUS','05','撤单',NULL),('USERTYPES','0','平台管理员',NULL),('USERTYPES','2','平台普通员工',NULL),('USERTYPES','1','加盟渠道管理员',NULL),('USERTYPES','3','加盟渠道普通员工',NULL),('USERTYPES','4','自有渠道员工',NULL),('DEPTTYPES','0','电商平台',NULL),('DEPTTYPES','1','加盟渠道',NULL),('DEPTTYPES','11','加盟渠道末梢',NULL),('DEPTTYPES','3','自有渠道',NULL),('DEPTTYPES','33','自有渠道末梢',NULL),('ORDERRES','00','电商平台',NULL),('NUM_DISTRICT','B','涪陵区号码池',NULL),('NUM_DISTRICT','C','万州区号码池',NULL),('NUM_DISTRICT','A','主城区号码池',NULL),('ORDERRES','01','欧顺快销',NULL),('ORDERRES','02','欧顺快服',NULL),('ORDERRES','99','其他',NULL),('CHONGQING_BIG_AREA','A','主城区',NULL),('CHONGQING_BIG_AREA','B','涪陵区',NULL),('CHONGQING_BIG_AREA','C','万州区',NULL),('BUSITYPES','01','缴费充值',NULL),('BUSITYPES','02','2G业务开户',NULL),('BUSITYPES','03','3G业务开户',NULL),('BUSITYPES','04','补换卡',NULL),('BUSITYPES','98','欧顺手机销售',NULL),('BUSITYPES','99','其他',NULL),('BUSISTATUS','01','待补录',NULL),('BUSISTATUS','02','待写卡',NULL),('BUSISTATUS','10','补录成功',NULL),('BUSISTATUS','11','开户成功',NULL),('BUSISTATUS','12','写卡失败',NULL),('BUSISTATUS','13','订单完成',NULL),('CARD_TYPE','1','成卡',NULL),('CARD_TYPE','2','白卡',NULL),('CHANNLE_STATUS','00','禁用',NULL),('CHANNLE_STATUS','01','有效',NULL),('CHANNLE_STATUS','02','已注销',NULL),('SELL_DETAIL_OPTTYPE','01','进货入库',NULL),('SELL_DETAIL_OPTTYPE','02','退货给供货商',NULL),('SELL_DETAIL_OPTTYPE','03','销售给普通用户',NULL),('SELL_DETAIL_OPTTYPE','04','销售给代理商',NULL),('SELL_DETAIL_OPTTYPE','05','普通用户退货入平台库',NULL),('SELL_DETAIL_OPTTYPE','06','代理商退货入平台库',NULL),('SELL_DETAIL_OPTTYPE','07','代理商销售给普通用户',NULL),('SELL_DETAIL_OPTTYPE','08','普通用户退货入代理商库',NULL),('BUSISTATUS','14','撤单',NULL),('NUMBER_STATUS','0','空闲',NULL),('NUMBER_STATUS','1','选号临时占用',NULL),('NUMBER_STATUS','2','长期占用',NULL),('NUMBER_STATUS','4','申请占用状态',NULL),('DEPT_STATUS','1','有效',NULL),('DEPT_STATUS','2','禁用',NULL),('DEPT_STATUS','3','注销',NULL),('BUSITYPES','05','合约机',NULL),('BUSISTATUS','00','补录失败',NULL),('SKU_ENTITY_STATUS','01','在平台库',NULL),('SKU_ENTITY_STATUS','02','在代理商库',NULL),('SKU_ENTITY_STATUS','03','已销售',NULL),('SKU_ENTITY_STATUS','04','已损坏',NULL),('SKU_STATUS','01','上架',NULL),('SKU_STATUS','00','下架',NULL),('SPE_TYPE','00','普通商品',NULL),('SPE_TYPE','02','推荐',NULL),('SPE_TYPE','03','热销',NULL),('SPE_TYPE','01','新品',NULL),('ACC_STYLE','300','充值','业务类型 Busi_id '),('ACC_STYLE','301','WEB采购','业务类型 Busi_id '),('ACC_STYLE','302','号卡销售','业务类型 Busi_id '),('ACC_STYLE','303','现金','缴费类型pay_style'),('ACC_STYLE','304','银行卡','缴费类型pay_style'),('ACC_STYLE','305','充值卡','缴费类型pay_style'),('ACC_STYLE','306','转账','缴费类型pay_style'),('ACC_STYLE','307','预存池','缴费类型pay_style'),('ACC_STYLE','308','终端采购','业务类型 Busi_id '),('ACC_STYLE','309','2G业务开户','业务类型 Busi_id '),('ACC_STYLE','310','3G业务开户','业务类型 Busi_id '),('ACC_STYLE','311','补换卡','业务类型 Busi_id '),('ACC_STYLE','312','其它','业务类型 Busi_id '),('ACC_STYLE','313','提现','业务类型 Busi_id '),('OPRSTYLE','1','缴费/充值',NULL),('OPRSTYLE','2','缴费/充值返销',NULL),('OPRSTYLE','3','扣款',NULL),('OPRSTYLE','4','扣款返销',NULL),('PAYTYPE','01','易极付支付',NULL),('PAYTYPE','02','欧顺账户支付',NULL),('PAYSTATUS','00','未支付',NULL),('PAYSTATUS','01','成功',NULL),('PAYSTATUS','02','失败',NULL),('PAYSTATUS','03','作废',NULL),('ORDERSTATUS','06','支付超时失效',NULL),('COMMON_STATUS','01','有效',''),('COMMON_STATUS','00','无效',''),('APPLICABLE_RANGE','3G','3G',''),('APPLICABLE_RANGE','2G','2G',''),('CERTIFICATE_TYPE','09','武警证',''),('CERTIFICATE_TYPE','08','护照',''),('CERTIFICATE_TYPE','04','军官证',''),('CERTIFICATE_TYPE','02','身份证',''),('BUSITYPES','06','宽带预约',NULL),('BUSISTATUS','03','待处理',NULL),('BANDTYPE','03','8M宽带',''),('BANDTYPE','01','36元/月8M仅针对联通3G新老客户办理',''),('BANDTYPE','04','16M宽带',''),('BANDTYPE','02','780元/年8M单装',''),('MODTYPES','0','开户订单实时佣金',''),('MODTYPES','1','其他',''),('DATA_TYPES','1','数字',NULL),('DATA_TYPES','2','字符',NULL),('DATA_TYPES','3','时间',NULL),('BUSISTATE','0','无效','invalid'),('BUSISTATE','1','有效','valid'),('MENUSTATUS','1','有效','valid'),('MENUSTATUS','2','无效','invalid'),('MENUTYPES','0','目录',NULL),('MENUTYPES','1','功能点',NULL),('MENUTYPES','2','系统链接',NULL),('MENUTYPES','3','外部链接',NULL),('SELL_DETAIL_OPTTYPE','11','充值缴费',NULL),('SELL_DETAIL_OPTTYPE','12','充值返销',NULL),('RECHARGE_STATUS','00','待缴费',NULL),('RECHARGE_STATUS','01','缴费成功',NULL),('RECHARGE_STATUS','02','缴费失败',NULL),('BUSISTATUS','15','缴费失败',NULL),('BUSISTATUS','16','缴费成功',NULL),('BUSISTATUS','17','缴费返销成功',NULL),('MENU_OTARGET','main','main',NULL),('MENU_OTARGET','_black','_black',''),('MENU_OTARGET','_self','_self',''),('BUSITYPES','10','缴费',NULL),('MENU_STATUS','1','正常','valid'),('MENU_STATUS','2','失效','invalid'),('MENU_TYPE','0','目录','menu'),('MENU_TYPE','1','功能点','function'),('MENU_TYPE','2','系统链接','system link'),('MENU_TYPE','3','外部链接','external link'),('RECHARGE_TYPE','01','充值','recharge'),('RECHARGE_TYPE','02','返销','refund'),('OUT_CUST_ID','asiainfo-linkage0001','易生活',NULL),('ts_ip',NULL,NULL,NULL),('ts_ip',NULL,NULL,NULL),('ts','ce','10.1',NULL),('ts','zs','10.222',NULL),('DEVICE_TYPE','iPhone','iPhone',NULL),('DEVICE_TYPE','iPad','iPad',NULL),('DEVICE_TYPE','aPhone','安卓Phone','android phone'),('DEVICE_TYPE','aPad','安卓Pad','android pad'),('DEVICE_TYPE','web','web页面','web page'),('PUSH_IP','消息推送','112.124.48.127',NULL),('PUSH_CHAT_ACCOUNT','消息推送','liuqi@ailk-tigase',NULL),('PUSH_PORT','消息推送','5222',NULL),('PUSH_DOMAIN','消息推送','ailk-tigase',NULL),('SYS_TAG','kx','快销',NULL),('SYS_TAG','kf','快服',NULL),('SYS_TAG','dspt','电商平台',NULL),('SYS_TAG','zcpt','支撑平台',NULL),('SYS_TAG','kxpc','快销PC版',NULL),('ACC_STYLE','315','缴费佣金','业务类型 Busi_id '),('ACC_STYLE','316','佣金充值','缴费类型pay_style'),('ACC_STYLE','317','佣金返销','缴费类型pay_style'),('COMM_ITEM_TYPE','0001','开户返佣','ai_mod_config,cim_agent_sum用到'),('COMM_ITEM_TYPE','0002','充值返佣','ai_mod_config,cim_agent_sum用到'),('COMM_ITEM_TYPE','0101','水电煤返佣','ai_mod_config,cim_agent_sum用到'),('BUSISTATUS','20','在途',NULL),('BUSISTATUS','21','竣工',NULL),('EASYLIFE_TYPE','000010','水费',NULL),('EASYLIFE_TYPE','000020','燃气费',NULL),('EASYLIFE_TYPE','000030','电费',NULL),('WATER_COMPANY','002300001','重庆中法水务',NULL),('WATER_COMPANY','002300002','重庆自来水',NULL),('WATER_COMPANY','002300003','重庆二次供水',NULL),('WATER_COMPANY','002300004','合川自来水',NULL),('WATER_COMPANY','002300005','重庆市渝南自来水',NULL),('WATER_COMPANY','002300006','重庆巴南鱼洞供水',NULL),('WATER_COMPANY','002300015','重庆巴南道角水',NULL),('WATER_COMPANY','002300016','大学城水务',NULL),('ELECTRIC_COMPANY','002300009','重庆电力',NULL),('GAS_COMPANY','002300007','重庆燃气',NULL),('GAS_COMPANY','002300008','凯源燃气',NULL),('DEPTTYPES_CHANNEL','1','加盟渠道',NULL),('DEPTTYPES_CHANNEL','11','加盟渠道末梢',NULL),('DEPTTYPES_CHANNEL','3','自有渠道',NULL),('DEPTTYPES_CHANNEL','33','自有渠道末梢',NULL),('COMM_ITEM_TYPE','0003','次月返佣','ai_mod_config,cim_agent_sum用到'),('ACC_STYLE','320','次月返佣','业务类型 Busi_id '),('NUMBER_STATUS','5','占用不可选',NULL),('SKU_ENTITY_STATUS','05','占用不可选',NULL),('BULU_LIMIT_COUNT','3','补录最大重置次数',NULL),('PRODUCT_TYPE','001','3G开户预约',NULL),('PRODUCT_TYPE','002','合约预约',NULL),('PRODUCT_TYPE','003','宽带预约',NULL),('BUSITYPES','08','预约开户',NULL),('UNI_ORDER_TYPE','210','非定制机合约购机',NULL),('UNI_ACTIVITY_TYPE','104','存费送短信',NULL),('UNI_ACTIVITY_TYPE','105','存费送流量',NULL),('UNI_ACTIVITY_TYPE','106','存费送语音',NULL),('UNI_ORDER_TYPE','201','全国预付费上网卡',NULL),('UNI_ORDER_TYPE','209','裸机',NULL),('UNI_ORDER_TYPE','208','后付费合约购机',NULL),('UNI_ORDER_TYPE','207','预付费合约购机',NULL),('UNI_ORDER_TYPE','206','3G后付费号码',NULL),('UNI_ORDER_TYPE','205','3G预付费号码',NULL),('UNI_ORDER_TYPE','204','2G号码',NULL),('UNI_ORDER_TYPE','203','配件',NULL),('UNI_ORDER_TYPE','202','省份预付费上网卡',NULL),('UNI_AREA','E','嘉兴',NULL),('UNI_AREA','F','绍兴',NULL),('UNI_AREA','G','台州',NULL),('UNI_AREA','H','丽水',NULL),('UNI_AREA','I','金华',NULL),('UNI_AREA','J','舟山',NULL),('UNI_AREA','K','宁波',NULL),('UNI_REMARK_TYPE','C1001','订单领取',NULL),('UNI_REMARK_TYPE','C1002','外呼反馈',NULL),('UNI_REMARK_TYPE','C1003','申请退单',NULL),('UNI_REMARK_TYPE','C1004','订单处理',NULL),('UNI_REMARK_TYPE','C1005','申请外呼',NULL),('UNI_REMARK_TYPE','C1006','订单配货',NULL),('UNI_REMARK_TYPE','C1007','订单稽核',NULL),('UNI_REMARK_TYPE','C1008','订单发货',NULL),('UNI_REMARK_TYPE','C1009','订单分配',NULL),('UNI_REMARK_TYPE','C1010','退单驳回',NULL),('UNI_PRINT_TYPE','101','快递面单-顺丰快递',NULL),('UNI_PRINT_TYPE','102','快递面单-EMS在线支付',NULL),('UNI_PRINT_TYPE','104','发货清单',NULL),('UNI_REFUND_REASON','101','商品修改',NULL),('UNI_REFUND_REASON','102','证件不全',NULL),('UNI_REFUND_TYPE','11','全额退款',NULL),('UNI_REFUND_TYPE','12','部分退款',NULL),('UNI_CUST_TYPE','11','个人',NULL),('UNI_CUST_TYPE','12','政府机关',NULL),('UNI_CUST_TYPE','13','民营企业',NULL),('UNI_ORDER_SRC','1004','电子沃店',NULL),('UNI_ORDER_SRC','1003','微信接口',NULL),('UNI_ORDER_SRC','1005','手机商城',NULL),('UNI_ORDER_SRC','1006','拍拍商城',NULL),('UNI_ORDER_SRC','1009','挑号网',NULL),('UNI_ORDER_SRC','1008','推广联盟',NULL),('UNI_ORDER_SRC','1007','外围接入',NULL),('UNI_PAY_STATUS','13','已支付',NULL),('UNI_PAY_STATUS','15','退款成功',NULL),('UNI_PAY_STATUS','16','退款失败',NULL),('UNI_PAY_STATUS','17','线下退款',NULL),('UNI_PAY_STATUS','14','申请退款',NULL),('UNI_ORDER_STATUS','10','待分配',NULL),('UNI_BACK_ORDER_REASON','201','客户申请退款',NULL),('UNI_BACK_ORDER_REASON','202','客户申请退货',NULL),('UNI_FIRST_MODE','0','套餐包外资费',NULL),('UNI_FIRST_MODE','1','全月套餐',NULL),('UNI_FIRST_MODE','2','套餐减半',NULL),('UNI_ORDER_STATUS','31','稽核驳回处理',NULL),('UNI_ORDER_STATUS','32','稽核驳回配货',NULL),('UNI_ORDER_STATUS','41','客户退单',NULL),('UNI_ORDER_STATUS','90','待预检',NULL),('UNI_ORDER_STATUS','22','待退件入库',NULL),('UNI_ORDER_STATUS','21','已签收',NULL),('UNI_OUT_CALL_REC_TYPE','101','无人接听',NULL),('UNI_OUT_CALL_REC_TYPE','102','稍后再拔等',NULL),('UNI_OUT_CALL_REC_TYPE','103','用户拒绝',NULL),('UNI_DATA_SRC','1001','省分天猫',NULL),('UNI_CHECK_QUES_TYPE','1001','话费没存',NULL),('UNI_CHECK_QUES_TYPE','1002','上网卡地市错误',NULL),('UNI_CHECK_QUES_TYPE','1003','未剪卡/剪卡错误',NULL),('UNI_CHECK_QUES_TYPE','1004','资料不完整',NULL),('UNI_CHECK_QUES_TYPE','1005','子产品活动未叠加',NULL),('UNI_CHECK_QUES_TYPE','1006','活动受理错误',NULL),('UNI_CHECK_QUES_TYPE','1007','套餐错误',NULL),('UNI_CHECK_QUES_TYPE','1008','副卡未受理',NULL),('UNI_CHECK_QUES_TYPE','1009','物流面单错误',NULL),('UNI_CHECK_QUES_TYPE','1010','发票错误',NULL),('UNI_CHECK_QUES_TYPE','1011','货到付款省外订单',NULL),('UNI_PRE_CHECK_TYPE','1012','省外地址(收货地址)',NULL),('UNI_REMARK_TYPE','C1011','退件备注',NULL),('UNI_REMARK_TYPE','C1012','申请退款',NULL),('UNI_REMARK_TYPE','C1013','外呼记录',NULL),('UNI_REMARK_TYPE','C1014','简要备注',NULL),('UNI_BACK_ORDER_REASON','102','证件不全',NULL),('UNI_BACK_ORDER_REASON','101','商品修改',NULL),('UNI_PRINT_TYPE','103','快递面单-EMS货到付款',NULL),('UNI_EXPRESS_TYPE','103','EMS货到付款',NULL),('UNI_PKG_REQ_TYPE','10','忽略报文',NULL),('UNI_PKG_REQ_TYPE','11','正常报文',NULL),('UNI_PKG_REQ_TYPE','12','异常报文',NULL),('UNI_PKG_REQ_TYPE','13','出错报文',NULL),('UNI_EXPRESS_TYPE','101','顺丰',NULL),('UNI_EXPRESS_TYPE','102','EMS在线支付',NULL),('UNI_GOODS_TYPE','101','号码',NULL),('UNI_GOODS_TYPE','102','手机',NULL),('UNI_ORDER_TYPE','101','合约购机',NULL),('UNI_ORDER_TYPE','102','套餐3G手机卡',NULL),('UNI_ORDER_TYPE','103','3G无线上网卡',NULL),('UNI_ORDER_TYPE','104','流量充值卡',NULL),('UNI_ORDER_SRC','1001','淘宝商城',NULL),('UNI_ORDER_SRC','1002','联通商城',NULL),('UNI_ORDER_STATUS','11','申请外呼',NULL),('UNI_ORDER_STATUS','12','待库管',NULL),('UNI_ORDER_STATUS','13','待处理',NULL),('UNI_ORDER_STATUS','14','待配货',NULL),('UNI_ORDER_STATUS','15','待稽核',NULL),('UNI_ORDER_STATUS','16','待发货',NULL),('UNI_ORDER_STATUS','17','待退单',NULL),('UNI_ORDER_STATUS','18','待退款',NULL),('UNI_ORDER_STATUS','19','待返销',NULL),('UNI_ORDER_STATUS','20','待签收',NULL),('UNI_ORDER_STATUS','29','订单完成',NULL),('UNI_ORDER_STEP','51','分配处理',NULL),('UNI_ORDER_STEP','52','订单确认',NULL),('UNI_ORDER_STEP','53','订单处理',NULL),('UNI_ORDER_STEP','54','发货处理',NULL),('UNI_ORDER_STEP','55','配送在途',NULL),('UNI_ORDER_STEP','59','订单完成',NULL),('UNI_PAY_TYPE','101','在线支付',NULL),('UNI_PAY_TYPE','102','货到付款',NULL),('UNI_DELIVERY_TYPE','101','快递',NULL),('UNI_DELIVERY_TYPE','102','来联通自提',NULL),('UNI_GOODS_TYPE','103','上网卡',NULL),('UNI_GOODS_TYPE','104','配件',NULL),('UNI_ACTIVITY_TYPE','101','购手机入网送话费',NULL),('UNI_ACTIVITY_TYPE','102','预存话费送手机',NULL),('UNI_ACTIVITY_TYPE','103','存费送费',NULL),('UNI_ACTIVITY_TYPE','999','无',NULL),('UNI_NEW_OLD_USER','1','新用户',NULL),('UNI_NEW_OLD_USER','2','老用户',NULL),('UNI_TERMINAL_TYPE','11','手机',NULL),('UNI_TERMINAL_TYPE','12','上网卡',NULL),('UNI_TERMINAL_TYPE','13','配件',NULL),('UNI_TERMINAL_BRAND','11101','HTC',NULL),('UNI_TERMINAL_BRAND','11102','三星',NULL),('UNI_TERMINAL_MODEL','11101101','T328W',NULL),('UNI_TERMINAL_MODEL','11101102','T329W',NULL),('UNI_TERMINAL_MODEL','11102101','P6200',NULL),('UNI_TERMINAL_MODEL','11102102','P6800',NULL),('UNI_PAY_STATUS','11','未支付',NULL),('UNI_PAY_STATUS','12','部分支付',NULL),('UNI_PRE_CHECK_TYPE','1001','黑名单',NULL),('UNI_PRE_CHECK_TYPE','1002','用户欠费',NULL),('UNI_PRE_CHECK_TYPE','1003','证件不全',NULL),('UNI_PRE_CHECK_TYPE','1004','证件客户最大数',NULL),('UNI_PRE_CHECK_TYPE','1005','证件用户最大数',NULL),('UNI_PRE_CHECK_TYPE','1006','产品互斥',NULL),('UNI_PRE_CHECK_TYPE','1007','靓号级别',NULL),('UNI_AREA','A','杭州',NULL),('UNI_AREA','B','温州',NULL),('UNI_AREA','C','衢州',NULL),('UNI_AREA','D','湖州',NULL),('UNI_ORDER_TYPE','105','话费充值',NULL),('UNI_GOODS_TYPE','105','流量充值卡',NULL),('UNI_GOODS_TYPE','999','其他',NULL),('UNI_CUST_CERT_TYPE','10','身份证(15位)',NULL),('UNI_CUST_CERT_TYPE','11','身份证(18位)',NULL),('UNI_CUST_CERT_TYPE','12','护照',NULL),('UNI_CUST_CERT_TYPE','13','军官证',NULL),('UNI_CUST_CERT_TYPE','14','驾驶证',NULL),('UNI_CUST_CERT_TYPE','15','工作证',NULL),('UNI_CUST_CERT_TYPE','16','学生证',NULL),('UNI_CUST_CERT_TYPE','17','户口簿',NULL),('UNI_CUST_CERT_TYPE','18','暂住证',NULL),('UNI_CUST_CERT_TYPE','19','警官证',NULL),('UNI_CUST_CERT_TYPE','20','单位介绍信',NULL),('UNI_CUST_CERT_TYPE','21','营业执照',NULL),('UNI_CUST_CERT_TYPE','22','其它',NULL),('UNI_CUST_CERT_TYPE','23','小微企业客户证件',NULL),('UNI_CUST_CERT_TYPE','24','集团编号',NULL),('UNI_CUST_CERT_TYPE','25','工商注册登记证',NULL),('UNI_CUST_CERT_TYPE','26','组织机构代码证',NULL),('UNI_CUST_CERT_TYPE','30','预开户编号',NULL),('UNI_CUST_CERT_TYPE','31','港澳居民来往内地通行证',NULL),('UNI_CUST_CERT_TYPE','32','台湾居民来往大陆通行证',NULL),('UNI_CUST_CERT_TYPE','33','武警身份证',NULL),('UNI_CUST_CERT_TYPE','41','事业单位法人代表证 ',NULL),('UNI_CUST_CERT_TYPE','42','照会',NULL),('UNI_CUST_CERT_TYPE','43','社会团体法人证书',NULL),('UNI_CUST_CERT_TYPE','99','待完善资料',NULL),('UNI_PRINT_TYPE','105','发票',NULL),('UNI_ORDER_TYPE','106','天猫券',NULL),('UNI_EXPRESS_TYPE','104','无需物流',NULL),('NETWORKER','CHINA_MOBILE','中国移动',NULL),('NETWORKER','CHINA_UNICOM','中国联通',NULL),('NETWORKER','CHINA_TELECOM','中国电信',NULL),('COMM_ITEM_TYPE','0004','晒单返佣','ai_mod_config,cim_agent_sum用到'),('SITECONTEXTPATH','http://42.121.57.229:7070/site','电商平台IP   截止到域名',NULL),('DEPTTYPES','111','末梢预约渠道',NULL),('BUSITYPES','07','合约机受理',''),('MODTYPES','2','层级佣金',NULL),('DEPTTYPES_CHANNEL','111','末梢预约渠道',NULL),('ACC_STYLE','3141','开户层级返佣',NULL),('ACC_STYLE','316','晒单佣金',NULL),('ACC_STYLE','3151','缴费层级返佣',NULL),('SKU_ENTITY_STATUS','06','在途',NULL);

/* Function  structure for function  `currval` */

/*!50003 DROP FUNCTION IF EXISTS `currval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
  DECLARE value INTEGER;
  SET value = 0;
  SELECT current_value INTO value
  FROM sequence
  WHERE name = seq_name;
  RETURN value;
END */$$
DELIMITER ;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN
   UPDATE sequence
   SET          current_value = current_value + increment
   WHERE NAME = seq_name;
   RETURN currval(seq_name);
END */$$
DELIMITER ;

/*Table structure for table `view_cache` */

DROP TABLE IF EXISTS `view_cache`;

/*!50001 DROP VIEW IF EXISTS `view_cache` */;
/*!50001 DROP TABLE IF EXISTS `view_cache` */;

/*!50001 CREATE TABLE  `view_cache`(
 `P_KEY` varchar(45) ,
 `P_VALUE` varchar(45) ,
 `P_DESC` varchar(45) 
)*/;

/*View structure for view view_cache */

/*!50001 DROP TABLE IF EXISTS `view_cache` */;
/*!50001 DROP VIEW IF EXISTS `view_cache` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_cache` AS select `sys_param_detail`.`P_KEY` AS `P_KEY`,`sys_param_detail`.`P_VALUE` AS `P_VALUE`,`sys_param_detail`.`P_DESC` AS `P_DESC` from `sys_param_detail` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
