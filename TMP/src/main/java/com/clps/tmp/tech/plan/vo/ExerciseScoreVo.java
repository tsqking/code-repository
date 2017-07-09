package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName ExerciseScoreVo
 * @Description TODO(学生平时成绩明细)
 * @author liuchen
 * @Date 2016年6月14日 下午4:08:50
 * @version 1.0.0
 */
public class ExerciseScoreVo {

	private int id;//主键ID
	private int plan_id;//教学计划ID
	private int student_id;//学生ID
	private int paper_id;//试卷ID
	private float true_score;//卷面分数
	private float switch_score;//转换分数
	private String del;//删除标示 Y-已删除 N-未删除
	private String create_time;//创建时间(20150901 115959000)
	private String create_person;//创建人
	private String update_time;//修改时间(20150901 115959000)
	private String update_person;//修改人
	
	private String paper_ratio;//比率
	private String paper_flag;//是否为期末卷
	private String paper_no;//试卷编号
	private String paper_name;//试卷名
	private String paper_description;//描述
	private String paper_instruction;//说明
	private String paper_total_score;//试卷总分
	private String paper_type;//试卷类型
	private String ratio_num;//总分占比
	
	private String score;//态度分数
	private String comment;//评价
	private String sign_name;//出勤情况
	private String point_name;//知识点名字
	private String point_description;//知识点描述
	private String teacher_name;//打分老师
	
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSign_name() {
		return sign_name;
	}
	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}
	public String getPoint_name() {
		return point_name;
	}
	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}
	public String getPoint_description() {
		return point_description;
	}
	public void setPoint_description(String point_description) {
		this.point_description = point_description;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getRatio_num() {
		return ratio_num;
	}
	public void setRatio_num(String ratio_num) {
		this.ratio_num = ratio_num;
	}
	public String getPaper_ratio() {
		return paper_ratio;
	}
	public void setPaper_ratio(String paper_ratio) {
		this.paper_ratio = paper_ratio;
	}
	public String getPaper_flag() {
		return paper_flag;
	}
	public void setPaper_flag(String paper_flag) {
		this.paper_flag = paper_flag;
	}
	public String getPaper_no() {
		return paper_no;
	}
	public void setPaper_no(String paper_no) {
		this.paper_no = paper_no;
	}
	public String getPaper_name() {
		return paper_name;
	}
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	public String getPaper_description() {
		return paper_description;
	}
	public void setPaper_description(String paper_description) {
		this.paper_description = paper_description;
	}
	public String getPaper_instruction() {
		return paper_instruction;
	}
	public void setPaper_instruction(String paper_instruction) {
		this.paper_instruction = paper_instruction;
	}
	public String getPaper_total_score() {
		return paper_total_score;
	}
	public void setPaper_total_score(String paper_total_score) {
		this.paper_total_score = paper_total_score;
	}
	public String getPaper_type() {
		return paper_type;
	}
	public void setPaper_type(String paper_type) {
		this.paper_type = paper_type;
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
	public int getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}
	public float getTrue_score() {
		return true_score;
	}
	public void setTrue_score(float true_score) {
		this.true_score = true_score;
	}
	public float getSwitch_score() {
		return switch_score;
	}
	public void setSwitch_score(float switch_score) {
		this.switch_score = switch_score;
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
