/**
 * Project Name:clps_mms_copyright_201610
 * File Name:ConnectionDatabase.java
 * Package Name:com.clps.mms.sys.factory
 * Date:2016年10月18日上午10:55:39
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



/**
 * ClassName:ConnectionDatabase 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月18日 上午10:55:39 
 * @author   tony.tan  
 * 	 
 */
public class ConnectionFactory {
	private static Properties pps=null;
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static{
		pps=new Properties();
		String path = ConnectionFactory.class.getResource("/").getPath();
		String websiteURL = (path.replace("/build/classes", "/WebContent/WEB-INF/jdbc")+ "jdbc.properties");
		try {
		InputStream in=new FileInputStream(websiteURL);
			pps.load(in);
			in.close();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		driver = pps.getProperty("driver");
		url = pps.getProperty("url");
		user =pps.getProperty("username");
		password =pps.getProperty("password");
	}
	public  static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
			Class.forName(driver).newInstance();
         return	DriverManager.getConnection(url, user, password);
	}
	/**
	 * 
	 * close:释放资源 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(CallableStatement cs,ResultSet rs,PreparedStatement pstmt,Connection conn) throws SQLException{
		if (cs!=null) {
			cs.close();
		}
		if (rs!=null) {
			rs.close();
		}
		if (pstmt!=null) {
			pstmt.close();
		}
		if (conn!=null) {
			conn.close();
		}
	}
}

