package com.clps.tmp.core.sm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clps.tmp.core.sm.constant.SystemConstant;

/**
 * @author Seven
 *
 * 2015年10月29日
 */
public class CheckLoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("登录过滤器初始化...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("--登录验证过滤");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session=req.getSession(false);
		String url=req.getRequestURI()+"?"+req.getQueryString();
		System.out.println("URL:"+url);
		if(session==null){//no login //session.getAttribute(SystemConstant.USER)==null
			if(url.toUpperCase().indexOf("LOGIN.JSP")>-1 ||
					url.toUpperCase().indexOf("LOGINSYSTEM.DO")>-1 ||
					url.toUpperCase().indexOf("LOGINSYSTEM.ACTION")>-1 ||
					url.toUpperCase().indexOf("FORGETPASSWORD.DO")>-1 ||
					
					url.toUpperCase().indexOf("TOONLINETESTPAGE.DO")>-1 ||//教学跳转在线考试登陆界面
					url.toUpperCase().indexOf("EXAMLOGIN.JSP")>-1 ||//访问在线考试登陆界面
					url.toUpperCase().indexOf("LOGINEXAMSYSTEM.DO")>-1 ||//登陆在线考试界面
					
					url.toUpperCase().indexOf("DOWNLOADCHROME.DO")>-1 //下载Chrome浏览器
					)
				chain.doFilter(request, response);
			else{
				if(url.toUpperCase().indexOf("ONLINETEST")>-1 && 
						url.toUpperCase().indexOf("PAPERNO=")>-1 && 
						url.toUpperCase().indexOf("PLANID=")>-1){//如果是在在线测试阶段，失去session了应该跳转到相应考试登陆界面
					String planId=getParamValue(url,"planId");
					String paperNo=getParamValue(url,"paperNo");
					res.sendRedirect(req.getContextPath()+"/"+planId+"/"+paperNo+".html");
				}else{
					res.sendRedirect(req.getContextPath()+"/login.jsp");
				}
			}
		}else{//login
			if(session.getAttribute(SystemConstant.USER)==null){//not real login 
				if(url.toUpperCase().indexOf("LOGIN.JSP")>-1 ||
						url.toUpperCase().indexOf("LOGINSYSTEM.DO")>-1 ||
						url.toUpperCase().indexOf("LOGINSYSTEM.ACTION")>-1 ||
						url.toUpperCase().indexOf("FORGETPASSWORD.DO")>-1 ||
						
						url.toUpperCase().indexOf("TOONLINETESTPAGE.DO")>-1 ||
						url.toUpperCase().indexOf("EXAMLOGIN.JSP")>-1 ||
						url.toUpperCase().indexOf("LOGINEXAMSYSTEM.DO")>-1 ||
						
						url.toUpperCase().indexOf("DOWNLOADCHROME.DO")>-1 //下载Chrome浏览器
						)
					chain.doFilter(request, response);
				else{
					if(url.toUpperCase().indexOf("ONLINETEST")>-1 && 
							url.toUpperCase().indexOf("PAPERNO=")>-1 && 
							url.toUpperCase().indexOf("PLANID=")>-1){//如果是在在线测试阶段，失去session了应该跳转到相应考试登陆界面
						String planId=getParamValue(url,"planId");
						String paperNo=getParamValue(url,"paperNo");
						res.sendRedirect(req.getContextPath()+"/"+planId+"/"+paperNo+".html");
					}else{
						res.sendRedirect(req.getContextPath()+"/login.jsp");
					}
				}
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		System.out.println("登录过滤器销毁...");
	}

	public static String getParamValue(String source,String paramName){
		int index=source.indexOf(paramName);
		if(index>-1){
			StringBuilder result=new StringBuilder(20);
			int length=source.length();
			for(int i=index+1+paramName.length();i<length;i++){
				char c=source.charAt(i);
				if(c=='&')
					break;
				else
					result.append(c);
			}
			return result.toString();
		}else{
			return null;
		}
	}
}
