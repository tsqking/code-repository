/**
 * Project Name:campus_community
 * File Name:UserInfoServiceImpl.java
 * Package Name:com.clps.service.impl
 * Date:2017年3月22日上午11:45:19
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
import com.clps.common.bean.UserInfo;
import com.clps.common.exception.UserServiceException;
import com.clps.common.util.DateFormat;
import com.clps.common.util.MD5;
import com.clps.common.util.PageVo;
import com.clps.dao.UserInfoDao;
import com.clps.service.IUserInfoService;

/**
 * ClassName:UserInfoServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月22日 上午11:45:19 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
	private Boolean flag = false;
	Logger log = Logger.getRootLogger();

	@Autowired
	private UserInfoDao userInfoDao;

	public boolean register(UserInfo userInfo) throws UserServiceException {
		if ((userInfoDao.findUserById(userInfo.getU_id()) == null)
				&& (userInfoDao.findUserByNickName(userInfo.getU_nickname()) == null)) {
			log.info("学号/工号:" + userInfo.getU_id() + ",用户名:" + userInfo.getU_nickname() + "注册成功");
			return userInfoDao.insertUserInfo(userInfo);
		}
		return flag;
	}

	public boolean login(String nickName, String password) throws UserServiceException {
		String pwd = MD5.getMD5Str(password);
		if (userInfoDao.findUserByNickName(nickName) == null || userInfoDao.findUserByNickName(nickName).equals("")) {
			log.error("该用户不存在！");
			flag = false;
			return flag;
		} else {
			if (userInfoDao.findPasswordByNickName(nickName) == null
					|| userInfoDao.findPasswordByNickName(nickName).equals("")) {
				log.info("用户" + nickName + "不可用");
				flag = false;
				return flag;
			} else {
				if (userInfoDao.findPasswordByNickName(nickName).equals(pwd)) {
					flag = true;
					log.info("用户" + nickName + "登录成功");
					return flag;
				}
			}
		}
		log.info("用户" + nickName + "登录失败");
		return flag;
	}

	public UserInfo findUserByNickname(String nickName) throws UserServiceException {
		UserInfo userInfo = new UserInfo();
		if (nickName != null) {
			userInfo = userInfoDao.findUserByNickName(nickName);
			if (userInfo != null) {
				return userInfo;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public UserInfo updateUserInfo(UserInfo userInfo) throws UserServiceException {
		if (userInfo != null) {
			if (userInfoDao.updateUserInfo(userInfo))
				return userInfoDao.findUserByNickName(userInfo.getU_nickname());
			log.info("修改成功");
		} else {
			log.info("修改失败");
			return null;
		}
		return null;
	}

	@Override
	public PageVo<UserInfo> listAllUser(PageVo<UserInfo> pv) throws UserServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<UserInfo> allUser = userInfoDao.listAllUser(map);
		PageVo<UserInfo> pageVo = new PageVo<>();
		if (allUser != null) {
			pageVo.setList(allUser);
			pageVo.setCount(Integer.toString(userInfoDao.UserCount(map)));
			return pageVo;
		}
		return null;
	}

	@Override
	public boolean bgUpdateUser(UserInfo userInfo) throws UserServiceException {
		if (userInfo != null) {
			if (userInfoDao.bgUpdateUser(userInfo))
				flag = true;
			log.info("修改成功");
			return flag;
		} else {
			log.info("修改失败");
		}
		return flag;
	}

	@Override
	public boolean deleteUser(String username) throws UserServiceException {
		if (userInfoDao.deleteUserByNickname(username)) {
			log.info("用户" + username + "删除成功");
			flag = true;
		} else {
			flag = false;
			log.info("删除失败");
		}
		return flag;
	}

	@Override
	public PageVo<Map<String, Object>> listAllFriends(PageVo<Map<String, Object>> pv) throws UserServiceException {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("pageWhere3", where.get("pageWhere3"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Map<String, Object>> allFriends = userInfoDao.listAllFriends(map);
		PageVo<Map<String, Object>> pageVo = new PageVo<>();
		if (allFriends != null) {
			pageVo.setList(allFriends);
			pageVo.setCount(Integer.toString(userInfoDao.friendCount(map)));
		}
		return pageVo;
	}

	@Override
	public boolean addFriend(Map<String, Object> map) throws UserServiceException {
		if (map != null) {
			map.put("f_add_date", DateFormat.getNow());
			return userInfoDao.addFriend(map);
		}
		return flag;
	}

	@Override
	public List<String> findFriendByNickname(String nickname) throws UserServiceException {
		if (nickname != null) {
			return userInfoDao.findFriendByNickname(nickname);
		}
		return null;
	}

	@Override
	public boolean deleteFriends(List<String> friendList) throws UserServiceException {
		if (friendList != null) {
			return userInfoDao.deleteFriends(friendList);
		}
		return flag;
	}

	@Override
	public boolean toBlack(List<Map<String, Object>> friendList) throws UserServiceException {
		if (friendList != null) {
			return userInfoDao.toBlack(friendList);
		}
		return flag;
	}

	@Override
	public boolean blackMark(List<String> friendList) throws UserServiceException {
		if (friendList != null) {
			return userInfoDao.blackMark(friendList);
		}
		return flag;
	}

	@Override
	public PageVo<Map<String, Object>> listAllBlacks(PageVo<Map<String, Object>> pv) throws UserServiceException {

		Map<String, Object> map = new HashMap<>();
		HashMap<String, String> where = pv.getWhere();

		map.put("pageWhere1", where.get("pageWhere1"));
		map.put("pageWhere2", where.get("pageWhere2"));
		map.put("pageWhere3", where.get("pageWhere3"));
		map.put("sortname", pv.getSortname());
		map.put("sortorder", pv.getSortorder());
		map.put("limit", pv.getLimit());
		map.put("offset", pv.getOffset());

		List<Map<String, Object>> allBlack = userInfoDao.listAllBlack(map);
		PageVo<Map<String, Object>> pageVo = new PageVo<>();
		if (allBlack != null) {
			pageVo.setList(allBlack);
			pageVo.setCount(Integer.toString(userInfoDao.blackCount(map)));
		}
		return pageVo;
	}

	@Override
	public boolean deleteBlack(List<String> blackList) throws UserServiceException {
		if (blackList != null) {
			return userInfoDao.deleteBlack(blackList);
		}
		return flag;
	}

	@Override
	public boolean blackMark2(List<String> blackList) throws UserServiceException {
		if (blackList != null) {
			return userInfoDao.blackMark2(blackList);
		}
		return flag;
	}

	@Override
	public boolean updateFace(UserInfo userInfo) throws UserServiceException {
		if (userInfo != null) {
			return userInfoDao.updateFace(userInfo);
		}
		return flag;
	}

}