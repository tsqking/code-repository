/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : mms

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-01-10 14:32:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_equipment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_equipment`;
CREATE TABLE `tbl_equipment` (
  `equ_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备编号',
  `equ_name` varchar(50) NOT NULL COMMENT '设备名称',
  `equ_price` decimal(10,0) DEFAULT NULL COMMENT '设备价格',
  `equ_state` varchar(2) DEFAULT '0' COMMENT '设备状态，0为空闲，1为被占用',
  `equ_use` varchar(50) DEFAULT NULL COMMENT '设备使用者，对应办公室信息表的办公室名称',
  `equ_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
  `equ_remarks` varchar(255) DEFAULT NULL COMMENT '设备备注',
  `equ_createtime` datetime DEFAULT NULL COMMENT '设备入库时间',
  `equ_createname` varchar(50) DEFAULT NULL COMMENT '设备入库人姓名',
  `equ_updatetime` datetime DEFAULT NULL COMMENT '设备更新时间',
  `equ_updatename` varchar(50) DEFAULT NULL COMMENT '设备更新人姓名',
  `equ_isEnable` int(11) DEFAULT 1 COMMENT '是否有效',
  PRIMARY KEY (`equ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_equipment
-- ----------------------------
