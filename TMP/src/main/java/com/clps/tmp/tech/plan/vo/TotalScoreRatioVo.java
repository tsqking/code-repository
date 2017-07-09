package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName TotalScoreRatioVo
 * @Description TODO(总成绩组成加权表)
 * @author liuchen
 * @Date 2016年6月14日 下午4:07:14
 * @version 1.0.0
 */
public class TotalScoreRatioVo {

	private int id;//主键ID
	private int plan_id;//教学计划ID
	private int normal_ratio;//平时成绩比例
	private int attendance_ratio;//考勤成绩比例
	private int attitude_ratio;//态度成绩比例
	private int exam_ratio;//最后测试成绩比例
	private String del;//删除标示 Y-已删除 N-未删除
	private String create_time;//创建时间(20150901 115959000)
	private String create_person;//创建人
	private String update_time;//修改时间(20150901 115959000)
	private String update_person;//修改人
	
	//其他
	private String student_id;//学生id
	private String total_score;//总分
	
	
	public String getTotal_score() {
		return total_score;
	}
	public void setTotal_score(String total_score) {
		this.total_score = total_score;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public int getNormal_ratio() {
		return normal_ratio;
	}
	public void setNormal_ratio(int normal_ratio) {
		this.normal_ratio = normal_ratio;
	}
	public int getAttendance_ratio() {
		return attendance_ratio;
	}
	public void setAttendance_ratio(int attendance_ratio) {
		this.attendance_ratio = attendance_ratio;
	}
	public int getAttitude_ratio() {
		return attitude_ratio;
	}
	public void setAttitude_ratio(int attitude_ratio) {
		this.attitude_ratio = attitude_ratio;
	}
	public int getExam_ratio() {
		return exam_ratio;
	}
	public void setExam_ratio(int exam_ratio) {
		this.exam_ratio = exam_ratio;
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
