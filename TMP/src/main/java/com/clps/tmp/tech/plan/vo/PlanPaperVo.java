package com.clps.tmp.tech.plan.vo;
import java.io.Serializable;


@SuppressWarnings("serial")
public class PlanPaperVo implements Serializable{

	//主键ID
	private String id;
	//状态 0-未布置 1-已布置
	private String status;
	//教学计划ID
	private String plan_id;
	//课程ID
	private String course_id;
	//班级ID
	private String class_id;
	//老师ID
	private String teacher_id;
	//试卷ID
	private String paper_id;
	//试卷编号 UUID
	private String paper_number;
	//试卷属性 1-测试，2-练习
	private String paper_type;
	//试卷开始时间(20150901 115959000)
	private String paper_start_time;
	//试卷结束时间(20150901 115959000)
	private String paper_end_time;
	//试卷时长(XDaysXHoursXMins)
	private String paper_long_time;
	//试卷备注
	private String paper_remark;
	//试卷链接
	private String url;
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
	
	
	//试卷名称
	private String paper_name;
	//班级名称
	private String class_name;
	//状态 0-未布置 1-已布置
	private String status_name;
	//试卷属性 1-测试，2-练习
	private String paper_type_name;
	//是否为期末卷 null-不是 0-不是 1-是
	private String flag;
	//学生ID
	private String student_id;
	//试卷得分
	private String score;
	//试卷总分
	private String total_score;
	//加权比例
	private String ratio;
	//比例id
	private String exercise_score_ratio_id;
	//总比例
	private String all_ratio;
	//学生id数组
	private String student_ids;
	//计划测试id
	private String plan_paper_id;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAll_ratio() {
		return all_ratio;
	}
	public void setAll_ratio(String all_ratio) {
		this.all_ratio = all_ratio;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getExercise_score_ratio_id() {
		return exercise_score_ratio_id;
	}
	public void setExercise_score_ratio_id(String exercise_score_ratio_id) {
		this.exercise_score_ratio_id = exercise_score_ratio_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTotal_score() {
		return total_score;
	}
	public void setTotal_score(String total_score) {
		this.total_score = total_score;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}
	public String getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}
	public String getPaper_number() {
		return paper_number;
	}
	public void setPaper_number(String paper_number) {
		this.paper_number = paper_number;
	}
	public String getPaper_type() {
		return paper_type;
	}
	public void setPaper_type(String paper_type) {
		this.paper_type = paper_type;
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
	public String getPaper_long_time() {
		return paper_long_time;
	}
	public void setPaper_long_time(String paper_long_time) {
		this.paper_long_time = paper_long_time;
	}
	public String getPaper_remark() {
		return paper_remark;
	}
	public void setPaper_remark(String paper_remark) {
		this.paper_remark = paper_remark;
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
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getPaper_name() {
		return paper_name;
	}
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getPaper_type_name() {
		return paper_type_name;
	}
	public void setPaper_type_name(String paper_type_name) {
		this.paper_type_name = paper_type_name;
	}
	public String getPlan_paper_id() {
		return plan_paper_id;
	}
	public void setPlan_paper_id(String plan_paper_id) {
		this.plan_paper_id = plan_paper_id;
	}
	public String getStudent_ids() {
		return student_ids;
	}
	public void setStudent_ids(String student_ids) {
		this.student_ids = student_ids;
	}
	

}
