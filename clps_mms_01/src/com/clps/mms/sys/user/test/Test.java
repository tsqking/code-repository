
/**
 * Project Name:clps_mms_201701
 * File Name:Test.java
 * Package Name:com.clps.mms.sys.user.test
 * Date:2017年1月5日下午4:53:45
 * Copyright (c) 2017, tmbasama@163.com All Rights Reserved.
 *
 */
package com.clps.mms.sys.user.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.user.service.IUserService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.UserServiceException;

/**
 * ClassName: Test.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2017年1月5日 下午4:53:45 
 *
 * @author tony.tan
 * @version 
 * 
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("com/clps/mms/sys/user/test/applicationContext.xml");
		IUserService service=(IUserService) context.getBean("userService");
		try {
		//System.out.println(service.login("ted", "1234"));
			service.findUserInfoLst(new PageVo<UserInfo>());
			//System.out.println(datas);
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}

