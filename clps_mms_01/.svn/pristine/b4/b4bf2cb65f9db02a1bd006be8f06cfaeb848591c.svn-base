package com.clps.mms.log.department.service;

import java.util.List;

import com.clps.mms.log.model.LogBean;
import com.clps.mms.sys.vo.PageVo;

/**
 * package:package com.clps.mms.log.booking.service; 
 * since:JDK1.8
 * author:andy.wang 
 * date:2017年2月8日 下午2:17:20
 */
public interface IDepartmentLogService {
	
	/**
	 * findDepartmentLogById
	 * @param DepartmentId
	 * @return
	 * @throws DepartmentLogServiceException
	 */
	public LogBean findDepartmentLogById(int DepartmentId);
	/**
	 * findDepartmentLogByName:由设部门称查询符合条件数据.
	 * @param DepartmentName
	 * @return
	 * @throws DepartmentLogServiceException
	 */
	public LogBean findDepartmentLogByName(String DepartmentName);
	/**
	 * findEquInfoLst:分页查询所有的数据.
	 * @param pageVo
	 * @return
	 * @throws DepartmentLogServiceException
	 */
	public PageVo<LogBean> findDepartInfoLst(PageVo<LogBean> pageVo) ;
	/**
	 * findAllDepartmentInfo:查询所有的设备记录信息.
	 * @return
	 * @throws DepartmentLogServiceException
	 */
	public List<LogBean> findAllDepartmentInfo() ;
	/**
	 * findCode查询修改类型
	 * @return
	 * @throws DepartmentLogServiceException
	 */
	public List<String> findCode() ;
}
