/**
 * Project Name:campus_community
 * File Name:News.java
 * Package Name:com.clps.common.bean
 * Date:2017年4月1日下午3:24:02
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.bean;

import java.io.Serializable;

/**
 * ClassName:News <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月1日 下午3:24:02 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class News implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	private int n_id;
	private String n_title;
	private String n_content;
	private String n_send_nickname;
	private String n_send_date;
	private String n_update_nickname;
	private String n_update_date;
	private int n_is_enable;

	public News() {
		super();
	}

	public News(int n_id, String n_title, String n_content, String n_send_nickname, String n_send_date,
			String n_update_nickname, String n_update_date, int n_is_enable) {
		super();
		this.n_id = n_id;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_send_nickname = n_send_nickname;
		this.n_send_date = n_send_date;
		this.n_update_nickname = n_update_nickname;
		this.n_update_date = n_update_date;
		this.n_is_enable = n_is_enable;
	}

	public int getN_id() {
		return n_id;
	}

	public String getN_title() {
		return n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public String getN_send_nickname() {
		return n_send_nickname;
	}

	public String getN_send_date() {
		return n_send_date;
	}

	public String getN_update_nickname() {
		return n_update_nickname;
	}

	public String getN_update_date() {
		return n_update_date;
	}

	public int getN_is_enable() {
		return n_is_enable;
	}

	public void setN_id(int n_id) {
		this.n_id = n_id;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public void setN_send_nickname(String n_send_nickname) {
		this.n_send_nickname = n_send_nickname;
	}

	public void setN_send_date(String n_send_date) {
		this.n_send_date = n_send_date;
	}

	public void setN_update_nickname(String n_update_nickname) {
		this.n_update_nickname = n_update_nickname;
	}

	public void setN_update_date(String n_update_date) {
		this.n_update_date = n_update_date;
	}

	public void setN_is_enable(int n_is_enable) {
		this.n_is_enable = n_is_enable;
	}

	@Override
	public String toString() {
		return "News [n_id=" + n_id + ", n_title=" + n_title + ", n_content=" + n_content + ", n_send_nickname="
				+ n_send_nickname + ", n_send_date=" + n_send_date + ", n_update_nickname=" + n_update_nickname
				+ ", n_update_date=" + n_update_date + ", n_is_enable=" + n_is_enable + "]";
	}

}
