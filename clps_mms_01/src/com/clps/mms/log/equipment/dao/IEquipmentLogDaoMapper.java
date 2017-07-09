package com.clps.mms.log.equipment.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.log.model.LogBean;
import com.clps.mms.util.exception.DataAccessException;

/**
 *package:package com.clps.mms.log.booking.dao;
 *since:JDK1.8
 *author:andy.wang
 *date:2017年1月21日 下午10:13:51
*/
@Repository("EquipmentLogDao")
public interface IEquipmentLogDaoMapper {
	/**
	* saveInfo:保存设备记录信息. 
	* @param LogBean
	* @throws DataAccessException
	*/
	public boolean saveEquipmentInfo(LogBean logEquipmentModel)throws DataAccessException;
	/**
	 * 
	 * selectInfo:查询设备记录信息. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public LogBean selectEquipmentInfo(String equipmentName)throws DataAccessException;
	/**
	 * 
	 * selectInfo:查询所有设备记录信息列表. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public List<LogBean> queryEquipmentInfoLst(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * 
	 * queryEquInfoCount:查询所有设备记录信息总数. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public Long queryEquInfoCount(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * queryEquipmentInfoLstByName:根据名称查询所有设备记录信息列表. 
	 * @param LogBean
	 * @throws DataAccessException
	 */
	public List<LogBean> queryEquipmentInfoLstByName(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * 
	 * queryAll:查询所有的设备记录信息. 
	 * @return
	 * @throws DataAccessException
	 */
	public List<LogBean> queryAll()throws DataAccessException;
	/**
	 * 
	 *queryRoleLogById:根据id查询设备日志表日志信息 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public LogBean queryEquipmentLogById(int id) throws DataAccessException;
	/**
	 * 
	 *queryCode:根据修改类型信息 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * */
	public  List<String > queryCode() throws DataAccessException;
}

