package com.clps.tmp.campusRecruit.univ.college.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.SelectVo;

/**
 * @author Wellen
 *
 * 2016年3月15日
 */
public interface CollegeService {
	/**
     * 添加学院信息
     */
	public int addCollege(CollegeVo collegeVo)throws Exception;
	/**
	 * 查询学院信息
	 * */
	public BtTableVo<CollegeVo> selectCollege(Map<String, Object> paramMap) throws Exception;
	/**
     * 编辑学院信息
     */
	public int editCollege(CollegeVo collegeVo)throws Exception;
	/**
     * 删除学院信息
     */
	public  int deleteCollege(String[] ids)throws Exception;
	/**
     * 查询学院信息
     */
	public BtTableVo<CollegeVo> selectUniversity(Map<String, Object> paramMap)throws Exception;
	/**
     * 查询更新学院信息
     */
	public CollegeVo selectEditCollege(Map<String, Object> paramMap)throws Exception;

	/**
     * 查询学院名称
     */
	public List<SelectVo> selectCollegeName(Map<String, Object> paramMap)throws Exception;

}
