
/**
 * Project Name:clps_mms_copyright_201610
 * File Name:PageVo.java
 * Package Name:com.clps.mms.sys.vo
 * Date:2016年11月29日下午2:53:24
 * Copyright (c) 2016, tsqking@163.com All Rights Reserved.
 *
 */
package com.clps.common.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ClassName: PageVo. Function: TODO ADD FUNCTION. Reason: TODO ADD REASON(可选).
 * date: 2016年11月29日 下午2:53:24
 * 
 * @author charles.tan
 * @version
 * 
 */
public class PageVo<T> {
	private HashMap<String, String> where;// 条件存放

	// 自定义搜索条件存放
	private HashMap<String, String> other;

	// 排序
	private LinkedHashMap<String, String> sort;// 请求参数

	// 当前页码
	private int offset;// 请求参数

	// 每页条数
	private int limit;// 请求参数

	// 数据
	private List<T> list;// 返回参数

	// 总条数
	private String count;// 返回参数
	// 排序方法
	private String sortorder;
	// 按什么排序
	private String sortname;

	// 时间范围搜索 yyyy-mm-dd HH:mm:ss
	private HashMap<String, String> dateTime;
	// 时间范围搜索 yyyy-mm-dd HH:mm:ss
	private HashMap<String, String> dateTime2;

	public PageVo() {
		super();
	}

	public PageVo(HashMap<String, String> where, HashMap<String, String> other, LinkedHashMap<String, String> sort,
			int offset, int limit, List<T> list, String count, String sortorder, String sortname,
			HashMap<String, String> dateTime, HashMap<String, String> dateTime2) {
		super();
		this.where = where;
		this.other = other;
		this.sort = sort;
		this.offset = offset;
		this.limit = limit;
		this.list = list;
		this.count = count;
		this.sortorder = sortorder;
		this.sortname = sortname;
		this.dateTime = dateTime;
		this.dateTime2 = dateTime2;
	}

	public HashMap<String, String> getWhere() {
		return where;
	}

	public HashMap<String, String> getOther() {
		return other;
	}

	public LinkedHashMap<String, String> getSort() {
		return sort;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public List<T> getList() {
		return list;
	}

	public String getCount() {
		return count;
	}

	public String getSortorder() {
		return sortorder;
	}

	public String getSortname() {
		return sortname;
	}

	public HashMap<String, String> getDateTime() {
		return dateTime;
	}

	public HashMap<String, String> getDateTime2() {
		return dateTime2;
	}

	public void setWhere(HashMap<String, String> where) {
		this.where = where;
	}

	public void setOther(HashMap<String, String> other) {
		this.other = other;
	}

	public void setSort(LinkedHashMap<String, String> sort) {
		this.sort = sort;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public void setDateTime(HashMap<String, String> dateTime) {
		this.dateTime = dateTime;
	}

	public void setDateTime2(HashMap<String, String> dateTime2) {
		this.dateTime2 = dateTime2;
	}

	@Override
	public String toString() {
		return "PageVo [where=" + where + ", other=" + other + ", sort=" + sort + ", offset=" + offset + ", limit="
				+ limit + ", list=" + list + ", count=" + count + ", sortorder=" + sortorder + ", sortname=" + sortname
				+ ", dateTime=" + dateTime + ", dateTime2=" + dateTime2 + "]";
	}

}
