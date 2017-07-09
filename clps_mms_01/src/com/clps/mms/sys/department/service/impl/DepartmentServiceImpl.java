/**
 * Project Name:clps_mms_01
 * File Name:DepartmentServiceImpl.java
 * Package Name:com.clps.mms.sys.department.service.impl
 * Date:2017年1月13日上午9:06:54
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.department.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.clps.mms.log.sys.dao.ILogDao;
import com.clps.mms.sys.department.dao.DepartmentInfoMapper;
import com.clps.mms.sys.department.model.DepartmentInfo;
import com.clps.mms.sys.department.service.IDepartmentService;
import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.DepartmentServiceException;
import com.clps.mms.util.exception.RoleServiceException;

/**
 * ClassName:DepartmentServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 上午9:06:54 <br/>
 * @author   ruby
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService{

	@Resource(name="departmentDao")
    private DepartmentInfoMapper departmentDao; 
	private Logger log=Logger.getLogger("console");
	//定义返回值
	private static final boolean flag=true;
	
    /**
	 * Creates a new instance of DepartmentServiceImpl.
	 *
	 */
	public DepartmentServiceImpl() {
		
	}
	
	
	/**
	 * TODO 添加部门信息.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#addDepartmentInfo(com.clps.mms.sys.department.model.DepartmentInfo)
	 */
	@Override
	public boolean addDepartmentInfo(DepartmentInfo departmentInfo) throws DepartmentServiceException {
			try {
				if(this.findDepartmentInfoByName(departmentInfo.getName()) != null){
					log.error(DepartmentServiceException.DEPARTMENT_EXIST);
				}else{
					departmentDao.insertDepartmentInfo(departmentInfo);
					return flag;
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			
		
		return !flag;
	}


	/**
	 * TODO 根据部门id查询部门信息.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#findDepartmentInfoById(java.lang.Long)
	 */
	@Override
	public DepartmentInfo findDepartmentInfoById(Long id) throws DepartmentServiceException {
		DepartmentInfo department=null;
		if(id != null){
			try {
				department = departmentDao.queryDepartmentInfoById(id);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		return department;
	}


	/**
	 * TODO 根据id更新部门信息.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#updateDepartmentInfoById(com.clps.mms.sys.department.model.DepartmentInfo)
	 */
	@Override
	public boolean updateDepartmentInfoById(DepartmentInfo departmentInfo) throws DepartmentServiceException {
		if(departmentInfo.getId()!=null){
			try {
			    departmentDao.updateDepartmentInfoById(departmentInfo);
				return flag;
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		return !flag;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#deleteDepartmentInfoById(java.lang.Long)
	 */
	@Override
	public boolean deleteDepartmentInfoById(Long id) throws DepartmentServiceException {
		
		try {
			 departmentDao.deleteDepartmentInfoById(id);
			 return flag;
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		return !flag;
	}


	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#findDepartmentInfoLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<DepartmentInfo> findDepartmentInfoLst(PageVo<DepartmentInfo> pageVo) throws DepartmentServiceException {
		Integer page = pageVo.getPage();
		Integer limitPage = pageVo.getLimitPage();
		if(page<1){
			page = 1;
		}
		Integer offset = (page - 1) * limitPage;
		Map<String,String> where=pageVo.getWhere2();
		Map<String,String> sort=pageVo.getSort();
		Map<String,Object> map=new HashMap<>();
		map.put("offset", offset);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		map.put("condition1", where.get("pageWhere1"));
		map.put("condition2", where.get("pageWhere2"));
		try {
			Long count=departmentDao.queryDepartmentInfoCount(map);
			List<DepartmentInfo> departmentList=departmentDao.queryDepartmentInfoLst(map);
			pageVo.setList(departmentList);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return pageVo;
	}


	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#findAllDepartmentInfo()
	 */
	@Override
	public List<DepartmentInfo> findAllDepartmentInfo() throws DepartmentServiceException {
		
		try {
			return departmentDao.queryAll();
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}


	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.clps.mms.sys.department.service.IDepartmentService#findDepartmentInfoByName(java.lang.String)
	 */
	@Override
	public DepartmentInfo findDepartmentInfoByName(String name) throws DepartmentServiceException {
		try {
			return departmentDao.queryDepartmentInfoByName(name);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

}

