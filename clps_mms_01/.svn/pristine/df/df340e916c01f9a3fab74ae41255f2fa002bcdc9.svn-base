/**
 * Project Name:clps_mms_01
 * File Name:DepartmentAction.java
 * Package Name:com.clps.mms.sys.action
 * Date:2017年2月8日上午11:33:26
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:DepartmentAction.java
 * Package Name:com.clps.mms.sys.action
 * Date:2017年2月8日上午11:33:26
 * Copyright (c) 2017, ruby@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.action;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.clps.mms.sys.department.model.DepartmentInfo;
import com.clps.mms.sys.department.service.IDepartmentService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.common.AjaxReturnInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:DepartmentAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年2月8日 上午11:33:26 <br/> v
 * @author   ruby
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
/**
 * ClassName: DepartmentAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年2月8日 上午11:33:26 <br/>
 *
 * @author ruby.zhao
 * @version 
 * @since JDK 1.8
 */
public class DepartmentAction extends BaseAction implements ModelDriven<DepartmentInfo>{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DepartmentInfo departmentInfo=null;
	@Resource(name="departmentService")
	private IDepartmentService service=null;
	private Map<String, Object> resultMap;

	
    /**
     *  
     * getDepartmentInfoLst:获得部门信息列表. 
     * @return
     * @throws Exception
     */
	public String getDepartmentInfoLst() throws Exception{
		System.out.println("进入服务");
		DataTableHelper<DepartmentInfo> dataTableHelper=new DataTableHelper<>();
		PageVo<DepartmentInfo> pageVo=dataTableHelper.getTableData(this.httpRequest);
		System.out.println(pageVo.toString());
		pageVo=service.findDepartmentInfoLst(pageVo);
//		List<DepartmentInfo> list = pageVo.getList();
		List<DepartmentInfo> list = service.findAllDepartmentInfo();
		Map<String,String> map= new HashMap<String,String>();
		for (DepartmentInfo departmentInfo : list) {
			map.put(departmentInfo.getName(),departmentInfo.getName());
		}
		session.put("list",list);
		session.put("map", map);
		resultMap=dataTableHelper.getReturnMap(pageVo);
		System.out.println(pageVo.toString());
		return "json";
	}
	/**
	 * 
	 * add:添加部门. 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("进入服务");
		AjaxReturnInfo ari=null;
		String managerName=this.session.get("username").toString();
		departmentInfo.setCreatedate(DateFormat.format(new Date()));
		departmentInfo.setCreatename(managerName);
		departmentInfo.setUpdatename(managerName);
		if (service.addDepartmentInfo(departmentInfo)) {
			ari=AjaxReturnInfo.success("成功");
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
		resultMap=ari.getReturnMap();
		return "input";
	}
	/**
	 * 
	 * update:更新部门. 
	 * @return
	 * @throws Exception
	 */
	public String update()throws Exception{
		System.out.println("进入服务");
		System.out.println("departmentInfo:"+departmentInfo);
		String managerName=this.session.get("username").toString();
		departmentInfo.setUpdatedate(DateFormat.format(new Date()));
		departmentInfo.setUpdatename(managerName);
		AjaxReturnInfo ari=null;
		if (service.updateDepartmentInfoById(departmentInfo)) {
			ari=AjaxReturnInfo.success("成功");
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
		resultMap=ari.getReturnMap();
		return "input";
	}
    public String findById()throws Exception{
    	System.out.println("进入服务");
    	AjaxReturnInfo ari=null;
    	DepartmentInfo tempDepartmentInfo=service.findDepartmentInfoById(departmentInfo.getId());
    	if (tempDepartmentInfo!=null) {
			ari=AjaxReturnInfo.success("成功");
			ari.add("departmentInfo", tempDepartmentInfo);
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
    	resultMap=ari.getReturnMap();
    	return "json";
    }
    public String delete()throws Exception{
    	AjaxReturnInfo ari=null;
    	if(service.deleteDepartmentInfoById(departmentInfo.getId())){
    		ari=AjaxReturnInfo.success("成功");
    	}else{
			ari=AjaxReturnInfo.failed("失败");
		}
    	resultMap=ari.getReturnMap();
    	return "json";
    }
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public DepartmentInfo getModel() {
		if (departmentInfo==null) {
			departmentInfo=new DepartmentInfo();
		}
		return departmentInfo;
	}
	
	/*
	//根据name查找，校验名称不能相同
	public String findByName() throws Exception{
		AjaxReturnInfo ari=null;
		DepartmentInfo tempDept = service.findDepartmentInfoByName(departmentInfo.getName());
		if( tempDept !=null){
			ari=AjaxReturnInfo.success("有相同的部门名称");
			ari.add("department", tempDept);
		} else {
			ari=AjaxReturnInfo.failed("无相同的部门名称");
		}
		resultMap = ari.getReturnMap();
		System.out.println("----------"+resultMap);
		return "json";
	}*/

}

