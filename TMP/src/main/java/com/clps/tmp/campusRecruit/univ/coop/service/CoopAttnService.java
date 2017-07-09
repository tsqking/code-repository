package com.clps.tmp.campusRecruit.univ.coop.service;

import java.util.Map;

import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.campusRecruit.univ.coop.vo.CoopVo;
import com.clps.tmp.common.vo.BtTableVo;

public interface CoopAttnService {
	/**
     * 添加合作信息  联系人
     */
	public int addCoopAttnInfo(Map<String,Object> param)throws Exception;
	/**
     * 更新合作信息 联系人
     */
	public  int deleteCoopAttnInfo(String[] ids)throws Exception;
	/**
     * 删除合作信息 联系人
     */
	public int updateCoopAttnInfo(String[] ids) throws Exception;
	/**
     * 删除合作信息 联系人 删除全部的合作的联系人信息
     */
	public int deleteCoopAttn(Map<String, Object> paramMap) throws Exception;
	/**
     * 查询合作信息
     */
	public BtTableVo<AttnVo> selectAttnInfo(Map<String, Object> paramMap)throws Exception;
	
}
