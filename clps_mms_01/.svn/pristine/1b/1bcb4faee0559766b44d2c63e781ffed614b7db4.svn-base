/**
 * Project Name:clps_mms_01
 * File Name:EquipmentServiceTest.java
 * Package Name:com.clps.mms.booking.meetingroom.test
 * Date:2017年1月19日上午10:26:03
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:EquipmentServiceTest.java
 * Package Name:com.clps.mms.booking.meetingroom.test
 * Date:2017年1月19日上午10:26:03
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

import com.clps.mms.booking.meetingroom.model.Equipment;
import com.clps.mms.booking.meetingroom.service.IEquipmentService;
import com.clps.mms.util.exception.EquipmentServiceException;

/**
 * ClassName:EquipmentServiceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月19日 上午10:26:03 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: EquipmentServiceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月19日 上午10:26:03 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class EquipmentServiceTest {

	ApplicationContext context;
	IEquipmentService service;
	@Before
	public void init(){
		context =new ClassPathXmlApplicationContext("com/clps/mms/booking/meetingroom/test/applicationContext.xml");
		service = (IEquipmentService) context.getBean("equipmentService");
	}
	@After
	public void Destroy(){}
	@Test
	public void insertTest(){
		Equipment equ = 
				new Equipment((long) 10, "desk", 100.0, "0", null, "type1", null, new Date(1).toString(), "lacus", null, null, null, null, 1);
		try {
			service.addEquipment(equ);
		} catch (EquipmentServiceException e) {
			e.printStackTrace();
			
		}
	}
	@Test
	public void deleteTest(){
		try {
			service.deleteEquipmentById("");
		} catch (EquipmentServiceException e) {
			e.printStackTrace();
			
		}
	}
	@Test
	public void updateTest(){
		Equipment equ = service.queryEquipmentById((long) 2);
		equ.setUpdateName("ruby");
		try {
			service.updateEquipmentById(equ);
		} catch (EquipmentServiceException e) {
			e.printStackTrace();
			
		}
	}
	@Test
	public void selectTest(){
		Equipment equipment = service.queryEquipmentById((long) 2);
		System.out.println(equipment);
	}
}

