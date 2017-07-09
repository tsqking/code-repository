/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserLogMapper.java
 * Package Name:com.clps.mms.log.dao
 * Date:2016年11月1日下午5:58:45
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.log.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.clps.mms.log.sys.model.LogModel;
import com.clps.mms.sys.factory.jdbc.JDBCIMapper;

/**
 * ClassName:UserLogMapper 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年11月1日 下午5:58:45 
 * @author   tony.tan  
 * 	 
 */
public class UserLogMapper implements JDBCIMapper<LogModel>{

	@Override
	public LogModel map(ResultSet rs) {
		String  userName=null;
		Integer type=null;
		String content=null;
		Date date=null;
		String updateName=null;
		String remark=null;
		Integer status=null;
		if (rs!=null) {
			try {
				if (isExistColumn(rs, "u_name")) {
					userName=rs.getString("u_name");
				}
				if (isExistColumn(rs, "ul_type")) {
					type=rs.getInt("ul_type");
				}
				if (isExistColumn(rs, "ul_content")) {
					content=rs.getString("ul_content");
				}
				if (isExistColumn(rs, "ul_updateDate")) {
					date=rs.getDate("ul_updateDate");
				}
				if (isExistColumn(rs, "ul_updateName")) {
					updateName=rs.getString("ul_updateName");
				}
				if (isExistColumn(rs, "ul_remark")) {
					remark=rs.getString("ul_remark");
				}
				if (isExistColumn(rs, "ul_status")) {
					status=rs.getInt("ul_status");
				}
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		LogModel model=new LogModel(null, userName, type, content, date,updateName, remark, status);
		return model;
	}
	public boolean isExistColumn(ResultSet rs,String column){
		try {
			if (rs.findColumn(column)>0) {
				return true;
			}
		} catch (SQLException e) {
			
			    return false;
		}
		return false;
	}

}

