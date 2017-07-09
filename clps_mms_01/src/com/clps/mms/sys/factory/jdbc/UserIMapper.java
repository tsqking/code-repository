/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserIMapper.java
 * Package Name:com.clps.mms.sys.factory
 * Date:2016年10月18日下午10:08:50
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.sys.factory.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.clps.mms.sys.user.model.UserInfo;

/**
 * ClassName:UserIMapper Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年10月18日 下午10:08:50
 * 
 * @author tony.tan
 * 
 */
public class UserIMapper implements JDBCIMapper<UserInfo> {
	@Override
	public UserInfo map(ResultSet rs) {
		if (rs != null) {
			UserInfo userInfo = null;
			Long id = null;
			String name = null;
			String nickname = null;
			String password = null;
			String gender=null;
			String mobNum = null;
			String email = null;
			Integer department=null;
			Integer position=null;
			Date redate = null;
			String updatename=null;
			Date updatedate=null;
			Integer isEnable=null;
			try {
				if (isExistColumn(rs, "id")) {
					id = rs.getLong("id");
				}
				if (isExistColumn(rs, "name")) {
					name = rs.getString("name");
				}
				if (isExistColumn(rs, "nickname")) {
					nickname = rs.getString("nickname");
				}
				if (isExistColumn(rs, "password")) {

					password = rs.getString("password");
				}
				if (isExistColumn(rs, "gender")) {
					gender=rs.getString("gender");
				}
				if (isExistColumn(rs, "mobnum")) {

					mobNum = rs.getString("mobnum");
				}
				if (isExistColumn(rs, "email")) {

					email = rs.getString("email");
				}
				if (isExistColumn(rs, "department")) {
					department=rs.getInt("department");
				}
				if (isExistColumn(rs, "position")) {
					position=rs.getInt("position");
				}
				if (isExistColumn(rs, "redate")) {

					redate = rs.getDate("redate");
				}
				if (isExistColumn(rs, "updatename")) {
					updatename=rs.getString("updatename");
				}
				if (isExistColumn(rs, "updatedate")) {
					updatedate=rs.getDate("updatedate");
				}
				if (isExistColumn(rs, "u_isEnable")) {
					
				}
				//userInfo = new UserInfo(id, name, nickname, password, gender,mobNum, email,department,position, redate,updatename,updatedate,isEnable);

			} catch (SQLException e) {

				e.printStackTrace();

			}
			return userInfo;
		} else {
			return null;
		}
	}

	public boolean isExistColumn(ResultSet rs, String columnName) {
		try {
			if (rs.findColumn(columnName) > 0) {
				return true;
			}
		} catch (SQLException e) {
			return false;

		}
		return false;
	}

}
