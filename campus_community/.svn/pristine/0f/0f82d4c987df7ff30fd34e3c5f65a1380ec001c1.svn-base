/**
 * Project Name:campus_community
 * File Name:DateFormat.java
 * Package Name:com.clps.common.util
 * Date:2017年3月22日上午10:46:33
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName:DateFormat <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月22日 上午10:46:33 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public final class DateFormat {
	public static String FORMAT_SHORT = "yyyy-MM-dd";

	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	private static SimpleDateFormat sdf = null;
	private static Calendar cal = null;

	static {
		if (sdf == null)
			sdf = new SimpleDateFormat();
		else if (cal == null)
			cal = Calendar.getInstance();
	}

	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	public static String getNow() {
		return format(new Date());
	}

	public static String getNow(String format) {
		return format(new Date(), format);
	}

	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			sdf.applyPattern(pattern);
			returnValue = sdf.format(date);
		}
		return returnValue;
	}

	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	public static Date parse(String strDate, String pattern) {
		sdf.applyPattern(pattern);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date addMonth(Date date, int n) {
		cal.setTime(date);
		cal.add(2, n);
		return cal.getTime();
	}

	public static Date addDay(Date date, int n) {
		cal.setTime(date);
		cal.add(5, n);
		return cal.getTime();
	}

	public static String getTimeString() {
		sdf.applyPattern(FORMAT_FULL);
		return sdf.format(cal.getTime());
	}

	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}

	public static int countDays(String date) {
		long t = cal.getTime().getTime();
		cal.setTime(parse(date));
		long t1 = cal.getTime().getTime();
		return (int) (t / 1000L - t1 / 1000L) / 3600 / 24;
	}

	public static int countDays(String date, String format) {
		long t = cal.getTime().getTime();
		cal.setTime(parse(date, format));
		long t1 = cal.getTime().getTime();
		return (int) (t / 1000L - t1 / 1000L) / 3600 / 24;
	}
}
