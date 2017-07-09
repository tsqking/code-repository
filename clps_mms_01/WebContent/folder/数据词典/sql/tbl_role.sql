用户表(tbl_user)
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `use_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `use_name` varchar(50) NOT NULL COMMENT '用户姓名',
  `use_gender` char(4) NOT NULL COMMENT '用户性别',
  `use_login_name` varchar(50) NOT NULL COMMENT '登录名',
  `use_password` varchar(50) NOT NULL COMMENT '登录密码',
  `use_mobile` varchar(20) NOT NULL COMMENT '联系方式',
  `use_email` varchar(50) NOT NULL COMMENT '电子邮箱',
  `use_create_name` varchar(50) NOT NULL COMMENT '创建人名',
  `use_create_time` varchar(50) NOT NULL COMMENT '创建日期',
  `use_update_name` varchar(50) DEFAULT NULL COMMENT '更新人名',
  `use_update_time` varchar(50) DEFAULT NULL COMMENT '更新日期',
  `use_description` varchar(255) NOT NULL COMMENT '用户描述',
  `use_is_enable` int(2) NOT NULL DEFAULT '1' COMMENT '有效数据',
  `use_default1` varchar(50) DEFAULT NULL COMMENT '缺省值1',
  `use_default2` varchar(50) DEFAULT NULL COMMENT '缺省值2',
  PRIMARY KEY (`use_id`),
  UNIQUE KEY `use_id` (`use_id`),
  UNIQUE KEY `use_name` (`use_name`),
  UNIQUE KEY `use_mobile` (`use_mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

角色表(tbl_role)
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `rol_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `rol_name` varchar(50) NOT NULL COMMENT '角色名称',
  `rol_create_name` varchar(50) NOT NULL COMMENT '创建人名',
  `rol_create_time` varchar(50) NOT NULL COMMENT '创建时间',
  `rol_update_name` varchar(50) DEFAULT NULL COMMENT '更新人名',
  `rol_update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `rol_description` varchar(255) NOT NULL COMMENT '角色描述',
  `rol_is_enable` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`rol_id`),
  UNIQUE KEY `rol_id` (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES ('4', '11111', 'lonnie', '2017-02-22 15:56:56', null, null, '11111', '0');
INSERT INTO `tbl_role` VALUES ('5', '11111', 'lonnie', '2017-02-22 15:57:22', null, null, '111', '1');
INSERT INTO `tbl_role` VALUES ('6', '2222', 'lonnie', '2017-02-22 15:58:07', null, null, '2222', '1');
INSERT INTO `tbl_role` VALUES ('7', '3333', 'lonnie', '2017-02-22 15:58:14', null, null, '33333', '1');
INSERT INTO `tbl_role` VALUES ('8', '44444', 'lonnie', '2017-02-22 15:58:20', null, null, '4444', '1');
INSERT INTO `tbl_role` VALUES ('9', '55555', 'lonnie', '2017-02-22 15:58:26', null, null, '55555', '1');
INSERT INTO `tbl_role` VALUES ('10', '6666', 'lonnie', '2017-02-22 15:58:31', null, null, '6666', '1');
INSERT INTO `tbl_role` VALUES ('11', '77777', 'lonnie', '2017-02-22 15:58:37', null, null, '77777', '1');
INSERT INTO `tbl_role` VALUES ('12', '88888', 'lonnie', '2017-02-22 15:58:48', null, null, '88888', '1');
INSERT INTO `tbl_role` VALUES ('13', '99999', 'lonnie', '2017-02-22 15:58:53', null, null, '9999', '1');
INSERT INTO `tbl_role` VALUES ('14', 'aaaaa', 'lonnie', '2017-02-22 15:59:03', null, null, 'aaaaa', '1');
INSERT INTO `tbl_role` VALUES ('15', 'bbbbb', 'lonnie', '2017-02-22 15:59:09', null, null, 'bbbbb', '1');
INSERT INTO `tbl_role` VALUES ('16', 'ccccc', 'lonnie', '2017-02-22 15:59:14', null, null, 'ccccc', '1');
INSERT INTO `tbl_role` VALUES ('17', 'ddddd', 'lonnie', '2017-02-22 15:59:20', null, null, 'ddddd', '1');
INSERT INTO `tbl_role` VALUES ('18', 'rrrrrr', 'lonnie', '2017-02-22 15:59:25', null, null, 'rrrrr', '1');
INSERT INTO `tbl_role` VALUES ('19', 'fffff', 'lonnie', '2017-02-22 15:59:32', null, null, 'fffff', '1');
INSERT INTO `tbl_role` VALUES ('20', 'ggggg', 'lonnie', '2017-02-22 15:59:39', null, null, 'ggggg', '1');
INSERT INTO `tbl_role` VALUES ('21', 'hhhhh', 'lonnie', '2017-02-22 16:00:02', 'jack', '2017-02-23 13:48:49', 'HHHHHHH', '1');
INSERT INTO `tbl_role` VALUES ('22', '111111', 'lonnie', '2017-02-22 16:08:48', null, null, '111', '1');
INSERT INTO `tbl_role` VALUES ('23', 'zzzzz', 'jack', '2017-02-15 12:03:55', null, null, '456', '1');
INSERT INTO `tbl_role` VALUES ('24', 'kkkkk', 'jack', '2017-02-16 11:44:33', null, '', 'fadsfads', '1');
INSERT INTO `tbl_role` VALUES ('25', 'llllll', 'jack', '2017-02-17 08:22:33', null, null, 'dsfadsfa', '1');
INSERT INTO `tbl_role` VALUES ('26', 'hhh', 'jack', '2017-02-23 13:46:38', null, null, 'hahahah', '0');
INSERT INTO `tbl_role` VALUES ('27', 'nnn', 'jack', '2017-02-23 13:47:18', null, null, 'hehhe', '1');
INSERT INTO `tbl_role` VALUES ('28', 'hhh', 'jack', '2017-02-23 13:48:18', null, null, 'wew', '1');

用户角色关系表(tbl_user_role_r)
DROP TABLE IF EXISTS `tbl_user_role_r`;
CREATE TABLE `tbl_user_role_r` (
  `ur_r_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录标识',
  `ur_r_use_id` varchar(50) NOT NULL COMMENT '用户编号',
  `ur_r_rol_id` varchar(50) NOT NULL COMMENT '角色编号',
  `ur_r_create_name` varchar(50) NOT NULL COMMENT '创建人名',
  `ur_r_create_time` varchar(50) NOT NULL COMMENT '创建时间',
  `ur_r_update_name` varchar(50) DEFAULT NULL COMMENT '更新人名',
  `ur_r_update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ur_r_id`),
  UNIQUE KEY `ur_r_id` (`ur_r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

菜单表(tbl_menu)
DROP TABLE IF EXISTS `tbl_menu`;
CREATE TABLE `tbl_menu` (
  `men_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `men_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `men_create_name` varchar(50) NOT NULL COMMENT '创建人名',
  `men_create_time` varchar(50) NOT NULL COMMENT '创建时间',
  `men_update_name` varchar(50) DEFAULT NULL COMMENT '更新人名',
  `men_update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `men_description` varchar(50) DEFAULT NULL COMMENT '菜单描述',
  PRIMARY KEY (`men_id`),
  UNIQUE KEY `men_id` (men_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

权限表（tbl_permission）
DROP TABLE IF EXISTS `tbl_permission`;		 
CREATE TABLE `tbl_permission`(
		 `per_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
		 `per_name` VARCHAR(50) NOT NULL UNIQUE COMMENT '权限名称',
	   	 `per_create_name` varchar(50) NOT NULL COMMENT '创建人名',
		 `per_create_time` varchar(50) NOT NULL COMMENT '创建时间',
		 `per_update_name` varchar(50) NOT NULL COMMENT '更新人名',
		 `per_update_time` varchar(50) NOT NULL COMMENT '更新时间',
		 `per_description` VARCHAR(255) NOT NULL COMMENT '权限描述',
		 `per_is_enable` int(2) NOT NULL DEFAULT '1' COMMENT '有效数据',
		 PRIMARY KEY(`per_id`),
		 UNIQUE KEY `per_id`(`per_id`)
		 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

		 
菜单权限关系表(tbl_menu_permission_r)
DROP TABLE IF EXISTS `tbl_menu_permission_r`;
CREATE TABLE `tbl_menu_permission_r` (
  `mp_r_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录标识',
  `mp_r_men_id` bigint(20) NOT NULL COMMENT '菜单编号',
  `mp_r_per_id` bigint(20) NOT NULL COMMENT '权限编号',
  `mp_r_create_name` varchar(50) NOT NULL COMMENT '创建人名',
  `mp_r_create_time` varchar(50) NOT NULL COMMENT '创建时间',
  `mp_r_update_name` varchar(50) NOT NULL COMMENT '更新人名',
  `mp_r_update_time` varchar(50) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`mp_r_id`),
  UNIQUE KEY `mp_r_id` (`mp_r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

菜单权限角色关系表(tbl_menu_permission_role_r)
DROP TABLE IF EXISTS `tbl_menu_permission_role_r`;
CREATE TABLE `tbl_menu_permission_role_r` (
  `mpr_r_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录标识',
  `mpr_r_mp_r_id` bigint(20) NOT NULL COMMENT '菜单权限关系编号',
  `mpr_r_role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `mpr_r_create_name` varchar(50) NOT NULL COMMENT '创建人名',
  `mpr_r_create_time` varchar(50) NOT NULL COMMENT '创建时间',
  `mpr_r_update_name` varchar(50) NOT NULL COMMENT '更新人名',
  `mpr_r_update_time` varchar(50) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`mpr_r_id`),
  UNIQUE KEY `mpr_r_id` (`mpr_r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;













		 
		 