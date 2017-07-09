package com.clps.tmp.tech.teacherpool.service;

import java.util.HashMap;
import java.util.LinkedList;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.point.vo.PointVo;
import com.clps.tmp.tech.teacherpool.vo.HistoryVo;
import com.clps.tmp.tech.teacherpool.vo.TeacherpoolVo;

public interface TeacherpoolService {

	PageVo<TeacherpoolVo> queryTeacherpoolPage(PageVo<TeacherpoolVo> pageVo) throws Exception;

	

	public int deleteTeacher(String nbr) throws Exception;

	//public TeacherpoolVo getTeacherByNbr(String nbr) throws Exception;

	TeacherpoolVo detailTeacehr(String nbr) throws Exception;

//	public int updateTeacher(TeacherpoolVo tepVo)throws Exception;



	public boolean addTeacher(TeacherpoolVo tepVo, UserVo userNow)throws Exception;



	public boolean updateTeacher(TeacherpoolVo tepVo, boolean flag) throws Exception;




	PageVo<TeacherpoolVo> queryHistory(PageVo<TeacherpoolVo> pageVo, String nbr) throws Exception;



	PageVo<PointVo> querySkillPage(PageVo<PointVo> pageVo) throws Exception;



	public int addTeacSkillPoint(String teacherId, String pointId, String skillId, String grant) throws Exception;



	public int removeTeacherSkill(String nbr, String skillId) throws Exception;



	PageVo<PointVo> queryPointPage(PageVo<PointVo> pageVo, String nbr,String skillId) throws Exception;



	public int managePoint(HashMap<String, Object> paramMap, String description) throws Exception;



	public LinkedList<HashMap<String, Object>> reviewTeacher(String nbr) throws Exception;



	public String queryTeacherPhoto(String nbr) throws Exception;



	public boolean updateTeacherPhoto(String photo, String nbr) throws Exception;



	public int checkData(String name, String  mobile) throws Exception;



	public boolean reVokeTeacher(String nbr, String pointId) throws Exception;



	public boolean addManage(String nbr, String pointId) throws Exception;
	/**
	 * 获取能教授特定知识点的教师信息(分页)
	 * @throws Exception 
	 */
	public PageVo<TeacherpoolVo> queryTeachersBySpecialPoint(String pointId,String starttime,String endtime,PageVo<TeacherpoolVo> pageVo) throws Exception;
	/**
	 * 记录教学历史
	 * 2016年1月7日 Seven
	 */
	public void recordTechHistory(HistoryVo histVo) throws Exception;

}
