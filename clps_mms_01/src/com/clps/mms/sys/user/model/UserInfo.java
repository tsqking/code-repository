/**
 * Project Name:clps_mms_copyright_201610
 * File Name:User_Info.java
 * Package Name:com.clps.mms.sys.menu.model
 * Date:2016年10月18日下午1:18:00
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:User_Info Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年10月18日 下午1:18:00
 * 
 * @author tony.tan
 * 
 */
public class UserInfo implements Serializable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = -4260030737741506579L;
	private Long id;
	private String name;
	private String nickname;
	private String password;
	private String gender;
	private Long age;
	private Date birthday;
	private String mobNum;
	private String qqNum;
	private String email;
	private String livePlace;
	private String workPlace;
	private Integer department;
	private Integer position;
	private String certType;
	private String certId;
	private String createdate;
	private String createname;
	private String updatename;
	private String updatedate;
	private Integer isEnable;
	private String statu;
	private String default1;
	private String default2;
    
	public UserInfo() {
		
		super();
		// TODO Auto-generated constructor stub
	}
    
	public UserInfo(Long id, String name, String nickname, String password,String gender, String mobNum, String email,Integer department,Integer position,String createname,String createdate,String updatename,String updatedate,Integer isEnable) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.mobNum = mobNum;
		this.email = email;
		this.department=department;
		this.position=position;
		this.createname=createname;
		this.createdate=createdate;
		this.updatename=updatename;
		this.updatedate=updatedate;
		this.isEnable=isEnable;
	}

	public UserInfo(String name, String nickname, String password, String gender, Long age, Date birthday,
			String email) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.birthday = birthday;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobNum() {
		return mobNum;
	}

	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLivePlace() {
		return livePlace;
	}

	public void setLivePlace(String livePlace) {
		this.livePlace = livePlace;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

    
	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public String getUpdatename() {
		return updatename;
	}

	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getDefault1() {
		return default1;
	}

	public void setDefault1(String default1) {
		this.default1 = default1;
	}

	public String getDefault2() {
		return default2;
	}

	public void setDefault2(String default2) {
		this.default2 = default2;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", nickname=" + nickname + ", gender=" + gender + ", mobNum="
				+ mobNum + ", email=" + email + ", department=" + department + ", position=" + position
				+ ", createdate=" + createdate + ", createname=" + createname + ", updatename=" + updatename
				+ ", updatedate=" + updatedate + ", isEnable=" + isEnable + "]";
	}




}
