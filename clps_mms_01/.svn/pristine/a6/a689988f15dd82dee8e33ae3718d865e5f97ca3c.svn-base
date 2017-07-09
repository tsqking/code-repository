/**
 * Project Name:clps_mms_01
 * File Name:MenuServiceImplTest.java
 * Package Name:com.clps.mms.sys.user.service.impl
 * Date:2017年1月12日下午11:23:45
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.menu.test.codetest;

import java.util.Date;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.clps.mms.sys.menu.dao.MenuDao;
import com.clps.mms.sys.menu.model.Menu;
import com.clps.mms.sys.menu.service.IMenuService;
import com.clps.mms.sys.menu.service.impl.MenuServiceImpl;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.exception.MenuServiceException;

/**
 * ClassName:MenuServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年1月12日 下午11:23:45 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class MenuServiceImplTest {
	private ApplicationContext context = new ClassPathXmlApplicationContext(
			"com/clps/mms/sys/menu/test/config/spring-mybatis.xml");
	private IMenuService service;

	@Before
	public void init() {
		service = (IMenuService) context.getBean("menuService");
	}

	@After
	public void destroy() {

	}

	/**
	 * codeTest:(插入菜单测试). <br/>
	 *
	 * @author Charles
	 * @since JDK 1.8
	 */
	@Test
	public void insertTest() {
		System.out.println("执行插入语句");
		String date = DateFormat.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		Menu menu = new Menu("root001", "fsaf", "fsadfsf", "fsadfsa", date, "dafdsf", date, "fsadfsaf", "fsafasf", "fsfasfsa", "fsdafasdf", "fsdafsf", 1);
		try {
			service.insertService(menu);
		} catch (MenuServiceException e) {

			e.printStackTrace();

		}
	}

	/**
	 * deleteTest:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author Charles
	 * @since JDK 1.8
	 */
	@Test
	public void deleteTest() {
		System.out.println("执行删除语句");
		try {
			service.deleteService("fsafasf");
		} catch (MenuServiceException e) {

			e.printStackTrace();
		}
	}

	/**
	 * updateTest:(测试更新方法). <br/>
	 *
	 * @author Charles
	 * @since JDK 1.8
	 */
	@Test
	public void updateTest() {
		System.out.println("执行更新语句");
		String date = DateFormat.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		Menu menu = new Menu("root001", "222", "fsadfsf", "fsadfsa", date, "dafdsf", date, "fsadfsaf", "fsafasf", "fsfasfsa", "fsdafasdf", "fsdafsf", 1);
		try {
			service.updateServiceById(menu);
		} catch (MenuServiceException e) {

			e.printStackTrace();

		}
	}

	@Test
	public void findMenuById() {
		Menu result = null;
		System.out.println("执行查询语句");
		try {
			result = service.findMenuById("root001");
		} catch (MenuServiceException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	@Test
	public void testList(){
		try {
			service.findMenuInfoLst(null);
		} catch (MenuServiceException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
