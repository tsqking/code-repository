package com.clps.mms.util.exception;

public class EquipmentLogServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String EQUIPMENTINFO_EXIST = "该设备名已存在！";
	public static final String EQUIPMENTINFO_NOT_EXIST = "该设备名不存在！";

	public EquipmentLogServiceException() {

		super();
		// TODO Auto-generated constructor stub
	}

	public EquipmentLogServiceException(String message, Throwable cause) {

		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EquipmentLogServiceException(String message) {

		super(message);
		// TODO Auto-generated constructor stub
	}

	public EquipmentLogServiceException(Throwable cause) {

		super(cause);
		// TODO Auto-generated constructor stub
	}

}
