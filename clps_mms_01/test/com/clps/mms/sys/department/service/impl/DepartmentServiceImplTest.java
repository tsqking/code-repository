/**
 * Project Name:clps_mms_01
 * File Name:DepartmentServiceImplTest.java
 * Package Name:com.clps.mms.sys.department.service.impl
 * Date:2017年1月13日上午10:24:10
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:DepartmentServiceImplTest.java
 * Package Name:com.clps.mms.sys.department.service.impl
 * Date:2017年1月13日上午10:24:10
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.department.service.impl;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.sys.department.dao.DepartmentInfoMapper;
import com.clps.mms.sys.department.model.DepartmentInfo;
import com.clps.mms.sys.department.service.IDepartmentService;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.DepartmentServiceException;

/**
 * ClassName:DepartmentServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 上午10:24:10 <br/>
 * @author   ruby
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: DepartmentServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月13日 上午10:24:10 <br/>
 *
 * @author ruby.zhao
 * @version 
 * @since JDK 1.8
 */
public class DepartmentServiceImplTest {

	ApplicationContext context=new ClassPathXmlApplicationContext("com/clps/mms/sys/department/test/applicationContext.xml");
	IDepartmentService service=(IDepartmentService) context.getBean("departmentService");
	
	/**
	 *
	 * @author ruby
	 * @throws java.lang.Exception
	 * @since JDK 1.8
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 *
	 * @author ruby
	 * @throws java.lang.Exception
	 * @since JDK 1.8
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_insertDepartmentInfo() {
		DepartmentInfo info=new DepartmentInfo("Tea", "教学部","Alice","0213-1234-344","alice@163.com",Trans_Date(), "ruby", null, null, 1);
		
		try {
			assertEquals(true, service.addDepartmentInfo(info));
		} catch (DepartmentServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_queryDepartmentInfoById() {
		System.out.println("执行查询语句");
		
		try {
			DepartmentInfo findDepartmentInfoById = service.findDepartmentInfoById(11L);
			System.out.println(findDepartmentInfoById);
		} catch (DepartmentServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_deleteDepartmentInfoById() {
		System.out.println("执行删除语句");
		try {
			service.deleteDepartmentInfoById(9L);
		} catch (DepartmentServiceException e) {
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void test_updateDepartmentInfoById() {
		System.out.println("执行更新语句");
		
		try {
			DepartmentInfo departmentInfo=service.findDepartmentInfoById(11L);
			departmentInfo.setName("咨询部");
			departmentInfo.setCreatedate(Trans_Date());
			departmentInfo.setCreatename("ruby");
			service.updateDepartmentInfoById(departmentInfo);
		} catch (DepartmentServiceException e) {
			e.printStackTrace();
			
		}
	}
	
	//日期格式转换
	public String Trans_Date(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}
	
	@Test
	public void test(){}
}

