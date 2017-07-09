package com.clps.tmp.tech.plan.service;

import java.util.List;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.vo.AnswerInfoVo;
import com.clps.tmp.tech.plan.vo.AnswerSheetVo;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.SectionVo;

public interface TechPaperService {
	
	/**
	 * @Description (TODO获取答题的概要信息)
	 * @param paperId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public AnswerInfoVo getAnswerInfoVo(String paperId,String userId)throws Exception;
	
	/**
	 * @Description (TODO获取试卷题块信息)
	 * @param paperId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SectionVo> getSectionInfo(String paperId,String userId)throws Exception;
	
	/**
	 * @Description (TODO获取批改进度)
	 * @param paperId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public AnswerInfoVo getProgress(String paperId,String userId)throws Exception;
	
	/**
	 * @Description (TODO获取答卷内容)
	 * @param paperId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<AnswerSheetVo> getAnswerSheet(String paperId,String userId)throws Exception;
	
	/**
	 * @Description (TODO打分)
	 * @param answerSheetVo
	 * @return
	 * @throws Exception
	 */
	public int setScore(AnswerSheetVo answerSheetVo,UserVo user)throws Exception;
	
	/**
	 * @Description (TODO写备注)
	 * @param answerSheetVo
	 * @return
	 * @throws Exception
	 */
	public int setRemark(AnswerSheetVo answerSheetVo)throws Exception;
	
	/**
	 * @Description (TODO更新试卷批改结果)
	 * @param answerSheetVo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int setPaperScore(AnswerInfoVo answerInfoVo,UserVo user)throws Exception;
	
	/**
	 * @Description (TODO自动阅卷)
	 * @param paperId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<AnswerSheetVo> setAutoCorrectPaper(String paperId,String userId,UserVo user)throws Exception;
	
	//查询班级课程的唯一性
	public boolean checkCourseClassOnly(PlanVo vo)throws Exception;
}
