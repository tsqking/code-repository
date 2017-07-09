
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:DataTableHelper.java
 * Package Name:com.clps.mms.util
 * Date:2016年11月29日上午11:06:38
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
 */
package com.clps.mms.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.clps.mms.sys.vo.PageVo;

import net.sf.json.JSONObject;

/**
 * ClassName: DataTableHelper.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2016年11月29日 上午11:06:38 
 *
 * @author tony.tan
 * @version 
 * 
 */
public class DataTableHelper<T> {
private final String PAGE="page";
private final String PAGESIZE="pagesize";
private final String SORTNAME="sortname";
private final String SORTORDER="sortorder";
private final String WHERE="where";
private int iDisplayStart = 0;
private int iDisplayLength = 0;
private int pageIndex;

public PageVo<T> getTableData(HttpServletRequest request){
	PageVo<T> pv=new PageVo<>();
	request=ServletActionContext.getRequest();
	HashMap<String, String> where2=new HashMap<>();
	LinkedHashMap<String, String> sort=new LinkedHashMap<>();
	//System.out.println("start....");
	if (request.getParameter(PAGE)!=null&&!request.getParameter(PAGE).equals("")) {
		pageIndex=Integer.parseInt(request.getParameter(PAGE));
		System.out.println("pageIndex:"+pageIndex);
	}
	if (request.getParameter(PAGESIZE)!=null&&!request.getParameter(PAGESIZE).equals("")) {
		iDisplayLength=Integer.parseInt(request.getParameter(PAGESIZE));
		System.out.println("size:"+iDisplayLength);
	}
	if (request.getParameter(SORTNAME)!=null&&!request.getParameter(SORTNAME).equals("")) {
		        sort.put(SORTNAME, request.getParameter(SORTNAME));
	}
	if (request.getParameter(SORTORDER)!=null&&!request.getParameter(SORTORDER).equals("")) {
		        sort.put(SORTORDER, request.getParameter(SORTORDER));
	}
	if (request.getParameter("pageWhere1")!=null&&!request.getParameter("pageWhere1").equals("")) {
		System.out.println("pageWhere1:"+request.getParameter("pageWhere1"));
		where2.put("pageWhere1", request.getParameter("pageWhere1"));
	}else{
		where2.put("pageWhere1", "");
	}
	if (request.getParameter("pageWhere2")!=null&&!request.getParameter("pageWhere2").equals("")) {
		System.out.println("pageWhere2:"+request.getParameter("pageWhere2"));
		where2.put("pageWhere2", request.getParameter("pageWhere2"));
	}else{
		where2.put("pageWhere2", "");
	}
	if (request.getParameter("pageWhere3")!=null&&!request.getParameter("pageWhere3").equals("")) {
		System.out.println("pageWhere3:"+request.getParameter("pageWhere3"));
		where2.put("pageWhere3", request.getParameter("pageWhere3"));
	}else{
		where2.put("pageWhere3", "");
	}
	if (request.getParameter("pageWhere4")!=null&&!request.getParameter("pageWhere4").equals("")) {
		System.out.println("pageWhere4:"+request.getParameter("pageWhere4"));
		where2.put("pageWhere4", request.getParameter("pageWhere4"));
	}else{
		where2.put("pageWhere4", "");
	}
	if (request.getParameter("pageWhere5")!=null&&!request.getParameter("pageWhere5").equals("")) {
		System.out.println("pageWhere5:"+request.getParameter("pageWhere5"));
		where2.put("pageWhere5", request.getParameter("pageWhere5"));
	}else{
		where2.put("pageWhere5", "");
	}
	if (request.getParameter("pageWhere6")!=null&&!request.getParameter("pageWhere6").equals("")) {
		System.out.println("pageWhere6:"+request.getParameter("pageWhere6"));
		where2.put("pageWhere6", request.getParameter("pageWhere6"));
	}else{
		where2.put("pageWhere6", "");
	}
	pv.setPage(pageIndex);
	pv.setLimitPage(iDisplayLength);
	pv.setSort(sort);
	pv.setWhere2(where2);
	return pv;
}
public Map<String, Object> getReturnMap(PageVo<T> pv){
	Map<String, Object> map=new HashMap<>();
	map.put("Rows", pv.getList());
	map.put("Total", pv.getAllcount());
	return map;
}
}

