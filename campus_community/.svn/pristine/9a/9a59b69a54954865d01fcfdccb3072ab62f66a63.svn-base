/**
 * Project Name:campus_community
 * File Name:NewsController.java
 * Package Name:com.clps.web.controller
 * Date:2017年4月12日下午3:28:00
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.clps.common.bean.News;
import com.clps.common.exception.NewsServiceException;
import com.clps.common.util.DataTableHelper;
import com.clps.common.util.PageVo;
import com.clps.service.INewsService;

/**
 * ClassName:NewsController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月12日 下午3:28:00 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class NewsController {
	@Autowired
	private INewsService service;
	Map<String, Object> resultMap = new HashMap<>();
	private final String MESSAGE = "msg";

	/**
	 * addActivityMng:(后台添加新闻). <br/>
	 * 
	 * @author Charles
	 * @param news
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "addNewsMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> addActivityMng(@RequestBody News news) {
		try {
			if (service.mngAddNews(news)) {
				resultMap.put(MESSAGE, "添加成功");
			} else {
				resultMap.put(MESSAGE, "添加失败");
			}
		} catch (NewsServiceException e) {
			resultMap.put(MESSAGE, "添加失败");
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * mngListNews:(后台分页查询所有新闻). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "bgListNews.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> mngListNews(@RequestBody Map<String, Object> map) {
		DataTableHelper<News> dth = new DataTableHelper<>();
		PageVo<News> pv = dth.getTableData(map);

		try {
			pv = service.mngListAllNews(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (NewsServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@RequestMapping(value = "updateNewsMng.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> mngUpdateNews(@RequestBody News news) {
		try {
			if (service.mngUpdateNews(news)) {
				resultMap.put(MESSAGE, "更新成功");
			} else {
				resultMap.put(MESSAGE, "更新失败");
			}
		} catch (NewsServiceException e) {
			resultMap.put(MESSAGE, "更新失败");
			e.printStackTrace();
		}
		return resultMap;
	}

	@RequestMapping(value = "mngDeleteNews.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> mngDeleteNews(@RequestBody List<Integer> idList) {
		if (idList != null) {
			try {
				for (int id : idList) {
					if (service.mngDeleteNews(id)) {
						resultMap.put(MESSAGE, "删除成功");
					} else {
						resultMap.put(MESSAGE, "删除失败");
					}
				}
			} catch (NewsServiceException e) {
				resultMap.put(MESSAGE, "删除失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * toIndex:(查询所有新闻). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping("index")
	public ModelAndView toIndex() {
		ModelAndView mv = new ModelAndView();
		try {
			List<News> allNews = service.queryAllNews();
			if (allNews != null) {
				mv.addObject("allNews", allNews);
				mv.setViewName("index");
				return mv;
			} else {
				mv.setViewName("index");
			}
		} catch (NewsServiceException e) {
			mv.setViewName("index");
		}
		return mv;
	}
}
