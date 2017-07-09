/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomDao.java
 * Package Name:com.clps.mms.sys.meetingroom.dao
 * Date:2017年1月11日下午3:35:31
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomDao.java
 * Package Name:com.clps.mms.sys.meetingroom.dao
 * Date:2017年1月11日下午3:35:31
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:MeetingRoomDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月11日 下午3:35:31 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingRoomDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月11日 下午3:35:31 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
@Repository("roomDao")
public interface MeetingRoomDao {
	/**
	 * 
	 * insertMeetingRoom:(保存办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param room
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean insertMeetingRoom(MeetingRoom room) throws DataAccessException;
	/**
	 * 
	 * updateMeetingRoom:(修改办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param room
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean updateMeetingRoomById(MeetingRoom room) throws DataAccessException;
	/**
	 * 
	 * deleteMeetingRoomById:(通过ID删除办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param meetingRoomId
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public boolean deleteMeetingRoomById(long meetingRoomId) throws DataAccessException;
	/**
	 * 
	 * queryMeetingRoomById:(根据ID查询办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param meetingRoomId
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public MeetingRoom queryMeetingRoomById(long meetingRoomId) throws DataAccessException;
	/**
	 * 
	 * queryMeetingRoomByName:(根据办公室名称查询办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param MeetingName
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public MeetingRoom queryMeetingRoomByName(String MeetingName) throws DataAccessException;
	/**
	 * 
	 * queryMeetingRoomByState:(根据状态查询办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param state
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<MeetingRoom> queryMeetingRoomByState(String state) throws DataAccessException;
	/**
	 * 
	 * queryMeetingRoomByNumber:(根据容纳人数查询办公室). <br/>
	 *
	 * @author lacus.wang
	 * @param number
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<MeetingRoom> queryMeetingRoomByNumber(int number) throws DataAccessException;
	/**
	 * 
	 * queryMeetingRoomOnCondition:(根据办公室状态和容纳人数查询办公室). <br/>
	 *
	 * @author lacus.wang
	 * @param state
	 * @param number
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<MeetingRoom> queryMeetingRoomOnCondition(String state,int number) throws DataAccessException;
	/**
	 * 
	 * queryAllMeetingRoom:(查询所有的办公室信息). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<MeetingRoom> queryAllMeetingRoom() throws DataAccessException;
	/**
	 * 
	 * queryMeetingroomLst:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author lacus.wang
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	public List<MeetingRoom> queryMeetingroomLst(Map<String, Object> map)throws DataAccessException;
	
	public Long queryMeetingroomCount(Map<String, Object> map)throws DataAccessException;
	public List<String> queryAllNames() throws DataAccessException;
	public List<Integer> queryAllNumber() throws DataAccessException;
	public List<String> queryAllAddresses()throws DataAccessException;
}

