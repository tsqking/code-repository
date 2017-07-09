package com.clps.tmp.core.common.dao;

import java.util.List;
import java.util.Map;


public interface MBDao {
	public Object selectOne(String statement, Object parameter) throws Exception;
	public Object selectOne(String statement) throws Exception;
	public List<?> selectList(String statement) throws Exception;
	public List<?> selectList(String statement, Object parameter) throws Exception;
	public Map<?,?> selectMap(String statement, String mapKey) throws Exception;
	public Map<?,?> selectMap(String statement, Object parameter, String mapKey) throws Exception;
	public int insert(String statement) throws Exception;
	public int insert(String statement, Object parameter) throws Exception;
	public int delete(String statement) throws Exception;
	public int delete(String statement, Object parameter) throws Exception;
	public int update(String statement) throws Exception;
	public int update(String statement, Object parameter) throws Exception;	
}
