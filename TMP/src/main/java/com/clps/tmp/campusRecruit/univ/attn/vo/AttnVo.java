package com.clps.tmp.campusRecruit.univ.attn.vo;

/**
 * @author Wellen
 *
 *         2016年3月14日
 */
public class AttnVo {
	// 自增Id
	private int id;
	// 联系人名
	private String name;
	// 性别
	private String gender;
	private String gender_name;
	// 联系人手机
	private String mobile;
	// 联系人座机
	private String phone;
	// 联系人邮箱
	private String email;
	// 联系人职务
	private String position;
	// 学校名称
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
	// 创建时间
	private String create_time;
	// 创建人
	private String create_user;
	// 更新时间
	private String update_time;
	// 更新人
	private String update_user;
	//删除标志位
    private String del;
    //合作联系人标志位
    private Boolean status;
    
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getGender_name() {
		return gender_name;
	}
	public void setGender_name(String gender_name) {
		this.gender_name = gender_name;
	}
	@Override
	public String toString() {
		return "AttnVo [id=" + id + ", name=" + name + ", gender=" + gender + ", mobile=" + mobile + ", phone=" + phone
				+ ", email=" + email + ", position=" + position + ", univ_name=" + univ_name + ", univ_id=" + univ_id
				+ ", univ_branch_name=" + univ_branch_name + ", univ_branch_id=" + univ_branch_id + ", college_name="
				+ college_name + ", college_id=" + college_id + ", create_time=" + create_time + ", create_user="
				+ create_user + ", update_time=" + update_time + ", update_user=" + update_user + ", del=" + del
				+ ", status=" + status + "]";
	}
}
