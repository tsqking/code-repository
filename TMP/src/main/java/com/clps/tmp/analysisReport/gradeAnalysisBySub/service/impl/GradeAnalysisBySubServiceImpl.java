/**
 * Project Name:TMP
 * File Name:GradeAnalysisBySubServiceImpl.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisBySub.service.impl
 * Date:2017年4月19日下午2:10:28
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisBySub.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.clps.tmp.analysisReport.gradeAnalysisBySub.service.GradeAnalysisBySubService;
import com.clps.tmp.analysisReport.gradeAnalysisBySub.vo.GradeAnalysisBySubVo;
import com.clps.tmp.core.common.service.MBBaseService;

/**
 * ClassName:GradeAnalysisBySubServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:10:28 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
@Service
public class GradeAnalysisBySubServiceImpl extends MBBaseService implements GradeAnalysisBySubService {
	@Override
	public Map<String, List<Long>> selectDataBySub(Map<String, Object> map) throws Exception {
		List<GradeAnalysisBySubVo> list = (List<GradeAnalysisBySubVo>) this.mbDao.selectList("gradeBySub.selectBySub",
				map);
		Map<String, List<Long>> dataMap = new HashMap<>();
		if (list != null) {
			for (GradeAnalysisBySubVo g : list) {
				List<Long> dataList = new ArrayList<Long>();
				if (g.getClass() != null) {
					dataList.add(g.getRange1());
					dataList.add(g.getRange2());
					dataList.add(g.getRange3());
					dataList.add(g.getRange4());
					dataList.add(g.getRange5());
					dataList.add(g.getRange6());
					dataMap.put(g.getCompany(), dataList);
				}
			}
		}
		return dataMap;
	}

}
