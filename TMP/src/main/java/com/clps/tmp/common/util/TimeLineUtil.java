package com.clps.tmp.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TimeLineUtil
 * @Description TODO(事件线UI工具类)
 * @author liuchen
 * @Date 2016年5月6日 下午5:49:41
 * @version 1.0.0
 * @param <T>
 */
public class TimeLineUtil<T> {

	// 类型 0:时间tag 1:信息
	private final String TYPE = "type";
	// 标题
	private final String TITLE = "title";
	// 时间
	private final String TIME = "time";
	// 参数
	private final String PARAM = "param";
	// tag
	private final int tag = 9999;
	// 自定义数据
	private ArrayList<HashMap<String, Object>> data;

	/**
	 * @Description (处理时间线对象)
	 * @param list
	 * @param titleName
	 * @param timeName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public ArrayList<HashMap<String, Object>> getTimeLineObject(List<T> list,String titleName,String timeName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		data = new ArrayList<HashMap<String, Object>>();
		//计数君
		int num = 1;
		for(T t : list){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(TYPE, "1");
			String title = "TimeLine Title";
			try{
				Object o = this.getFieldInfo(t, titleName);
				if(o!=null){
					try{
						title = DateTimeUtil.databaseToSystem(o.toString());
					}catch(Exception e){
						title = o.toString();
					}	
				}else{
					title = titleName;
				}
			}catch(Exception e){
				title = titleName;
			}			
			map.put(TITLE, title);
			String time = "";
			try{
				Object o = this.getFieldInfo(t, timeName);
				if(o!=null){
					time = DateTimeUtil.databaseToSystem(o.toString());
				}else{
					time = timeName;
				}
			}catch(Exception e){
				title = titleName;
			}
			map.put(TIME, time);
			map.put(PARAM, t);	
			data.add(map);
			//添加tag
			if(num%this.tag==0){
				HashMap<String, Object> mapTag = new HashMap<String, Object>();
				mapTag.put(TYPE, "0");
				mapTag.put(TITLE, DateTimeUtil.nowToSystem());
				data.add(mapTag);
			}
			num++;
		}
		return data;
	}

	
	/**
	 * @Description (TODO反射获取对象值)
	 * @param o
	 * @param name
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private Object getFieldInfo(T o, String name)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field f = o.getClass().getDeclaredField(name);
		f.setAccessible(true);
		return f.get(o);
	}
}
