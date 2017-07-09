 package com.clps.tmp.core.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @author Seven
 *
 * 2015年11月1日
 */
public interface DateService {
	/**
	 * 获取某个月及其前后两个月的假期
	 * @param tarMonth eg. "2015-01"
	 */
	public List<Map<String,Object>> getNearHoliday(String tarMonth) throws Exception;
	/**
	 * 设置或者取消某天为假期
	 *  @param day eg. "20150101"
	 *  @param operation eg. "Y" or "N"
	 */
	public int setHoliday(String day,String operation) throws Exception;
	/**
	 * 给定开始时间与耗时，返回结束时间点，非法数据将返回null<br>
	 * @return Map { starttime：开始时间（自动调整最近工作日做工时间、容错(向后调整至三分钟倍数时间点)后得出的时间）， endtime：结束时间 }
	 * @param starttime 开始时间 如："20151209 110000"
	 * @param cost cost 耗时长(h)，正数，数值须为0.05的倍数,否则取其最近较小的0.05倍数值  如3.07h将按照3.05h计算
	 * 2015年12月9日 Seven
	 */
	public Map<String,String> getEndTime(String starttime,double cost)throws Exception;
	/**
	 * 给定开始时间与结束时间，给出两个时间之间的工作时间差（h），出错返回-1
	 * 2015年12月11日 Seven
	 */
	public double getWorkTimeInterval(String starttime, String endtime) throws Exception;
}
