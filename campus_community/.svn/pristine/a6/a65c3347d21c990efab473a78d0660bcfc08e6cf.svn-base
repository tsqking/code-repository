/**
 * Project Name:campus_community
 * File Name:UserInfoDao.java
 * Package Name:com.clps.dao
 * Date:2017年3月22日上午11:42:41
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.dao;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import com.clps.common.bean.UserInfo;

/**
 * ClassName:UserInfoDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月22日 上午11:42:41 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface UserInfoDao {
	/**
	 * insertUserInfo:(插入用户). <br/>
	 * 
	 * @author Charles
	 * @param paramUserInfo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean insertUserInfo(UserInfo paramUserInfo) throws DataAccessException;

	/**
	 * findUserById:(根据id查找用户). <br/>
	 * 
	 * @author Charles
	 * @param paramString
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	UserInfo findUserById(String paramString) throws DataAccessException;

	/**
	 * findUserByNickName:(根据昵称查找用户). <br/>
	 * 
	 * @author Charles
	 * @param paramString
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	UserInfo findUserByNickName(String paramString) throws DataAccessException;

	/**
	 * findPasswordByNickName:(根据昵称查找密码). <br/>
	 * 
	 * @author Charles
	 * @param paramString
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	String findPasswordByNickName(String paramString) throws DataAccessException;

	/**
	 * updateUserInfo:(更新用户信息). <br/>
	 * 
	 * @author Charles
	 * @param userInfo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean updateUserInfo(UserInfo userInfo) throws DataAccessException;

	/**
	 * queryAllUser:(查找所有用户昵称和签名,用于显示推荐添加好友). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<UserInfo> queryNicMotto() throws DataAccessException;

	/**
	 * queryPoint:(查询头像、昵称、积分，用于显示积分排行). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<UserInfo> queryPoint() throws DataAccessException;

	/**
	 * listAllUser:(管理员分页查询所有用户). <br/>
	 * 
	 * @author Charles
	 * @param pv
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<UserInfo> listAllUser(Map<String, Object> map) throws DataAccessException;

	/**
	 * UserCount:(管理员查询所有用户的数量). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int UserCount(Map<String, Object> map) throws DataAccessException;

	/**
	 * bgUpdateUser:(管理员修改用户信息). <br/>
	 * 
	 * @author Charles
	 * @param userInfo
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean bgUpdateUser(UserInfo userInfo) throws DataAccessException;

	/**
	 * deleteUser:(管理员删除用户). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteUserByNickname(String username) throws DataAccessException;

	/**
	 * listAllFriends:(分页查询所有好友). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAllFriends(Map<String, Object> map) throws DataAccessException;

	/**
	 * friendCount:(分页查询所有好友个数). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int friendCount(Map<String, Object> map) throws DataAccessException;

	/**
	 * addFriend:(添加好友). <br/>
	 * 
	 * @author Charles
	 * @param username
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean addFriend(Map<String, Object> map) throws DataAccessException;
	
	/**
	 * findFriendByNickname:(根据名字查找好友). <br/>
	 * @author Charles
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<String> findFriendByNickname(String nickname)throws DataAccessException;
	
	/**
	 * deleteFriends:(批量删除好友). <br/>
	 * @author Charles
	 * @param list
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteFriends(List<String> friendList)throws DataAccessException;
	
	/**
	 * toBlack:(批量添加黑名单). <br/>
	 * @author Charles
	 * @param friendList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean toBlack(List<Map<String, Object>> friendList)throws DataAccessException;
	
	/**
	 * blackMark:(更改黑名单标记为1). <br/>
	 * @author Charles
	 * @param friendList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean blackMark(List<String> friendList) throws DataAccessException;
	
	/**
	 * listAllFriends:(分页查询所有黑名单好友). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	List<Map<String, Object>> listAllBlack(Map<String, Object> map) throws DataAccessException;

	/**
	 * friendCount:(分页查询所有黑名单好友个数). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	int blackCount(Map<String, Object> map) throws DataAccessException;
	
	/**
	 * deleteBlack:(从黑名单批量移除). <br/>
	 * @author Charles
	 * @param blackList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean deleteBlack(List<String> blackList) throws DataAccessException;
	
	/**
	 * blackMark:(更改黑名单标记为0). <br/>
	 * @author Charles
	 * @param friendList
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean blackMark2(List<String> blackList) throws DataAccessException;
	
	/**
	 * updateFace:(这里用一句话描述这个方法的作用). <br/>
	 * @author Charles
	 * @param facePath
	 * @return
	 * @throws DataAccessException
	 * @since JDK 1.8
	 */
	boolean updateFace(UserInfo userInfo)throws DataAccessException;
	
}
