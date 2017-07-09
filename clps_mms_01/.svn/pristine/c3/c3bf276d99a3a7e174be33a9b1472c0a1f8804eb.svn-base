/**
 * Project Name:clps_mms_copyright_201610
 * File Name:JdbcTemplate.java
 * Package Name:com.clps.mms.sys.factory
 * Date:2016年10月18日下午5:06:28
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.factory.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.clps.mms.sys.factory.ConnectionFactory;

/**
 * ClassName:JdbcTemplate 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 下午5:06:28 
 * @author   tony.tan  
 * 	 
 */
public class JDBCTemplate<T> {
	private List<T> list = new ArrayList<T>();
	private Connection connection=null;
	private PreparedStatement pst=null;
	private ResultSet rs=null;
	private CallableStatement cs=null;
	
	public JDBCTemplate() {
		
		super();
		// TODO Auto-generated constructor stub
		list = new ArrayList<T>();
	}

	//增删改
	public boolean updateData(String sql,Object[] args){
		try {
			try {
				//获得连接
				connection=ConnectionFactory.getConnection();
				//创建会话
				pst=connection.prepareStatement(sql);
				//替换占位符
				for (int i = 0; i < args.length; i++) {
					pst.setObject(i+1, args[i]);
				}
				pst.executeUpdate();
				return true;
			} finally {
				// TODO: handle finally clause
				ConnectionFactory.close(cs,rs, pst, connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 查询结果集  返回全部数据
	 * @param  	sql args map
	 * @return	T
	 * */
	public List<T> queryAll(String sql,Object[] args,JDBCIMapper<T> map){
		 List<T> list = new ArrayList<T>();
		try {
			try {
				connection=ConnectionFactory.getConnection();
				pst=connection.prepareStatement(sql);
				//替换占位符
				for (int i = 0; i < args.length; i++) {
					pst.setObject(i+1, args[i]);
				}
				rs=pst.executeQuery();
				while (rs.next()) {
					T obj=map.map(rs);
					list.add(obj);
				}
			} finally {
				ConnectionFactory.close(cs,rs, pst, connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 按条件查询结果集 返回单条数据
	 * @param  	sql args map
	 * @return	T
	 * */
	public T queryUnique(String sql,Object[] args,JDBCIMapper<T>
	map){
		T obj = null;
		try {
			try{
				connection = ConnectionFactory.getConnection();
				pst = connection.prepareStatement(sql);
				//替换占位符
				for(int i=0;i<args.length;i++){
					pst.setObject(i+1, args[i]);
				}
				rs = pst.executeQuery();
				if(rs.next()){
					obj = map.map(rs);
				}
			}finally{
				ConnectionFactory.close(cs,rs, pst, connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}


