/**
 * Project Name:clps_mms_copyright_201610
 * File Name:ExcelHelper.java
 * Package Name:com.clps.mms.sys.util.poi
 * Date:2016年10月27日下午3:08:13
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util.poi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.clps.mms.util.exception.ExcelServiceException;

/**
 * ClassName:ExcelHelper 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月27日 下午3:08:13 
 * @author   tony.tan  
 * 	 
 */
public interface IExcelHelper<T> {
/**
 * 
 * createSheet:创建一个表. 
 * @param list
 * @return
 * @throws ExcelServiceException
 */
public boolean createSheet(Map<String,List<T>> map) throws ExcelServiceException;
/**
 * 
 * cteateHeadCell:创建标题行. 
 * @param wb
 * @param row
 * @param val
 * @return
 * @throws ExcelServiceException
 */
public boolean cteateHeadCell(Workbook wb,Row row,String[] val)throws ExcelServiceException;
/**
 * 
 * cteateCell:创建普通行. 
 * @param wb
 * @param row
 * @param col
 * @param val
 * @return
 * @throws ExcelServiceException
 */
public boolean cteateCell(Workbook wb,Row row,short col,String val)throws ExcelServiceException;
/**
 * 
 * exportExcel:导出excel. 
 * @param path
 * @return
 * @throws ExcelServiceException
 * @throws IOException
 */
public boolean exportExcel(String path) throws ExcelServiceException,IOException;
/**
 * 
 * importExcel:导入excel. 
 * @return
 * @throws ExcelServiceException
 */
public List<T> importExcel()throws ExcelServiceException;
}

