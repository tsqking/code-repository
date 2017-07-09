/**
 * Project Name:campus_community
 * File Name:INewsService.java
 * Package Name:com.clps.service
 * Date:2017年4月12日下午3:27:21
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service;

import java.util.List;

import com.clps.common.bean.News;
import com.clps.common.exception.NewsServiceException;
import com.clps.common.util.PageVo;

/**
 * ClassName:INewsService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月12日 下午3:27:21 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface INewsService {

	/**
	 * mngAddNews:(后台管理员添加新闻). <br/>
	 * 
	 * @author Charles
	 * @param news
	 * @return
	 * @throws NewsServiceException
	 * @since JDK 1.8
	 */
	boolean mngAddNews(News news) throws NewsServiceException;

	/**
	 * mngListAllNews:(后台分页查询所有新闻). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws NewsServiceException
	 * @since JDK 1.8
	 */
	PageVo<News> mngListAllNews(PageVo<News> pv) throws NewsServiceException;

	/**
	 * mngUpdateNews:(后台管理员修改新闻). <br/>
	 * 
	 * @author Charles
	 * @param news
	 * @return
	 * @throws NewsServiceException
	 * @since JDK 1.8
	 */
	boolean mngUpdateNews(News news) throws NewsServiceException;

	/**
	 * queryAllNews:(前台查询所有新闻). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws NewsServiceException
	 * @since JDK 1.8
	 */
	List<News> queryAllNews() throws NewsServiceException;

	/**
	 * mngDeleteNews:(后台删除新闻). <br/>
	 * 
	 * @author Charles
	 * @param id
	 * @return
	 * @throws NewsServiceException
	 * @since JDK 1.8
	 */
	boolean mngDeleteNews(Integer id) throws NewsServiceException;
}
