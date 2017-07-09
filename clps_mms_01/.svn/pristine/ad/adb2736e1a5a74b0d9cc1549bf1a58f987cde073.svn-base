/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserServiceImpl.java
 * Package Name:com.clps.mms.sys.user.service.impl
 * Date:2016年10月18日下午10:52:24
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clps.mms.log.sys.dao.ILogDao;
import com.clps.mms.log.sys.dao.impl.UserLogDaoImpl;
import com.clps.mms.log.sys.model.LogModel;
import com.clps.mms.sys.user.dao.UserInfoMapper;
import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.user.service.IUserService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.MD5;
import com.clps.mms.util.StringConnection;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.UserServiceException;
import com.clps.mms.util.log.UserLogHelper;

/**
 * ClassName:UserServiceImpl 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 下午10:52:24 
 * @author   tony.tan  
 * 	 
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource(name="userDao")
    private UserInfoMapper userDao;
    private ILogDao userLogDao=null;
    private Logger log=Logger.getLogger("console");
	public UserServiceImpl() {
		userLogDao=new UserLogDaoImpl();
	}
	/*public void setUserDao(UserInfoMapper userDao) {
		this.userDao = userDao;
	}
	@Resource(name="userDao")
	public UserServiceImpl(UserInfoMapper userDao) {
		this.setUserDao(userDao);;
	}*/
    

	/**
	 * TODO 添加用户.
	 * @see com.clps.mms.sys.user.service.IUserService#addUserInfo(com.clps.mms.sys.user.model.UserInfo)
	 */
	@Override
	public boolean addUserInfo(UserInfo userInfo) throws UserServiceException {
		if (this.findUserInfoByName(userInfo.getName())!=null) {
			log.error(UserServiceException.USERINFO_EXIST);
			throw new UserServiceException(UserServiceException.USERINFO_EXIST);
		}else{
			String password=userInfo.getPassword();
			userInfo.setPassword(MD5.getMD5Str(password));
			try {
				return userDao.insertUserInfo(userInfo);
			} catch (DataAccessException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return false;
	}
    /**
     * 
     * TODO 注册用户.
     * @see com.clps.mms.sys.user.service.IUserService#registerUserInfo(com.clps.mms.sys.user.model.UserInfo)
     */
	@Override
	public boolean register(UserInfo user) throws UserServiceException {
		try {
			if (this.findUserInfoByName(user.getName())!=null) {
				log.error(UserServiceException.USERINFO_EXIST);
				throw new UserServiceException(UserServiceException.USERINFO_EXIST);
			}else {
				String password=user.getPassword();
				user.setPassword(MD5.getMD5Str(password));
				Date date=new Date();
				user.setCreatedate(DateFormat.format(date));
				user.setUpdatedate(DateFormat.format(date));
				userDao.insertUserInfo(user);
				//LogModel model=UserLogHelper.getRegisterUserLog(user);
				//userLogDao.saveLog(model);
				return true;
			}
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		// TODO Auto-generated method stub
		return false;
		
	}
    /**
     * 
     * TODO 登录用户.
     * @see com.clps.mms.sys.user.service.IUserService#loginUserInfo(java.lang.String, java.lang.String)
     */
	@Override
	public UserInfo login(String name, String password) throws UserServiceException {
		try {
			System.out.println("name:"+name);
			UserInfo loginUserInfo= userDao.queryLoginUserInfoByName(name);
			if(loginUserInfo!=null){
				if (loginUserInfo.getPassword().equals(MD5.getMD5Str(password))) {
					log.info("登录成功");
					return loginUserInfo;
				}else {
					log.error(UserServiceException.WRONG_PWD);
					throw new UserServiceException(UserServiceException.WRONG_PWD);
				}
			}else {
				log.error(UserServiceException.USERINFO_NOT_EXIST);
				throw new UserServiceException(UserServiceException.USERINFO_NOT_EXIST);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
		return null;
	}
	/**
	 * 
	 * TODO 找回密码.
	 * @see com.clps.mms.sys.user.service.IUserService#findUserPWD(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean findUserPWD(String username, String password) throws UserServiceException {
		try {
			UserInfo userInfo=userDao.queryLoginUserInfoByName(username);
			System.out.println(userInfo);
			if (userInfo!=null) {
				userDao.updateUserPWD(userInfo.getId(),MD5.getMD5Str(password));
				LogModel model=UserLogHelper.getUpdatePwdUserLog(userInfo);
				userLogDao.saveLog(model);
				return true;
			}
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
	/**
	 * 
	 * TODO 查询所有的用户信息.
	 * @see com.clps.mms.sys.user.service.IUserService#findAllUserInfo()
	 */
	@Transactional
	@Override
	public List<UserInfo> findAllUserInfo() throws UserServiceException {
		
		try {
			return userDao.queryAll();
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 
	 * TODO 更新用户信息.
	 * @see com.clps.mms.sys.user.service.IUserService#updateUserInfo(com.clps.mms.sys.user.model.UserInfo)
	 */
	@Override
	public boolean updateUserInfoById(UserInfo userInfo) throws UserServiceException {
        try {
				UserInfo dbUserInfo=this.findUserInfoByName(userInfo.getName());
				if (null!=dbUserInfo) {
					//userInfo.setUpdatedate(new Date());
					return userDao.updateUserInfoById(userInfo);
					//LogModel model=UserLogHelper.getUpdateUserLog(dbUserInfo, userInfo);
					//userLogDao.saveLog(model);
					
				}
				else {
					log.error(UserServiceException.USERINFO_NOT_EXIST);
					throw new UserServiceException(UserServiceException.USERINFO_NOT_EXIST);
				}
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		return false;
	}
	/**
	 * 
	 * TODO 删除用户信息.
	 * @see com.clps.mms.sys.user.service.IUserService#deleteUserInfoById(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteUserInfoById(String managerName,String userName) throws UserServiceException {
		try {
			UserInfo userInfo=this.findUserInfoByName(userName);
			if(null!=userInfo){
			userDao.deleteUserInfoById(userInfo.getId());
			LogModel model=UserLogHelper.getDeleteUserLog(managerName,userName);
			userLogDao.saveLog(model);
			return true;
			}
			else {
				log.error(UserServiceException.USERINFO_NOT_EXIST);
				throw new UserServiceException(UserServiceException.USERINFO_NOT_EXIST);
			}
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
	/**
	 * 根据用户id查询用户信息.
	 * @see com.clps.mms.sys.user.service.IUserService#findUserInfoById(java.lang.Long)
	 */
	@Override
	public UserInfo findUserInfoById(Long id) throws UserServiceException {
		
		try {
			return userDao.queryUserInfoById(id);
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 
	 * 根据用户名查询用户信息.
	 * @see com.clps.mms.sys.user.service.IUserService#findUserInfoByName(java.lang.String)
	 */
	@Override
	public UserInfo findUserInfoByName(String name) throws UserServiceException {
		try {
			return userDao.queryUserInfoByName(name);
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	@Override
	public PageVo<UserInfo> findUserInfoLst(PageVo<UserInfo> pageVo) throws UserServiceException {
		Integer page = pageVo.getPage()-1;
		Integer limitPage = pageVo.getLimitPage();
		Map<String,String> where=pageVo.getWhere2();
		Map<String,String> sort=pageVo.getSort();
		Map<String,Object> map=new HashMap<>();
		map.put("offset", page);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		try {
			Long count=userDao.queryUserInfoCount(map);
			List<UserInfo> userList=userDao.queryUserInfoLst(map);
			pageVo.setList(userList);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return pageVo;
	}
	

}

