/**
 * Project Name:campus_community
 * File Name:ActivityDao.java
 * Package Name:com.clps.dao
 * Date:2017年3月23日下午9:50:54
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

/**
 * ClassName:ActivityDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月23日 下午9:50:54 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import com.clps.common.bean.Activity;

public interface ActivityDao {
	/**
	 * listAllActivity:(分页查询所有可用活动列表). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Activity> listAllActivity(Map<String, Object> map) throws DataAccessException;

	/**
	 * allCount:(查询可用活动数量). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int allCount(Map<String, Object> map) throws DataAccessException;

	/**
	 * listActivityMng:(管理员分页查询所有活动). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Activity> listActivityMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * CountMng:(管理员查询活动总条数). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int countMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * addAcMng:(管理员添加活动). <br/>
	 * 
	 * @author Charles
	 * @param activity
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean addAcMng(Activity activity) throws DataAccessException;

	/**
	 * updateAcMng:(后台管理员更新活动). <br/>
	 * 
	 * @author Charles
	 * @param activity
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean updateAcMng(Activity activity) throws DataAccessException;

	/**
	 * deleteAcMng:(管理员通过活动id删除活动). <br/>
	 * 
	 * @author Charles
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteAcMng(int id) throws DataAccessException;

	/**
	 * actApply:(活动报名). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean actApply(List<Map<String, Object>> applyList) throws DataAccessException;

	/**
	 * listApply:(分页查询所有报名情况). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAcApply(Map<String, Object> map) throws DataAccessException;

	/**
	 * countAcApply:(分页查询报名情况数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countAcApply(Map<String, Object> map) throws DataAccessException;
}
