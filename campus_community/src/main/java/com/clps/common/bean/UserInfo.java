/**
 * Project Name:campus_community
 * File Name:UserInfo.java
 * Package Name:com.clps.common.bean
 * Date:2017年3月22日上午10:44:14
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.bean;

import java.io.Serializable;

/**
 * ClassName:UserInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月22日 上午10:44:14 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class UserInfo implements Serializable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	private String u_id;
	private String u_nickname;
	private String u_password;
	private String u_name;
	private String u_gender;
	private int u_age;
	private String u_email;
	private String u_password_ques;
	private String u_password_ans;
	private String u_address;
	private String u_phone;
	private String u_point;
	private String u_motto;
	private String u_face;
	private String u_register_date;
	private String u_type;
	private String u_is_enable;

	public UserInfo() {
		super();
	}

	public UserInfo(String u_id, String u_nickname, String u_password, String u_name, String u_gender, int u_age,
			String u_email, String u_password_ques, String u_password_ans, String u_address, String u_phone,
			String u_point, String u_motto, String u_face, String u_register_date, String u_type, String u_is_enable) {
		super();
		this.u_id = u_id;
		this.u_nickname = u_nickname;
		this.u_password = u_password;
		this.u_name = u_name;
		this.u_gender = u_gender;
		this.u_age = u_age;
		this.u_email = u_email;
		this.u_password_ques = u_password_ques;
		this.u_password_ans = u_password_ans;
		this.u_address = u_address;
		this.u_phone = u_phone;
		this.u_point = u_point;
		this.u_motto = u_motto;
		this.u_face = u_face;
		this.u_register_date = u_register_date;
		this.u_type = u_type;
		this.u_is_enable = u_is_enable;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_nickname() {
		return u_nickname;
	}

	public void setU_nickname(String u_nickname) {
		this.u_nickname = u_nickname;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_gender() {
		return u_gender;
	}

	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public int getU_age() {
		return u_age;
	}

	public void setU_age(int u_age) {
		this.u_age = u_age;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_password_ques() {
		return u_password_ques;
	}

	public void setU_password_ques(String u_password_ques) {
		this.u_password_ques = u_password_ques;
	}

	public String getU_password_ans() {
		return u_password_ans;
	}

	public void setU_password_ans(String u_password_ans) {
		this.u_password_ans = u_password_ans;
	}

	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_point() {
		return u_point;
	}

	public void setU_point(String u_point) {
		this.u_point = u_point;
	}

	public String getU_motto() {
		return u_motto;
	}

	public void setU_motto(String u_motto) {
		this.u_motto = u_motto;
	}

	public String getU_face() {
		return u_face;
	}

	public void setU_face(String u_face) {
		this.u_face = u_face;
	}

	public String getU_register_date() {
		return u_register_date;
	}

	public void setU_register_date(String u_register_date) {
		this.u_register_date = u_register_date;
	}

	public String getU_type() {
		return u_type;
	}

	public void setU_type(String u_type) {
		this.u_type = u_type;
	}

	public String getU_is_enable() {
		return u_is_enable;
	}

	public void setU_is_enable(String u_is_enable) {
		this.u_is_enable = u_is_enable;
	}

	@Override
	public String toString() {
		return "UserInfo [u_id=" + u_id + ", u_nickname=" + u_nickname + ", u_password=" + u_password + ", u_name="
				+ u_name + ", u_gender=" + u_gender + ", u_age=" + u_age + ", u_email=" + u_email + ", u_password_ques="
				+ u_password_ques + ", u_password_ans=" + u_password_ans + ", u_address=" + u_address + ", u_phone="
				+ u_phone + ", u_point=" + u_point + ", u_motto=" + u_motto + ", u_face=" + u_face
				+ ", u_register_date=" + u_register_date + ", u_type=" + u_type + ", u_is_enable=" + u_is_enable + "]";
	}

}
