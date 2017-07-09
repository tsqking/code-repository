/**
 * Project Name:clps_mms_01
 * File Name:Equipment.java
 * Package Name:com.clps.mms.sys.meetingroom.model
 * Date:2017年1月11日下午3:18:17
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:Equipment.java
 * Package Name:com.clps.mms.sys.meetingroom.model
 * Date:2017年1月11日下午3:18:17
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.model;
/**
 * ClassName:Equipment <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月11日 下午3:18:17 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: Equipment <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月11日 下午3:18:17 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class Equipment {

	private Long equipmentId;
	private String equipmentName;
	private Double equipmentPrice;
	private String equipmentState;
	private String equipmentUse;
	private String equipmentType;
	private String equipmentRemark;
	private String createDate;
	private String createName;
	private String updateDate;
	private String updateName;
	private String default1;
	private String default2;
	private Integer isEnable;
	/**
	 * Creates a new instance of Equipment.
	 *
	 */
	
	public Equipment() {
		
		super();
	}
	/**
	 * Creates a new instance of Equipment.
	 *
	 * @param equipmentId
	 * @param equipmentName
	 * @param equipmentPrice
	 * @param equipmentState
	 * @param equipmentUse
	 * @param equipmentType
	 * @param equipmentRemark
	 * @param createDate
	 * @param createName
	 * @param updateDate
	 * @param updateName
	 * @param default1
	 * @param default2
	 * @param isEnable
	 */
	
	public Equipment(Long equipmentId, String equipmentName, Double equipmentPrice, String equipmentState,
			String equipmentUse, String equipmentType, String equipmentRemark, String createDate, String createName,
			String updateDate, String updateName, String default1, String default2,Integer isEnable) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.equipmentPrice = equipmentPrice;
		this.equipmentState = equipmentState;
		this.equipmentUse = equipmentUse;
		this.equipmentType = equipmentType;
		this.equipmentRemark = equipmentRemark;
		this.createDate = createDate;
		this.createName = createName;
		this.updateDate = updateDate;
		this.updateName = updateName;
		this.default1 = default1;
		this.default2 = default2;
		this.isEnable = isEnable;
	}
	/**
	 * Creates a new instance of Equipment.
	 *
	 * @param equipmentId
	 * @param equipmentName
	 * @param equipmentPrice
	 * @param equipmentState
	 * @param equipmentUse
	 * @param equipmentType
	 * @param equipmentRemark
	 */
	
	public Equipment(Long equipmentId, String equipmentName, Double equipmentPrice, String equipmentState,
			String equipmentUse, String equipmentType, String equipmentRemark) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.equipmentPrice = equipmentPrice;
		this.equipmentState = equipmentState;
		this.equipmentUse = equipmentUse;
		this.equipmentType = equipmentType;
		this.equipmentRemark = equipmentRemark;
	}
	/**
	 * equipmentId.
	 *
	 * @return  the equipmentId
	 * @since   JDK 1.8
	 */
	public Long getEquipmentId() {
		return equipmentId;
	}
	/**
	 * equipmentId.
	 *
	 * @param   equipmentId    the equipmentId to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	/**
	 * equipmentName.
	 *
	 * @return  the equipmentName
	 * @since   JDK 1.8
	 */
	public String getEquipmentName() {
		return equipmentName;
	}
	/**
	 * equipmentName.
	 *
	 * @param   equipmentName    the equipmentName to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	/**
	 * equipmentPrice.
	 *
	 * @return  the equipmentPrice
	 * @since   JDK 1.8
	 */
	public Double getEquipmentPrice() {
		return equipmentPrice;
	}
	/**
	 * equipmentPrice.
	 *
	 * @param   equipmentPrice    the equipmentPrice to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentPrice(Double equipmentPrice) {
		this.equipmentPrice = equipmentPrice;
	}
	/**
	 * equipmentState.
	 *
	 * @return  the equipmentState
	 * @since   JDK 1.8
	 */
	public String getEquipmentState() {
		return equipmentState;
	}
	/**
	 * equipmentState.
	 *
	 * @param   equipmentState    the equipmentState to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}
	/**
	 * equipmentUse.
	 *
	 * @return  the equipmentUse
	 * @since   JDK 1.8
	 */
	public String getEquipmentUse() {
		return equipmentUse;
	}
	/**
	 * equipmentUse.
	 *
	 * @param   equipmentUse    the equipmentUse to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentUse(String equipmentUse) {
		this.equipmentUse = equipmentUse;
	}
	/**
	 * equipmentType.
	 *
	 * @return  the equipmentType
	 * @since   JDK 1.8
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * equipmentType.
	 *
	 * @param   equipmentType    the equipmentType to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	/**
	 * equipmentRemark.
	 *
	 * @return  the equipmentRemark
	 * @since   JDK 1.8
	 */
	public String getEquipmentRemark() {
		return equipmentRemark;
	}
	/**
	 * equipmentRemark.
	 *
	 * @param   equipmentRemark    the equipmentRemark to set
	 * @since   JDK 1.8
	 */
	public void setEquipmentRemark(String equipmentRemark) {
		this.equipmentRemark = equipmentRemark;
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
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", equipmentPrice="
				+ equipmentPrice + ", equipmentState=" + equipmentState + ", equipmentUse=" + equipmentUse
				+ ", equipmentType=" + equipmentType + ", equipmentRemark=" + equipmentRemark + ", createDate="
				+ createDate + ", createName=" + createName + ", updateDate=" + updateDate + ", updateName="
				+ updateName + ", default1=" + default1 + ", default2=" + default2 + ", isEnable=" + isEnable + "]";
	}
	
}

