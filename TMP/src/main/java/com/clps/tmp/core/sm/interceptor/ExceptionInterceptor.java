/*
* @Title:		ExceptionInterceptor.java 
* @Package		org.vbs.front.common.intercept 
* @Description:	TODO(用一句话描述该文件做什么) 
* @author		seven.sun
* @date			2015年6月30日 下午3:18:52 
* @version		V1.0  
*/ 
package com.clps.tmp.core.sm.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author	seven
 */
public class ExceptionInterceptor implements Interceptor {
	@Override
	public void destroy() {
		System.out.println("异常拦截器销毁...");
	}

	@Override
	public void init() {
		System.out.println("异常拦截器启动...");
	}

	@Override
	public String intercept(ActionInvocation invocation){
		String result = null;// Action的返回值
		try {
			// 运行被拦截的Action,期间如果发生异常会被catch住
			result = invocation.invoke();
			return result;
		} catch (Exception e) {
			/**
			 * 处理异常
			 */
			String errorMsg = "出现错误信息，请查看日志！";
			/**
			 * log4j记录日志
			 */
			Logger log = Logger.getLogger(invocation.getAction().getClass());
			log.error(errorMsg, e);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse(); 
			//分ajax请求与普通请求
			if (request.getHeader("accept").indexOf("application/json") > -1
		             || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
		        System.out.println("Ajax请求回应(exception)");
		        ValueStack stack = invocation.getStack();
		        StringWriter sw = new StringWriter(); 
		        PrintWriter pw = new PrintWriter(sw);
		        e.printStackTrace(pw);
		        /*
		         * ajax 返回AjaxReturnInfo 对象
		         */
		        AjaxReturnInfo rtn=AjaxReturnInfo.failed("Error! Pls retry or contact system manager!");
		        rtn.setException(e);
		        stack.set("exceptionResult", rtn.getReturnMap());
		        return "exception";
		     }
		     else{//如果是普通请求
		    	 System.out.println("正常请求回应(exception)");
		    	 return "denied";
		    }
		}
	}

}
