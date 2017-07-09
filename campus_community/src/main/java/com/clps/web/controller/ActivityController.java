/**
 * Project Name:campus_community
 * File Name:ActivityController.java
 * Package Name:com.clps.web.controller
 * Date:2017年3月27日下午10:07:21
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.clps.common.bean.Activity;
import com.clps.common.bean.UserInfo;
import com.clps.common.exception.ActivityServiceException;
import com.clps.common.util.DataTableHelper;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.service.IActivityService;

/**
 * ClassName:ActivityController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月27日 下午10:07:21 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class ActivityController {

	@Autowired
	private IActivityService service;
	Map<String, Object> resultMap = new HashMap<>();
	private final String MESSAGE = "msg";

	/**
	 * listActivity:(分页查询所有可用活动). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "activity.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listActivity(@RequestBody Map<String, Object> map) {
		DataTableHelper<Activity> dth = new DataTableHelper<>();
		PageVo<Activity> pv = dth.getTableData(map);
		try {
			pv = service.listAllActivity(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (ActivityServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * listActivityMng:(分页查询所有活动). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listActivity.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listActivityMng(@RequestBody Map<String, Object> map) {
		DataTableHelper<Activity> dth = new DataTableHelper<>();
		PageVo<Activity> pv = dth.getTableData(map);
		try {
			pv = service.listActivityMng(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (ActivityServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * addActivityMng:(管理员添加活动). <br/>
	 * 
	 * @author Charles
	 * @param activity
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "addAcMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> addActivityMng(@RequestBody Activity activity) {
		try {
			if (service.addAcMng(activity)) {
				resultMap.put(MESSAGE, "添加成功");
			} else {
				resultMap.put(MESSAGE, "添加失败");
			}
		} catch (ActivityServiceException e) {
			resultMap.put(MESSAGE, "添加失败");
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * updateAcMng:(管理员更新活动). <br/>
	 * 
	 * @author Charles
	 * @param activity
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "updateAcMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateAcMng(@RequestBody Activity activity) {
		try {
			if (service.updateAcMng(activity)) {
				resultMap.put(MESSAGE, "更新成功");
			} else {
				resultMap.put(MESSAGE, "更新失败");
			}
		} catch (ActivityServiceException e) {
			resultMap.put(MESSAGE, "更新失败");
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * deleteAcMng:(管理员删除活动). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "deleteActivity.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteAcMng(@RequestBody List<Integer> idList) {
		if (idList != null) {
			for (int id : idList) {
				try {
					if (service.deleteAcMng(id)) {
						resultMap.put(MESSAGE, "删除成功");
					} else {
						resultMap.put(MESSAGE, "删除失败");
					}
				} catch (ActivityServiceException e) {
					resultMap.put(MESSAGE, "删除失败");
					e.printStackTrace();
				}
			}
		}
		return resultMap;
	}

	/**
	 * applyAct:(活动批量报名). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "applyAct.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> applyAct(@RequestBody List<Integer> idList, HttpSession session) {
		resultMap.clear();
		UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
		List<Map<String, Object>> newList = new ArrayList<>();
		if (idList != null) {
			for (Integer id : idList) {
				Map<String, Object> newMap = new HashMap<>();
				newMap.put("aa_apply_ac_id", id);
				newMap.put("aa_apply_nickname", userinfo.getU_nickname());
				newMap.put("aa_apply_date", DateFormat.getNow());
				newList.add(newMap);
			}
			try {
				if (service.applyAct(newList)) {
					resultMap.put(MESSAGE, "报名成功");
					return resultMap;
				}
				resultMap.put(MESSAGE, "报名失败");
			} catch (ActivityServiceException e) {
				resultMap.put(MESSAGE, "报名失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	@RequestMapping(value = "listAcApply.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAcApply(@RequestBody Map<String, Object> map) {
		DataTableHelper<Map<String, Object>> dth = new DataTableHelper<>();
		PageVo<Map<String, Object>> pv = dth.getTableData(map);
		try {
			pv = service.listAcApply(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (ActivityServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
