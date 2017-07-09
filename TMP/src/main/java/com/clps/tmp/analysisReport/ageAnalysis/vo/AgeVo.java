/**
 * Project Name:TMP
 * File Name:ageVo.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysis.vo
 * Date:2017年4月14日下午3:32:00
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.ageAnalysis.vo;

/**
 * ClassName:ageVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月14日 下午3:32:00 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
public class AgeVo {
	private double range1;
	private double range2;
	private double range3;
	private double range4;
	private double range5;
	private double range6;
	private double range7;

	public AgeVo() {

		// TODO Auto-generated constructor stub

	}

	/**
	 * Creates a new instance of AgeVo.
	 *
	 * @param range1
	 * @param range2
	 * @param range3
	 * @param range4
	 * @param range5
	 * @param range6
	 * @param range7
	 */

	public AgeVo(double range1, double range2, double range3, double range4, double range5, double range6,
			double range7) {
		super();
		this.range1 = range1;
		this.range2 = range2;
		this.range3 = range3;
		this.range4 = range4;
		this.range5 = range5;
		this.range6 = range6;
		this.range7 = range7;
	}

	public double getRange1() {
		return range1;
	}

	public void setRange1(double range1) {
		this.range1 = range1;
	}

	public double getRange2() {
		return range2;
	}

	public void setRange2(double range2) {
		this.range2 = range2;
	}

	public double getRange3() {
		return range3;
	}

	public void setRange3(double range3) {
		this.range3 = range3;
	}

	public double getRange4() {
		return range4;
	}

	public void setRange4(double range4) {
		this.range4 = range4;
	}

	public double getRange5() {
		return range5;
	}

	public void setRange5(double range5) {
		this.range5 = range5;
	}

	public double getRange6() {
		return range6;
	}

	public void setRange6(double range6) {
		this.range6 = range6;
	}

	public double getRange7() {
		return range7;
	}

	public void setRange7(double range7) {
		this.range7 = range7;
	}

	@Override
	public String toString() {
		return "AgeVo [range1=" + range1 + ", range2=" + range2 + ", range3=" + range3 + ", range4=" + range4
				+ ", range5=" + range5 + ", range6=" + range6 + ", range7=" + range7 + "]";
	}

}
