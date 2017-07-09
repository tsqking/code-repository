/**
 * Project Name:campus_community
 * File Name:StudyServiceImpl.java
 * Package Name:com.clps.service.impl
 * Date:2017年3月23日下午11:37:47
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clps.common.bean.Study;
import com.clps.common.exception.StudyServiceException;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.dao.StudyDao;
import com.clps.service.IStudyService;

/**
 * ClassName:StudyServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月23日 下午11:37:47 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class StudyServiceImpl implements IStudyService {
	@Autowired
	private StudyDao dao;
	private boolean flag;

	@Override
	public boolean insertStudy(Study study) throws StudyServiceException {
		if (study != null) {
			study.setS_date(DateFormat.getNow());
			return dao.insertStudy(study);
		}
		return flag;
	}

	@Override
	public PageVo<Study> listAllStudy(PageVo<Study> pv) throws StudyServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Study> allStudy = dao.listAllStudy(map);
		PageVo<Study> pageVo = new PageVo<>();
		if (allStudy != null) {
			pageVo.setList(allStudy);
			pageVo.setCount(Integer.toString(dao.countStudy(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public PageVo<Study> listAllStudyMng(PageVo<Study> pv) throws StudyServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Study> allStudy = dao.listAllStudyMng(map);
		PageVo<Study> pageVo = new PageVo<>();
		if (allStudy != null) {
			pageVo.setList(allStudy);
			pageVo.setCount(Integer.toString(dao.countStudyMng(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public boolean updateStudyMng(Study study) throws StudyServiceException {
		if (study != null) {
			study.setS_check_date(DateFormat.getNow());
			return dao.updateStudyMng(study);
		}
		return false;
	}

	@Override
	public boolean deleteStudyMng(List<Integer> idList) throws StudyServiceException {
		if (idList != null) {
			return dao.deleteStudyMng(idList);
		}
		return false;
	}

}
