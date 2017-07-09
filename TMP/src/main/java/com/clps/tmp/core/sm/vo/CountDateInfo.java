package com.clps.tmp.core.sm.vo;

import java.util.HashMap;
import java.util.Map;
import com.clps.tmp.common.util.DateTimeUtil;
/**
 * 工作日工作时间计算类
 * @author Seven
 * 2015年12月11日
 */
public class CountDateInfo {
	private static double dayHour=8;//最小宽度(h)，与timePoit对应
	private static double minHour=0.05;//最小宽度(h)，与timePoit对应
	private static int minMinute=3;//最小宽度(min)，与timePoit对应
	private static String morningStart="090000";//上午开始工作时间，与timePoit对应
	private static String morningEnd="120000";//上午结束工作时间，与timePoit对应
	private static String afternoonStart="130000";//下午开始工作时间，与timePoit对应
	private static String afternoonEnd="180000";//下午结束工作时间，与timePoit对应
	
	public static double getDayHour() {
		return dayHour;
	}
	public static void setDayHour(double dayHour) {
		CountDateInfo.dayHour = dayHour;
	}
	public static double getMinHour() {
		return minHour;
	}
	public static void setMinHour(double minHour) {
		CountDateInfo.minHour = minHour;
	}
	public static int getMinMinute() {
		return minMinute;
	}
	public static void setMinMinute(int minMinute) {
		CountDateInfo.minMinute = minMinute;
	}
	public static String getMorningStart() {
		return morningStart;
	}
	public static void setMorningStart(String morningStart) {
		CountDateInfo.morningStart = morningStart;
	}
	public static String getMorningEnd() {
		return morningEnd;
	}
	public static void setMorningEnd(String morningEnd) {
		CountDateInfo.morningEnd = morningEnd;
	}
	public static String getAfternoonStart() {
		return afternoonStart;
	}
	public static void setAfternoonStart(String afternoonStart) {
		CountDateInfo.afternoonStart = afternoonStart;
	}
	public static String getAfternoonEnd() {
		return afternoonEnd;
	}
	public static void setAfternoonEnd(String afternoonEnd) {
		CountDateInfo.afternoonEnd = afternoonEnd;
	}
	private static String[][] timePoint={
		{"090000","180000"},{"090300","090300"},{"090600","090600"},{"090900","090900"},{"091200","091200"},{"091500","091500"},
		{"091800","091800"},{"092100","092100"},{"092400","092400"},{"092700","092700"},{"093000","093000"},{"093300","093300"},
		{"093600","093600"},{"093900","093900"},{"094200","094200"},{"094500","094500"},{"094800","094800"},{"095100","095100"},
		{"095400","095400"},{"095700","095700"},
		{"100000","100000"},{"100300","100300"},{"100600","100600"},{"100900","100900"},{"101200","101200"},{"101500","101500"},
		{"101800","101800"},{"102100","102100"},{"102400","102400"},{"102700","102700"},{"103000","103000"},{"103300","103300"},
		{"103600","103600"},{"103900","103900"},{"104200","104200"},{"104500","104500"},{"104800","104800"},{"105100","105100"},
		{"105400","105400"},{"105700","105700"},
		{"110000","110000"},{"110300","110300"},{"110600","110600"},{"110900","110900"},{"111200","111200"},{"111500","111500"},
		{"111800","111800"},{"112100","112100"},{"112400","112400"},{"112700","112700"},{"113000","113000"},{"113300","113300"},
		{"113600","113600"},{"113900","113900"},{"114200","114200"},{"114500","114500"},{"114800","114800"},{"115100","115100"},
		{"115400","115400"},{"115700","115700"},
		{"130000","120000"},{"130300","130300"},{"130600","130600"},{"130900","130900"},{"131200","131200"},{"131500","131500"},
		{"131800","131800"},{"132100","132100"},{"132400","132400"},{"132700","132700"},{"133000","133000"},{"133300","133300"},
		{"133600","133600"},{"133900","133900"},{"134200","134200"},{"134500","134500"},{"134800","134800"},{"135100","135100"},
		{"135400","135400"},{"135700","135700"},
		{"140000","140000"},{"140300","140300"},{"140600","140600"},{"140900","140900"},{"141200","141200"},{"141500","141500"},
		{"141800","141800"},{"142100","142100"},{"142400","142400"},{"142700","142700"},{"143000","143000"},{"143300","143300"},
		{"143600","143600"},{"143900","143900"},{"144200","144200"},{"144500","144500"},{"144800","144800"},{"145100","145100"},
		{"145400","145400"},{"145700","145700"},
		{"150000","150000"},{"150300","150300"},{"150600","150600"},{"150900","150900"},{"151200","151200"},{"151500","151500"},
		{"151800","151800"},{"152100","152100"},{"152400","152400"},{"152700","152700"},{"153000","153000"},{"153300","153300"},
		{"153600","153600"},{"153900","153900"},{"154200","154200"},{"154500","154500"},{"154800","154800"},{"155100","155100"},
		{"155400","155400"},{"155700","155700"},
		{"160000","160000"},{"160300","160300"},{"160600","160600"},{"160900","160900"},{"161200","161200"},{"161500","161500"},
		{"161800","161800"},{"162100","162100"},{"162400","162400"},{"162700","162700"},{"163000","163000"},{"163300","163300"},
		{"163600","163600"},{"163900","163900"},{"164200","164200"},{"164500","164500"},{"164800","164800"},{"165100","165100"},
		{"165400","165400"},{"165700","165700"},
		{"170000","170000"},{"170300","170300"},{"170600","170600"},{"170900","170900"},{"171200","171200"},{"171500","171500"},
		{"171800","171800"},{"172100","172100"},{"172400","172400"},{"172700","172700"},{"173000","173000"},{"173300","173300"},
		{"173600","173600"},{"173900","173900"},{"174200","174200"},{"174500","174500"},{"174800","174800"},{"175100","175100"},
		{"175400","175400"},{"175700","175700"}};
	/**
	 * 查找一个值在timePoint数组中的位置
	 * 2015年12月9日 Seven
	 */
	private int getValuePos(String value){
		if(value==null || "".equals(value))return -1;
		int i=0;
		for(;i<timePoint.length;i++){
			if(value.equals(timePoint[i][0]) || value.equals(timePoint[i][1])){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 根据开始时间点以及耗时时长给出结束时间点 如 "20151209 143000"
	 * @param starttime 开始时间,只识别工作时间三分钟的倍数  "20151209 110000"
	 * @param cost 耗时长(h)，数值须为0.05的倍数,否则取其最近较小的0.05倍数值  如3.07h将按照3.05h计算
	 * 2015年12月9日 Seven
	 */
	public Map<String,Object> getEndTime(String starttime, double cost){
		if(starttime==null || "".equals(starttime))return null;
		if(starttime.length()<15)return null;
		String time=starttime.substring(9);
		int interval=((int) (cost*100))/((int)(minHour*100));
		int startIdx=getValuePos(time);
		if(startIdx==-1)return null;
		int jumpDay=(startIdx+interval)/timePoint.length;
		int endIdx=(startIdx+interval)%timePoint.length;
		Map<String,Object> rtn=new HashMap<String,Object>();
		//特殊情况
		if(jumpDay!=0 && endIdx==0){
			rtn.put("time", timePoint[endIdx][1]);
			rtn.put("index", jumpDay-1);
		}else if(jumpDay==0 && endIdx==0){
			rtn.put("time", timePoint[endIdx][0]);
			rtn.put("index", jumpDay);
		}else{
			rtn.put("time", timePoint[endIdx][1]);
			rtn.put("index", jumpDay);
		}
		return rtn;
	}
	/**
	 * 给定开始时间点与工作日，调整出人性化的开始时间点(且容错)
	 * 2015年12月9日 Seven
	 */
	public Map<String,Object> getStartTime(String starttime){
		if(starttime==null || "".equals(starttime))return null;
		if(starttime.length()<15)return null;
		String time=starttime.substring(9);
		Map<String,Object> rtn=new HashMap<String,Object>();
		while(true){//容错
			if(time.compareTo(morningStart)<0){//090000之前
				time=morningStart;
				rtn.put("time", time);
				rtn.put("index", 0);
				return rtn;
			}else if(time.compareTo(morningEnd)>0 && time.compareTo(afternoonStart)<0){//120000-130000之间
				time=afternoonStart;
				rtn.put("time", time);
				rtn.put("index", 0);
				return rtn;
			}else if(time.compareTo(afternoonEnd)>0){//180000之后
				time=morningStart;
				rtn.put("time", time);
				rtn.put("index", 1);
				return rtn;
			}
			//工作时间范围内，微调
			if(Integer.valueOf(time.substring(2, 4))%minMinute==0){
				break;
			}else{
				time=DateTimeUtil.addJustTime(time, 60);
			}
		}
		int startIdx=getValuePos(time);
		if(startIdx==-1)return null;
		rtn.put("time", timePoint[startIdx][0]);
		if(time.equals(afternoonEnd)){//开始时间调整为下一个工作日的 090000
			rtn.put("index", 1);
			return rtn;
		}
		rtn.put("index", 0);
		return rtn;
	}
	/**
	 * 计算时间差 
	 * @param beginTime 开始时间点(符合规范的时间点) 如 "103000"
	 * @param endTime 结束时间点(符合规范的时间点) 如 "144000"
	 * 2015年12月11日 Seven
	 */
	public double getTimeInterval(String beginTime,String endTime){
		int startIdx=getValuePos(beginTime);
		int endIdx=getValuePos(endTime);
		return (endIdx-startIdx)*minHour;
	}
}
