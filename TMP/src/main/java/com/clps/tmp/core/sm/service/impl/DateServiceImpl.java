package com.clps.tmp.core.sm.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.clps.tmp.core.common.service.BaseService;
import com.clps.tmp.core.sm.constant.SystemSQLConstant;
import com.clps.tmp.core.sm.service.DateService;
import com.clps.tmp.core.sm.vo.CountDateInfo;

/**
 * @author Seven
 *
 * 2015年11月1日
 */
@Service("dateService")
public class DateServiceImpl extends BaseService implements DateService  {

	@Override
	public List<Map<String, Object>> getNearHoliday(String tar) throws Exception {
		//获取上下月
		String tar_next=null;
		String tar_last=null;
		int year=Integer.valueOf(tar.split("-")[0]);
		int month=Integer.valueOf(tar.split("-")[1]);
		tar=tar.replace("-", "");
		if(month==1){
			tar_last=String.valueOf(year-1)+"12";
		}else{
			tar_last=String.valueOf(year)+((month-1)<10?("0"+String.valueOf(month-1)):(month-1));
		}
		if(month==12){
			tar_next=String.valueOf(year+1)+"01";
		}else{
			tar_next=String.valueOf(year)+((month+1)<10?("0"+String.valueOf(month+1)):(month+1));
		}
		log.info("查询的月份为："+tar_last+" "+tar+" "+tar_next);
		
		//搜索条件
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("month", tar+"%");
		paramMap.put("last_month", tar_last+"%");
		paramMap.put("next_month", tar_next+"%");
		log.info("SQL:\n"+SystemSQLConstant.QUERY_HOLIDAYS_BY_NEAR_MONTH+"\nparam:"+paramMap.toString());
		List<Map<String, Object>> listMap=this.dao.queryForList(SystemSQLConstant.QUERY_HOLIDAYS_BY_NEAR_MONTH, paramMap);
		return listMap;
	}

	@Override
	public int setHoliday(String day,String operation) throws Exception {
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("holiday", operation);
		paramMap.put("date", day);
		log.info("SQL:\n"+SystemSQLConstant.SET_HOLIDAY+"\nparam:"+paramMap.toString());
		int effect=this.dao.update(SystemSQLConstant.SET_HOLIDAY, paramMap);
		if(effect==0){//未有记录
			//自动补数  填充日历
			log.info("SQL:\n"+SystemSQLConstant.FILL_CALENDAR+"\nparam:"+paramMap.toString());
			effect=this.dao.update(SystemSQLConstant.FILL_CALENDAR, paramMap);
		}
		return effect;
	}

	@Override
	public Map<String,String> getEndTime(String starttime, double cost) throws Exception {
		if("".equals(starttime) || starttime==null || cost<0) return null;
		if(starttime.length()<15) return null;
		starttime=starttime.subSequence(0, 13)+"00";
		Map<String,String> rtn=new HashMap<String,String>();//Return Map
		
		CountDateInfo util=new CountDateInfo();
		Map<String,Object> startInfoMap=util.getStartTime(starttime);//获取开始时间辅助信息
		HashMap<String, Object> paramMap=new HashMap<String, Object>();//查询条件
		paramMap.put("date", starttime.substring(0,8));
		paramMap.put("start", 0);
		List<String> list=getWorkDay(paramMap);//查询
		//得到真正开始时间
		String realStartTime=null;
		if(starttime.substring(0,8).equals(list.get(0))){//starttime落在工作日
			realStartTime=list.get((int)startInfoMap.get("index"))+" "+startInfoMap.get("time");
		}else{//starttime不为工作日
			realStartTime=list.get(0)+" "+CountDateInfo.getMorningStart();
		}
		
		Map<String,Object> endInfoMap=util.getEndTime(realStartTime, cost);//获取结束时间辅助信息
		paramMap.put("date", realStartTime.substring(0,8));
		paramMap.put("start", (int)endInfoMap.get("index"));
		list=getWorkDay(paramMap);//查询
		//获取真正结束时间
		String realEndTime=(String) list.get(0)+" "+endInfoMap.get("time");
		
		rtn.put("starttime",realStartTime);
		rtn.put("endtime", realEndTime);
		return rtn;
	}
	/**
	 * 数据库查询出两个工作日
	 * 2015年12月11日 Seven
	 */
	private List<String> getWorkDay(Map<String,Object> sqlParamMap) throws Exception{
		String sql=SystemSQLConstant.QUERY_NEXT_TWO_WORKDAY;
		log.info("SQL:\n"+sql+"\nparam:"+sqlParamMap.toString());
		List<String> list=(List<String>) this.dao.query(sql, sqlParamMap, new RowMapper<String>(){
			public String mapRow(ResultSet rs, int rowNum) throws SQLException{
				return rs.getString("date");
			}
		});
		return list;
	}
	@Override
	public double getWorkTimeInterval(String starttime, String endtime) throws Exception {
		if("".equals(starttime) || starttime==null || "".equals(endtime) || endtime==null || starttime.compareTo(endtime)>0) return -1;
		if(starttime.length()<15 || endtime.length()<15) return -1;
		starttime=starttime.subSequence(0, 13)+"00";
		
		CountDateInfo util=new CountDateInfo();
		Map<String,Object> startInfoMap=util.getStartTime(starttime);//获取开始时间辅助信息
		HashMap<String, Object> paramMap=new HashMap<String, Object>();//查询条件
		paramMap.put("date", starttime.substring(0,8));
		paramMap.put("start", 0);
		List<String> list=getWorkDay(paramMap);//查询
		//得到真正开始时间
		String realStartTime=null;
		if(starttime.substring(0,8).equals(list.get(0))){//starttime落在工作日
			realStartTime=list.get((int)startInfoMap.get("index"))+" "+startInfoMap.get("time");
		}else{//starttime不为工作日
			realStartTime=list.get(0)+" "+CountDateInfo.getMorningStart();
		}
		
		Map<String,Object> endInfoMap=util.getStartTime(endtime);//获取开始时间辅助信息
		paramMap.put("date", endtime.substring(0,8));
		paramMap.put("start", 0);
		list=getWorkDay(paramMap);//查询
		//得到真正开始时间
		String realEndTime=null;
		if(endtime.substring(0,8).equals(list.get(0))){//endtime落在工作日
			realEndTime=list.get((int)endInfoMap.get("index"))+" "+endInfoMap.get("time");
		}else{//endtime不为工作日
			realEndTime=list.get(0)+" "+CountDateInfo.getMorningStart();
		}
		log.info("\n传入计算时间范围："+starttime+" - "+endtime+"\n实际计算时间范围："+realStartTime+" - "+realEndTime);
		
		String sql=SystemSQLConstant.QUERY_WORKDAY_INTERVAL;
		paramMap.put("startday", realStartTime.substring(0, 8));
		paramMap.put("endday", realEndTime.substring(0, 8));
		double interval=(Integer)this.dao.queryForObject(sql, paramMap, Integer.class)*CountDateInfo.getDayHour() + 
				util.getTimeInterval(realStartTime.substring(9), realEndTime.substring(9));
		return interval;
	}
}
