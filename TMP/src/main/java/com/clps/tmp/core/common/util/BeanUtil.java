package com.clps.tmp.core.common.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 实体类工具类
  * @ClassName: BeanToMap
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月14日 下午5:31:27
 */
public class BeanUtil {

	public static HashMap<String, Object> changeBean(Object model){
		BeanUtil btm = new BeanUtil();	
		try {
			return btm.changeBean2(model);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private HashMap<String, Object> changeBean2(Object model) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// 获取实体类的所有属性，返回Field数组
		Field[] field = model.getClass().getDeclaredFields();
		// 获取属性的名字
		String[] modelName = new String[field.length];
		String[] modelType = new String[field.length];
		HashMap<String, Object> map = new  HashMap<String, Object>();
		for (int i = 0; i < field.length; i++) {
			// 获取属性的名字
			String name = field[i].getName();
			modelName[i] = name;
			// 获取属性类型
			String type = field[i].getGenericType().toString();
			modelType[i] = type;

			// 关键。。。可访问私有变量
			field[i].setAccessible(true);

			// 将属性的首字母大写
			String oldName = name;
			name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
					.toUpperCase());
			String value = null;
			if (type.equals("class java.lang.String")) {
				// 如果type是类类型，则前面包含"class "，后面跟类名
				Method m = model.getClass().getMethod("get" + name);
				// 调用getter方法获取属性值
				value = (String) m.invoke(model);
				if (value == null) {
					value="";
				}
			}
			if (type.equals("class java.lang.Integer")) {
				Method m = model.getClass().getMethod("get" + name);
				value = (Integer) m.invoke(model)+"";
				if (value == null) {
					value="";
				}
			}
			if (type.equals("class java.lang.Short")) {
				Method m = model.getClass().getMethod("get" + name);
				value = (Short) m.invoke(model)+"";
				if (value == null) {
					value="";
				}
			}
			if (type.equals("class java.lang.Double")) {
				Method m = model.getClass().getMethod("get" + name);
				value = (Double) m.invoke(model)+"";
				if (value == null) {
					value="";
				}
			}
			if (type.equals("class java.lang.Boolean")) {
				Method m = model.getClass().getMethod("get" + name);
				value = (Boolean) m.invoke(model)+"";
				if (value == null) {
					value="";
				}
			}
			if (type.equals("class java.util.Date")) {
				Method m = model.getClass().getMethod("get" + name);
				value = new SimpleDateFormat("yyyy-MM-dd HHmmssSS").format(((Date) m.invoke(model)));
				if (value == null) {
					value="";
				}
			}
			map.put(oldName, value);
		}
		return map;
	}

}
