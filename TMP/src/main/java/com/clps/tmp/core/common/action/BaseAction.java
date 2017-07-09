package com.clps.tmp.core.common.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @author seven 
 * base action
 */
public class BaseAction extends ActionSupport implements 
	RequestAware,SessionAware, ApplicationAware,ServletResponseAware, ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1662497966295491210L;
	protected Logger log=Logger.getLogger(getName());
	protected Map<String, Object> application;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected HttpServletRequest httpRequest;
	protected HttpServletResponse httpResponse;
	
	protected String getName(){
		return getClass().getName();
	}
	/**
	 * 直接以流的方式输出内容到前端
	 * 2016年4月11日 Seven
	 */
	protected void print(String content) throws Exception{
		this.httpResponse.setContentType("text/html");
		PrintWriter out = this.httpResponse.getWriter();
		out.println(content);
		out.flush();
		out.close();
	}
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpRequest) {
		this.httpRequest=httpRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpResponse) {
		this.httpResponse=httpResponse;
	}

	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}
	
}
