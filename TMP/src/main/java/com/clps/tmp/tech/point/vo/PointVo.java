package com.clps.tmp.tech.point.vo;

/**
 * @author Seven
 *
 * 2015年11月3日
 */
public class PointVo {
	// id号，自增主键
	private String id;
	// 知识点名称
	private String name;
	// 知识点名称--英文
	private String name_en_US;
	// 排序序号
	private String order;
	// 描述
	private String description;
	// 描述--英文
	private String description_en_US;
	// 生效标示 'T'-启用状态/'F'-禁用状态
	private String enable;
	// 创建时间
	private String create_time;
	// 创建人
	private String create_person;
	// 更新时间
	private String update_time;
	// 更新人
	private String update_person;
	//**************************
	// 一级技能 id
	private String first_skill_id;
	// 一级技能名称
	private String first_skill;
	// 二级技能 id
	private String second_skill_id;
	// 二级技能名称
	private String second_skill;
	// 三级技能 id
	private String third_skill_id;
	// 三级技能名称
	private String third_skill;
	//**************************
	// 生效标示 键'T'/'F'
	private String enable_key;
	// 更新时候用
	private String last_update_time;
	// 更新时候用
	private String last_update_person;
	//**************************
	private String manage;
	public String getManage() {
		return manage;
	}
	public void setManage(String manage) {
		this.manage = manage;
	}
	public String getId() {
		return id;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public String getLast_update_person() {
		return last_update_person;
	}
	public void setLast_update_person(String last_update_person) {
		this.last_update_person = last_update_person;
	}
	public String getEnable_key() {
		return enable_key;
	}
	public void setEnable_key(String enable_key) {
		this.enable_key = enable_key;
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
	public String getName_en_US() {
		return name_en_US;
	}
	public void setName_en_US(String name_en_US) {
		this.name_en_US = name_en_US;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription_en_US() {
		return description_en_US;
	}
	public void setDescription_en_US(String description_en_US) {
		this.description_en_US = description_en_US;
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
	public String getFirst_skill_id() {
		return first_skill_id;
	}
	public void setFirst_skill_id(String first_skill_id) {
		this.first_skill_id = first_skill_id;
	}
	public String getFirst_skill() {
		return first_skill;
	}
	public void setFirst_skill(String first_skill) {
		this.first_skill = first_skill;
	}
	public String getSecond_skill_id() {
		return second_skill_id;
	}
	public void setSecond_skill_id(String second_skill_id) {
		this.second_skill_id = second_skill_id;
	}
	public String getSecond_skill() {
		return second_skill;
	}
	public void setSecond_skill(String second_skill) {
		this.second_skill = second_skill;
	}
	public String getThird_skill_id() {
		return third_skill_id;
	}
	public void setThird_skill_id(String third_skill_id) {
		this.third_skill_id = third_skill_id;
	}
	public String getThird_skill() {
		return third_skill;
	}
	public void setThird_skill(String third_skill) {
		this.third_skill = third_skill;
	}
	@Override
	public String toString() {
		return "PointVo [id=" + id + ", name=" + name + ", name_en_US="
				+ name_en_US + ", order=" + order + ", description="
				+ description + ", description_en_US=" + description_en_US
				+ ", enable=" + enable + ", create_time=" + create_time
				+ ", create_person=" + create_person + ", update_time="
				+ update_time + ", update_person=" + update_person
				+ ", first_skill_id=" + first_skill_id + ", first_skill="
				+ first_skill + ", second_skill_id=" + second_skill_id
				+ ", second_skill=" + second_skill + ", third_skill_id="
				+ third_skill_id + ", third_skill=" + third_skill + "]";
	}
	
}
