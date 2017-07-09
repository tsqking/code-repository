package com.clps.tmp.campusRecruit.univ.coop.vo;
/**
 * @author Wellen
 *
 *         2016年3月14日
 */
public class CoopVo {
	// 自增Id
    private int id;
    // 合作时间
    private String coop_time;
    // 公司名称
    private String company_name;
    //学校名称
    private String univ_name;
    // 学校id
    private int univ_id;
    // 分校名称
    private String univ_branch_name;
    // 分校id
    private int univ_branch_id;
    // 学院名称
    private String college_name;
    // 学院id
    private int college_id;
    // 国家
    private String country;
    // 省code
    private String province;
    //省名称
    private String ProvinceName;
    // 市code
    private String city;
    //市名称
    private String cityName;
    // 合作状态
    private String status;
    private String status_name;
    // 合作状态 别名
    private String state;
    // 合作方式
    private String style;
    private String style_name;
    // 招生邮箱
    private String recruit_email;
    // 招生时间
    private String recruit_time;
    // 备注
    private String remark;
    // 创建时间
    private String create_time;
    // 创建人
    private String create_user;
    //更新时间
    private String update_time;
    //更新人
    private String update_user;
    //删除标志位
    private String del;
    
    //简短说明
    private String info;
    //联系人信息
    private String contacts;
    private String[] contactIds;
	
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getStyle_name() {
		return style_name;
	}
	public void setStyle_name(String style_name) {
		this.style_name = style_name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRecruit_email() {
		return recruit_email;
	}
	public void setRecruit_email(String recruit_email) {
		this.recruit_email = recruit_email;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoop_time() {
		return coop_time;
	}
	public void setCoop_time(String coop_time) {
		this.coop_time = coop_time;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getUniv_name() {
		return univ_name;
	}
	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}
	public int getUniv_id() {
		return univ_id;
	}
	public void setUniv_id(int univ_id) {
		this.univ_id = univ_id;
	}
	public String getUniv_branch_name() {
		return univ_branch_name;
	}
	public void setUniv_branch_name(String univ_branch_name) {
		this.univ_branch_name = univ_branch_name;
	}
	public int getUniv_branch_id() {
		return univ_branch_id;
	}
	public void setUniv_branch_id(int univ_branch_id) {
		this.univ_branch_id = univ_branch_id;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public int getCollege_id() {
		return college_id;
	}
	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getRecruit_time() {
		return recruit_time;
	}
	public void setRecruit_time(String recruit_time) {
		this.recruit_time = recruit_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	
	public String getProvinceName() {
		return ProvinceName;
	}
	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String[] getContactIds() {
		return contactIds;
	}
	public void setContactIds(String[] contactIds) {
		this.contactIds = contactIds;
	}
	@Override
	public String toString() {
		return "CoopVo [id=" + id + ", coop_time=" + coop_time + ", company_name=" + company_name + ", univ_name="
				+ univ_name + ", univ_id=" + univ_id + ", univ_branch_name=" + univ_branch_name + ", univ_branch_id="
				+ univ_branch_id + ", college_name=" + college_name + ", college_id=" + college_id + ", country="
				+ country + ", province=" + province + ", ProvinceName=" + ProvinceName + ", city=" + city
				+ ", cityName=" + cityName + ", status=" + status + ", state=" + state + ", style=" + style
				+ ", recruit_email=" + recruit_email + ", recruit_time=" + recruit_time + ", remark=" + remark
				+ ", create_time=" + create_time + ", create_user=" + create_user + ", update_time=" + update_time
				+ ", update_user=" + update_user + ", del=" + del + "]";
	}
	
}
