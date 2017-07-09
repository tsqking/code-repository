package com.clps.tmp.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.ContextLoaderListener;

import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.core.sm.service.DateService;

/**
 * 日期时间工具类
  * @ClassName: DateTimeUtil
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年10月15日 下午3:45:12
 */
public class DateTimeUtil{

	//系统时间格式
	private static String SYSTEM_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; 
	//数据库时间格式
	private static String DATABASE_DATE_TIME_FORMAT = "yyyyMMdd HHmmssSS";
	//DateService
	private static String DATE_TIME_FORMAT = "yyyyMMdd HHmmss";
	//简单年
	private static String SIMPLE_YEAR = "yy";
	//月份
	private static String MONTH = "MM";
	//日期格式
	private static String DATE_FORMAT = "yyyyMMdd"; 
	//时间格式
	private static String TIME_FORMAT = "HHmmss"; 
	//当前时间字符串
	private static String NOW_TIME = "yyyyMMddHHmmssSS";
	//展现日期格式
	private static String SHOW_DATE_FORMAT = "yyyy-MM-dd"; 
	private static DateService dateService;
	static{
		//DateTimeUtil.dateService=(DateService) ContextLoaderListener.getCurrentWebApplicationContext().getBean("dateService");
		DateTimeUtil.dateService=(DateService) SpringContextUtil.getBean("dateService");//SpringContextUtil
	}
	
	/**
	 * 时间点加减(不考虑日期)
	 * @param time 时间点 如"085900"
	 * @param sec 加减的秒数
	 * 2015年12月9日 Seven
	 */
	public static String addJustTime(String time,int sec){
		SimpleDateFormat sdf=new SimpleDateFormat(TIME_FORMAT);
		Date dt = null;
		try {
			dt = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		Calendar now = Calendar.getInstance(); 
		now.setTime(dt);
		now.add(Calendar.SECOND, sec);
		dt=now.getTime();
		return sdf.format(dt);
	}
	/**
	 * 增加相应秒
	 * @param date
	 * @return
	 */
	public static Date addSecond(Date date,int second) {    
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date);    
	    calendar.add(Calendar.SECOND, second);    
	    return calendar.getTime();    
	}
	/**
	 * 日期加减
	 * @param date 日期 如"20151209"
	 * @param days 加或减的天数
	 * 2015年12月9日 Seven
	 * @throws ParseException 
	 */
	public static String addJustDate(String date,int days){
		if("".equals(date) || date==null)return "";
		SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT);
		Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		Calendar now = Calendar.getInstance(); 
		now.setTime(dt);
		now.add(Calendar.DAY_OF_MONTH, days);
		dt=now.getTime();
		return sdf.format(dt);
	}

	/**
	 * 返回简单年
	 * 2015年11月16日 Seven
	 */
	public static String getSimpleYear(){
		String date=new SimpleDateFormat(SIMPLE_YEAR).format(new Date());
		return date;
	}
	/**
	 * 返回月份
	 * 2015年11月16日 Seven
	 */
	public static String getMonth(){
		String date=new SimpleDateFormat(MONTH).format(new Date());
		return date;
	}
	/**
	 * 当前时间-系统格式时间
	  * nowToSystem(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: nowToSystem
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String nowToSystem(){
		String date = new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT).format(new Date());
		if(date.length()==17){
			return date.substring(0, 15)+"0"+date.substring(15);
		}else if(date.length()==16){
			return date.substring(0, 15)+"00"+date.substring(15);
		}else{
			return date;
		}	
	}
	
	/**
	 * 目标时间-系统格式时间
	  * toSystem(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toSystem
	  * @Description: Comsys-liuchen
	  * @param @param d
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String toSystem(Date d){
		return new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT).format(d);
	}
	
	/**
	 * 当前时间-数据库格式时间
	  * nowToDatabase(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: nowToDatabase
	  * @Description: Comsys-liuchen
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String nowToDatabase(){
		String date = new SimpleDateFormat(DATABASE_DATE_TIME_FORMAT).format(new Date());
		if(date.length()==17){
			return date.substring(0, 15)+"0"+date.substring(15);
		}else if(date.length()==16){
			return date.substring(0, 15)+"00"+date.substring(15);
		}else{
			return date;
		}
	}
	
	/**
	 * 目标时间-数据库格式时间
	  * toDatabase(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: toDatabase
	  * @Description: Comsys-liuchen
	  * @param @param d
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String toDatabase(Date d){
		String date =  new SimpleDateFormat(DATABASE_DATE_TIME_FORMAT).format(d);
		if(date.length()==17){
			return date.substring(0, 15)+"0"+date.substring(15);
		}else if(date.length()==16){
			return date.substring(0, 15)+"00"+date.substring(15);
		}else{
			return date;
		}
	}
	
	/**
	 * 系统格式-数据库格式
	  * systemtoDatabase(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: systemtoDatabase
	  * @Description: Comsys-liuchen
	  * @param @param date
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String systemToDatabase(String date){
		if(date.contains("-")){
			date = date.replaceAll("-", "");
			date = date.replaceAll(":", "");
			return StringUtil.completeString(date, 18, '0', false);
		}else{
			return date;
		}
		
	}
	
	/**
	 * 数据库格式-系统格式
	  * databasetoSystem(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: databasetoSystem
	  * @Description: Comsys-liuchen
	  * @param @param date
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String databaseToSystem(String date,boolean secShow){
		if("".equals(date) || null==date)
			return null;
		if(date.contains("-")){
			return date;
		}else{
			StringBuffer sb = new StringBuffer();
			//年
			sb.append(date.subSequence(0, 4));
			sb.append("-");
			//月
			sb.append(date.subSequence(4, 6));
			sb.append("-");
			//日
			sb.append(date.subSequence(6, 8));
			try{
				sb.append(" ");
				//时
				sb.append(date.subSequence(9, 11));
				sb.append(":");
				//分
				sb.append(date.subSequence(11, 13));
				if(secShow){
					sb.append(":");
					//秒
					sb.append(date.subSequence(13, 15));
				}
			}catch(Exception e){
				//nothing to do
			}
			return sb.toString();
		}		
	}
	
	public static String databaseToSystem(String date){
		return databaseToSystem(date,true);
	}
	//------------------------------------------------------------------------------
	
	/**
	 * 获得时间差,0->小时数int,1->分钟数int,2->毫秒数long
	  * getHours2(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getHours2
	  * @Description: Comsys-liuchen
	  * @param @param starttime
	  * @param @param endtime
	  * @param @return    设定文件
	  * @return ArrayList<Object>    返回类型
	  * @throws
	 */
	@SuppressWarnings("static-access")
	public ArrayList<Object> getHours2(String starttime,String endtime){
		//new方法
		double h = 0;
		try {		
			h = dateService.getWorkTimeInterval(
					new SimpleDateFormat(DATE_TIME_FORMAT).format(this.changeTimeString(starttime)), 
					new SimpleDateFormat(DATE_TIME_FORMAT).format(this.changeTimeString(endtime)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Object> hourMin=getHourMins(h);
		java.text.DecimalFormat df = new java.text.DecimalFormat("#");  
        ArrayList<Object> al = new ArrayList<Object>();
        al.add(Integer.parseInt((String)hourMin.get("Hours")));
        al.add(Integer.parseInt((String)hourMin.get("Mins")));
        al.add(Integer.parseInt((String)hourMin.get("MilliSec")));
		//old方法
		/*
		Date d1 = changeTimeString(starttime);
		Date d2 = changeTimeString(endtime);
		long time1=d1.getTime();  
        long time2=d2.getTime();  
        long time=time2-time1;  
        int mins = (int)(time/(1000*60));
        int mins2 = (int)(mins%60);
        int hours = (int)((mins-mins2)/60);       
        ArrayList<Object> al = new ArrayList<Object>();
        al.add(hours);
        al.add(mins2);
        al.add(time);
        */
        return al;
	}
	
	
	/**
	 * 基准时间增加小时和分钟数-->日期
	  * addHoursMins(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: addHoursMins
	  * @Description: Comsys-liuchen
	  * @param @param starttime
	  * @param @param hours
	  * @param @param mins
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	@SuppressWarnings("static-access")
	public ArrayList<Date> addHoursMins(String starttime,int hours,int mins){
		//new方法
		double cost = ((double)(hours*60+mins))/60;
		Map<String, String> map = null;
		try {
			map = dateService.getEndTime(
					new SimpleDateFormat(DATE_TIME_FORMAT).format(this.changeTimeString(starttime)), cost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date d1 = changeTimeString(map.get("starttime"));
		Date d2 = changeTimeString(map.get("endtime"));
		ArrayList<Date> al = new ArrayList<Date>();
		al.add(d1);
		al.add(d2);
		//old方法
		/*
		Date d1 = changeTimeString(starttime);
		long time = d1.getTime() + ((hours*3600 + mins*60) * 1000);  
		Date d2 = new Date();
		d2.setTime(time);
		*/
		return al;
	}
	
	/**
	 * 基准时间增加毫秒数
	  * addMSec(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: addMSec
	  * @Description: Comsys-liuchen
	  * @param @param starttime
	  * @param @param t
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	@SuppressWarnings("static-access")
	public ArrayList<Date> addMSec(String starttime,long t){
		//new方法
		double cost = ((double)t)/(1000*3600);
		Map<String, String> map = null;
		try {
			map = dateService.getEndTime(
					new SimpleDateFormat(DATE_TIME_FORMAT).format(this.changeTimeString(starttime)), cost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date d1 = changeTimeString(map.get("starttime"));
		Date d2 = changeTimeString(map.get("endtime"));
		ArrayList<Date> al = new ArrayList<Date>();
		al.add(d1);
		al.add(d2);
		//old方法
		/*
		Date d1 = changeTimeString(starttime);
		long time = d1.getTime() + t;  
		Date d2 = new Date();
		d2.setTime(time);
		*/
		return al;
	}
	
	/**
	 * 格式化时间字符串
	  * changeTimeString(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: changeTimeString
	  * @Description: Comsys-liuchen
	  * @param @param time
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date changeTimeString(String time){
		if(time.contains("-")){
			//系统格式 2015-12-08 10:00:00:000
			if(time.length()==10){
				time = time + " 00:00:00";
			}else if(time.length()==16){
				time = time + ":00";		
			}else if(time.length()>16){
				time = time.substring(0, 18);		
			}
			SimpleDateFormat sdf=new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT);
			try {
				return sdf.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}else{
			//数据库格式  20151208 100000000
			if(time.length()==8){
				time = time + " 000000000";
			}else if(time.length()==15){
				time = time + "000";		
			}else if(time.length()>18){
				time = time.substring(0, 17);		
			}
			SimpleDateFormat sdf=new SimpleDateFormat(DATABASE_DATE_TIME_FORMAT);
			try {
				return sdf.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}		
		return null;
	}
	/**
	 * 将cost时长转换为Hours+Mins
	 * @return Map 格式为 Hours-XXX Mins-XXX MilliSec<br>
	 * 2015年12月30日 Seven
	 */
	public static Map<String,Object> getHourMins(double cost){
		Map<String,Object> rtnMap=new HashMap<String,Object>();
		BigDecimal costVal=new BigDecimal(Double.toString(cost));
		BigDecimal hourVal=new BigDecimal(Integer.toString((int)cost));
		BigDecimal minsVal=((costVal.subtract(hourVal)).multiply(new BigDecimal("60"))).setScale(0,BigDecimal.ROUND_DOWN);
		BigDecimal milliSecVal=costVal.multiply(new BigDecimal("3600")).multiply(new BigDecimal("1000")).setScale(0,BigDecimal.ROUND_DOWN);
		rtnMap.put("Hours", hourVal.toString());
		rtnMap.put("Mins", minsVal.toString());
		rtnMap.put("MilliSec", milliSecVal.toString());
		return rtnMap;
	}
	/**
	 * 将hour与mins转换为cost值
	 * 2015年12月31日 Seven
	 */
	public static String getCost(String hour,String mins){
		BigDecimal hourVal=new BigDecimal(hour);
		BigDecimal minsVal=new BigDecimal(mins);
		BigDecimal costVal=hourVal.add(minsVal.divide(new BigDecimal(60),2,BigDecimal.ROUND_HALF_EVEN));
		return costVal.toString();
	}
	/**
	 * 获取当下时间点2016114625000
	 * 2016年4月11日 Seven
	 */
	public static String getNow(){
		SimpleDateFormat sdf=new SimpleDateFormat(NOW_TIME);
		Date dt = new Date();
		return sdf.format(dt);
		
	}
	/**
	 * 将字符串(20160411)转换为2016-04-11
	 * 2016年4月11日 Seven
	 */
	public static String getShowDate(String date){
		if("".equals(date) || date==null)return "";
		SimpleDateFormat from=new SimpleDateFormat(DATE_FORMAT);
		SimpleDateFormat to=new SimpleDateFormat(SHOW_DATE_FORMAT);
		Date dt = null;
		try {
			dt = from.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		return to.format(dt);
	}
	
	public static String freeFormate(String str,String fromFormate,String toFormate){
		if("".equals(str) || str==null)return null;
		if("".equals(fromFormate) || fromFormate==null)return null;
		if("".equals(toFormate) || toFormate==null)return null;
		String result = null;
		Date dt = null;
		try{
			SimpleDateFormat from=new SimpleDateFormat(fromFormate);
			SimpleDateFormat to=new SimpleDateFormat(toFormate);
			dt = from.parse(str);
			result=to.format(dt);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Description (TODO获取两个时间的分钟数)
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static String getMin(String starttime,String endtime){
		long start = changeTimeString(starttime).getTime();
		long end = changeTimeString(endtime).getTime();
		return Math.floor(((end-start)/(1000*60)))+"";
	}
	
	/**
	 * 测试
	  * main(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: main
	  * @Description: Comsys-liuchen
	  * @param @param args    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(freeFormate("2016-12-12","yyyy-MM-dd","yyyyMMdd"));
	}
	
}
