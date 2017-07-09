/**
 * Project Name:campus_community
 * File Name:StudyDao.java
 * Package Name:com.clps.dao
 * Date:2017年3月23日下午11:29:57
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.clps.common.bean.Study;

/**
 * ClassName:StudyDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月23日 下午11:29:57 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */

public interface StudyDao {

	/**
	 * insertStudy:(前台界面发表文章). <br/>
	 * 
	 * @author Charles
	 * @param study
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean insertStudy(Study study) throws DataAccessException;

	/**
	 * listAllStudy:(前台分页查询所有可用文章). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Study> listAllStudy(Map<String, Object> map) throws DataAccessException;

	/**
	 * countStudy:(前台查询所有可用文章数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int countStudy(Map<String, Object> map) throws DataAccessException;

	/**
	 * listAllStudyMng:(后台分页查询所有文章). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Study> listAllStudyMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * countStudyMng:(后台查询所有文章数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int countStudyMng(Map<String, Object> map) throws DataAccessException;

	/**
	 * updateStudyMng:(后台审核文章). <br/>
	 *
	 * @author Charles
	 * @param study
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean updateStudyMng(Study study) throws DataAccessException;
	
	/**
	 * deleteStudyMng:(批量删除文章). <br/>
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteStudyMng(List<Integer> idList)throws DataAccessException;

}
