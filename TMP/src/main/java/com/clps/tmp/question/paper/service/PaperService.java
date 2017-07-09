package com.clps.tmp.question.paper.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.question.paper.vo.SectionVo;
import com.clps.tmp.question.question.vo.QuestionVo;

public interface PaperService {

	/**
	 * 获取所有的试卷信息
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PaperVo> findPaperList(Map<String,Object> map) throws Exception;
	
	/**
	 * 查询题目列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<QuestionVo> findQuestionList(Map<String, Object> map) throws Exception;
	
	/**
	 * 查询这张试卷下的题目信息
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public String findPaperQuestion(PaperVo paperVo) throws Exception;
	
	/**
	 * 查询某个试卷信息
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findPaperById(PaperVo paperVo) throws Exception;
	
	/**
	 * 创建试卷（未完成）
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public int insertPaper(PaperVo paperVo) throws Exception;
	
	/**
	 * 完成试卷创建，更新试卷状态
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public int updatePaperStatus(PaperVo paperVo) throws Exception;
	
	/**
	 * 验证试卷是否完成创建
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public int validatePaperStatus(PaperVo paperVo) throws Exception;
	
	/**
	 * 保存部分
	 * @param sectionVo
	 * @return
	 * @throws Exception
	 */
	public int insertSection(SectionVo sectionVo,PaperVo paperVo,Map<String,Object> map) throws Exception;
	
	/**
	 * 删除该部分
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public int deleteSection(Map<String,Object> paraMap) throws Exception;
	
	/**
	 * 查询部分内容
	 * @param sectionVo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findSection(Map<String,Object> paraMap) throws Exception;
	
	/**
	 * 获取试卷信息（总分数，总题数，考试时长）
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findPaperInfo(Map<String,Object> map) throws Exception;
	
	/**
	 * 查询试卷所有的信息（包括部分信息，题目信息，考试时长，试卷题数，试卷分数）
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findAllPaperInfo(Map<String,Object> map) throws Exception;
	
	/**
	 * 删除试卷
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public int deleteSection(PaperVo paperVo) throws Exception;
	
	/**
	 * 保存题目信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int saveQuestion(Map<String,Object> map) throws Exception;
	
	/**
	 * 删除题目信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteQuestion(Map<String,Object> map) throws Exception;
	/**
	 * 保存总时间
	 * 2016年4月26日 Seven
	 * @throws Exception 
	 */
	public void saveTotalInfoForPaper(PaperVo paperVo) throws Exception;
	
	/**
	 * 查询试卷属性
	 * @param paperVo
	 * @return
	 * @throws Exception
	 */
	public List<PaperVo> findPaperProperty(PaperVo paperVo) throws Exception;
	
	/**
	 * 更新试卷信息
	 * @param paperVo
	 * @throws Exception
	 */
	public void savePaperInfo(PaperVo paperVo) throws Exception;
	/**
	 * 更新启用禁用标志
	 * 2016年5月25日 Seven
	 */
	public int changeEnableState(PaperVo paperVo)throws Exception;
	/**
	 * 复制试卷
	 * @param old_paper_id
	 * @param paperVo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean copyPaper(String old_paper_id, PaperVo paperVo, String user) throws Exception;
}
