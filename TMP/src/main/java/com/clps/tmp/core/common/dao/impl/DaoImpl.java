/*
* @Title:		daoImpl.java 
* @Package		com.clps.spring.jdbcTemplate.dao.impl 
* @author		seven.sun
* @version		V1.0  
*/ 
package com.clps.tmp.core.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.Assert;

import com.clps.tmp.core.common.dao.Dao;

/**
 * 通用spring jdbc dao
 * @author	seven
 */	
public class DaoImpl extends NamedParameterJdbcDaoSupport implements Dao {
	@Override
	public Object queryForObject(String sql, Class<?> requiredType) throws Exception{
		Assert.hasText(sql);
		return this.getJdbcTemplate().queryForObject(sql, requiredType);
	}
	@Override
	public Object queryForObject(String sql, Map<String, ?> paramMap, Class<?> requiredType) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, requiredType);
	}
	@Override
	public Object queryForObject(String sql, SqlParameterSource paramSource, Class<?> requiredType) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().queryForObject(sql, paramSource, requiredType);
	}
	@Override
	public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) throws Exception{
		Assert.hasText(sql);
		//TODO paramMap为空是否可行？
		return this.getNamedParameterJdbcTemplate().queryForMap(sql, paramMap);
	}
	/*
	 * UserModel model = new UserModel();  
     * model.setMyName("name5");  
     * SqlParameterSource paramSource = new BeanPropertySqlParameterSource(model);
	 */
	@Override
	public Map<String, Object> queryForMap(String sql, SqlParameterSource paramSource) throws Exception{
		Assert.hasText(sql);
		//TODO paramSource为空是否可行？
		return this.getNamedParameterJdbcTemplate().queryForMap(sql, paramSource);
	}
	@Override
	public List<?> query(String sql, RowMapper<?> rowMapper )throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().query(sql, rowMapper);
	}
	
	@Override
	public List<?> query(String sql, Map<String, Object> paramMap, RowMapper<?> rowMapper) throws Exception{
		Assert.hasText(sql);
		if(paramMap == null)
			return query(sql,rowMapper);
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap, rowMapper);
	}
	@Override
	public List<?> query(String sql, SqlParameterSource paramSource, RowMapper<?> rowMapper) throws Exception{
		Assert.hasText(sql);
		if(paramSource == null)
			return query(sql,rowMapper);
		return this.getNamedParameterJdbcTemplate().query(sql, paramSource, rowMapper);
	}
	@Override
	public List<Map<String,Object>> queryForList(String sql, Map<String, ?> paramMap) throws Exception{
		Assert.hasText(sql);
		//TODO paramMap为空是否可行？
		return this.getNamedParameterJdbcTemplate().queryForList(sql, paramMap);
	}
	@Override
	public List<Map<String,Object>> queryForList(String sql, SqlParameterSource paramSource) throws Exception{
		Assert.hasText(sql);
		//TODO paramSource为空是否可行？
		return this.getNamedParameterJdbcTemplate().queryForList(sql, paramSource);
	}
	@Override
	public SqlRowSet queryForRowSet(String sql) throws Exception{
		Assert.hasText(sql);
		return this.getJdbcTemplate().queryForRowSet(sql);
	}
	@Override
	public SqlRowSet queryForRowSet(String sql, Map<String, ?> paramMap) throws Exception{
		Assert.hasText(sql);
		if(paramMap == null)
			return queryForRowSet(sql);
		return this.getNamedParameterJdbcTemplate().queryForRowSet(sql, paramMap);
	}
	@Override
	public int insert(String sql, Map<String, Object> paramMap)throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	@Override
	public int insert(String sql, SqlParameterSource paramSource)throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().update(sql, paramSource);
	}
	@Override
	public int insertAndRtnKey(String sql, SqlParameterSource paramSource) throws Exception {
		Assert.hasText(sql);
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		this.getNamedParameterJdbcTemplate().update(sql, paramSource, generatedKeyHolder);
		return generatedKeyHolder.getKey().intValue();
	}
	@Override
	public int delete(String sql, Map<String, Object> paramMap)throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	@Override
	public int delete(String sql, SqlParameterSource paramSource)throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().update(sql, paramSource);
	}
	@Override
	public int update(String sql, Map<String, Object> paramMap) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	@Override
	public int update(String sql, SqlParameterSource paramSource) throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().update(sql, paramSource);
	}
	@Override
	public int execute(String sql) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().execute(sql, new JDBCPreparedStatementCallback());
	}
	@Override
	public int execute(String sql, Map<String, Object> paramMap) throws Exception {
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().execute(sql, paramMap, new JDBCPreparedStatementCallback());
	}
	@Override
	public int execute(String sql, SqlParameterSource paramSource) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().execute(sql, paramSource, new JDBCPreparedStatementCallback());
	}
	@Override
	public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().batchUpdate(sql, batchValues);
	}
	@Override
	public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) throws Exception{
		Assert.hasText(sql);
		return this.getNamedParameterJdbcTemplate().batchUpdate(sql, batchArgs);
	}
}
