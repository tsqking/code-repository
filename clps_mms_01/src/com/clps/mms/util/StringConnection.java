/**
 * Project Name:clps_mms_copyright_201610
 * File Name:StringConnection.java
 * Package Name:com.clps.mms.sys.util
 * Date:2016年11月1日下午3:28:31
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ClassName:StringConnection 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年11月1日 下午3:28:31 
 * @author   tony.tan  
 * 	 
 */
public class StringConnection {
public final static String fizzyQuery="%";
public final static String QUESTION_MARK="[?]";
public final static String COMMMA=",";
public final static String ORDERNAME="order by";
public static String[] connect(String[] strs,String format){
	for(int i=0;i<strs.length;i++){
	   strs[i]=	format+strs[i]+format;
	}
	return strs;
}
public static String connect(String sql,String str1,String str2){
	StringBuffer sb=new StringBuffer(sql);
	System.out.println(sb.indexOf("LIMIT"));
	sb.insert(sb.indexOf("LIMIT"), StringConnection.connect(str1, str2));
	return sb.toString();
}
public static String connect(String str1,String str2){
	StringBuffer sb=new StringBuffer();
	sb.append(ORDERNAME);
	sb.append(" ");
	sb.append(str1);
	sb.append(" ");
	sb.append(str2);
	sb.append(" ");
	return sb.toString();
}
public static String connectByFormat(String[] strs,String format){
	StringBuffer sb=new StringBuffer();
	for(int i=0;i<strs.length;i++){
		if (i==strs.length-1) {
			sb.append(strs[i]);
		}else{
			sb.append(strs[i]+format);
		}
		
	}
	return sb.toString();
}
public static Map<String,String> ConnectionByFormat(Map<String,String> map,String format){
	Iterator<Entry<String, String>> it=map.entrySet().iterator();
	while(it.hasNext()){
		StringBuffer sb=new StringBuffer();
		Entry<String,String> itEntry=it.next();
		String key=itEntry.getKey();
		String value=itEntry.getValue();
		sb.append(fizzyQuery);
		sb.append(value);
		sb.append(fizzyQuery);
		itEntry.setValue(sb.toString());
		System.out.println(key+"----"+value);
	}
	return map;
}
public static String replaceSQL(String sql,Object[] args){
	String temp=sql;
	for(int i=0;i<args.length;i++){
		if (args[i]!=null) {
			
			if (args[i] instanceof Date) {
				temp=temp.replaceFirst(QUESTION_MARK, DateFormat.format((Date) args[i]));
			}else{
				
				temp=temp.replaceFirst(QUESTION_MARK,  args[i].toString());
			}
		}
	}
	return temp;
}
public static Map<String,Object> splitByFormat(String str,String format){
	String[] strs=str.split(",");
	Map<String,Object> map=new HashMap<>();
	for(String s:strs){
		map.put(s, s);
	}
	return map;
}
public static void main(String[] args) {
	/*String a="u?gii?";
	Object[] args1={"b",1};
	System.out.println(replaceSQL(a, args1));*/
	/*Map<String,String>map=new HashMap<>();
	map.put("name", "dsd");
	Map<String,String>map1=StringConnection.ConnectionByFormat(map, StringConnection.fizzyQuery);
	StringConnection.ConnectionByFormat(map1, StringConnection.fizzyQuery);*/
	
	
	/*String a="select * from tbl_demo where LIMIT 3,2";
	
	String b="order by";
	String c="name";
	String d="asc";
	System.out.println(StringConnection.connect(a,c,d));*/
	
	String s="a,b,c";
	StringConnection.splitByFormat(s, COMMMA);
}
}

