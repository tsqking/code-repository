package com.clps.tmp.tech.plan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.TreeViewVoUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.common.vo.TreeViewVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.PersonalPlanService;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.EvalVo;
/**
 * 个人教学计划
 * @author Seven
 * 2015年12月28日
 */
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("personalPlan")
@Results({
		@Result(name = "toPersonalTeachPlanMegtPage", location = "plan/personalPlan/personalTeachPlanMegt.jsp"),
		@Result(name = "toTeacherReviewPlanPage", location = "plan/personalPlan/teacherReviewPlan.jsp"),
		@Result(name = "toUpdateTeachStatePage", location = "plan/personalPlan/updateTeachState.jsp"),
		@Result(name = "recordHistory", type = "redirect", location = "teacherpool!recordTechHistory.do?histVo.teacher_id=${planVo.teacher_id}&&histVo.class_id=${planVo.class_id}"
				+ "&&histVo.point_id=${planVo.point_id}&&histVo.starttime=${planVo.starttime}&&histVo.endtime=${planVo.endtime}&&histVo.address=${planVo.location}&&histVo.course_id=${planVo.course_id}"),
		@Result(name = "toProcEvalStudentPage", location = "plan/personalPlan/provEvalStudent.jsp"),
		@Result(name = "toPersonalStudyPlanMegtPage", location = "plan/personalPlan/personalStudyPlanMegt.jsp"),
		@Result(name = "toStudentReviewPlanPage", location = "plan/personalPlan/studentReviewPlan.jsp"),
		@Result(name = "toTFinalEvalSPage", location = "plan/personalPlan/finalEvalStudent.jsp"),
		@Result(name = "toGetStuRefEvalPage", location = "plan/personalPlan/getStuRefEval.jsp"),
		@Result(name = "toEvalTeachersPage", location = "plan/personalPlan/evalTeachers.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class PersonalPlanAction extends BaseAction {
	private static final long serialVersionUID = -2758158769903641176L;
	private HashMap<String, Object> resultMap;// json返回数据map
	private PlanVo planVo;//页面数据封装
	private EvalVo procVo;
	@Resource
	private PersonalPlanService personalPlanService;
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
	/**
	 * 跳转个人教学计划界面
	 * 2015年12月28日 Seven
	 */
	public String toPersonalTeachPlanMegtPage(){
		return "toPersonalTeachPlanMegtPage";
	}
	/**
	 * 获取‘我的教学计划’列表数据
	 * 2015年12月29日 Seven
	 * @throws Exception 
	 */
	public String getTeachPlanTable() throws Exception{
		DataTableUtil<PlanVo> du = new DataTableUtil<PlanVo>();
		PageVo<PlanVo> pv = du.getTableData(this.getHttpRequest());
		// 1.搜索条件
		// 1.1.精确查询
		// 1.2.模糊查询
		// 1.3.其他条件
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		HashMap<String,String> other=new HashMap<String,String>();
		other.put("teacher_id", user.getId());
		pv.setOther(other);
		// 3.获取数据
		pv = personalPlanService.getTeachPlanTable(pv);
		// 4.返回值
		resultMap = du.getReturnMap(pv);
		return "json";
	}
	/**
	 * 跳转教师查看教学计划界面
	 * 2015年12月30日 Seven
	 */
	public String toTeacherReviewPlanPage(){
		log.info("教师去往教学计划查看界面：Course ID:"+planVo.getCourse_id()+" Course Name:"+planVo.getCourse_id_name()+" Class ID:"+planVo.getClass_id()
				+" Class Name:"+planVo.getClass_id_name()+" Location:"+planVo.getLocation()+" Plan ID:"+planVo.getTeach_plan_id());
		return "toTeacherReviewPlanPage";
	}
	/**
	 * 树状显示某个具体教学计划
	 * 2015年12月30日 Seven
	 * @throws Exception 
	 */
	public String getTeacherTeachPlan() throws Exception{
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		log.info("教师查看教学计划：Plan ID:"+planVo.getTeach_plan_id()+" 教师人员ID:"+user.getId());
		ArrayList<TreeViewVo> al = personalPlanService.getTeachPlan(user.getId(),planVo.getTeach_plan_id());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		TreeViewVoUtil tu = new TreeViewVoUtil();
		resultMap = tu.getResultMap(rtn,al);
		return "json";
	}
	/**
	 * 跳转修改教学状态界面
	 * 2016年1月15日 Seven
	 */
	public String toUpdateTeachStatePage(){
		return "toUpdateTeachStatePage";
	}
	/**
	 * 修改教学状态
	 * 2016年1月15日 Seven
	 * @throws Exception 
	 */
	public String updateTeachState() throws Exception{
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		log.info("修改教学状态：教学ID-"+planVo.getId()+" 知识点ID-"+planVo.getPoint_id()+" 状态码-"+planVo.getFinish_state()+" 人员号-"+user.getId());
		int effect = personalPlanService.updateTeachState(planVo.getId(),planVo.getPoint_id(),user.getId(),planVo.getFinish_state());
		AjaxReturnInfo rtn=null;
		if(effect==1){
			rtn=AjaxReturnInfo.success("0");//修改成功
		}else if(effect==0){
			rtn=AjaxReturnInfo.success("1");//修改失败
		}else{
			rtn=AjaxReturnInfo.success("2");//未知错误
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 记录授课历史
	 * 2016年1月15日 Seven
	 */
	public String recordHistory(){
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		planVo.setTeacher_id(user.getId());
		return "recordHistory";
	}
	/**
	 * 跳转过程评价学员界面
	 * 2016年1月18日 Seven
	 */
	public String toProcEvalStudentPage(){
		return "toProcEvalStudentPage";
	}
	/**
	 * 根据课程、班级、知识点获取学员的过程评价情况
	 * 2016年1月19日 Seven
	 */
	public String getProcEvalList() throws Exception{
		ArrayList<EvalVo> procEvalList=personalPlanService.getProcEvalList(procVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("procEvalList", procEvalList);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 保存评价
	 * 2016年1月19日 Seven
	 */
	public String saveProcEval()throws Exception{
		UserVo teacher=(UserVo)this.session.get(SystemConstant.USER);
		procVo.setCreate_person(teacher.getName());
		procVo.setCreate_time(DateTimeUtil.nowToDatabase());
		procVo.setUpdate_person(teacher.getName());
		procVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		procVo.setTeacher_id(teacher.getId());
		int effect=personalPlanService.saveProcEval(procVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		if(effect==1){
			rtn.add("code", 1);//评价成功
		}else if(effect==2){
			rtn.add("code", 2);//修改成功
		}else{
			rtn.add("code", -1);//其他错误
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转个人学习计划管理界面
	 * 2016年1月22日 Seven
	 */
	public String toPersonalStudyPlanMegtPage(){
		return "toPersonalStudyPlanMegtPage";
	}
	/**
	 * 获取‘我的学习计划’列表数据
	 * 2015年12月29日 Seven
	 * @throws Exception 
	 */
	public String getLearnPlanTable() throws Exception{
		DataTableUtil<PlanVo> du = new DataTableUtil<PlanVo>();
		PageVo<PlanVo> pv = du.getTableData(this.getHttpRequest());
		// 1.搜索条件
		// 1.1.精确查询
		// 1.2.模糊查询
		// 1.3.其他条件
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		HashMap<String,String> other=new HashMap<String,String>();
		other.put("student_id", user.getId());
		pv.setOther(other);
		// 3.获取数据
		pv = personalPlanService.getLearnPlanTable(pv);
		// 4.返回值
		resultMap = du.getReturnMap(pv);
		return "json";
	}
	/**
	 * 跳转 学生查看具体教学计划 页面
	 * 2016年1月22日 Seven
	 */
	public String toStudentReviewPlanPage(){
		return "toStudentReviewPlanPage";
	}
	/**
	 * 树状显示某个具体教学计划
	 * 2015年12月30日 Seven
	 * @throws Exception 
	 */
	public String getStudentLearnPlan() throws Exception{
		log.info("学员查看教学计划：Plan ID:"+planVo.getTeach_plan_id());
		ArrayList<TreeViewVo> al = personalPlanService.getLearnPlan(planVo.getTeach_plan_id());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		TreeViewVoUtil tu = new TreeViewVoUtil();
		resultMap = tu.getResultMap(rtn,al);
		return "json";
	}
	/**
	 * 跳转教师最终评价学员界面
	 * 2016年1月25日 Seven
	 */
	public String toTFinalEvalSPage() throws Exception{
		return "toTFinalEvalSPage";
	}
	/**
	 * 获取教师最终评价学员的学员列表
	 * 2016年1月26日 Seven
	 */
	public String getFinalEvalStuList() throws Exception{
		UserVo teacher=(UserVo)this.session.get(SystemConstant.USER);
		procVo.setTeacher_id(teacher.getId());//采用当前人ID为教师ID
		ArrayList<EvalVo> finalEvalStuList=personalPlanService.getFinalEvalStuList(procVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("finalEvalStuList", finalEvalStuList);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 教师最终评价学员
	 * 2016年1月26日 Seven
	 */
	public String saveFinalToStuEval() throws Exception{
		UserVo teacher=(UserVo)this.session.get(SystemConstant.USER);
		procVo.setCreate_person(teacher.getName());
		procVo.setCreate_time(DateTimeUtil.nowToDatabase());
		procVo.setUpdate_person(teacher.getName());
		procVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		procVo.setTeacher_id(teacher.getId());
		procVo.setType("1");
		int effect=personalPlanService.saveFinalEval(procVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		if(effect==1){
			rtn.add("code", 1);//评价成功
		}else if(effect==2){
			rtn.add("code", 2);//修改成功
		}else{
			rtn.add("code", -1);//其他错误
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转获取学生平时表现小结页面
	 * 2016年1月26日 Seven
	 */
	public String toGetStuRefEvalPage(){
		return "toGetStuRefEvalPage";
	}
	/**
	 * 获取学员平时表现信息
	 * 2016年1月26日 Seven
	 */
	public String getStuRefEval() throws Exception{
		UserVo teacher=(UserVo)this.session.get(SystemConstant.USER);
		procVo.setTeacher_id(teacher.getId());
		HashMap<String,Object> info=personalPlanService.getStuRefEval(procVo,planVo.getTeach_plan_id());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("info", info);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转评价教师界面
	 * 2016年1月27日 Seven
	 */
	public String toEvalTeachersPage(){
		return "toEvalTeachersPage";
	}
	/**
	 * 根据教学计划获取授课教师下拉列表
	 * 2016年1月27日 Seven
	 */
	public String getTeachersByPlan() throws Exception{
		List<SelectVo> list=personalPlanService.getTeachersByPlan(planVo.getTeach_plan_id());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 获取学生评价某教师的结果
	 * 2016年1月27日 Seven
	 */
	public String getStuEvalTeacherRS() throws Exception{
		UserVo student=(UserVo)this.session.get(SystemConstant.USER);
		procVo.setStudent_id(student.getId());
		HashMap<String,Object> rsMap=personalPlanService.getStuEvalTeacherRS(procVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		if(rsMap==null){
			rtn.add("score", 0);
			rtn.add("comment", "");
		}else{
			rtn.add("score", rsMap.get("score"));
			rtn.add("comment", rsMap.get("comment"));
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 学员评价教师
	 * 2016年1月26日 Seven
	 */
	public String saveFinalToTeacherEval() throws Exception{
		UserVo student=(UserVo)this.session.get(SystemConstant.USER);
		procVo.setCreate_person(student.getName());
		procVo.setCreate_time(DateTimeUtil.nowToDatabase());
		procVo.setUpdate_person(student.getName());
		procVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		procVo.setStudent_id(student.getId());
		procVo.setType("2");
		int effect=personalPlanService.saveFinalEval(procVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		if(effect==1){
			rtn.add("code", 1);//评价成功
		}else if(effect==2){
			rtn.add("code", 2);//修改成功
		}else{
			rtn.add("code", -1);//其他错误
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
}
