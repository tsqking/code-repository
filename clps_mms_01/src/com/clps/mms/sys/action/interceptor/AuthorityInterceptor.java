
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:AuthorityInterceptor.java
 * Package Name:com.clps.mms.sys.action.interceptor
 * Date:2016年11月13日下午11:19:10
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
 */
package com.clps.mms.sys.action.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.user.service.IUserService;
import com.clps.mms.sys.user.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * ClassName: AuthorityInterceptor.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2016年11月13日 下午11:19:10 
 *
 * @author tony.tan
 * @version 
 * 
 */
public class AuthorityInterceptor implements Interceptor{
   // private IDepartmentService deptService=new DepartmentServiceImpl();
   // private IUserService userService=new UserServiceImpl();
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		ActionContext ctx=invo.getInvocationContext();
		Map<String, Object>session=ctx.getSession();
			String username=(String) session.get("username");
			System.out.println("username:"+username);
			//UserInfo user=userService.findUserInfoByName(username);
			//deptService.findDepartmentById();
			if (username!=null&&!username.equals("")) {
				System.out.println("hello,you are permitted.");
				return Action.SUCCESS;
			}
			 // 获取HttpServletRequest对象 
			/*HttpServletRequest req=ServletActionContext.getRequest();
			// 获取此请求的地址  
			String path=req.getRequestURI();
			System.out.println("path:"+path);
			session.put("prePage", path);*/
			invo.invoke();
			return "login";
			
	}

}

