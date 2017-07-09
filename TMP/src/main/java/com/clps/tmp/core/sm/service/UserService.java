 package com.clps.tmp.core.sm.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * 用户相关service
  * @ClassName: UserService
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 上午9:47:42
 */
public interface UserService {
	
	//查询账户信息,根据账户
	public UserVo queryUserInfo(UserVo user) throws Exception;
	
	//用户翻页 where为查询条件 ,where2为模糊查询条件
	public PageVo<UserVo> queryUserPage(PageVo<UserVo> pv) throws Exception;
	
	//用户数据详细查询
	public UserVo detailUser(String id) throws Exception;
	
	//查找账户图片
	public String queryUserPhoto(String id) throws Exception;
	
	//更新用户信息
	public boolean updateUserInfo(UserVo user) throws Exception;
	
	//更新用户头像
	public boolean updateUserPhoto(String photo,String id) throws Exception;
	
	//新建用户
	public boolean addUserInfo(UserVo user,UserVo userNow) throws Exception;
	
	//删除用户
	public boolean deleteUser(UserVo user) throws Exception;
	
	//验证数据的唯一,主要是,登录名和手机号码
	public int checkData(String userName,String phone) throws Exception;
	
	//角色翻页 where为查询条件 ,where2为模糊查询条件
	public PageVo<UserVo> queryRolePage(PageVo<UserVo> pv) throws Exception;
	
	//获取用户的所有角色
	public ArrayList<String> queryUserRoles(String id) throws Exception;
	
	//去除-增加角色
	public boolean updateUserRole(String userId,String roleId,boolean type) throws Exception;
	
	//去除所有角色
	public boolean deleteUserAllRole(String userId) throws Exception;

	
	/**
	 * 批量添加用户
	 * @param list 用户信息
	 * @return map 存放信息如下：existMobile-用户已存在的手机号信息  userNameList-本次新加的用户名信息
	 * 2015年12月2日 Seven
	 */
	public HashMap<String, Object> batchAddUser(List<Map<String,Object>> list) throws Exception;
	/**
	 * 验证用户名是否存在
	 * 2015年12月2日 Seven
	 */
	public boolean existUserName(String userName) throws Exception;
	/**
	 * 验证手机号是否存在
	 * 2015年12月2日 Seven
	 */
	public boolean existMobile(String mobile) throws Exception;
}
