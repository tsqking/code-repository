package com.clps.tmp.question.tag.vo;

/**
 * 标签库
 * @author jevon
 *
 */
public class TagVo {

	//自增主键
	private int id;
	//标签名称
	private String name;
	//标签说明
	private String description;
	//使用次数
	private int user_count;
	//启用状态
	private String enable;
	//创建时间
	private String create_time;
	//创建人
	private String create_person;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUser_count() {
		return user_count;
	}
	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
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
	
	public String toString(){
		return "TagVo[id="+ id +",name="+ name +",description="+ description +",user_count="+ user_count 
				+",enable="+ enable +",create_time="+ create_time +",create_person="+ create_person +"]";
	}
}
