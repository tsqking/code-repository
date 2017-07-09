package com.clps.tmp.core.sm.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.service.DateService;
import com.clps.tmp.core.sm.vo.DateVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 假期管理
 * @author Seven
 *
 * 2015年10月30日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("date")
@Results({ 
		@Result(name = "toDateManagementPage", location = "date/dateManagement.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class DateAction extends BaseAction implements ModelDriven{
	//页面数据封装
	private DateVo dateVo;
	public DateVo getDateVo() {
		return dateVo;
	}
	public void setDateVo(DateVo dateVo) {
		this.dateVo = dateVo;
	}
	@Override
	public Object getModel() {
		if (dateVo == null) {
			dateVo = new DateVo();
		}
		return dateVo;
	}
	//json数据封装
	private HashMap<String, Object> resultMap;
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	//service
	@Resource
	private DateService dateService;
	
	/**
	 * 跳转日期管理界面
	 */
	public String toDateManagementPage(){
		return "toDateManagementPage";
	}
	
	/**
	 * 获取某个月及其前后两个月的假期
	 * @throws Exception 
	 */
	public String getNearHoliday() throws Exception{
		String tarMonth=dateVo.getDate();
		List<Map<String, Object>> listMap=dateService.getNearHoliday(tarMonth);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("holidays", listMap);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 设置或取消某天为假期
	 * @throws Exception 
	 */
	public String setHoliday() throws Exception{
		String day=dateVo.getDate();//日期
		String operation=dateVo.getHoliday();//设置或者取消   'Y'/'N'
		log.info("设置的日期为："+day+" 是否设置假期："+operation);
		int effect=dateService.setHoliday(day, operation);
//		log.info("&&&&&&&&"+dateService.getEndTime("20151231 100000", 2));//当天1200
//		log.info("&&&&&&&&"+dateService.getEndTime("20151231 120000", 2));//当天1500
//		log.info("&&&&&&&&"+dateService.getEndTime("20151231 160000", 3));//下一个工作日1000
//		log.info("&&&&&&&&"+dateService.getEndTime("20151231 090000", 0));//当天0900
//		log.info("&&&&&&&&"+dateService.getEndTime("20151231 090000", 8));//当天1800
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
	//liuchen日期时间工具类的接口---------------------------------------------------------------------
	/**
	 * 给定开始时间与耗时，返回结束时间点，非法数据将返回null<br>
	  * getEndTime(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getEndTime
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getEndTime() throws Exception{
		double cost = Double.parseDouble(dateVo.getCost());
		cost = cost/(1000*3600);
		Map<String,String> map = dateService.getEndTime(
				new SimpleDateFormat("yyyyMMdd HHmmss").format(DateTimeUtil.changeTimeString(dateVo.getStart_time())), cost);	
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(map!=null){
			rtn.setSuccess(true);
			rtn.add("start_time", DateTimeUtil.databaseToSystem(map.get("starttime")));
			rtn.add("end_time", DateTimeUtil.databaseToSystem(map.get("endtime")));			
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 给定开始时间与结束时间，给出两个时间之间的工作时间差（h），出错返回-1
	  * getWorkTimeInterval(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getWorkTimeInterval
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getWorkTimeInterval() throws Exception{
		double h = dateService.getWorkTimeInterval(
				new SimpleDateFormat("yyyyMMdd HHmmss").format(DateTimeUtil.changeTimeString(dateVo.getStart_time())), 
				new SimpleDateFormat("yyyyMMdd HHmmss").format(DateTimeUtil.changeTimeString(dateVo.getEnd_time())));
		System.out.println("时差："+h);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(h!=-1){
			rtn.setSuccess(true);
			Map<String,Object> hm=DateTimeUtil.getHourMins(h);
			rtn.add("cost", hm.get("MilliSec"));
			rtn.add("hour", hm.get("Hours"));
			rtn.add("min", hm.get("Mins"));
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 计算新的开始时间和结束时间
	  * getTrueStarttimeEndtime(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: getTrueStarttimeEndtime
	  * @Description: Comsys-liuchen
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String getTrueStarttimeEndtime()throws Exception{
		//原开始时间
		String t1 = dateVo.getStart_time();
		//原结束时间
		String t2 = dateVo.getEnd_time();
		//现在开始时间
		String t3 = dateVo.getStart_time1();
		//原来的时长
		double h = dateService.getWorkTimeInterval(
				new SimpleDateFormat("yyyyMMdd HHmmss").format(DateTimeUtil.changeTimeString(t1)),
				new SimpleDateFormat("yyyyMMdd HHmmss").format(DateTimeUtil.changeTimeString(t2)));
		//计算
		Map<String,String> map = dateService.getEndTime(
				new SimpleDateFormat("yyyyMMdd HHmmss").format(DateTimeUtil.changeTimeString(t3)),h);	
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(map!=null){
			rtn.setSuccess(true);
			rtn.add("start_time", DateTimeUtil.databaseToSystem(map.get("starttime")));
			rtn.add("end_time", DateTimeUtil.databaseToSystem(map.get("endtime")));			
		}else{
			rtn.setSuccess(false);
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
}
