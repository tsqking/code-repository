/**
 * Project Name:clps_mms_01
 * File Name:RoleInfo.java
 * Package Name:com.clps.mms.sys.role.dao
 * Date:2017年1月12日下午10:06:00
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:RoleInfo.java
 * Package Name:com.clps.mms.sys.role.dao
 * Date:2017年1月12日下午10:06:00
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.role.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.sys.role.model.RoleInfo;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:RoleInfo 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2017年1月12日 下午10:06:00 
 * @author   lonnie
 * @version     
 * @since    JDK 1.8	 
 */
@Repository("RoleDao") 
public interface RoleInfoMapper {
	/**
	 * 
	 * queryRoleInfoByName:(根据名字查询角色信息). 
	 * @author lonnie
	 * @param name
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public RoleInfo queryRoleInfoByName(String name)throws DataAccessException;
	/**
	 * 
	 * queryRoleInfoByCreateName:(根据创建查询角色信息). 
	 * @author lonnie
	 * @param name
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public RoleInfo queryRoleInfoByCreateName(String CreateName)throws DataAccessException;
	/**
	 * 
	 * queryRoleInfoById:(根据id查询角色信息). 
	 * @author lonnie
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public RoleInfo queryRoleInfoById(Long id)throws DataAccessException;
	/**
	 * 
	 * queryAll:(查询所有的角色信息). 
	 * @author lonnie
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<RoleInfo> queryAll() throws DataAccessException;
	/**
	 * 
	 * insertRoleInfo:(新增角色信息). 
	 * @author lonnie
	 * @param roleinfo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean insertRoleInfo(RoleInfo roleinfo)throws DataAccessException;
	/**
	 * 
	 * updateRoleInfo:(更新角色信息). 
	 * @author lonnie
	 * @param roleinfo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean updateRoleInfoById(RoleInfo roleinfo)throws DataAccessException;
	/**
	 * 
	 * deleteRoleInfo:(删除角色信息). 
	 * @author lonnie
	 * @param roleinfo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean deleteRoleInfoById(Long id)throws DataAccessException;
	/**
	 * 
	 * queryUserInfoCount:查询所有的角色总数. 
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Long queryRoleInfoCount(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * queryUserInfoLst:(这里用一句话描述这个方法的作用). 
	 * @return
	 * @throws DataAccessException
	 */
	public List<RoleInfo> queryRoleInfoLst(Map<String, Object> map)throws DataAccessException;
}

