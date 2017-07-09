package com.clps.tmp.tech.plan.vo;
import java.io.Serializable;


@SuppressWarnings("serial")
public class PlanPaperStuHisVo implements Serializable{

	//主键ID
	private String id;
	//计划测试ID
	private String plan_paper_id;
	//学生ID（对应于sm_person中的人员）
	private String student_id;
	//状态 0-未完成 1-已完成 2-已批阅
	private String status;
	//答卷开始时间(20150901 115959000)
	private String start_time;
	//答卷结束时间(20150901 115959000)
	private String end_time;
	//答卷耗时(XDaysXHoursXMins)
	private String long_time;
	//分数
	private String score;
	//批卷人
	private String teacher_id;
	//批卷时间(20150901 115959000)
	private String check_time;
	//删除标示 Y-已删除 N-未删除
	private String del;
	//创建时间(20150901 115959000)
	private String create_time;
	//创建人
	private String create_person;
	//修改时间(20150901 115959000)
	private String update_time;
	//修改人
	private String update_person;
	
	//其他
	//学生姓名
	private String student_name;
	//状态名
	private String status_name;
	//批卷人
	private String teacher_name;
	//试卷ID
	private String paper_id;
	//试卷名称
	private String paper_name;
	//试卷编号
	private String paper_number;
	//试卷用途
	private String paper_type_name;
	//班级名称
	private String class_name;
	//课程名称
	private String course_name;
	//试卷开始时间(20150901 115959000)
	private String paper_start_time;
	//试卷结束时间(20150901 115959000)
	private String paper_end_time;
	//试卷时长
	private String paper_long_time;
	
	//试卷链接
	private String url;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}
	public String getPaper_long_time() {
		return paper_long_time;
	}
	public void setPaper_long_time(String paper_long_time) {
		this.paper_long_time = paper_long_time;
	}
	public String getPaper_start_time() {
		return paper_start_time;
	}
	public void setPaper_start_time(String paper_start_time) {
		this.paper_start_time = paper_start_time;
	}
	public String getPaper_end_time() {
		return paper_end_time;
	}
	public void setPaper_end_time(String paper_end_time) {
		this.paper_end_time = paper_end_time;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getPaper_name() {
		return paper_name;
	}
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	public String getPaper_number() {
		return paper_number;
	}
	public void setPaper_number(String paper_number) {
		this.paper_number = paper_number;
	}
	public String getPaper_type_name() {
		return paper_type_name;
	}
	public void setPaper_type_name(String paper_type_name) {
		this.paper_type_name = paper_type_name;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlan_paper_id() {
		return plan_paper_id;
	}
	public void setPlan_paper_id(String plan_paper_id) {
		this.plan_paper_id = plan_paper_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getLong_time() {
		return long_time;
	}
	public void setLong_time(String long_time) {
		this.long_time = long_time;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getCheck_time() {
		return check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_person() {
		return update_person;
	}
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}

}
