package com.clps.tmp.common.util;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * Ajax统一返回对象
 * @author seven
 */
public class AjaxReturnInfo {
	
	private boolean success=true;	// true or false，默认为true
	private String message;			// 返回信息
	private Exception exception;
	private Map<String, Object> datas = new HashMap<String, Object>(); // 返回的数据

	/**
	 * 取得成功状态的返回对象
	 * @return success
	 * @param message
	 */
	public static AjaxReturnInfo success(String message) {
	    AjaxReturnInfo ret =  new AjaxReturnInfo(true);
	    ret.message = StringUtils.hasText(message) ? message : "success";
	    return ret;
	}

	/**
	 * 取得失败状态的返回对象
	 * 
	 * @return failed对象
	 * @param message
	 */
	public static AjaxReturnInfo failed(String message) {
		AjaxReturnInfo ret = new AjaxReturnInfo(false);
		ret.message = StringUtils.hasText(message) ? message : "failed";
		return ret;
	}

	/**
	 * 私有的构造函数
	 * @param result
	 *            'true' or 'false'
	 */
	private AjaxReturnInfo(boolean result) {
		this.success = result;
	}
	
	/**
	 * 构造函数
	 * success默认是成功的
	 */
	public AjaxReturnInfo(){
	}
	
	/**
	 * 取得成功标志
	 * 
	 * @return 'true' or 'false'
	 */
	public boolean getSuccess() {
		return success;
	}
	
	/**
	 * 设置成功标志
	 * 
	 * @param result 'true' or 'false'
	 */
	public void setSuccess(boolean result) {
		this.success=result;
	}
	
	/**
	 * 获得返回信息
	 * @return 返回信息
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置返回信息
	 * @return 返回信息
	 */
	public void setMessage(String mess) {
		this.message=mess;
	}

	/**
	 * 取得所有数据
	 * @return 数据集合
	 */
	public Map<String, Object> getDatas() {
		return datas;
	}
	
	/**
	 * 添加数据
	 * @param key  Key值
	 * @param obj  对象
	 */
	public void add(String key, Object obj) {
		if (!StringUtils.hasText(key) || obj == null)
			return;
		this.datas.put(key, obj);
	}

	/**
	 * 设置后台产生的异常
	 * @param exp  异常
	 */
	public void setException(Exception exp) {
		exception = exp;
	}

	/**
	 * 获取后台产生的异常
	 * @return 异常对象
	 */
	public Exception getException() {
		return exception;
	}
	
	/**
	 * 获取返回到前台的JsonMap
	 */
	public HashMap<String,Object> getReturnMap(){
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("success", success?"true":"false");
		map.put("message", message);
		map.put("exception", StringUtil.getStackTrace(exception));
		map.put("datas",datas);
		return map;
	}
}