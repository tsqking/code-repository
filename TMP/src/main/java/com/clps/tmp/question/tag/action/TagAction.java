package com.clps.tmp.question.tag.action;

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
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.question.tag.service.TagService;
import com.clps.tmp.question.tag.vo.TagVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 标签Action
 * @author jevon
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/question")
@Controller
@Scope("prototype")
@Action("tag")
@Results({@Result(name = "toTagManagerPage", location="../question/tag/tagManager.jsp"),
		@Result(name = "addNewTagPage", location="../question/tag/addTagPage.jsp"),
		@Result(name = "json", type = "json", params = { "root", "tagMap" })})
public class TagAction extends BaseAction implements ModelDriven{

	//页面封装数据
	private TagVo tagVo;
	
	private HashMap<String,Object> tagMap;
	
	@Resource
	private TagService tagService;
	
	/**
	 * 跳转到标签管理页面../question/tag!toTagManagerPage.do
	 * @return
	 * @throws Exception
	 */
	public String toTagManagerPage() throws Exception{
		return "toTagManagerPage";
	}
	
	/**
	 * 分页获取所有标签
	 * @return
	 * @throws Exception
	 */
	public String getTagPage() throws Exception{
		BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<TagVo> bootStrapPageVo= tagService.findTagList(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    tagMap=rtn.getReturnMap();
	    return "json";
	}

	/**
	 * 前往添加新标签界面
	 * @return
	 * @throws Exception
	 */
	public String addNewTagPage() throws Exception{
		return "addNewTagPage";
	}
	
	/**
	 * 添加新标签数据
	 * @return
	 * @throws Exception
	 */
	public String addNewTagData() throws Exception{
        //查重
		AjaxReturnInfo rtn = null;
		List<TagVo> list=tagService.getTagByName(tagVo.getName());
		if(list!=null){
			rtn = AjaxReturnInfo.success("2222");//已经存在同名标签
		}else{
			tagVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
			// 调用service方法，操作数据库
	        int tagId = tagService.addTagData(tagVo);
	        // 封装返回数据
	        if (tagId != 0) {
	            rtn = AjaxReturnInfo.success("0000");//添加成功
	            rtn.add("id", tagId);
	        } else {
	            rtn = AjaxReturnInfo.success("1111");//添加不成功
	        }
		}
        tagMap = rtn.getReturnMap();
        return "json";
	}
	
	/**
	 * 禁用标签
	 * @return
	 * @throws Exception
	 */
	public String changeTagStateDisable() throws Exception {
        AjaxReturnInfo rtn = null;
        int effect = tagService.changeTagStateDisable(tagVo);
        if (effect<=0)// 0-使用中，不能删除
            rtn = AjaxReturnInfo.failed(String.valueOf(effect));
        else
            rtn = AjaxReturnInfo.success("true");
        tagMap = rtn.getReturnMap();
        return "json";
	}
	
	/**
	 * 启用标签
	 * @return
	 * @throws Exception
	 */
	public String changeTagStateEnable() throws Exception {
        AjaxReturnInfo rtn = null;
        int effect = tagService.changeTagStateEnable(tagVo);
        if (effect<=0)// 未找到数据
            rtn = AjaxReturnInfo.failed(String.valueOf(effect));
        else
            rtn = AjaxReturnInfo.success("true");
        tagMap = rtn.getReturnMap();
        return "json";
	}
	
	/**
	 * 获取所有的标签（用于下拉框初始化）
	 */
	public String getAllTag() throws Exception{
		AjaxReturnInfo rtn=null;
		List<SelectVo> list=tagService.getAllTag();
		rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		tagMap=rtn.getReturnMap();
 		return "json";
	}
	
	public TagVo getTagVo() {
		return tagVo;
	}

	public void setTagVo(TagVo tagVo) {
		this.tagVo = tagVo;
	}

	public HashMap<String, Object> getTagMap() {
		return tagMap;
	}

	public void setTagMap(HashMap<String, Object> tagMap) {
		this.tagMap = tagMap;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	@Override
	public Object getModel() {
		if (tagVo == null) {
			tagVo = new TagVo();
		}
		return tagVo;
	}
	
}
