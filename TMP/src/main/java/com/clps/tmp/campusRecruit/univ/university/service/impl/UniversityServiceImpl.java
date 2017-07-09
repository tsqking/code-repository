package com.clps.tmp.campusRecruit.univ.university.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.campusRecruit.univ.university.service.UniversityService;
import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.region.vo.ChinaRegionVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
/**
 * @author Wellen
 *
 * 2016年3月15日
 */
@Service("universityService")
public class UniversityServiceImpl extends MBBaseService implements UniversityService {
	/**
     * 添加学校信息
     */
	@Override
	public int addUniversity(UniversityVo universityVo) throws Exception {
		return mbDao.insert("university.addUniversity", universityVo);
	}
	/**
     * 编辑学校信息
     */
	@Override
	public int editUniversity(UniversityVo universityVo) throws Exception {
		return mbDao.update("university.editUniversity", universityVo);
	}
	/**
     * 删除学校信息
     */
	@Override
	public int deleteUniversity(String[] ids) throws Exception {
		return mbDao.update("university.updateUniversityDel", ids);
	}
	/**
     * 查询学校信息
     */
	@SuppressWarnings("unchecked")
	@Override
	public BtTableVo<UniversityVo> selectUniversity(Map<String, Object> paramMap) throws Exception {
		 long total = (Long) mbDao.selectOne("university.getAllUniv", paramMap);
		 getLangParam(paramMap);//获取国际化语言参数
		 List<UniversityVo> universityVoList = (List<UniversityVo>) mbDao.selectList("university.selectUniversity", paramMap);
		
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
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectMainUniversity(Map<String, Object> paramMap) throws Exception {
		return (List<SelectVo>) mbDao.selectList("university.getMainUniv", paramMap);
	}
	@Override
	public UniversityVo selectEditUniv(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		UniversityVo temp = (UniversityVo) mbDao.selectOne("university.getEditUniv", paramMap);
		temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
		temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		return temp;
	}
	@Override
	public ChinaRegionVo selectProvince(Map<String, Object> paramMap) throws Exception {
		
		return (ChinaRegionVo)mbDao.selectOne("university.getProvince", paramMap);
	}
	@Override
	public ChinaRegionVo selectCity(Map<String, Object> paramMap) throws Exception {
		return (ChinaRegionVo)mbDao.selectOne("university.getCity", paramMap);
	}
	@Override
	public int checkUnivName(String univName) throws Exception {
		int c=(Integer) this.mbDao.selectOne("university.getInfo",univName);
		return c;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> batchAddUniversity(List<Map<String, Object>> list) throws Exception {
		HashMap<String,Object> rtn=new HashMap<String,Object>();//返回map
		StringBuffer existname=new StringBuffer();//存放已存在学校信息
		List<Map<String,Object>> newList1=new ArrayList<Map<String,Object>>();
		List<String> univNameList=(List<String>) mbDao.selectList("university.getAllUnivName");
		int univSize=univNameList.size();
		int listSize=list.size();
		for (int j = 0; j < listSize; j++) {
			boolean exist=false;
			for(int k=0;k<univSize;k++){
				if(univNameList.get(k).equals(list.get(j).get("name"))){
					existname.append((String) list.get(j).get("name") + "<br>");
					exist=true;
					break;
				}
			}
			if(!exist){
				newList1.add(list.get(j));
			}
		}
		if(newList1.size()!=0)
			mbDao.insert("university.batchAddUniversity", newList1);
		rtn.put("existname", existname.toString());
		return rtn;
	}
	
	@Override
	public boolean existUniversity(String universityName) throws Exception{
		boolean exist=true;
		UniversityVo count=(UniversityVo) mbDao.selectOne("university.selectcheckname", universityName);
		if(count==null){
			exist=false;
		}
		return exist;
	}
	@Override
	public String getRegionscode(String sName) throws Exception {
		return (String) mbDao.selectOne("university.getRegionCode",sName);
	}

}
