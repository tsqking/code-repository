/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : mms

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-02-23 14:42:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_department
-- ----------------------------
DROP TABLE IF EXISTS `tbl_department`;
CREATE TABLE `tbl_department` (
  `dep_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dep_name` varchar(50) NOT NULL COMMENT '部门名称',
  `dep_desc` varchar(255) DEFAULT NULL COMMENT '部门描述',
  `dep_manager` varchar(50) DEFAULT NULL COMMENT '部门经理',
  `dep_tel` varchar(30) DEFAULT NULL COMMENT '部门电话',
  `dep_email` varchar(30) DEFAULT NULL COMMENT '部门邮箱',
  `dep_createdate` varchar(50) NOT NULL COMMENT '创建时间',
  `dep_createname` varchar(50) NOT NULL COMMENT '创建者',
  `dep_updatedate` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `dep_updatename` varchar(50) DEFAULT NULL COMMENT '更新者',
  `dep_isenable` int(11) unsigned zerofill NOT NULL DEFAULT '00000000001' COMMENT '有效数据',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_department
-- ----------------------------
INSERT INTO `tbl_department` VALUES ('1', 'TrainingRoom', '培训室', 'Alice', '021-1234-3444', '6372638@163.com', '2017-01-13', 'ruby', '2017-02-22 18:47:14', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('2', 'Mainframe', '', 'Lucas', '021-4273-3233', '', '2017-01-13', 'ruby', '2017-02-22 16:49:26', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('5', 'C++', 'C++培训室', 'Neil', '021-5454-4398', '', '2017-01-13 1624:41:26', 'ruby', null, null, '00000000001');
INSERT INTO `tbl_department` VALUES ('9', '.Net', '.net培训室', 'Lonnies', '021-3234-6574', '', '2017-01-13', 'ruby', null, null, '00000000001');
INSERT INTO `tbl_department` VALUES ('12', 'J2EE', 'J2EE培训室', 'Jenny', '021-1234-3444', 'alice@163.com', '2017-01-18 1424:41:53', 'ruby', null, null, '00000000001');
INSERT INTO `tbl_department` VALUES ('17', 'GeneralOffice', '综合办公室', 'Ruby', '021-1337-1837', '6372638@163.com', '2017-02-13 13:51:58', 'tom', '2017-02-22 17:35:58', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('24', 'ProjectControl', '项目管控', 'Andy', '021-4735-3894', 'fsafsdf', '2017-02-13 18:53:23', 'tom', '2017-02-22 17:36:03', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('25', 'FinanceDept', '财务部', 'Clement', '021-4384-5849', 'dasds', '2017-02-14 15:25:43', 'tom', '2017-02-22 17:36:08', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('26', 'HRDept', '人力资源部门', 'Crist', '021-1337-1837', '6372638@163.com', '2017-02-14 15:25:56', 'tom', '2017-02-22 17:36:19', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('27', 'ProjectDelivery', '项目支付部', 'Tony', '021-3135-9473', '6372638@163.com', '2017-02-14 15:26:09', 'tom', '2017-02-22 17:36:25', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('28', 'ProjectManagement', '项目管理部', 'Kevin', '021-1234-3444', 'gsdag', '2017-02-14 15:26:24', 'tom', '2017-02-22 17:36:30', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('29', 'TechAdvisorySerCen', '技术咨询服务中心', 'Victor', '021-4385-5948', 'bytyu', '2017-02-14 15:26:49', 'tom', '2017-02-22 17:37:17', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('39', 'Project payment', '', '', '', '', '2017-02-17 15:59:53', 'tom', null, null, '00000000000');
INSERT INTO `tbl_department` VALUES ('41', 'TrainingRoom', 'RRRRRR', 'QWERQ', 'RQWER', 'RRR', '2017-02-21 17:40:17', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('42', 'TrainingRoom', 'RRR', 'QWERQ', 'RRRR', 'RRR', '2017-02-21 17:41:01', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('43', 'TrainingRoom', 'rr', 'weqr', 'RRrRR', 'RRR', '2017-02-21 17:42:04', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('44', 'TrainingRoom', 'aa', 'Alice', '021-1234-3444', 'alice@163.com', '2017-02-21 17:56:44', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('45', 'TrainingRoom', '', 'sield', '', '', '2017-02-22 11:24:07', 'tom', '2017-02-22 11:35:42', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('46', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:12', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('47', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:17', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('48', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:23', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('49', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:28', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('50', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:34', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('51', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:38', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('52', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:43', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('53', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:47', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('54', 'TrainingRoom', '', '', '', '', '2017-02-22 11:24:54', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('55', 'TrainingRoom', '', '', '', '', '2017-02-22 11:25:00', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('56', 'TrainingRoom', '', '', '', '', '2017-02-22 11:25:24', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('57', 'TrainingRoom123', '', '', '', '', '2017-02-22 11:25:30', 'tom', '2017-02-22 18:21:10', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('58', 'TrainingRoom', '', '', '', '', '2017-02-22 11:25:36', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('59', 'TrainingRoom', '', '', '', '', '2017-02-22 14:33:12', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('60', 'TrainingRoom', '', '', '', '', '2017-02-22 14:46:33', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('61', 'TrainingRoom', '', '', '', '', '2017-02-22 14:49:08', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('62', 'fcregergr', '', '', '', '', '2017-02-22 16:30:48', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('63', 'cevtr', '', '', '', '', '2017-02-22 16:33:37', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('64', 'xxcfregrt', '', '', '', '', '2017-02-22 16:46:13', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('65', 'cgvthrcchtryhtyhet', '', '', '', '', '2017-02-22 16:49:39', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('66', 'cgvthrcchtryhtyhet1', '', '', '', '', '2017-02-22 16:50:02', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('67', 'TrainingRoom11', '', '', '', '', '2017-02-22 17:25:14', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('68', 'vewvythrewhr', '', '', '', '', '2017-02-22 17:31:42', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('69', 'C', '', '', '', '', '2017-02-22 17:34:03', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('70', 'cffefergerger', '', '', '', '', '2017-02-22 17:40:17', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('71', 'TrainingRoom1', '', '', '', '', '2017-02-22 18:12:26', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('72', 'TrainingRoom1111', '', '', '', '', '2017-02-22 18:12:35', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('73', 'ECRERQEW', '', '', '', '', '2017-02-22 18:13:03', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('74', 'TrainingRoomXXXXX', '', '', '', '', '2017-02-22 18:13:17', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('75', 'TrainingRoomXX33132', '', '', '', '', '2017-02-22 18:14:12', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('76', 'GVTR', '', '', '', '', '2017-02-22 18:14:30', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('77', 'CERCGRGR', '', '', '', '', '2017-02-22 18:21:16', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('78', 'vdfgdbd', '', '', '', '', '2017-02-22 18:21:32', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('79', '666666666666666', '', '', '', '', '2017-02-22 18:22:20', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('80', 'vbdcdfsvd', '', '', '', '', '2017-02-22 18:26:37', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('81', '777777777777777', '', '', '', '', '2017-02-22 18:27:23', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('82', 'vfvdf11', '', 'qerqwe', '', '', '2017-02-22 18:31:20', 'tom', '2017-02-23 12:38:39', 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('83', '6666666666', '', '', '', '', '2017-02-22 18:32:14', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('84', 'xcercgrg', '', '', '', '', '2017-02-22 18:47:19', 'tom', null, 'tom', '00000000000');
INSERT INTO `tbl_department` VALUES ('85', 'dadas', '', '', '', '', '2017-02-23 12:38:47', 'tom', null, 'tom', '00000000001');
INSERT INTO `tbl_department` VALUES ('86', 'dadas1', '', '', '', '', '2017-02-23 12:38:57', 'tom', null, 'tom', '00000000000');
