/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : mms

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-02-23 14:43:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_menu
-- ----------------------------
DROP TABLE IF EXISTS `tbl_menu`;
CREATE TABLE `tbl_menu` (
  `mnu_id` varchar(20) NOT NULL COMMENT '菜单ID',
  `mnu_name` varchar(20) NOT NULL COMMENT '菜单名',
  `mnu_url` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `mnu_create_user` varchar(20) NOT NULL COMMENT '创建人',
  `mnu_create_time` varchar(30) NOT NULL COMMENT '创建时间',
  `mnu_update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `mnu_update_time` varchar(30) DEFAULT NULL COMMENT '更新时间',
  `mnu_parent_id` varchar(20) DEFAULT NULL COMMENT '父菜单编号',
  `mnu_child_id` varchar(20) DEFAULT NULL COMMENT '子菜单编号',
  `mnu_root_id` varchar(20) DEFAULT NULL COMMENT '根菜单编号',
  `mnu_logo_url` varchar(100) DEFAULT NULL COMMENT '图标地址',
  `mnu_desc` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `mnu_is_enable` varchar(2) DEFAULT '1' COMMENT '是否可用,1代表可用，0代表不可用,默认为1可用',
  PRIMARY KEY (`mnu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_menu
-- ----------------------------
INSERT INTO `tbl_menu` VALUES ('booking001', '预约管理', '', 'charles', '2017-02-22 18:37:51', 'charles', '2017-02-23 13:24:01', '', null, '', 'jsp/images/menuicon/16.png', '预约管理', '1');
INSERT INTO `tbl_menu` VALUES ('departmentlog001', '部门日志', '', 'charles', '2017-02-22 15:02:14', 'charles', '2017-02-23 13:29:51', 'log001', null, 'log001', 'jsp/images/menuicon/10.png', '部门日志', '1');
INSERT INTO `tbl_menu` VALUES ('dev001', '设备管理', '', 'charles', '2017-02-22 18:38:19', 'charles', '2017-02-23 13:26:37', 'booking001', null, 'booking001', 'jsp/images/menuicon/9.png', '设备管理', '1');
INSERT INTO `tbl_menu` VALUES ('devlog001', '设备日志', '', 'charles', '2017-02-22 15:01:21', 'charles', '2017-02-23 13:29:58', 'log001', null, 'log001', 'jsp/images/menuicon/11.png', '设备日志', '1');
INSERT INTO `tbl_menu` VALUES ('email001', '邮件管理', '', 'charles', '2017-02-22 12:37:42', 'charles', '2017-02-23 13:23:48', '', null, '', 'jsp/images/menuicon/25.png', '邮件管理', '1');
INSERT INTO `tbl_menu` VALUES ('gsgdsg', 'gdsgdsg', '', 'charles', '2017-02-23 14:04:53', null, null, 'root001', null, 'root001', 'jsp/images/menuicon/32.png', 'gdsgdsgsd', '0');
INSERT INTO `tbl_menu` VALUES ('impemail001', '星标邮件', '', 'charles', '2017-02-22 18:42:43', 'charles', '2017-02-23 13:25:22', 'email001', null, 'email001', 'jsp/images/menuicon/18.png', '星标邮件,用于存放重要的邮件', '1');
INSERT INTO `tbl_menu` VALUES ('log001', '日志管理', '', 'charles', '2017-02-22 12:36:40', 'charles', '2017-02-23 13:23:32', '', null, '', 'jsp/images/menuicon/14.png', '日志管理', '1');
INSERT INTO `tbl_menu` VALUES ('meettingroom001', '会议室管理', '', 'charles', '2017-02-22 18:39:14', 'charles', '2017-02-23 13:35:10', 'booking001', null, 'booking001', 'jsp/images/menuicon/36.png', '会议室管理', '1');
INSERT INTO `tbl_menu` VALUES ('menu001', '菜单管理', 'jsp/menu.jsp', 'charles', '2017-02-17 16:36:48', 'charles', '2017-02-23 12:52:25', 'root001', null, 'root001', 'jsp/images/menuicon/1.png', '菜单管理', '1');
INSERT INTO `tbl_menu` VALUES ('menulog001', '菜单日志', '', 'charles', '2017-02-22 12:37:18', 'charles', '2017-02-23 13:30:15', 'log001', null, 'log001', 'jsp/images/menuicon/12.png', '菜单日志', '1');
INSERT INTO `tbl_menu` VALUES ('myinfo001', '个人信息', '', 'charles', '2017-02-22 18:48:31', 'charles', '2017-02-23 13:24:38', 'root001', null, 'root001', 'jsp/images/menuicon/5.png', '我的个人信息', '1');
INSERT INTO `tbl_menu` VALUES ('recieve002', '收件箱', '', 'charles', '2017-02-22 13:03:43', 'charles', '2017-02-23 13:25:55', 'email001', null, 'email001', 'jsp/images/menuicon/23.png', '收件箱', '1');
INSERT INTO `tbl_menu` VALUES ('role001', '角色管理', 'jsp/role.jsp', 'charles', '2017-02-21 15:07:04', 'charles', '2017-02-23 12:52:41', 'root001', null, 'root001', 'jsp/images/menuicon/2.png', '角色管理', '1');
INSERT INTO `tbl_menu` VALUES ('root001', '系统管理', '', 'charles', '2017-02-17 16:36:06', 'charles', '2017-02-23 13:24:11', '', null, '', 'jsp/images/menuicon/28.png', '系统管理。各类管理的集合', '1');
INSERT INTO `tbl_menu` VALUES ('send001', '发件箱', '', 'charles', '2017-02-22 15:00:38', 'charles', '2017-02-23 13:25:40', 'email001', null, 'email001', 'jsp/images/menuicon/24.png', '发件箱', '1');
INSERT INTO `tbl_menu` VALUES ('user001', '用户管理', 'jsp/user.jsp', 'charles', '2017-02-22 18:41:24', 'charles', '2017-02-23 13:23:04', 'root001', null, 'root001', 'jsp/images/menuicon/3.png', '用户管理', '1');
INSERT INTO `tbl_menu` VALUES ('write001', '写邮件', '', 'charles', '2017-02-22 12:38:14', 'charles', '2017-02-23 13:26:19', 'email001', null, 'email001', 'jsp/images/menuicon/19.png', '写邮件', '1');
SET FOREIGN_KEY_CHECKS=1;
