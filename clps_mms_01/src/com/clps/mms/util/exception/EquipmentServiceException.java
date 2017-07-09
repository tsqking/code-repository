/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserServiceException.java
 * Package Name:com.clps.mms.sys.user.exception
 * Date:2016年10月18日下午10:40:52
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.exception;
/**
 * ClassName:UserServiceException 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 下午10:40:52 
 * @author   tony.tan  
 * 	 
 */
public class EquipmentServiceException extends Exception{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
    public static final String EQUIPMENT_EXIST="该设备已存在！";
    public static final String EQUIPMENT_NOT_EXIST="该设备不存在！";
	public EquipmentServiceException() {
		
		super();
	}

	public EquipmentServiceException(String message, Throwable cause) {
		
		super(message, cause);
	}

	public EquipmentServiceException(String message) {
		
		super(message);
	}

	public EquipmentServiceException(Throwable cause) {
		
		super(cause);
	}

}

