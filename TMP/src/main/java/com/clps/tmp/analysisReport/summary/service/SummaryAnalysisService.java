/**
 * Project Name:TMP
 * File Name:SummaryAnalysisService.java
 * Package Name:com.clps.tmp.analysisReport.summary.service
 * Date:2017年4月18日下午2:16:07
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.summary.service;

import java.util.Map;

/**
 * ClassName:SummaryAnalysisService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月18日 下午2:16:07 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import com.clps.tmp.analysisReport.summary.vo.HeadVo;
import com.clps.tmp.analysisReport.summary.vo.SummaryVo;
import com.clps.tmp.common.vo.BtTableVo;

public interface SummaryAnalysisService {

	/**
	 * queryHead:(查询表头上男女生人数，考试平均年龄). <br/>
	 * 
	 * @author Charles
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	HeadVo queryHead(Map<String, Object> map) throws Exception;

	/**
	 * queryData:(查询表格数据). <br/>
	 * 
	 * @author Charles
	 * @param map
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	BtTableVo<SummaryVo> queryData(Map<String, Object> map) throws Exception;

}
