
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserAction.java
 * Package Name:com.clps.mms.sys.action
 * Date:2017年1月3日上午10:09:34
 * Copyright (c) 2017, tmbasama@163.com All Rights Reserved.
 *
 */
package com.clps.mms.sys.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.user.service.IUserService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.clps.mms.util.common.AjaxReturnInfo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName: UserAction.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2017年1月3日 上午10:09:34 
 *
 * @author tony.tan
 * @version 
 * 
 */
@Controller
public class UserAction extends BaseAction implements ModelDriven<UserInfo>{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 1L;
	private UserInfo userInfo=null;
	@Resource(name="userService")
	private IUserService service=null;
	private Map<String, Object> resultMap;
	
    /**
     *  
     * getUserInfoLst:获得用户信息列表. 
     * @return
     * @throws Exception
     */
	public String getUserInfoLst() throws Exception{
		System.out.println("进入服务");
		DataTableHelper<UserInfo> dataTableHelper=new DataTableHelper<>();
		PageVo<UserInfo> pageVo=dataTableHelper.getTableData(this.httpRequest);
		System.out.println(pageVo.toString());
		pageVo=service.findUserInfoLst(pageVo);
		resultMap=dataTableHelper.getReturnMap(pageVo);
		System.out.println(pageVo.toString());
		return "json";
	}
	/**
	 * 
	 * add:添加用户. 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		System.out.println("进入服务");
		AjaxReturnInfo ari=null;
		String managerName=this.session.get("username").toString();
		userInfo.setUpdatename(managerName);
		userInfo.setCreatename(managerName);
		if (service.register(userInfo)) {
			ari=AjaxReturnInfo.success("成功");
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
		resultMap=ari.getReturnMap();
		return "input";
	}
	/**
	 * 
	 * update:更新用户. 
	 * @return
	 * @throws Exception
	 */
	public String update()throws Exception{
		System.out.println("进入服务");
		System.out.println("userInfo:"+userInfo);
		AjaxReturnInfo ari=null;
		if (service.updateUserInfoById(userInfo)) {
			ari=AjaxReturnInfo.success("成功");
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
		resultMap=ari.getReturnMap();
		return "input";
	}
    public String findByName()throws Exception{
    	System.out.println("进入服务");
    	AjaxReturnInfo ari=null;
    	System.out.println("username:"+userInfo.getName());
    	UserInfo tempUserInfo=service.findUserInfoByName(userInfo.getName());
    	System.out.println(tempUserInfo);
    	if (tempUserInfo!=null) {
			ari=AjaxReturnInfo.success("成功");
			ari.add("userInfo", tempUserInfo);
		}else{
			ari=AjaxReturnInfo.failed("失败");
		}
    	resultMap=ari.getReturnMap();
    	return "json";
    }
    public String delete()throws Exception{
    	AjaxReturnInfo ari=null;
    	if(service.deleteUserInfoById(this.session.get("username").toString(), userInfo.getName())){
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
	public UserInfo getModel() {
		if (userInfo==null) {
			userInfo=new UserInfo();
		}
		return userInfo;
	}

}

