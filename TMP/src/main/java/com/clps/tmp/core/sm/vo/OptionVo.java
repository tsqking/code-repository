package com.clps.tmp.core.sm.vo;


/**
 *  选项框
  * @ClassName: OptionVo
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 下午3:36:39
 */
public class OptionVo{
	//id自增长
	private String id;
	//名字
	private String name;
	//英文名字
	private String name_en_US;
	//级别,1:一级选项组,2:选项/二级选项组,3:选项/三级选项组……
	private String level;
	//类型,0:选项组,1:选项
	private String type;
	//选项值,如果类型为type=0这里为空
	private String value;
	//父选项,如果level=1,这里为0
	private String parent_id;
	//父选项名称
	private String parent_name;
	//排序
	private String order;
	//生效标识(例：T-生效，F-不生效)
	private String enable;
	//创建时间(20150901 115959000)
	private String create_time;
	//创建人
	private String create_person;
	//修改时间(20150901 115959000)
	private String update_time;
	//修改人
	private String update_person;
	//*******************************
	//存放上次修改时间(20150901 115959000)
	private String last_update_time;
	//*******************************
	//总条数
	private String allcount;
	//下拉框名称
	private String searchOptionName;
	//下拉框组
	private String searchOptionGroups;
	//下拉框状态
	private String searchOptionStatus;
	//下拉框类型
	private String searchOptionType;
	
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public String getAllcount() {
		return allcount;
	}
	public void setAllcount(String allcount) {
		this.allcount = allcount;
	}
	public String getSearchOptionName() {
		return searchOptionName;
	}
	public void setSearchOptionName(String searchOptionName) {
		this.searchOptionName = searchOptionName;
	}
	public String getSearchOptionStatus() {
		return searchOptionStatus;
	}
	public void setSearchOptionStatus(String searchOptionStatus) {
		this.searchOptionStatus = searchOptionStatus;
	}
	public String getSearchOptionType() {
		return searchOptionType;
	}
	public void setSearchOptionType(String searchOptionType) {
		this.searchOptionType = searchOptionType;
	}
	public String getSearchOptionGroups() {
		return searchOptionGroups;
	}
	public void setSearchOptionGroups(String searchOptionGroups) {
		this.searchOptionGroups = searchOptionGroups;
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
	
		
}
