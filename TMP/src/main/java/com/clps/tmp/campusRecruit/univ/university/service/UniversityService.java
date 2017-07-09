package com.clps.tmp.campusRecruit.univ.university.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.region.vo.ChinaRegionVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.SelectVo;

/**
 * @author Wellen
 *
 * 2016年3月15日
 */
public interface UniversityService {
	/**
     * 添加学校信息
     */
	public int addUniversity(UniversityVo universityVo)throws Exception;
	/**
     * 编辑学校信息
     */
	public int editUniversity(UniversityVo universityVo)throws Exception;
	/**
     * 删除学校信息
     */
	public  int deleteUniversity(String[] ids)throws Exception;
	/**
     * 查询学校信息
     */
	public BtTableVo<UniversityVo> selectUniversity(Map<String, Object> paramMap)throws Exception;
	
	/**
     * 查询总校信息
     */
	public List<SelectVo> selectMainUniversity(Map<String, Object> paramMap)throws Exception;

	/**
     * 查询更新学校信息
     */
	public UniversityVo selectEditUniv(Map<String, Object> paramMap)throws Exception;
	/**
     * 查询省市信息
     */
	public ChinaRegionVo selectProvince(Map<String, Object> paramMap)throws Exception;
	/**
     * 查询市区信息
     */
	public ChinaRegionVo selectCity(Map<String, Object> paramMap)throws Exception;
	/**
	 * 检查学校名称是否重复
	 * @param univName
	 * @return
	 * @throws Exception 
	 */
	public int checkUnivName(String univName) throws Exception;
	/**
     * 批量填加学校信息
     */
	public HashMap<String, Object> batchAddUniversity(List<Map<String, Object>> list) throws Exception;
	/**
	 *验证学校是否存在 
	 */
	public boolean existUniversity(String userName) throws Exception;
	/**
	 *获取省市区id
	 */
	public String getRegionscode(String sName)throws Exception;
}
