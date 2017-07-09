/**
 * Project Name:clps_mms_copyright_201610
 * File Name:DateAccessException.java
 * Package Name:com.clps.mms.sys.menu.exception
 * Date:2016年10月18日下午1:52:42
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.exception;
/**
 * ClassName:DateAccessException 
 * Function: TODO 数据库操作的一系列异常.
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 下午1:52:42 
 * @author   tony.tan  
 * 	 
 */
public class DataAccessException extends Exception{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException() {
		
		super();
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message, Throwable cause) {
		
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(String message) {
		
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Throwable cause) {
		
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

