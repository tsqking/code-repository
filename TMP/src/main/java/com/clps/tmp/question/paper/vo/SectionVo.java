package com.clps.tmp.question.paper.vo;

public class SectionVo {

	//自增主键ID
	private int id;
	//模块名
	private String name;
	//模块描述
	private String instruction;
	//创建时间
	private String create_time;
	//创建人
	private String create_person;
	//更新时间
	private String update_time;
	//更新人
	private String update_person;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	public String toString(){
		return "SectionVo [name="+name+",instruction="+instruction+",create_time="+create_time+",create_person="
				+create_person+",update_time="+update_time+"update_person="+update_person+"]";
	}
}
