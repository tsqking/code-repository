package com.clps.tmp.core.sm.vo;

/**
 * @author Seven
 *
 * 2015年10月30日
 */
public class DateVo {
	//日期 如	20151010
	String date;
	//标识假期  'Y'/'N'
	String holiday;
	//描述
	String common;
	//开始时间
	private String start_time;
	private String start_time1;
	//结束时间
	private String end_time;
	private String end_time1;
	//时长-毫秒
	private String cost;
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getCommon() {
		return common;
	}
	public void setCommon(String common) {
		this.common = common;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getStart_time1() {
		return start_time1;
	}
	public void setStart_time1(String start_time1) {
		this.start_time1 = start_time1;
	}
	public String getEnd_time1() {
		return end_time1;
	}
	public void setEnd_time1(String end_time1) {
		this.end_time1 = end_time1;
	}
	
	
}
