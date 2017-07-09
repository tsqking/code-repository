/**
 * Project Name:clps_mms_01
 * File Name:EquipmentServiceImpl.java
 * Package Name:com.clps.mms.booking.meetingroom.service.impl
 * Date:2017年1月18日下午2:53:34
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:EquipmentServiceImpl.java
 * Package Name:com.clps.mms.booking.meetingroom.service.impl
 * Date:2017年1月18日下午2:53:34
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.clps.mms.booking.meetingroom.dao.EquipmentDao;
import com.clps.mms.booking.meetingroom.model.Equipment;
import com.clps.mms.booking.meetingroom.service.IEquipmentService;
import com.clps.mms.log.equipment.dao.IEquipmentLogDaoMapper;
import com.clps.mms.log.model.LogBean;
import com.clps.mms.log.util.DateUtil;
import com.clps.mms.log.util.LogHelper;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.EquipmentServiceException;

/**
 * ClassName:EquipmentServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月18日 下午2:53:34 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("equipmentService")
public class EquipmentServiceImpl implements IEquipmentService{
	
	@Resource(name="equDao")
	private EquipmentDao equDao;
	private Logger log=Logger.getLogger("console");
	@Resource(name="EquipmentLogDao")
	private IEquipmentLogDaoMapper logDao;
	private LogHelper logHelper=null;
	private LogBean logBean = null;

	/**
	 * TODO 新增设备.
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#addEquipment(com.clps.mms.booking.meetingroom.model.Equipment)
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean addEquipment(Equipment equ) throws EquipmentServiceException {
		try {
			if (equDao.queryEquipmentByName(equ.getEquipmentName()) != null) {
				log.error(EquipmentServiceException.EQUIPMENT_EXIST);
				//throw new EquipmentServiceException(EquipmentServiceException.EQUIPMENT_EXIST);
			} else {
				boolean flag = equDao.insertEquipment(equ);
				if(flag){
					HashMap<String, Object> map = new HashMap<>();
					logBean = new LogBean();
					logBean.setName(equ.getEquipmentName());
					logBean.setCreateTime(DateUtil.getDate());
					logBean.setUpdateName(equ.getCreateName());
					if(null!=equ.getEquipmentName()){
						map.put("设备名称", equ.getEquipmentName().trim());
					}
					if(null!=equ.getEquipmentPrice()){
						map.put("设备单价", equ.getEquipmentPrice());
					}
					if(null!=equ.getEquipmentState()){
						map.put("设备状态", equ.getEquipmentState().trim());
					}
					if(null!=equ.getEquipmentUse()){
						map.put("设备使用者", equ.getEquipmentUse().trim());
					}
					if(null!=equ.getEquipmentType()){
						map.put("设备类型", equ.getEquipmentType().trim());
					}
					if(null!=equ.getEquipmentRemark()){
						map.put("备注", equ.getEquipmentRemark().trim());
					}
					logBean = logHelper.getAddLog(logBean, map);
					logDao.saveEquipmentInfo(logBean);
				}
				return flag;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * TODO 删除设备（可选）.
	 * @throws EquipmentServiceException 
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#deleteEquipmentById(java.lang.Long)
	 */
	@Override
	public boolean deleteEquipmentById(String name) throws EquipmentServiceException {
		try {
			Equipment eqp = equDao.queryEquipmentByName(name);
			System.out.println(eqp);
			if ( eqp!= null) {
				boolean b = equDao.deleteEquipmentById(eqp.getEquipmentId());
				
				return b;
			} else {
				log.error(EquipmentServiceException.EQUIPMENT_NOT_EXIST);
				throw new EquipmentServiceException(EquipmentServiceException.EQUIPMENT_NOT_EXIST);
			}
		} catch (DataAccessException e) {

			e.printStackTrace();

		}
		return false;
	}

	/**
	 * TODO 修改设备（可选）.
	 * @throws EquipmentServiceException 
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#updateEquipmentById(com.clps.mms.booking.meetingroom.model.Equipment)
	 */
	@Override
	public boolean updateEquipmentById(Equipment equ) throws EquipmentServiceException {
		
		try {
			Equipment DBequipment = this.queryEquipmentByName(equ.getEquipmentName());
			if (DBequipment != null) {
				boolean flag = equDao.updateEquipmentById(equ);
				if (flag) {
					
				
					HashMap<String, Object> map = new HashMap<>();
					logBean = new LogBean();
					logBean.setName(equ.getEquipmentName());
					logBean.setCreateTime(DateUtil.getDate());
					logBean.setUpdateName(equ.getCreateName());
					if(null!=equ.getEquipmentName()){
						map.put("设备名称", equ.getEquipmentName().trim());
					}
					if(null!=equ.getEquipmentPrice()){
						map.put("设备单价", equ.getEquipmentPrice());
					}
					if(null!=equ.getEquipmentState()){
						map.put("设备状态", equ.getEquipmentState().trim());
					}
					if(null!=equ.getEquipmentUse()){
						map.put("设备使用者", equ.getEquipmentUse().trim());
					}
					if(null!=equ.getEquipmentType()){
						map.put("设备类型", equ.getEquipmentType().trim());
					}
					if(null!=equ.getEquipmentRemark()){
						map.put("备注", equ.getEquipmentRemark().trim());
					}
					logBean = LogHelper.getUpdateLog(logBean, map);
					logDao.saveEquipmentInfo(logBean);
					}
				return flag;
			} else {
				log.error(EquipmentServiceException.EQUIPMENT_NOT_EXIST);
				throw new EquipmentServiceException(EquipmentServiceException.EQUIPMENT_NOT_EXIST);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * TODO 查询设备（可选）.
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#queryEquipmentById(java.lang.Long)
	 */
	@Override
	public Equipment queryEquipmentById(Long id) {
		Equipment equ;
		try {
			equ = equDao.queryEquipmentById(id);
			return equ;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * TODO 分页查询设备信息（可选）.
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#findEquipmenetLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<Equipment> findEquipmentLst(PageVo<Equipment> pageVo) {
		Integer page = pageVo.getPage();
		Integer limitPage = pageVo.getLimitPage();
		if(page<1){
			page=1;
		}
		Integer offset = (page-1)*limitPage;
		Map<String, String> where = pageVo.getWhere2();
		Map<String,String> sort=pageVo.getSort();
		Map<String,Object> map=new HashMap<>();
		map.put("offset", offset);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		map.put("condition", where.get("pageWhere1"));
		map.put("condition2", where.get("pageWhere2"));
		map.put("condition3", where.get("pageWhere3"));
		map.put("condition4", where.get("pageWhere4"));
		map.put("condition5", where.get("pageWhere5"));
		try {
			Long count = equDao.queryEquipmentCount(map);
			List<Equipment> equipmentLst = equDao.queryEquipmentLst(map);
			pageVo.setList(equipmentLst);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return pageVo;
	}

	/**
	 * TODO 根据名称查询设备.
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#queryEquipmentByName(java.lang.String)
	 */
	@Override
	public Equipment queryEquipmentByName(String name) {
		try {
			return equDao.queryEquipmentByName(name);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

	/**
	 * TODO 查询设备类型.
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#findAllType()
	 */
	@Override
	public List<String> findAllType() {
		try {
			List<String> list = equDao.queryAllType();
			return list;
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

	/**
	 * TODO 查询所有设备名称.
	 * @see com.clps.mms.booking.meetingroom.service.IEquipmentService#findAllNames()
	 */
	@Override
	public List<String> findAllNames() {
		try {
			List<String> names = equDao.queryAllNames();
			return names;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}

