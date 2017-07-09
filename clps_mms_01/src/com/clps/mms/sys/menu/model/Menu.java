/**
 * Project Name:clps_mms_01
 * File Name:Menu.java
 * Package Name:com.clps.mms.sys.menu.model
 * Date:2017年1月12日下午10:55:29
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.menu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName:Menu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年1月12日 下午10:55:29 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class Menu implements Serializable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	private String mnu_id; // 菜单ID
	private String mnu_name;// 菜单名
	private String mnu_url;// 菜单地址
	private String mnu_create_user;// 创建人
	private String mnu_create_time;// 创建时间
	private String mnu_update_user;// 更新人
	private String mnu_update_time;// 更新时间
	private String mnu_parent_id;// 父菜单ID
	private String mnu_child_id;// 子菜单ID
	private String mnu_root_id;// 跟菜单ID
	private String mnu_logo_url;// logo地址
	private String mnu_desc;// 菜单描述
	private int mnu_is_enable;// 菜单是否可用
	public List<Menu> children;

	public Menu() {

	}

	/**
	 * Creates a new instance of Menu.
	 *
	 * @param mnu_id
	 * @param mnu_name
	 * @param mnu_url
	 * @param mnu_create_user
	 * @param mnu_create_time
	 * @param mnu_update_user
	 * @param mnu_update_time
	 * @param mnu_parent_id
	 * @param mnu_child_id
	 * @param mnu_root_id
	 * @param mnu_logo_url
	 * @param mnu_desc
	 * @param mnu_is_enable
	 */

	public Menu(String mnu_id, String mnu_name, String mnu_url, String mnu_create_user, String mnu_create_time,
			String mnu_update_user, String mnu_update_time, String mnu_parent_id, String mnu_child_id,
			String mnu_root_id, String mnu_logo_url, String mnu_desc, int mnu_is_enable) {
		super();
		this.mnu_id = mnu_id;
		this.mnu_name = mnu_name;
		this.mnu_url = mnu_url;
		this.mnu_create_user = mnu_create_user;
		this.mnu_create_time = mnu_create_time;
		this.mnu_update_user = mnu_update_user;
		this.mnu_update_time = mnu_update_time;
		this.mnu_parent_id = mnu_parent_id;
		this.mnu_child_id = mnu_child_id;
		this.mnu_root_id = mnu_root_id;
		this.mnu_logo_url = mnu_logo_url;
		this.mnu_desc = mnu_desc;
		this.mnu_is_enable = mnu_is_enable;
	}

	/**
	 * Creates a new instance of Menu.
	 *
	 * @param mnu_name
	 * @param mnu_url
	 * @param mnu_create_user
	 * @param mnu_create_time
	 * @param mnu_update_user
	 * @param mnu_update_time
	 * @param mnu_parent_id
	 * @param mnu_child_id
	 * @param mnu_root_id
	 * @param mnu_logo_url
	 * @param mnu_desc
	 * @param mnu_is_enable
	 */

	public Menu(String mnu_name, String mnu_url, String mnu_create_user, String mnu_create_time, String mnu_update_user,
			String mnu_update_time, String mnu_parent_id, String mnu_child_id, String mnu_root_id, String mnu_logo_url,
			String mnu_desc, int mnu_is_enable) {
		super();
		this.mnu_name = mnu_name;
		this.mnu_url = mnu_url;
		this.mnu_create_user = mnu_create_user;
		this.mnu_create_time = mnu_create_time;
		this.mnu_update_user = mnu_update_user;
		this.mnu_update_time = mnu_update_time;
		this.mnu_parent_id = mnu_parent_id;
		this.mnu_child_id = mnu_child_id;
		this.mnu_root_id = mnu_root_id;
		this.mnu_logo_url = mnu_logo_url;
		this.mnu_desc = mnu_desc;
		this.mnu_is_enable = mnu_is_enable;
	}

	/**
	 * mnu_id.
	 *
	 * @return the mnu_id
	 * @since JDK 1.8
	 */
	public String getMnu_id() {
		return mnu_id;
	}

	/**
	 * mnu_id.
	 *
	 * @param mnu_id
	 *            the mnu_id to set
	 * @since JDK 1.8
	 */
	public void setMnu_id(String mnu_id) {
		this.mnu_id = mnu_id;
	}

	/**
	 * mnu_name.
	 *
	 * @return the mnu_name
	 * @since JDK 1.8
	 */
	public String getMnu_name() {
		return mnu_name;
	}

	/**
	 * mnu_name.
	 *
	 * @param mnu_name
	 *            the mnu_name to set
	 * @since JDK 1.8
	 */
	public void setMnu_name(String mnu_name) {
		this.mnu_name = mnu_name;
	}

	/**
	 * mnu_url.
	 *
	 * @return the mnu_url
	 * @since JDK 1.8
	 */
	public String getMnu_url() {
		return mnu_url;
	}

	/**
	 * mnu_url.
	 *
	 * @param mnu_url
	 *            the mnu_url to set
	 * @since JDK 1.8
	 */
	public void setMnu_url(String mnu_url) {
		this.mnu_url = mnu_url;
	}

	/**
	 * mnu_create_user.
	 *
	 * @return the mnu_create_user
	 * @since JDK 1.8
	 */
	public String getMnu_create_user() {
		return mnu_create_user;
	}

	/**
	 * mnu_create_user.
	 *
	 * @param mnu_create_user
	 *            the mnu_create_user to set
	 * @since JDK 1.8
	 */
	public void setMnu_create_user(String mnu_create_user) {
		this.mnu_create_user = mnu_create_user;
	}

	/**
	 * mnu_create_time.
	 *
	 * @return the mnu_create_time
	 * @since JDK 1.8
	 */
	public String getMnu_create_time() {
		return mnu_create_time;
	}

	/**
	 * mnu_create_time.
	 *
	 * @param mnu_create_time
	 *            the mnu_create_time to set
	 * @since JDK 1.8
	 */
	public void setMnu_create_time(String mnu_create_time) {
		this.mnu_create_time = mnu_create_time;
	}

	/**
	 * mnu_update_user.
	 *
	 * @return the mnu_update_user
	 * @since JDK 1.8
	 */
	public String getMnu_update_user() {
		return mnu_update_user;
	}

	/**
	 * mnu_update_user.
	 *
	 * @param mnu_update_user
	 *            the mnu_update_user to set
	 * @since JDK 1.8
	 */
	public void setMnu_update_user(String mnu_update_user) {
		this.mnu_update_user = mnu_update_user;
	}

	/**
	 * mnu_update_time.
	 *
	 * @return the mnu_update_time
	 * @since JDK 1.8
	 */
	public String getMnu_update_time() {
		return mnu_update_time;
	}

	/**
	 * mnu_update_time.
	 *
	 * @param mnu_update_time
	 *            the mnu_update_time to set
	 * @since JDK 1.8
	 */
	public void setMnu_update_time(String mnu_update_time) {
		this.mnu_update_time = mnu_update_time;
	}

	/**
	 * mnu_parent_id.
	 *
	 * @return the mnu_parent_id
	 * @since JDK 1.8
	 */
	public String getMnu_parent_id() {
		return mnu_parent_id;
	}

	/**
	 * mnu_parent_id.
	 *
	 * @param mnu_parent_id
	 *            the mnu_parent_id to set
	 * @since JDK 1.8
	 */
	public void setMnu_parent_id(String mnu_parent_id) {
		this.mnu_parent_id = mnu_parent_id;
	}

	/**
	 * mnu_child_id.
	 *
	 * @return the mnu_child_id
	 * @since JDK 1.8
	 */
	public String getMnu_child_id() {
		return mnu_child_id;
	}

	/**
	 * mnu_child_id.
	 *
	 * @param mnu_child_id
	 *            the mnu_child_id to set
	 * @since JDK 1.8
	 */
	public void setMnu_child_id(String mnu_child_id) {
		this.mnu_child_id = mnu_child_id;
	}

	/**
	 * mnu_root_id.
	 *
	 * @return the mnu_root_id
	 * @since JDK 1.8
	 */
	public String getMnu_root_id() {
		return mnu_root_id;
	}

	/**
	 * mnu_root_id.
	 *
	 * @param mnu_root_id
	 *            the mnu_root_id to set
	 * @since JDK 1.8
	 */
	public void setMnu_root_id(String mnu_root_id) {
		this.mnu_root_id = mnu_root_id;
	}

	/**
	 * mnu_logo_url.
	 *
	 * @return the mnu_logo_url
	 * @since JDK 1.8
	 */
	public String getMnu_logo_url() {
		return mnu_logo_url;
	}

	/**
	 * mnu_logo_url.
	 *
	 * @param mnu_logo_url
	 *            the mnu_logo_url to set
	 * @since JDK 1.8
	 */
	public void setMnu_logo_url(String mnu_logo_url) {
		this.mnu_logo_url = mnu_logo_url;
	}

	/**
	 * mnu_desc.
	 *
	 * @return the mnu_desc
	 * @since JDK 1.8
	 */
	public String getMnu_desc() {
		return mnu_desc;
	}

	/**
	 * mnu_desc.
	 *
	 * @param mnu_desc
	 *            the mnu_desc to set
	 * @since JDK 1.8
	 */
	public void setMnu_desc(String mnu_desc) {
		this.mnu_desc = mnu_desc;
	}

	/**
	 * mnu_is_enable.
	 *
	 * @return the mnu_is_enable
	 * @since JDK 1.8
	 */
	public int getMnu_is_enable() {
		return mnu_is_enable;
	}

	/**
	 * mnu_is_enable.
	 *
	 * @param mnu_is_enable
	 *            the mnu_is_enable to set
	 * @since JDK 1.8
	 */
	public void setMnu_is_enable(int mnu_is_enable) {
		this.mnu_is_enable = mnu_is_enable;
	}

	/**
	 * children.
	 *
	 * @return the children
	 * @since JDK 1.8
	 */
	public List<Menu> getChildren() {
		return children;
	}

	/**
	 * children.
	 *
	 * @param children
	 *            the children to set
	 * @since JDK 1.8
	 */
	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Menu [mnu_id=" + mnu_id + ", mnu_name=" + mnu_name + ", mnu_url=" + mnu_url + ", mnu_create_user="
				+ mnu_create_user + ", mnu_create_time=" + mnu_create_time + ", mnu_update_user=" + mnu_update_user
				+ ", mnu_update_time=" + mnu_update_time + ", mnu_parent_id=" + mnu_parent_id + ", mnu_child_id="
				+ mnu_child_id + ", mnu_root_id=" + mnu_root_id + ", mnu_logo_url=" + mnu_logo_url + ", mnu_desc="
				+ mnu_desc + ", mnu_is_enable=" + mnu_is_enable + ", children=" + children + "]";
	}
}
