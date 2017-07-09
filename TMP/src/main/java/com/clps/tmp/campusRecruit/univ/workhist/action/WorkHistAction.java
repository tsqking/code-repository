package com.clps.tmp.campusRecruit.univ.workhist.action;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.campusRecruit.univ.college.service.CollegeService;
import com.clps.tmp.campusRecruit.univ.university.service.UniversityService;
import com.clps.tmp.campusRecruit.univ.workhist.service.WorkHistService;
import com.clps.tmp.campusRecruit.univ.workhist.vo.WorkHistVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.UserVo;
/**
 * 工作历史信息管理
 * @ClassName: WorkInfoAction
 * @Description: TODO
 * @author Wellen
 * @date 2016年3月14日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/univ")
@Controller
@Scope("prototype")   
@Action("workHist")
@Results({  @Result(name = "toWorkHistManagePage", location ="../campusRecruit/univ/workhist/workHistManage.jsp"),
			@Result(name = "toAddWorkHistPage", location ="../campusRecruit/univ/workhist/addWorkHist.jsp"),
			@Result(name = "toEditWorkHistPage", location ="../campusRecruit/univ/workhist/editWorkHist.jsp"),
			@Result(name = "toViewWorkHistPage", location ="../campusRecruit/univ/workhist/viewWorkHist.jsp"),
            @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class WorkHistAction extends BaseAction {
	
	// 页面属性 页面通过'xx.属性'方式
    private WorkHistVo workHistVo;
    @Resource
    WorkHistService workHistService;
    @Resource
    UniversityService universityService;
    @Resource
    CollegeService collegeService;
    // json返回数据map
    private HashMap<String, Object> resultMap;
	
	public WorkHistVo getWorkHistVo() {
		return workHistVo;
	}
	public void setWorkHistVo(WorkHistVo workHistVo) {
		this.workHistVo = workHistVo;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
     * 跳转工作历史信息管理界面
     */
    public String toWorkHistManagePage() throws Exception{
        return "toWorkHistManagePage";
    }
    /**
     * 跳转工作历史信息管理界面
     */
    public String toAddWorkHistPage() throws Exception{
        return "toAddWorkHistPage";
    }
    /**
     * 跳转工作历史信息管理界面
     * @throws Exception 
     */
    public String toEditWorkHistPage() throws Exception {
    	// 获取记录序列号id
        String id = String.valueOf(workHistVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        workHistVo=workHistService.selectEditWorkHist(paramMap);
        return "toEditWorkHistPage";
    }
    /**
     * 详细信息查询
     * @throws Exception 
     */
    public String viewWorkHistPage() throws Exception {
    	// 获取记录序列号id
        String id = String.valueOf(workHistVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        workHistVo=workHistService.selectViewWorkHist(paramMap);
        return "toViewWorkHistPage";
    }
    /**
     * 添加工作历史信息
     */
    public String addWorkHist() throws Exception{
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
    	workHistVo.setCreate_time(DateTimeUtil.nowToDatabase());
    	workHistVo.setCreate_user(user.getName());
    	workHistVo.setUpdate_time(DateTimeUtil.nowToDatabase());
    	workHistVo.setUpdate_user(user.getName());
    	// 调用service方法，操作数据库
        int resData =workHistService.addWorkHist(workHistVo);
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
     * 更新工作历史信息
     */
    public String editWorkHistInfo() throws Exception {
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
    	workHistVo.setUpdate_time(DateTimeUtil.nowToDatabase());
    	workHistVo.setUpdate_user(user.getUpdate_person());
        String op_status= workHistVo.getOp_status().substring(0,1);
        workHistVo.setOp_status(op_status);
    	// 调用service方法，操作数据库
        int resData =workHistService.editWorkHist(workHistVo);
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
     * 查询工作历史信息
     */
    public String selectWorkHist() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    if(dataMap.get("opeationTime")!=null){
	    	String opeationtime[]=((String)dataMap.get("opeationTime")).split("~");
	    	System.out.println(opeationtime[0]+"fdskjfhksdhfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
	    	opeationtime[1]=("'"+(opeationtime[1]).trim()+"'").trim();
	    	opeationtime[0]=("'"+(opeationtime[0]).trim()+"'").trim();
	    	 dataMap.put("end", opeationtime[1]);
	    	 dataMap.put("start", opeationtime[0]);
	    }
	    BtTableVo<WorkHistVo> bootStrapPageVo= workHistService.selectWorkHist(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
    	return "json";
    }
    /**
     * 删除工作历史信息
     */
    public String deleteWorkHist() throws Exception {
    	 BtTableUtil bootStrapTable = new BtTableUtil();
    	 Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
    	 String[] ids=((String)dataMap.get("id")).split(",");
         int deleteRtn =workHistService.deleteWorkHist(ids);
         // 封装返回数据
         AjaxReturnInfo rtn = null;
         if (deleteRtn >= 1 ) {
             rtn = AjaxReturnInfo.success("0");
         } else {
             rtn = AjaxReturnInfo.success("1");
         }
         resultMap = rtn.getReturnMap();
    	return "json";
    }
}
