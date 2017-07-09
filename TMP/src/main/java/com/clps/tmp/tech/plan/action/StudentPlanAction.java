package com.clps.tmp.tech.plan.action;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.TeaStuPlanService;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.EvalVo;
import com.clps.tmp.tech.plan.vo.PlanPaperStuHisVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;


@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("studentPlan")
@Results({
		@Result(name = "toPaperManagePage", location = "plan/personalPlan/studentPlan/paperManage.jsp"),
		@Result(name = "toFeedbackManage", location = "plan/personalPlan/studentPlan/feedbackManage.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class StudentPlanAction extends BaseAction {
	
	private static final long serialVersionUID = -2758158769903641176L;
	private HashMap<String, Object> resultMap;// json返回数据map
	private PlanVo planVo;//页面数据封装
	private PlanPaperVo planPaperVo;// 页面数据封装
	private PlanPaperStuHisVo planPaperStuHisVo;
	private EvalVo procVo;
	@Resource
	private TeaStuPlanService teaStuPlanService;
	
	
	public PlanPaperVo getPlanPaperVo() {
		return planPaperVo;
	}
	public void setPlanPaperVo(PlanPaperVo planPaperVo) {
		this.planPaperVo = planPaperVo;
	}
	public PlanVo getPlanVo() {
		return planVo;
	}
	public void setPlanVo(PlanVo planVo) {
		this.planVo = planVo;
	}
	public EvalVo getProcVo() {
		return procVo;
	}
	public void setProcVo(EvalVo procVo) {
		this.procVo = procVo;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public PlanPaperStuHisVo getPlanPaperStuHisVo() {
		return planPaperStuHisVo;
	}
	public void setPlanPaperStuHisVo(PlanPaperStuHisVo planPaperStuHisVo) {
		this.planPaperStuHisVo = planPaperStuHisVo;
	}
	
	
	/**
	 * @Description (TODO跳转学生测试管理页面)
	 * @return
	 */
	public String toPaperManagePage() {
		return "toPaperManagePage";
	}
	
	/**
	 * @Description (TODO跳转教师反馈界面)
	 * @return
	 */
	public String toFeedbackManage() {
		return "toFeedbackManage";
	}
	
	/**
	 * @Description (TODO学生作业列表)
	 * @return
	 * @throws Exception
	 */
	public String selectStuPaperHisInfo() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		dataMap.put("student_id", user.getId());
		BtTableVo<PlanPaperStuHisVo> bootStrapPageVo = teaStuPlanService.selectStuPaperHisList(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO教师反馈列表)
	 * @return
	 * @throws Exception
	 */
	public String selectFeedBackList() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		UserVo student = (UserVo) this.session.get(SystemConstant.USER);
		dataMap.put("student_id",student.getId());
		BtTableVo<EvalVo> bootStrapPageVo = teaStuPlanService.selectFeedbackList2(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
}
