/**
 * Project Name:clps_mms_01
 * File Name:MenuDao.java
 * Package Name:com.clps.mms.sys.menu.dao
 * Date:2017年1月12日下午11:03:19
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.menu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.clps.mms.sys.menu.model.Menu;
import com.clps.mms.util.exception.DataAccessException;

/**
 * ClassName:MenuDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年1月12日 下午11:03:19 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Repository("menuDao")
public interface MenuDao {
	/**
	 * insertMenu:(插入菜单). <br/>
	 *
	 * @author Charles
	 * @param menu
	 * @since JDK 1.8
	 */
	boolean insertMenu(Menu menu)throws DataAccessException;
	/**
	 * deleteMenuByName:(根据名字删除菜单). <br/>
	 *
	 * @author Charles
	 * @param name
	 * @since JDK 1.8
	 */
	boolean deleteMenuById(String Id)throws DataAccessException;
	
	/**
	 * updateMenu:(修改菜单). <br/>
	 *
	 * @author Charles
	 * @since JDK 1.8
	 */
	boolean updateMenuById(Menu menu)throws DataAccessException;
	
	/**
	 * findMenuById:(根据Id查找菜单). <br/>
	 *
	 * @author Charles
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	Menu findMenuById(String id)throws DataAccessException;
	
	/**
	 * queryMenuInfoCount:(查询总条数). <br/>
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	Long queryMenuInfoCount(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * queryUserInfoLst:(分页列表查询). <br/>
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	List<Menu> queryMenuInfoLst(Map<String, Object> map)throws DataAccessException;
	
	/**
	 * queryAllMenu:(查询所有的菜单). <br/>
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Menu> queryAllMenu() throws DataAccessException;
}

