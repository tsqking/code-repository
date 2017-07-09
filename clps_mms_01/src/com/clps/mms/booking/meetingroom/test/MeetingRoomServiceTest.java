/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomServiceTest.java
 * Package Name:com.clps.mms.sys.meetingroom.test
 * Date:2017年1月13日下午3:29:52
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomServiceTest.java
 * Package Name:com.clps.mms.sys.meetingroom.test
 * Date:2017年1月13日下午3:29:52
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.test;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.booking.meetingroom.service.IMeetingRoomService;
import com.clps.mms.util.exception.MeetingroomServiceException;

/**
 * ClassName:MeetingRoomServiceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 下午3:29:52 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingRoomServiceTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月13日 下午3:29:52 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class MeetingRoomServiceTest {

	ApplicationContext context;
	IMeetingRoomService service;
	@Before
	public void init(){
		context =new ClassPathXmlApplicationContext("com/clps/mms/booking/meetingroom/test/applicationContext.xml");
		service = (IMeetingRoomService) context.getBean("meetingRoomService");
	}
	@After
	public void Destroy(){}
	@Test
	public void insertTest() {
		MeetingRoom room = new MeetingRoom(4L, "room3", 20, "1th floor", "021-1234567", "1", "java", null,new Date(0, 0, 0).toString(),"lacus",null,null, null, null, 1);
		try {
			service.insertMeetingRoom(room);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	@Test
	public void deleteTest(){
		try {
			service.deleteMeetingRoomById("");
		} catch (MeetingroomServiceException e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	public void selectTest(){
		MeetingRoom room = service.queryMeetingRoomById((long) 9);
		System.out.println(room);
	}

	@Test
	public void updateTest(){
		MeetingRoom room = service.queryMeetingRoomById((long) 9);
		room.setRoomName("TrainningRoom");
		room.setRoomNumber(12);
		room.setRoomAddress("2td floor");
		room.setRoomPhone("021-1234567");
		room.setRoomState("0");
		room.setUpdateDate(new java.sql.Date(1).toLocaleString());
		room.setUpdateName("ruby");
		try {
			service.updateMeetingRoomById(room);
		} catch (MeetingroomServiceException e) {
			e.printStackTrace();
			
		}
	}
	@Test
	public void selectAllTest(){
		List<MeetingRoom> list = service.findAllMeetingroom();
		for (MeetingRoom meetingRoom : list) {
			System.out.println(meetingRoom);
		}
	}
}

