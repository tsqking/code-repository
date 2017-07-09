/*
Navicat MySQL Data Transfer

Source Server         : MySql Connection
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : campus

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-04-18 13:52:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
`a_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录流水' ,
`a_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称' ,
`a_detail`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动详情' ,
`a_send_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动发布人用户名' ,
`a_send_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动发布日期' ,
`a_is_enable`  int(1) NOT NULL DEFAULT 1 COMMENT '是否可用,0：不可用，1：可用' ,
PRIMARY KEY (`a_id`),
INDEX `a_send_nickname` (`a_send_nickname`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=21

;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
BEGIN;
INSERT INTO `t_activity` VALUES ('1', '百灵杯', '歌唱比赛', 'charles', null, '1'), ('2', '篮球赛', '打篮球', 'charles', null, '1'), ('3', '足球赛', '踢足球', 'charles', null, '1'), ('4', '测试1', '测试', 'charles', null, '1'), ('5', '测试2', '测试', 'charles', null, '1'), ('6', '测试3', '测试', 'charles', null, '1'), ('7', '测试4', '测试', 'charles', null, '1'), ('8', '测试5', '测试', 'charles', null, '1'), ('9', '测试6', '测试', 'charles', null, '1'), ('10', '测试7', '测试', 'charles', null, '1'), ('11', '测试8', '测试', 'charles', null, '1'), ('12', '测试9', '测试', 'charles', null, '1'), ('13', '测试10', '测试', 'charles', null, '1'), ('14', '测试11', '测试', 'charles', null, '1'), ('15', '测试12', '测试', 'charles', null, '1'), ('16', '测试13', '测试', 'charles', null, '1'), ('17', '测试14', '测试', 'charles', null, '1'), ('18', '测试15', '测试', 'charles', null, '1'), ('19', '测试16', '测试', 'charles', null, '1'), ('20', '测试17', '测试', 'charles', null, '1');
COMMIT;

-- ----------------------------
-- Table structure for t_activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_apply`;
CREATE TABLE `t_activity_apply` (
`aa_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录流水号' ,
`aa_apply_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动报名人' ,
`aa_apply_date`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报名时间' ,
`aa_phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人联系方式' ,
PRIMARY KEY (`aa_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_activity_apply
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_black
-- ----------------------------
DROP TABLE IF EXISTS `t_black`;
CREATE TABLE `t_black` (
`b_id`  int(11) NOT NULL COMMENT '记录流水号' ,
`b_self_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录名' ,
`b_friend_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朋友登录名' ,
PRIMARY KEY (`b_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_black
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
`c_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id' ,
`c_send_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布者' ,
`c_rec_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接受者' ,
`c_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论时间' ,
`c_check_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人' ,
`c_check_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核时间' ,
`w_id`  int(11) NULL DEFAULT NULL COMMENT '被评论微博id' ,
`c_body`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容' ,
`c_is_enable`  int(1) NOT NULL DEFAULT 1 COMMENT '是否可用' ,
PRIMARY KEY (`c_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
`f_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录流水号' ,
`f_self_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录名' ,
`f_friend_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '好友登录名' ,
PRIMARY KEY (`f_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_maket_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_maket_apply`;
CREATE TABLE `t_maket_apply` (
`ma_id`  int(11) NOT NULL COMMENT '记录流水号' ,
`ma_nickname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品申请人' ,
`ma_apply_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请时间' ,
`ma_phone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人联系方式' ,
PRIMARY KEY (`ma_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_maket_apply
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_market
-- ----------------------------
DROP TABLE IF EXISTS `t_market`;
CREATE TABLE `t_market` (
`m_id`  int(10) NOT NULL AUTO_INCREMENT COMMENT '商品id' ,
`m_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名字' ,
`m_detail`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详情' ,
`m_send_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品发布人' ,
`m_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品发布时间' ,
`m_is_enable`  int(1) NULL DEFAULT 1 COMMENT '是否可用' ,
PRIMARY KEY (`m_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_market
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
`m_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '信息记录流水号' ,
`m_title`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '信息标题' ,
`m_content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '信息内容' ,
`m_send_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送者' ,
`m_rec_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者' ,
`m_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送时间' ,
`m_status`  int(1) NOT NULL DEFAULT 0 COMMENT '信息状态（1:已读，0：未读）' ,
`m_is_enable`  int(1) NOT NULL DEFAULT 0 COMMENT '是否已删除（0：未删除，1：已删除）' ,
PRIMARY KEY (`m_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_message
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
`n_id`  int(11) NOT NULL COMMENT '新闻id' ,
`n_title`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻标题' ,
`n_content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻内容' ,
`n_send_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻发布人' ,
`n_send_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻发布时间' ,
`n_is_enable`  int(1) NULL DEFAULT 1 COMMENT '新闻是否可用' ,
PRIMARY KEY (`n_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_news
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_study
-- ----------------------------
DROP TABLE IF EXISTS `t_study`;
CREATE TABLE `t_study` (
`s_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '记录流水' ,
`s_title`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题' ,
`s_content`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容' ,
`s_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间' ,
`s_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人用户名' ,
`s_check_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人用户名' ,
`s_check_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核时间' ,
`s_is_enable`  int(1) NULL DEFAULT 1 COMMENT '是否可用，0：不可用，1：可用' ,
PRIMARY KEY (`s_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of t_study
-- ----------------------------
BEGIN;
INSERT INTO `t_study` VALUES ('1', '中国历史', 'ihao', null, 'charles', null, null, '1'), ('2', '世界历史', 'agdsgasg', null, 'charles', null, null, '1');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
`u_id`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号或者是工号' ,
`u_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称' ,
`u_password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码' ,
`u_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名' ,
`u_gender`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别' ,
`u_age`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄' ,
`u_email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' ,
`u_password_ques`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密保问题' ,
`u_password_ans`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密保问题答案' ,
`u_address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址' ,
`u_phone`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码' ,
`u_point`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '积分' ,
`u_motto`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名、格言' ,
`u_face`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像' ,
`u_register_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间' ,
`u_type`  int(1) NOT NULL DEFAULT 1 COMMENT '用户类型，0为管理员，1为普通用户' ,
`u_is_enable`  int(1) NOT NULL DEFAULT 1 COMMENT '是否可用，0为不可用，1为可用' ,
PRIMARY KEY (`u_id`, `u_nickname`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', '测试1', '202CB962AC59075B964B07152D234B70', '', '', '0', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-05 17:16:03', '0', '1'), ('10', '测试10', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:17:00', '1', '1'), ('11', '测试11', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:17:05', '1', '1'), ('12', '测试12', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:17:10', '1', '1'), ('13', '测试13', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:17:15', '1', '1'), ('13223337', 'charles', 'E10ADC3949BA59ABBE56E057F20F883E', '谭世强', '男', '20', 'tsqking@163.com', '我是谁', '天', '江西吉安', '18679175705', '100', '我的愿望是世界和平', 'img/face/default.png', '2017-03-14 14:10:56', '0', '1'), ('13265', 'tom', '202CB962AC59075B964B07152D234B70', 'tom', '男', '18', 'tom@163.com', '我是谁', '天', '上海', '110', '100', null, 'img/face/default.png', '2017-03-24 10:47:58', '1', '1'), ('14', '测试14', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:17:19', '1', '1'), ('15', '测试15', '202CB962AC59075B964B07152D234B70', '', '', '23', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-05 17:17:24', '1', '1'), ('16', '测试16', '202CB962AC59075B964B07152D234B70', '', '', '10', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-05 17:17:29', '1', '1'), ('17', '测试17', '202CB962AC59075B964B07152D234B70', '', '', '30', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-05 17:17:33', '1', '1'), ('18', '测试18', '202CB962AC59075B964B07152D234B70', '', '', '33', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-09 19:41:14', '1', '1'), ('19', '测试19', '202CB962AC59075B964B07152D234B70', '', '', '32', 'ad', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-09 19:41:55', '1', '1'), ('2', '测试2', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:11', '1', '1'), ('20', '测试20', '202CB962AC59075B964B07152D234B70', '测试员20', '男', '20', '123@163.com', '我是谁', '天', '上海', '110', '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-09 20:09:52', '1', '1'), ('21', '测试21', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', null, 'img/face/default.png', '2017-04-09 20:19:14', '1', '1'), ('22', '测试22', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', null, 'img/face/default.png', '2017-04-09 20:19:23', '1', '1'), ('23', '测试23', '202CB962AC59075B964B07152D234B70', '', '', '18', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-09 20:19:37', '1', '1'), ('24', '测试24', '202CB962AC59075B964B07152D234B70', '', '', '18', '', '', '', '', '', '100', null, 'img/face/default.png', '2017-04-09 20:20:31', '1', '1'), ('27', '测试27', '202CB962AC59075B964B07152D234B70', '测试员', '男', '18', '123', '我是谁', '天', '上海', '110', '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-09 20:22:05', '1', '1'), ('3', '测试3', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:16', '1', '1'), ('4', '测试4', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:21', '1', '1'), ('5', '测试5', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:32', '1', '1'), ('6', '测试6', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:40', '1', '1'), ('7', '测试7', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:46', '1', '1'), ('8', '测试8', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:50', '1', '1'), ('9', '测试9', '202CB962AC59075B964B07152D234B70', null, null, null, null, '', '', null, null, '100', '我的愿望是世界和平', 'img/face/default.png', '2017-04-05 17:16:56', '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for t_weibo
-- ----------------------------
DROP TABLE IF EXISTS `t_weibo`;
CREATE TABLE `t_weibo` (
`w_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '微博id' ,
`w_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博发布用户名' ,
`w_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博发布时间' ,
`check_nickname`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人用户名' ,
`w_check_date`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核时间' ,
`w_topic`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博话题' ,
`w_content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微博正文' ,
`w_praise_num`  int(11) NULL DEFAULT NULL COMMENT '点赞数量' ,
`w_forward_num`  int(11) NULL DEFAULT NULL COMMENT '转发数量' ,
`w_img`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加图片' ,
`w_is_enable`  int(1) NOT NULL DEFAULT 1 COMMENT '是否可用' ,
PRIMARY KEY (`w_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of t_weibo
-- ----------------------------
BEGIN;
INSERT INTO `t_weibo` VALUES ('2', 'charles', null, 'charles', '2017-04-11 12:54:29', '运动', '跑步1000米', '5', '5', null, '1'), ('3', 'tom', null, 'charles', '2017-04-12 21:21:20', '看电视', '吸血鬼', '30', '20', null, '1'), ('4', 'tom', null, 'charles', '2017-04-10 15:50:34', '看电视', '吸血鬼', '30', '20', null, '1');
COMMIT;

-- ----------------------------
-- Auto increment value for t_activity
-- ----------------------------
ALTER TABLE `t_activity` AUTO_INCREMENT=21;

-- ----------------------------
-- Auto increment value for t_activity_apply
-- ----------------------------
ALTER TABLE `t_activity_apply` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_comment
-- ----------------------------
ALTER TABLE `t_comment` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_friend
-- ----------------------------
ALTER TABLE `t_friend` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_market
-- ----------------------------
ALTER TABLE `t_market` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_message
-- ----------------------------
ALTER TABLE `t_message` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_study
-- ----------------------------
ALTER TABLE `t_study` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for t_weibo
-- ----------------------------
ALTER TABLE `t_weibo` AUTO_INCREMENT=5;
