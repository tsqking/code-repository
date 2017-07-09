/**
 * Project Name:clps_mms_copyright_201610
 * File Name:LogModel.java
 * Package Name:com.clps.mms.log.model
 * Date:2016年10月19日下午1:43:55
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.log.sys.model;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:LogModel Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年10月19日 下午1:43:55
 * 
 * @author tony.tan
 * 
 */
public class LogModel implements Serializable{
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private Integer type;
	private String content;
	private Date updateTime;
	private String updateName;
	private String remark;
	private Integer status;

	public LogModel() {

		super();
		// TODO Auto-generated constructor stub
	}

	public LogModel(Integer id, String userName, Integer type, String content, Date updateTime,String upateName, String remark,
			Integer status) {
		super();
		this.id = id;
		this.userName = userName;
		this.type = type;
		this.content = content;
		this.updateTime = updateTime;
		this.updateName=upateName;
		this.remark = remark;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userName;
	}

	public void setUserId(String userName) {
		this.userName = userName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LogModel [id=" + id + ", userId=" + userName + ", type=" + type + ", content=" + content + ", updateTime="
				+ updateTime + ", remark=" + remark + ", status=" + status + "]";
	}
    
}
