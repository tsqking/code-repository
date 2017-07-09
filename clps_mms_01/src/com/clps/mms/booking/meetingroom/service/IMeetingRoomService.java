/**
 * Project Name:clps_mms_01
 * File Name:IMeetingRoomService.java
 * Package Name:com.clps.mms.sys.meetingroom.service
 * Date:2017年1月12日下午6:19:29
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:IMeetingRoomService.java
 * Package Name:com.clps.mms.sys.meetingroom.service
 * Date:2017年1月12日下午6:19:29
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.service;

import java.util.List;

import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.MeetingroomServiceException;

/**
 * ClassName:IMeetingRoomService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月12日 下午6:19:29 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: IMeetingRoomService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月12日 下午6:19:29 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
public interface IMeetingRoomService {

	/**
	 * 
	 * insertMeetingRoom:(新增一个办公室，保存到会议室). <br/>
	 *
	 * @author lacus.wang
	 * @param room
	 * @return
	 * @since JDK 1.8
	 */
	public boolean insertMeetingRoom(MeetingRoom room)throws Exception;
	/**
	 * 
	 * deleteMeetingRoomById:(删除会议室). <br/>
	 *
	 * @author lacus.wang
	 * @param id
	 * @return 
	 * @throws MeetingroomServiceException 
	 * @since JDK 1.8
	 */
	public boolean deleteMeetingRoomById(String name) throws MeetingroomServiceException;
	/**
	 * 
	 * queryMeetingRoomById:(根据id查找会议室). <br/>
	 *
	 * @author lacus.wang
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	public MeetingRoom queryMeetingRoomById(Long id);
	
	public MeetingRoom queryMeetingRoomByName(String name);
	/**
	 * 
	 * updateMeetingRoomById:(修改会议室信息). <br/>
	 *
	 * @author lacus.wang
	 * @param room
	 * @throws MeetingroomServiceException 
	 * @since JDK 1.8
	 */
	public boolean updateMeetingRoomById(MeetingRoom room) throws MeetingroomServiceException;
	/**
	 * 
	 * findUserInfoLst:(分页查询所有数据). <br/>
	 *
	 * @author lacus.wang
	 * @param pageVo
	 * @return
	 * @since JDK 1.8
	 */
	public PageVo<MeetingRoom> findMeetingroomLst(PageVo<MeetingRoom> pageVo);
	/**
	 * 
	 * findAllMeetingroom:(查询所有的会议室信息). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @since JDK 1.8
	 */
	public List<MeetingRoom> findAllMeetingroom();
	
	public List<String> findAllNames();
	
	public List<Integer> findAllNumber();
	
	public List<String> findAllAddresses();
}

