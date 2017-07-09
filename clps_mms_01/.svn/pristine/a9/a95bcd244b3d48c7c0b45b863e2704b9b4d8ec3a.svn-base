/**
 * Project Name:clps_mms_copyright_201610
 * File Name:UserLogDaoImpl.java
 * Package Name:com.clps.mms.log.dao.impl
 * Date:2016年11月1日下午5:55:20
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.log.sys.dao.impl;

import java.util.List;

import com.clps.mms.log.sys.dao.ILogDao;
import com.clps.mms.log.sys.dao.UserLogMapper;
import com.clps.mms.log.sys.model.LogModel;
import com.clps.mms.sys.factory.jdbc.JDBCTemplate;

/**
 * ClassName:UserLogDaoImpl 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年11月1日 下午5:55:20 
 * @author   tony.tan  
 * 	 
 */
public class UserLogDaoImpl implements ILogDao{
    private JDBCTemplate<LogModel> temp=null;
    private UserLogMapper mapper=null;
    private final String savaUserLog="INSERT INTO tbl_user_log(ul_id,u_name,ul_type,"
    		+ "ul_content,ul_updatedate,ul_updatename,ul_remark,ul_status) VALUES(?,?,?,?,?,?,?,?)";
    private final String queryAllUserLogInfo="SELECT * FROM tbl_user_log ";
	public UserLogDaoImpl() {
		
		super();
		temp=new JDBCTemplate<>();
		mapper=new UserLogMapper();
	}

	@Override
	public boolean saveLog(LogModel model) {
		Object[] args={model.getId(),model.getUserId(),model.getType(),model.getContent(),
				model.getUpdateTime(),model.getUpdateName(),model.getRemark(),model.getStatus()};
		return temp.updateData(savaUserLog, args);
	}

	@Override
	public boolean deleteLog(LogModel model) {
		
		return false;
	}

	@Override
	public boolean updateLog(LogModel model) {
		
		return false;
	}

	@Override
	public List<LogModel> queryAllLog() {
		Object[] args={};
		return temp.queryAll(queryAllUserLogInfo, args, mapper);
	}
	public static void main(String[] args) {
		ILogDao dao=new UserLogDaoImpl();
 		/*LogModel model=new LogModel(100001,"tyyy", 1, "保存", new Date(), "zw", 1);
		dao.saveLog(model);*/
		List<LogModel> models=dao.queryAllLog();
		System.out.println(models);
	}

}

