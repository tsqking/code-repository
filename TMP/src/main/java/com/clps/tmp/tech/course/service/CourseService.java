package com.clps.tmp.tech.course.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.course.vo.CourseVo;
import com.clps.tmp.tech.point.vo.PointVo;

/**
 * @author Seven
 * 2015年11月16日
 */
public interface CourseService {
	/**
	 * 获取课程管理表格数据
	 * 2015年11月17日 Seven
	 */
	PageVo<CourseVo> queryCoursePage(PageVo<CourseVo> pageVo)throws Exception;
	/**
	 * 添加课程信息
	 * 2015年11月17日 Seven
	 */
	public int addCourse(CourseVo course) throws Exception;
	/**
	 * 通过id查询课程信息
	 * 2015年11月17日 Seven
	 */
	public CourseVo getCourseById(String id) throws Exception;
	/**
	 * 修改课程信息
	 * 2015年11月17日 Seven
	 */
	public int editCourse(CourseVo course)throws Exception;
	/**
	 * 删除课程<br>
	 * -1 标识课程被教学计划使用，删除失败；否则删除成功
	 * 
	 * 2015年11月17日 Seven
	 */
	public int deleteCourse(String id)throws Exception;
	/**
	 * 获取课程技能表格
	 * 2015年11月18日 Seven
	 */
	public PageVo<PointVo> querySkillPage(PageVo<PointVo> pageVo)throws Exception;
	/**
	 * 为课程添加技能知识点
	 * 2015年11月19日 Seven
	 */
	public int addSkillPoint(String courseId,String pointId,String skillId)throws Exception;
	/**
	 * 移除技能
	 * 2015年11月19日 Seven
	 */
	public int removeCourseSkill(String courseId,String skillId)throws Exception;
	/**
	 * 获取某课程某技能下的知识点列表
	 * 2015年11月19日 Seven
	 */
	public PageVo<PointVo> queryPointPage(PageVo<PointVo> pageVo,String courseId, String skillId) throws Exception;
	/**
	 * 管理课程下知识点
	 * @param paramMap 包含 course_id与point_id
	 * @param operation 分为：add/remove
	 * 2015年11月20日 Seven
	 */
	public int managePoint(HashMap<String,Object> paramMap,String operation) throws Exception;
	/**
	 * 课程知识点一览
	 * 2015年11月23日 Seven
	 */
	public LinkedList<HashMap<String,Object>> reviewCourse(String courseId) throws Exception;
	/**
	 * 获取课程下拉框
	 * 2015年11月24日 Seven
	 */
	public List<SelectVo> getCourseOption(HashMap<String,Object> paramMap) throws Exception;
	/**
	 * 根据No查询课程
	 * 2015年11月24日 Seven
	 */
	public CourseVo queryCourseByNo(String courseNo) throws Exception;
	/**
	 * 根据Id查询课程
	 * 2015年11月24日 Seven
	 */
	public CourseVo queryCourseById(String courseId) throws Exception;
	/**
	 * 获取子类别（即第一级技能）
	 * 2016年4月7日 Seven
	 */
	public List<SelectVo> getSubCategory()throws Exception;
	/**
	 * 获取子子类别（即第二级技能）
	 * 2016年4月7日 Seven
	 */
	public List<SelectVo> getSubSubCategory()throws Exception;
	/**
	 * 获取课程导出所需的课程数据
	 * 2016年4月7日 Seven
	 */
	List<Map<String, Object>> getCourseData(Map<String, Object> paramMap)throws Exception;
	
	
}
