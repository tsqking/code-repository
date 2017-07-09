/**
 * Project Name:campus_community
 * File Name:IMessageService.java
 * Package Name:com.clps.service
 * Date:2017年5月25日上午1:19:09
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service;
import java.util.List;

import com.clps.common.bean.Message;
import com.clps.common.exception.MessageServiceException;
/**
 * ClassName:IMessageService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月25日 上午1:19:09 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import com.clps.common.util.PageVo;
public interface IMessageService {

	/**
	 * listAllUnread:(分页查询所有未读信息). <br/>
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	PageVo<Message> listAllUnread(PageVo<Message> pv) throws MessageServiceException;
	
	/**
	 * toRead:(把信息改为已读状态). <br/>
	 * @author Charles
	 * @param id
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	boolean toRead(Integer id)throws MessageServiceException;
	
	/**
	 * deleteUnread:(批量删除信息). <br/>
	 * @author Charles
	 * @param idlist
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	boolean deleteUnread(List<Integer> idlist)throws MessageServiceException;
	
	/**
	 * moveToImp:(移动到重要信息). <br/>
	 * @author Charles
	 * @param idlist
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	boolean moveToImp(List<Integer> idlist)throws MessageServiceException;
	/**
	 * moveToImp:(移动到垃圾箱). <br/>
	 * @author Charles
	 * @param idlist
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	boolean moveToTrash(List<Integer> idlist)throws MessageServiceException;
	
	/**
	 * listAllRead:(分页查询所有已读信息). <br/>
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	PageVo<Message> listAllRead(PageVo<Message> pv) throws MessageServiceException;
	
	/**
	 * listAllImg:(分页查询所有重要信息). <br/>
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	PageVo<Message> listAllImp(PageVo<Message> pv) throws MessageServiceException;
	
	/**
	 * impToRead:(重要信息批量移动到已读). <br/>
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	boolean impToRead(List<Integer> idList)throws MessageServiceException;
	
	/**
	 * senMessage:(发送信息). <br/>
	 * @author Charles
	 * @param message
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	boolean senMessage(Message message)throws MessageServiceException;
	
	/**
	 * listAllTrash:(分页查询所有垃圾箱信息). <br/>
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws MessageServiceException
	 * @since JDK 1.8
	 */
	PageVo<Message> listAllTrash(PageVo<Message> pv) throws MessageServiceException;
	
	
}

