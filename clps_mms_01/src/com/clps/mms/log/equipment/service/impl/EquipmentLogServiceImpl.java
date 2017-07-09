package com.clps.mms.log.equipment.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.clps.mms.log.equipment.dao.IEquipmentLogDaoMapper;
import com.clps.mms.log.equipment.service.IEquipmentLogService;
import com.clps.mms.log.model.LogBean;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.EquipmentLogServiceException;

/**
 * package:package com.clps.mms.log.booking.service.impl; 
 * @since:JDK1.8
 * @author:andy.wang 
 * @date:2017年2月8日 下午2:19:17
 */
@Service("equipmentLogService")
public class EquipmentLogServiceImpl implements IEquipmentLogService {
	@Resource(name = "EquipmentLogDao")
	private IEquipmentLogDaoMapper equipmentLogDao;
	private Map<String, Object> map;
	
	
	/* 
	 * @see com.clps.mms.log.booking.service.IEquipmentLogService#findEquInfoLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<LogBean> findEquInfoLst(PageVo<LogBean> pageVo) throws EquipmentLogServiceException {
		Integer page = pageVo.getPage();
		Integer limitPage = pageVo.getLimitPage();
		if (page < 1) {
			page = 1;
		}
		Integer offset = (page - 1) * limitPage;
		Map<String, String> where = pageVo.getWhere2();
		Map<String, String> sort = pageVo.getSort();
		map = new HashMap<>();
		HashMap<String, String> dateTime = pageVo.getDateTime();
		map.put("offset", offset);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		map.put("condition1", where.get("pageWhere1"));
		map.put("condition2", where.get("pageWhere2"));
		map.put("condition3", where.get("pageWhere3"));
		map.put("condition4", where.get("pageWhere4"));
		try {
			Long count = equipmentLogDao.queryEquInfoCount(map);
			List<LogBean> equipmentList = equipmentLogDao.queryEquipmentInfoLst(map);
			pageVo.setList(equipmentList);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return pageVo;
	}

	
	/* 
	 * @see com.clps.mms.log.booking.service.IEquipmentLogService#findAllEquipmentInfo()
	 */
	@Override
	public List<LogBean> findAllEquipmentInfo() throws EquipmentLogServiceException {
		try {
			equipmentLogDao.queryAll();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 * @see com.clps.mms.log.booking.service.IEquipmentLogService#findEquipmentLogByName(java.lang.String)
	 */
	@Override
	public LogBean findEquipmentLogByName(String equipmentName) throws EquipmentLogServiceException {

		return null;
	}
	
	/* 
	 * @see com.clps.mms.log.booking.service.IEquipmentLogService#findEquipmentLogById(int)
	 */
	@Override
	public LogBean findEquipmentLogById(int equipmentId) throws EquipmentLogServiceException {
		try {
			equipmentLogDao.queryEquipmentLogById(equipmentId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 * @see com.clps.mms.log.booking.service.IEquipmentLogService#findCode()
	 */
	@Override
	public List<String> findCode() throws EquipmentLogServiceException {
		try {
			List<String> list = equipmentLogDao.queryCode();
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
