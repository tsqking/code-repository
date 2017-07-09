/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : mms

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-01-12 18:26:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee` (
  `emp_id` bigint(20) NOT NULL COMMENT '工号',
  `emp_loginname` varchar(50) NOT NULL COMMENT '登录名',
  `emp_realname` varchar(50) NOT NULL COMMENT '真实姓名',
  `emp_password` varchar(50) NOT NULL COMMENT '密码',
  `emp_depart_id` bigint(20) NOT NULL COMMENT '部门编号',
  `emp_gender` char(6) NOT NULL COMMENT '性别',
  `emp_job` varchar(50) DEFAULT NULL COMMENT '职务',
  `emp_birth` varchar(50) NOT NULL COMMENT '出生年月',
  `emp_email` varchar(50) NOT NULL COMMENT '电子邮箱',
  `emp_comphone` varchar(20) DEFAULT NULL COMMENT '办公室电话',
  `emp_mobilephone` varchar(20) NOT NULL COMMENT '个人电话',
  `emp_date` varchar(50) NOT NULL COMMENT '注册时间',
  `emp_remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `emp_createdate` varchar(50) NOT NULL COMMENT '创建时间',
  `emp_createname` varchar(50) NOT NULL COMMENT '创建者',
  `emp_updatedate` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `emp_updatename` varchar(50) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
