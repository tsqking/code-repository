/**
 * Project Name:campus_community
 * File Name:WeiBoController.java
 * Package Name:com.clps.web.controller
 * Date:2017年3月29日下午9:28:25
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.clps.common.bean.UserInfo;
import com.clps.common.bean.WeiBo;
import com.clps.common.exception.ActivityServiceException;
import com.clps.common.exception.UserServiceException;
import com.clps.common.exception.WeiBoServiceException;
import com.clps.common.util.DataTableHelper;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.common.util.UploadUtil;
import com.clps.dao.UserInfoDao;
import com.clps.service.IWeiBoService;

/**
 * ClassName:WeiBoController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月29日 下午9:28:25 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
@Controller
public class WeiBoController {
	Logger log = Logger.getRootLogger();
	private final String MESSAGE = "msg";
	Map<String, Object> resultMap = new HashMap<>();
	@Autowired
	private IWeiBoService service;
	@Autowired
	private UserInfoDao userDao;

	/**
	 * listAllWeiBo:(前台列出所有微博). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping("weibo")
	public ModelAndView listAllWeiBo() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Map<String, Object>> listAllWeiBo = service.listAllWeiBo();
			List<UserInfo> queryNicMotto = userDao.queryNicMotto();
			List<UserInfo> queryPoint = userDao.queryPoint();
			mav.addObject("allWeibo", listAllWeiBo);
			mav.addObject("nicMotto", queryNicMotto);
			mav.addObject("queryPoint", queryPoint);
			mav.setViewName("weibo");
		} catch (WeiBoServiceException e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * listWeiboMng:(管理员分页查询微博). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listWeibo.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	private Map<String, Object> listWeiboMng(@RequestBody Map<String, Object> map) {
		DataTableHelper<WeiBo> dt = new DataTableHelper<>();
		PageVo<WeiBo> pv = dt.getTableData(map);
		try {
			pv = service.listWeiboMng(pv);
			resultMap = dt.getReturnMap(pv);
		} catch (ActivityServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * checkWeibo:(审核微博). <br/>
	 * 
	 * @author Charles
	 * @param weiBo
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "editWeibo.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	private Map<String, Object> checkWeibo(@RequestBody WeiBo weiBo) {
		weiBo.setW_check_date(DateFormat.format(new Date(), DateFormat.FORMAT_LONG));
		try {
			if (service.checkWeibo(weiBo)) {
				resultMap.put(MESSAGE, "审核成功");
				return resultMap;
			} else {
				resultMap.put(MESSAGE, "审核失败");
			}
		} catch (WeiBoServiceException e) {
			resultMap.put(MESSAGE, "审核失败");
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * deleteWeibo:(删除微博). <br/>
	 * 
	 * @author Charles
	 * @param listWid
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "deleteWeibo.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteWeibo(@RequestBody List<String> listWid) {
		if (listWid == null) {
			resultMap.put(MESSAGE, "删除失败");
		} else {
			for (String s : listWid) {
				try {
					if (service.deleteWeibo(s)) {
						resultMap.put(MESSAGE, "删除成功");
						return resultMap;
					} else {
						resultMap.put(MESSAGE, "删除失败");
					}
				} catch (WeiBoServiceException e) {
					resultMap.put(MESSAGE, "删除失败");
					e.printStackTrace();
				}
			}
		}
		return resultMap;
	}

	/**
	 * publishWeibo:(发布微博). <br/>
	 * 
	 * @author Charles
	 * @param weiBo
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "publishWeibo.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> publishWeibo(@RequestBody WeiBo weiBo) {
		resultMap.clear();
		if (weiBo != null) {
			try {
				if (service.publishWeiBo(weiBo)) {
					resultMap.put(MESSAGE, "发布成功");
					return resultMap;
				} else {
					resultMap.put(MESSAGE, "发布失败");
				}
			} catch (WeiBoServiceException e) {
				resultMap.put(MESSAGE, "发布失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * upload:(上传微博图片). <br/>
	 * 
	 * @author Charles
	 * @param files
	 * @param request
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping("getWeiBoImg.do")
	public String upload(@RequestParam("imgfile") MultipartFile[] files, HttpServletRequest request) {
		resultMap.clear();
		if (files != null) {
			for (MultipartFile file : files) {
				// 保存的文件路径
				String filePath = request.getServletContext().getRealPath("/") + "WEB-INF/img/weibo/"
						+ file.getOriginalFilename();
				// 再保存一份到指定路径
				String localPath = "E:/jxust/workspace/campus_community/src/main/webapp/WEB-INF/img/weibo/"
						+ file.getOriginalFilename();
				if (files != null && files.length > 0) {
					// 保存文件
					if (UploadUtil.saveFile(request, file, filePath, localPath)) {
						System.out.println("img/weibo" + file.getOriginalFilename());
					}
				}
			}
		}
		return "weibo";
	}

	@RequestMapping("friend")
	public ModelAndView listAllFriendsWeiBo() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Map<String, Object>> listAllWeiBo = service.listAllFriendWeiBo();
			List<UserInfo> queryNicMotto = userDao.queryNicMotto();
			List<UserInfo> queryPoint = userDao.queryPoint();
			mav.addObject("allWeibo", listAllWeiBo);
			mav.addObject("nicMotto", queryNicMotto);
			mav.addObject("queryPoint", queryPoint);
			mav.setViewName("friend");
		} catch (WeiBoServiceException e) {
			e.printStackTrace();
		}
		return mav;
	}

}
