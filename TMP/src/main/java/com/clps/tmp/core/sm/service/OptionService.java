 package com.clps.tmp.core.sm.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.OptionVo;
import com.clps.tmp.core.sm.vo.SelectVo;



/**
 * @author Seven
 *
 * 2015年10月13日
 */
public interface OptionService {
	
	/**
	 * 根据选项组调用值查询选项
	 */
	public List<SelectVo> getOptionsByGPVal(String value) throws Exception;
	/**
	 * 根据选项组调用值查询选项
	 */
	public List<Map<String, Object>> getOptionMapByGPVal(String value) throws Exception;
	/**
	 * 根据选项level查询选项
	 */
	public List<SelectVo> getOptionsByLevel(String level) throws Exception;
	/**
	 * 根据选项组调用值查询选项
	 */
	public List<SelectVo> getOptionGroups() throws Exception;
	/**
	 * 获取所有选项组
	 */
	public PageVo<OptionVo> queryOptionPage(PageVo<OptionVo> pageVo) throws Exception;
	/**
	 * 删除选项(组)
	 */
	public int deleteOption(String id)throws Exception;
	/**
	 * 根据id查询选项
	 */
	public OptionVo getOptionByID(String id) throws Exception;
	/**
	 * 根据id更新选项信息
	 */
	public int updateOption(OptionVo option)throws Exception;
	/**
	 * 查询是否存在相同Value的选项组
	 */
	public int checkOptionGroupByVal(String value) throws Exception; 
	/**
	 * 查询出制定id外是否存在相同Value的选项组
	 */
	public int checkOptionGroupByVal(String value, String excludeId) throws Exception;
	/**
	 * 查询某个组下是否存在相同value的选项
	 */
	public int checkOptionsByValPid(String value,String parent_id) throws Exception;
	/**
	 * 查询某个组下除指定id外是否存在相同value的选项
	 */
	public int checkOptionsByValPid(String value,String parent_id, String excludeId) throws Exception;
	/**
	 * 增加选项
	 */
	public int addOption(OptionVo option) throws Exception;
	
}
