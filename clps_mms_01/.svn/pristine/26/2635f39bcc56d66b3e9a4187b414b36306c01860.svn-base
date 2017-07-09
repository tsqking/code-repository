/**
 * Project Name:clps_mms_01
 * File Name:MeetingroomAction.java
 * Package Name:com.clps.mms.booking.action
 * Date:2017年2月8日下午2:15:33
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:MeetingroomAction.java
 * Package Name:com.clps.mms.booking.action
 * Date:2017年2月8日下午2:15:33
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.clps.mms.booking.meetingroom.model.MeetingRoom;
import com.clps.mms.booking.meetingroom.service.IMeetingRoomService;
import com.clps.mms.sys.action.BaseAction;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.common.AjaxReturnInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:MeetingroomAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年2月8日 下午2:15:33 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: MeetingroomAction <br/>

 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年2月8日 下午2:15:33 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
@Controller
public class MeetingroomAction extends BaseAction implements ModelDriven<MeetingRoom>{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	MeetingRoom room = null;
	@Resource(name="meetingRoomService")
	private IMeetingRoomService service = null;
	private Map<String, Object> resultMap;
	
	
	/**
	 * Creates a new instance of MeetingroomAction.
	 *
	 */
	
	public MeetingroomAction() {}

	/**
	 * resultMap.
	 *
	 * @return  the resultMap
	 * @since   JDK 1.8
	 */
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	/**
	 * resultMap.
	 *
	 * @param   resultMap    the resultMap to set
	 * @since   JDK 1.8
	 */
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * 
	 * getMeetingroomLst:(获得会议室信息列表). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String getMeetingroomLst() throws Exception{
		System.out.println("进入服务");
		DataTableHelper<MeetingRoom> dataTableHelper = new DataTableHelper<>();
		PageVo<MeetingRoom> pageVo = dataTableHelper.getTableData(this.httpRequest);
		List<String> names2 = service.findAllNames();
		List<Integer> numbers = service.findAllNumber();
		List<String> addresses = service.findAllAddresses();
		session.put("names2", names2);
		session.put("numbers", numbers);
		session.put("addresses", addresses);
		session.put("endDate",DateFormat.format(new Date(), DateFormat.FORMAT_SHORT));
		pageVo = service.findMeetingroomLst(pageVo);
		resultMap = dataTableHelper.getReturnMap(pageVo);
		System.out.println(pageVo.toString());
		return "json";
	}
	/**
	 * 
	 * add:(添加会议室). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String add() throws Exception{
		System.out.println("进入插入服务");
		AjaxReturnInfo ari = null;
		String managername = this.session.get("username").toString();
		room.setCreateName(managername);
		room.setIsEnable(1);
		Date date=new Date();
		room.setCreateDate(DateFormat.format(date));
		if(service.insertMeetingRoom(room)){
			ari = AjaxReturnInfo.success("成功");
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "input";
	}
	
	public String update() throws Exception{
		System.out.println("进入服务");
		System.out.println("room:"+room);
		AjaxReturnInfo ari = null;
		if(service.updateMeetingRoomById(room)){
			ari = AjaxReturnInfo.success("成功");
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "input";
	}

	public String findByName() throws Exception{
		System.out.println("进入服务findByName");
		AjaxReturnInfo ari = null;
		System.out.println("roomName:"+room.getRoomName());
		MeetingRoom tempRoom = service.queryMeetingRoomByName(room.getRoomName());
		System.out.println(tempRoom);
		if(tempRoom!=null){
			ari = AjaxReturnInfo.success("成功");
			ari.add("room", tempRoom);
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "json";
	}
	
	public String delete() throws Exception{
		AjaxReturnInfo ari = null;
		if(service.deleteMeetingRoomById(room.getRoomName())){
			ari = AjaxReturnInfo.success("成功");
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap= ari.getReturnMap();
		return "json";
	}
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public MeetingRoom getModel() {
		if (room==null) {
			room = new MeetingRoom();
		}
		return room;
	}

}

