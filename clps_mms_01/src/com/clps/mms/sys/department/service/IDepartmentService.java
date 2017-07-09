/**
 * Project Name:clps_mms_01
 * File Name:IDepartmentService.java
 * Package Name:com.clps.mms.sys.department.service
 * Date:2017年1月13日上午9:07:17
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.department.service;

import java.util.List;

import com.clps.mms.sys.department.model.DepartmentInfo;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.DepartmentServiceException;
import com.clps.mms.util.exception.UserServiceException;

/**
 * ClassName:IDepartmentService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 上午9:07:17 <br/>
 * @author   ruby
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface IDepartmentService {

	/**
	 * 
	 * addDepartmentInfo:(添加部门). <br/>
	 *
	 * @author ruby
	 * @param departmentInfo
	 * @return
	 * @throws DepartmentServiceException
	 * @since JDK 1.8
	 */
	public boolean addDepartmentInfo(DepartmentInfo departmentInfo)throws DepartmentServiceException;
	
	/**
	 * 
	 * findDepartmentInfoById:(根据部门id查询部门信息). <br/>
	 *
	 * @author ruby
	 * @param id
	 * @return
	 * @throws DepartmentServiceException
	 * @since JDK 1.8
	 */
	public DepartmentInfo findDepartmentInfoById(Long id)throws DepartmentServiceException;
	
	/**
	 * 
	 * findDepartmentInfoByName:根据部名称查询部门信息. 
	 * @param name
	 * @return DepartmentInfo
	 * @throws DepartmentServiceException
	 */
	public DepartmentInfo findDepartmentInfoByName(String name)throws DepartmentServiceException;
	
	/**
	 * 
	 * updateDepartmentInfoById:更新部门信息. 
	 * @param departmentInfo
	 * @return
	 * @throws DepartmentServiceException
	 */
	public boolean updateDepartmentInfoById(DepartmentInfo departmentInfo)throws DepartmentServiceException;
	
	/**
	 * 
	 * deleteDepartmentInfoById:删除部门信息. 
	 * @param managerName
	 * @param userName
	 * @return
	 * @throws UserServiceException
	 */
	public boolean deleteDepartmentInfoById(Long id)throws DepartmentServiceException;
	
	/**
	 * 
	 * findDepartmentInfoLst:分页查询所有的数据. 
	 * @param pageVo
	 * @return
	 * @throws DepartmentServiceException
	 */
	public PageVo<DepartmentInfo> findDepartmentInfoLst(PageVo<DepartmentInfo> pageVo)throws DepartmentServiceException;
	
	/**
	 * 
	 * findAllDepartmentInfo:查询所有的用户信息. 
	 * @return List
	 * @throws DepartmentServiceException
	 */
	public List<DepartmentInfo> findAllDepartmentInfo()throws DepartmentServiceException;
}

