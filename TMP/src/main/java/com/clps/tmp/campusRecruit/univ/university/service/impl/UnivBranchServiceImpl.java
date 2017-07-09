package com.clps.tmp.campusRecruit.univ.university.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.clps.tmp.campusRecruit.univ.university.service.UnivBranchService;
import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.SelectVo;
@Service("univBranchService")
public class UnivBranchServiceImpl extends MBBaseService implements UnivBranchService {

	/*
	 * 查询分校信息
	 * */
	@Override
	public BtTableVo<UniversityVo> selectUnivBranch(Map<String, Object> paramMap) throws Exception {
		 long total = (Long) mbDao.selectOne("university.getAllUnivBranch", paramMap);
		 getLangParam(paramMap);//获取国际化语言参数
		 @SuppressWarnings("unchecked")
		List<UniversityVo> universityVoList = (List<UniversityVo>) mbDao.selectList("university.selectUnivBranch", paramMap);
		 int length=universityVoList.size();
	        for(int i=0;i<length;i++){
	        	UniversityVo temp=universityVoList.get(i);
	        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
	        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
	        }
		 BtTableVo<UniversityVo> bootStrapPageVo = new BtTableVo<UniversityVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(universityVoList);
	     return bootStrapPageVo;
	}

	@Override
	public int addUnivBranch(UniversityVo universityVo) throws Exception {
		return mbDao.insert("university.addUniversity", universityVo);
	}

	/**
     * 删除学校信息
     */
	@Override
	public int deleteUniversity(String[] ids) throws Exception {
		return mbDao.update("university.updateUnivBranchDel", ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectUnivBranchName(Map<String, Object> paramMap) throws Exception {
		return (List<SelectVo>) mbDao.selectList("university.getAllUnivBranchName", paramMap);
	}

}
