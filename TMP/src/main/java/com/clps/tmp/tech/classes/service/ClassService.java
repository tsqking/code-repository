package com.clps.tmp.tech.classes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.classes.vo.ClassVo;


/**
 * 班级管理 服务类
 * @author Seven
 *
 * 2015年11月3日
 */
public interface ClassService {
	 /**
     * 添加班级信息
     */
    public int addClassData(ClassVo classVo) throws Exception;
    /**
	 * 获取知识点分页信息
	 */
	PageVo<ClassVo> queryClassPage(PageVo<ClassVo> pageVo) throws Exception;
	/**
     * 删除班级
     */
    public int deleteClass(String id)throws Exception;
    /**
     * 查找班级
     */
    ClassVo selectClass(Map<String,Object> paramMap)throws Exception;
    /**
     * 编辑班级
     */
    int editClass(ClassVo classVo) throws Exception;
    /**
     * 查询人员
     */
    public PageVo<UserVo> queryUserPage(PageVo<UserVo> pageVo,int classId) throws Exception;
    /**
     * 添加班长班主任
     */
    public int setMonitorInClass(int classId,String type,String monitorNo,String updateUser,String updateTime) throws Exception;
    /**
     * 添加人员到班级
     */
    public int addPersonsInClass(Map<String, Object> paramMap) throws Exception;
    /**
     * 更新班级人数
     */
    public int updatePersonsInClassSize(Map<String, Object> paramMap) throws Exception;
    /**
     * 移除班级人员
     */
    public int deletePersonInClass(Map<String, Object> paramMap) throws Exception;
    /**
	 * 批量添加用户
	 * @param list 用户信息
	 * @return map 存放信息如下：existMobile-用户已存在的手机号信息  userNameList-本次新加的用户名信息
	 */
	public HashMap<String, Object> batchAddUser(List<Map<String,Object>> list) throws Exception;
	/**
	 * 验证用户名是否存在
	 */
	public boolean existUserName(String userName) throws Exception;
	/**
	 * 验证手机号是否存在
	 */
	public boolean existMobile(String mobile) throws Exception;
	/**
	 * 批量添加用户到班级
	 * @param list 用户信息
	 * @return map 存放信息如下：existMobile-用户已存在的手机号信息  userNameList-本次新加的用户名信息 要添加的班级ID
	 */
	public void batchAddUserInClass(String [] userNames,int id) throws Exception;
	
}
