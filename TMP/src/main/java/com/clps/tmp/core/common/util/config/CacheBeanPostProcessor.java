package com.clps.tmp.core.common.util.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * bean初始化前后记录
 * @author seven
 */
public class CacheBeanPostProcessor implements BeanPostProcessor {

	/**
	 * 在spring中定义的bean初始化前调用这个方法
	 */
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		//System.out.println("Spring 初始化后:"+arg0);
		return arg0;
	}

	/**
	 * 在spring中定义的bean初始化后调用这个方法
	 */
	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("Spring 初始化:"+arg0);
		return arg0;
	}

}
