/**
 * Project Name:clps_mms_copyright_201610
 * File Name:PropertiesFactory.java
 * Package Name:com.clps.mms.sys.factory
 * Date:2016年10月21日下午1:50:07
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.factory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * ClassName:PropertiesFactory Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON. Date: 2016年10月21日 下午1:50:07
 * 
 * @author tony.tan
 * 
 */
public class PropertiesFactory {
	private static  Properties pps = null;
	private  String driver;
	private  String url;
	private  String user;
	private  String password;
	private String dbName;
	public PropertiesFactory() {
		super();
		
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
		pps=new Properties();
		String path = PropertiesFactory.class.getResource("/").getPath();
		String websiteURL = (path.replace("/build/classes", "/WebContent/WEB-INF/jdbc")+ "jdbc.properties");
		try {
		InputStream in=new FileInputStream(websiteURL);
			pps.load(in);
			in.close();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//Enumeration<Object> em=pps.keys();
		for(Enumeration<Object> e=pps.keys();e.hasMoreElements();){
			System.out.println(e.nextElement());
		}
		this.driver = pps.getProperty("driver");
		this.url = pps.getProperty("url");
		this.user =pps.getProperty("username");
		this.password =pps.getProperty("password");
	}
	public static void main(String[] args) {
		pps=new Properties();
		String path = PropertiesFactory.class.getResource("/").getPath();
		String websiteURL = (path.replace("/build/classes", "/WebContent/WEB-INF/jdbc")+ "jdbc.properties");
		try {
		InputStream in=new FileInputStream(websiteURL);
			pps.load(in);
			in.close();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//Enumeration<Object> em=pps.keys();
		for(Enumeration<Object> e=pps.keys();e.hasMoreElements();){
			System.out.println(e.nextElement());
		}
	}
    
	
}
