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
import com.clps.tmp.common.util.TreeViewVoUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.common.vo.TreeViewVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.PlanService;
import com.clps.tmp.tech.plan.service.TechPaperService;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 教学计划管理-管理员
 * 
 * @ClassName: PlanAction
 * @Description: TODO
 * @author Comsys-liuchen
 * @date 2015年12月3日 下午2:24:50
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("plan")
@Results({
		@Result(name = "toPlanManagementPage", location = "plan/planManagement.jsp"),
		@Result(name = "toPlanChangeInfo", location = "plan/planChangeInfo.jsp"),
		@Result(name = "toPlanDetailInfo", location = "plan/planDetailInfo.jsp"),
		@Result(name = "toPlanEdit", location = "plan/planEdit.jsp"),
		@Result(name = "toPlanDetail", location = "plan/planDetail.jsp"),
		@Result(name = "toPlanAdd", location = "plan/planAdd.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class PlanAction extends BaseAction implements ModelDriven {

	private HashMap<String, Object> resultMap;// json返回数据map
	private PlanVo planVo;

	@Resource
	private PlanService planService; // 业务处理service
	@Resource
	private TechPaperService techPaperService; // 业务处理service

	@Override
	public Object getModel() {
		if (planVo == null) {
			planVo = new PlanVo();
		}
		return planVo;
	}

	public PlanVo getPlanVo() {
		return planVo;
	}

	public void setPlanVo(PlanVo planVo) {
		this.planVo = planVo;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * 跳转计划管理首页 toPlanManagementPage(这里用一句话描述这个方法的作用) TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * @Title: toPlanManagementPage
	 * @Description: Comsys-liuchen
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String toPlanManagementPage() {
		return "toPlanManagementPage";
	}
	
	/**
	 * 跳转编辑计划列表
	  * toPlanEdit(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toPlanEdit
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toPlanEdit()throws Exception{
		return "toPlanEdit";
	}
	
	/**
	 * 跳转详细页面
	  * toPlanDetail(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toPlanDetail
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toPlanDetail()throws Exception{
		return "toPlanDetail";
	}
	
	/**
	 * 跳转菜单添加页面
	  * toPlanAdd(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toPlanAdd
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toPlanAdd()throws Exception{
		return "toPlanAdd";
	}
	
	/**
	 * 跳转改变时间的方法
	  * toPlanChangeInfo(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toPlanChangeInfo
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toPlanChangeInfo()throws Exception{
		return "toPlanChangeInfo";
	}
	
	/**
	 * 跳转知识点详细页面
	  * toPlanDetailInfo(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toPlanDetailInfo
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toPlanDetailInfo()throws Exception{
		return "toPlanDetailInfo";
	}

	/**
	 * 计划分页查询 getPlanPage(这里用一句话描述这个方法的作用) TODO(这里描述这个方法适用条件 – 可选)
	 * @Title: getPlanPage
	 * @Description: Comsys-liuchen
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String getPlanPage() throws Exception {
		DataTableUtil<PlanVo> du = new DataTableUtil<PlanVo>();
		PageVo<PlanVo> pv = du.getTableData(this.getHttpRequest());
		// 1.搜索条件
		// 1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if (planVo.getCourse_id() != null && !planVo.getCourse_id().equals("")) {
			where1.put("course_id", planVo.getCourse_id());
		}
		if (planVo.getClass_id() != null && !planVo.getClass_id().equals("")) {
			where1.put("class_id", planVo.getClass_id());
		}
		pv.setWhere1(where1);
		// 1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		pv.setWhere2(where2);
		// 1.3.时间范围
		HashMap<String, String> dateTime = new HashMap<String, String>();
		if (planVo.getCreate_time() != null
				&& !planVo.getCreate_time().equals("")) {
			dateTime.put("create_time", planVo.getCreate_time());
		}
		if (planVo.getUpdate_time() != null
				&& !planVo.getUpdate_time().equals("")) {
			dateTime.put("update_time", planVo.getUpdate_time());
		}
		if (planVo.getStart_time() != null
				&& !planVo.getStart_time().equals("")) {
			dateTime.put("start_time", planVo.getStart_time());
		}
		if (planVo.getEnd_time() != null
				&& !planVo.getEnd_time().equals("")) {
			dateTime.put("end_time", planVo.getEnd_time());
		}
		pv.setDateTime(dateTime);
		// 3.获取数据
		pv = planService.queryPlanPage(pv);
		// 4.返回值
		resultMap = du.getReturnMap(pv);
		return "json";
	}
	
	/**
	 * 课程下拉框
	  * getAllCourseName_Id(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getAllCourseName_Id
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getAllCourseName_Id() throws Exception{
		AjaxReturnInfo rtn=null;
		List<SelectVo> list=planService.getAllCourseName_Id();
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	
	/**
	 * 班级下拉框
	  * getAllClassName_Id(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getAllClassName_Id
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getAllClassName_Id() throws Exception{
		AjaxReturnInfo rtn=null;
		List<SelectVo> list=planService.getAllClassName_Id();
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	
	/**
	 * 获取教学计划知识点
	  * getPlanPoint(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getPlanPoint
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getPlanPoint() throws Exception{
		String planId = planVo.getId();
		String courseId = planVo.getCourse_id();
		String type = planVo.getType();
		boolean f = false;
		if(type!=null&&type.equals("reSet")){
			f = true;
		}
		ArrayList<TreeViewVo> al = planService.getPlanPoint(planId,planVo,courseId,f);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		TreeViewVoUtil tu = new TreeViewVoUtil();
		resultMap = tu.getResultMap(rtn,al);
		return "json";
	}
	
	
	/**
	 * 获取所有老师的的id和名字的下拉框
	  * getAllTeacherSelect(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getAllTeacherSelect
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getAllTeacherSelect()throws Exception{
		List<SelectVo> li = planService.getAllTeacherSelect();
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("options", li);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	
	/**
	 * 获取知识点完成状态
	  * getPoint_Finish_state(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getPoint_Finish_state
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getPoint_Finish_state()throws Exception{
		List<SelectVo> li = planService.getPoint_Finish_state();
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("options", li);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	
	/**
	 * 修改计划起始时间
	  * updatePlanStartTime(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: updatePlanStartTime
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String updatePlanStartTime()throws Exception{
		UserVo userNow = (UserVo)this.session.get(SystemConstant.USER);
		boolean re = planService.updatePlanStartTime(planVo,userNow);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setSuccess(true);
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 查找课程号的知识点
	  * getPlanPoint2(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getPlanPoint2
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getPlanPoint2() throws Exception{
		String starttime = planVo.getStarttime();
		String endtime = planVo.getEndtime();
		String end_time = planVo.getEnd_time();
		String courseId = planVo.getCourse_id();
		ArrayList<TreeViewVo> al = planService.getPlanPoint2(starttime,end_time,endtime,courseId);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		TreeViewVoUtil tu = new TreeViewVoUtil();
		resultMap = tu.getResultMap(rtn,al);
		return "json";
	}
	
	/**
	 * 添加教学计划
	  * addPlan(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: addPlan
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String addPlan() throws Exception{
		UserVo userNow = (UserVo)this.session.get(SystemConstant.USER);
		planVo = planService.addPlan(planVo, userNow);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.setSuccess(true);
		rtn.add("data", planVo);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除教学计划和详细计划
	  * planDel(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: planDel
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String planDel() throws Exception{
		boolean re = planService.planDel(planVo.getId());
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setSuccess(true);
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";	
	}
	
	/**
	 * 修改详细知识点信息
	  * updatePointDetail(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: updatePointDetail
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String updatePointDetail()throws Exception{
		//输入数据:当前用户,教学计划ID,知识点ID,时长(小时,分钟),开始时间,结束时间,老师ID,状态
		UserVo userNow = (UserVo)this.session.get(SystemConstant.USER);
		HashMap<String,String> map = planService.updatePointDetail(planVo,userNow);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.setSuccess(true);
		rtn.add("info", map);
		resultMap=rtn.getReturnMap();
		return "json";	
	}
	

	/**
	 * @Description (TODO检查课程,班级唯一性)
	 * @return
	 * @throws Exception
	 */
	public String checkCourseClassOnly() throws Exception{
		boolean re = techPaperService.checkCourseClassOnly(planVo);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setSuccess(true);
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";	
	}
	
	
}
