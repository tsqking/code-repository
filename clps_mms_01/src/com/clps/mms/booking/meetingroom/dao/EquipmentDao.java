/**
 * Project Name:clps_mms_01
 * File Name:EquipmentDao.java
 * Package Name:com.clps.mms.booking.meetingroom.dao
 * Date:2017年1月18日上午10:28:25
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:EquipmentDao.java
 * Package Name:com.clps.mms.booking.meetingroom.dao
 * Date:2017年1月18日上午10:28:25
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.booking.meetingroom.model.Equipment;
import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:EquipmentDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月18日 上午10:28:25 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: EquipmentDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月18日 上午10:28:25 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
@Repository("equDao")
public interface EquipmentDao {
	/**
	 * 
	 * insertEquipment:(新增设备). <br/>
	 * @author lacus.wang
	 * @param equ
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean insertEquipment(Equipment equ) throws DataAccessException;
	/**
	 * 
	 * deleteEquipmentById:(根据id删除设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean deleteEquipmentById(Long id) throws DataAccessException;
	/**
	 * 
	 * updateEquipmentById:(根据id修改设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param equ
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean updateEquipmentById(Equipment equ) throws DataAccessException;
	/**
	 * 
	 * queryEquipmentById:(根据id查询设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public Equipment queryEquipmentById(Long id) throws DataAccessException;
	
	/**
	 * 
	 * queryEquipmentByName:(根据名称查询设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param name
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public Equipment queryEquipmentByName(String name) throws DataAccessException;
	/**
	 * 
	 * queryEquipmentLst:(分页查询设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<Equipment> queryEquipmentLst(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * queryEquipmentCount:(查询设备数量). <br/>
	 * @author lacus.wang
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public Long queryEquipmentCount(Map<String, Object> map)throws DataAccessException;
	/**
	 * 
	 * queryAllType:(查询设备类型). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<String> queryAllType() throws DataAccessException;
	public List<String> queryAllNames() throws DataAccessException;
}

