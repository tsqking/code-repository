package com.clps.tmp.tech.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.common.vo.TreeViewVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.vo.PlanVo;

/**
 * 教学计划管理
  * @ClassName: PlanService
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年12月3日 下午2:25:30
 */
public interface PlanService {
	
	//教学计划管理分页查询
	public PageVo<PlanVo> queryPlanPage(PageVo<PlanVo> pv) throws Exception;
		
	//查询课程下拉框
	public List<SelectVo> getAllCourseName_Id() throws Exception;

	//查询班级下拉框
	public List<SelectVo> getAllClassName_Id() throws Exception;
	
	//获得计划的知识点列表
	public ArrayList<TreeViewVo> getPlanPoint(String planVo,PlanVo vo,String courseId,boolean f) throws Exception;
	
	//获取所有老师的的id和名字的下拉框
	public List<SelectVo> getAllTeacherSelect()throws Exception;
	
	//修改计划开始时间
	public boolean updatePlanStartTime(PlanVo vo,UserVo userNow)throws Exception;
	
	//获得计划的知识点列表-课程号
	public ArrayList<TreeViewVo> getPlanPoint2(String starttime,String end_time,String endtime,String courseId) throws Exception;
	
	//添加教学计划
	public PlanVo addPlan(PlanVo vo,UserVo userNow) throws Exception;
	
	//删除教学计划
	public boolean planDel(String planId) throws Exception;
	
	//获取知识点完成状态
	public List<SelectVo> getPoint_Finish_state()throws Exception;
	
	//修改详细知识点的信息
	public HashMap<String,String> updatePointDetail(PlanVo vo,UserVo userNow)throws Exception;
	
	//更新教学计划最后修改时间
	public void updateTeachPlanUpdateTime(String planId,UserVo userNow)throws Exception;
	
}
