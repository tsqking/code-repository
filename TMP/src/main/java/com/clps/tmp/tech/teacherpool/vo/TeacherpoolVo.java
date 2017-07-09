package com.clps.tmp.tech.teacherpool.vo;

public class TeacherpoolVo {
	//确认时间
	private String confirmTime;
	//地址
	private String address;
	//证件号码
	private String cardNbr;
	//证件类型
	private String cardType;
	//
	private String cardType_name;
	//课程名称
	private String courseName;
	//课程名称Id
	private String courseId;
	//班级名称
	private String className;
	//开始时间
	private String start_time;
	//结束时间
	private String end_time;
	//登录密码
	private String password;
	//密码
	private String pwd1;
	//密码
	private String pwd2;
	//教师类型
	private String teacType;
	private String teacType_name;
	//	教师号
	private String no;
	//	登录用户
	private String username;
	//	教师角色
	private String role;
	private String role_name;
	private String knowledge;
	private String konwledge_id;
	//	教员编号
	private String nbr;
	//	教员姓名
	private String name;
	//	一级技能id
	private String first_skill_id;
	//	一级技能
	private String first_skill;
	//	二级技能id
	private String second_skill_id;
	//	二级技能
	private String second_skill;
	//	三级技能id
	private String third_skill_id;
	//	三级技能
	private String third_skill;
	//	教师年龄
	private String age;
	//	教师邮件
	private String email;
	//	教师手机号
	private String mobile;
	//	专业方向
	private String direction;
	private String direction_name;
	//	操作
	private String action;
	// 创建时间(20150901 115959000)
	private String create_time;
	// 创建人
	private String create_person;
	// 更新时间(20150901 115959000)
	private String update_time;
	// 更新人
	private String update_person;
	//
	//private String className;
	/*****************************
	 *******************************/
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
		//联系电话
		private String phone;
		//绩点
		private String gpa;
		//人员情况简介
		private String description;
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
		//性别
		private String gender;
		private String gender_name;

		//简历地址
		private String resume;
		//生效标识(例：T-生效，F-不生效)
		private String enable;
		private String enable_name;

		private String en_name;
		//数量
		private String number;
		
		
		public String getConfirmTime() {
			return confirmTime;
		}
		public void setConfirmTime(String confirmTime) {
			this.confirmTime = confirmTime;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCardType_name() {
			return cardType_name;
		}
		public void setCardType_name(String cardType_name) {
			this.cardType_name = cardType_name;
		}
		public String getCardNbr() {
			return cardNbr;
		}
		public void setCardNbr(String cardNbr) {
			this.cardNbr = cardNbr;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getCourseId() {
			return courseId;
		}
		public void setCourseId(String courseId) {
			this.courseId = courseId;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getStart_time() {
			return start_time;
		}
		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}
		public String getEnd_time() {
			return end_time;
		}
		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}
		public String getDirection_name() {
			return direction_name;
		}
		public void setDirection_name(String direction_name) {
			this.direction_name = direction_name;
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
		/**
		 * @return the number
		 */
		public String getNumber() {
			return number;
		}
		/**
		 * @param number the number to set
		 */
		public void setNumber(String number) {
			this.number = number;
		}
		/**
		 * @return the teacType_name
		 */
		public String getTeacType_name() {
			return teacType_name;
		}
		/**
		 * @param teacType_name the teacType_name to set
		 */
		public void setTeacType_name(String teacType_name) {
			this.teacType_name = teacType_name;
		}
		/**
		 * @return the teacType
		 */
		public String getTeacType() {
			return teacType;
		}
		/**
		 * @param teacType the teacType to set
		 */
		public void setTeacType(String teacType) {
			this.teacType = teacType;
		}
		/**
		 * @return the konwledge_id
		 */
		public String getKonwledge_id() {
			return konwledge_id;
		}
		/**
		 * @param konwledge_id the konwledge_id to set
		 */
		public void setKonwledge_id(String konwledge_id) {
			this.konwledge_id = konwledge_id;
		}
		/**
		 * @return the knowledge
		 */
		public String getKnowledge() {
			return knowledge;
		}
		/**
		 * @param knowledge the knowledge to set
		 */
		public void setKnowledge(String knowledge) {
			this.knowledge = knowledge;
		}
	/**
		 * @return the role_name
		 */
		public String getRole_name() {
			return role_name;
		}
		/**
		 * @param role_name the role_name to set
		 */
		public void setRole_name(String role_name) {
			this.role_name = role_name;
		}
		/**
		 * @return the gender_name
		 */
		public String getGender_name() {
			return gender_name;
		}
		/**
		 * @param gender_name the gender_name to set
		 */
		public void setGender_name(String gender_name) {
			this.gender_name = gender_name;
		}
		/**
		 * @return the enable_name
		 */
		public String getEnable_name() {
			return enable_name;
		}
		/**
		 * @param enable_name the enable_name to set
		 */
		public void setEnable_name(String enable_name) {
			this.enable_name = enable_name;
		}
	/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return the pwd1
		 */
		public String getPwd1() {
			return pwd1;
		}
		/**
		 * @param pwd1 the pwd1 to set
		 */
		public void setPwd1(String pwd1) {
			this.pwd1 = pwd1;
		}
		/**
		 * @return the pwd2
		 */
		public String getPwd2() {
			return pwd2;
		}
		/**
		 * @param pwd2 the pwd2 to set
		 */
		public void setPwd2(String pwd2) {
			this.pwd2 = pwd2;
		}
	/**
		 * @return the phone
		 */
		public String getPhone() {
			return phone;
		}
		/**
		 * @param phone the phone to set
		 */
		public void setPhone(String phone) {
			this.phone = phone;
		}
	/**
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}
		/**
		 * @param gender the gender to set
		 */
		public void setGender(String gender) {
			this.gender = gender;
		}
	/**
		 * @return the no
		 */
		public String getNo() {
			return no;
		}
		/**
		 * @param no the no to set
		 */
		public void setNo(String no) {
			this.no = no;
		}
	/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
	/**
		 * @return the role
		 */
		public String getRole() {
			return role;
		}
		/**
		 * @param role the role to set
		 */
		public void setRole(String role) {
			this.role = role;
		}
	/**
		 * @return the birthday
		 */
		public String getBirthday() {
			return birthday;
		}
		/**
		 * @param birthday the birthday to set
		 */
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		/**
		 * @return the education_background
		 */
		public String getEducation_background() {
			return education_background;
		}
		/**
		 * @param education_background the education_background to set
		 */
		public void setEducation_background(String education_background) {
			this.education_background = education_background;
		}
		/**
		 * @return the degree
		 */
		public String getDegree() {
			return degree;
		}
		/**
		 * @param degree the degree to set
		 */
		public void setDegree(String degree) {
			this.degree = degree;
		}
		/**
		 * @return the university
		 */
		public String getUniversity() {
			return university;
		}
		/**
		 * @param university the university to set
		 */
		public void setUniversity(String university) {
			this.university = university;
		}
		/**
		 * @return the college
		 */
		public String getCollege() {
			return college;
		}
		/**
		 * @param college the college to set
		 */
		public void setCollege(String college) {
			this.college = college;
		}
		/**
		 * @return the major
		 */
		public String getMajor() {
			return major;
		}
		/**
		 * @param major the major to set
		 */
		public void setMajor(String major) {
			this.major = major;
		}
		/**
		 * @return the cet4
		 */
		public String getCet4() {
			return cet4;
		}
		/**
		 * @param cet4 the cet4 to set
		 */
		public void setCet4(String cet4) {
			this.cet4 = cet4;
		}
		/**
		 * @return the cet6
		 */
		public String getCet6() {
			return cet6;
		}
		/**
		 * @param cet6 the cet6 to set
		 */
		public void setCet6(String cet6) {
			this.cet6 = cet6;
		}
		/**
		 * @return the gpa
		 */
		public String getGpa() {
			return gpa;
		}
		/**
		 * @param gpa the gpa to set
		 */
		public void setGpa(String gpa) {
			this.gpa = gpa;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the contact_address
		 */
		public String getContact_address() {
			return contact_address;
		}
		/**
		 * @param contact_address the contact_address to set
		 */
		public void setContact_address(String contact_address) {
			this.contact_address = contact_address;
		}
		/**
		 * @return the contact_postcode
		 */
		public String getContact_postcode() {
			return contact_postcode;
		}
		/**
		 * @param contact_postcode the contact_postcode to set
		 */
		public void setContact_postcode(String contact_postcode) {
			this.contact_postcode = contact_postcode;
		}
		/**
		 * @return the home_address
		 */
		public String getHome_address() {
			return home_address;
		}
		/**
		 * @param home_address the home_address to set
		 */
		public void setHome_address(String home_address) {
			this.home_address = home_address;
		}
		/**
		 * @return the home_postcode
		 */
		public String getHome_postcode() {
			return home_postcode;
		}
		/**
		 * @param home_postcode the home_postcode to set
		 */
		public void setHome_postcode(String home_postcode) {
			this.home_postcode = home_postcode;
		}
		/**
		 * @return the photo
		 */
		public String getPhoto() {
			return photo;
		}
		/**
		 * @param photo the photo to set
		 */
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		/**
		 * @return the resume
		 */
		public String getResume() {
			return resume;
		}
		/**
		 * @param resume the resume to set
		 */
		public void setResume(String resume) {
			this.resume = resume;
		}
		/**
		 * @return the enable
		 */
		public String getEnable() {
			return enable;
		}
		/**
		 * @param enable the enable to set
		 */
		public void setEnable(String enable) {
			this.enable = enable;
		}
		/**
		 * @return the en_name
		 */
		public String getEn_name() {
			return en_name;
		}
		/**
		 * @param en_name the en_name to set
		 */
		public void setEn_name(String en_name) {
			this.en_name = en_name;
		}
	/**
	 * @return the create_time
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the create_person
	 */
	public String getCreate_person() {
		return create_person;
	}
	/**
	 * @param create_person the create_person to set
	 */
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	/**
	 * @return the update_time
	 */
	public String getUpdate_time() {
		return update_time;
	}
	/**
	 * @param update_time the update_time to set
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	/**
	 * @return the update_person
	 */
	public String getUpdate_person() {
		return update_person;
	}
	/**
	 * @param update_person the update_person to set
	 */
	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}
	/**
	 * @return the nbr
	 */
	public String getNbr() {
		return nbr;
	}
	/**
	 * @param nbr the nbr to set
	 */
	public void setNbr(String nbr) {
		this.nbr = nbr;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the first_skill_id
	 */
	public String getFirst_skill_id() {
		return first_skill_id;
	}
	/**
	 * @param first_skill_id the first_skill_id to set
	 */
	public void setFirst_skill_id(String first_skill_id) {
		this.first_skill_id = first_skill_id;
	}
	/**
	 * @return the first_skill
	 */
	public String getFirst_skill() {
		return first_skill;
	}
	/**
	 * @param first_skill the first_skill to set
	 */
	public void setFirst_skill(String first_skill) {
		this.first_skill = first_skill;
	}
	/**
	 * @return the second_skill_id
	 */
	public String getSecond_skill_id() {
		return second_skill_id;
	}
	/**
	 * @param second_skill_id the second_skill_id to set
	 */
	public void setSecond_skill_id(String second_skill_id) {
		this.second_skill_id = second_skill_id;
	}
	/**
	 * @return the second_skill
	 */
	public String getSecond_skill() {
		return second_skill;
	}
	/**
	 * @param second_skill the second_skill to set
	 */
	public void setSecond_skill(String second_skill) {
		this.second_skill = second_skill;
	}
	/**
	 * @return the third_skill_id
	 */
	public String getThird_skill_id() {
		return third_skill_id;
	}
	/**
	 * @param third_skill_id the third_skill_id to set
	 */
	public void setThird_skill_id(String third_skill_id) {
		this.third_skill_id = third_skill_id;
	}
	/**
	 * @return the third_skill
	 */
	public String getThird_skill() {
		return third_skill;
	}
	/**
	 * @param third_skill the third_skill to set
	 */
	public void setThird_skill(String third_skill) {
		this.third_skill = third_skill;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TeacherpoolVo [password=" + password + ", pwd1=" + pwd1
				+ ", pwd2=" + pwd2 + ", teacType=" + teacType
				+ ", teacType_name=" + teacType_name + ", no=" + no
				+ ", username=" + username + ", role=" + role + ", role_name="
				+ role_name + ", knowledge=" + knowledge + ", konwledge_id="
				+ konwledge_id + ", nbr=" + nbr + ", name=" + name
				+ ", first_skill_id=" + first_skill_id + ", first_skill="
				+ first_skill + ", second_skill_id=" + second_skill_id
				+ ", second_skill=" + second_skill + ", third_skill_id="
				+ third_skill_id + ", third_skill=" + third_skill + ", age="
				+ age + ", email=" + email + ", mobile=" + mobile
				+ ", direction=" + direction + ", action=" + action
				+ ", create_time=" + create_time + ", create_person="
				+ create_person + ", update_time=" + update_time
				+ ", update_person=" + update_person + ", birthday=" + birthday
				+ ", education_background=" + education_background
				+ ", degree=" + degree + ", university=" + university
				+ ", college=" + college + ", major=" + major + ", cet4="
				+ cet4 + ", cet6=" + cet6 + ", phone=" + phone + ", gpa=" + gpa
				+ ", description=" + description + ", contact_address="
				+ contact_address + ", contact_postcode=" + contact_postcode
				+ ", home_address=" + home_address + ", home_postcode="
				+ home_postcode + ", photo=" + photo + ", gender=" + gender
				+ ", gender_name=" + gender_name + ", resume=" + resume
				+ ", enable=" + enable + ", enable_name=" + enable_name
				+ ", en_name=" + en_name + ", number=" + number + "]";
	}
	

	
	}
