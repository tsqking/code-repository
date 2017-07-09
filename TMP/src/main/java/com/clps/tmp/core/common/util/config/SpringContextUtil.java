package com.clps.tmp.core.common.util.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 获取Spring的应用上下文工具类
 * @author Seven
 * 2015年12月25日
 */
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	
	public SpringContextUtil(){
        
    }
	
    public static <T> T getObject(String id,Class<T> clazz) throws BeanNotOfRequiredTypeException,NoSuchBeanDefinitionException,BeansException{  
        return context.getBean(id,clazz);  
    }  
    
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.context = applicationContext;  
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}
	/**
	 * 获取对象
	 * 2015年12月25日 Seven
	 */
	public static Object getBean(String name) throws BeansException {
	    return context.getBean(name);
	}
	/**
	 * 获取类型为requiredType的对象
	 * 
	 * 2015年12月25日 Seven
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBean(String name, Class requiredType) throws BeansException {
	    return context.getBean(name, requiredType);
	}
	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true 
	 * 2015年12月25日 Seven
	 */
	public static boolean containsBean(String name) {
	    return context.containsBean(name);
	}
	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype
	 * 2015年12月25日 Seven
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return context.isSingleton(name);
	}
	/**
	 * 注册对象的类型
	 * 2015年12月25日 Seven
	 */
	@SuppressWarnings("rawtypes")
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		 return context.getType(name);
	}
	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名 
	 * 2015年12月25日 Seven
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return context.getAliases(name);
	}

}
