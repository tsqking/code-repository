/**
 * Project Name:TMP
 * File Name:GradeAnalysisBySubAction.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisBySub.action
 * Date:2017年4月19日下午2:07:40
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisBySub.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.analysisReport.gradeAnalysisBySub.service.GradeAnalysisBySubService;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.action.BaseAction;

/**
 * ClassName:GradeAnalysisBySubAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:07:40 <br/>
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
@Action("gradeBySub")
@Results({ @Result(name = "toGradeAnalysisBySub", location = "../analysisReport/grade/gradeAnalysisBySub.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class GradeAnalysisBySubAction extends BaseAction {

	@Autowired
	private GradeAnalysisBySubService service;

	private static final long serialVersionUID = 1L;
	private Map<String, Object> resultMap;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String toGradeAnalysisBySub() {
		return "toGradeAnalysisBySub";
	}

	public String getDataBySub() throws Exception {
		AjaxReturnInfo ajaxReturnInfo = null;
		// 前台查询条件
		String subject = (String) this.httpRequest.getParameter("subject");
		String start_time = (String) this.httpRequest.getParameter("start_time");
		String end_time = (String) this.httpRequest.getParameter("end_time");

		Map<String, Object> map = new HashMap<String, Object>();

		// 处理查询条件
		map.put("subject", subject);
		if (start_time != null && start_time != "") {
			map.put("start_time", DateTimeUtil.systemToDatabase(start_time));
		}
		if (end_time != null && end_time != "") {
			map.put("end_time", DateTimeUtil.systemToDatabase(end_time));
		}
		Map<String, List<Long>> mapcount = service.selectDataBySub(map);
		ajaxReturnInfo = AjaxReturnInfo.success("");
		ajaxReturnInfo.add("data", mapcount);
		resultMap = ajaxReturnInfo.getReturnMap();

		return "json";
	}
}
