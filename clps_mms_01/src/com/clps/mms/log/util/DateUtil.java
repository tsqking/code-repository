package com.clps.mms.log.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @ClassName: 
* @Description: TODO
* @author  andy.wang
* @date 2017年2月8日 下午1:36:17
*/
public class DateUtil {
	public static String getDate(){
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = format.format(new Date());
	return date;
	}
}
