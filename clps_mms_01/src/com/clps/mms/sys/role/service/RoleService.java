/**
 * Project Name:clps_mms_01
 * File Name:RoleService.java
 * Package Name:com.clps.mms.sys.role.service
 * Date:2017年1月13日上午12:03:46
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:RoleService.java
 * Package Name:com.clps.mms.sys.role.service
 * Date:2017年1月13日上午12:03:46
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.role.service;

import java.util.List;

import com.clps.mms.sys.role.model.RoleInfo;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.RoleServiceException;
import com.clps.mms.util.exception.UserServiceException;

/**
 * ClassName:RoleService 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2017年1月13日 上午12:03:46 
 * @author   lonnie
 * @version     
 * @since    JDK 1.8	 
 */
public interface RoleService {
	/**
	 * 
	 * addRoleInfo:添加角色. 
	 * @param RoleInfo
	 * @return
	 * @throws RoleServiceException
	 */
	public boolean addRoleInfo(RoleInfo roleInfo)throws RoleServiceException;
	/**
	 * 
	 * findAllRoleInfo:查询所有的角色信息. 
	 * @return List
	 * @throws RoleServiceException
	 */
	public List<RoleInfo> findAllRoleInfo()throws RoleServiceException;
	/**
	 * 
	 * findRoleInfoByName:根据角色名查询角色信息. 
	 * @param Rolename
	 * @return RoleInfo
	 * @throws RoleServiceException
	 */
	public RoleInfo findRoleInfoByName(String roleName)throws RoleServiceException;
	/**
	 * 
	 * findRoleInfoByName:根据角创建人名查询角色信息. 
	 * @param Rolename
	 * @return RoleInfo
	 * @throws RoleServiceException
	 */
	public RoleInfo findRoleInfoByCreateName(String roleCreateName)throws RoleServiceException;
	/**
	 * 
	 * findRoleInfoById:根据角色id查询角色信息. 
	 * @param id
	 * @return
	 * @throws RoleServiceException
	 */
	public RoleInfo findRoleInfoById(Long id)throws RoleServiceException;
	/**
	 * 
	 * updateRoleInfo:更新角色信息. 
	 * @param RoleInfo
	 * @return
	 * @throws RoleServiceException
	 */
	public boolean updateRoleInfoById(RoleInfo roleInfo)throws RoleServiceException;
	/**
	 * 
	 * deleteRoleInfo:删除角色. 
	 * @param managerName
	 * @param RoleName
	 * @return
	 * @throws RoleServiceException
	 */
	public boolean deleteRoleInfoById(String managerName,String roleName)throws RoleServiceException;
	/**
	 * 
	 * findRoleInfoLst:分页查询所有的数据. 
	 * @param pageVo
	 * @return
	 * @throws UserServiceException
	 */
	public PageVo<RoleInfo> findRoleInfoLst(PageVo<RoleInfo> pageVo)throws RoleServiceException;
}

