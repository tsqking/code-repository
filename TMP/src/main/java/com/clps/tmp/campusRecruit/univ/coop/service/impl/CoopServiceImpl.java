package com.clps.tmp.campusRecruit.univ.coop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.campusRecruit.univ.coop.service.CoopService;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopHisVo;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.JsonUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.SelectVo;

/**
 * @author Wellen
 *
 *         2016年3月15日
 */
@Service("coopService")
public class CoopServiceImpl extends MBBaseService implements CoopService {

	@Override
	public int addCoopInfo(CoopVo coopVo) throws Exception {
		return mbDao.insert("coop.addCoopInfo", coopVo);
	}

	@Override
	public int addCoopInfoHis(CoopVo coopVo) throws Exception {
		// TODO Auto-generated method stub
		// 初次添加历史记录
		CoopHisVo coopHisVo = new CoopHisVo();
		coopHisVo.setCoop_id(coopVo.getId());
		coopHisVo.setTime(coopVo.getCoop_time());
		coopHisVo.setStatus(coopVo.getStatus());
		coopHisVo.setRemark(coopVo.getRemark());
		coopHisVo.setCreate_time(coopVo.getCreate_time());
		coopHisVo.setCreate_user(coopVo.getCreate_user());
		coopHisVo.setUpdate_time(coopVo.getUpdate_time());
		coopHisVo.setUpdate_user(coopVo.getUpdate_user());
		coopHisVo.setDel("N");
		coopHisVo.setContacts(JsonUtil.toJSONString(coopVo.getContactIds()));
		return this.addCoopHis(coopHisVo);
	}

	@Override
	public int editCoopInfo(CoopVo coopVo) throws Exception {
		return mbDao.update("coop.editCoop", coopVo);
	}

	@Override
	public int deleteCoopInfo(String[] ids) throws Exception {
		// 删除合作信息
		int re = mbDao.update("coop.updateCoopDel", ids);
		// 删除合作状态变更历史
		mbDao.update("coop.updateCoopHisDel", ids);
		return re;
	}

	@Override
	public BtTableVo<CoopVo> selectCoopInfo(Map<String, Object> paramMap) throws Exception {
		long total = (Long) mbDao.selectOne("coop.getAllCoop", paramMap);
		getLangParam(paramMap);// 获取国际化语言参数
		@SuppressWarnings("unchecked")
		List<CoopVo> coopVoList = (List<CoopVo>) mbDao.selectList("coop.selectCoop", paramMap);
		int length = coopVoList.size();
		for (int i = 0; i < length; i++) {
			CoopVo temp = coopVoList.get(i);
			temp.setCoop_time(DateTimeUtil.databaseToSystem(temp.getCoop_time()));
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<CoopVo> bootStrapPageVo = new BtTableVo<CoopVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(coopVoList);
		return bootStrapPageVo;
	}

	@Override
	public CoopVo selectEditCoop(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		CoopVo temp = (CoopVo) mbDao.selectOne("coop.getEditCoop", paramMap);
		temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
		temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> selectCompanyName() throws Exception {
		return (List<SelectVo>) mbDao.selectList("coop.getCompanyName");
	}

	@Override
	public CoopVo selectViewCoop(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		CoopVo temp = (CoopVo) mbDao.selectOne("coop.getViewCoop", paramMap);
		temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
		temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoopHisVo> getCoopHis(String coopId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		getLangParam(paramMap);// 获取国际化语言参数
		paramMap.put("id", coopId);
		List<CoopHisVo> coopHisList = (List<CoopHisVo>) mbDao.selectList("coop.getCoopHis", paramMap);
		// 查询联系人信息
		for (CoopHisVo coopHis : coopHisList) {
			List<String> contactsIds = JsonUtil.toList(coopHis.getContacts(), String.class);
			paramMap.put("contacts", contactsIds);
			coopHis.setContactsList((List<AttnVo>) mbDao.selectList("coop.getCoopAttnByIds", paramMap));
		}
		return coopHisList;
	}

	@Override
	public int addCoopHis(CoopHisVo coopHisVo) throws Exception {
		// TODO Auto-generated method stub
		int re = mbDao.insert("coop.addCoopHis", coopHisVo);
		// 变更合作信息
		this.changCoopLastInfo(coopHisVo.getCoop_id() + "", coopHisVo,coopHisVo.getUpdate_user());
		return re;
	}

	@Override
	public CoopHisVo getCoopHisById(String coopHisId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", coopHisId);
		return (CoopHisVo) mbDao.selectOne("coop.getCoopHisById", paramMap);
	}

	@Override
	public int editCoopHis(CoopHisVo coopHisVo) throws Exception {
		// TODO Auto-generated method stub
		int re = mbDao.update("coop.updateCoopHis", coopHisVo);
		// 变更合作信息
		this.changCoopLastInfo(coopHisVo.getCoop_id() + "", coopHisVo,coopHisVo.getUpdate_user());
		return re;
	}

	@Override
	public void changCoopLastInfo(String coopId, CoopHisVo coopHisVo, String userName) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (coopHisVo == null) {
			// 查询内容
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", coopId);
			coopHisVo = (CoopHisVo) mbDao.selectOne("coop.getCoopHisLast", paramMap);
			flag = true;
		}else{
			String count = (String) mbDao.selectOne("coop.checkLastInfo", coopHisVo);
			if (count.equals("0")) {	
				flag = true;
			}
		}	
		if (flag) {	
			// 指定内容
			// 最新
			// 修改合作信息
			CoopVo coopVo = new CoopVo();
			if(coopHisVo==null){
				coopVo.setStatus("");
				coopVo.setRemark("");
				coopVo.setUpdate_time(DateTimeUtil.nowToDatabase());
				coopVo.setUpdate_user(userName);
				coopVo.setId(Integer.parseInt(coopId));
			}else{
				coopVo.setStatus(coopHisVo.getStatus());
				coopVo.setRemark(coopHisVo.getRemark());
				coopVo.setUpdate_time(coopHisVo.getUpdate_time());
				coopVo.setUpdate_user(coopHisVo.getUpdate_user());
				coopVo.setId(coopHisVo.getCoop_id());
			}	
			mbDao.update("coop.updateCoopLastInfo", coopVo);
		} else {
			// 不是最新数据
			// nothing to do
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoopVo> getRecruitInfo(Map<String, Object> map) throws Exception {
		return (List<CoopVo>) mbDao.selectList("coop.getRecruitInfo", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttnVo> getCoopAttn(String coopId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 获取国际化语言参数
		getLangParam(paramMap);
		paramMap.put("coopId", coopId);
		return (List<AttnVo>) mbDao.selectList("coop.getCoopAttn", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectVo> getContactsList(int coopId) throws Exception {
		// TODO Auto-generated method stub
		// 获取合作的甲方信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", coopId);
		CoopVo coop = (CoopVo) mbDao.selectOne("coop.getCoopSchoolInfo", paramMap);
		// 获取合作的甲方对接人信息
		paramMap = new HashMap<String, Object>();
		// 获取国际化语言参数
		getLangParam(paramMap);
		paramMap.put("univ_id", coop.getUniv_id() == 0 ? null : (coop.getUniv_id() + ""));
		paramMap.put("univ_branch_id", coop.getUniv_branch_id() == 0 ? null : (coop.getUniv_branch_id() + ""));
		paramMap.put("college_id", coop.getCollege_id() == 0 ? null : (coop.getCollege_id() + ""));
		List<AttnVo> attns = (List<AttnVo>) mbDao.selectList("coop.getCoopSchoolContactsInfo", paramMap);
		// 处理信息
		List<SelectVo> re = new ArrayList<SelectVo>();
		for (AttnVo att : attns) {
			SelectVo sv = new SelectVo();
			sv.setId(att.getId() + "");
			sv.setText(att.getName() + "&nbsp;&nbsp;&nbsp;&nbsp;" + att.getGender_name() + "&nbsp;&nbsp;&nbsp;&nbsp;"
					+ att.getMobile() + "&nbsp;&nbsp;&nbsp;&nbsp;" + att.getPhone() + "&nbsp;&nbsp;&nbsp;&nbsp;"
					+ att.getEmail() + "&nbsp;&nbsp;&nbsp;&nbsp;" + att.getPosition());
			re.add(sv);
		}
		return re;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CoopVo getCoopInfo(int coopId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", coopId);
		getLangParam(paramMap);
		// 获取合作信息
		CoopVo coop = (CoopVo) mbDao.selectOne("coop.getCoopInfo", paramMap);
		// 时间处理
		coop.setCoop_time(DateTimeUtil.databaseToSystem(coop.getCoop_time()));
		coop.setCreate_time(DateTimeUtil.databaseToSystem(coop.getCreate_time()));
		coop.setUpdate_time(DateTimeUtil.databaseToSystem(coop.getUpdate_time()));
		// 获取联系人信息
		List<String> attnList = (List<String>) mbDao.selectList("coop.getCoopAttnIds", paramMap);
		// 信息处理
		coop.setContacts(JsonUtil.toJSONString(attnList));
		return coop;
	}

	@Override
	public int updateCoopInfo(CoopVo coopVo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", coopVo.getId());
		getLangParam(paramMap);
		// 删除信息
		mbDao.delete("coop.deleteAttnInfo", paramMap);
		// 添加信息
		for (String attnId : coopVo.getContactIds()) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("coopId", coopVo.getId());
			paramMap.put("attnId", attnId);
			mbDao.insert("coop.addAttnInfo", paramMap);
		}
		return 1;
	}

	@Override
	public int deleteCoopHis(String coopHisId, String coopId,String name) throws Exception {
		// TODO Auto-generated method stub
		// 删除历史记录
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", coopHisId);
		getLangParam(paramMap);
		// 删除信息
		mbDao.update("coop.deleteCoopHis", paramMap);
		// 修改合作中最新的信息
		// 变更合作信息
		this.changCoopLastInfo(coopId, null,name);
		return 1;
	}

}
