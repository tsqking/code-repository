package com.clps.tmp.common.region.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.region.service.ChinaRegionService;
import com.clps.tmp.common.region.service.impl.ChinaRegionImpl;
import com.clps.tmp.common.region.vo.ChinaRegionVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.vo.SelectVo;

@SuppressWarnings({ "serial"})
@ParentPackage("publicPackage")
@Namespace("/region")
@Controller
@Scope("prototype")   
@Action("chinaregion")
@Results({@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class ChinaRegionAction  extends BaseAction{
	ChinaRegionVo chinaRegionVo;
	ChinaRegionService  chinaRegionService;
	private HashMap<String, Object> resultMap;
	
	 public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public ChinaRegionVo getChinaRegionVo() {
		return chinaRegionVo;
	}

	public void setChinaRegionVo(ChinaRegionVo chinaRegionVo) {
		this.chinaRegionVo = chinaRegionVo;
	}

	public ChinaRegionService getChinaRegionService() {
		return chinaRegionService;
	}

	public void setChinaRegionService(ChinaRegionService chinaRegionService) {
		this.chinaRegionService = chinaRegionService;
	}
	//添加省市信息
    @Test
	public void insertChinaRegion() {
		try {
			
			System.out.println("清空表数据成功,开始插入省市区代码和名字.....");
			String fileName = ChinaRegionImpl.class.getClassLoader().getResource("/").getPath()  + "chinaRegion.txt";
			fileName = fileName.substring(1, fileName.length());
		    
			chinaRegionService.addChinaRegion(fileName);
			System.out.println("插入省市区代码和名字成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    //查询省市信息
    public String findRegionOption() throws Exception{
    	// 获取父级id
        String code = String.valueOf(chinaRegionVo.getParentId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code);
        List<SelectVo> selectList;
        selectList = chinaRegionService.findRegion(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", selectList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    } 
}
