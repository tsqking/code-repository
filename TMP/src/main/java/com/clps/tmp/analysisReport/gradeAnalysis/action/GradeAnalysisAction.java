
package com.clps.tmp.analysisReport.gradeAnalysis.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.analysisReport.gradeAnalysis.service.GradeAnalysisService;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.action.BaseAction;
@ParentPackage("publicPackage")
@Namespace("/report")
@Controller
@Scope("prototype")
@Action("grade")
@Results({@Result(name="toGradeAnalysisPage",location="../analysisReport/grade/gradeAnalysis.jsp"),
	      @Result(name="json",type="json",params={"root","resultMap"})
        })
public class GradeAnalysisAction extends BaseAction {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> resultMap;
	@Resource
	private GradeAnalysisService gradeAnalysisService;
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getGradeAnalysisReport() throws Exception{
		AjaxReturnInfo ajaxReturnInfo=null;
		String subject=(String) this.httpRequest.getParameter("subject");
		String company=(String) this.httpRequest.getParameter("company");
		String start_time=(String) this.httpRequest.getParameter("start_time");
		String end_time=(String) this.httpRequest.getParameter("end_time");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("company", company);
		if(start_time!=null&&start_time!=""){
		map.put("start_time", DateTimeUtil.systemToDatabase(start_time));
		}
		if(end_time!=null&&end_time!=""){
		map.put("end_time", DateTimeUtil.systemToDatabase(end_time));
		}
		System.out.println("map:"+map);
		List<Double> dataList=gradeAnalysisService.getStuGradeData(map);
			ajaxReturnInfo=AjaxReturnInfo.success("");
			ajaxReturnInfo.add("data", dataList);
		resultMap=ajaxReturnInfo.getReturnMap();
		return "json";
	}
	/**
	 * 
	 * toGradeAnalysisPage:跳转到各成绩段人数分析
	 * @return
	 */
	public String toGradeAnalysisPage(){
		return "toGradeAnalysisPage";
	}

}

