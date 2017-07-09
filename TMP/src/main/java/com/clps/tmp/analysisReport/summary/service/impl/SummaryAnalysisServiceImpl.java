/**
 * Project Name:TMP
 * File Name:SummaryAnalysisServiceImpl.java
 * Package Name:com.clps.tmp.analysisReport.summary.service.impl
 * Date:2017年4月18日下午2:16:44
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.summary.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.clps.tmp.analysisReport.summary.service.SummaryAnalysisService;
import com.clps.tmp.analysisReport.summary.vo.HeadVo;
import com.clps.tmp.analysisReport.summary.vo.SummaryVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DecimalUntil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;

/**
 * ClassName:SummaryAnalysisServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月18日 下午2:16:44 <br/>
 * 分析总表业务层
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
@Service
public class SummaryAnalysisServiceImpl extends MBBaseService implements SummaryAnalysisService {

	/**
	 * TODO 获得表头上的男女生人数和平均考试年龄.
	 * 
	 * @see com.clps.tmp.analysisReport.summary.service.SummaryAnalysisService#queryHead(java.util.Map)
	 */
	@Override
	public HeadVo queryHead(Map<String, Object> map) throws Exception {
		HeadVo headInfo = (HeadVo) this.mbDao.selectOne("summary.queryHead", map);
		if (headInfo != null) {
			return headInfo;
		}
		return null;
	}

	/**
	 * TODO 获得表格内容.
	 * 
	 * @see com.clps.tmp.analysisReport.summary.service.SummaryAnalysisService#queryData(java.util.Map)
	 */
	@Override
	public BtTableVo<SummaryVo> queryData(Map<String, Object> map) throws Exception {
		SummaryVo summaryVo = new SummaryVo();
		BtTableVo<SummaryVo> summaryVoDataTbl = new BtTableVo<>();

		// 查出男女生人数
		HeadVo headInfo = (HeadVo) this.mbDao.selectOne("summary.queryHead", map);

		// 查出所有报名人数
		long applyCount = (long) this.mbDao.selectOne("summary.queryApplyCount", map);

		// 查出所有参加考试的人数
		long attendCount = (long) this.mbDao.selectOne("summary.queryAttendCount", map);

		// 总人数
		long stuNum = headInfo.getFemaleNum() + headInfo.getMaleNum();

		/******************************************** 选择题开始 *****************************/
		// 选择题总题目数
		long chioseQuesCount = (long) this.mbDao.selectOne("summary.queryChioseCount", map);

		// 选择题总分值
		long chioseTotalScore = (long) this.mbDao.selectOne("summary.queryChioseTotalScore", map);

		// 学生选择题总得分
		long stuChioseTotalScore = (long) this.mbDao.selectOne("summary.queryStuChioseTotalScore", map);

		// 判断除数为0时赋予0
		long chioseDiv = chioseTotalScore * stuNum;// 除数,选择题总分数*总考生数量
		if (stuNum == 0 || chioseDiv == 0) {
			summaryVo.setChioseAvg("0");// 选择题平均分
			summaryVo.setChioseQuesCount("0");// 选择题数量
			summaryVo.setChioseTotalScore("0");// 选择题总分值
			summaryVo.setChioseFinishRate("0");// 选择题完成率
		} else {
			// 选择题平均分,所有考生在选择题中的总得分数/总考生数量
			String chioseAvgScore = DecimalUntil.div(Long.toString(stuChioseTotalScore), Long.toString(stuNum), 1);

			// 选择题完成率,所有考生在选择题中的总得分数/（选择题总分数*总考生数量）
			String chioseFinishRate = DecimalUntil.div(Long.toString(stuChioseTotalScore * 100),
					Long.toString(chioseDiv), 1);

			summaryVo.setChioseAvg(chioseAvgScore);// 选择题平均分
			summaryVo.setChioseQuesCount(Long.toString(chioseQuesCount));// 选择题数量
			summaryVo.setChioseTotalScore(Long.toString(chioseTotalScore));// 选择题总分值
			summaryVo.setChioseFinishRate(chioseFinishRate + "%");// 选择题完成率
		}

		/********************************************** 填空题开始 *****************************/
		// 填空题总题目数
		long fillCount = (long) this.mbDao.selectOne("summary.queryFillCount", map);

		// 填空题总分值
		long fillTotalScore = (long) this.mbDao.selectOne("summary.queryFillTotalScore", map);

		// 学生填空题总得分
		long stuFillTotalScore = (long) this.mbDao.selectOne("summary.queryStuFillTotalScore", map);

		// 判断除数为0时赋予0
		long fillDiv = fillTotalScore * stuNum;// 除数,填空题总分数*总考生数量
		if (stuNum == 0 || fillDiv == 0) {
			summaryVo.setFillAvgScore("0");// 填空题平均分
			summaryVo.setFillQuesCount("0");// 填空题数量
			summaryVo.setFillTotalScore("0");// 填空题总分值
			summaryVo.setFillFinishRate("0");// 填空题完成率
		} else {
			// 填空题平均得分【所有考生在填空题中的总得分数/（总考生数量x20空格）】
			String fillAvg = DecimalUntil.div(Long.toString(stuFillTotalScore), Long.toString(stuNum * 20), 1);

			// 填空题完成率【所有考生在填空题中的总得分数/（填空题总分数*总考生数量）】
			String fillFinishRate = DecimalUntil.div(Long.toString(stuFillTotalScore * 100), Long.toString(fillDiv), 1);

			summaryVo.setFillAvgScore(fillAvg);// 填空题平均分
			summaryVo.setFillQuesCount(Long.toString(fillCount));// 填空题数量
			summaryVo.setFillTotalScore(Long.toString(fillTotalScore));// 填空题总分值
			summaryVo.setFillFinishRate(fillFinishRate + "%");// 填空题完成率
		}

		/******************************************* 编程题开始 *****************************/
		// 因为Testing没有编程题，所以在这里做个判断，如果传过来的科目是Testing，那么编程题部分群补置为空字符串
		if (map.get("subject").equals("Testing")) {
			summaryVo.setProgramAvgScore("");// 编程题平均分
			summaryVo.setProgramTotalScore("");// 编程题总分
			summaryVo.setProgramQuesCount("");// 编程题目总数
			summaryVo.setProgramFinishRate("");// 编程题完成率
		} else {

			// 编程题总题目数
			long programCount = (long) this.mbDao.selectOne("summary.queryProgramCount", map);
			// 编程题总分值
			long programTotalScore = (long) this.mbDao.selectOne("summary.queryProgramTotalScore", map);

			// 学生编程题总得分
			long stuProgramTotalScore = (long) this.mbDao.selectOne("summary.queryStuProgramTotalScore", map);

			// 判断除数为0时赋予0
			long programDiv = programTotalScore * stuNum;// 除数,编程题总分数*总考生数量
			if (programDiv == 0 || stuNum == 0) {
				summaryVo.setProgramAvgScore("0");// 编程题平均分
				summaryVo.setProgramTotalScore("0");// 编程题总分
				summaryVo.setProgramQuesCount("0");// 编程题目总数
				summaryVo.setProgramFinishRate("0");// 编程题完成率
			} else {
				// 计算编程题平均分【所有考生在编程题中的总得分数/总考生数量】
				String programAvg = DecimalUntil.div(Long.toString(stuProgramTotalScore), Long.toString(stuNum), 1);

				// 计算编程题完成率【所有考生在编程题中的总得分数/（编程题总分数*总考生数量）】
				String programFinishRate = DecimalUntil.div(Long.toString(stuProgramTotalScore * 100),
						Long.toString(programDiv), 1);

				summaryVo.setProgramAvgScore(programAvg);// 编程题平均分
				summaryVo.setProgramTotalScore(Long.toString(programTotalScore));// 编程题总分值
				summaryVo.setProgramQuesCount(Long.toString(programCount));// 编程题数量
				summaryVo.setProgramFinishRate(programFinishRate + "%");// 编程题完成率
			}
		}

		/********************************************* 是非题开始 *****************************/
		// 查出是非题总题目数
		long tfCount = (long) this.mbDao.selectOne("summary.queryTfCount", map);

		// 是非题总分值
		long tfTotalScore = (long) this.mbDao.selectOne("summary.queryTfTotalScore", map);

		// 学生是非题总得分
		long stuTfTotalScore = (long) this.mbDao.selectOne("summary.queryStuTfTotalScore", map);

		// 判断除数为0时赋予0
		long tFDiv = tfTotalScore * stuNum;// 除数,是非题总分数*总考生数量
		if (tFDiv == 0 || stuNum == 0) {
			summaryVo.setTfAvgScore("0");//是非题平均分
			summaryVo.setTfFinishRate("0");//是非题完成率
			summaryVo.setTfQuesCount("0");//是非题数量
			summaryVo.setTfTotalScore("0");//是非题总分值
		} else {
			// 计算是非题平均分【所有考生在是非题中的总得分数/总考生数量】
			String tfAvgScore = DecimalUntil.div(Long.toString(stuTfTotalScore), Long.toString(stuNum), 1);

			// 计算是非题完成率【所有考生在是非题中的总得分数/（编程题总分数*总考生数量）】
			String tfFinishRate = DecimalUntil.div(Long.toString(stuTfTotalScore * 100), Long.toString(tFDiv), 1);
			summaryVo.setTfAvgScore(tfAvgScore);// 是非题平均分
			summaryVo.setTfFinishRate(tfFinishRate + "%");// 是非题完成率
			summaryVo.setTfQuesCount(Long.toString(tfCount));// 是非题数量
			summaryVo.setTfTotalScore(Long.toString(tfTotalScore));// 是非题总分值
		}

		/*************************************** 简答题开始 ************************************/
		// 查出简答题总题目数
		long shortCount = (long) this.mbDao.selectOne("summary.queryShortCount", map);

		// 简答题总分值
		long shortTotalScore = (long) this.mbDao.selectOne("summary.queryShortTotalScore", map);

		// 学生简答题总得分
		long stuShortTotalScore = (long) this.mbDao.selectOne("summary.queryStuShortTotalScore", map);

		// 判断除数为0时赋予0
		long shortDiv = shortTotalScore * stuNum;// 除数,简答题总分数*总考生数量
		if (shortDiv == 0 || stuNum == 0) {
			summaryVo.setShortAvgScore("0");// 简答题平均分
			summaryVo.setShortFinishRate("0");// 简答题完成率
			summaryVo.setShortQuesCount("0");// 简答题数量
			summaryVo.setShortTotalScore("0");// 简答题总分值
		} else {
			// 计算简答题平均分【所有考生在简答题中的总得分数/总考生数量】
			String shortAvgScore = DecimalUntil.div(Long.toString(stuShortTotalScore), Long.toString(stuNum), 1);

			// 计算简答题完成率【所有考生在是简答中的总得分数/（编程题总分数*总考生数量）】
			String shortFinishRate = DecimalUntil.div(Long.toString(stuShortTotalScore * 100), Long.toString(shortDiv),
					1);

			summaryVo.setShortAvgScore(shortAvgScore);// 简答题平均分
			summaryVo.setShortFinishRate(shortFinishRate + "%");// 简答题完成率
			summaryVo.setShortQuesCount(Long.toString(shortCount));// 简答题数量
			summaryVo.setShortTotalScore(Long.toString(shortTotalScore));// 简答题总分值
		}

		// 缺考率百分比【参加考试总人数/报名考试总人数】
		// 判断除数为0时赋予0
		if (applyCount == 0) {
			summaryVo.setAbsentRate("0");
		} else {
			String absentRate = DecimalUntil.div(Long.toString((applyCount - attendCount) * 100),
					Long.toString(applyCount), 1);
			summaryVo.setAbsentRate(absentRate + "%");
		}

		/********************************** 考试时长开始 ***************************************/
		// 学生考试总时长
		long timeCount = (long) this.mbDao.selectOne("summary.queryStuExamTime", map);

		// 判断除数为0时赋予0
		if (stuNum == 0) {
			summaryVo.setExamAvgDuration("0");// 平均答题市场
			summaryVo.setFormularyExamDuration("0");// 规定考试时长
		} else {

			// 平均考试答题时长【所有考生答题时长总数/总考生数量】
			String stuAvgExamTime = DecimalUntil.div(Long.toString(timeCount / 60), Long.toString(stuNum), 0);
			summaryVo.setExamAvgDuration(stuAvgExamTime + "分钟");
		}
		// 规定考试答题时长【固定值】
		summaryVo.setFormularyExamDuration("120分钟");

		/********************************** 数据获取完毕 ***************************************/
		// 封装成前台bootstrap-table需要的格式
		List<SummaryVo> list = new ArrayList<>();
		list.add(summaryVo);
		summaryVoDataTbl.setRows(list);
		summaryVoDataTbl.setTotal(1);
		return summaryVoDataTbl;
	}
}
