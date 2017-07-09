
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserInterceptor.java
 * Package Name:com.clps.mms.sys.action.interceptor
 * Date:2016年11月15日下午5:13:09
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
 */
package com.clps.mms.sys.action.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * ClassName: UserInterceptor.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2016年11月15日 下午5:13:09 
 *
 * @author tony.tan
 * @version 
 * 
 */
public class UserInterceptor extends AbstractInterceptor{

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		
		ActionContext ctx=invo.getInvocationContext();
		Map<String, Object>session=ctx.getSession();
			String username=(String) session.get("username");
			System.out.println("username:"+username);
			//UserInfo user=userService.findUserInfoByName(username);
			//deptService.findDepartmentById();
			if (username!=null&&username.equals("ted")) {
				System.out.println("hello,you are permitted.");
				invo.invoke();
				return Action.SUCCESS;
			}
			 // 获取HttpServletRequest对象 
			HttpServletRequest req=ServletActionContext.getRequest();
			// 获取此请求的地址  
			String path=req.getRequestURI();
			System.out.println("path:"+path);
			session.put("prePage", path);
			return "login";
	}

}

