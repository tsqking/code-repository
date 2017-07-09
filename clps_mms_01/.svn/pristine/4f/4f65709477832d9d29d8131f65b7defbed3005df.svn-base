/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : mms

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-01-11 15:06:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_email
-- ----------------------------
DROP TABLE IF EXISTS `tbl_email`;
CREATE TABLE `tbl_email` (
  `ema_id` bigint(20) NOT NULL COMMENT '用户ID',
  `ema_sender_addr` text NOT NULL COMMENT '发件人地址',
  `ema_accept_addr` text NOT NULL COMMENT '收件人地址',
  `ema_copy_addr` text COMMENT '抄送地址',
  `ema_title` text NOT NULL COMMENT '邮件标题',
  `ema_body` text COMMENT '邮件正文',
  `ema_model_id` bigint(20) DEFAULT NULL COMMENT '邮件模版ID',
  `ema_attach_filename` varchar(20) DEFAULT NULL COMMENT '附件名',
  `ema_create_time` varchar(10) NOT NULL COMMENT '创建时间',
  `ema_statue` varchar(4) NOT NULL COMMENT '邮件状态',
  `ema_create_user` varchar(50) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`ema_id`),
  CONSTRAINT `tbl_email_ibfk_1` FOREIGN KEY (`ema_id`) REFERENCES `tbl_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_email
-- ----------------------------
