package com.clps.tmp.campusRecruit.univ.attn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.campusRecruit.univ.attn.service.AttnService;
import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.SelectVo;

/**
 * @author Wellen
 *
 * 2016年3月15日
 */
@Service("attnService")
public class AttnServiceImpl extends MBBaseService implements AttnService {

	@Override
	public int addAttn(AttnVo attnVo) throws Exception {
		return mbDao.insert("attn.addAttn", attnVo);
	}

	@Override
	public BtTableVo<AttnVo> selectAttn(Map<String, Object> paramMap) throws Exception {
		long total = (Long) mbDao.selectOne("attn.getAllAttn", paramMap);
		getLangParam(paramMap);//获取国际化语言参数
		 @SuppressWarnings("unchecked")
		List<AttnVo> attnVoList = (List<AttnVo>) mbDao.selectList("attn.selectAttn", paramMap);
		 int length=attnVoList.size();
	        for(int i=0;i<length;i++){
	        	AttnVo temp=attnVoList.get(i);
	        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
	        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
	        }
		 BtTableVo<AttnVo> bootStrapPageVo = new BtTableVo<AttnVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(attnVoList);
	     return bootStrapPageVo;
	}

	@Override
	public int editAttn(AttnVo attnVo) throws Exception {
		return mbDao.update("attn.editAttn", attnVo);
	}

	@Override
	public int deleteAttn(String[] ids) throws Exception {
		return mbDao.update("attn.updateAttnDel", ids);
	}

	@Override
	public AttnVo selectEditAttn(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		AttnVo temp = (AttnVo) mbDao.selectOne("attn.getEditAttn", paramMap);
		temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
		temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectAllUniv() throws Exception {
		return (List<SelectVo>) mbDao.selectList("attn.getAllUniv");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectAllMainUniv(Map<String, Object> paramMap) throws Exception {
		return (List<SelectVo>) mbDao.selectList("attn.getAllMainUniv",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectCollege(Map<String, Object> paramMap) throws Exception {
		return (List<SelectVo>) mbDao.selectList("attn.getCollege",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectAttnList() throws Exception {
		return (List<SelectVo>) mbDao.selectList("attn.getAttoList");
	}

	@Override
	public BtTableVo<AttnVo> selectAllAttn(Map<String, Object> paramMap) throws Exception {
		long total = (Long) mbDao.selectOne("attn.getAllAttn", paramMap);
		getLangParam(paramMap);//获取国际化语言参数
		 @SuppressWarnings("unchecked")
		List<AttnVo> attnVoList = (List<AttnVo>) mbDao.selectList("attn.selectAllAttn", paramMap);
		 int length=attnVoList.size();
	        for(int i=0;i<length;i++){
	        	AttnVo temp=attnVoList.get(i);
	        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
	        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
	        }
		 BtTableVo<AttnVo> bootStrapPageVo = new BtTableVo<AttnVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(attnVoList);
	     return bootStrapPageVo;
	}

	@Override
	public AttnVo findAttnByName(String name) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", name);
		getLangParam(paramMap);//获取国际化语言参数
		Object o = mbDao.selectOne("attn.getAttnByName", paramMap);
		if(o==null){
			return null;
		}else{
			return (AttnVo)o;
		}
	}
	
}
