package com.clps.tmp.campusRecruit.univ.attn.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.SelectVo;

/**
 * @author Wellen
 *
 * 2016年3月15日
 */
public interface AttnService {
	/**
     * 添加联系人信息
     */
	public int addAttn(AttnVo attnVo)throws Exception;
	/**
	 * 查询联系人信息
	 * */
	public BtTableVo<AttnVo> selectAttn(Map<String, Object> paramMap) throws Exception;
	/**
     * 编辑联系人信息
     */
	public int editAttn(AttnVo attnVo)throws Exception;
	/**
     * 删除联系人信息
     */
	public  int deleteAttn(String[] ids)throws Exception;
	/**
     * 查询更新联系人信息
     */
	public AttnVo selectEditAttn(Map<String, Object> paramMap)throws Exception;
	/**
     * 查询学校
     */
	public List<SelectVo> selectAllUniv() throws Exception;
	/**
     * 查询总校
     */
	public List<SelectVo> selectAllMainUniv(Map<String, Object> paramMap) throws Exception;
	/**
     * 查询学院
     */
	public List<SelectVo> selectCollege(Map<String, Object> paramMap) throws Exception;
	
	/**
     * 查询联系人
     */
	public List<SelectVo> selectAttnList() throws Exception;
	/**
	 * 查询联系人信息
	 * */
	public BtTableVo<AttnVo> selectAllAttn(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Description (TODO根据名字查找联系人信息)
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public AttnVo findAttnByName(String name) throws Exception;
	
}
