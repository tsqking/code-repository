package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName TotalScoreVo
 * @Description TODO(学生总成绩表)
 * @author liuchen
 * @Date 2016年6月14日 下午4:10:54
 * @version 1.0.0
 */
public class TotalScoreVo {

	private int id;//主键ID
	private int plan_id;//教学计划ID
	private int student_id;//学生ID
	private float normal_score;//平时分
	private float attendance_score;//考勤分
	private float attitude_score;//态度分
	private float attitude_score_old;//历史-态度分
	private float exam_score;//测试分
	private float total_score;//总分
	private int flag;//有效标识 0-无效 1-有效
	private String del;//删除标示 Y-已删除 N-未删除
	private String create_time;//创建时间(20150901 115959000)
	private String create_person;//创建人
	private String update_time;//修改时间(20150901 115959000)
	private String update_person;//修改人
	
	//额外信息
	private String student_name;//学生姓名
	private String student_no;//学生编号
	
	
	
	public float getAttitude_score_old() {
		return attitude_score_old;
	}
	public void setAttitude_score_old(float attitude_score_old) {
		this.attitude_score_old = attitude_score_old;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_no() {
		return student_no;
	}
	public void setStudent_no(String student_no) {
		this.student_no = student_no;
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
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public float getNormal_score() {
		return normal_score;
	}
	public void setNormal_score(float normal_score) {
		this.normal_score = normal_score;
	}
	public float getAttendance_score() {
		return attendance_score;
	}
	public void setAttendance_score(float attendance_score) {
		this.attendance_score = attendance_score;
	}
	public float getAttitude_score() {
		return attitude_score;
	}
	public void setAttitude_score(float attitude_score) {
		this.attitude_score = attitude_score;
	}
	public float getExam_score() {
		return exam_score;
	}
	public void setExam_score(float exam_score) {
		this.exam_score = exam_score;
	}
	public float getTotal_score() {
		return total_score;
	}
	public void setTotal_score(float total_score) {
		this.total_score = total_score;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
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
