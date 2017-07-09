package com.clps.tmp.tech.plan.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.vo.ExerciseScoreRatioVo;
import com.clps.tmp.tech.plan.vo.ExerciseScoreVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;
import com.clps.tmp.tech.plan.vo.TotalScoreRatioVo;
import com.clps.tmp.tech.plan.vo.TotalScoreVo;

/**
 * @ClassName ScoreService
 * @Description TODO(成绩管理)
 * @author liuchen
 * @Date 2016年6月14日 下午4:00:35
 * @version 1.0.0
 */
public interface ScoreService {
	
	/**
	 * @Description (TODO数据库查询总成绩)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<TotalScoreVo> selectTotalScoreInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO获取试卷列表-设置期末卷)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PlanPaperVo> selectPlanPaperInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO获取试卷列表-设置比例)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<PlanPaperVo> selectPlanPaperInfo1(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO获取平时成绩)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<ExerciseScoreVo> selectDetailNormal(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (考勤成绩查看)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<ExerciseScoreVo> selectDetailAttendance(Map<String, Object> paramMap) throws Exception;

	
	/**
	 * @Description (TODO设置期末综合测试卷)
	 * @param exerciseScoreRatioVo
	 * @return
	 * @throws Exception
	 */
	public int setLastPaper(ExerciseScoreRatioVo exerciseScoreRatioVo,UserVo user) throws Exception;
	
	/**
	 * @Description (TODO取消期末综合测试卷)
	 * @param exerciseScoreRatioVo
	 * @return
	 * @throws Exception
	 */
	public int unsetLastPaper(ExerciseScoreRatioVo exerciseScoreRatioVo,UserVo user) throws Exception;
	
	/**
	 * @Description (TODO初始化成绩信息)
	 * @param plan_id
	 * @return
	 * @throws Exception
	 */
	public boolean initScoreData(String plan_id,UserVo user) throws Exception;
	
	/**
	 * @Description (TODO设置默认加权)
	 * @param exerciseScoreRatioVo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int setDeRatio(ExerciseScoreRatioVo exerciseScoreRatioVo,UserVo user) throws Exception;

	/**
	 * @Description (TODO设置比例)
	 * @param exerciseScoreRatioVo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int setRatio(ExerciseScoreRatioVo exerciseScoreRatioVo,UserVo user) throws Exception;

	
	/**
	 * @Description (TODO计算平时成绩)
	 * @param exerciseScoreRatioVo
	 * @return
	 * @throws Exception
	 */
	public int saveNormalScore(ExerciseScoreRatioVo exerciseScoreRatioVo) throws Exception;
	
	
	/**
	 * @Description (TODO计算考勤分,态度分,期末考试分) flag->true(强制更改)
	 * @param exerciseScoreRatioVo
	 * @return
	 * @throws Exception
	 */
	public int saveStudentOtherScore(ExerciseScoreRatioVo exerciseScoreRatioVo,boolean flag) throws Exception;
	
	/**
	 * @Description (TODO打态度分)
	 * @param totalScoreVo
	 * @return
	 * @throws Exception
	 */
	public int setAtt(TotalScoreVo totalScoreVo, UserVo user) throws Exception;
	
	
	/**
	 * @Description (TODO获取总分比例数据)
	 * @param totalScoreVo
	 * @return
	 * @throws Exception
	 */
	public TotalScoreRatioVo getTotalRatio(TotalScoreRatioVo totalScoreVo) throws Exception;

	/**
	 * @Description (TODO保存比例)
	 * @param totalScoreVo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int saveTotalRatio(TotalScoreRatioVo totalScoreVo, UserVo user) throws Exception;
	
	/**
	 * @Description (TODO完成计算)
	 * @param totalScoreVo
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int finish(TotalScoreRatioVo totalScoreVo, UserVo user) throws Exception;

	
	/**
	 * @Description (TODO获取总分比例)
	 * @param planPaperVo
	 * @return
	 * @throws Exception
	 */
	public TotalScoreRatioVo getTotalScoreRatio(PlanPaperVo planPaperVo) throws Exception;

	/**
	 * @Description (TODO获取总成绩信息)
	 * @param planId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getTotalScoreToExport(String planId)throws Exception;
}
