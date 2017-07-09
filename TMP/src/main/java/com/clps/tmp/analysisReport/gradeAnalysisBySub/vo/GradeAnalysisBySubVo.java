/**
 * Project Name:TMP
 * File Name:GradeAnalysisBySubVo.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisBySub.vo
 * Date:2017年4月19日下午2:52:45
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisBySub.vo;

/**
 * ClassName:GradeAnalysisBySubVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:52:45 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
// 通过科目查询，得到公司
public class GradeAnalysisBySubVo {
	private Long range1;
	private Long range2;
	private Long range3;
	private Long range4;
	private Long range5;
	private Long range6;
	private String company;

	public GradeAnalysisBySubVo() {

		// TODO Auto-generated constructor stub

	}

	/**
	 * Creates a new instance of GradeAnalysisBySubVo.
	 *
	 * @param range1
	 * @param range2
	 * @param range3
	 * @param range4
	 * @param range5
	 * @param range6
	 * @param company
	 */

	public GradeAnalysisBySubVo(Long range1, Long range2, Long range3, Long range4, Long range5, Long range6,
			String company) {
		super();
		this.range1 = range1;
		this.range2 = range2;
		this.range3 = range3;
		this.range4 = range4;
		this.range5 = range5;
		this.range6 = range6;
		this.company = company;
	}

	public Long getRange1() {
		return range1;
	}

	public void setRange1(Long range1) {
		this.range1 = range1;
	}

	public Long getRange2() {
		return range2;
	}

	public void setRange2(Long range2) {
		this.range2 = range2;
	}

	public Long getRange3() {
		return range3;
	}

	public void setRange3(Long range3) {
		this.range3 = range3;
	}

	public Long getRange4() {
		return range4;
	}

	public void setRange4(Long range4) {
		this.range4 = range4;
	}

	public Long getRange5() {
		return range5;
	}

	public void setRange5(Long range5) {
		this.range5 = range5;
	}

	public Long getRange6() {
		return range6;
	}

	public void setRange6(Long range6) {
		this.range6 = range6;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "GradeAnalysisBySubVo [range1=" + range1 + ", range2=" + range2 + ", range3=" + range3 + ", range4="
				+ range4 + ", range5=" + range5 + ", range6=" + range6 + ", company=" + company + "]";
	}

}
