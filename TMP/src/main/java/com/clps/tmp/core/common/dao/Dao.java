/*
* @Title:		dao.java 
* @Package		com.clps.spring.jdbcTemplate.dao 
* @Description:	TODO(��һ�仰�������ļ���ʲô) 
* @author		seven.sun
* @date			2015��8��5�� ����9:32:09 
* @version		V1.0  
*/ 
package com.clps.tmp.core.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author	seven
 */

public interface Dao {
	public Object queryForObject(String sql, Class<?> requiredType) throws Exception;
	public Object queryForObject(String sql, Map<String, ?> paramMap, Class<?> requiredType) throws Exception;
	public Object queryForObject(String sql, SqlParameterSource paramSource, Class<?> requiredType) throws Exception;
	public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) throws Exception;
	public Map<String, Object> queryForMap(String sql, SqlParameterSource paramSource) throws Exception;
	public List<?> query(String sql, RowMapper<?> rowMapper ) throws Exception;
	public List<?> query(String sql, Map<String, Object> paramMap,RowMapper<?> rowMapper) throws Exception;
	public List<?> query(String sql, SqlParameterSource paramSource, RowMapper<?> rowMapper) throws Exception;
	public List<Map<String,Object>> queryForList(String sql, Map<String, ?> paramMap) throws Exception;
	public List<Map<String,Object>> queryForList(String sql, SqlParameterSource paramSource) throws Exception;
	public SqlRowSet queryForRowSet(String sql) throws Exception;
	public SqlRowSet queryForRowSet(String sql, Map<String, ?> paramMap) throws Exception;
	public int insert(String sql, Map<String,Object> paramMap) throws Exception;
	public int insert(String sql, SqlParameterSource paramSource) throws Exception;
	public int insertAndRtnKey(String sql, SqlParameterSource paramSource) throws Exception;
	public int delete(String sql, Map<String, Object> paramMap) throws Exception;
	public int delete(String sql, SqlParameterSource paramSource) throws Exception;
	public int update(String sql, Map<String, Object> paramMap) throws Exception;
	public int update(String sql, SqlParameterSource paramSource) throws Exception;
	public int execute(String sql) throws Exception;
	public int execute(String sql, Map<String, Object> paramMap) throws Exception;
	public int execute(String sql, SqlParameterSource paramSource) throws Exception;
	public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) throws Exception;
	public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) throws Exception;	
}
