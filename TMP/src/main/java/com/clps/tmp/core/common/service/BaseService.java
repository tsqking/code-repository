package com.clps.tmp.core.common.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.clps.tmp.core.common.dao.Dao;
import com.clps.tmp.core.sm.constant.SystemConstant;

public abstract class BaseService {
	@Resource
	protected Dao dao;
	protected Logger log=Logger.getLogger(getName());
	protected String getName(){
		return getClass().getName();
	}
	public String getLang(){
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		String lang=( null==(String)session.getAttribute(SystemConstant.LANG) || "zh_CN".equals((String)session.getAttribute(SystemConstant.LANG)) )?"":("_"+(String)session.getAttribute(SystemConstant.LANG));
		return lang;
	}
}
