package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName AnswerVo
 * @Description TODO(答案vo)
 * @author liuchen
 * @Date 2016年5月30日 上午9:50:43
 * @version 1.0.0
 */
public class AnswerSheetVo {

	//ANSWER_SHEET
	//试卷ID
	private String paper_id;
	private String paper_id_name;
	//题目ID
	//private String question_id;
	private String question_id_name;
	//考生ID
	private String user_id;
	private String user_id_name;
	//考生类型（1-招生模块，2-教学模块），不同类型，对应不同的人员表
	private String user_type;
	private String user_type_name;
	//考生答案（多选题和多空填空题，答案之间使用#$#来分隔）
	//private String answer;
	//答题时间（YYYY-MM-DD HH-mm-SS）
	private String answer_time;
	//备注
	private String remark;
	//考生此题得分
	//private float score;
	//阅卷人（系统或人）
	//private String judge_person;
	//阅卷时间（YYYY-MM-DD HH-mm-SS）
	private String judge_time;
	
	//模块id
	private String section_id;
	//题目id
	private String question_id;
	//名称
	private String section_name;
	//描述
	private String section_instruction;
	//题目类型
	private String type;
	private String type_name;
	//题目内容
	private String content;
	//正确答案
	private String true_answer;
	//选项
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private String opt5;
	private String opt6;
	//分析
	private String analysis;
	//学生答案1
	private String answer;
	//批改人
	private String judge_person;
	//得分
	private float score;
	//总分
	private String all_score;
	//主客标识位 QUST_SO：s-Subject主观题，o-Object客观题
	private String so_flag;
	
	
	
	public String getSo_flag() {
		return so_flag;
	}
	public void setSo_flag(String so_flag) {
		this.so_flag = so_flag;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getSection_instruction() {
		return section_instruction;
	}
	public void setSection_instruction(String section_instruction) {
		this.section_instruction = section_instruction;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getAll_score() {
		return all_score;
	}
	public void setAll_score(String all_score) {
		this.all_score = all_score;
	}
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTrue_answer() {
		return true_answer;
	}
	public void setTrue_answer(String true_answer) {
		this.true_answer = true_answer;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public String getOpt5() {
		return opt5;
	}
	public void setOpt5(String opt5) {
		this.opt5 = opt5;
	}
	public String getOpt6() {
		return opt6;
	}
	public void setOpt6(String opt6) {
		this.opt6 = opt6;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
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
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_id_name() {
		return question_id_name;
	}
	public void setQuestion_id_name(String question_id_name) {
		this.question_id_name = question_id_name;
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer_time() {
		return answer_time;
	}
	public void setAnswer_time(String answer_time) {
		this.answer_time = answer_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
