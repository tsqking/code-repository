package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName AnswerVo
 * @Description TODO(答案vo)
 * @author liuchen
 * @Date 2016年5月30日 上午9:50:43
 * @version 1.0.0
 */
public class AnswerInfoVo {

	//ANSWER_INFO
	//试卷ID
	private String paper_id;
	private String paper_id_name;
	//考生ID
	private String user_id;
	private String user_id_name;
	//考生类型（1-招生模块，2-教学模块）不同类型，对应不同的人员表
	private String user_type;
	private String user_type_name;
	//目前答卷耗费时长（00：09：30）
	private String cost_time;
	//离开次数
	private int leave_limit;
	//是否完成答卷标志位（0-未完成，1-已完成）
	private String finish_flag;
	private String finish_flag_name;
	//答卷开始时间（YYYY-MM-DD HH-mm-SS）
	private String start_time;
	//答卷结束时间（YYYY-MM-DD HH-mm-SS）
	private String end_time;
	//考生答卷总得分
	private String score;
	//试卷总分
	private String all_score;
	//判卷人（系统或人）
	private String judge_person;
	//判卷时间（YYYY-MM-DD HH-mm-SS）
	private String judge_time;
	
	//总条数
	private String count_all;
	//已批改
	private String count;
	//进度
	private String progress;
	//教学计划
	private String plan_paper_id;
	
	
	public String getPlan_paper_id() {
		return plan_paper_id;
	}
	public void setPlan_paper_id(String plan_paper_id) {
		this.plan_paper_id = plan_paper_id;
	}
	public String getAll_score() {
		return all_score;
	}
	public void setAll_score(String all_score) {
		this.all_score = all_score;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getCount_all() {
		return count_all;
	}
	public void setCount_all(String count_all) {
		this.count_all = count_all;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}
	public String getPaper_id_name() {
		return paper_id_name;
	}
	public void setPaper_id_name(String paper_id_name) {
		this.paper_id_name = paper_id_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_id_name() {
		return user_id_name;
	}
	public void setUser_id_name(String user_id_name) {
		this.user_id_name = user_id_name;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getUser_type_name() {
		return user_type_name;
	}
	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}
	public String getCost_time() {
		return cost_time;
	}
	public void setCost_time(String cost_time) {
		this.cost_time = cost_time;
	}
	public int getLeave_limit() {
		return leave_limit;
	}
	public void setLeave_limit(int leave_limit) {
		this.leave_limit = leave_limit;
	}
	public String getFinish_flag() {
		return finish_flag;
	}
	public void setFinish_flag(String finish_flag) {
		this.finish_flag = finish_flag;
	}
	public String getFinish_flag_name() {
		return finish_flag_name;
	}
	public void setFinish_flag_name(String finish_flag_name) {
		this.finish_flag_name = finish_flag_name;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getJudge_person() {
		return judge_person;
	}
	public void setJudge_person(String judge_person) {
		this.judge_person = judge_person;
	}
	public String getJudge_time() {
		return judge_time;
	}
	public void setJudge_time(String judge_time) {
		this.judge_time = judge_time;
	}
	
}
