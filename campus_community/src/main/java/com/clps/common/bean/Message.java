/**
 * Project Name:campus_community
 * File Name:Message.java
 * Package Name:com.clps.common.bean
 * Date:2017年5月20日上午1:16:37
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.bean;

/**
 * ClassName:Message <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月20日 上午1:16:37 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class Message {
	private int m_id;
	private String m_title;
	private String m_content;
	private String m_send_nickname;
	private String m_rec_nickname;
	private String m_date;
	private String m_status;
	private String m_is_enable;

	public Message() {
		super();
	}

	public Message(int m_id, String m_title, String m_content, String m_send_nickname, String m_rec_nickname,
			String m_date, String m_status, String m_is_enable) {
		super();
		this.m_id = m_id;
		this.m_title = m_title;
		this.m_content = m_content;
		this.m_send_nickname = m_send_nickname;
		this.m_rec_nickname = m_rec_nickname;
		this.m_date = m_date;
		this.m_status = m_status;
		this.m_is_enable = m_is_enable;
	}

	public int getM_id() {
		return m_id;
	}

	public String getM_title() {
		return m_title;
	}

	public String getM_content() {
		return m_content;
	}

	public String getM_send_nickname() {
		return m_send_nickname;
	}

	public String getM_rec_nickname() {
		return m_rec_nickname;
	}

	public String getM_date() {
		return m_date;
	}

	public String getM_status() {
		return m_status;
	}

	public String getM_is_enable() {
		return m_is_enable;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	public void setM_content(String m_content) {
		this.m_content = m_content;
	}

	public void setM_send_nickname(String m_send_nickname) {
		this.m_send_nickname = m_send_nickname;
	}

	public void setM_rec_nickname(String m_rec_nickname) {
		this.m_rec_nickname = m_rec_nickname;
	}

	public void setM_date(String m_date) {
		this.m_date = m_date;
	}

	public void setM_status(String m_status) {
		this.m_status = m_status;
	}

	public void setM_is_enable(String m_is_enable) {
		this.m_is_enable = m_is_enable;
	}

	@Override
	public String toString() {
		return "Message [m_id=" + m_id + ", m_title=" + m_title + ", m_content=" + m_content + ", m_send_nickname="
				+ m_send_nickname + ", m_rec_nickname=" + m_rec_nickname + ", m_date=" + m_date + ", m_status="
				+ m_status + ", m_is_enable=" + m_is_enable + "]";
	}

}
