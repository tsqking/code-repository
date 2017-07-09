/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomServiceImplTest.java
 * Package Name:com.clps.mms.sys.meetingroom.test
 * Date:2017年1月13日上午9:43:02
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomServiceImplTest.java
 * Package Name:com.clps.mms.sys.meetingroom.test
 * Date:2017年1月13日上午9:43:02
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clps.mms.booking.meetingroom.dao.MeetingRoomDao;
import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:MeetingRoomServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月13日 上午9:43:02 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingRoomServiceImplTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月13日 上午9:43:02 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public class MeetingRoomDaoImplTest {
	ApplicationContext context;
	MeetingRoomDao roomDao;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("com/clps/mms/booking/meetingroom/test/applicationContext.xml");
		roomDao = (MeetingRoomDao) context.getBean("roomDao");
	}
	@After
	public void destroy(){
		
	}
	/**
	 * 
	 * testInsert:(测试dao层insertMeetingRoom()方法). <br/>
	 *
	 * @author lacus.wang
	 * @since JDK 1.8
	 */
	@Test
	public void testInsert() {
//		MeetingRoomDao roomDao = (MeetingRoomDao) context.getBean("roomDao");
		MeetingRoom room = new MeetingRoom(1L, "room1", 20, "1th floor", "021-1234567", "1", "java", null,new java.sql.Date(0, 0, 0).toString(),"lacus",null,null, null, null, 1);
		try {
			roomDao.insertMeetingRoom(room);
		} catch (DataAccessException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		/*try {
			assertEquals(true,roomDao.insertMeetingRoom(room));
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}*/
	}
	/**
	 * 
	 * selectTest:(测试dao层queryMeetingRoomById(Long id)方法). <br/>
	 *
	 * @author lacus.wang
	 * @since JDK 1.8
	 */
	@Test
	public void selectTest(){
//		MeetingRoomDao roomDao = (MeetingRoomDao) context.getBean("roomDao");
		try {
			roomDao.queryMeetingRoomById(9);
		} catch (DataAccessException e) {
			e.printStackTrace();
			
		}
	}
	/**
	 * 
	 * deleteTest:(测试dao层deleteMeetingRoomById()方法). <br/>
	 *
	 * @author lacus.wang
	 * @since JDK 1.8
	 */
	@Test
	public void deleteTest(){
		try {
			roomDao.deleteMeetingRoomById(8);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
	}
	/**
	 * 
	 * updateTest:(测试dao层updateMeetingRoomById()方法). <br/>
	 *
	 * @author lacus.wang
	 * @since JDK 1.8
	 */
	@Test
	public void updateTest(){
		try {
			MeetingRoom room = roomDao.queryMeetingRoomById(9);
			System.out.println(room.getRoomName()+"----------");
			room.setRoomName("TrainningRoom");
			room.setRoomNumber(30);
			room.setRoomAddress("2td floor");
			room.setRoomPhone("021-1234567");
			room.setRoomState("0");
			room.setUpdateDate(new java.sql.Date(1).toLocaleString());
			room.setUpdateName("lacus");
			roomDao.updateMeetingRoomById(room);
		} catch (DataAccessException e) {
			e.printStackTrace();
			
		}
	}
}

