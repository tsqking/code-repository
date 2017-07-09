/**
 * Project Name:clps_mms_01
 * File Name:IMenuService.java
 * Package Name:com.clps.mms.sys.menu.service
 * Date:2017年1月12日下午11:12:24
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.menu.service;

import java.util.List;
import com.clps.mms.sys.menu.model.Menu;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.exception.MenuServiceException;

/**
 * ClassName:IMenuService <br/>
 * Function: TODO 菜单接口. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年1月12日 下午11:12:24 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface IMenuService {
	/**
	 * insertService:(插入菜单). <br/>
	 *
	 * @author Charles
	 * @param menu
	 * @return 
	 * @throws MenuServiceException
	 * @since JDK 1.8
	 */
	boolean insertService(Menu menu) throws MenuServiceException;

	/**
	 * deleteService:(根据名字删除菜单). <br/>
	 *
	 * @author Charles
	 * @param name
	 * @return 
	 * @throws MenuServiceException
	 * @since JDK 1.8
	 */
	boolean deleteService(String id) throws MenuServiceException;

	/**
	 * updateService:(根据名字修改菜单). <br/>
	 *
	 * @author Charles
	 * @param menu
	 * @throws MenuServiceException
	 * @since JDK 1.8
	 */
	boolean updateServiceById(Menu menu) throws MenuServiceException;

	/**
	 * findMenuById:(根据Id查找菜单). <br/>
	 * 
	 * @author Charles
	 * @param string
	 * @return
	 * @throws MenuServiceException
	 * @since JDK 1.8
	 */
	Menu findMenuById(String id) throws MenuServiceException;

	/**
	 * findMenuInfoLst:(分页列表查询). <br/>
	 * @author Charles
	 * @param pageVo
	 * @return
	 * @throws MenuServiceException
	 * @since JDK 1.8
	 */
	PageVo<Menu> findMenuInfoLst(PageVo<Menu> pageVo) throws MenuServiceException;
	
	/**
	 * getRootList:(获取所有根节点菜单). <br/>
	 * @author Charles
	 * @return
	 * @since JDK 1.8
	 */
	List<Menu> getRootList();
	
	/**
	 * getChildrenList:(获取所有子节点菜单). <br/>
	 * @author Charles
	 * @return
	 * @since JDK 1.8
	 */
	List<Menu> getChildrenList();
	
	/**
	 * getOrgList:(查出所有组织好数据的菜单). <br/>
	 * @author Charles
	 * @return
	 * @since JDK 1.8
	 */
	List<Menu> getOrgList();
	
}
