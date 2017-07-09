package com.clps.tmp.campusRecruit.univ.college.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.campusRecruit.univ.college.service.CollegeService;
import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.SelectVo;
/**
 * @author Wellen
 *
 * 2016年3月15日
 */
@Service("collegeService")
public class CollegeServiceImpl extends MBBaseService implements CollegeService {

	@Override
	public int addCollege(CollegeVo collegeVo) throws Exception {
		return mbDao.insert("college.addCollege", collegeVo);
	}

	@Override
	public int editCollege(CollegeVo collegeVo) throws Exception {
		return mbDao.update("college.editCollege", collegeVo);
	}

	@Override
	public int deleteCollege(String[] ids) throws Exception {
		return mbDao.update("college.updateCollegeDel", ids);
	}

	@Override
	public BtTableVo<CollegeVo> selectUniversity(Map<String, Object> paramMap) throws Exception {
		long total = (Long) mbDao.selectOne("college.getAllCollege", paramMap);
		getLangParam(paramMap);//获取国际化语言参数
		@SuppressWarnings("unchecked")
		List<CollegeVo> universityVoList = (List<CollegeVo>) mbDao.selectList("college.selectCollege", paramMap);
		 int length=universityVoList.size();
	        for(int i=0;i<length;i++){
	        	CollegeVo temp=universityVoList.get(i);
	        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
	        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
	        }
		 BtTableVo<CollegeVo> bootStrapPageVo = new BtTableVo<CollegeVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(universityVoList);
	     return bootStrapPageVo;
	}

	@Override
	public BtTableVo<CollegeVo> selectCollege(Map<String, Object> paramMap) throws Exception {
		long total = (Long) mbDao.selectOne("college.getAllCollege", paramMap);
		 @SuppressWarnings("unchecked")
		List<CollegeVo> collegeVoList = (List<CollegeVo>) mbDao.selectList("college.selectCollege", paramMap);
		 getLangParam(paramMap);//获取国际化语言参数
		 int length=collegeVoList.size();
	        for(int i=0;i<length;i++){
	        	CollegeVo temp=collegeVoList.get(i);
	        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
	        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
	        }
		 BtTableVo<CollegeVo> bootStrapPageVo = new BtTableVo<CollegeVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(collegeVoList);
	     return bootStrapPageVo;
	}

	@Override
	public CollegeVo selectEditCollege(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		CollegeVo temp=(CollegeVo)mbDao.selectOne("college.getEditCollege", paramMap);
    	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
    	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
        return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectCollegeName(Map<String, Object> paramMap) throws Exception {
		return (List<SelectVo>) mbDao.selectList("college.getCollegeName", paramMap);
	}
}
