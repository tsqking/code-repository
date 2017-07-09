/**
 * Project Name:TMP
 * File Name:GradeAnalysisByComServiceImpl.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysisByCom.service.impl
 * Date:2017年4月19日下午2:15:42
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.gradeAnalysisByCom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.clps.tmp.analysisReport.gradeAnalysisByCom.service.GradeAnalysisByComService;
import com.clps.tmp.analysisReport.gradeAnalysisByCom.vo.GradeAnalysisByComVo;
import com.clps.tmp.core.common.service.MBBaseService;

/**
 * ClassName:GradeAnalysisByComServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月19日 下午2:15:42 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
@Service
public class GradeAnalysisByComServiceImpl extends MBBaseService implements GradeAnalysisByComService {

	@Override
	public Map<String, List<Integer>> selectDataByCom(Map<String, Object> map) throws Exception {
		List<GradeAnalysisByComVo> list = (List<GradeAnalysisByComVo>) this.mbDao.selectList("gradeByCom.selectByCom",
				map);
		Map<String, List<Integer>> dataMap = new HashMap<>();
		// 处理得到的数据
		if (list != null) {
			for (GradeAnalysisByComVo g : list) {
				if (g.getSubject().equals("5")) {
					List<Integer> testData = new ArrayList<>();
					testData.add(g.getRange1());
					testData.add(g.getRange2());
					testData.add(g.getRange3());
					testData.add(g.getRange4());
					testData.add(g.getRange5());
					testData.add(g.getRange6());
					dataMap.put("Testing", testData);
				} else {
					List<Integer> listData = new ArrayList<>();
					listData.add(g.getRange1());
					listData.add(g.getRange2());
					listData.add(g.getRange3());
					listData.add(g.getRange4());
					listData.add(g.getRange5());
					listData.add(g.getRange6());
					dataMap.put(g.getSubject(), listData);
				}
			}
			if (dataMap.get("MF") != null) {
				List<Integer> mfMap = dataMap.get("MF");
				List<Integer> mafMap = dataMap.get("Mainframe");
				int mf1 = mfMap.get(0) + mafMap.get(0);
				int mf2 = mfMap.get(1) + mafMap.get(1);
				int mf3 = mfMap.get(2) + mafMap.get(2);
				int mf4 = mfMap.get(3) + mafMap.get(3);
				int mf5 = mfMap.get(4) + mafMap.get(4);
				int mf6 = mfMap.get(5) + mafMap.get(5);
				List<Integer> listData = new ArrayList<>();
				listData.add(mf1);
				listData.add(mf2);
				listData.add(mf3);
				listData.add(mf4);
				listData.add(mf5);
				listData.add(mf6);

				dataMap.put("Mainframe", listData);
				dataMap.remove("MF");
			}
		}
		return dataMap;
	}

}
