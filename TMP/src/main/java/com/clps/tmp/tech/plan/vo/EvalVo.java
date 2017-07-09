package com.clps.tmp.tech.plan.vo;
/**
 * 过程评价
 * @author Seven
 * 2016年1月19日
 */
public class EvalVo {
	//课程ID
	private String course_id;
	//班级ID
	private String class_id;
	//知识点ID
	private String point_id;
	//教师ID
	private String teacher_id;
	//学员ID
	private String student_id;
	//类型(1-教师对学员的评价，2-学员队教师的评价）
	private String type;
	//签到情况
	private String sign;
	//评分
	private double score;
	//评语
	private String comment;
	//创建时间
	private String create_time;
	//创建人
	private String create_person;
	//更新时间
	private String update_time;
	//更新人
	private String update_person;
	///////////
	//用户ID
	private String user_id;
	//用户中文名
	private String user_name;
	//用户英文名
	private String user_en_name;
	//标志辅助
	private String flag;
	//课程名称
	private String course_name;
	//班级名称
	private String class_name;
	//教师名称
	private String teacher_name;
	//学员名称
	private String student_name;
	
	
	
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
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_en_name() {
		return user_en_name;
	}
	public void setUser_en_name(String user_en_name) {
		this.user_en_name = user_en_name;
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
	public String getPoint_id() {
		return point_id;
	}
	public void setPoint_id(String point_id) {
		this.point_id = point_id;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	@Override
	public String toString() {
		return "ProcEvalVo [course_id=" + course_id + ", class_id=" + class_id
				+ ", point_id=" + point_id + ", teacher_id=" + teacher_id
				+ ", student_id=" + student_id + ", type=" + type + ", sign="
				+ sign + ", score=" + score + ", comment=" + comment
				+ ", create_time=" + create_time + ", create_person="
				+ create_person + ", update_time=" + update_time
				+ ", update_person=" + update_person + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", user_en_name=" + user_en_name
				+ ", flag=" + flag + "]";
	}

}
