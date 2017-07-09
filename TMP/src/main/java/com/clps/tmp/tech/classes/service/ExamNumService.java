package com.clps.tmp.tech.classes.service;

import java.util.List;
import java.util.Map;

public interface ExamNumService {

	/**
	 * 批量添加准考证号
	 * @param list
	 * @return int 是否插入成功
	 */
	public int batchAddUserExamList(List<Map<String, Object>> list) throws Exception;

	/**
	 * 按男女与考试类别分组 查询该组最大的准考证号
	 * @param map
	 * @return Integer 询该组最大的准考证号
	 */
	public String queryUserExamNumMax(Map<String, Object> map)throws Exception;
	
	/**
	 * 按男女与考试类别分组 查询该组最大的准考证号
	 * @param map
	 * @return Integer 询该组最大的准考证号
	 */
	public List<String> queryUserExamNumMaxList(Map<String, Object> map)throws Exception;
	
	/**
	 * 生成准考证号
	 * @param list
	 * @return int 是否插入成功
	 */
	public int updUserExamList(Map<String, Object> map) throws Exception;

	public int addUserAndExamNum(Map<String, Object> map) throws Exception;

	public void addUserInClass(Map<String, Object> paramMap) throws Exception;

	public void updatePersonsInClassSize(Map<String, Object> paramMap) throws Exception;

	public int usernameToId(Map<String, Object> paramMap) throws Exception;


}
