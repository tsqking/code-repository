package com.clps.mms.log.department.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.log.model.LogBean;
import com.clps.mms.util.exception.DataAccessException;

/**
 *package:package com.clps.mms.log.department.dao;
 *since:JDK1.8
 *author:andy.wang
 *date:2017年1月21日 下午10:37:58
*/
@Repository("DepartmentLogDao")
public interface IDepartmentLogDaoMapper {
	/**
	* saveDepartmentInfo:保存部门记录信息. 
	* @param LogBean
	* @throws DataAccessException
	*/
	public boolean saveDepartmentInfo(LogBean logDepartmentModel)throws DataAccessException;
	/**
	 * 
	 * selectDepartmentInfo:查询部门记录信息. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public LogBean selectDepartmentInfo(String departmentName)throws DataAccessException;
	/**
	 * 
	 * queryDepartmentInfoLst:查询所有部门记录信息列表. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public List<LogBean> queryDepartmentInfoLst(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * 
	 * queryDepInfoCount:查询所有部门录信息总数. 
	 * @param LogModel
	 * @throws DataAccessException
	 */
	public Long queryDepInfoCount(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * queryDepartmentInfoLstByName:根据名称查询所有部门记录信息列表. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public List<LogBean> queryDepartmentInfoLstByName(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * 
	 * queryAll:查询所有的部门记录信息. 
	 * @return
	 * @throws DataAccessException
	 */
	public List<LogBean> queryAll()throws DataAccessException;
	/**
	 * 
	 *queryDepartmentLogById:根据id查询部门日志表日志信息 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public LogBean queryDepartmentLogById(int id) throws DataAccessException;
	/**
	 * 
	 *queryCode:根据修改类型信息 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * */
	public  List<String > queryCode() throws DataAccessException;
}
