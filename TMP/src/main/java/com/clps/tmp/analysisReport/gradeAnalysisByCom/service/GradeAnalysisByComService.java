/**
 * Project Name:TMP
 * File Name:GradeAnalysisByComService.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisByCom.service
 * Date:2017年4月19日下午2:15:00
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisByCom.service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:GradeAnalysisByComService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:15:00 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface GradeAnalysisByComService {
	/**
	 * selectDataByCom:(通过公司、时间查找科目以及相应数据). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public Map<String, List<Integer>> selectDataByCom(Map<String, Object> map) throws Exception;
}
