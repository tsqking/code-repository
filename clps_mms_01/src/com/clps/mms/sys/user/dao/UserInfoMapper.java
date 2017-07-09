/**
 * Project Name:clps_mms_copyright_201610
 * File Name:IUserDao.java
 * Package Name:com.clps.mms.sys.user.dao
 * Date:2016年10月18日下午4:57:47
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:IUserDao 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 下午4:57:47 
 * @author   tony.tan  
 * 	 
 */
@Repository("userDao")
public interface UserInfoMapper {
	/**
	 * 
	 * queryLoginUserInfoByName:查询用户登录信息. 
	 * @param name
	 * @return UserInfo
	 * @throws DataAccessException
	 */
	public UserInfo queryLoginUserInfoByName(String name) throws DataAccessException;
	/**
	 * 
	 * queryUserInfoByName:根据名字查询用户信息. 
	 * @param name
	 * @return UserInfo
	 * @throws DataAccessException
	 */
	public UserInfo queryUserInfoByName(String name)throws DataAccessException;
	/**
	 * 
	 * queryUserInfoById:根据id查询用户信息. 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public UserInfo queryUserInfoById(Long id)throws DataAccessException;
	/**
	 * 
	 * queryAll:查询所有的用户信息. 
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserInfo> queryAll()throws DataAccessException;
	/**
	 * 
	 * queryUserInfoCount:查询所有的用户总数. 
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Long queryUserInfoCount(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * queryUserInfoLst:(这里用一句话描述这个方法的作用). 
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserInfo> queryUserInfoLst(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * insertUserInfo:保存用户信息. 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public boolean insertUserInfo(UserInfo userInfo)throws DataAccessException;
	/**
	 * 
	 * updateUserInfo:更新用户信息. 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public boolean updateUserInfoById(UserInfo userInfo)throws DataAccessException;
	/**
	 * 
	 * updateUserPWD:修改用户密码. 
	 * @param username
	 * @return
	 * @throws DataAccessException
	 */
	public boolean updateUserPWD(Long userId,String password)throws DataAccessException;
	/**
	 * 
	 * deleteUserInfo:删除用户信息. 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public boolean deleteUserInfoById(Long userId)throws DataAccessException;
}

