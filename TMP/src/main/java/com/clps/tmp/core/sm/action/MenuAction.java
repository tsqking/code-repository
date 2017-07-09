package com.clps.tmp.core.sm.action;

import java.util.HashMap;
import java.util.List;

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
import com.clps.tmp.core.common.util.BeanUtil;
import com.clps.tmp.core.sm.vo.MenuVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.MenuService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单管理action
 * system/menu!toMenuManagementPage.do
  * @ClassName: MenuAction
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月10日 下午2:11:48
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("menu")
@Results({ @Result(name = "toMenuManagementPage", location = "menu/menuManagement.jsp"),
		@Result(name = "toMenuDetail", location = "menu/menuDetail.jsp"),
		@Result(name = "toMenuUpdate", location = "menu/menuUpdate.jsp"),
		@Result(name = "toMenuAdd", location = "menu/menuAdd.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class MenuAction extends BaseAction implements ModelDriven {

	private MenuVo menu;
	// json返回数据map
	private HashMap<String, Object> resultMap;

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public Object getModel() {
		if (menu == null) {
			menu = new MenuVo();
		}
		return menu;
	}
	

	public MenuVo getMenu() {
		return menu;
	}

	public void setMenu(MenuVo menu) {
		this.menu = menu;
	}


	@Resource
	private MenuService menuService;
	
	/**
	 * 跳转菜单管理页面
	  * toMenuManagementPage(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: toMenuManagementPage
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toMenuManagementPage(){
		return "toMenuManagementPage";
	}
		
	
	/**
	 * @throws Exception 
	 * 菜单分页1-获得所有菜单id
	  * getAllMenuId(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: getAllMenuId
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getMenuPage() throws Exception{
		DataTableUtil<MenuVo> du = new DataTableUtil<MenuVo>();
		PageVo<MenuVo> pv = du.getTableData(this.getHttpRequest());
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(menu.getSearchMenuLevel()!=null&&!menu.getSearchMenuLevel().equals("")){
			where1.put("level", menu.getSearchMenuLevel());
		}
		if(menu.getSearchMenuStatus()!=null&&!menu.getSearchMenuStatus().equals("")){
			where1.put("enable", menu.getSearchMenuStatus());
		}
		if(menu.getSearchParentMenu()!=null&&!menu.getSearchParentMenu().equals("")){
			where1.put("parent_id", menu.getSearchParentMenu());
		}
		pv.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(menu.getSearchMenuName()!=null&&!menu.getSearchMenuName().equals("")){
			where2.put("name", menu.getSearchMenuName());
		}
		if(menu.getSearchMenuDesc()!=null&&!menu.getSearchMenuDesc().equals("")){
			where2.put("description", menu.getSearchMenuDesc());
		}
		if(menu.getSearchMenuUrl()!=null&&!menu.getSearchMenuUrl().equals("")){
			where2.put("url", menu.getSearchMenuUrl());
		}
		pv.setWhere2(where2);
		//3.获取数据	
		pv = menuService.queryMenuPage(pv);
		//4.返回值
		resultMap = du.getReturnMap(pv);		
		return "json";
	}
	/**
	 * 根据level获取菜单，用于下拉框
	 * @return
	 * @throws Exception
	 */
	public String getMenuByLevel() throws Exception{
		AjaxReturnInfo rtn=null;
		String level=this.httpRequest.getParameter("level");
		List<SelectVo> list=menuService.getMenuByLevel(level);
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	
	/**
	 * @throws Exception 
	 * 删除菜单
	  * deleteMenu(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: deleteMenu
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String deleteMenu() throws Exception{
		boolean re = menuService.deleteMenu(menu.getId());
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("0000");//删除成功
		}else{
			rtn.setMessage("1111");//存在关联，不能删除
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * 详细信息查询
	  * detailMenu(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: detailMenu
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String detailMenu() throws Exception{
		MenuVo mv = menuService.detailMenu(menu.getId());
		mv.setCreate_time(DateTimeUtil.databaseToSystem(mv.getCreate_time()));
		mv.setUpdate_time(DateTimeUtil.databaseToSystem(mv.getUpdate_time()));
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("info", BeanUtil.changeBean(mv));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 跳转页面菜单
	  * toMenuDetail(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  * @Title: toMenuDetail
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toMenuDetail()throws Exception{
		return "toMenuDetail";
	}
	
	/**
	 * 跳转菜单编辑界面
	  * toMenuUpdate(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toMenuUpdate
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toMenuUpdate()throws Exception{
		return "toMenuUpdate";
	}
	
	/**
	 * 跳转菜单添加界面
	  * toMenuAdd(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toMenuAdd
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String toMenuAdd()throws Exception{
		return "toMenuAdd";
	}
	
	
	/**
	 * 跟新菜单信息
	  * updateMenuInfo(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: updateMenuInfo
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String updateMenuInfo()throws Exception{
		UserVo user = (UserVo)this.session.get(SystemConstant.USER);
		boolean re = menuService.updateMenu(menu, user);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setSuccess(true);
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 
	  * getMenuByOrder(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getMenuByOrder
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getMenuByOrder()throws Exception{
		List<SelectVo> li = menuService.queryMenuOrderByLevel(menu.getLevel(), menu.getId());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("options", li);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	
	/**
	 * 添加菜单
	  * addMenu(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: addMenu
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String addMenu()throws Exception{
		UserVo userNow = (UserVo)this.session.get(SystemConstant.USER);
		boolean re = menuService.addMenuToParentMenu(menu, userNow);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setSuccess(true);
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
}
