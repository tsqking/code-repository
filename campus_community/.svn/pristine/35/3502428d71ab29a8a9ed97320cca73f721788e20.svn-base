/**
 * Project Name:campus_community
 * File Name:NewsServiceImpl.java
 * Package Name:com.clps.service.impl
 * Date:2017年4月1日下午3:27:32
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clps.common.bean.News;
import com.clps.common.exception.NewsServiceException;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.dao.NewsDao;
import com.clps.service.INewsService;

/**
 * ClassName:NewsServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月1日 下午3:27:32 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class NewsServiceImpl implements INewsService {
	private boolean flag;
	@Autowired
	private NewsDao newsDao;

	@Override
	public boolean mngAddNews(News news) throws NewsServiceException {
		if (news != null) {
			news.setN_send_date(DateFormat.getNow());
			return newsDao.addMngNews(news);
		}
		return flag;
	}

	@Override
	public PageVo<News> mngListAllNews(PageVo<News> pv) throws NewsServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<News> listMngNews = newsDao.listMngNews(map);
		PageVo<News> pageVo = new PageVo<>();
		if (listMngNews != null) {
			pageVo.setList(listMngNews);
			pageVo.setCount(Integer.toString(newsDao.mngNewsCout(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public boolean mngUpdateNews(News news) throws NewsServiceException {
		if (news != null) {
			news.setN_update_date(DateFormat.getNow());
			return newsDao.mngUpdateNews(news);
		}
		return flag;
	}

	@Override
	public List<News> queryAllNews() throws NewsServiceException {
		List<News> allNews = newsDao.queryAllNews();
		if (allNews != null) {
			return allNews;
		}
		return null;
	}

	@Override
	public boolean mngDeleteNews(Integer id) throws NewsServiceException {
		if (id != null) {
			return newsDao.mngDeleteNews(id);
		}
		return flag;
	}

}
