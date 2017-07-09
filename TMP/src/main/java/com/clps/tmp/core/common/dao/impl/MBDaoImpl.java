package com.clps.tmp.core.common.dao.impl;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.clps.tmp.core.common.dao.MBDao;

public class MBDaoImpl extends SqlSessionDaoSupport implements MBDao {
	@Override
	public Object selectOne(String statement, Object parameter) throws Exception {
		return getSqlSession().selectOne(statement, parameter);
	}
	@Override
	public Object selectOne(String statement) throws Exception{
		return getSqlSession().selectOne(statement);
	}
	@Override
	public List<?> selectList(String statement) throws Exception{
		return getSqlSession().selectList(statement);
	}
	@Override
	public List<?> selectList(String statement, Object parameter) throws Exception{
		return getSqlSession().selectList(statement, parameter);
	}
	@Override
	public Map<?,?> selectMap(String statement, String mapKey) throws Exception{
		return getSqlSession().selectMap(statement, mapKey);
	}
	@Override
	public Map<?,?> selectMap(String statement, Object parameter, String mapKey) throws Exception{
		return getSqlSession().selectMap(statement, parameter, mapKey);
	}
	@Override
	public int insert(String statement) throws Exception{
		return getSqlSession().insert(statement);
	}
	@Override
	public int insert(String statement, Object parameter) throws Exception{
		return getSqlSession().insert(statement, parameter);
	}
	@Override
	public int delete(String statement) throws Exception{
		return getSqlSession().delete(statement);
	}
	@Override
	public int delete(String statement, Object parameter) throws Exception{
		return getSqlSession().delete(statement, parameter);
	}
	@Override
	public int update(String statement) throws Exception{
		return getSqlSession().update(statement);
	}
	@Override
	public int update(String statement, Object parameter) throws Exception{
		return getSqlSession().update(statement, parameter);
	}
}
