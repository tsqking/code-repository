/**
 * Project Name:clps_mms_01
 * File Name:DaoTest.java
 * Package Name:com.clps.mms.sys.department.test
 * Date:2017年1月13日下午1:39:20
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:DaoTest.java
 * Package Name:com.clps.mms.sys.department.test
 * Date:2017年1月13日下午1:39:20
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.department.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.DepartmentServiceException;

/**
 * ClassName:DaoTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 下午1:39:20 <br/>
 * @author   ruby
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class DaoTest {

	ApplicationContext context=new ClassPathXmlApplicationContext("com/clps/mms/sys/department/test/applicationContext.xml");
//	DefaultSqlSessionFactory sessionFactory;
//	SqlSession session;
	/**
	 * setUp:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author ZYR
	 * @throws java.lang.Exception
	 * @since JDK 1.8
	 */
	@Before
	public void setUp() throws Exception {
//		sessionFactory  = (DefaultSqlSessionFactory) context.getBean("departmentDao");
//		session = sessionFactory.openSession();
	}

	/**
	 * tearDown:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author ZYR
	 * @throws java.lang.Exception
	 * @since JDK 1.8
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_insertDepartmentInfo() {
		DepartmentInfoMapper bean = (DepartmentInfoMapper) context.getBean("departmentDao");
		DepartmentInfo info=new DepartmentInfo("Tea", "教学部","Alice","0213-1234-344","alice@163.com",Trans_Date(), "ruby", null, null, 1);
//		DepartmentInfoMapper mapper = session.getMapper(DepartmentInfoMapper.class);
		try {
			bean.insertDepartmentInfo(info);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
	}

	//日期格式转换
	public String Trans_Date(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}
	

}

