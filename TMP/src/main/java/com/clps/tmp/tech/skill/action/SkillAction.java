package com.clps.tmp.tech.skill.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.skill.service.SkillService;
import com.clps.tmp.tech.skill.vo.SkillVo;

/**
 * 技能知识点管理
 * 
 * @ClassName: SkillAction
 * @Description: TODO
 * @author tom
 * @date 2015年10月23日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")   
@Action("skill")
@Results({ @Result(name = "toSkillManagePage", location = "skill/skillManange.jsp"),
        @Result(name = "addSkillPage", location = "skill/addSkill.jsp"),
        @Result(name = "toEditSkillPage", location = "skill/editSkill.jsp"),
        @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class SkillAction extends BaseAction {
    // 页面属性 页面通过'xx.属性'方式
    private SkillVo skillVo;
    // json返回数据map
    private HashMap<String, Object> resultMap;

    public HashMap<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(HashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public SkillVo getSkillVo() {
        return skillVo;
    }

    public void setSkillVo(SkillVo skillVo) {
        this.skillVo = skillVo;
    }

	@Resource
    private SkillService skillService;
    /**
     * 跳转技能知识点管理界面
     */
    public String toSkillManagePage() {

        return "toSkillManagePage";
    }
    /**
     * 打开添加技能窗口
     * @throws Exception 
     */
    public String addSkillPage() throws Exception {
        // 获取父级ID
        String parentId = String.valueOf(skillVo.getParent_id());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", parentId);
        SkillVo skill = skillService.selectSkill(paramMap);
        skillVo.setType(skill==null?"":skill.getType());
        String lang=(String) this.session.get(SystemConstant.LANG);
        skillVo.setParent_name(skill==null?"":("zh_CN".equals(lang)?skill.getName():skill.getName_en_US()));
        // 技能层级
        String level = skill==null?"0":skill.getLevel();
        // 如果级别是最高级别,则设置级别为1
        if (parentId.equals("0")) {
            skillVo.setLevel("1");
        }
        // 如果技能层级为1,则设置添加技能层级为2,一级菜单为0,则层级为1,以此类推
        if (level.equals("0")) {
            skillVo.setLevel("1");
        }
        if (level.equals("1")) {
            skillVo.setLevel("2");
        }
        if (level.equals("2")) {
            skillVo.setLevel("3");
        }
        // 设置父类ID
        skillVo.setParent_id(Integer.valueOf(parentId));
        skillVo.setCreate_time(DateTimeUtil.nowToSystem());
        return "addSkillPage";
    }

    /**
     * 添加技能数据
     */
    public String addSkillData() throws Exception {
        // 接收表单数据
        String type = skillVo.getType();
        String name = skillVo.getName();
        String level = skillVo.getLevelCopy();
        int order = skillVo.getOrder();
        String enable = skillVo.getEnable();
        int parentId = skillVo.getParent_id();
        UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        String descript = skillVo.getDescription();
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type", type);
        paramMap.put("name", name);
        paramMap.put("level", level);
        paramMap.put("order", order);
        paramMap.put("enable", enable);
        paramMap.put("parentId", parentId);
        paramMap.put("create_time", DateTimeUtil.nowToDatabase());
        paramMap.put("create_person", user.getName());
        paramMap.put("update_time", DateTimeUtil.nowToDatabase());
        paramMap.put("update_person", user.getName());
        paramMap.put("descript", descript);
        paramMap.put("name_en_US", skillVo.getName_en_US());
        paramMap.put("descript_en_US", skillVo.getDescription_en_US());
        // 调用service方法，操作数据库
        int resData = skillService.addSkillData(paramMap);
        // 封装返回数据
        AjaxReturnInfo rtn = null;
        if (resData == 1) {
            rtn = AjaxReturnInfo.success("0");
        } else {
            rtn = AjaxReturnInfo.success("1");
        }
        resultMap = rtn.getReturnMap();
        return "json";
    }

    /**
     * 搜索下拉框查询
     */
    public String findSkillOption() throws Exception {
        // 获取父级id
        String parentId = String.valueOf(skillVo.getParent_id());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parentId", parentId);
        List<SelectVo> skillList = skillService.findOptionsByParentId(paramMap);
        // 返回json数据
        AjaxReturnInfo rtn = null;
        rtn = AjaxReturnInfo.success("");
        rtn.add("options", skillList);
        resultMap = rtn.getReturnMap();
        return "json";
    }

    /**
     * 查询技能
     */
    public String findSkillData() throws Exception {
        DataTableUtil<SkillVo> dataTableUtil = new DataTableUtil<SkillVo>();
        PageVo<SkillVo> pageVo = dataTableUtil.getTableData(this.httpRequest);
        // 1.搜索条件
        // 1.1.精确查询
        HashMap<String, String> where1 = new HashMap<String, String>();
        if (null != skillVo) {
            // 条件
            if (null != skillVo.getSkillFirst() && !"".equals(skillVo.getSkillFirst())) {
                where1.put("grand_id", skillVo.getSkillFirst());
                where1.put("parent_id", skillVo.getSkillFirst());
                where1.put("id", skillVo.getSkillFirst());
                if (null != skillVo.getSkillSecond() && !"".equals(skillVo.getSkillSecond())) {
                    where1.put("grand_id", skillVo.getSkillSecond());
                    where1.put("parent_id", skillVo.getSkillSecond());
                    where1.put("id", skillVo.getSkillSecond());
                    if (null != skillVo.getSkillThird() && !"".equals(skillVo.getSkillThird())) {
                        where1.put("grand_id", skillVo.getSkillThird());
                        where1.put("parent_id", skillVo.getSkillThird());
                        where1.put("id", skillVo.getSkillThird());
                    }
                }
            }
        }
        pageVo.setWhere1(where1);
        // 3.获取数据
        pageVo = skillService.queryOptionPage(pageVo);
        // 4.返回值
        resultMap = dataTableUtil.getReturnMap(pageVo);
        return "json";
    }

    /**
     * 删除技能
     */
    public String deleteSkill() throws Exception {
        // 获取记录序列号id
        String id = String.valueOf(skillVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        AjaxReturnInfo rtn = null;
        Map<String,Object> deleteRtn = skillService.deleteSkill(paramMap);
        if("0".equals(deleteRtn.get("code"))){//成功删除
        	rtn = AjaxReturnInfo.success((String) deleteRtn.get("effect"));
        }else{
        	rtn = AjaxReturnInfo.failed((String) deleteRtn.get("code"));
        	rtn.add("existPoint", deleteRtn.get("existPoint"));
        }
        resultMap = rtn.getReturnMap();
        return "json";

    }
    
    /**
     * 查找技能
     */
    public String toEditSkillPage() throws Exception {
        // 获取记录序列号id
        String id = String.valueOf(skillVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SkillVo skill = skillService.selectSkill(paramMap);
        skillVo.setId(skill.getId());
        skillVo.setType(skill.getType());
        skillVo.setName(skill.getName());
        skillVo.setName_en_US(skill.getName_en_US());
        skillVo.setLevelCopy(skill.getLevel());
        skillVo.setOrder(skill.getOrder());
        skillVo.setEnable(skill.getEnable());
        skillVo.setParent_id(skill.getParent_id());
        skillVo.setParent_name(skill.getParent_name());
        skillVo.setDescription(skill.getDescription());
        skillVo.setDescription_en_US(skill.getDescription_en_US());
        skillVo.setUpdate_person(skill.getUpdate_person());
        skillVo.setUpdate_time(skill.getUpdate_time());
        return "toEditSkillPage";
    }
    /**
     * 更新技能
     * */
    public String editSkill() throws Exception {
        // 接收表单数据
        String id = String.valueOf(skillVo.getId());
        String type = skillVo.getType();
        String name = skillVo.getName();
        String level = skillVo.getLevelCopy();
        int order = skillVo.getOrder();
        String enable = skillVo.getEnable();
        int parentId = skillVo.getParent_id();
        UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        String descript = skillVo.getDescription();
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("type", type);
        paramMap.put("name", name);
        paramMap.put("level", level);
        paramMap.put("order", order);
        paramMap.put("enable", enable);
        paramMap.put("parentId", parentId);
        paramMap.put("update_time", DateTimeUtil.nowToDatabase());
        paramMap.put("update_person",  user.getName());
        paramMap.put("descript", descript);
        paramMap.put("name_en_US", skillVo.getName_en_US());
        paramMap.put("descript_en_US", skillVo.getDescription_en_US());
        //后台数据交互
        AjaxReturnInfo rtn = null;
        int count = skillService.editSkill(paramMap);
        if (count < 0)
            rtn = AjaxReturnInfo.success("1");
        else
            rtn = AjaxReturnInfo.success("0");
        resultMap = rtn.getReturnMap();
        return "json";
    }

}
