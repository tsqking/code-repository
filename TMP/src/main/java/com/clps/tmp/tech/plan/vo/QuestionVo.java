package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName QuestionVo
 * @Description TODO(问题VO)
 * @author liuchen
 * @Date 2016年5月30日 上午9:42:17
 * @version 1.0.0
 */
public class QuestionVo {

	//id自增主键
	private String id;
	//题型 QUST_TYPE：1-单选，2-多选，3-判断，4-填空，5-简答，6-编程，7-综合
	private String type;
	private String type_name;
	//主客标识位 QUST_SO：s-Subject主观题，o-Object客观题
	private String so_flag;
	private String so_flag_name;
	//难易度 DIFFICULTY：1-易，2-中，3-难
	private String difficulty;
	private String difficulty_name;
	//题目属性 QUST_PROP：00-练习题，01-课堂练习，02-课后练习，03-模拟练习，04-综合练习
	private String property;
	private String property_name;
	//用途标识位 USE_FLAG：1-教学，2-校招，3-通用
	private String use_flag;
	private String use_flag_name;
	//题干
	private String content;
	//答案：主观参考，客观判题；多选题与多空填空题，答案之间用#$#来分隔
	private String answer;
	//选型内容一
	private String opt1;
	//选型内容二
	private String opt2;
	//选型内容三
	private String opt3;
	//选型内容四
	private String opt4;
	//选型内容五
	private String opt5;
	//选型内容六
	private String opt6;
	//题目解析
	private String analysis;
	//生效标识：T-生效，F-不生效
	private String enable;
	private String enable_name;
	//创建时间（YYYY-MM-DD HH-mm-SS）
	private String create_time;
	//创建人
	private String create_person;
	//修改时间（YYYY-MM-DD HH-mm-SS）
	private String update_time;
	//修改人
	private String update_person;
	
	//题目分数
	private String question_score;
	
	
	public String getQuestion_score() {
		return question_score;
	}
	public void setQuestion_score(String question_score) {
		this.question_score = question_score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getSo_flag() {
		return so_flag;
	}
	public void setSo_flag(String so_flag) {
		this.so_flag = so_flag;
	}
	public String getSo_flag_name() {
		return so_flag_name;
	}
	public void setSo_flag_name(String so_flag_name) {
		this.so_flag_name = so_flag_name;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getDifficulty_name() {
		return difficulty_name;
	}
	public void setDifficulty_name(String difficulty_name) {
		this.difficulty_name = difficulty_name;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getProperty_name() {
		return property_name;
	}
	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
	public String getUse_flag() {
		return use_flag;
	}
	public void setUse_flag(String use_flag) {
		this.use_flag = use_flag;
	}
	public String getUse_flag_name() {
		return use_flag_name;
	}
	public void setUse_flag_name(String use_flag_name) {
		this.use_flag_name = use_flag_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getEnable_name() {
		return enable_name;
	}
	public void setEnable_name(String enable_name) {
		this.enable_name = enable_name;
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
