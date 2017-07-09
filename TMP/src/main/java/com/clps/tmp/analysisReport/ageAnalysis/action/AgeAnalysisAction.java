/**
 * Project Name:TMP
 * File Name:AgeAnalysisAction.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysis.action
 * Date:2017年4月13日下午2:52:43
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.ageAnalysis.action;

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
import com.clps.tmp.analysisReport.ageAnalysis.service.AgeAnalysisService;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.action.BaseAction;

/**
 * ClassName:AgeAnalysisAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月13日 下午2:52:43 <br/>
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
@Action("age")
@Results({ @Result(name = "toAgeAnalysisPage", location = "../analysisReport/ageDistribution/ageAnalysis.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class AgeAnalysisAction extends BaseAction {
	@Autowired
	private AgeAnalysisService service;

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> resultMap;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String toAgeAnalysisPage() {
		return "toAgeAnalysisPage";
	}

	public String getAgeAnalysisReport() throws Exception {
		AjaxReturnInfo ajaxReturnInfo = null;
		// 前台查询条件
		String subject = (String) this.httpRequest.getParameter("subject");
		String company = (String) this.httpRequest.getParameter("company");
		String start_time = (String) this.httpRequest.getParameter("start_time");
		String end_time = (String) this.httpRequest.getParameter("end_time");

		Map<String, Object> map = new HashMap<String, Object>();

		// 处理查询条件
		map.put("subject", subject);
		map.put("company", company);
		if (start_time != null && start_time != "") {
			map.put("start_time", DateTimeUtil.systemToDatabase(start_time));
		}
		if (end_time != null && end_time != "") {
			map.put("end_time", DateTimeUtil.systemToDatabase(end_time));
		}

		List<Double> resultData = service.getAgeData(map);
		ajaxReturnInfo = AjaxReturnInfo.success("");
		ajaxReturnInfo.add("data", resultData);
		resultMap = ajaxReturnInfo.getReturnMap();
		return "json";
	}

}
