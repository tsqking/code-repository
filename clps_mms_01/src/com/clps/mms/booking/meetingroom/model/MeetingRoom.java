/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoom.java
 * Package Name:com.clps.mms.sys.meetingroom.model
 * Date:2017年1月11日下午3:04:53
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoom.java
 * Package Name:com.clps.mms.sys.meetingroom.model
 * Date:2017年1月11日下午3:04:53
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.model;

import java.io.Serializable;

/**
 * ClassName:MeetingRoom <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月11日 下午3:04:53 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingRoom <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月11日 下午3:04:53 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class MeetingRoom implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = -4738883540731169921L;
	private Long  roomId;
	private String roomName;
	private Integer roomNumber;
	private String roomAddress;
	private String roomPhone;
	private String roomState;
	private String roomTopic;
	private String roomRemark;
	private String createDate;
	private String createName;
	private String updateDate;
	private String updateName;
	private String default1;
	private String default2;
	private Integer isEnable;
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
	 * Creates a new instance of MeetingRoom.
	 *
	 */
	
	public MeetingRoom() {
		super();
	}
	
	/**
	 * Creates a new instance of MeetingRoom.
	 *
	 * @param roomId
	 * @param roomName
	 * @param roomNumber
	 * @param roomAddress
	 * @param roomPhone
	 * @param roomState
	 * @param roomTopic
	 * @param roomRemark
	 * @param createDate
	 * @param createName
	 * @param updateDate
	 * @param updateName
	 * @param default1
	 * @param default2
	 * @param isEnable
	 */
	
	public MeetingRoom(Long roomId, String roomName, Integer roomNumber, String roomAddress, String roomPhone,
			String roomState, String roomTopic, String roomRemark, String createDate, String createName,
			String updateDate, String updateName, String default1, String default2, Integer isEnable) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.roomAddress = roomAddress;
		this.roomPhone = roomPhone;
		this.roomState = roomState;
		this.roomTopic = roomTopic;
		this.roomRemark = roomRemark;
		this.createDate = createDate;
		this.createName = createName;
		this.updateDate = updateDate;
		this.updateName = updateName;
		this.default1 = default1;
		this.default2 = default2;
		this.isEnable = isEnable;
	}

	/**
	 * Creates a new instance of MeetingRoom.
	 *
	 * @param roomName
	 * @param roomNumber
	 * @param roomAddress
	 * @param roomPhone
	 * @param roomState
	 * @param roomTopic
	 * @param roomRemark
	 * @param createDate
	 * @param createName
	 * @param updateDate
	 * @param updateName
	 * @param isEnable
	 */
	
	public MeetingRoom(String roomName, Integer roomNumber, String roomAddress, String roomPhone, String roomState,
			String roomTopic, String roomRemark, String createDate, String createName, String updateDate,
			String updateName, Integer isEnable) {
		super();
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.roomAddress = roomAddress;
		this.roomPhone = roomPhone;
		this.roomState = roomState;
		this.roomTopic = roomTopic;
		this.roomRemark = roomRemark;
		this.createDate = createDate;
		this.createName = createName;
		this.updateDate = updateDate;
		this.updateName = updateName;
		this.isEnable = isEnable;
	}

	/**
	 * Creates a new instance of MeetingRoom.
	 *
	 * @param roomId
	 * @param roomName
	 * @param roomNumber
	 * @param roomAddress
	 * @param roomPhone
	 * @param roomState
	 * @param roomTopic
	 * @param roomRemark
	 * @param isEnable
	 */
	
	public MeetingRoom(Long roomId, String roomName, Integer roomNumber, String roomAddress, String roomPhone,
			String roomState, String roomTopic, String roomRemark, Integer isEnable) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomNumber = roomNumber;
		this.roomAddress = roomAddress;
		this.roomPhone = roomPhone;
		this.roomState = roomState;
		this.roomTopic = roomTopic;
		this.roomRemark = roomRemark;
		this.isEnable = isEnable;
	}

	/**
	 * roomId.
	 *
	 * @return  the roomId
	 * @since   JDK 1.8
	 */
	public Long getRoomId() {
		return roomId;
	}
	/**
	 * roomId.
	 *
	 * @param   roomId    the roomId to set
	 * @since   JDK 1.8
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
	 * roomName.
	 *
	 * @return  the roomName
	 * @since   JDK 1.8
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * roomName.
	 *
	 * @param   roomName    the roomName to set
	 * @since   JDK 1.8
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * roomNumber.
	 *
	 * @return  the roomNumber
	 * @since   JDK 1.8
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
	/**
	 * roomNumber.
	 *
	 * @param   roomNumber    the roomNumber to set
	 * @since   JDK 1.8
	 */
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	/**
	 * roomAddress.
	 *
	 * @return  the roomAddress
	 * @since   JDK 1.8
	 */
	public String getRoomAddress() {
		return roomAddress;
	}
	/**
	 * roomAddress.
	 *
	 * @param   roomAddress    the roomAddress to set
	 * @since   JDK 1.8
	 */
	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}
	/**
	 * roomPhone.
	 *
	 * @return  the roomPhone
	 * @since   JDK 1.8
	 */
	public String getRoomPhone() {
		return roomPhone;
	}
	/**
	 * roomPhone.
	 *
	 * @param   roomPhone    the roomPhone to set
	 * @since   JDK 1.8
	 */
	public void setRoomPhone(String roomPhone) {
		this.roomPhone = roomPhone;
	}
	/**
	 * roomState.
	 *
	 * @return  the roomState
	 * @since   JDK 1.8
	 */
	public String getRoomState() {
		return roomState;
	}
	/**
	 * roomState.
	 *
	 * @param   roomState    the roomState to set
	 * @since   JDK 1.8
	 */
	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}
	/**
	 * roomTopic.
	 *
	 * @return  the roomTopic
	 * @since   JDK 1.8
	 */
	public String getRoomTopic() {
		return roomTopic;
	}
	/**
	 * roomTopic.
	 *
	 * @param   roomTopic    the roomTopic to set
	 * @since   JDK 1.8
	 */
	public void setRoomTopic(String roomTopic) {
		this.roomTopic = roomTopic;
	}
	/**
	 * roomRemark.
	 *
	 * @return  the roomRemark
	 * @since   JDK 1.8
	 */
	public String getRoomRemark() {
		return roomRemark;
	}
	/**
	 * roomRemark.
	 *
	 * @param   roomRemark    the roomRemark to set
	 * @since   JDK 1.8
	 */
	public void setRoomRemark(String roomRemark) {
		this.roomRemark = roomRemark;
	}
	/**
	 * createDate.
	 *
	 * @return  the createDate
	 * @since   JDK 1.8
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * createDate.
	 *
	 * @param   createDate    the createDate to set
	 * @since   JDK 1.8
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * createName.
	 *
	 * @return  the createName
	 * @since   JDK 1.8
	 */
	public String getCreateName() {
		return createName;
	}
	/**
	 * createName.
	 *
	 * @param   createName    the createName to set
	 * @since   JDK 1.8
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * updateDate.
	 *
	 * @return  the updateDate
	 * @since   JDK 1.8
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * updateDate.
	 *
	 * @param   updateDate    the updateDate to set
	 * @since   JDK 1.8
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * updateName.
	 *
	 * @return  the updateName
	 * @since   JDK 1.8
	 */
	public String getUpdateName() {
		return updateName;
	}
	/**
	 * updateName.
	 *
	 * @param   updateName    the updateName to set
	 * @since   JDK 1.8
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * default1.
	 *
	 * @return  the default1
	 * @since   JDK 1.8
	 */
	public String getDefault1() {
		return default1;
	}
	/**
	 * default1.
	 *
	 * @param   default1    the default1 to set
	 * @since   JDK 1.8
	 */
	public void setDefault1(String default1) {
		this.default1 = default1;
	}
	/**
	 * default2.
	 *
	 * @return  the default2
	 * @since   JDK 1.8
	 */
	public String getDefault2() {
		return default2;
	}
	/**
	 * default2.
	 *
	 * @param   default2    the default2 to set
	 * @since   JDK 1.8
	 */
	public void setDefault2(String default2) {
		this.default2 = default2;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeetingRoom [roomId=" + roomId + ", roomName=" + roomName + ", roomNumber=" + roomNumber
				+ ", roomAddress=" + roomAddress + ", roomPhone=" + roomPhone + ", roomState=" + roomState
				+ ", roomTopic=" + roomTopic + ", roomRemark=" + roomRemark + ", createDate=" + createDate
				+ ", createName=" + createName + ", updateDate=" + updateDate + ", updateName=" + updateName
				+ ", default1=" + default1 + ", default2=" + default2 + ", isEnable=" + isEnable + "]";
	}
	
	
	
}

