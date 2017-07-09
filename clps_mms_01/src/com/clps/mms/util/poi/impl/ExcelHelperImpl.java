/**
 * Project Name:clps_mms_copyright_201610
 * File Name:ExcelHelperImpl.java
 * Package Name:com.clps.mms.sys.util.poi.impl
 * Date:2016年10月28日下午4:40:40
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.poi.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.clps.mms.util.exception.ExcelServiceException;
import com.clps.mms.util.poi.IExcelHelper;


/**
 * ClassName:ExcelHelperImpl 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月28日 下午4:40:40 
 * @author   tony.tan  
 * 	 
 */
public class ExcelHelperImpl<T> implements IExcelHelper<T>{

	

	@Override
	public boolean cteateHeadCell(Workbook wb, Row row, String[] val) throws ExcelServiceException {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cteateCell(Workbook wb, Row row, short col, String val) throws ExcelServiceException {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportExcel(String path) throws ExcelServiceException, IOException {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> importExcel() throws ExcelServiceException {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSheet(Map<String, List<T>> map) throws ExcelServiceException {
		
		// TODO Auto-generated method stub
		return false;
	}

}

