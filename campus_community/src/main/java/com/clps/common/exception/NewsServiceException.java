/**
 * Project Name:campus_community
 * File Name:NewsServiceException.java
 * Package Name:com.clps.common.exception
 * Date:2017年4月12日下午3:25:56
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.exception;

/**
 * ClassName:NewsServiceException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月12日 下午3:25:56 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class NewsServiceException extends Exception {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	public NewsServiceException() {
	}

	public NewsServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewsServiceException(String message) {
		super(message);
	}

	public NewsServiceException(Throwable cause) {
		super(cause);
	}

}
