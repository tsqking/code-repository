/**
 * Project Name:campus_community
 * File Name:StudyController.java
 * Package Name:com.clps.web.controller
 * Date:2017年3月23日下午11:40:49
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.clps.common.bean.Study;
import com.clps.common.exception.StudyServiceException;
import com.clps.common.util.DataTableHelper;
import com.clps.common.util.PageVo;
import com.clps.service.IStudyService;

/**
 * ClassName:StudyController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月23日 下午11:40:49 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class StudyController {
	@Autowired
	private IStudyService service;
	private final String MESSAGE = "msg";
	private Map<String, Object> resultMap = new HashMap<>();

	/**
	 * getAllStudy:(前台发表文章). <br/>
	 * 
	 * @author Charles
	 * @param study
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "publishStudy.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> publishStudy(@RequestBody Study study) {
		if (study != null) {
			try {
				if (service.insertStudy(study)) {
					resultMap.put(MESSAGE, "发表成功");
				} else {
					resultMap.put(MESSAGE, "发表失败");
				}
			} catch (StudyServiceException e) {
				resultMap.put(MESSAGE, "发表失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * listAllStudy:(前台分页查询所有可用文章). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listStudy.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAllStudy(@RequestBody Map<String, Object> map) {
		DataTableHelper<Study> dth = new DataTableHelper<>();
		PageVo<Study> pv = dth.getTableData(map);
		try {
			pv = service.listAllStudy(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (StudyServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * listAllStudy:(后台分页查询所有文章). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listStudyMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAllStudyMng(@RequestBody Map<String, Object> map) {
		DataTableHelper<Study> dth = new DataTableHelper<>();
		PageVo<Study> pv = dth.getTableData(map);
		try {
			pv = service.listAllStudyMng(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (StudyServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@RequestMapping(value = "updateStudyMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateStudyMng(@RequestBody Study study) {
		if (study != null) {
			try {
				if (service.updateStudyMng(study)) {
					resultMap.put(MESSAGE, "审核成功");
				} else {
					resultMap.put(MESSAGE, "审核失败");
				}
			} catch (StudyServiceException e) {
				resultMap.put(MESSAGE, "审核失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	@RequestMapping(value = "deleteStudyMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteStudyMng(@RequestBody List<Integer> idList) {
		if (idList != null) {
			try {
				if (service.deleteStudyMng(idList)) {
					resultMap.put(MESSAGE, "删除成功");
				} else {
					resultMap.put(MESSAGE, "删除失败");
				}
			} catch (StudyServiceException e) {
				resultMap.put(MESSAGE, "删除失败");
				e.printStackTrace();
			}
		}

		return resultMap;
	}
}
