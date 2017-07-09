package com.clps.tmp.question.question.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.question.question.vo.QuestionVo;

public interface QuestionService {

	/**
	 * 禁用题目
	 * @param questionVo
	 * @return
	 * @throws Exception
	 */
	public int changeQuestionStateDisable(QuestionVo questionVo) throws Exception;
	
	/**
	 * 启用题目
	 * @param questionVo
	 * @return
	 * @throws Exception
	 */
	public int changeQuestionStateEnable(QuestionVo questionVo) throws Exception;
	
	/*******************************************新模板***************************************/
	/***************************************************************************************/
	/**
	 * 查询所有题目
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<QuestionVo> findQuestionList(Map<String,Object> map) throws Exception;
	
	/**
	 * 查找题目
	 * @param questionVo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findQuestion(QuestionVo questionVo) throws Exception;
	
	/**
	 * 添加新题
	 * @param questionVo
	 * @return
	 * @throws Exception
	 */
	public int insertQuestion(QuestionVo questionVo,String pointIds,String tagIds) throws Exception;
	
	/**
	 * 更新题目
	 * @param questionVo
	 * @return
	 * @throws Exception
	 */
	public int updateQuestion(QuestionVo questionVo,String tagIds) throws Exception;
	
	/**
	 * 插入知识点
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertPoint(Map<String,Object> map) throws Exception;
	
	/**
	 * 查询技能下的知识点
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findPoints(Map<String,Object> map) throws Exception;
	
	/**
	 * 删除知识点
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deletePoint(Map<String,Object> map) throws Exception;
	
	//获取图片
	public QuestionVo getImgs(QuestionVo questionVo) throws Exception;
	
//	/**
//	 * 获取所有的知识点（用于下拉框初始化）
//	 * @return
//	 * @throws Exception
//	 */
//	public List<SelectVo> getAllPoint() throws Exception;
	
}
