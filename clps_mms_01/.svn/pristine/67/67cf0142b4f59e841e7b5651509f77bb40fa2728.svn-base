/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : mms

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-01-10 14:32:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_meetingroom
-- ----------------------------
DROP TABLE IF EXISTS `tbl_meetingroom`;
CREATE TABLE `tbl_meetingroom` (
  `roo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '办公室编号',
  `roo_name` varchar(50) NOT NULL COMMENT '办公室名称',
  `roo_number` int(11) DEFAULT NULL COMMENT '容纳人数',
  `roo_address` varchar(50) DEFAULT NULL COMMENT '办公室地址',
  `roo_phone` varchar(11) DEFAULT NULL COMMENT '办公室电话',
  `roo_state` varchar(2) DEFAULT '0' COMMENT '办公室状态，0为空闲，1为占用',
  `roo_topic` varchar(50) DEFAULT NULL COMMENT '会议主题',
  `roo_remarks` varchar(255) DEFAULT NULL COMMENT '办公室备注',
  `roo_createtime` datetime DEFAULT NULL COMMENT '办公室创建时间',
  `roo_createname` varchar(50) DEFAULT NULL COMMENT '办公室创建人姓名',
  `roo_updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `roo_updatename` varchar(50) DEFAULT NULL COMMENT '更新人姓名',
  `roo_isEnable` int(11) DEFAULT 1 COMMENT '是否有效',
  PRIMARY KEY (`roo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_meetingroom
-- ----------------------------
