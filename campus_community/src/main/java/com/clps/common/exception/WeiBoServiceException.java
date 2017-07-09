/**
 * Project Name:campus_community
 * File Name:WeiBoServiceException.java
 * Package Name:com.clps.common.exception
 * Date:2017年3月24日上午12:14:55
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.exception;

/**
 * ClassName:WeiBoServiceException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月24日 上午12:14:55 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class WeiBoServiceException extends Exception {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	public WeiBoServiceException() {
	}

	public WeiBoServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeiBoServiceException(String message) {
		super(message);
	}

	public WeiBoServiceException(Throwable cause) {
		super(cause);
	}
}
