package com.clps.tmp.core.sm.vo;


/**
 *  角色
  * @ClassName: RoleVo
  * @Description: TODO
  * @author Seven
  * @date 2015年10月19日
 */
public class RoleVo{
	//id 自增
	private String id;
	//角色名称
	private String name;
	//角色名称 英文
	private String name_en_US;
	//描述
	private String description;
	//描述 英文
	private String description_en_US;
	//生效表示 'T'或'F'
	private String enable;
	//创建时间 '20151019 115959000'
	private String create_time;
	//创建人员
	private String create_person;
	//更新时间 '20151019 115959000'
	private String update_time;
	//更新人
	private String update_person;
	//**************************
	//上次更新时间  '20151019 115959000'
	private String last_update_time;
	//**************************
	//搜索条件  角色名称
	private String searchRoleName;
	//搜索条件 角色状态 
	private String searchRoleStatus;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getSearchRoleName() {
		return searchRoleName;
	}
	public void setSearchRoleName(String searchRoleName) {
		this.searchRoleName = searchRoleName;
	}
	public String getSearchRoleStatus() {
		return searchRoleStatus;
	}
	public void setSearchRoleStatus(String searchRoleStatus) {
		this.searchRoleStatus = searchRoleStatus;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public String getName_en_US() {
		return name_en_US;
	}
	public void setName_en_US(String name_en_US) {
		this.name_en_US = name_en_US;
	}
	public String getDescription_en_US() {
		return description_en_US;
	}
	public void setDescription_en_US(String description_en_US) {
		this.description_en_US = description_en_US;
	}
}

