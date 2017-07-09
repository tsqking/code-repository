 package com.clps.tmp.core.sm.service;


import java.util.List;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.MenuVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * 菜单相关服务
  * @ClassName: MenuService
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月9日 下午4:03:23
 */
public interface MenuService {
		
	//根据用户查找菜单
	public List<MenuVo> queryUserMenu(UserVo user) throws Exception;
	
	//菜单翻页 where为查询条件 ,where2为模糊查询条件
	public PageVo<MenuVo> queryMenuPage(PageVo<MenuVo> pv) throws Exception;
	
	//获得菜单名
	public String queryMenuNameById(String menuId)throws Exception;

	//根据level查询Menu
	public List<SelectVo> getMenuByLevel(String level) throws Exception;
	
	//根据id删除菜单
	public boolean deleteMenu(String menuId) throws Exception;
	
	//根据id查找菜单信息
	public MenuVo detailMenu(String menuId) throws Exception;
	
	//更新菜单信息
	public boolean updateMenu(MenuVo mv,UserVo user) throws Exception;
	
	//同等级菜单排序下拉框
	public List<SelectVo> queryMenuOrderByLevel(String level,String id)throws Exception;
	
	//添加菜单
	public boolean addMenuToParentMenu(MenuVo mv,UserVo user) throws Exception;
	
	/**
	 * 获取所有菜单，并检查某角色是否拥有
	 */
	public List<MenuVo> queryMenuAndChkRole(String roleId) throws Exception;
}
