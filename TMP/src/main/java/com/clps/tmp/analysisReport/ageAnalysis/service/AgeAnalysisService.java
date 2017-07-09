/**
 * Project Name:TMP
 * File Name:AgeAnalysisService.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysis.service
 * Date:2017年4月14日下午3:58:05
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.ageAnalysis.service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:AgeAnalysisService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月14日 下午3:58:05 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface AgeAnalysisService {
	List<Double> getAgeData(Map<String, Object> map) throws Exception;
}
