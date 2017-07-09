package com.clps.tmp.core.sm.service;

import java.util.HashMap;
import java.util.Map;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.RoleVo;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * @author Seven
 *
 * 2015年10月19日
 */
public interface RoleService {
	/**
	 * 获取角色分页信息
	 */
	public PageVo<RoleVo> queryRolePage(PageVo<RoleVo> pageVo) throws Exception;
	/**
	 * 根据id删除角色
	 */
	public int deleteRole(String id) throws Exception;
	/**
	 * 根据id获取角色
	 */
	public RoleVo getRoleByID(String id) throws Exception;
	/**
	 * 更新角色
	 */
	public int updateRole(RoleVo role) throws Exception;
	/**
	 * 添加角色
	 */
	public int addRole(RoleVo role) throws Exception;
	/**
	 * 更新角色菜单对应
	 */
	public int updateRoleMenu(String role_id,Map<String,Object>[] paramMap) throws Exception;
	/**
	 * 获取角色人员对应界面分页数据
	 */
	public PageVo<UserVo> queryRolePersonPage(PageVo<UserVo> pageVo,String roleId) throws Exception;
	/**
	 * 更新某角色的用户关联关系
	 */
	public int updatePersonsInRole(Map<String,Object>[] paramMap, String operation) throws Exception;
}
