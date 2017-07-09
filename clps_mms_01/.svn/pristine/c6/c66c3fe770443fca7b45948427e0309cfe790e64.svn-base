package com.clps.mms.util.common;

import java.io.Serializable;

/**
 * 
 * ClassName: AjaxResult.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2016年11月30日 下午2:36:14 
 *
 * @author tony.tan
 * @version 
 *
 */
public class AjaxResult implements Serializable {

    /**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 *
	 */
	private static final long serialVersionUID = 6384036902700306790L;
	public static final Integer AJAX_STATUS_CODE_SUCCESS=0;
    public static final Integer AJAX_STATUS_CODE_WARN=1;
    public static final Integer AJAX_STATUS_CODE_ERROR=2;

    private Integer statusCode;

    private String message;

    public AjaxResult() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AjaxResult(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static AjaxResult success(){
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setMessage("操作成功！");
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_SUCCESS);
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_ERROR);
        return ajaxResult;
    }

    public static AjaxResult Warn(){
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setStatusCode(AJAX_STATUS_CODE_WARN);
        return ajaxResult;
    }
}
