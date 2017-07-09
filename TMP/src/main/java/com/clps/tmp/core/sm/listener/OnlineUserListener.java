package com.clps.tmp.core.sm.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.clps.tmp.core.sm.constant.SystemConstant;

public class OnlineUserListener implements HttpSessionListener, HttpSessionAttributeListener{
	private static Map<String,Object> onlineUser=new ConcurrentHashMap<String,Object>();
	protected Logger log=Logger.getLogger(OnlineUserListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.info("Session创建...");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("Session销毁...");
	}
	
	public static void inLine(String userName, HttpSession session){
		onlineUser.put(userName, session);
	}
	
	public static boolean isInline(String userName){
		return onlineUser.containsKey(userName);
	}
	
	public static void outLine(String userName){
		HttpSession session=(HttpSession) onlineUser.get(userName);
		onlineUser.remove(userName);
		try{
			session.invalidate();
		}catch(Exception e){
		}
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if(SystemConstant.USERNAME.equals(event.getName())){
			String userName=(String) event.getValue();
			log.info(userName+"登录系统......");
			if(isInline(userName)){
				log.info(userName+"在异地登录......");
				outLine(userName);
				inLine(userName,event.getSession());
			}else{
				inLine(userName,event.getSession());
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if(SystemConstant.USERNAME.equals(event.getName())){
			String userName=(String) event.getValue();
			log.info(userName+"退出系统......");
			if(isInline(userName)){
				outLine(userName);
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		//TODO
	}
}
