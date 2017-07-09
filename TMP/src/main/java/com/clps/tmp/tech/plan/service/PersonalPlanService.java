package com.clps.tmp.tech.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.common.vo.TreeViewVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.EvalVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;

/**
 * 个人教学计划
 * @author Seven
 * 2015年12月28日
 */
public interface PersonalPlanService {
	/**
	 * 获取个人-教学计划 列表数据
	 * 2015年12月30日 Seven
	 */
	PageVo<PlanVo> getTeachPlanTable(PageVo<PlanVo> pageVo) throws Exception;
	/**
	 * 获取某个教学计划的详细
	 * 2015年12月30日 Seven
	 */
	public ArrayList<TreeViewVo> getTeachPlan(String techerId,String planId) throws Exception; 
	/**
	 * 修改授课状态
	 * 2016年1月15日 Seven
	 */
	public int updateTeachState(String planId,String pointId,String userId,String state) throws Exception;
	/**
	 * 获取过程评价列表
	 * 2016年1月19日 Seven
	 */
	public ArrayList<EvalVo> getProcEvalList(EvalVo procVo) throws Exception;
	/**
	 * 保存评价记录
	 * 2016年1月19日 Seven
	 */
	public int saveProcEval(EvalVo procVo) throws Exception;
	/**
	 * 获取个人-学习计划 列表数据
	 * 2016年1月22日 Seven
	 */
	PageVo<PlanVo> getLearnPlanTable(PageVo<PlanVo> pageVo) throws Exception;
	/**
	 * 获取某个教学计划的详细
	 * 2015年12月30日 Seven
	 */
	public ArrayList<TreeViewVo> getLearnPlan(String planId) throws Exception; 
	/**
	 * 获取教师最终评价学员的学员列表
	 * 2016年1月26日 Seven
	 */
	public ArrayList<EvalVo> getFinalEvalStuList(EvalVo procVo) throws Exception;
	/**
	 * 保存 最终评价 记录
	 * 2016年1月26日 Seven
	 */
	public int saveFinalEval(EvalVo procVo) throws Exception;
	/**
	 * 获取学员平时表现信息
	 * 2016年1月26日 Seven
	 */
	public HashMap<String,Object> getStuRefEval(EvalVo procVo,String planId) throws Exception;
	/**
	 * 根据教学计划获取授课教师
	 * 2016年1月27日 Seven
	 */
	public List<SelectVo> getTeachersByPlan(String planId) throws Exception;
	/**
	 * 获取学生评价某教师的结果
	 * 2016年1月27日 Seven
	 */
	public HashMap<String,Object> getStuEvalTeacherRS(EvalVo procVo) throws Exception;
	
}
