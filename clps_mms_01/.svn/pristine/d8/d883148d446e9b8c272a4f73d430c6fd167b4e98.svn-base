/**
 * Project Name:clps_mms_01
 * File Name:MeetingroomInterceptor.java
 * Package Name:com.clps.mms.booking.action.interceptor
 * Date:2017年2月8日下午3:09:11
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingroomInterceptor.java
 * Package Name:com.clps.mms.booking.action.interceptor
 * Date:2017年2月8日下午3:09:11
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.action.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * ClassName:MeetingroomInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年2月8日 下午3:09:11 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingroomInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年2月8日 下午3:09:11 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class MeetingroomInterceptor extends AbstractInterceptor{

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		ActionContext ctx = invo.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		String username = (String)session.get("username");
		System.out.println("username:"+username);
		return null;
	}

	
}

