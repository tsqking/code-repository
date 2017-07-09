package com.clps.tmp.tech.teacherpool.vo;

public class HistoryVo {
	//教师号
	private String teacher_id;
	//班级号
	private String class_id;
	//课程号
	private String course_id;
	//知识点号
	private String point_id;
	//开始时间
	private String starttime;
	//结束时间
	private String endtime;
	//地点
	private String address;
	//确认时间
	private String confirmtime;
	//确认人
	private String confirmperson;
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getConfirmperson() {
		return confirmperson;
	}
	public void setConfirmperson(String confirmperson) {
		this.confirmperson = confirmperson;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getPoint_id() {
		return point_id;
	}
	public void setPoint_id(String point_id) {
		this.point_id = point_id;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getConfirmtime() {
		return confirmtime;
	}
	public void setConfirmtime(String confirmtime) {
		this.confirmtime = confirmtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "HistoryVo [teacher_id=" + teacher_id + ", class_id=" + class_id
				+ ", course_id=" + course_id + ", point_id=" + point_id
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", address=" + address + ", confirmtime=" + confirmtime
				+ ", confirmperson=" + confirmperson + "]";
	}
	
	
}
