package com.clps.tmp.campusRecruit.other.talentPool.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.campusRecruit.other.talentPool.vo.TalentVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.core.sm.vo.SelectVo;

public interface TalentPoolService {
	/**
	 * 分页查找人才资源<br>
	 * 2016年3月24日 Seven
	 */
	public BtTableVo<TalentVo> findTalentList(Map<String,Object> paramMap)throws Exception;
	/**
	 * 获取大学下拉列表<br>
	 * 2016年3月25日 Seven
	 */
	public List<SelectVo> getUnivSelect()throws Exception;
	/**
	 * 获取大学下学院下拉列表<br>
	 * 2016年3月25日 Seven
	 */
	public List<SelectVo> getCollegeSelect(Map<String, Object> paramMap)throws Exception;
	/**
	 * 根据ID获取Talent信息<br>
	 * 2016年3月25日 Seven
	 */
	public TalentVo getTalentById(int id) throws Exception;
	/**
	 * 添加人才信息(除简历&头像url)<br>
	 * 2016年3月28日 Seven
	 */
	public int addTalentInfo(TalentVo talent)throws Exception;
	/**
	 * 根据人才ID更新其头像信息
	 * 2016年3月28日 Seven
	 */
	public int updatePhotoInfoByID(String talentId,String operator_name,FileUploadInfoVo photo)throws Exception;
	/**
	 * 根据人才ID更新其简历附件
	 * 2016年3月28日 Seven
	 */
	public int updateResumeInfoByID(String talentId,String operator_name,FileUploadInfoVo resume)throws Exception;
	/**
	 * 根据人才ID更新其简历附件
	 * 2016年3月28日 Seven
	 */
	public int updateResumeInfoByID(int talentId,String operator_name,String resume)throws Exception;
	/**
	 * 根据ID查看人才信息
	 * 2016年4月11日 Seven
	 */
	public TalentVo viewTalentById(int id) throws Exception;
	/**
	 * 修改人才信息
	 * 2016年4月13日 Seven
	 * @throws Exception 
	 */
	public int updateTalentInfo(TalentVo talentVo) throws Exception;
	/**
	 * 删除人才信息
	 * 2016年4月14日 Seven
	 */
	public int deleteTalentInfo(Map<String, Object> param)throws Exception;
	/**
	 * 人才年龄每年增长一岁
	 * @return
	 * @throws Exception 
	 */
	public int updateTalentAge() throws Exception;
	/**
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getTalentData(Map<String, Object> paramMap) throws Exception;
}
