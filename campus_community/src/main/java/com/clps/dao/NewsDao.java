/**
 * Project Name:campus_community
 * File Name:NewsDao.java
 * Package Name:com.clps.dao
 * Date:2017年4月12日下午3:26:39
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.clps.common.bean.News;

/**
 * ClassName:NewsDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月12日 下午3:26:39 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface NewsDao {
	/**
	 * addMngNews:(管理员后台添加新闻). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean addMngNews(News news) throws DataAccessException;

	/**
	 * listMngNews:(后台分页显示所有新闻). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<News> listMngNews(Map<String, Object> map) throws DataAccessException;

	/**
	 * mngNewsCout:(后台分页查询总条数). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int mngNewsCout(Map<String, Object> map) throws DataAccessException;

	/**
	 * mngUpdateNews:(后台修改新闻). <br/>
	 * 
	 * @author Charles
	 * @param news
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean mngUpdateNews(News news) throws DataAccessException;

	/**
	 * queryAllNews:(前台查询所有新闻). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<News> queryAllNews() throws DataAccessException;

	/**
	 * mngDeleteNews:(后台批量删除新闻). <br/>
	 * 
	 * @author Charles
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean mngDeleteNews(int id) throws DataAccessException;

}
