package com.clps.tmp.core.sm.listener;

import javax.annotation.Resource;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.XmlWebApplicationContext;
import com.clps.tmp.core.common.service.InitDataService;

/**
 * 在项目启动时候，通过监听初始化根上下文来加载项目的初始化数据
 * @author seven
 */
@SuppressWarnings("rawtypes")
//交给Spring管理 
@Repository
public class InitDataListener implements ApplicationListener {
	@Resource
	InitDataService initDataService;// 注入一个dao，可以操控数据库
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		//只在初始化"根上下文"的时候执行
		if(event.getSource() instanceof XmlWebApplicationContext){
			System.out.println("初始化上下文时加载初始化数据....");
			initWebData();
		}
	}
	/**
	 * 初始化数据
	 */
	private void initWebData() {
		try {
			initDataService.loadNoData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
