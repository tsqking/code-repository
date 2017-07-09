package com.clps.tmp.question.tag.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.question.tag.vo.TagVo;

public interface TagService {

	/**
	 * 获取所有标签
	 * @return
	 */
	public PageVo<TagVo> queryTagPage(PageVo<TagVo> pageVo) throws Exception;
	
	/**
	 * 添加新标签
	 * @param tagVo
	 * @return
	 * @throws Exception
	 */
	public int addTagData(TagVo tagVo) throws Exception;
	
	/**
	 * 禁用标签
	 * @param tagVo
	 * @return
	 * @throws Exception
	 */
	public int changeTagStateDisable(TagVo tagVo) throws Exception;
	
	/**
	 * 启用标签
	 * @param tagVo
	 * @return
	 * @throws Exception
	 */
	public int changeTagStateEnable(TagVo tagVo) throws Exception;
	
	
	/*************************************新模板*****************************************/
	/**********************************************************************************/
	/**
	 * 查询所有的标签
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BtTableVo<TagVo> findTagList(Map<String,Object> map) throws Exception;
	
	/**
	 * 获取所有的标签（用于下拉框初始化）
	 * @return
	 * @throws Exception
	 */
	public List<SelectVo> getAllTag() throws Exception;
	/**
	 * 根据Name查询标签(若有，一般只有一个)
	 * @return
	 * @throws Exception
	 */
	public List<TagVo> getTagByName(String name) throws Exception;
}
