/**
 * Project Name:clps_mms_01
 * File Name:MenuAction.java
 * Package Name:com.clps.mms.sys.action
 * Date:2017年2月8日下午5:55:15
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.clps.mms.sys.menu.dao.MenuDao;
import com.clps.mms.sys.menu.model.Menu;
import com.clps.mms.sys.menu.service.IMenuService;
import com.clps.mms.sys.user.model.UserInfo;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DataTableHelper;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.common.AjaxReturnInfo;
import com.clps.mms.util.exception.MenuServiceException;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:MenuAction <br/>
 * Function: TODO 菜单action层. <br/>
 * Date: 2017年2月8日 下午5:55:15 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
@SuppressWarnings("all")
public class MenuAction extends BaseAction implements ModelDriven<Menu> {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	private Menu menuInfo = null;
	@Autowired
	private IMenuService service = null;
	private Map<String, Object> resultMap;
	@Autowired
	private MenuDao menuDao;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public Menu getModel() {
		if (menuInfo == null) {
			menuInfo = new Menu();
		}
		return menuInfo;
	}

	/**
	 * getMenuInfoLst:(菜单查询列表). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String getMenuInfoLst() throws Exception {
		System.out.println("进入菜单列表查询服务");
		DataTableHelper<Menu> dataTableHelper = new DataTableHelper<>();
		PageVo<Menu> pageVo = dataTableHelper.getTableData(this.httpRequest);
		System.out.println(pageVo.toString());
		pageVo = service.findMenuInfoLst(pageVo);
		resultMap = dataTableHelper.getReturnMap(pageVo);

		List<Menu> allMenu = menuDao.queryAllMenu();
		session.put("allMenu", allMenu);

		/*IconUtil iconUtil = new IconUtil();
		List<String> allIcon = iconUtil.getAllIcon();
		session.put("allIcon", allIcon);*/

		System.out.println(pageVo.toString());
		return "json";
	}

	/**
	 * add:(新增菜单). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String add() throws Exception {
		System.out.println("进入菜单插入服务");
		AjaxReturnInfo ari = null;
		String managerName = this.session.get("username").toString();
		menuInfo.setMnu_create_time(DateFormat.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		menuInfo.setMnu_create_user(managerName);
		menuInfo.setMnu_is_enable(1);
		if (service.insertService(menuInfo)) {
			ari = AjaxReturnInfo.success("成功");
		} else {
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "input";
	}

	/**
	 * update:(修改菜单). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String update() throws Exception {
		System.out.println("进入菜单修改服务");
		System.out.println("menuInfo:" + menuInfo);
		String managerName = this.session.get("username").toString();
		menuInfo.setMnu_update_time(DateFormat.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		menuInfo.setMnu_update_user(managerName);
		AjaxReturnInfo ari = null;
		if (service.updateServiceById(menuInfo)) {
			ari = AjaxReturnInfo.success("成功");
		} else {
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "input";
	}

	/**
	 * findMenuById:(根据id查找菜单). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String findMenuById() throws Exception {
		System.out.println("进入菜单查找服务");
		AjaxReturnInfo ari = null;
		System.out.println("menuId:" + menuInfo.getMnu_id());
		Menu findMenu = service.findMenuById(menuInfo.getMnu_id());
		System.out.println(findMenu);
		if (findMenu != null) {
			ari = AjaxReturnInfo.success("成功");
			ari.add("menuInfo", findMenu);
		} else {
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "json";
	}

	/**
	 * delete:(根据id删除菜单). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public String delete() throws Exception {
		AjaxReturnInfo ari = null;
		if (service.deleteService(menuInfo.getMnu_id())) {
			ari = AjaxReturnInfo.success("成功");
		} else {
			ari = AjaxReturnInfo.failed("失败");
		}
		resultMap = ari.getReturnMap();
		return "json";

	}
}
