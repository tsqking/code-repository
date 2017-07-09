/**
 * Project Name:clps_mms_01
 * File Name:RoleInfo.java
 * Package Name:com.clps.mms.sys.role.model
 * Date:2017年1月12日下午10:08:41
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:RoleInfo.java
 * Package Name:com.clps.mms.sys.role.model
 * Date:2017年1月12日下午10:08:41
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.role.model;

import java.io.Serializable;

/**
 * ClassName:RoleInfo 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2017年1月12日 下午10:08:41 
 * @author   lonnie
 * @version     
 * @since    JDK 1.8	 
 */
public class RoleInfo implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//角色标号
	private String name;//角色名称
	private String create_name;//创建人名
	private String create_time;//创建时间
	private String update_name;//更新人名
	private String update_time;//更新时间
	private Integer isEnable;//有效数据
	private String description;//角色描述
	/**
	 * Creates a new instance of RoleInfo.
	 *
	 */
	
	public RoleInfo() {
		
		super();
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * Creates a new instance of RoleInfo.
	 *
	 * @param id
	 * @param name
	 * @param create_name
	 * @param create_time
	 * @param update_name
	 * @param update_time
	 * @param isEnable
	 * @param description
	 */
	
	public RoleInfo(Long id, String name, String create_name, String create_time, String update_name,
			String update_time, Integer isEnable, String description) {
		super();
		this.id = id;
		this.name = name;
		this.create_name = create_name;
		this.create_time = create_time;
		this.update_name = update_name;
		this.update_time = update_time;
		this.isEnable = isEnable;
		this.description = description;
	}
	/**
	 * id.
	 *
	 * @return  the id
	 * @since   JDK 1.8
	 */
	public Long getId() {
		return id;
	}
	/**
	 * id.
	 *
	 * @param   id    the id to set
	 * @since   JDK 1.8
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * name.
	 *
	 * @return  the name
	 * @since   JDK 1.8
	 */
	public String getName() {
		return name;
	}
	/**
	 * name.
	 *
	 * @param   name    the name to set
	 * @since   JDK 1.8
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * create_name.
	 *
	 * @return  the create_name
	 * @since   JDK 1.8
	 */
	public String getCreate_name() {
		return create_name;
	}
	/**
	 * create_name.
	 *
	 * @param   create_name    the create_name to set
	 * @since   JDK 1.8
	 */
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	/**
	 * create_time.
	 *
	 * @return  the create_time
	 * @since   JDK 1.8
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * create_time.
	 *
	 * @param   create_time    the create_time to set
	 * @since   JDK 1.8
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	/**
	 * update_name.
	 *
	 * @return  the update_name
	 * @since   JDK 1.8
	 */
	public String getUpdate_name() {
		return update_name;
	}
	/**
	 * update_name.
	 *
	 * @param   update_name    the update_name to set
	 * @since   JDK 1.8
	 */
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	/**
	 * update_time.
	 *
	 * @return  the update_time
	 * @since   JDK 1.8
	 */
	public String getUpdate_time() {
		return update_time;
	}
	/**
	 * update_time.
	 *
	 * @param   update_time    the update_time to set
	 * @since   JDK 1.8
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	/**
	 * isEnable.
	 *
	 * @return  the isEnable
	 * @since   JDK 1.8
	 */
	public Integer getIsEnable() {
		return isEnable;
	}
	/**
	 * isEnable.
	 *
	 * @param   isEnable    the isEnable to set
	 * @since   JDK 1.8
	 */
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * description.
	 *
	 * @return  the description
	 * @since   JDK 1.8
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * description.
	 *
	 * @param   description    the description to set
	 * @since   JDK 1.8
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * serialversionuid.
	 *
	 * @return  the serialversionuid
	 * @since   JDK 1.8
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{text:"+name +","+"id:"+create_name+" }";
	}
	

}

