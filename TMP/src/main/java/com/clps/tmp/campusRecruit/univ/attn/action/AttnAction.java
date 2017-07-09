package com.clps.tmp.campusRecruit.univ.attn.action;

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

import com.clps.tmp.campusRecruit.univ.attn.service.AttnService;
import com.clps.tmp.campusRecruit.univ.attn.vo.AttnVo;
import com.clps.tmp.campusRecruit.univ.college.service.CollegeService;
import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.campusRecruit.univ.university.service.UniversityService;
import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
/**
 * 联系人信息管理
 * 
 * @ClassName: AttnAction
 * @Description: TODO
 * @author Wellen
 * @date 2016年3月14日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/univ")
@Controller
@Scope("prototype")   
@Action("attn")
@Results({ @Result(name = "toAttnManagePage", location ="../campusRecruit/univ/attn/attnManage.jsp"),
	       @Result(name = "toAddAttnPage", location ="../campusRecruit/univ/attn/addAttn.jsp"),
	       @Result(name = "toeditAttnPage", location ="../campusRecruit/univ/attn/editAttn.jsp"),
           @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class AttnAction extends BaseAction {
	// 页面属性 页面通过'xx.属性'方式
    private AttnVo attnVo;
    // json返回数据map
    private HashMap<String, Object> resultMap;
    @Resource
    AttnService attnService;
    @Resource
    UniversityService universityService;
    @Resource
    CollegeService collegeService;
    
	public AttnVo getAttnVo() {
		return attnVo;
	}
	public void setAttnVo(AttnVo attnVo) {
		this.attnVo = attnVo;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
     * 跳转联系人信息管理界面
     */
    public String toAttnManagePage() {
        return "toAttnManagePage";
    }
    /**
     * 添加联系人信息
     */
    public String addAttn() throws Exception{
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
    	attnVo.setCreate_time(DateTimeUtil.nowToDatabase());
    	attnVo.setCreate_user(user.getName());
        attnVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        attnVo.setUpdate_user(user.getName());
    	// 调用service方法，操作数据库
        int resData = attnService.addAttn(attnVo);
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
     * 查询联系人信息
     */
    public String selectAttn() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<AttnVo> bootStrapPageVo= attnService.selectAttn(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
    	return "json";
    }
    /**
     * 新增合作信息  查询联系人信息
     */
    public String selectAttnList() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<AttnVo> bootStrapPageVo= attnService.selectAllAttn(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
    	return "json";
    }
    /**
     * 跳转添加联系人界面
	 * @throws Exception 
     */
    public String addAttnPage() throws Exception {
        return "toAddAttnPage";
    }
    /**
     * 跳转更新联系人信息管理界面
	 * @throws Exception 
     */
    public String editAttnPage() throws Exception {
    	// 获取记录序列号id
        String id = String.valueOf(attnVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        attnVo=attnService.selectEditAttn(paramMap);
        return "toeditAttnPage";
    }
    /**
     * 更新联系人信息
     */
    public String editAttn() throws Exception {
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        attnVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        attnVo.setUpdate_user(user.getUpdate_person());
    	// 调用service方法，操作数据库
        int resData = attnService.editAttn(attnVo);
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
     * 删除联系人信息
     */
    public String deleteAttn() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
   	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
   	    String[] ids=((String)dataMap.get("id")).split(",");
        int deleteRtn = attnService.deleteAttn(ids);
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
     * 搜索框初始化学校信息下拉框
     */
    public String selectMainUniversity() throws Exception {
        List<SelectVo> universityList;
        universityList =attnService.selectAllUniv();
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", universityList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
    /**
     * 初始化学校 分校信息下拉框
     */
    public String findMainUniv() throws Exception {
    	// 获取父级id
        String parent_id = String.valueOf(attnVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id", parent_id);
        // 封装表单数据
        paramMap.put("parent_id", parent_id);
        List<SelectVo> universityList;
        universityList =attnService.selectAllMainUniv(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", universityList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
    /**
     * 初始化学校 分校信息下拉框
     */
    public String findMainUnivBranch() throws Exception {
    	// 获取父级id
        String parent_id = String.valueOf(attnVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id", parent_id);
        List<SelectVo> universityList;
        universityList =attnService.selectAllMainUniv(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", universityList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
  
    /**
     * 初始化学院信息下拉框
     */
    public String findCollegeOption() throws Exception {
    	// 获取父级id
        String parent_id = String.valueOf(attnVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id", parent_id);
        // 封装表单数据
        List<SelectVo> collegeList;
        collegeList =attnService.selectCollege(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", collegeList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
    /**
     * 初始化联系人信息下拉框
     */
    public String findAttnOption() throws Exception {	
        List<SelectVo> attnList;
        attnList =attnService.selectAttnList();
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", attnList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
    
    /**
     * @Description (TODO查询联系人姓名唯一的信息)
     * @return
     * @throws Exception
     */
    public String findAttnOnlyOneInfo() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		String name = (String) dataMap.get("name");
		attnVo = attnService.findAttnByName(name);
		if(attnVo==null){
			//没有
			// 返回json数据   data.datas.
		    AjaxReturnInfo rtn = null;
		    rtn = AjaxReturnInfo.success("");
		    rtn.setMessage("0");
		    resultMap = rtn.getReturnMap();
		}else{
			//存在返回数据
			// 返回json数据   data.datas.
		    AjaxReturnInfo rtn = null;
		    rtn = AjaxReturnInfo.success("");
		    rtn.setMessage("1");
		    String info = attnVo.getName()+" "+
		    		attnVo.getGender_name()+" "+
		    		attnVo.getPosition()+"<br/>"+
		    		(attnVo.getUniv_name()==null?"":attnVo.getUniv_name()+" ")+
		    		(attnVo.getUniv_branch_name()==null?"":attnVo.getUniv_branch_name()+" ")+
		    		(attnVo.getCollege_name()==null?"<br/>":attnVo.getCollege_name()+"<br/>")+
		    		(attnVo.getMobile()==null?"":attnVo.getMobile()+" ")+
		    		(attnVo.getPhone()==null?"":attnVo.getPhone()+" ");
		    rtn.add("info", info);
		    resultMap = rtn.getReturnMap();	
		}
	    return "json";
    }
}
