package com.clps.tmp.tech.plan.service;

import java.util.List;
import java.util.Map;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.tech.plan.vo.EvalVo;
import com.clps.tmp.tech.plan.vo.PlanPaperStuHisVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;
import com.clps.tmp.tech.plan.vo.PlanVo;

public interface TeaStuPlanService {
	
	/**
	 * @Description (TODO查询作业和测试的信息)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PlanPaperVo> selectPaperInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO试卷列表)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PaperVo> selectPaperList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO添加测试)
	 * @param planPaperVo
	 * @return
	 * @throws Exception
	 */
	public int addPaperTest(PlanPaperVo planPaperVo,UserVo teacher)throws Exception;
	
	
	/**
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deletePaperTest(String[] ids,UserVo user)throws Exception;
	
	
	/**
	 * @Description (TODO获取试卷的信息)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PaperVo getPaperInfo(String id)throws Exception;
	
	/**
	 * @Description (TODO获取测试作业的信息)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlanPaperVo getPaperTestInfo(String id)throws Exception;
	
	/**
	 * @Description (TODO更新)
	 * @param planPaperVo
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public int editPaperTest(PlanPaperVo planPaperVo,UserVo teacher)throws Exception;
	
	/**
	 * @Description (TODO布置作业)
	 * @param planPaperVo
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public int updatePaperStatus(PlanPaperVo planPaperVo,UserVo teacher)throws Exception;
	
	/**
	 * @Description (TODO获取班级作业状态列表)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PlanPaperStuHisVo> selectClassPaperList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO学生的作业测试列表)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PlanPaperStuHisVo> selectStuPaperHisList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO获取反馈列表-教师使用)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<EvalVo> selectFeedbackList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO获取反馈列表-学生使用)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<EvalVo> selectFeedbackList2(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO获得教学计划信息)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public PlanVo getPlanInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO导出分数)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> downScoreData(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO导出答案)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> downAnswerData(Map<String, Object> paramMap) throws Exception;

	/**
	 * @Description (TODO一键签到)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int oneKeyPro(Map<String, Object> paramMap,UserVo teacher) throws Exception;

	/**
	 * @Description (TODO一键打分)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int oneKeyScore(Map<String, Object> paramMap,UserVo teacher) throws Exception;
	
	/**
	 * 查询学生成绩单
	 * @param map
	 * @return
	 */
	public List selectTranscript(Map<String, Object> map) throws Exception;
	
	/**
	 * 查询学生历史总成绩分布
	 * @param subj
	 * @return
	 */
	public Map<String, Object> selectRateData(String subj) throws Exception;

}
