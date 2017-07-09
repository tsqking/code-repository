package com.clps.tmp.core.common.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.dao.MBDao;
import com.clps.tmp.core.sm.constant.SystemConstant;

public abstract class MBBaseService {
	protected Logger log=Logger.getLogger(getName());
	@Resource
	protected MBDao mbDao;
	protected String getName(){
		return getClass().getName();
	}
	/**
	 * 获取国际化语言参数<br><br>
	 * 
	 * 2016年3月25日 Seven
	 */
	public void getLangParam(Map<String,Object> param){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		String lang=( null==(String)session.getAttribute(SystemConstant.LANG) || "zh_CN".equals((String)session.getAttribute(SystemConstant.LANG)) )?"":((String)session.getAttribute(SystemConstant.LANG));
		if("en_US".equals(lang)){
			param.put("lang", "_en_US");
		}else{
			param.put("lang", "");
		}
	}
	/**
	 * 格式化时间范围查找参数。<br><br>
	 * 如 Map中存放key为create_time，值为2016-03-24 00:00:00 ~ 2016-03-24 23:59:59<br>
	 * 转换后变为两个：<br>&nbsp;&nbsp;&nbsp;一个key为create_time_start，值为20160324 000000<br>&nbsp;&nbsp;&nbsp;一个key为create_time_end，值为20160324 235959<br>
	 * 2016年3月25日 Seven
	 */
	public void fmtTimeScopeQueryParam(Map<String,Object> param, String key){
		Object value=param.get(key);
		if(value!=null){
			String[] timeArr=((String)value).split("~");
			String start=DateTimeUtil.systemToDatabase(timeArr[0].trim());
			String end=DateTimeUtil.systemToDatabase(timeArr[1].trim());
			//param.remove(key);
			param.put(key+"_start", start);
			param.put(key+"_end", end);
		}
	}
	/**
	 * 格式化日期范围查找参数。<br><br>
	 * 如 Map中存放key为create_date，值为2016-03-24 ~ 2016-03-26 <br>
	 * 转换后变为两个：<br>&nbsp;&nbsp;&nbsp;一个key为create_date_start，值为2016-03-24<br>&nbsp;&nbsp;&nbsp;一个key为create_date_end，值为2016-03-26<br>
	 * 2016年3月25日 Seven
	 */
	public void fmtDateScopeQueryParam(Map<String,Object> param, String key){
		Object value=param.get(key);
		if(value!=null){
			String[] timeArr=((String)value).split("~");
			//String start=DateTimeUtil.freeFormate(timeArr[0].trim(), "yyyy-MM-dd", "yyyyMMdd");
			//String end=DateTimeUtil.freeFormate(timeArr[1].trim(), "yyyy-MM-dd", "yyyyMMdd");
			param.put(key+"_start", timeArr[0].trim());
			param.put(key+"_end", timeArr[1].trim());
		}
	}
}
