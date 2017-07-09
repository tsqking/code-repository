package com.clps.tmp.core.sm.action;

import java.util.HashMap;
import java.util.List;

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
import com.clps.tmp.core.sm.vo.OptionVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.OptionService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 选项框管理
  * @ClassName: OptionAction
  * @Description: TODO
  * @author Seven
  * @date 2015年10月11日 
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("option")
@Results({ 
		@Result(name = "toOptionManagementPage", location = "option/optionManagement.jsp"),
		@Result(name = "toOptionManagementEditPage", location = "option/optionManagementEdit.jsp"),
		@Result(name = "toOptionManagementAddPage", location = "option/optionManagementAdd.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class OptionAction extends BaseAction implements ModelDriven {

	private OptionVo option;
	// json返回数据map
	private HashMap<String, Object> resultMap;

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void setOption(OptionVo option) {
		this.option = option;
	}

	public OptionVo getOption() {
		return option;
	}

	@Override
	public Object getModel() {
		if (option == null) {
			option = new OptionVo();
		}
		return option;
	}

	@Resource
	private OptionService optionService;
	/**
	 * 跳转选项管理界面 
	 */
	public String toOptionManagementPage(){
		return "toOptionManagementPage";
	}
	/**
	 * 通过选项组的键值获取此组下所有选项 
	 */
	public String getOptionsByGPVal() throws Exception{
		AjaxReturnInfo rtn=null;
		String value=this.httpRequest.getParameter("value");
		List<SelectVo> list=optionService.getOptionsByGPVal(value);
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	/**
	 * 根据level获取所有选项 
	 */
	public String getOptionsByLevel() throws Exception{
		AjaxReturnInfo rtn=null;
		String level=this.httpRequest.getParameter("level");
		List<SelectVo> list=optionService.getOptionsByLevel(level);
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	/**
	 * 获取所有的选项组 
	 */
	public String getOptionGroups() throws Exception{
		AjaxReturnInfo rtn=null;
		List<SelectVo> list=optionService.getOptionGroups();
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
 		return "json";
	}
	/**
	 * 获取选项管理界面中的分页数据 
	 */
	public String getOptionPage() throws Exception{
		DataTableUtil<OptionVo> dataTableUtil=new DataTableUtil<OptionVo>();
		PageVo<OptionVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(option.getSearchOptionStatus()!=null && !option.getSearchOptionStatus().equals("")){
			where1.put("enable", option.getSearchOptionStatus());
		}
		if(option.getSearchOptionType()!=null && !option.getSearchOptionType().equals("")){
			where1.put("type", option.getSearchOptionType());
		}
		if(option.getSearchOptionGroups()!=null && !option.getSearchOptionGroups().equals("")){
			where1.put("b.`value`", option.getSearchOptionGroups());
		}
		pageVo.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(option.getSearchOptionName()!=null && !option.getSearchOptionName().equals("")){
			where2.put("name[lang]", option.getSearchOptionName());
		}
		pageVo.setWhere2(where2);
		//3.获取数据	
		pageVo = optionService.queryOptionPage(pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 根据选项ID删除选项(组) 
	 */
	public String deleteOption() throws Exception{
		AjaxReturnInfo rtn=null;
		String lang=(String) session.get(SystemConstant.LANG);
		log.info("删除的option id:"+option.getId());
		int count=optionService.deleteOption(option.getId());
		if(count==0)
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"删除失败!":"Delete failed!");
		else
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("成功删除数据"+count+"条!"):("Delete success!"));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转选项编辑界面 
	 */
	public String toOptionManagementEditPage() throws Exception{
		log.info("传去修改的option id:"+option.getId());
		option=optionService.getOptionByID(option.getId());
		return "toOptionManagementEditPage";
	}
	/**
	 * 更新选项 
	 */
	public String updateOption() throws Exception{
		AjaxReturnInfo rtn=null;
		String lang=(String) session.get(SystemConstant.LANG);
		log.info("更新的option id:"+option.getId());
		if("0".equals(option.getParent_id())){//需注意键值不能重复
			log.info("更新选项组");
			int count=optionService.checkOptionGroupByVal(option.getValue(),option.getId());
			if(count!=0){
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("更新失败!组键"+option.getValue()+"已经被使用!"):("Update failed! The option group key:"+option.getValue()+" have been used!"));
				resultMap=rtn.getReturnMap();
				return "json";
			}
		}else{//需注意键值不能重复
			log.info("更新选项");
			int count=optionService.checkOptionsByValPid(option.getValue(),option.getParent_id(),option.getId());
			if(count!=0){
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("更新失败!键值"+option.getValue()+"已经被使用!"):("Update failed! The option key:"+option.getValue()+" have been used!"));
				resultMap=rtn.getReturnMap();
				return "json";
			}
		}
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		option.setUpdate_person(user.getName());
		option.setLast_update_time(option.getUpdate_time());
		option.setUpdate_time(DateTimeUtil.nowToDatabase());
		int count=optionService.updateOption(option);
		if(count==0)
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"更新失败!数据可能已经被其他用户更新,请查看!":"Update failed! The data may be updated by another user, please check~");
		else
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("成功更新数据"+count+"条!"):("Update success!"));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转选项添加界面 
	 */
	public String toOptionManagementAddPage() throws Exception{
		log.info("传去添加的option父级id:"+option.getId());
		option.setParent_id(option.getId());
		option.setParent_name(option.getParent_name());
		if("0".equals(option.getId())){//选项组的添加
			option.setLevel("1");
			option.setType("0");
		}else{//选项的添加
			option.setLevel("2");
			option.setType("1");
		}
		return "toOptionManagementAddPage";
	}
	/**
	 * 添加选项
	 */
	public String addOption() throws Exception{
		AjaxReturnInfo rtn=null;
		log.info("添加的option父级id:"+option.getParent_id());
		String lang=(String) session.get(SystemConstant.LANG);
		if("0".equals(option.getParent_id())){//添加选项组，需注意键值不能重复
			int count=optionService.checkOptionGroupByVal(option.getValue());
			if(count!=0){
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("添加失败!组键"+option.getValue()+"已经被使用!"):("Add failed! The option group key:"+option.getValue()+" have been used!"));
				resultMap=rtn.getReturnMap();
				return "json";
			}
		}else{//添加选项，需注意键值不能重复
			int count=optionService.checkOptionsByValPid(option.getValue(),option.getParent_id());
			if(count!=0){
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("添加失败!键值"+option.getValue()+"已经被使用!"):("Add failed! The option key:"+option.getValue()+" have been used!"));
				resultMap=rtn.getReturnMap();
				return "json";
			}
		}
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		option.setCreate_person(user.getName());
		option.setCreate_time(DateTimeUtil.nowToDatabase());
		option.setUpdate_person(user.getName());
		option.setUpdate_time(DateTimeUtil.nowToDatabase());
		int count=optionService.addOption(option);
		if(count==0)
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"添加失败!请重试!":"Add Failed! Please try again!");
		else
			rtn=AjaxReturnInfo.success("zh_CN".equals(lang)?("成功添加数据"+count+"条!"):("Add success!"));
		resultMap=rtn.getReturnMap();
		return "json";
	}
}
