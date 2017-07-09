/**
 * Project Name:clps_mms_01
 * File Name:RoleServiceImplTest.java
 * Package Name:com.clps.mms.sys.role.service.impl
 * Date:2017年1月13日上午9:16:54
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:RoleServiceImplTest.java
 * Package Name:com.clps.mms.sys.role.service.impl
 * Date:2017年1月13日上午9:16:54
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.role.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.sys.role.dao.RoleInfoMapper;
import com.clps.mms.sys.role.model.RoleInfo;
import com.clps.mms.sys.role.service.RoleService;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.RoleServiceException;
import com.clps.mms.util.exception.UserServiceException;

import net.sf.json.JSONObject;

/**
 * ClassName:RoleServiceImplTest Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON. Date: 2017年1月13日 上午9:16:54
 * 
 * @author lonnie
 * @version
 * @since JDK 1.8
 */
public class RoleServiceImplTest {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"com/clps/mms/sys/role/test/applicationContext.xml");
	RoleService service = (RoleService) context.getBean("roleService");

	// DefaultSqlSessionFactory sessionFactory;
	// SqlSession session;
	/**
	 * setUp:(这里用一句话描述这个方法的作用).
	 * 
	 * @author lonnie
	 * @throws java.lang.Exception
	 * @since JDK 1.8
	 */
	@Before
	public void setUp() throws Exception {
		// sessionFactory= (DefaultSqlSessionFactory)
		// context.getBean("sqlSessionFactory");
		// session = sessionFactory.openSession();
	}

	/**
	 * tearDown:(这里用一句话描述这个方法的作用).
	 * 
	 * @author lonnie
	 * @throws java.lang.Exception
	 * @since JDK 1.8
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 * testaddRoleInfo:(添加测试).
	 * 
	 * @author lonnie
	 * @since JDK 1.8
	 */
	@Test
	public void testaddRoleInfo() {
		RoleInfo roleinfo = new RoleInfo(7L,"jack", "jackie", "2017-01-18", null, null, 1, "ok");
		// RoleInfoMapper mapper = session.getMapper(RoleInfoMapper.class);
		// try {
		// mapper.insertRoleInfo(roleinfo);
		// } catch (DataAccessException e) {
		//
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		//
		// }
		try {
			assertEquals(true, service.addRoleInfo(roleinfo));
		} catch (RoleServiceException e) {

			e.printStackTrace();

		}
	}

	/**
	 * 
	 * testdeleteRoleInfo:(删除测试). .
	 * 
	 * @author lonnie
	 * @since JDK 1.8
	 */
	@Test
	public void testdeleteRoleInfo() {
		try {
			assertEquals(true, service.deleteRoleInfoById("4566", null));
		} catch (RoleServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * testFindUserInfoByName:(通过名字查找角色测试).
	 * 
	 * @author lonnie
	 * @since JDK 1.8
	 */
	@Test
	public void testFindRoleInfoByName() {
		try {
			assertNotNull(service.findRoleInfoByName("sdaf"));
		} catch (RoleServiceException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * testfindAllRoleInfo:(查询所有角色测试).
	 * 
	 * @author lonnie
	 * @since JDK 1.8
	 */
	@Test
	public void testfindAllRoleInfo() {
		try {
			List<RoleInfo> list = service.findAllRoleInfo();
			
			String sjson = null;
			JSONObject json=new JSONObject();
			for (RoleInfo roleInfo : list) {
				json.put("text", roleInfo.getName()); 
				json.put("id", roleInfo.getId());
			}
			sjson=json.toString();
			System.out.println(sjson);
			//System.out.println(list);
		} catch (RoleServiceException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * testupdateRoleInfo:(更新角色信测试). 
	 * @author lonnie
	 * @since JDK 1.8
	 */
	@Test
	public void testupdateRoleInfo() {
		try {
			RoleInfo roleInfo = service.findRoleInfoById(1L);
			roleInfo.setName("倪子龙");
			roleInfo.setCreate_name("lonnie");
			roleInfo.setCreate_time("2017-01-18");
			roleInfo.setDescription("no");
			System.out.println(roleInfo);
			assertEquals(true, service.updateRoleInfoById(roleInfo));
		} catch (RoleServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
