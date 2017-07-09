/**
 * Project Name:campus_community
 * File Name:WeiBoDao.java
 * Package Name:com.clps.dao
 * Date:2017年3月24日上午12:15:49
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

/**
 * ClassName:WeiBoDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月24日 上午12:15:49 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.clps.common.bean.WeiBo;

public interface WeiBoDao {
	/**
	 * listAllWeiBo:(前台列出所有微博). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAllWeiBo() throws DataAccessException;

	/**
	 * listWeiboMng:(管理员分页查询所有微博). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<WeiBo> listWeiboMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * weiboCountMng:(管理员查询所有微博数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int weiboCountMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * updateWeibo:(管理员审核微博). <br/>
	 * 
	 * @author Charles
	 * @param weiBo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean bgUpdateWeibo(WeiBo weiBo) throws DataAccessException;

	/**
	 * deleteWeibo:(删除微博). <br/>
	 * 
	 * @author Charles
	 * @param w_id
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteWeibo(String w_id) throws DataAccessException;

	/**
	 * insertWebo:(发布微博). <br/>
	 * 
	 * @author Charles
	 * @param weiBo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean publishWebo(WeiBo weiBo) throws DataAccessException;

	/**
	 * listAllFriendWeiBo:(列出朋友圈所有微博). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAllFriendWeiBo() throws DataAccessException;

}
