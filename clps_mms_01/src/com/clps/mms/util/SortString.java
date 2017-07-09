/**
 * Project Name:clps_mms_copyright_201610
 * File Name:SortString.java
 * Package Name:com.clps.mms.util
 * Date:2016年10月16日下午9:20:45
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName:SortString Function: 处理字符串排序. Reason: TODO ADD REASON.
 * Date: 2016年10月16日 下午9:20:45
 * 
 * @author tony.tan
 * 
 */
public class SortString{
	private static List<String> list=null;
	static{
		if (list==null) {
			list=new ArrayList<String>();
		}
	}
	/**
	 * 
	 * StringToList:将String[]转换成List. 
	 * @param strs
	 * @return
	 */
	public static List<String> StringToList(String[] strs){
		List<String> list=new ArrayList<String>();
		Collections.addAll(list, strs);
		return list;
	}
	/**
	 * 
	 * ListToString:将List转化成String[]. 
	 * @param list
	 * @return
	 */
	public static String[] ListToString(List<String> list) {
		String[] str = (String[]) list.toArray(new String[list.size()]);
		return str;
	}
	/**
	 * 
	 * SortByAsc:升序排列list. 
	 * @param strs
	 * @return
	 */
	
    public static List<String> SortByAsc(List<String> list){
    	if (list!=null) {
    		Collections.sort(list, new AscComparator());
		}
    	return list;
    }
    /**
     * 
     * SortByAsc:升序排列String[]. 
     * @param strs
     * @return
     */
    public static List<String> SortByAsc(String[] strs){
    	List<String> list=StringToList(strs);
    	SortByAsc(list);
    	return list;
    }
    /**
     * 
     * SortByDesc:降序排列list. 
     * @param strs
     * @return
     */
    public static List<String> SortByDesc(List<String> list){
    	if (list!=null) {
			Collections.sort(list, new DescComparator());
		}
		return list;
    }
   /**
    * 
    * SortByDesc:降序排列String[] 
    * @param strs
    * @return
    */
    public static List<String> SortByDesc(String[] strs){
    	List<String> list=StringToList(strs);
    	SortByDesc(list);
    	return list;
    }

}
