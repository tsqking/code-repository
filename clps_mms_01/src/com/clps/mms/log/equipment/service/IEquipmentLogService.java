package com.clps.mms.log.equipment.service;

import java.util.List;

import com.clps.mms.log.model.LogBean;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.EquipmentLogServiceException;

/**
 * package:package com.clps.mms.log.booking.service; 
 * since:JDK1.8
 * author:andy.wang 
 * date:2017年2月8日 下午2:17:20
 */
public interface IEquipmentLogService {
	
	/**
	 * findEquipmentLogById
	 * @param equipmentId
	 * @return
	 * @throws EquipmentLogServiceException
	 */
	public LogBean findEquipmentLogById(int equipmentId) throws EquipmentLogServiceException;
	/**
	 * findEquipmentLogByName:由设备名称查询符合条件数据.
	 * @param equipmentName
	 * @return
	 * @throws EquipmentLogServiceException
	 */
	public LogBean findEquipmentLogByName(String equipmentName) throws EquipmentLogServiceException;
	/**
	 * findEquInfoLst:分页查询所有的数据.
	 * @param pageVo
	 * @return
	 * @throws EquipmentLogServiceException
	 */
	public PageVo<LogBean> findEquInfoLst(PageVo<LogBean> pageVo) throws EquipmentLogServiceException;
	/**
	 * findAllEquipmentInfo:查询所有的设备记录信息.
	 * @return
	 * @throws EquipmentLogServiceException
	 */
	public List<LogBean> findAllEquipmentInfo() throws EquipmentLogServiceException;
	/**
	 * findCode查询修改类型
	 * @return
	 * @throws EquipmentLogServiceException
	 */
	public List<String> findCode() throws EquipmentLogServiceException;
}
