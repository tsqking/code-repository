/**
 * Project Name:clps_mms_copyright_201610
 * File Name:LogConent.java
 * Package Name:com.clps.mms.log.util
 * Date:2016年11月2日下午1:47:38
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.log;

import java.util.Date;

import com.clps.mms.log.sys.model.LogModel;
import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.util.DateFormat;


/**
 * ClassName:LogConent 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年11月2日 下午1:47:38 
 * @author   tony.tan  
 * 	 
 */
public class UserLogHelper{
private static final int update=1;
private static final int delete=2;
private static final int register=3;
private static final String CHANGE_INFO="更改信息";
private static final String NICKNAME="昵称:";
private static final String GENDER="性别:";
private static final String MOBILE="手机电话:";
private static final String EMAIL="电子邮箱";
private static final String INFO_BEGIN="将[";
private static final String INFO_MIDDLE="]改成了[";
private static final String INFO_END="]";
private static final String DELETE_USER="删除了";
private static final String IN="在";
private static final String LOGIN="进行了注册";
private static final String FIND_PWD="找回了密码";
private static final String DEPARTMENT="部门:";
private static final String POSITION="职位:";
private static final StringBuilder sb=new StringBuilder();
public static LogModel getUpdateUserLog(UserInfo oldUserInfo,UserInfo newUserInfo){
	sb.setLength(0);
	sb.append(oldUserInfo.getName()+CHANGE_INFO);
	if (!oldUserInfo.getNickname().equals(newUserInfo.getNickname())) {
		sb.append(NICKNAME);
		sb.append(INFO_BEGIN+oldUserInfo.getNickname()+INFO_MIDDLE+newUserInfo.getNickname()+INFO_END);
	}
	if (!oldUserInfo.getGender().equals(newUserInfo.getGender())) {
		sb.append(GENDER);
		sb.append(INFO_BEGIN+oldUserInfo.getGender()+INFO_MIDDLE+newUserInfo.getGender()+INFO_END);
	}
	if (!oldUserInfo.getMobNum().equals(newUserInfo.getMobNum())) {
		sb.append(MOBILE);
		sb.append(INFO_BEGIN+oldUserInfo.getMobNum()+INFO_MIDDLE+newUserInfo.getMobNum()+INFO_END);
	}
	if (!oldUserInfo.getEmail().equals(newUserInfo.getEmail())) {
		sb.append(EMAIL);
		sb.append(INFO_BEGIN+oldUserInfo.getEmail()+INFO_MIDDLE+newUserInfo.getEmail()+INFO_END);
	}
	if (!oldUserInfo.getDepartment().equals(newUserInfo.getDepartment())) {
		sb.append(DEPARTMENT);
		sb.append(INFO_BEGIN+oldUserInfo.getDepartment()+INFO_MIDDLE+newUserInfo.getDepartment()+INFO_END);
	}
	if (!oldUserInfo.getPosition().equals(newUserInfo.getPosition())) {
		sb.append(POSITION);
		sb.append(INFO_BEGIN+oldUserInfo.getPosition()+INFO_MIDDLE+newUserInfo.getPosition()+INFO_END);
	}
	LogModel model=new LogModel(null, oldUserInfo.getName(), update, sb.toString(),null,newUserInfo.getName(), null, 1);
	return model;
}
public static LogModel getDeleteUserLog(String managerName,String userName){
	sb.setLength(0);
	sb.append(managerName);
    sb.append(DELETE_USER+userName);
    LogModel model=new LogModel(null, managerName, delete, sb.toString(), new Date(),managerName, null, 1);
	return model;
}
public static LogModel getRegisterUserLog(UserInfo userInfo){
	sb.setLength(0);
	sb.append(userInfo.getName());
	sb.append(IN);
	sb.append(userInfo.getCreatedate());
	sb.append(LOGIN);
	LogModel model=new LogModel(null, userInfo.getName(),register , sb.toString(), null,userInfo.getUpdatename(), null, 1);
	return model;
}
public static LogModel getUpdatePwdUserLog(UserInfo userInfo){
	sb.setLength(0);
	sb.append(userInfo.getName());
	sb.append(FIND_PWD);
	LogModel model=new LogModel(null, userInfo.getName(), update, sb.toString(), new Date(), userInfo.getName(), null, 1);
	return model;
}
}

