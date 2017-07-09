/**
 * Project Name:clps_mms_01
 * File Name:EquipmentDaoTest.java
 * Package Name:com.clps.mms.booking.meetingroom.test
 * Date:2017年1月18日上午10:53:29
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:EquipmentDaoTest.java
 * Package Name:com.clps.mms.booking.meetingroom.test
 * Date:2017年1月18日上午10:53:29
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.test;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.booking.meetingroom.dao.EquipmentDao;
import com.clps.mms.booking.meetingroom.model.Equipment;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:EquipmentDaoTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月18日 上午10:53:29 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: EquipmentDaoTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月18日 上午10:53:29 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class EquipmentDaoTest {

	ApplicationContext context;
	EquipmentDao equDao;
	
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("com/clps/mms/booking/meetingroom/test/applicationContext.xml");
		equDao = (EquipmentDao) context.getBean("equDao");
	}
	@After
	public void destroy(){}
	
	@Test
	public void insertTest(){
		Equipment equ = 
			new Equipment((long) 2, "equip2", 100.0, "0", null, "type1", null, new Date(1).toString(), "lacus", null, null, null, null, 1);
		try {
			equDao.insertEquipment(equ);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void deleteTest(){
		try {
			equDao.deleteEquipmentById((long) 1);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void updateTest(){
		try {
			Equipment equipment = equDao.queryEquipmentById((long) 2);
			equipment.setEquipmentName("desk");
			equipment.setEquipmentUse("use1");
			equipment.setUpdateDate(new Date(1).toString());
			equipment.setUpdateName("lacus");
			equDao.updateEquipmentById(equipment);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	public void selectTest(){
		try {
			Equipment equipment = equDao.queryEquipmentById((long) 2);
			System.out.println(equipment);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
	}
	
}

