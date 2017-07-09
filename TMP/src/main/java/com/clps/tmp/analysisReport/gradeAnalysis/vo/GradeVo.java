
package com.clps.tmp.analysisReport.gradeAnalysis.vo;
/**
 * 
 * ClassName: GradeVo.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2017年3月29日 下午2:37:25 
 *
 * @author tony.tan
 * @version 
 *
 */
public class GradeVo {
private Long range1;//成绩段：0~30
private Long range2;//成绩段：30-40
private Long range3;//成绩段：40-60
private Long range4;//成绩段：60~80
private Long range5;//成绩段：80~100
private Long range6;//成绩段：100~120
private Long total;//总成绩
public GradeVo() {
	
	super();
	// TODO Auto-generated constructor stub
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
public Long getTotal() {
	return total;
}
public void setTotal(Long total) {
	this.total = total;
}
@Override
public String toString() {
	return "GradeVo [range1=" + range1 + ", range2=" + range2 + ", range3=" + range3 + ", range4=" + range4
			+ ", range5=" + range5 + ", range6=" + range6 + ", total=" + total + "]";
}


}

