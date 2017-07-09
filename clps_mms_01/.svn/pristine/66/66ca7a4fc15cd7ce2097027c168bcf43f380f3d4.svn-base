/**
 * Project Name:clps_mms_01
 * File Name:IEquipmentService.java
 * Package Name:com.clps.mms.booking.meetingroom.service
 * Date:2017年1月18日下午2:50:34
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:IEquipmentService.java
 * Package Name:com.clps.mms.booking.meetingroom.service
 * Date:2017年1月18日下午2:50:34
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.service;

import java.util.List;

import com.clps.mms.booking.meetingroom.model.Equipment;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.EquipmentServiceException;

/**
 * ClassName:IEquipmentService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月18日 下午2:50:34 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: IEquipmentService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月18日 下午2:50:34 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public interface IEquipmentService {
	/**
	 * 
	 * addEquipment:(新增设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param equ
	 * @return
	 * @throws EquipmentServiceException 
	 * @since JDK 1.8
	 */
	public boolean addEquipment(Equipment equ) throws EquipmentServiceException;
	/**
	 * 
	 * deleteEquipmentById:(删除设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param name
	 * @return
	 * @throws EquipmentServiceException 
	 * @since JDK 1.8
	 */
	public boolean deleteEquipmentById(String name) throws EquipmentServiceException;
	/**
	 * 
	 * updateEquipmentById:(修改设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param equ
	 * @return
	 * @throws EquipmentServiceException 
	 * @since JDK 1.8
	 */
	public boolean updateEquipmentById(Equipment equ) throws EquipmentServiceException;
	/**
	 * 
	 * queryEquipmentByName:(根据名称查询设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param name
	 * @return
	 * @since JDK 1.8
	 */
	public Equipment queryEquipmentByName(String name);
	/**
	 * 
	 * queryEquipmentById:(根据id查询设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	public Equipment queryEquipmentById(Long id);
	/**
	 * 
	 * findEquipmentLst:(分页查询设备信息). <br/>
	 * @author lacus.wang
	 * @param pageVo
	 * @return
	 * @since JDK 1.8
	 */
	public PageVo<Equipment> findEquipmentLst(PageVo<Equipment> pageVo);
	/**
	 * 
	 * findAllType:(查询设备类型). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @since JDK 1.8
	 */
	public List<String> findAllType();
	/**
	 * 
	 * findAllNames:(查询所有设备名称). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @since JDK 1.8
	 */
	public List<String> findAllNames();
}

