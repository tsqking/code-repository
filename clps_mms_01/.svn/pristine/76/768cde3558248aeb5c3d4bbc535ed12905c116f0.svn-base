
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:RoleAction.java
 * Package Name:com.clps.mms.sys.action
 * Date:2017年2月7日上午10:09:34
 *
 */
package com.clps.mms.sys.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.clps.mms.sys.role.model.RoleInfo;
import com.clps.mms.sys.role.service.RoleService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.common.AjaxReturnInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName: RoleAction.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2017年1月3日 上午10:09:34 
 *
 * @author lonnie
 * @version 
 * 
 */
@Controller
public class RoleAction extends BaseAction implements ModelDriven<RoleInfo>{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
	private RoleInfo roleInfo=null;
	@Resource(name="roleService")
	private RoleService service=null;
	private Map<String, Object> resultMap;
	
    /**
     *  
     * getRoleInfoLst:获得角色信息列表. 
     * @return
     * @throws Exception
     */
	public String getRoleInfoLst() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		System.out.println("进入服务111111111");
		DataTableHelper<RoleInfo> dataTableHelper=new DataTableHelper<>();
		PageVo<RoleInfo> pageVo=dataTableHelper.getTableData(this.httpRequest);
//		System.out.println(pageVo.toString());
		pageVo=service.findRoleInfoLst(pageVo);
		resultMap=dataTableHelper.getReturnMap(pageVo);
		List<RoleInfo> list = service.findAllRoleInfo();
		
//		List<RoleInfo> list = pageVo.getList();
		
		for(RoleInfo L:list){
			map.put(L.getCreate_name(), L.getCreate_name());
		}
//		List<RoleInfo> list = (List<RoleInfo>) resultMap.get("Rows");
		session.put("list", list);
		session.put("map", map);
		session.put("date", DateFormat.format(new Date(), DateFormat.FORMAT_SHORT));
		return "json";
	}
	
	/**
	 * 
	 * add:添加角色. 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("进入服务222222222222");
		AjaxReturnInfo ari=null;
		String managerName=this.session.get("username").toString();
		roleInfo.setCreate_name(managerName);
		if (service.addRoleInfo(roleInfo)) {
			ari=AjaxReturnInfo.success("成功");
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
		resultMap=ari.getReturnMap();
		return "input";
	}
	/**
	 * 
	 * update:更新角色. 
	 * @return
	 * @throws Exception
	 */
	public String update()throws Exception{
		System.out.println("进入服务333333333333");
		System.out.println("RoleInfo:"+roleInfo);
		AjaxReturnInfo ari=null;
		String managerName=this.session.get("username").toString();
		roleInfo.setUpdate_name(managerName);
		System.out.println("managerName:"+managerName);
		if (service.updateRoleInfoById(roleInfo)) {
			ari=AjaxReturnInfo.success("成功");
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
		resultMap=ari.getReturnMap();
		return "input";
	}
    public String findByName()throws Exception{
    	System.out.println("进入服务444444444444");
    	AjaxReturnInfo ari=null;
    	RoleInfo tempRoleInfo=service.findRoleInfoByName(roleInfo.getName());
    	if (tempRoleInfo!=null) {
			ari=AjaxReturnInfo.success("成功");
			ari.add("roleInfo", tempRoleInfo);
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
    	resultMap=ari.getReturnMap();
    	return "json";
    }
    public String delete()throws Exception{
    	AjaxReturnInfo ari=null;
    	if(service.deleteRoleInfoById(this.session.get("username").toString(), roleInfo.getName())){
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
	public RoleInfo getModel() {
		if (roleInfo==null) {
			roleInfo=new RoleInfo();
		}
		return roleInfo;
	}


}

