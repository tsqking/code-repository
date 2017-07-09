/**
 * Project Name:clps_mms_01
 * File Name:MenuServiceImpl.java
 * Package Name:com.clps.mms.sys.menu.service.impl
 * Date:2017年1月12日下午11:16:02
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clps.mms.sys.menu.dao.MenuDao;
import com.clps.mms.sys.menu.model.Menu;
import com.clps.mms.sys.menu.service.IMenuService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.MenuServiceException;

/**
 * ClassName:MenuServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年1月12日 下午11:16:02 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private MenuDao menuDao;
	Logger logger = Logger.getRootLogger();

	/**
	 * TODO 删除菜单（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#deleteService(java.lang.String)
	 */
	@Override
	public boolean deleteService(String id) throws MenuServiceException {
		Menu findMenu = this.findMenuById(id);
		if (findMenu != null) {
			try {
				return menuDao.deleteMenuById(findMenu.getMnu_id());
			} catch (DataAccessException e) {

				e.printStackTrace();
			}
		} else {
			logger.error(MenuServiceException.MENU_NOT_EXIST);
			throw new MenuServiceException(MenuServiceException.MENU_NOT_EXIST);
		}
		return false;

	}

	
	/**
	 * TODO  通过id查找菜单（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#findMenuById(java.lang.String)
	 */
	@Override
	public Menu findMenuById(String id) throws MenuServiceException {
		try {
			return menuDao.findMenuById(id);
		} catch (DataAccessException e) {

			e.printStackTrace();

		}
		return null;

	}

	
	/**
	 * TODO  菜单查询列表（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#findMenuInfoLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<Menu> findMenuInfoLst(PageVo<Menu> pageVo) throws MenuServiceException {
		Integer page = pageVo.getPage();
		Integer limitPage = pageVo.getLimitPage();
		if (page < 1) {
			page = 1;
		}
		Integer offset = (page - 1) * limitPage;
		Map<String, String> where = pageVo.getWhere2();
		Map<String, String> sort = pageVo.getSort();
		Map<String, Object> map = new HashMap<>();
		map.put("offset", offset);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		try {
			Long count = menuDao.queryMenuInfoCount(map);
			List<Menu> menuList = menuDao.queryMenuInfoLst(map);
			pageVo.setList(menuList);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return pageVo;
	}

	
	/**
	 * TODO  插入菜单（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#insertService(com.clps.mms.sys.menu.model.Menu)
	 */
	@Override
	public boolean insertService(Menu menu) throws MenuServiceException {
		if (this.findMenuById(menu.getMnu_id()) != null) {
			logger.error(MenuServiceException.MENU_EXIST);
			// throw new MenuServiceException(MenuServiceException.MENU_EXIST);
		} else {
			try {
				return menuDao.insertMenu(menu);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	
	/**
	 * TODO  更新菜单（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#updateServiceById(com.clps.mms.sys.menu.model.Menu)
	 */
	@Override
	public boolean updateServiceById(Menu menu) throws MenuServiceException {
		Menu findMenu = this.findMenuById(menu.getMnu_id());
		if (findMenu != null) {
			try {
				return menuDao.updateMenuById(menu);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		} else {
			logger.error(MenuServiceException.MENU_NOT_EXIST);
			throw new MenuServiceException(MenuServiceException.MENU_NOT_EXIST);
		}
		return false;
	}

	
	/**
	 * TODO  获得所有根节点菜单（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#getRootList()
	 */
	public List<Menu> getRootList() {
		try {
			List<Menu> menuList = menuDao.queryAllMenu();// 查出所有的菜单
			List<Menu> parentMenuList = new ArrayList<Menu>(); // 取出父亲结点的菜单
			if (menuList != null && menuList.size() > 0) {
				Iterator<Menu> menuIterator = menuList.iterator();
				while (menuIterator.hasNext()) {
					Menu menu = menuIterator.next();
					if (StringUtils.isEmpty(menu.getMnu_parent_id())) {
						parentMenuList.add(menu);// 得到所有父节点菜单
					}
				}
			}
			return parentMenuList;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * TODO  获得子菜单列表（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#getChildrenList()
	 */
	public List<Menu> getChildrenList() {
		try {
			List<Menu> menuList = menuDao.queryAllMenu();
			List<Menu> childrenMenuList = new ArrayList<Menu>(); // 取出所有子菜单
			if (menuList != null && menuList.size() > 0) {
				Iterator<Menu> mIterator = menuList.iterator();
				while (mIterator.hasNext()) {
					Menu menu = mIterator.next();
					if (StringUtils.isNotEmpty(menu.getMnu_parent_id())) {
						childrenMenuList.add(menu);
					}
				}
			}
			return childrenMenuList;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * TODO 查出所有的菜单（可选）.
	 * @see com.clps.mms.sys.menu.service.IMenuService#getOrgList()
	 */
	public List<Menu> getOrgList() {
		List<Menu> parentList = getRootList();
		List<Menu> childList = getChildrenList();

		for (Menu rMenu : parentList) {
			List<Menu> childrenList = new ArrayList<>();
			Menu rootMenu = rMenu;
			for (Menu cMenu : childList) {
				Menu childMenu = cMenu;
				if (childMenu.getMnu_parent_id().equals(String.valueOf(rootMenu.getMnu_id()))) {
					childrenList.add(childMenu);// 找出匹配根节点的子菜单添加到集合中
					rootMenu.setChildren(childrenList);// 设置子节点
				}
			}
		}
		return parentList;
	}

}
