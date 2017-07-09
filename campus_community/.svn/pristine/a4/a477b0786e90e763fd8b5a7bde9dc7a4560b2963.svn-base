/**
 * Project Name:campus_community
 * File Name:MessageController.java
 * Package Name:com.clps.web.controller
 * Date:2017年5月25日上午1:20:00
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
import com.clps.common.bean.Message;
import com.clps.common.exception.MessageServiceException;
import com.clps.common.util.DataTableHelper;
import com.clps.common.util.DateFormat;
import com.clps.common.util.PageVo;
import com.clps.service.IMessageService;

/**
 * ClassName:MessageController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月25日 上午1:20:00 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
public class MessageController {
	@Autowired
	private IMessageService service;
	private final String MESSAGE = "msg";
	Map<String, Object> resultMap = new HashMap<>();

	/**
	 * listAllUnread:(分页查询所有未读信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listUnread.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAllUnread(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Message> dth = new DataTableHelper<>();
		PageVo<Message> pv = dth.getTableData(map);
		try {
			pv = service.listAllUnread(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MessageServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * toRead:(改变信息状态为已读). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "toRead.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> toRead(@RequestBody Map<String, Object> map) {
		if (map != null) {
			int id = Integer.parseInt(map.get("id").toString());
			try {
				if (service.toRead(id)) {
					resultMap.put(MESSAGE, "操作成功");
					return resultMap;
				} else {
					resultMap.put(MESSAGE, "操作失败");
					return resultMap;
				}
			} catch (MessageServiceException e) {
				resultMap.put(MESSAGE, "操作失败");

				e.printStackTrace();
				return resultMap;
			}
		} else {
			resultMap.put(MESSAGE, "操作失败");
			return resultMap;
		}
	}

	/**
	 * deleteUnread:(批量删除信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "deleteUnread.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteUnread(@RequestBody List<Integer> idList) {
		resultMap.clear();
		if (idList != null) {
			try {
				if (service.deleteUnread(idList)) {
					resultMap.put(MESSAGE, "删除成功");
					return resultMap;
				} else {
					resultMap.put(MESSAGE, "删除失败");
					return resultMap;
				}
			} catch (MessageServiceException e) {
				resultMap.put(MESSAGE, "删除失败");
				e.printStackTrace();
				return resultMap;
			}
		} else {
			resultMap.put(MESSAGE, "删除失败");
			return resultMap;
		}

	}

	/**
	 * toRead:(批量改变信息状态为已读). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "batchToRead.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> batchToRead(@RequestBody List<Integer> idList) {
		if (idList != null) {
			for (int id : idList) {
				try {
					if (service.toRead(id)) {
						resultMap.put(MESSAGE, "操作成功");
					} else {
						resultMap.put(MESSAGE, "操作失败");
					}
				} catch (MessageServiceException e) {
					resultMap.put(MESSAGE, "操作失败");
					e.printStackTrace();
				}
			}
		} else {
			resultMap.put(MESSAGE, "操作失败");
		}
		return resultMap;
	}

	/**
	 * moveToImp:(批量移动到重要信息). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "moveToImp.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> moveToImp(@RequestBody List<Integer> idList) {
		resultMap.clear();
		if (idList != null) {
			try {
				if (service.moveToImp(idList)) {
					resultMap.put(MESSAGE, "操作成功");
				} else {
					resultMap.put(MESSAGE, "操作失败");
				}
			} catch (MessageServiceException e) {
				e.printStackTrace();
				resultMap.put(MESSAGE, "操作失败");
			}
		} else {
			resultMap.put(MESSAGE, "操作失败");
		}
		return resultMap;
	}

	/**
	 * moveToImp:(批量移动到重要信息). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "moveToTrash.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> moveToTrash(@RequestBody List<Integer> idList) {
		resultMap.clear();
		if (idList != null) {
			try {
				if (service.moveToTrash(idList)) {
					resultMap.put(MESSAGE, "操作成功");
				} else {
					resultMap.put(MESSAGE, "操作失败");
				}
			} catch (MessageServiceException e) {
				e.printStackTrace();
				resultMap.put(MESSAGE, "操作失败");
			}
		} else {
			resultMap.put(MESSAGE, "操作失败");
		}
		return resultMap;
	}

	/**
	 * listAllRead:(分页查询所有已读信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listRead.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAllRead(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Message> dth = new DataTableHelper<>();
		PageVo<Message> pv = dth.getTableData(map);
		try {
			pv = service.listAllRead(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MessageServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * listAllImp:(分页查询所有重要信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listImp.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAllImp(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Message> dth = new DataTableHelper<>();
		PageVo<Message> pv = dth.getTableData(map);
		try {
			pv = service.listAllImp(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MessageServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * imgToRead:(重要信息移动到已读). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "moveToRead.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> imgToRead(@RequestBody List<Integer> idList) {
		if (idList != null) {
			try {
				if (service.impToRead(idList)) {
					resultMap.put(MESSAGE, "操作成功");
				} else {
					resultMap.put(MESSAGE, "操作失败");
				}
			} catch (MessageServiceException e) {
				resultMap.put(MESSAGE, "操作失败");
				e.printStackTrace();
			}
		} else {
			resultMap.put(MESSAGE, "操作失败");
		}
		return resultMap;
	}

	/**
	 * sendMessage:(发送信息). <br/>
	 * @author Charles
	 * @param message
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "sendMsg.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(@RequestBody Message message) {
		message.setM_date(DateFormat.getNow());
		resultMap.clear();
		if (message != null) {
			try {
				if (service.senMessage(message)) {
					resultMap.put(MESSAGE, "发送成功");
				} else {
					resultMap.put(MESSAGE, "发送失败");
				}
			} catch (MessageServiceException e) {
				resultMap.put(MESSAGE, "发送失败");
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	
	/**
	 * listAllTrash:(分页查询所有垃圾箱信息). <br/>
	 * @author Charles
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "listTrash.do", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String, Object> listAllTrash(@RequestBody Map<String, Object> map) {
		resultMap.clear();
		DataTableHelper<Message> dth = new DataTableHelper<>();
		PageVo<Message> pv = dth.getTableData(map);
		try {
			pv = service.listAllTrash(pv);
			resultMap = dth.getReturnMap(pv);
		} catch (MessageServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
