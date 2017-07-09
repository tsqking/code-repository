package com.clps.tmp.core.common.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCallback;

public class JDBCPreparedStatementCallback implements PreparedStatementCallback<Integer> {
	Integer totalEffected = 0;
	public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException {
		ps.execute();
		totalEffected += ps.getUpdateCount();
		return totalEffected;
	}
}