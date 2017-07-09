package com.clps.tmp.tech.classes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.tech.classes.service.ExamNumService;
@Service("examNumService")
public class ExamNumServiceImpl extends MBBaseService implements ExamNumService {

	@Override
	public int batchAddUserExamList(List<Map<String, Object>> list) throws Exception {
		int flag=mbDao.insert("userExamNum.batchAddUserExamNum", list);
		return flag;
	}

	@Override
	public String queryUserExamNumMax(Map<String, Object> map) throws Exception {
		String max = (String) mbDao.selectOne("userExamNum.getUserExamNumMax", map);
		return max;
	}

	@Override
	public int updUserExamList(Map<String, Object> map) throws Exception {
		int flag=mbDao.update("userExamNum.updateExamNum", map);
		return flag;
	}

	@Override
	public List<String> queryUserExamNumMaxList(Map<String, Object> map) throws Exception {
		@SuppressWarnings("unchecked")
		List<String> list=(List<String>) mbDao.selectList("userExamNum.getUserExamNumMaxList", map);
		return list;
	}

	@Override
	public int addUserAndExamNum(Map<String, Object> map) throws Exception {
		int flag=0;
		int exist=(int) mbDao.selectOne("userExamNum.userExists", map);
		if(exist!=1){
			 flag=mbDao.insert("userExamNum.addUserAndExamNum", map);
		}else{
			 flag=mbDao.update("userExamNum.updateExamNum", map);
		}
		
		return flag;
	}

	@Override
	public void addUserInClass(Map<String, Object> paramMap) throws Exception {
				//先清除记录
				mbDao.delete("userExamNum.delUserInClass", paramMap);
				//再插入数据库
				mbDao.insert("userExamNum.addUserInClass", paramMap);
		
	}

	@Override
	public void updatePersonsInClassSize(Map<String, Object> paramMap) throws Exception {
		mbDao.update("userExamNum.updateUserSize", paramMap);
	}

	@Override
	public int usernameToId(Map<String, Object> paramMap) throws Exception {
		int student_id=(int) mbDao.selectOne("userExamNum.usernameToId", paramMap);
		return student_id;
	}

}
