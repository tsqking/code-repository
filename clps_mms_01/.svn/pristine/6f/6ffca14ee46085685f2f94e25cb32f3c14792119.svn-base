package com.clps.mms.log.util;

import java.util.Map;

import com.clps.mms.log.model.LogBean;




/**
 * @param 日志管理工具类LogHelper
 * @since:JDK1.8 
 * @ author:andy.wang
 * @date:2017年2月6日 下午5:37:32
 */
public class LogHelper {
	private static final String ADD = "增加";
	private static final String UPDATE = "更新";
	private static final String DELETE = "删除";
	private static final String UPDATEINFO = "修改信息：";
	private static final String DELETEINFO = "删除信息：";
	private static final String ADDINFO = "增加信息：";
	private static final String FOR = "为";
	private static final String COMMA = ",";
	private static final String INFO_BEGIN = "[";
	private static final String INFO_MIDDLE = "]改成了[";
	private static final String INFO_END = "]";
	private static final StringBuffer sb = new StringBuffer();

	/**
	 * @param LogBean,map
	 * @param map
	 * @return LogBean
	 */
	public static LogBean getAddLog(LogBean LogBean, Map<String, Object> map) {
		sb.setLength(0);
		LogBean.setType(ADD);
		sb.append(ADDINFO);
		sb.append(INFO_BEGIN);
		sb.append(LogBean.getName());
		sb.append(INFO_END);
		sb.append(ADD);
		sb.append(INFO_BEGIN);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(INFO_BEGIN);
			sb.append(entry.getKey());
			sb.append(FOR);
			sb.append(entry.getValue());
			sb.append(INFO_END);
			sb.append(COMMA);
		}
		LogBean.setContent(sb.toString());
		return LogBean;
	}

	/**
	 * 
	 * @param LogBean
	 * @param map
	 * @return  LogBean
	 */
	public static LogBean getUpdateLog(LogBean LogBean, Map<String, Object> map) {
		sb.setLength(0);
		sb.append(UPDATEINFO);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(INFO_BEGIN);
			sb.append(entry.getKey());
			sb.append(INFO_MIDDLE);
			sb.append(entry.getValue());
			sb.append(INFO_END);
			sb.append(COMMA);
		}
		LogBean.getName();
		LogBean.setType(UPDATE);
		LogBean.setContent(sb.toString());
		return LogBean;
	}

	/**
	 * @param LogBean
	 * @return LogBean
	 */
	public static LogBean getDeleteLog(LogBean LogBean) {
		sb.setLength(0);
		sb.append(DELETEINFO);
		sb.append(INFO_BEGIN);
		sb.append(LogBean.getUpdateName());
		sb.append(INFO_END);
		sb.append(DELETE);
		sb.append(INFO_BEGIN);
		sb.append(LogBean.getName());
		sb.append(INFO_END);
		LogBean.setType(DELETE);
		LogBean.setContent(sb.toString());
		return LogBean;
	}
}
