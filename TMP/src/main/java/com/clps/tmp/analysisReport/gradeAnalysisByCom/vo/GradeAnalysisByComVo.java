/**
 * Project Name:TMP
 * File Name:GradeAnalysisByComVo.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisByCom.vo
 * Date:2017年4月19日下午3:45:35
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisByCom.vo;


/**
 * ClassName:GradeAnalysisByComVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午3:45:35 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class GradeAnalysisByComVo {
	private int range1;
	private int range2;
	private int range3;
	private int range4;
	private int range5;
	private int range6;
	private String subject;

	public GradeAnalysisByComVo() {

		// TODO Auto-generated constructor stub

	}

	/**
	 * Creates a new instance of GradeAnalysisByComVo.
	 *
	 * @param range1
	 * @param range2
	 * @param range3
	 * @param range4
	 * @param range5
	 * @param range6
	 * @param subject
	 */

	public GradeAnalysisByComVo(int range1, int range2, int range3, int range4,
			int range5, int range6, String subject) {
		super();
		this.range1 = range1;
		this.range2 = range2;
		this.range3 = range3;
		this.range4 = range4;
		this.range5 = range5;
		this.range6 = range6;
		this.subject = subject;
	}

	public int getRange1() {
		return range1;
	}

	public void setRange1(int range1) {
		this.range1 = range1;
	}

	public int getRange2() {
		return range2;
	}

	public void setRange2(int range2) {
		this.range2 = range2;
	}

	public int getRange3() {
		return range3;
	}

	public void setRange3(int range3) {
		this.range3 = range3;
	}

	public int getRange4() {
		return range4;
	}

	public void setRange4(int range4) {
		this.range4 = range4;
	}

	public int getRange5() {
		return range5;
	}

	public void setRange5(int range5) {
		this.range5 = range5;
	}

	public int getRange6() {
		return range6;
	}

	public void setRange6(int range6) {
		this.range6 = range6;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "GradeAnalysisByComVo [range1=" + range1 + ", range2=" + range2 + ", range3=" + range3 + ", range4="
				+ range4 + ", range5=" + range5 + ", range6=" + range6 + ", subject=" + subject + "]";
	}

}
