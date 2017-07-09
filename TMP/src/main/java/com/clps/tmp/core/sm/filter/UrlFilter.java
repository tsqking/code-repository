package com.clps.tmp.core.sm.filter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.clps.tmp.common.util.StringUtil;

public class UrlFilter implements Filter {
	private static Map<String, Object> urlMapping =null;
	static{
		String classPath=UrlFilter.class.getClassLoader().getResource("/").getPath();
		String fileName = classPath.substring(1,classPath.indexOf("classes"))+"urlrewrite.xml";
		loadRewriteUrl(fileName);
		for(Map.Entry<String, Object> entry:urlMapping.entrySet()){
			System.out.println("******"+entry.getKey()+"==="+entry.getValue());
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("URL过滤器启动...");

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getServletPath();
		System.out.println("请求的uri:"+uri);
		String rewriteUrl = getRewriteUrl(uri);
		System.out.println("实际的uri:"+rewriteUrl);
		if (null != rewriteUrl) {
			request.getRequestDispatcher(rewriteUrl).forward(request, response);
			return;
		}
		chain.doFilter(req, resp);

	}

	@Override
	public void destroy() {
		System.out.println("URL过滤器销毁...");
	}

	private String getRewriteUrl(String sourceUri){
		Object value=urlMapping.get(sourceUri);
		if(value==null){
			return null;
		}
		return (String)value;
	}
	
	private static void loadRewriteUrl(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		urlMapping = new HashMap<String, Object>();
		try {
			Document document = saxReader.read(inputXml);
			Element urlRewrite = document.getRootElement();
			for (Iterator i = urlRewrite.elementIterator(); i.hasNext();) {
				Element rule = (Element) i.next();
				urlMapping.put(rule.element("from").getTextTrim(), rule.element("to").getTextTrim());
			}
		} catch (DocumentException e) {
			StringUtil.getStackTrace(e);
		}
	}

}
