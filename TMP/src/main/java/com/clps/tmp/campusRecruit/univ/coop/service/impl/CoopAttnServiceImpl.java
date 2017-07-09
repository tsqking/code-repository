package com.clps.tmp.campusRecruit.univ.coop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.campusRecruit.univ.coop.service.CoopAttnService;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;

@Service("coopAttnService")
public class CoopAttnServiceImpl extends MBBaseService implements CoopAttnService {

	@Override
	public int addCoopAttnInfo(Map<String,Object> param) throws Exception {
		return mbDao.insert("coopAttn.addCoopAttnInfo", param);
	}
	@Override
	public int updateCoopAttnInfo(String[] ids) throws Exception {
		return mbDao.update("coopAttn.upadteCoopAttn", ids);
	}
	@Override
	public int deleteCoopAttnInfo(String[] ids) throws Exception {
		return mbDao.delete("coopAttn.deleteCoopAttn", ids);
	}
	@Override
	public BtTableVo<AttnVo> selectAttnInfo(Map<String, Object> paramMap) throws Exception {
		 long total = (Long) mbDao.selectOne("coopAttn.getAttnNum", paramMap);
		 @SuppressWarnings("unchecked")
		 List<AttnVo> coopVoList = (List<AttnVo>) mbDao.selectList("coopAttn.selectAttn", paramMap);
		 BtTableVo<AttnVo> bootStrapPageVo = new BtTableVo<AttnVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(coopVoList);
	     return bootStrapPageVo;
	}
	@Override
	public int deleteCoopAttn(Map<String, Object> paramMap) throws Exception {
		return mbDao.delete("coopAttn.upadteAttn",paramMap);
	}
}
