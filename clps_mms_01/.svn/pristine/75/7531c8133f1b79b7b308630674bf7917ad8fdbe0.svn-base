/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomServiceImpl.java
 * Package Name:com.clps.mms.sys.meetingroom.service.impl
 * Date:2017年1月12日下午6:24:00
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingRoomServiceImpl.java
 * Package Name:com.clps.mms.sys.meetingroom.service.impl
 * Date:2017年1月12日下午6:24:00
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.meetingroom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.clps.mms.booking.meetingroom.dao.MeetingRoomDao;
import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.booking.meetingroom.service.IMeetingRoomService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.MeetingroomServiceException;

/**
 * ClassName:MeetingRoomServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月12日 下午6:24:00 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingRoomServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年1月12日 下午6:24:00 <br/>
 *
 * @author lacus.wang
 * @version
 * @since JDK 1.8
 */
@Service("meetingRoomService")
public class MeetingRoomServiceImpl implements IMeetingRoomService {

	@Resource(name="roomDao")
	private MeetingRoomDao roomDao;
	private Logger log=Logger.getLogger("console");

	/**
	 * roomDao TODO 添加办公室（可选）.
	 * 
	 * @see com.clps.mms.sys.meetingroom.service.IMeetingRoomService#insertMeetingRoom(com.clps.mms.sys.meetingroom.model.MeetingRoom)
	 */
	@Override
	public boolean insertMeetingRoom(MeetingRoom room) throws MeetingroomServiceException{

			System.out.println("进入service");
			try {
				if (roomDao.queryMeetingRoomByName(room.getRoomName()) != null) {
					log.error(MeetingroomServiceException.MEETINGROOM_EXIST);
					return false;
					//throw new MeetingroomServiceException(MeetingroomServiceException.MEETINGROOM_EXIST);
				} else {
					return roomDao.insertMeetingRoom(room);
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		return false;
	}

	/**
	 * TODO 删除会议室（可选）.
	 * @throws MeetingroomServiceException 
	 * 
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#deleteMeetingRoomById(java.lang.Long)
	 */
	@Override
	public boolean deleteMeetingRoomById(String name) throws MeetingroomServiceException{
		try {
			MeetingRoom room = roomDao.queryMeetingRoomByName(name);
			if ( room!= null) {
				boolean b = roomDao.deleteMeetingRoomById(room.getRoomId());
				return b;
			} else {
				log.error(MeetingroomServiceException.MEETINGROOM_NOT_EXIST);
				throw new MeetingroomServiceException(MeetingroomServiceException.MEETINGROOM_NOT_EXIST);
			}
		} catch (DataAccessException e) {

			e.printStackTrace();

		}
		return false;
	}

	/**
	 * TODO 查找会议室（可选）.
	 * 
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#queryMeetingRoomById(java.lang.Long)
	 */
	@Override
	public MeetingRoom queryMeetingRoomById(Long id) {
		MeetingRoom room;
		try {
			room = roomDao.queryMeetingRoomById(id);
			return room;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * TODO 修改会议室信息（可选）.
	 * @throws MeetingroomServiceException 
	 * 
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#updateMeetingRoomById()
	 */
	@Override
	public boolean updateMeetingRoomById(MeetingRoom room) throws MeetingroomServiceException {
		try {
			MeetingRoom DBroom = this.queryMeetingRoomByName(room.getRoomName());
			if (DBroom != null) {
				return roomDao.updateMeetingRoomById(room);
			} else {
				log.error(MeetingroomServiceException.MEETINGROOM_NOT_EXIST);
				throw new MeetingroomServiceException(MeetingroomServiceException.MEETINGROOM_NOT_EXIST);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * TODO 分页查询会议室信息.
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#findMeetingroomLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<MeetingRoom> findMeetingroomLst(PageVo<MeetingRoom> pageVo) {
		Integer page = pageVo.getPage();
		Integer limitPage = pageVo.getLimitPage();
		Map<String, String> where = pageVo.getWhere2();
		Map<String,String> sort=pageVo.getSort();
		if(page<1){
			page=1;
		}
		Integer offset = (page-1)*limitPage;
		Map<String,Object> map=new HashMap<>();
		map.put("offset", offset);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		map.put("condition", where.get("pageWhere1"));
		map.put("condition2", where.get("pageWhere2"));
		map.put("condition3", where.get("pageWhere3"));
		map.put("condition4", where.get("pageWhere4"));
		map.put("condition5", where.get("pageWhere5"));
		map.put("condition6", where.get("pageWhere6"));
		try {
			Long count = roomDao.queryMeetingroomCount(map);
			List<MeetingRoom> meetingroomLst = roomDao.queryMeetingroomLst(map);
			pageVo.setList(meetingroomLst);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return pageVo;
	}

	/**
	 * TODO 查询所有会议室信息.
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#findAllMeetingroom()
	 */
	@Override
	public List<MeetingRoom> findAllMeetingroom() {
		
		try {
			return roomDao.queryAllMeetingRoom();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * TODO 通过会议室名字查找会议室.
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#queryMeetingRoomByName(java.lang.String)
	 */
	@Override
	public MeetingRoom queryMeetingRoomByName(String name) {
		try {
			return roomDao.queryMeetingRoomByName(name);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}

	/**
	 * 查询所有会议室名称
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#findAllNames()
	 */
	@Override
	public List<String> findAllNames() {
		try {
			List<String> names = roomDao.queryAllNames();
			return names;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有容纳人数
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#findAllNumber()
	 */
	@Override
	public List<Integer> findAllNumber() {
		try {
			List<Integer> list = roomDao.queryAllNumber();
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.clps.mms.booking.meetingroom.service.IMeetingRoomService#findAllAddresses()
	 */
	@Override
	public List<String> findAllAddresses() {
		try {
			List<String> list = roomDao.queryAllAddresses();
			return list;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
