package com.clps.tmp.campusRecruit.other.talentPool.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.campusRecruit.other.talentPool.service.TalentPoolService;
import com.clps.tmp.campusRecruit.other.talentPool.vo.TalentVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.SelectVo;
@Service("talentPoolService")
public class TalentPoolServiceImpl extends MBBaseService implements TalentPoolService {
	@SuppressWarnings("unchecked")
	@Override
	public BtTableVo<TalentVo> findTalentList(Map<String, Object> paramMap)
			throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		fmtTimeScopeQueryParam(paramMap,"create_time");//格式化时间范围查找参数
		fmtTimeScopeQueryParam(paramMap,"update_time");//格式化时间范围查找参数
		fmtDateScopeQueryParam(paramMap,"in_company_time");//格式化日期范围查找参数
		fmtDateScopeQueryParam(paramMap,"in_proj_time");//格式化日期范围查找参数
		long total = (Long) mbDao.selectOne("talent.allCount", paramMap);
        List<TalentVo> talentList = (List<TalentVo>) mbDao.selectList("talent.findTalentList", paramMap);
        int length=talentList.size();
        for(int i=0;i<length;i++){
        	TalentVo temp=talentList.get(i);
        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
        }
        BtTableVo<TalentVo> bootStrapPageVo = new BtTableVo<TalentVo>();
        bootStrapPageVo.setTotal((int)total);
        bootStrapPageVo.setRows(talentList);
        return bootStrapPageVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> getUnivSelect() throws Exception {
		List<SelectVo> list=(List<SelectVo>) mbDao.selectList("talent.getUnivSelect");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> getCollegeSelect(Map<String, Object> paramMap) throws Exception {
		List<SelectVo> list=(List<SelectVo>) mbDao.selectList("talent.getCollegeSelect",paramMap);
		return list;
	}

	@Override
	public TalentVo getTalentById(int id) throws Exception {
		TalentVo talentVo=(TalentVo) mbDao.selectOne("talent.getTalentById", id);
		talentVo.setLast_update_time(talentVo.getUpdate_time());
		talentVo.setLast_update_person(talentVo.getUpdate_person());
		talentVo.setCreate_time(DateTimeUtil.databaseToSystem(talentVo.getCreate_time()));
		talentVo.setUpdate_time(DateTimeUtil.databaseToSystem(talentVo.getUpdate_time()));
		talentVo.setBirthday(DateTimeUtil.getShowDate(talentVo.getBirthday()));
		return talentVo;
	}

	@Override
	public int addTalentInfo(TalentVo talent) throws Exception {
		mbDao.insert("talent.addTalentInfo", talent);
		return talent.getId();
	}

	@Override
	public int updatePhotoInfoByID(String talentId, String operator_name, FileUploadInfoVo photo) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("talentId", talentId);
		paramMap.put("photo_url", photo.getFilePath());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", operator_name);
		return mbDao.update("talent.updatePhotoInfo", paramMap);
	}

	@Override
	public int updateResumeInfoByID(String talentId, String operator_name, FileUploadInfoVo resume) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("talentId", talentId);
		paramMap.put("resume_url", resume.getFilePath());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", operator_name);
		return mbDao.update("talent.updateResumeInfo", paramMap);
	}

	@Override
	public TalentVo viewTalentById(int id) throws Exception {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", id);
		this.getLangParam(paramMap);
		TalentVo talentVo=(TalentVo) mbDao.selectOne("talent.viewTalentById", paramMap);
		talentVo.setCreate_time(DateTimeUtil.databaseToSystem(talentVo.getCreate_time()));
		talentVo.setUpdate_time(DateTimeUtil.databaseToSystem(talentVo.getUpdate_time()));
		talentVo.setBirthday(DateTimeUtil.getShowDate(talentVo.getBirthday()));
		return talentVo;
	}

	@Override
	public int updateTalentInfo(TalentVo talentVo) throws Exception {
		return mbDao.update("talent.updateTalentInfo", talentVo);
	}

	@Override
	public int deleteTalentInfo(Map<String, Object> param) throws Exception {
		return mbDao.update("talent.deleteTalentInfo", param);
	}

	@Override
	public int updateTalentAge() throws Exception {
		// TODO Auto-generated method stub
		return mbDao.update("talent.updateTalentAge");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> getTalentData(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		fmtTimeScopeQueryParam(paramMap,"create_time");//格式化时间范围查找参数
		fmtTimeScopeQueryParam(paramMap,"update_time");//格式化时间范围查找参数
		fmtDateScopeQueryParam(paramMap,"in_company_time");//格式化日期范围查找参数
		fmtDateScopeQueryParam(paramMap,"in_proj_time");//格式化日期范围查找参数
		List list=(List) mbDao.selectList("talent.getTalentInfo",paramMap);
		return list;
	}

	@Override
	public int updateResumeInfoByID(int talentId, String operator_name, String resume) throws Exception {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("talentId", talentId);
		paramMap.put("resume_url", resume);
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", operator_name);
		return mbDao.update("talent.updateResumeInfo", paramMap);
	}
}
