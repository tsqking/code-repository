package com.clps.tmp.campusRecruit.univ.workhist.service;

import java.util.Map;
import com.clps.tmp.campusRecruit.univ.workhist.vo.WorkHistVo;
import com.clps.tmp.common.vo.BtTableVo;

/**
 * @author Wellen
 *
 * 2016年3月15日
 */
public interface WorkHistService {
	/**
     * 添加工作历史信息
     */
	public int addWorkHist(WorkHistVo  workHistVo)throws Exception;
	/**
     * 编辑工作历史信息
     */
	public int editWorkHist(WorkHistVo  workHistVo)throws Exception;
	/**
     * 删除工作历史信息
     */
	public  int deleteWorkHist(String[] ids)throws Exception;
	/**
     * 查询工作历史信息
     */
	public BtTableVo<WorkHistVo> selectWorkHist(Map<String, Object> paramMap)throws Exception;
	/**
     * 查询工作历史信息
     */
	public WorkHistVo selectEditWorkHist(Map<String, Object> paramMap)throws Exception;
	/**
     * 查询工作历史信息 by id 详细详细
     */
	public WorkHistVo selectViewWorkHist(Map<String, Object> paramMap)throws Exception;
	
}
