/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserServiceImplTest.java
 * Package Name:com.clps.mms.sys.user.service.impl
 * Date:2016年11月6日下午8:49:29
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserServiceImplTest.java
 * Package Name:com.clps.mms.sys.user.service.impl
 * Date:2016年11月6日下午8:49:29
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.user.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.user.service.IUserService;
import com.clps.mms.util.exception.UserServiceException;

/**
 * ClassName:UserServiceImplTest 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年11月6日 下午8:49:29 
 * @author   tony.tan  
 * 	 
 */
/**
 * ClassName: UserServiceImplTest.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2016年11月6日 下午8:49:29 
 *
 * @author tony.tan
 * @version 
 * 
 */
public class UserServiceImplTest {
	ApplicationContext context=new ClassPathXmlApplicationContext("com/clps/mms/sys/user/test/applicationContext.xml");
	IUserService service=(IUserService) context.getBean("userService");
	/**
	 * setUp:(这里用一句话描述这个方法的作用). 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * tearDown:(这里用一句话描述这个方法的作用). 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#UserServiceImpl()}.
	 */
	@Test
	public void testUserServiceImpl() {
	}

	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#register(com.clps.mms.sys.user.model.UserInfo)}.
	 */
	@Test
	public void testRegister() {
		 UserInfo user=new UserInfo(null, "terry", "ffffff", "1234","男", "15070080777", "tmbasama@163.com",11,111,null,"tan","","tan",null);
		try {
			assertEquals(true,service.register(user));
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		try {
			assertNotNull(service.login("ted", "1234"));
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#findUserPWD(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testFindUserPWD() {
		try {
			assertEquals(true,service.findUserPWD("ted", "1234"));
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#findAllUserInfo()}.
	 */
	@Test
	public void testFindAllUserInfo() {
		try {
			List<UserInfo> data=service.findAllUserInfo();
			for (UserInfo userInfo : data) {
				System.out.println(userInfo);
			}
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}


	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#updateUserInfoById(com.clps.mms.sys.user.model.UserInfo)}.
	 */
	@Test
	public void testUpdateUserInfo() {
		
		try {
			UserInfo userInfo=service.findUserInfoByName("jam");
			userInfo.setNickname("fsfsd");
			userInfo.setGender("男");
			userInfo.setMobNum("10086");
			userInfo.setEmail("10086@126.com");
			userInfo.setDepartment(1);
			userInfo.setPosition(2323);
			assertEquals(true,service.updateUserInfoById(userInfo));
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	/**
	 * Test method for {@link com.clps.mms.sys.user.service.impl.UserServiceImpl#deleteUserInfo(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDeleteUserInfo() {
		try {
			assertEquals(true,service.deleteUserInfoById("tan", "jam"));
		} catch (UserServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	/**
	 * 
	 * testFindUserInfoByName:(这里用一句话描述这个方法的作用).
	 * Test method for{@link com.clps.mms.sys.user.service.IUserService#findUserInfoByName(java.lang.String)}
	 */
   @Test
   public void testFindUserInfoByName(){
	   try {
		   assertNotNull(service.findUserInfoByName("tarry"));
	} catch (UserServiceException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
   }
}

