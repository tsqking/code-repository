package com.clps.tmp.tech.skill.service;

import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.skill.vo.SkillVo;

/**
 * @author Seven
 *
 * 2015年10月19日
 */
public interface SkillService {
    /**
     * 添加技能信息
     */
    public int addSkillData(Map<String,Object> paramMap) throws Exception;
    
    /**
     * 获取所有的技能
     */
    public PageVo<SkillVo> queryOptionPage(PageVo<SkillVo> pageVo) throws Exception;
    
    /**
     * 根据父类id获取子类的id和name
     * 
     */
    List<SelectVo> findOptionsByParentId(Map<String,Object> paramMap) throws Exception;
    
    /**
     * 删除技能<br>
     * 返回Map<br>
     * code - 0/1 0标识已删除 1标识未能删除<br>
     * code = 0 时 effect - 删除记录数<br>
     * code = 1 时 existPoint - 挂载在技能下的知识点<br>
     */
    public Map<String, Object> deleteSkill(Map<String,Object> paramMap)throws Exception;
    /**
     * 查找技能
     */
    SkillVo selectSkill(Map<String,Object> paramMap)throws Exception;
    /**
     * 编辑技能 
     */
    int editSkill(Map<String,Object> paramMap) throws Exception;
}
