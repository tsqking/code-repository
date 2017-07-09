/**
 * Project Name:TMP
 * File Name:GradeAnalysisByComAction.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisByCom.action
 * Date:2017年4月19日下午2:12:06
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisByCom.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.analysisReport.gradeAnalysisByCom.service.GradeAnalysisByComService;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.action.BaseAction;

/**
 * ClassName:GradeAnalysisByComAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:12:06 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
@ParentPackage("publicPackage")
@Namespace("/report")
@Controller
@Scope("prototype")
@Action("gradeByCom")
@Results({ @Result(name = "toGradeAnalysisByComPage", location = "../analysisReport/grade/gradeAnalysisByCom.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class GradeAnalysisByComAction extends BaseAction {
	@Autowired
	private GradeAnalysisByComService service;
	private static final long serialVersionUID = 1L;
	private Map<String, Object> resultMap;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String toGradeAnalysisByComPage() {
		return "toGradeAnalysisByComPage";
	}

	/**
	 * getDataByCom:(通过公司、时间段查询科目以及数据). <br/>
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String getDataByCom() throws Exception {

		// 前台查询条件
		String company = (String) this.httpRequest.getParameter("company");
		String start_time = (String) this.httpRequest.getParameter("start_time");
		String end_time = (String) this.httpRequest.getParameter("end_time");

		Map<String, Object> map = new HashMap<String, Object>();
		// 处理查询条件
		map.put("company", company);
		if (start_time != null && start_time != "") {
			map.put("start_time", DateTimeUtil.systemToDatabase(start_time));
		}
		if (end_time != null && end_time != "") {
			map.put("end_time", DateTimeUtil.systemToDatabase(end_time));
		}
		Map<String, List<Integer>> dataMap = service.selectDataByCom(map);

		// 定义前台需要的数据
		List<String> subList = new ArrayList<>();
		List<Integer> rangeList1 = new ArrayList<>();
		List<Integer> rangeList2 = new ArrayList<>();
		List<Integer> rangeList3 = new ArrayList<>();
		List<Integer> rangeList4 = new ArrayList<>();
		List<Integer> rangeList5 = new ArrayList<>();
		List<Integer> rangeList6 = new ArrayList<>();
		AjaxReturnInfo ajaxReturnInfo = null;
		Set<Entry<String, List<Integer>>> entrySet = dataMap.entrySet();
		for (Entry<String, List<Integer>> e : entrySet) {
			subList.add(e.getKey());
			rangeList1.add(e.getValue().get(0));
			rangeList2.add(e.getValue().get(1));
			rangeList3.add(e.getValue().get(2));
			rangeList4.add(e.getValue().get(3));
			rangeList5.add(e.getValue().get(4));
			rangeList6.add(e.getValue().get(5));
		}
		ajaxReturnInfo = AjaxReturnInfo.success("");
		ajaxReturnInfo.add("subList", subList);
		ajaxReturnInfo.add("rangeList1", rangeList1);
		ajaxReturnInfo.add("rangeList2", rangeList2);
		ajaxReturnInfo.add("rangeList3", rangeList3);
		ajaxReturnInfo.add("rangeList4", rangeList4);
		ajaxReturnInfo.add("rangeList5", rangeList5);
		ajaxReturnInfo.add("rangeList6", rangeList6);
		resultMap = ajaxReturnInfo.getReturnMap();
		return "json";
	}

}
