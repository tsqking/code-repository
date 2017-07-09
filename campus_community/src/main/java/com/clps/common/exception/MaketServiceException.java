/**
 * Project Name:campus_community
 * File Name:MaketServiceException.java
 * Package Name:com.clps.common.exception
 * Date:2017年4月12日上午11:38:48
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.exception;

/**
 * ClassName:MaketServiceException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月12日 上午11:38:48 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class MaketServiceException extends Exception {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	public MaketServiceException() {
	}

	public MaketServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MaketServiceException(String message) {
		super(message);
	}

	public MaketServiceException(Throwable cause) {
		super(cause);
	}

}
