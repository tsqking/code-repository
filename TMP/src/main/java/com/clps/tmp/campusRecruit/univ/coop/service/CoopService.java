package com.clps.tmp.campusRecruit.univ.coop.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopHisVo;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.SelectVo;

public interface CoopService {
	/**
	 * 添加合作信息
	 */
	public int addCoopInfo(CoopVo coopVo) throws Exception;
	//添加初始tag
	public int addCoopInfoHis(CoopVo coopVo) throws Exception;

	/**
	 * 编辑合作信息
	 */
	public int editCoopInfo(CoopVo coopVo) throws Exception;

	/**
	 * 删除合作信息
	 */
	public int deleteCoopInfo(String[] ids) throws Exception;

	/**
	 * 查询合作信息
	 */
	public BtTableVo<CoopVo> selectCoopInfo(Map<String, Object> paramMap) throws Exception;

	/**
	 * 查询更新合作信息
	 */
	public CoopVo selectEditCoop(Map<String, Object> paramMap) throws Exception;

	/**
	 * 查询公司名称
	 */
	public List<SelectVo> selectCompanyName() throws Exception;

	/**
	 * 查询更新合作信息
	 */
	public CoopVo selectViewCoop(Map<String, Object> paramMap) throws Exception;

	/**
	 * @Description (TODO获取合作状态变更历史)
	 * @param coopId
	 * @return
	 * @throws Exception
	 */
	public List<CoopHisVo> getCoopHis(String coopId) throws Exception;

	/**
	 * @Description (TODO获取合作状态变更历史ById)
	 * @param coopHisId
	 * @return
	 * @throws Exception
	 */
	public CoopHisVo getCoopHisById(String coopHisId) throws Exception;

	/**
	 * @Description (TODO添加变更历史)
	 * @param coopHisVo
	 * @return
	 * @throws Exception
	 */
	public int addCoopHis(CoopHisVo coopHisVo) throws Exception;

	/**
	 * @Description (TODO编辑历史)
	 * @param coopHisVo
	 * @return
	 * @throws Exception
	 */
	public int editCoopHis(CoopHisVo coopHisVo) throws Exception;
	
	
	/**
	 * @Description (TODO改变合作信息里的最新记录)
	 * @param coopHisVo
	 * @throws Exception
	 */
	public void changCoopLastInfo(String coopId,CoopHisVo coopHisVo,String userName) throws Exception;
	
	/**
	 * 获取校招提醒邮件相关信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CoopVo> getRecruitInfo(Map<String,Object> map) throws Exception;
	
	/**
	 * @Description (TODO获取联系人信息)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<AttnVo> getCoopAttn(String coopId) throws Exception;
	
	/**
	 * @Description (TODO获取联系人信息列表)
	 * @param coopId
	 * @return
	 * @throws Exception
	 */
	public List<SelectVo> getContactsList(int coopId) throws Exception;
	
	/**
	 * @Description (TODO获取合作详细信息)
	 * @param coopId
	 * @return
	 * @throws Exception
	 */
	public CoopVo getCoopInfo(int coopId) throws Exception;
	
	/**
	 * @Description (TODO变更合作信息)
	 * @param coopVo
	 * @return
	 * @throws Exception
	 */
	public int updateCoopInfo(CoopVo coopVo)throws Exception;
	
	/**
	 * @Description (TODO删除历史tag)
	 * @param coopHisId
	 * @return
	 * @throws Exception
	 */
	public int deleteCoopHis(String coopHisId,String coopId, String userName)throws Exception;
}
