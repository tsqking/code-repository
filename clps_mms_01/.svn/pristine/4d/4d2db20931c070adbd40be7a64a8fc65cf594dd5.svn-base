/**
 * Project Name:clps_mms_01
 * File Name:RoleServiceImpl.java
 * Package Name:com.clps.mms.sys.role.service.impl
 * Date:2017年1月13日上午12:51:24
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
*/
/**
 * Project Name:clps_mms_01
 * File Name:RoleServiceImpl.java
 * Package Name:com.clps.mms.sys.role.service.impl
 * Date:2017年1月13日上午12:51:24
 * Copyright (c) 2017, lonnie@163.com All Rights Reserved.
 *
 */

package com.clps.mms.sys.role.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.clps.mms.sys.role.dao.RoleInfoMapper;
import com.clps.mms.sys.role.model.RoleInfo;
import com.clps.mms.sys.role.service.RoleService;
import com.clps.mms.sys.vo.PageVo;
import com.clps.mms.util.DateFormat;
import com.clps.mms.util.exception.DataAccessException;
import com.clps.mms.util.exception.RoleServiceException;

/**
 * ClassName:RoleServiceImpl 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2017年1月13日 上午12:51:24 
 * @author   lonnie
 * @version     
 * @since    JDK 1.8	 
 */
/**
 * ClassName: RoleServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). date: 2017年1月13日 上午12:51:24
 *
 * @author lonnie
 * @since JDK 1.8
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource(name = "RoleDao")
	private RoleInfoMapper roleDao;
	private Logger log = Logger.getLogger("console");
	private boolean Falg =false;

	/**
	 * TODO 添加角色
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#addRoleInfo(com.clps.mms.sys.role.model.RoleInfo)
	 */
	@Override
	public boolean addRoleInfo(RoleInfo roleInfo) throws RoleServiceException {

		if (this.findRoleInfoByName(roleInfo.getName()) != null) {
			return Falg;
//			log.error(RoleServiceException.ROLEINFO_EXIST);
//			throw new RoleServiceException(RoleServiceException.ROLEINFO_EXIST);
		} else {
			try {
				Date date = new Date();
				roleInfo.setCreate_time(DateFormat.format(date));
				System.out.println("roleInfo:" + roleInfo);
				return roleDao.insertRoleInfo(roleInfo);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		return Falg;
	}

	/**
	 * TODO 查询所有角色.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#findAllRoleInfo()
	 */
	@Override
	public List<RoleInfo> findAllRoleInfo() throws RoleServiceException {
		try {
			List<RoleInfo> queryAll = roleDao.queryAll();
			return queryAll;
		} catch (DataAccessException e) {

			e.printStackTrace();

		}

		return null;
	}

	/**
	 * TODO 根据角色名查询角色.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#findRoleInfoByName(java.lang.String)
	 */
	@Override
	public RoleInfo findRoleInfoByName(String roleName) throws RoleServiceException {
		try {
			return roleDao.queryRoleInfoByName(roleName);
		} catch (DataAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * TODO 根据角色id查询角色.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#findRoleInfoById(java.lang.Long)
	 */
	@Override
	public RoleInfo findRoleInfoById(Long id) throws RoleServiceException {
		try {
			return roleDao.queryRoleInfoById(id);
		} catch (DataAccessException e) {

			e.printStackTrace();

		}
		return null;
	}

	/**
	 * TODO 更新角色信息.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#updateRoleInfoById(com.clps.mms.sys.role.model.RoleInfo)
	 */
	@Override
	public boolean updateRoleInfoById(RoleInfo roleInfo) throws RoleServiceException {
		try {
			Date date = new Date();
			roleInfo.setUpdate_time(DateFormat.format(date));
			RoleInfo dbRoleInfo = this.findRoleInfoById(roleInfo.getId());
			// RoleInfo dbRoleInfo=this.findRoleInfoByName(roleInfo.getName());
			if (dbRoleInfo != null) {
				return roleDao.updateRoleInfoById(roleInfo);
			} else {
				log.error(RoleServiceException.ROLEINFO_NOT_EXIST);
				throw new RoleServiceException(RoleServiceException.ROLEINFO_NOT_EXIST);
			}
		} catch (DataAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Falg;
	}

	/**
	 * TODO 删除角色.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#deleteRoleInfoById(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean deleteRoleInfoById(String managerName, String roleName) throws RoleServiceException {
		try {
			RoleInfo roleInfo = this.findRoleInfoByName(roleName);
			if (null != roleInfo) {
				roleDao.deleteRoleInfoById(roleInfo.getId());
				return true;
			} else {
				log.error(RoleServiceException.ROLEINFO_NOT_EXIST);
				throw new RoleServiceException(RoleServiceException.ROLEINFO_NOT_EXIST);
			}
		} catch (DataAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return Falg;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#findRoleInfoLst(com.clps.mms.sys.vo.PageVo)
	 */
	@Override
	public PageVo<RoleInfo> findRoleInfoLst(PageVo<RoleInfo> pageVo) throws RoleServiceException {
		Integer page = pageVo.getPage();
		Integer limitPage = pageVo.getLimitPage();
		if (page < 1) {
			page = 1;
		}
		Integer offset = (page - 1) * limitPage;
		Map<String, String> where = pageVo.getWhere2();
		Map<String, String> sort = pageVo.getSort();
		Map<String, Object> map = new HashMap<>();

		map.put("offset", offset);
		map.put("limit", limitPage);
		map.put("sortname", sort.get("sortname"));
		map.put("sortorder", sort.get("sortorder"));
		map.put("condition1", where.get("pageWhere1"));
		map.put("condition2", where.get("pageWhere2"));
		map.put("condition3", where.get("pageWhere3"));
		map.put("condition4", where.get("pageWhere4"));
		try {
			System.out.println("map"+map);
			Long count = roleDao.queryRoleInfoCount(map);
			List<RoleInfo> roleList = roleDao.queryRoleInfoLst(map);
			pageVo.setList(roleList);
			pageVo.setAllcount(count.toString());
		} catch (DataAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return pageVo;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.clps.mms.sys.role.service.RoleService#findRoleInfoByCreateName(java.lang.String)
	 */
	@Override
	public RoleInfo findRoleInfoByCreateName(String roleCreateName) throws RoleServiceException {
		try {
			return roleDao.queryRoleInfoByCreateName(roleCreateName);
		} catch (DataAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

}