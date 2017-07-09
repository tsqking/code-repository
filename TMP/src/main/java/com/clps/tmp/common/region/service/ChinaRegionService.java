package com.clps.tmp.common.region.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.region.vo.ChinaRegionVo;
import com.clps.tmp.core.sm.vo.SelectVo;



public interface ChinaRegionService {
	/**
	 * 插入省市区县信息
	 * */
	public ChinaRegionVo addChinaRegion(String fileName) throws Exception;
	/** 
     *  根据父级code获取子级code和name
     */ 
	public Map<String, ChinaRegionVo> findChinaRegion(Map<String, Object> param) throws Exception;
	
	/** 
     *  清空数据
     */ 
	public void deleteTable()throws Exception;
	/** 
     *  获取省市信息
     */ 
	public  List<SelectVo> findRegion(Map<String, Object> paramMap) throws Exception;
	/**
	 * 获取所有code-name对应map
	 * 2016年6月21日 Seven
	 */
	public List<Map<String, Object>> getAllCodeName() throws Exception;
}
