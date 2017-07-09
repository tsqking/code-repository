/**
 * Project Name:campus_community
 * File Name:UserServiceException.java
 * Package Name:com.clps.common.exception
 * Date:2017年3月22日上午10:45:34
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.exception;

/**
 * ClassName:UserServiceException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月22日 上午10:45:34 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class UserServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String USERINFO_EXIST = "该用户已存在！";
	public static final String USERINFO_NOT_EXIST = "该用户不存在！";
	public static final String WRONG_PWD = "密码错误！";

	public UserServiceException() {
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(Throwable cause) {
		super(cause);
	}
}
