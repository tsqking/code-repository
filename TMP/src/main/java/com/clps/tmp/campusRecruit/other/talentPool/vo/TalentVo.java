package com.clps.tmp.campusRecruit.other.talentPool.vo;

import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;

public class TalentVo {
	// id自增主键
	private int id;
	// 中文名
	private String name;
	// 姓名拼音
	private String spell_name;
	// 性别 （0-女，1-男）
	private String gender;
	// 年龄
	private int age;
	// 出生日期
	private String birthday;
	// 身份证号
	private String cardno;
	// 手机号
	private String mobile;
	// 电子邮件
	private String email;
	// 学历
	private String degree;
	// 学校ID
	private UniversityVo univ;
	// 学院ID
	private CollegeVo college;
	// 英语等级
	// ENG_LEVEL（0-无，1-PETS-1B,2-PETS-1，3-PETS-2，4-PETS-3(CET4)，5-PETS-4(CET6)，6-PETS-5）
	private String english_level;
	// 人才来源 TAL_SOURCE（1-校宣讲会，2-校招聘会，3-网络招聘，4-培训学校，5-其他途径）
	private String talent_source;
	// 招聘状态
	// RC_STATE（1-筛选简历不合格，2-筛选简历合格，3-未参加初试，4-参加初试，5-放弃初试，6-初试不合格，7-初试合格，8-未完善简历，9-已完善简历，10-放弃完善简历）
	private String recruit_state;
	// 招聘状态说明
	private String state_comment;
	// 简历附件存放地址
	private String resume_url;
	// 头像存放地址
	private String photo_url;
	// 创建时间（YYYYMMDD HHmmSS）
	private String create_time;
	// 创建人
	private String create_person;
	// 修改时间（YYYYMMDD HHmmSS）
	private String update_time;
	// 修改人
	private String update_person;
	// -------------
	private String last_update_time;
	private String last_update_person;
	// -------------

	// 用户名
	private String username;
	// 密码
	private String password;
	// 岗位
	private String position;
	// 岗位（无岗位选项时，手动填写）
	private String positionComment;
	// 学校名称备注
	private String univComment;
	// 学院名称备注
	private String collegeComment;
	// 毕业年月
	private String graduate_month;
	// 工作地点
	private String work_location;
	// 工作地点备注
	private String work_loc_comment;
	// 项目组名称
	private String proj_group;
	// 项目组名称备注
	private String proj_group_comment;
	// 户籍省份
	private String native_place_prov;
	// 户籍市区
	private String native_place_city;
	// 户籍备注
	private String native_place_comment;
	// 就业协议
	private String employmt_agreemt;
	// 专业
	private String major;
	//专业备注
	private String major_comment;
	// 人才信息备注
	private String remark;
	// 入职公司时间
	private String in_company_time;
	// 入职项目组时间
	private String in_proj_time;
	// 离职时间
	private String leave_time;
	
	public String getGraduate_month() {
		return graduate_month;
	}

	public void setGraduate_month(String graduate_month) {
		this.graduate_month = graduate_month;
	}

	public String getWork_location() {
		return work_location;
	}

	public void setWork_location(String work_location) {
		this.work_location = work_location;
	}

	public String getWork_loc_comment() {
		return work_loc_comment;
	}

	public void setWork_loc_comment(String work_loc_comment) {
		this.work_loc_comment = work_loc_comment;
	}

	public String getProj_group() {
		return proj_group;
	}

	public void setProj_group(String proj_group) {
		this.proj_group = proj_group;
	}

	public String getProj_group_comment() {
		return proj_group_comment;
	}

	public void setProj_group_comment(String proj_group_comment) {
		this.proj_group_comment = proj_group_comment;
	}

	public String getNative_place_prov() {
		return native_place_prov;
	}

	public void setNative_place_prov(String native_place_prov) {
		this.native_place_prov = native_place_prov;
	}

	public String getNative_place_city() {
		return native_place_city;
	}

	public void setNative_place_city(String native_place_city) {
		this.native_place_city = native_place_city;
	}

	public String getNative_place_comment() {
		return native_place_comment;
	}

	public void setNative_place_comment(String native_place_comment) {
		this.native_place_comment = native_place_comment;
	}

	
	public String getEmploymt_agreemt() {
		return employmt_agreemt;
	}

	public void setEmploymt_agreemt(String employmt_agreemt) {
		this.employmt_agreemt = employmt_agreemt;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIn_company_time() {
		return in_company_time;
	}

	public void setIn_company_time(String in_company_time) {
		this.in_company_time = in_company_time;
	}

	public String getIn_proj_time() {
		return in_proj_time;
	}

	public void setIn_proj_time(String in_proj_time) {
		this.in_proj_time = in_proj_time;
	}

	public String getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}

	public String getCollegeComment() {
		return collegeComment;
	}

	public void setCollegeComment(String collegeComment) {
		this.collegeComment = collegeComment;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public int getId() {
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

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell_name() {
		return spell_name;
	}

	public void setSpell_name(String spell_name) {
		this.spell_name = spell_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public UniversityVo getUniv() {
		return univ;
	}

	public void setUniv(UniversityVo univ) {
		this.univ = univ;
	}

	public CollegeVo getCollege() {
		return college;
	}

	public void setCollege(CollegeVo college) {
		this.college = college;
	}

	public String getEnglish_level() {
		return english_level;
	}

	public void setEnglish_level(String english_level) {
		this.english_level = english_level;
	}

	public String getTalent_source() {
		return talent_source;
	}

	public void setTalent_source(String talent_source) {
		this.talent_source = talent_source;
	}

	public String getRecruit_state() {
		return recruit_state;
	}

	public void setRecruit_state(String recruit_state) {
		this.recruit_state = recruit_state;
	}

	public String getState_comment() {
		return state_comment;
	}

	public void setState_comment(String state_comment) {
		this.state_comment = state_comment;
	}

	public String getResume_url() {
		return resume_url;
	}

	public void setResume_url(String resume_url) {
		this.resume_url = resume_url;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
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

	public String getPositionComment() {
		return positionComment;
	}

	public void setPositionComment(String positionComment) {
		this.positionComment = positionComment;
	}

	public String getUnivComment() {
		return univComment;
	}

	public void setUnivComment(String univComment) {
		this.univComment = univComment;
	}
	

	public String getMajor_comment() {
		return major_comment;
	}

	public void setMajor_comment(String major_comment) {
		this.major_comment = major_comment;
	}

	@Override
	public String toString() {
		return "TalentVo [id=" + id + ", name=" + name + ", spell_name="
				+ spell_name + ", gender=" + gender + ", age=" + age
				+ ", birthday=" + birthday + ", cardno=" + cardno + ", mobile="
				+ mobile + ", email=" + email + ", degree=" + degree
				+ ", univ=" + univ + ", college=" + college
				+ ", english_level=" + english_level + ", talent_source="
				+ talent_source + ", recruit_state=" + recruit_state
				+ ", state_comment=" + state_comment + ", resume_url="
				+ resume_url + ", photo_url=" + photo_url + ", create_time="
				+ create_time + ", create_person=" + create_person
				+ ", update_time=" + update_time + ", update_person="
				+ update_person + ", last_update_time=" + last_update_time
				+ ", last_update_person=" + last_update_person + ", username="
				+ username + ", password=" + password + ", position="
				+ position + ", positionComment=" + positionComment
				+ ", univComment=" + univComment + ", collegeComment="
				+ collegeComment + ", graduate_month=" + graduate_month
				+ ", work_location=" + work_location + ", work_loc_comment="
				+ work_loc_comment + ", proj_group=" + proj_group
				+ ", proj_group_comment=" + proj_group_comment
				+ ", native_place_prov=" + native_place_prov
				+ ", native_place_city=" + native_place_city
				+ ", native_place_comment=" + native_place_comment
				+ ", employmt_agreemt=" + employmt_agreemt + ", major=" + major
				+ ", major_comment=" + major_comment + ", remark=" + remark
				+ ", in_company_time=" + in_company_time + ", in_proj_time="
				+ in_proj_time + ", leave_time=" + leave_time + "]";
	}

}
