package com.clps.tmp.campusRecruit.univ.university.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.sm.vo.SelectVo;

public interface UnivBranchService {
	/**
     * 查询分校信息
     */
	public int addUnivBranch(UniversityVo universityVo)throws Exception;
	/**
     * 查询分校信息
     */
	public BtTableVo<UniversityVo> selectUnivBranch(Map<String, Object> paramMap)throws Exception;
	/**
     * 删除学校信息
     */
	public  int deleteUniversity(String[] ids)throws Exception;
	/**
     * 查询分校信息
     */
	public List<SelectVo> selectUnivBranchName(Map<String, Object> paramMap)throws Exception;
}
