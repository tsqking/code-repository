package com.clps.tmp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ChinaRegionUtil
 * @Description: TODO
 * @author jun.yang
 * @date 2015-11-12 
 */
public class ChinaRegionUtil {	
    
	/** 
	* @Title: deleteBlank 
	* @Description: 去除区域名字的不规则空格
	* @param tempStringName
	* @return String
	* @throws 
	*/ 
	public static String deleteBlank(String tempStringName){
		 String temp = null;
         Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
         Matcher m = p.matcher(tempStringName);
         while (m.find())
         {
             temp = m.group(0);
         }
		return temp;
	}
}
