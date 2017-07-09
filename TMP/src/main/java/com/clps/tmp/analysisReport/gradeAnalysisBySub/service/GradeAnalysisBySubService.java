/**
 * Project Name:TMP
 * File Name:GradeAnalysisBySubService.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisBySub.service
 * Date:2017年4月19日下午2:10:00
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisBySub.service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:GradeAnalysisBySubService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:10:00 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public interface GradeAnalysisBySubService {

	/**
	 * selectCountByCom:(按照科目、时间查找，得到公司以及对应数据). <br/>
	 * @author Charles
	 * @param map
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public Map<String, List<Long>> selectDataBySub(Map<String, Object> map) throws Exception;
}
