/**
 * Project Name:campus_community
 * File Name:WeiBoServiceImpl.java
 * Package Name:com.clps.service.impl
 * Date:2017年3月24日上午12:19:41
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clps.common.bean.WeiBo;
import com.clps.common.exception.ActivityServiceException;
import com.clps.common.exception.WeiBoServiceException;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.dao.WeiBoDao;
import com.clps.service.IWeiBoService;

/**
 * ClassName:WeiBoServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月24日 上午12:19:41 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class WeiBoServiceImpl implements IWeiBoService {
	private Boolean flag = false;
	Logger log = Logger.getRootLogger();
	@Autowired
	private WeiBoDao dao;

	@Override
	public List<Map<String, Object>> listAllWeiBo() throws WeiBoServiceException {
		List<Map<String, Object>> list = dao.listAllWeiBo();
		if (list != null) {
			return list;
		}
		return null;
	}

	@Override
	public PageVo<WeiBo> listWeiboMng(PageVo<WeiBo> pv) throws ActivityServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<WeiBo> allWeibo = dao.listWeiboMng(map);
		PageVo<WeiBo> pageVo = new PageVo<>();
		if (allWeibo != null) {
			pageVo.setList(allWeibo);
			pageVo.setCount(Integer.toString(dao.weiboCountMng(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public boolean checkWeibo(WeiBo weiBo) throws WeiBoServiceException {
		if (weiBo != null) {
			if (dao.bgUpdateWeibo(weiBo)) {
				log.info("审核成功");
				return dao.bgUpdateWeibo(weiBo);
			} else
				log.info("审核失败");
		}
		return flag;
	}

	@Override
	public boolean deleteWeibo(String w_id) throws WeiBoServiceException {
		if (w_id != null) {
			if (dao.deleteWeibo(w_id)) {
				log.info("删除成功");
				return dao.deleteWeibo(w_id);
			} else {
				log.info("删除失败");
			}
		}
		return flag;
	}

	@Override
	public boolean publishWeiBo(WeiBo weiBo) throws WeiBoServiceException {
		if (weiBo != null) {
			String content = weiBo.getW_content();

			String s1 = content.replaceAll("\\[#qq", "<img src=\"img/qq/");
			String s2 = s1.replaceAll("qq#\\]", ".gif\"" + ">");
			String s3 = s2.replaceAll("\\[#tb", "<img src=\"img/tieba/");
			String newContent = s3.replaceAll("tb#\\]", ".jpg\"" + ">");

			weiBo.setW_content(newContent);
			weiBo.setW_date(DateFormat.getNow());
			return dao.publishWebo(weiBo);
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> listAllFriendWeiBo() throws WeiBoServiceException {

		List<Map<String, Object>> friendWeiBo = dao.listAllFriendWeiBo();
		if (friendWeiBo != null) {
			return friendWeiBo;
		}
		return null;
	}
}
