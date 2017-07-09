package com.clps.tmp.common.vo;

import java.util.List;
import java.util.Map;

//bootstrap-table的视图类,封装前台传过来的参数以及后台发往前台的参数。

public class BtTableVo<T> {
    
	//排序
	private String order;
	//排序字段
	private String sort;
	//页码
	private int offset;
	//单页数据量
	private int limit;
	//总条数
	private int total;
	//返回数据
	private List<T> rows;
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
