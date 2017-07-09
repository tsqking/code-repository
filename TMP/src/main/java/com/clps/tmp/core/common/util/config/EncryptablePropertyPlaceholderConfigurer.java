package com.clps.tmp.core.common.util.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.clps.tmp.core.common.util.SecurityHelper;

/**
 * 配置文件解密
 */
public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	static String ENCRYPTION="ENCRY_"; 
	/**
	 * 配置文件解密
	 */
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			// 返回Properties中包含的key-value的Set视图  
	        Set<Entry<Object, Object>> set = props.entrySet();  
	        // 返回在此Set中的元素上进行迭代的迭代器  
	        Iterator<Map.Entry<Object, Object>> it = set.iterator();  
	        String key = null, value = null;  
	        // 循环取出key-value  
	        while (it.hasNext()) {    
	            Entry<Object, Object> entry = it.next();  	  
	            key = String.valueOf(entry.getKey());  
	            value = String.valueOf(entry.getValue());  
	            if(key.startsWith(ENCRYPTION)){
	            	//需要解密
					if (value != null) {
						value = SecurityHelper.DESDecrypt(value);
						props.setProperty(key,value);
					}           	
	            }
	        } 
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}
