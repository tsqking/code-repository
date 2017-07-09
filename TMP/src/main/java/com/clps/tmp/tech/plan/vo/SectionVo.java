package com.clps.tmp.tech.plan.vo;

/**
 * @ClassName SectionVo
 * @Description TODO(题块)
 * @author liuchen
 * @Date 2016年5月30日 下午4:52:42
 * @version 1.0.0
 */
public class SectionVo {

	//id主键自增
	private String id;
	//模块名称
	private String name;
	//模块说明，考生可见
	private String instruction;
	//创建时间（YYYY-MM-DD HH-mm-SS）
	private String create_time;
	//创建人
	private String create_person;
	//修改时间（YYYY-MM-DD HH-mm-SS）
	private String update_time;
	//修改人
	private String update_person;
	//得分
	private String question_score;
	//总分
	private String question_score_all;
	//题数
	private String question_number;
	
	
	public String getQuestion_score() {
		return question_score;
	}
	public void setQuestion_score(String question_score) {
		this.question_score = question_score;
	}
	public String getQuestion_number() {
		return question_number;
	}
	public void setQuestion_number(String question_number) {
		this.question_number = question_number;
	}
	public String getQuestion_score_all() {
		return question_score_all;
	}
	public void setQuestion_score_all(String question_score_all) {
		this.question_score_all = question_score_all;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
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
