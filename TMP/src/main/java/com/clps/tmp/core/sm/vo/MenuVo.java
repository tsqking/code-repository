package com.clps.tmp.core.sm.vo;


/**
 * 菜单
  * @ClassName: MenuVo
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 下午3:36:39
 */
public class MenuVo{

	//id自增主键
	private String id;
	//菜单名字
	private String name;
	//菜单名字 英文
	private String name_en_US;
	//菜单描述
	private String description;
	//菜单描述  英文
	private String description_en_US;
	//菜单url
	private String url;
	//菜单级别,1:一级菜单,2:二级菜单……
	private String level;
	//父级菜单id
	private String parent_id;
	//父级菜单名
	private String parent_name;
	//排序
	private String order;
	//生效标识(例：T-生效，F-不生效)
	private String enable;
	//生效名
	private String enable_name;
	//创建时间(20150901 115959000)
	private String create_time;
	//创建人
	private String create_person;
	//更新时间
	private String update_time;
	//更新人
	private String update_person;
	//////////////
	//总条数
	private String allcount;
	//菜单名称
	private String searchMenuName;
	//菜单描述
	private String searchMenuDesc;
	//URL地址
	private String searchMenuUrl;
	//菜单等级
	private String searchMenuLevel;
	//父级菜单
	private String searchParentMenu;
	//菜单状态
	private String searchMenuStatus;
	
	
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getAllcount() {
		return allcount;
	}
	public void setAllcount(String allcount) {
		this.allcount = allcount;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public String getSearchMenuName() {
		return searchMenuName;
	}
	public void setSearchMenuName(String searchMenuName) {
		this.searchMenuName = searchMenuName;
	}
	public String getSearchMenuDesc() {
		return searchMenuDesc;
	}
	public void setSearchMenuDesc(String searchMenuDesc) {
		this.searchMenuDesc = searchMenuDesc;
	}
	public String getSearchMenuUrl() {
		return searchMenuUrl;
	}
	public void setSearchMenuUrl(String searchMenuUrl) {
		this.searchMenuUrl = searchMenuUrl;
	}
	public String getSearchMenuLevel() {
		return searchMenuLevel;
	}
	public void setSearchMenuLevel(String searchMenuLevel) {
		this.searchMenuLevel = searchMenuLevel;
	}
	public String getSearchParentMenu() {
		return searchParentMenu;
	}
	public void setSearchParentMenu(String searchParentMenu) {
		this.searchParentMenu = searchParentMenu;
	}
	public String getSearchMenuStatus() {
		return searchMenuStatus;
	}
	public void setSearchMenuStatus(String searchMenuStatus) {
		this.searchMenuStatus = searchMenuStatus;
	}
	public String getEnable_name() {
		return enable_name;
	}
	public void setEnable_name(String enable_name) {
		this.enable_name = enable_name;
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
