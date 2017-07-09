/**
 * Project Name:clps_mms_01
 * File Name:EquipmentAction.java
 * Package Name:com.clps.mms.booking.action
 * Date:2017年2月13日下午3:29:54
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:EquipmentAction.java
 * Package Name:com.clps.mms.booking.action
 * Date:2017年2月13日下午3:29:54
 * Copyright (c) 2017, lacus@163.com All Rights Reserved.
 *
 */

package com.clps.mms.booking.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.clps.mms.booking.meetingroom.model.Equipment;
import com.clps.mms.booking.meetingroom.service.IEquipmentService;
import com.clps.mms.sys.action.BaseAction;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.common.AjaxReturnInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:EquipmentAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年2月13日 下午3:29:54 <br/>
 * @author   lacus.wang
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: EquipmentAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年2月13日 下午3:29:54 <br/>
 *
 * @author lacus.wang
 * @version 
 * @since JDK 1.8
 */
@Controller
public class EquipmentAction extends BaseAction implements ModelDriven<Equipment>{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	Equipment eqp = null;
	@Resource(name="equipmentService")
	private IEquipmentService service = null;
	private Map<String, Object> resultMap;
	

	/**
	 * 
	 * getEquipmentLst:(显示设备信息列表). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String getEquipmentLst() throws Exception{
		System.out.println("进入服务");
		DataTableHelper<Equipment> dataTableHelper = new DataTableHelper<>();
		PageVo<Equipment> pageVo = dataTableHelper.getTableData(this.httpRequest);
		List<String> list = service.findAllType();
		List<String> names = service.findAllNames();
		session.put("list", list);
		session.put("names", names);
		session.put("endDate",DateFormat.format(new Date(), DateFormat.FORMAT_SHORT));
		pageVo = service.findEquipmentLst(pageVo);
		/*List<Equipment> list = pageVo.getList();
		session.put("list", list);*/
		resultMap = dataTableHelper.getReturnMap(pageVo);
		System.out.println(pageVo.toString());
		return "json";
	}
	/**
	 * 
	 * add:(增加设备). <br/>
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
		eqp.setCreateName(managername);
		eqp.setIsEnable(1);
		Date date=new Date();
		eqp.setCreateDate(DateFormat.format(date));

		if(service.addEquipment(eqp)){
			ari = AjaxReturnInfo.success("成功");
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "input";
	}
	/**
	 * 
	 * update:(修改设备). <br/>
	 * @author lacus.wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String update() throws Exception{
		System.out.println("进入服务");
		System.out.println("equipment:"+eqp);
		AjaxReturnInfo ari = null;
		String managername = this.session.get("username").toString();
		eqp.setUpdateName(managername);
		Date date=new Date();
		eqp.setUpdateDate(DateFormat.format(date));
		
		if(service.updateEquipmentById(eqp)){
			ari = AjaxReturnInfo.success("成功");
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "input";
	}
	/**
	 * 
	 * findByName:(根据名称查询设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String findByName() throws Exception{
		System.out.println("进入服务findByName");
		AjaxReturnInfo ari = null;
		System.out.println("eqpName:"+eqp.getEquipmentName());
		Equipment tempEqp = service.queryEquipmentByName(eqp.getEquipmentName());
		System.out.println(tempEqp);
		if(tempEqp!=null){
			ari = AjaxReturnInfo.success("成功");
			ari.add("eqp", tempEqp);
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "json";
	}
	/**
	 * 
	 * delete:(删除设备信息). <br/>
	 *
	 * @author lacus.wang
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String delete() throws Exception{
		AjaxReturnInfo ari = null;
		//if(service.deleteMeetingRoomById(room.getRoomName())){
		if(service.deleteEquipmentById(eqp.getEquipmentName())){
			ari = AjaxReturnInfo.success("成功");
		}else{
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap= ari.getReturnMap();
		return "json";
	}
	
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
	 * Creates a new instance of EquipmentAction.
	 *
	 */
	
	public EquipmentAction() {}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Equipment getModel() {
		if (eqp==null) {
			eqp = new Equipment();
		}
		return eqp;
	}
	
	
	
	
	
	

}

