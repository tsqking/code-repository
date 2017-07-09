/**
 * Project Name:campus_community
 * File Name:MessageDao.java
 * Package Name:com.clps.dao
 * Date:2017年5月20日上午1:17:59
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

/**
 * ClassName:MessageDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年5月20日 上午1:17:59 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.clps.common.bean.Message;

/**
 * ClassName: MessageDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年5月25日 下午2:39:31 <br/>
 *
 * @author Charles
 * @version
 * @since JDK 1.8
 */
public interface MessageDao {
	/**
	 * listUnread:(分页查询所有未读信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Message> listUnread(Map<String, Object> map) throws DataAccessException;

	/**
	 * unreadCount:(分页查询未读信息数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer unreadCount(Map<String, Object> map) throws DataAccessException;

	/**
	 * toRead:(改为已读状态). <br/>
	 * 
	 * @author Charles
	 * @param idlist
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean toRead(Integer id) throws DataAccessException;

	/**
	 * deleteUnread:(批量删除未读信息). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteMessage(List<Integer> idList) throws DataAccessException;

	/**
	 * moveToImp:(批量移动到重要信息). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean moveToImp(List<Integer> idList) throws DataAccessException;

	/**
	 * moveToTrash:(批量移动到垃圾箱). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean moveToTrash(List<Integer> idList) throws DataAccessException;

	/**
	 * listRead:(分页查询所有已读信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Message> listRead(Map<String, Object> map) throws DataAccessException;

	/**
	 * countRead:(分页列出所有已读信息数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countRead(Map<String, Object> map) throws DataAccessException;

	/**
	 * listImp:(分页查询所有重要信息). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Message> listImp(Map<String, Object> map) throws DataAccessException;

	/**
	 * countImp:(分页查询所有重要信息数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countImp(Map<String, Object> map) throws DataAccessException;

	/**
	 * impToRead:(重要信息批量移动到已读). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean impToRead(List<Integer> idList) throws DataAccessException;

	/**
	 * sendMessage:(发送邮件). <br/>
	 * 
	 * @author Charles
	 * @param message
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean sendMessage(Message message) throws DataAccessException;


	/**
	 * listTrash:(分页查询所有垃圾箱信息 ). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Message> listTrash(Map<String, Object> map) throws DataAccessException;

	/**
	 * countTrash:(查询所有垃圾箱信息数量). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	Integer countTrash(Map<String, Object> map) throws DataAccessException;

}
