package com.clps.tmp.core.sm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.vo.MenuVo;
import com.clps.tmp.core.sm.vo.RoleVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.MenuService;
import com.clps.tmp.core.sm.service.RoleService;

/**
 *  角色管理
  * @ClassName: RoleAction
  * @Description: TODO
  * @author Seven
  * @date 2015年10月19日 
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("role")
@Results({ 
		@Result(name = "toRoleManagementPage", location = "role/roleManagement.jsp"),
		@Result(name = "toRoleManagementEditRolePage", location = "role/roleManagementEditRole.jsp"),
		@Result(name = "toRoleManagementAddRolePage", location = "role/roleManagementAddRole.jsp"),
		@Result(name = "toRoleManagementUpdateMenuPage", location = "role/roleManagementUpdateMenu.jsp"),
		@Result(name = "toRoleManagementUpdatePersonPage", location = "role/roleManagementUpdatePerson.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class RoleAction extends BaseAction{
	//页面数据
	private RoleVo role;
	private UserVo user;
	// json返回数据map
	private HashMap<String, Object> resultMap;

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public RoleVo getRole() {
		return role;
	}

	public void setRole(RoleVo role) {
		this.role = role;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;
	/**
	 * 跳转角色管理界面 
	 */
	public String toRoleManagementPage()  throws Exception{
		return "toRoleManagementPage";
	}
	/**
	 * 获取角色分页信息 
	 * @throws Exception 
	 */
	public String getRolePage() throws Exception{
		DataTableUtil<RoleVo> dataTableUtil=new DataTableUtil<RoleVo>();
		PageVo<RoleVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(role!=null){
			if(role.getSearchRoleStatus()!=null && !role.getSearchRoleStatus().equals("")){
				where1.put("enable", role.getSearchRoleStatus());
			}
		}
		pageVo.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(role!=null){
			if(role.getSearchRoleName()!=null && !role.getSearchRoleName().equals("")){
				where2.put("name[lang]", role.getSearchRoleName());
			}
		}
		pageVo.setWhere2(where2);
		//3.获取数据	
		pageVo = roleService.queryRolePage(pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 删除角色
	 * @return
	 * @throws Exception
	 */
	public String deleteRole() throws Exception{
		AjaxReturnInfo rtn=null;
		String lang=(String) session.get(SystemConstant.LANG);
		log.info("删除的role id:"+role.getId());
		int count=roleService.deleteRole(role.getId());
		if(count==0)
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"删除失败!":"Delete failed!");
		else
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("成功删除数据"+count+"条!"):"Delete success!");
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转角色编辑界面 
	 */
	public String toRoleManagementEditRolePage() throws Exception{
		log.info("传去修改的role id:"+role.getId());
		role=roleService.getRoleByID(role.getId());
		return "toRoleManagementEditRolePage";
	}
	/**
	 * 更新角色
	 */
	public String updateRole() throws Exception{
		AjaxReturnInfo rtn=null;
		String lang=(String) session.get(SystemConstant.LANG);
		log.info("更新的role id:"+role.getId());
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		role.setUpdate_person(user.getName());
		role.setLast_update_time(role.getUpdate_time());
		role.setUpdate_time(DateTimeUtil.nowToDatabase());
		int count=roleService.updateRole(role);
		if(count==0)
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"更新失败!数据可能已经被其他用户更新,请查看!":"Update failed! The data may be updated by another user, please check~");
		else
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("成功更新数据"+count+"条!"):"Update success!");
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转角色添加界面
	 */
	public String toRoleManagementAddRolePage() throws Exception{
		return "toRoleManagementAddRolePage";
	}
	/**
	 * 角色添加
	 */
	public String addRole() throws Exception{
		String lang=(String) session.get(SystemConstant.LANG);
		AjaxReturnInfo rtn=null;
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		role.setCreate_person(user.getName());
		role.setCreate_time(DateTimeUtil.nowToDatabase());
		role.setUpdate_person(user.getName());
		role.setUpdate_time(DateTimeUtil.nowToDatabase());
		int count=roleService.addRole(role);
		if(count==0)
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"添加失败!":"Add failed!");
		else
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("成功添加数据"+count+"条!"):"Add success!");
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 *  跳转到角色菜单对应修改界面
	 */
	public String toRoleManagementUpdateMenuPage() throws Exception{
		return "toRoleManagementUpdateMenuPage";
	}
	/**
	 * 初始化角色添加菜单界面
	 */
	public String initialRoleAddMenuPage() throws Exception{
		@SuppressWarnings("unused")
		List<MenuVo> li=menuService.queryMenuAndChkRole(role.getId());
		//1.分类
		LinkedHashMap<String,Object> li1 = new LinkedHashMap<String,Object>();
		LinkedHashMap<String,Object> li2= new LinkedHashMap<String,Object>();
		LinkedHashMap<String,Object> li3= new LinkedHashMap<String,Object>();
		for(MenuVo mv : li){
			HashMap<String,Object> m = new HashMap<String,Object>();
			m.put("name", mv.getName()==null?"":mv.getName());
			m.put("pid", mv.getParent_id()==null?"":mv.getParent_id());
			m.put("type", "0");
			m.put("id", mv.getId());
			m.put("checked", mv.getDescription()==null?"":mv.getDescription());
			m.put("order", mv.getOrder()==null?"":mv.getOrder());
			m.put("menu", null);
			if(mv.getLevel().equals("1")){	
				li1.put(mv.getId(), m);
			}else if(mv.getLevel().equals("2")){
				li2.put(mv.getId(), m);
			}else if(mv.getLevel().equals("3")){
				li3.put(mv.getId(), m);
			}
		}
		//2.循环三级菜单
		for (Map.Entry<String, Object> entry : li3.entrySet()) {
			//取出父级id
			HashMap<String,Object> m = (HashMap<String,Object>)entry.getValue();
			String pid = (String)m.get("pid");
			//找出id的menu
			HashMap<String,Object> mp = (HashMap<String,Object>)li2.get(pid);
			if(mp!=null){
				String type = (String)mp.get("type");
				if(type.equals("0")){
					ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();
					al.add(m);
					mp.put("menu", al);
					mp.put("type", "1");
				}else{
					ArrayList<HashMap<String,Object>> al = (ArrayList<HashMap<String,Object>>)mp.get("menu");
					al.add(m);
				}
			}
		}
		//3.循环二级菜单
		for (Map.Entry<String, Object> entry : li2.entrySet()) {
			//取出父级id
			HashMap<String,Object> m = (HashMap<String,Object>)entry.getValue();
			String pid = (String)m.get("pid");
			//找出id的menu
			HashMap<String,Object> mp = (HashMap<String,Object>)li1.get(pid);
			if(mp!=null){
				String type = (String)mp.get("type");
				if(type.equals("0")){
					ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();
					al.add(m);
					mp.put("menu", al);
					mp.put("type", "1");
				}else{
					ArrayList<HashMap<String,Object>> al = (ArrayList<HashMap<String,Object>>)mp.get("menu");
					al.add(m);
				}
			}
		}
		//4.循环一级菜单
		ArrayList<HashMap<String,Object>> al = new ArrayList<HashMap<String,Object>>();//存放所有菜单的List
		for (Map.Entry<String, Object> entry : li1.entrySet()) {
			HashMap<String,Object> m = (HashMap<String,Object>)entry.getValue();
			al.add(m);	
		}	
		//4.写入返回数据
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("menu",al);
		resultMap=rtn.getReturnMap();
		log.info("菜单:"+resultMap.toString());
		return "json";
	}
	/**
	 * 修改角色菜单对应
	 */
	public String updateRoleMenu()throws Exception{
		log.info("修改的role id为:"+role.getId()+" 绑定其的的menu id有:"+role.getDescription());
		AjaxReturnInfo rtn=null;
		String [] menuArr=role.getDescription().split(",");
		Map<String,Object>[] paramMap=new HashMap[menuArr.length];
		if("".equals(role.getDescription())){
			paramMap=null;
		}else{
			for(int i=0;i<menuArr.length;i++){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("role_id", role.getId());
				map.put("menu_id", menuArr[i]);
				paramMap[i]=map;
			}
		}
		int effect=roleService.updateRoleMenu(role.getId(),paramMap);
		String lang=(String) session.get(SystemConstant.LANG);
		rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("修改成功！角色拥有"+effect+"条菜单！"):("Update success! The role have "+effect+" menus."));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转到角色人员对应修改界面
	 */
	public String toRoleManagementUpdatePersonPage()  throws Exception{
		return "toRoleManagementUpdatePersonPage";
	}
	/**
	 * 获取角色人员对应界面分页数据
	 */
	public String getRoleUpdatePersonPage() throws Exception{
		log.info("当前角色ID为:"+role.getId());
		DataTableUtil<UserVo> dataTableUtil=new DataTableUtil<UserVo>();
		PageVo<UserVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(user!=null){
			if(user.getDescription()!=null && !user.getDescription().equals("")){
				where1.put("description", user.getDescription());
			}
		}
		pageVo.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(user!=null){
			if(user.getUsername()!=null && !user.getUsername().equals("")){
				where2.put("username", user.getUsername());
			}
		}
		pageVo.setWhere2(where2);
		//3.获取数据	
		pageVo = roleService.queryRolePersonPage(pageVo,role.getId());
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 更新某角色的用户关联关系
	 * @throws Exception 
	 */
	public String updatePersonsInRole() throws Exception{
		log.info("接收到的role id:"+role.getId());
		log.info("接收到的username:"+user.getUsername());
		log.info("接收到的操作命令:"+(("0").equals(user.getEnable())?"移除":"添加"));
		AjaxReturnInfo rtn=null;
		String [] usernames=user.getUsername().replaceAll("\r|\n", "").split(";");
		Map<String,Object>[] paramMap=new HashMap[usernames.length];
		for(int i=0;i<usernames.length;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("role_id", role.getId());
			map.put("username", usernames[i]);
			paramMap[i]=map;
		}
		//0 删除 1 插入
		String operation="0".equals(user.getEnable())?"delete":("1".equals(user.getEnable())?"insert":"others");
		String lang=(String) session.get(SystemConstant.LANG);
		if("others".equals(operation))
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"操作命令有误，请核实！":"Operation wrong! Please check!");
		else{
			int effect=roleService.updatePersonsInRole(paramMap,operation);
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?"操作成功！":"Update success!");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
}
