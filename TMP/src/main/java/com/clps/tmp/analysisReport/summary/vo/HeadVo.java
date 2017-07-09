/**
 * Project Name:TMP
 * File Name:HeadVo.java
 * Package Name:com.clps.tmp.analysisReport.summary.vo
 * Date:2017年4月25日下午12:36:08
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.summary.vo;

/**
 * ClassName:HeadVo <br/>
 * Function: TODO 视图类：存放男女生数量，平均考试年龄. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月25日 下午12:36:08 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class HeadVo {
	private int maleNum;// 男生数量
	private int femaleNum;// 女生数量
	private int examAvgAge;// 平均考试年龄
	private String examSub;//考试科目

	public HeadVo() {

	}

	/**
	 * Creates a new instance of HeadVo.
	 *
	 * @param maleNum
	 * @param femaleNum
	 * @param examAvgAge
	 * @param examSub
	 */

	public HeadVo(int maleNum, int femaleNum, int examAvgAge, String examSub) {
		super();
		this.maleNum = maleNum;
		this.femaleNum = femaleNum;
		this.examAvgAge = examAvgAge;
		this.examSub = examSub;
	}

	public int getMaleNum() {
		return maleNum;
	}

	public void setMaleNum(int maleNum) {
		this.maleNum = maleNum;
	}

	public int getFemaleNum() {
		return femaleNum;
	}

	public void setFemaleNum(int femaleNum) {
		this.femaleNum = femaleNum;
	}

	public int getExamAvgAge() {
		return examAvgAge;
	}

	public void setExamAvgAge(int examAvgAge) {
		this.examAvgAge = examAvgAge;
	}

	public String getExamSub() {
		return examSub;
	}

	public void setExamSub(String examSub) {
		this.examSub = examSub;
	}

	@Override
	public String toString() {
		return "HeadVo [maleNum=" + maleNum + ", femaleNum=" + femaleNum + ", examAvgAge=" + examAvgAge + ", examSub="
				+ examSub + "]";
	}

}
