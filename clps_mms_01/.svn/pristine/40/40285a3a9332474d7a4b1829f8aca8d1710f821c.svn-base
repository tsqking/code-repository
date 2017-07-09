package com.clps.mms.log.department.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.clps.mms.log.department.dao.IDepartmentLogDaoMapper;
import com.clps.mms.log.department.service.IDepartmentLogService;
import com.clps.mms.log.model.LogBean;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.DataAccessException;


/**
 * package:package com.clps.mms.log.booking.service.impl; 
 * @since:JDK1.8
 * @author:andy.wang 
 * @date:2017年2月8日 下午2:19:17
 */
@Service("DepartmentLogService")
public class DepartmentLogService implements IDepartmentLogService {
	@Resource(name = "DepartmentLogDao")
	private IDepartmentLogDaoMapper DepartmentLogDao;
	private Map<String, Object> map;
	
	
	/* 
	 * @see com.clps.mms.log.booking.service.IDepartmentLogService#findEquInfoLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<LogBean> findDepartInfoLst(PageVo<LogBean> pageVo){
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
		System.out.println("where:" + where.get("pageWhere1"));
		System.out.println("where:" + where.get("pageWhere2"));
		System.out.println("where:" + where.get("pageWhere3"));
		System.out.println("where:" + where.get("pageWhere4"));
		map.put("condition1", where.get("pageWhere1"));
		map.put("condition2", where.get("pageWhere2"));
		map.put("condition3", where.get("pageWhere3"));
		map.put("condition4", where.get("pageWhere4"));
		try {
			Long count = DepartmentLogDao.queryDepInfoCount(map);
			List<LogBean> DepartmentList = DepartmentLogDao.queryDepartmentInfoLst(map);
			pageVo.setList(DepartmentList);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return pageVo;
	}

	
	/* 
	 * @see com.clps.mms.log.booking.service.IDepartmentLogService#findAllDepartmentInfo()
	 */
	@Override
	public List<LogBean> findAllDepartmentInfo(){
		try {
			DepartmentLogDao.queryAll();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 * @see com.clps.mms.log.booking.service.IDepartmentLogService#findDepartmentLogByName(java.lang.String)
	 */
	@Override
	public LogBean findDepartmentLogByName(String DepartmentName) {

		return null;
	}
	
	/* 
	 * @see com.clps.mms.log.booking.service.IDepartmentLogService#findDepartmentLogById(int)
	 */
	@Override
	public LogBean findDepartmentLogById(int DepartmentId) {
		try {
			DepartmentLogDao.queryDepartmentLogById(DepartmentId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* 
	 * @see com.clps.mms.log.booking.service.IDepartmentLogService#findCode()
	 */
	@Override
	public List<String> findCode() {
		try {
			List<String> list = DepartmentLogDao.queryCode();
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
