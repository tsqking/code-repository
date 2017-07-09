/**
 * Project Name:campus_community
 * File Name:MessageServiceImpl.java
 * Package Name:com.clps.service.impl
 * Date:2017年5月25日上午1:19:30
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clps.common.bean.Message;
import com.clps.common.exception.MessageServiceException;
import com.clps.common.util.PageVo;
import com.clps.dao.MessageDao;
import com.clps.service.IMessageService;

/**
 * ClassName:MessageServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月25日 上午1:19:30 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private MessageDao dao;
	private boolean flag;

	@Override
	public PageVo<Message> listAllUnread(PageVo<Message> pv) throws MessageServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("pageWhere3", where.get("pageWhere3"));
		map.put("pageWhere4", where.get("pageWhere4"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Message> listUnread = dao.listUnread(map);
		PageVo<Message> pageVo = new PageVo<>();
		if (listUnread != null) {
			pageVo.setList(listUnread);
			pageVo.setCount(Integer.toString(dao.unreadCount(map)));
		}
		return pageVo;
	}

	@Override
	public boolean toRead(Integer id) throws MessageServiceException {
		if (id != null) {
			return dao.toRead(id);
		}
		return flag;
	}

	@Override
	public boolean deleteUnread(List<Integer> idlist) throws MessageServiceException {
		if (idlist != null) {
			return dao.deleteMessage(idlist);
		}
		return flag;
	}

	@Override
	public boolean moveToImp(List<Integer> idList) throws MessageServiceException {
		if (idList != null) {
			return dao.moveToImp(idList);
		}
		return flag;
	}

	@Override
	public boolean moveToTrash(List<Integer> idList) throws MessageServiceException {
		if (idList != null) {
			return dao.moveToTrash(idList);
		}
		return flag;
	}

	@Override
	public PageVo<Message> listAllRead(PageVo<Message> pv) throws MessageServiceException {

		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("pageWhere3", where.get("pageWhere3"));
		map.put("pageWhere4", where.get("pageWhere4"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Message> listRead = dao.listRead(map);
		PageVo<Message> pageVo = new PageVo<>();
		if (listRead != null) {
			pageVo.setList(listRead);
			pageVo.setCount(Integer.toString(dao.countRead(map)));
		}
		return pageVo;
	}

	@Override
	public PageVo<Message> listAllImp(PageVo<Message> pv) throws MessageServiceException {

		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("pageWhere3", where.get("pageWhere3"));
		map.put("pageWhere4", where.get("pageWhere4"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Message> listRead = dao.listImp(map);
		PageVo<Message> pageVo = new PageVo<>();
		if (listRead != null) {
			pageVo.setList(listRead);
			pageVo.setCount(Integer.toString(dao.countImp(map)));
		}
		return pageVo;
	}

	@Override
	public boolean impToRead(List<Integer> idList) throws MessageServiceException {
		if (idList != null) {
			return dao.impToRead(idList);
		}
		return flag;
	}

	@Override
	public boolean senMessage(Message message) throws MessageServiceException {
		if(message!=null){
			return dao.sendMessage(message);
		}
		return flag;
	}


	@Override
	public PageVo<Message> listAllTrash(PageVo<Message> pv) throws MessageServiceException {
		
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("pageWhere3", where.get("pageWhere3"));
		map.put("pageWhere4", where.get("pageWhere4"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Message> listRead = dao.listTrash(map);
		PageVo<Message> pageVo = new PageVo<>();
		if (listRead != null) {
			pageVo.setList(listRead);
			pageVo.setCount(Integer.toString(dao.countTrash(map)));
		}
		return pageVo;
	}
}
