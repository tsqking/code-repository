/**
 * Project Name:TMP
 * File Name:SummaryVo.java
 * Package Name:com.clps.tmp.analysisReport.summary.vo
 * Date:2017年4月18日下午2:18:15
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.summary.vo;

/**
 * ClassName: SummaryVo <br/>
 * date: 2017年4月26日 下午5:56:20 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 */
public class SummaryVo {
	private String chioseLogicAvgScore;// 选择题业务部分平均得分
	private String chioseTechAvgScore;// 选择题技术部分平均得分
	private String chioseAvg;// 选择题平均得分
	private String chioseQuesCount;// 选择题总题数目
	private String chioseTotalScore;// 选择题总分
	private String chioseFinishRate;// 选择题完成率
	private String fillAvgScore;// 填空题平均得分
	private String fillQuesCount;// 填空题总题数目
	private String fillTotalScore;// 填空题总分
	private String fillFinishRate;// 填空题完成率
	private String programAvgScore;// 编程题平均得分
	private String programQuesCount;// 编程题总题数目
	private String programTotalScore;// 编程题总分
	private String programFinishRate;// 编程题完成率
	private String examAvgDuration;// 平均考试答题时长
	private String formularyExamDuration;// 规定考试答题时长
	private String tfAvgScore;// 是非题平均得分
	private String tfQuesCount;// 是非题总题目数
	private String tfTotalScore;// 是非题总分
	private String tfFinishRate;// 是非题完成率
	private String shortAvgScore;// 简答题平均得分
	private String shortQuesCount;// 简答题总题目数
	private String shortTotalScore;// 简答题总分
	private String shortFinishRate;// 简答题完成率
	private String absentRate;// 缺考率

	public SummaryVo() {

	}

	/**
	 * Creates a new instance of SummaryVo.
	 *
	 * @param chioseLogicAvgScore
	 * @param chioseTechAvgScore
	 * @param chioseAvg
	 * @param chioseQuesCount
	 * @param chioseTotalScore
	 * @param chioseFinishRate
	 * @param fillAvgScore
	 * @param fillQuesCount
	 * @param fillTotalScore
	 * @param fillFinishRate
	 * @param programAvgScore
	 * @param programQuesCount
	 * @param programTotalScore
	 * @param programFinishRate
	 * @param examAvgDuration
	 * @param formularyExamDuration
	 * @param tfAvgScore
	 * @param tfQuesCount
	 * @param tfTotalScore
	 * @param tfFinishRate
	 * @param shortAvgScore
	 * @param shortQuesCount
	 * @param shortTotalScore
	 * @param shortFinishRate
	 * @param absentRate
	 */

	public SummaryVo(String chioseLogicAvgScore, String chioseTechAvgScore, String chioseAvg, String chioseQuesCount,
			String chioseTotalScore, String chioseFinishRate, String fillAvgScore, String fillQuesCount,
			String fillTotalScore, String fillFinishRate, String programAvgScore, String programQuesCount,
			String programTotalScore, String programFinishRate, String examAvgDuration, String formularyExamDuration,
			String tfAvgScore, String tfQuesCount, String tfTotalScore, String tfFinishRate, String shortAvgScore,
			String shortQuesCount, String shortTotalScore, String shortFinishRate, String absentRate) {
		super();
		this.chioseLogicAvgScore = chioseLogicAvgScore;
		this.chioseTechAvgScore = chioseTechAvgScore;
		this.chioseAvg = chioseAvg;
		this.chioseQuesCount = chioseQuesCount;
		this.chioseTotalScore = chioseTotalScore;
		this.chioseFinishRate = chioseFinishRate;
		this.fillAvgScore = fillAvgScore;
		this.fillQuesCount = fillQuesCount;
		this.fillTotalScore = fillTotalScore;
		this.fillFinishRate = fillFinishRate;
		this.programAvgScore = programAvgScore;
		this.programQuesCount = programQuesCount;
		this.programTotalScore = programTotalScore;
		this.programFinishRate = programFinishRate;
		this.examAvgDuration = examAvgDuration;
		this.formularyExamDuration = formularyExamDuration;
		this.tfAvgScore = tfAvgScore;
		this.tfQuesCount = tfQuesCount;
		this.tfTotalScore = tfTotalScore;
		this.tfFinishRate = tfFinishRate;
		this.shortAvgScore = shortAvgScore;
		this.shortQuesCount = shortQuesCount;
		this.shortTotalScore = shortTotalScore;
		this.shortFinishRate = shortFinishRate;
		this.absentRate = absentRate;
	}

	public String getChioseLogicAvgScore() {
		return chioseLogicAvgScore;
	}

	public void setChioseLogicAvgScore(String chioseLogicAvgScore) {
		this.chioseLogicAvgScore = chioseLogicAvgScore;
	}

	public String getChioseTechAvgScore() {
		return chioseTechAvgScore;
	}

	public void setChioseTechAvgScore(String chioseTechAvgScore) {
		this.chioseTechAvgScore = chioseTechAvgScore;
	}

	public String getChioseAvg() {
		return chioseAvg;
	}

	public void setChioseAvg(String chioseAvg) {
		this.chioseAvg = chioseAvg;
	}

	public String getChioseQuesCount() {
		return chioseQuesCount;
	}

	public void setChioseQuesCount(String chioseQuesCount) {
		this.chioseQuesCount = chioseQuesCount;
	}

	public String getChioseTotalScore() {
		return chioseTotalScore;
	}

	public void setChioseTotalScore(String chioseTotalScore) {
		this.chioseTotalScore = chioseTotalScore;
	}

	public String getChioseFinishRate() {
		return chioseFinishRate;
	}

	public void setChioseFinishRate(String chioseFinishRate) {
		this.chioseFinishRate = chioseFinishRate;
	}

	public String getFillAvgScore() {
		return fillAvgScore;
	}

	public void setFillAvgScore(String fillAvgScore) {
		this.fillAvgScore = fillAvgScore;
	}

	public String getFillQuesCount() {
		return fillQuesCount;
	}

	public void setFillQuesCount(String fillQuesCount) {
		this.fillQuesCount = fillQuesCount;
	}

	public String getFillTotalScore() {
		return fillTotalScore;
	}

	public void setFillTotalScore(String fillTotalScore) {
		this.fillTotalScore = fillTotalScore;
	}

	public String getFillFinishRate() {
		return fillFinishRate;
	}

	public void setFillFinishRate(String fillFinishRate) {
		this.fillFinishRate = fillFinishRate;
	}

	public String getProgramAvgScore() {
		return programAvgScore;
	}

	public void setProgramAvgScore(String programAvgScore) {
		this.programAvgScore = programAvgScore;
	}

	public String getProgramQuesCount() {
		return programQuesCount;
	}

	public void setProgramQuesCount(String programQuesCount) {
		this.programQuesCount = programQuesCount;
	}

	public String getProgramTotalScore() {
		return programTotalScore;
	}

	public void setProgramTotalScore(String programTotalScore) {
		this.programTotalScore = programTotalScore;
	}

	public String getProgramFinishRate() {
		return programFinishRate;
	}

	public void setProgramFinishRate(String programFinishRate) {
		this.programFinishRate = programFinishRate;
	}

	public String getExamAvgDuration() {
		return examAvgDuration;
	}

	public void setExamAvgDuration(String examAvgDuration) {
		this.examAvgDuration = examAvgDuration;
	}

	public String getFormularyExamDuration() {
		return formularyExamDuration;
	}

	public void setFormularyExamDuration(String formularyExamDuration) {
		this.formularyExamDuration = formularyExamDuration;
	}

	public String getTfAvgScore() {
		return tfAvgScore;
	}

	public void setTfAvgScore(String tfAvgScore) {
		this.tfAvgScore = tfAvgScore;
	}

	public String getTfQuesCount() {
		return tfQuesCount;
	}

	public void setTfQuesCount(String tfQuesCount) {
		this.tfQuesCount = tfQuesCount;
	}

	public String getTfTotalScore() {
		return tfTotalScore;
	}

	public void setTfTotalScore(String tfTotalScore) {
		this.tfTotalScore = tfTotalScore;
	}

	public String getTfFinishRate() {
		return tfFinishRate;
	}

	public void setTfFinishRate(String tfFinishRate) {
		this.tfFinishRate = tfFinishRate;
	}

	public String getShortAvgScore() {
		return shortAvgScore;
	}

	public void setShortAvgScore(String shortAvgScore) {
		this.shortAvgScore = shortAvgScore;
	}

	public String getShortQuesCount() {
		return shortQuesCount;
	}

	public void setShortQuesCount(String shortQuesCount) {
		this.shortQuesCount = shortQuesCount;
	}

	public String getShortTotalScore() {
		return shortTotalScore;
	}

	public void setShortTotalScore(String shortTotalScore) {
		this.shortTotalScore = shortTotalScore;
	}

	public String getShortFinishRate() {
		return shortFinishRate;
	}

	public void setShortFinishRate(String shortFinishRate) {
		this.shortFinishRate = shortFinishRate;
	}

	public String getAbsentRate() {
		return absentRate;
	}

	public void setAbsentRate(String absentRate) {
		this.absentRate = absentRate;
	}

	@Override
	public String toString() {
		return "SummaryVo [chioseLogicAvgScore=" + chioseLogicAvgScore + ", chioseTechAvgScore=" + chioseTechAvgScore
				+ ", chioseAvg=" + chioseAvg + ", chioseQuesCount=" + chioseQuesCount + ", chioseTotalScore="
				+ chioseTotalScore + ", chioseFinishRate=" + chioseFinishRate + ", fillAvgScore=" + fillAvgScore
				+ ", fillQuesCount=" + fillQuesCount + ", fillTotalScore=" + fillTotalScore + ", fillFinishRate="
				+ fillFinishRate + ", programAvgScore=" + programAvgScore + ", programQuesCount=" + programQuesCount
				+ ", programTotalScore=" + programTotalScore + ", programFinishRate=" + programFinishRate
				+ ", examAvgDuration=" + examAvgDuration + ", formularyExamDuration=" + formularyExamDuration
				+ ", tfAvgScore=" + tfAvgScore + ", tfQuesCount=" + tfQuesCount + ", tfTotalScore=" + tfTotalScore
				+ ", tfFinishRate=" + tfFinishRate + ", shortAvgScore=" + shortAvgScore + ", shortQuesCount="
				+ shortQuesCount + ", shortTotalScore=" + shortTotalScore + ", shortFinishRate=" + shortFinishRate
				+ ", absentRate=" + absentRate + "]";
	}
}
