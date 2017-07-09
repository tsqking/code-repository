/**
 * Project Name:clps_mms_01
 * File Name:DepartmentInfoMapper.java
 * Package Name:com.clps.mms.sys.department.dao
 * Date:2017年1月13日上午9:05:11
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.department.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.sys.department.model.DepartmentInfo;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:DepartmentInfoMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 上午9:05:11 <br/>
 * @author   ruby
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Repository("departmentDao")
public interface DepartmentInfoMapper {

	/**
	 * 
	 * insertDepartmentInfo:保存部门信息. 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public boolean insertDepartmentInfo(DepartmentInfo departmentInfo)throws DataAccessException;
	/**
	 * 
	 * queryDepartmentInfoById:根据id查询部门信息. 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public DepartmentInfo queryDepartmentInfoById(Long id)throws DataAccessException;
	/**
	 * 
	 * queryDepartmentInfoByName:根据名字查询部门信息. 
	 * @param name
	 * @return DepartmentInfo
	 * @throws DataAccessException
	 */
	public DepartmentInfo queryDepartmentInfoByName(String name)throws DataAccessException;
	/**
	 * 
	 * updateDepartmentInfoById:更新部门信息. 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public boolean updateDepartmentInfoById(DepartmentInfo departmentInfo)throws DataAccessException;
	/**
	 * 
	 * deleteDepartmentInfoById:删除部门信息. 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public boolean deleteDepartmentInfoById(Long id)throws DataAccessException;
	
	/**
	 * 
	 * queryDepartmentInfoCount:查询所有的部门总数. 
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Long queryDepartmentInfoCount(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * 
	 * queryDepartmentInfoLst:(这里用一句话描述这个方法的作用). 
	 * @return
	 * @throws DataAccessException
	 */
	public List<DepartmentInfo> queryDepartmentInfoLst(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * 
	 * queryAll:查询所有的部门信息. 
	 * @return
	 * @throws DataAccessException
	 */
	public List<DepartmentInfo> queryAll()throws DataAccessException;
}

