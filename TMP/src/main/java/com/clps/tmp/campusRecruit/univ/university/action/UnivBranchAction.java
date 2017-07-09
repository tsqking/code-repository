package com.clps.tmp.campusRecruit.univ.university.action;

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

import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.campusRecruit.univ.university.service.UniversityService;
import com.clps.tmp.campusRecruit.univ.university.service.impl.UnivBranchServiceImpl;
import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/univ")
@Controller
@Scope("prototype")  
@Action("univBranch")
@Results({ @Result(name = "toAddUnivBranchPage", location = "../campusRecruit/univ/university/branch/addUnivBranch.jsp"),
	       @Result(name = "toUnivBranchPage", location = "../campusRecruit/univ/university/branch/univBranchManange.jsp"),
	       @Result(name = "toEditUnivBranchPage", location = "../campusRecruit/univ/university/branch/editUnivBranch.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class UnivBranchAction extends BaseAction{
	// 页面属性 页面通过'xx.属性'方式
    private UniversityVo universityVo;
    private CollegeVo collegeVo;
    @Resource
    UnivBranchServiceImpl univBranchService;
    @Resource
    UniversityService universityService;
    private HashMap<String, Object> resultMap;
    
	 public UniversityVo getUniversityVo() {
		return universityVo;
	}

	public void setUniversityVo(UniversityVo universityVo) {
		this.universityVo = universityVo;
	}
	
	public CollegeVo getCollegeVo() {
		return collegeVo;
	}

	public void setCollegeVo(CollegeVo collegeVo) {
		this.collegeVo = collegeVo;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
     * 添加分院信息
     */
    public String univBranchPage() throws Exception {
    	return "toUnivBranchPage";
    }
   
	/**
     * 获取分院信息
     */
    public String selectUnivBranch() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<UniversityVo> bootStrapPageVo= univBranchService.selectUnivBranch(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
    	return "json";
    }
    /**
     * 跳转添加分校管理
     * */
    public String addUnivBranchPage() throws Exception {
    	int idN=universityVo.getId();
    	universityVo.setId(idN);
    	// 获取记录序列号id
        String id = String.valueOf(universityVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        universityVo=universityService.selectEditUniv(paramMap);
    	return "toAddUnivBranchPage";
    }
    /**
     * 添加分校信息
     */
    public String addUniversity() throws Exception{
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
    	universityVo.setCreate_time(DateTimeUtil.nowToDatabase());
        universityVo.setCreate_user(user.getName());
        universityVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        universityVo.setUpdate_user(user.getName());
        universityVo.setParent_id(universityVo.getId());
        // 调用service方法，操作数据库
        int resData = univBranchService.addUnivBranch(universityVo);
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
     * 跳转分校信息更新界面
     */
    public String editUnivBranchPage() throws Exception{
    	 // 获取记录序列号id
        String id = String.valueOf(universityVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        universityVo=universityService.selectEditUniv(paramMap);
    	return "toEditUnivBranchPage";
    }
    /**
     * 更新分校信息
     */
    public String editUnivBranch() throws Exception {
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        universityVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        universityVo.setUpdate_user(user.getUpdate_person());
        universityVo.setCountry("中国");
    	// 调用service方法，操作数据库
        int resData = universityService.editUniversity(universityVo);
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
     * 删除分校信息
     */
    public String deleteUnivBranch() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
   	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
   	    String[] ids=((String)dataMap.get("id")).split(",");
         int deleteRtn = univBranchService.deleteUniversity(ids);
         // 封装返回数据
         AjaxReturnInfo rtn = null;
         if (deleteRtn >= 1) {
             rtn = AjaxReturnInfo.success("0");
         } else {
             rtn = AjaxReturnInfo.success("1");
         }
         resultMap = rtn.getReturnMap();
    	return "json";
    }
    /**
     * 获取分校名称
     */
    public String selectUnivBranchOptions() throws Exception {
    	// 获取父级id
        String parent_id = String.valueOf(universityVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id", parent_id);
        List<SelectVo> univBranchList;
        univBranchList =univBranchService.selectUnivBranchName(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", univBranchList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
}
