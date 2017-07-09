/**
 * Project Name:TMP
 * File Name:summaryAnalysisAction.java
 * Package Name:com.clps.tmp.analysisReport.summary.action
 * Date:2017年4月18日下午2:13:03
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.summary.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.analysisReport.summary.service.SummaryAnalysisService;
import com.clps.tmp.analysisReport.summary.vo.HeadVo;
import com.clps.tmp.analysisReport.summary.vo.SummaryVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.question.tag.vo.TagVo;

/**
 * ClassName:summaryAnalysisAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月18日 下午2:13:03 <br/>
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
@Action("summary")
@Results({ @Result(name = "toSummaryAnalysisPage", location = "../analysisReport/summary/summaryAnalysis.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class SummaryAnalysisAction extends BaseAction {

	@Autowired
	private SummaryAnalysisService service;

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

	public String toSummaryAnalysisPage() throws Exception {
		return "toSummaryAnalysisPage";
	}

	/**
	 * getHead:(获得表头标题栏数据). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String getHead() throws Exception {
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
		// 查询表头上的男女生人数和平均考试年龄
		HeadVo queryHead = service.queryHead(map);

		ajaxReturnInfo = AjaxReturnInfo.success("");
		ajaxReturnInfo.add("headData", queryHead);
		resultMap = ajaxReturnInfo.getReturnMap();
		return "json";
	}

	/**
	 * getData:(获得表格数据). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String getData() throws Exception {
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
		if (subject == null || subject == "") {
			map.put("subject", "JAVA");
		}

		BtTableVo<SummaryVo> queryData = service.queryData(map);
		ajaxReturnInfo = AjaxReturnInfo.success("");
		ajaxReturnInfo.add("data", queryData);
		resultMap = ajaxReturnInfo.getReturnMap();
		return "json";
	}

}
