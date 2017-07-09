package com.clps.tmp.core.sm.vo;

/**
 * 用户信息类
  * @ClassName: UserVo
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 上午10:18:40
 */
public class UserVo {
	
	//自增主键
	private String id;
	//区分人员(例：0-超级管理员，1-管理员，2-讲师，3-学员)
	private String role;
	private String role_name;
	//登陆名
	private String username;
	//登陆密码(页面输入至多15位)
	private String password;
	//学号(例：2015081001)或者教员号(例：1001)
	private String no;
	//学员真实姓名
	private String name;
	//学员英文姓名
	private String en_name;
	//性别,0:女,1:男
	private String gender;
	private String gender_name;
	//手机号码(移动)
	private String mobile;
	//电话号码(座机)
	private String phone;
	//电子邮件
	private String email;
	//年龄
	private String age;
	//生日（20150101）
	private String birthday;
	//学历(博士后，博士研究生，硕士研究生，大学本科，大学专科，高中，初中，无)
	private String education_background;
	private String education_background_name;
	//学位(博士，硕士，学士，无)
	private String degree;
	private String degree_name;
	//最高学历毕业院校
	private String university;
	//学院
	private String college;
	//主修方向，即专业
	private String major;
	//四级成绩
	private String cet4;
	//六级成绩
	private String cet6;
	//绩点
	private String gpa;
	//人员情况简介
	private String description;
	//学习或主教方向(例如：Mainframe，Java)
	private String direction;
	//联系地址
	private String contact_address;
	//联系地址邮编
	private String contact_postcode;
	//家庭地址
	private String home_address;
	//家庭地址邮编
	private String home_postcode;
	//头像地址
	private String photo;
	//简历地址
	private String resume;
	//生效标识(例：T-生效，F-不生效)
	private String enable;
	private String enable_name;
	//创建时间(20150901 115959000)
	private String create_time;
	//创建人
	private String create_person;
	//修改时间(20150901 115959000)
	private String update_time;
	//修改人
	private String update_person;
	//总条数
	private String allcount;
	//---------------role
	//id 自增
	//private String id;
	//角色名称
	//private String name;
	//描述
	//private String description;
	//生效表示 'T'或'F'
	//private String enable;
	//private String enable_name;
	//创建时间 '20151019 115959000'
	//private String create_time;
	//创建人员
	//private String create_person;
	//更新时间 '20151019 115959000'
	//private String update_time;
	//更新人
	//private String update_person;
	//总条数
	//private String allcount;
	//has
	private String has;
	//密码
	private String pwd1;
	private String pwd2;
	//数量
	private String number;
	//证件类型
	private String cardtype;
	private String cardtype_name;
	//证件号码
	private String cardno;
	//权限开关
	private String flag;
	//考试证件号
	private String exam_num;
	
	public String getExam_num() {
		return exam_num;
	}
	public void setExam_num(String exam_num) {
		this.exam_num = exam_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEducation_background() {
		return education_background;
	}
	public void setEducation_background(String education_background) {
		this.education_background = education_background;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCet4() {
		return cet4;
	}
	public void setCet4(String cet4) {
		this.cet4 = cet4;
	}
	public String getCet6() {
		return cet6;
	}
	public void setCet6(String cet6) {
		this.cet6 = cet6;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getContact_address() {
		return contact_address;
	}
	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}
	public String getContact_postcode() {
		return contact_postcode;
	}
	public void setContact_postcode(String contact_postcode) {
		this.contact_postcode = contact_postcode;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getHome_postcode() {
		return home_postcode;
	}
	public void setHome_postcode(String home_postcode) {
		this.home_postcode = home_postcode;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
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
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getGender_name() {
		return gender_name;
	}
	public void setGender_name(String gender_name) {
		this.gender_name = gender_name;
	}
	public String getEnable_name() {
		return enable_name;
	}
	public void setEnable_name(String enable_name) {
		this.enable_name = enable_name;
	}
	public String getAllcount() {
		return allcount;
	}
	public void setAllcount(String allcount) {
		this.allcount = allcount;
	}
	public String getHas() {
		return has;
	}
	public void setHas(String has) {
		this.has = has;
	}
	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCardtype_name() {
		return cardtype_name;
	}
	public void setCardtype_name(String cardtype_name) {
		this.cardtype_name = cardtype_name;
	}
	public String getEducation_background_name() {
		return education_background_name;
	}
	public void setEducation_background_name(String education_background_name) {
		this.education_background_name = education_background_name;
	}
	public String getDegree_name() {
		return degree_name;
	}
	public void setDegree_name(String degree_name) {
		this.degree_name = degree_name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
