package com.clps.tmp.campusRecruit.univ.university.vo;

/**
 * @author Wellen
 *
 *         2016年3月14日
 */
public class UniversityVo {
	// 自增Id
    private int id;
    //父级id
    private int parent_id;
    //学校名称
    private String name;
    //分校名称
    private String univ_name;
    //学校类型
    private String type;
    //学校性质
    private String quality;
    // 国家
    private String country;
    // 省
    private String province;
    // 省名称
    private String provinceName;
    // 市
    private String city;
    // 市名称
    private String cityName;
    //详细地址
    private String detail_addr;
    // 网址
    private String website;
    // 电话
    private String phone;
    //邮箱
    private String email;
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
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getUniv_name() {
		return univ_name;
	}
	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
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
	public String getDetail_addr() {
		return detail_addr;
	}
	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@Override
	public String toString() {
		return "UniversityVo [id=" + id + ", parent_id=" + parent_id + ", name=" + name + ", univ_name=" + univ_name
				+ ", type=" + type + ", quality=" + quality + ", country=" + country + ", province=" + province
				+ ", provinceName=" + provinceName + ", city=" + city + ", cityName=" + cityName + ", detail_addr="
				+ detail_addr + ", website=" + website + ", phone=" + phone + ", email=" + email + ", create_time="
				+ create_time + ", create_user=" + create_user + ", update_time=" + update_time + ", update_user="
				+ update_user + ", del=" + del + "]";
	}
}
