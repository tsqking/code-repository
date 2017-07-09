package com.clps.tmp.core.sm.util;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.StringUtil;

/**
 * 用于产生下一个号码
 * <br>使用方法：
 * <br>GenerateNextNo generate=new GenerateNextNo();
 * <br>然后调用方法 generate.getNextXXXNo();
 * @author Seven
 * 2015年10月27日
 */
public class GenerateNextNo {
	Lock lock=new ReentrantLock();
	//存放人员初始化数据
	private static Map<String,Object> personNoList=null;
	//存放课程初始化数据
	private static String courseNo=null; 
	//存放班级初始化数据
	private static String classNo=null; 
	//存放临时用户名数据
	private static String tempUserName=null;
	
	public static String getTempUserName() {
		return tempUserName;
	}
	public static void setTempUserName(String tempUserName) {
		GenerateNextNo.tempUserName = tempUserName;
	}
	public static String getCourseNo() {
		return courseNo;
	}
	public static void setCourseNo(String courseNo) {
		GenerateNextNo.courseNo = courseNo;
	}
	public static String getClassNo() {
		return classNo;
	}
	public static void setClassNo(String classNo) {
		GenerateNextNo.classNo = classNo;
	}
	public static Map<String, Object> getPersonNoList() {
		return personNoList;
	}
	public static void setPersonNoList(Map<String, Object> personNoList) {
		Logger log=Logger.getLogger(GenerateNextNo.class);
		GenerateNextNo.personNoList = personNoList;
		for(Map.Entry<String, Object> entry:GenerateNextNo.personNoList.entrySet()){
			log.info("key:"+entry.getKey()+" value:"+entry.getValue());
		}
	}
	/**
	 * 根据角色类型获取下一个工号
	 * 
	 * @param role <br>0-超级管理员<br>1-管理员<br>2-讲师<br>3-学员
	 * @return
	 */
	public String getNextPersonNo(String role){
		String id=null;
		lock.lock();
		if("0".equals(role)){
			id=generateNextPersonNo("SuperManagerNo",(String) personNoList.get("SuperManagerNo"));
		}else if("1".equals(role)){
			id=generateNextPersonNo("ManagerNo",(String) personNoList.get("ManagerNo")); 
		}else if("2".equals(role)){
			id=generateNextPersonNo("TeacherNo",(String) personNoList.get("TeacherNo")); 
		}else if("3".equals(role)){
			id=generateNextPersonNo("StudentNo",(String) personNoList.get("StudentNo")); 
		}else if("4".equals(role)){
			id=generateNextPersonNo("OthersNo",(String) personNoList.get("OthersNo")); 
		}
		lock.unlock();
		return id;
	}
	private String generateNextPersonNo(String role,String id){
		int temp=Integer.parseInt(id.substring(3))+1;
		StringBuffer nextVal=new StringBuffer();
		nextVal.append(id.substring(0,1));
		String year=DateTimeUtil.getSimpleYear();
		nextVal.append(year);
		if(year.equals(id.substring(1,3))){
			nextVal.append(StringUtil.completeString(String.valueOf(temp), 4, '0', true));
		}else{
			nextVal.append("0000");
		}
		this.personNoList.put(role, nextVal.toString());
		return nextVal.toString();
	}
	/**
	 * 获取下一个班级号
	 * 2015年11月16日 Seven
	 */
	public String getNextClassNo(){//CLS150100
		lock.lock();
		int temp=Integer.parseInt(this.classNo.substring(7))+1;
		StringBuffer nextVal=new StringBuffer();
		String year=DateTimeUtil.getSimpleYear();
		String month=DateTimeUtil.getMonth();
		nextVal.append(this.classNo.substring(0, 3));
		nextVal.append(year);
		nextVal.append(month);
		if(year.equals(this.classNo.substring(3,5)) && month.equals(this.classNo.substring(5, 7)) ){
			nextVal.append(StringUtil.completeString(String.valueOf(temp), 2, '0', true));
		}else{
			nextVal.append("00");
		}
		this.classNo=nextVal.toString();
		lock.unlock();
		return nextVal.toString();
	}
	/**
	 * 获取下一个课程号
	 * 2015年11月16日 Seven
	 */
	public String getNextCourseNo(){//CSE15000
		lock.lock();
		int temp=Integer.parseInt(this.courseNo.substring(5))+1;
		StringBuffer nextVal=new StringBuffer();
		String year=DateTimeUtil.getSimpleYear();
		nextVal.append(this.courseNo.substring(0, 3));
		nextVal.append(year);
		if(year.equals(this.courseNo.substring(3, 5))){
			nextVal.append(StringUtil.completeString(String.valueOf(temp), 3, '0', true));
		}else{
			nextVal.append("000");
		}
		this.courseNo=nextVal.toString();
		lock.unlock();
		return nextVal.toString();
	}
	/**
	 * 获取下一个临时用户名
	 * 2015年12月8日 Seven
	 */
	public String getNextTempUserName(){//tmpuser0001
		lock.lock();
		int temp=Integer.parseInt(this.tempUserName.substring(7))+1;
		StringBuffer nextVal=new StringBuffer();
		nextVal.append(this.tempUserName.substring(0, 7));
		nextVal.append(StringUtil.completeString(String.valueOf(temp), 4, '0', true));
		this.tempUserName=nextVal.toString();
		lock.unlock();
		return nextVal.toString();
	}
}
