package com.clps.tmp.campusRecruit.univ.college.action;

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

import com.clps.tmp.campusRecruit.univ.college.service.CollegeService;
import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.campusRecruit.univ.university.service.UnivBranchService;
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
 * 学院信息管理
 * 
 * @ClassName: CollegeAction
 * @Description: TODO
 * @author Wellen
 * @date 2016年3月14日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/univ")
@Controller
@Scope("prototype")   
@Action("college")
@Results({ @Result(name = "toCollegeManagePage", location ="../campusRecruit/univ/college/collegeManage.jsp"),
	       @Result(name = "toAddCollegePage", location ="../campusRecruit/univ/college/addCollege.jsp"),
	       @Result(name = "toeditCollegePage", location ="../campusRecruit/univ/college/editCollege.jsp"),
           @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class CollegeAction extends BaseAction {
	// 页面属性 页面通过'xx.属性'方式
    private CollegeVo collegeVo;
    // json返回数据map
    private HashMap<String, Object> resultMap;
    // 页面属性 页面通过'xx.属性'方式
    private UniversityVo universityVo;
	@Resource
    UnivBranchService univBranchService;
    @Resource
    UniversityService universityService;
    @Resource
    CollegeService collegeService;
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
     * 跳转学院信息管理界面
	 * @throws Exception 
     */
    public String collegeManagePage() throws Exception {
    	/*// 获取记录序列号id
        String id = String.valueOf(universityVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        universityVo=universityService.selectEditUniv(paramMap);*/
        return "toCollegeManagePage";
    }
    /**
     * 跳转添加学院学院界面
	 * @throws Exception 
     */
    public String addcollegePage() throws Exception {
    	// 获取记录序列号id
        String id = String.valueOf(universityVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        universityVo=universityService.selectEditUniv(paramMap);
        return "toAddCollegePage";
    }
    /**
     * 添加学院信息
     */
    public String addCollege() throws Exception{
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        collegeVo.setCreate_time(DateTimeUtil.nowToDatabase());
        collegeVo.setCreate_user(user.getName());
        collegeVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        collegeVo.setUpdate_user(user.getName());
        collegeVo.setUniv_id(universityVo.getId());
    	// 调用service方法，操作数据库
        int resData = collegeService.addCollege(collegeVo);
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
     * 查询学院信息
     */
    public String selectCollege() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    if(dataMap.get("universityVo.id")!=null){
	    	System.out.println("fdskldjfklsdf"+dataMap.get("universityVo.id").toString());
	    }
	    BtTableVo<CollegeVo> bootStrapPageVo= collegeService.selectCollege(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
    	return "json";
    }
    /**
     * 跳转更新学院信息管理界面
	 * @throws Exception 
     */
    public String editUniversityPage() throws Exception {
    	// 获取记录序列号id
        String id = String.valueOf(collegeVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        collegeVo=collegeService.selectEditCollege(paramMap);
        return "toeditCollegePage";
    }
    /**
     * 更新学院信息
     */
    public String editCollege() throws Exception {
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        collegeVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        collegeVo.setUpdate_user(user.getUpdate_person());
    	// 调用service方法，操作数据库
        int resData = collegeService.editCollege(collegeVo);
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
     * 删除学院信息
     */
    public String deleteCollege() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
   	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
   	    String[] ids=((String)dataMap.get("id")).split(",");
        int deleteRtn = collegeService.deleteCollege(ids);
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
     * 获取学院名称
     * */
    public String selectCollegeName() throws Exception {
    	// 获取父级id
        String univ_id = String.valueOf(collegeVo.getUniv_id());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("univ_id", univ_id);
        List<SelectVo> collegeList;
        collegeList =collegeService.selectCollegeName(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", collegeList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
}
