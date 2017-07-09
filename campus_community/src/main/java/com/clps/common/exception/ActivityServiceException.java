/**
 * Project Name:campus_community
 * File Name:ActivityServiceException.java
 * Package Name:com.clps.common.exception
 * Date:2017年3月27日下午9:56:16
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.exception;

/**
 * ClassName:ActivityServiceException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月27日 下午9:56:16 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class ActivityServiceException extends Exception {
	public static final String Activity_EXIST = "该活动已存在！";
	public static final String Activity_NOT_EXIST = "该活动不存在！";
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	public ActivityServiceException() {
	}

	public ActivityServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActivityServiceException(String message) {
		super(message);
	}

	public ActivityServiceException(Throwable cause) {
		super(cause);
	}

}
