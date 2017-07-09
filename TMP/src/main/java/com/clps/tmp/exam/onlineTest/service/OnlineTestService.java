package com.clps.tmp.exam.onlineTest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clps.tmp.exam.onlineTest.vo.OnlineTestVo;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.clps.tmp.tech.plan.vo.AnswerInfoVo;

public interface OnlineTestService {

	HashMap<String, Object> findPaperById(Map<String, Object> paramMap) throws Exception;
	/**
	 * 根据试卷id或试卷no查询题目
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	HashMap<String,Object> findQuestion(Map<String, Object> paramMap) throws Exception;
	
	public void addQustAnswer(List<Map<String,Object>> list) throws Exception;
	
	public int findAnswerSheet(Map<String, Object> paramMap) throws Exception;
	/**
	 * 删除已经存在的答题卡信息
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	int deleteAnswerSheet(Map<String, Object> paramMap) throws Exception;
	/**
	 * 保存试卷信息
	 * @param paperMap
	 * @throws Exception
	 */
	public void addPaperInfo(Map<String, Object> paperMap) throws Exception;
	//
	int checkPaperInfo(Map<String, Object> paperMap) throws Exception;
	//
	int deletePaperInfo(Map<String, Object> paperMap) throws Exception;
	//
	public String allowExam(Map<String, Object> paramMap) throws Exception;
	//
//	Map<String, Object> findQustList(Map<String, Object> paramMap);
	String findPno(String paperId) throws Exception;
	/**
	 * 判断该考生是否已经考过这张试卷
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	String judgeWhetherTest(Map<String, Object> paramMap) throws Exception;
	/**
	 * 查询计划测试中的试卷信息
	 * @return
	 */
	HashMap<String, Object> findPlanPaperInfo(Map<String,Object> paramMap) throws Exception;
	/**
	 * 根据Paper No查找Paper信息
	 * 2016年6月30日 Seven
	 * @throws Exception 
	 */
	PaperVo findPaperInfoByNo(String paperNo) throws Exception;
	/**
	 * 
	 * @param paramMap
	 * @return 
	 * @throws Exception 
	 */
	public int editPlanPaperHis(Map<String, Object> paramMap) throws Exception;
	/**
	 * 获取图片信息
	 * @param questionId
	 * @return
	 * @throws Exception 
	 */
	QuestionVo getImgs(Integer questionId) throws Exception;

}
