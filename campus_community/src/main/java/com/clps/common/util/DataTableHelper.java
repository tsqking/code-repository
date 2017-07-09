
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:DataTableHelper.java
 * Package Name:com.clps.mms.util
 * Date:2016年11月29日上午11:06:38
 * Copyright (c) 2016, tsqking@163.com All Rights Reserved.
 *
 */
package com.clps.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DataTableHelper. Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). date: 2016年11月29日 上午11:06:38
 *
 * @author charles.tan
 * @version
 * 
 */
public class DataTableHelper<T> {
	private final String OFFSET = "offset";
	private final String LIMIT = "limit";
	private final String SORTNAME = "sortname";
	private final String SORTORDER = "sortorder";
	private int offset = 0;
	private int limit = 0;

	public PageVo<T> getTableData(Map<String, Object> map) {
		PageVo<T> pv = new PageVo<>();
		HashMap<String, String> where = new HashMap<>();
		String sortorder = null;
		String sortname = null;
		// 前台传来 page值作为定位第几页
		if (map.get(OFFSET) != null && !map.get(OFFSET).equals("")) {
			offset = Integer.parseInt(map.get(OFFSET).toString());
		}
		if (!map.get(LIMIT).equals("") && map.get(LIMIT) != null) {
			limit = Integer.parseInt(map.get(LIMIT).toString());
		}
		if (!map.get(SORTORDER).equals("") && map.get(SORTORDER) != null) {
			sortorder = map.get(SORTORDER).toString();
		}
		if (!map.get(SORTNAME).equals("") && map.get(SORTNAME) != null) {
			sortname = map.get(SORTNAME).toString();
		}
		if (map.get("pageWhere1")!=null&&!map.get("pageWhere1").equals("")) {
			System.out.println("pageWhere1:"+map.get("pageWhere1"));
			where.put("pageWhere1", (String) map.get("pageWhere1"));
		}else{
			where.put("pageWhere1", "");
		}
		if (map.get("pageWhere2")!=null&&!map.get("pageWhere2").equals("")) {
			System.out.println("pageWhere2:"+map.get("pageWhere2"));
			where.put("pageWhere2", (String) map.get("pageWhere2"));
		}else{
			where.put("pageWhere2", "");
		}
		if (map.get("pageWhere3")!=null&&!map.get("pageWhere3").equals("")) {
			System.out.println("pageWhere3:"+map.get("pageWhere3"));
			where.put("pageWhere3", (String) map.get("pageWhere3"));
		}else{
			where.put("pageWhere3", "");
		}
		if (map.get("pageWhere4")!=null&&!map.get("pageWhere4").equals("")) {
			System.out.println("pageWhere4:"+map.get("pageWhere4"));
			where.put("pageWhere4", (String) map.get("pageWhere4"));
		}else{
			where.put("pageWhere4", "");
		}
		if (map.get("pageWhere5")!=null&&!map.get("pageWhere5").equals("")) {
			System.out.println("pageWhere5:"+map.get("pageWhere5"));
			where.put("pageWhere5", (String) map.get("pageWhere5"));
		}else{
			where.put("pageWhere5", "");
		}
		if (map.get("pageWhere6")!=null&&!map.get("pageWhere6").equals("")) {
			System.out.println("pageWhere6:"+map.get("pageWhere6"));
			where.put("pageWhere6", (String) map.get("pageWhere6"));
		}else{
			where.put("pageWhere6", "");
		}
		pv.setLimit(limit);
		pv.setSortname(sortname);
		pv.setSortorder(sortorder);
		pv.setOffset(offset);
		pv.setWhere(where);
		return pv;
	}

	public Map<String, Object> getReturnMap(PageVo<T> pv) {
		Map<String, Object> map = new HashMap<>();
		map.put("rows", pv.getList());
		map.put("total", pv.getCount());
		return map;
	}
}
