/**
 * Project Name:clps_mms_copyright_201610
 * File Name:IUserService.java
 * Package Name:com.clps.mms.sys.user.service
 * Date:2016年10月18日下午10:38:30
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.user.service;

import java.util.List;
import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.UserServiceException;

/**
 * ClassName:IUserService 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 下午10:38:30 
 * @author   tony.tan  
 * 	 
 */
public interface IUserService {
/**
 * 
 * register:注册. 
 * @param user
 * @return
 * @throws UserServiceException
 */
public boolean register(UserInfo user)throws UserServiceException;
/**
 * 
 * login:登录. 
 * @param name
 * @param password
 * @return
 * @throws UserServiceException
 */
public UserInfo login(String name,String password)throws UserServiceException;
/**
 * 
 * findUserInfoLst:分页查询所有的数据. 
 * @param pageVo
 * @return
 * @throws UserServiceException
 */
public PageVo<UserInfo> findUserInfoLst(PageVo<UserInfo> pageVo)throws UserServiceException;
/**
 * 
 * addUserInfo:添加用户. 
 * @param userInfo
 * @return
 * @throws UserServiceException
 */
public boolean addUserInfo(UserInfo userInfo)throws UserServiceException;
/**
 * 
 * findAllUserInfo:查询所有的用户信息. 
 * @return List
 * @throws UserServiceException
 */
public List<UserInfo> findAllUserInfo()throws UserServiceException;
/**
 * 
 * findUserInfoByName:根据用户名查询用户信息. 
 * @param username
 * @return UserInfo
 * @throws UserServiceException
 */
public UserInfo findUserInfoByName(String username)throws UserServiceException;
/**
 * 
 * findUserInfoById:根据用户id查询用户信息. 
 * @param id
 * @return
 * @throws UserServiceException
 */
public UserInfo findUserInfoById(Long id)throws UserServiceException;
/**
 * 
 * updateUserInfo:更新用户信息. 
 * @param userInfo
 * @return
 * @throws UserServiceException
 */
public boolean updateUserInfoById(UserInfo userInfo)throws UserServiceException;
/**
 * 
 * deleteUserInfo:删除用户. 
 * @param managerName
 * @param userName
 * @return
 * @throws UserServiceException
 */
public boolean deleteUserInfoById(String managerName,String userName)throws UserServiceException;
/**
 * 
 * findUserPWD:找回用户密码. 
 * @param username
 * @param password
 * @return
 * @throws UserServiceException
 */
public boolean findUserPWD(String username,String password)throws UserServiceException;
}

