/**
 * Project Name:TMP
 * File Name:AgeAnalysisServiceImpl.java
 * Package Name:com.clps.tmp.analysisReport.gradeAnalysis.service.impl
 * Date:2017年4月14日下午4:00:26
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.analysisReport.ageAnalysis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.clps.tmp.analysisReport.ageAnalysis.service.AgeAnalysisService;
import com.clps.tmp.analysisReport.ageAnalysis.vo.AgeVo;
import com.clps.tmp.core.common.service.MBBaseService;

/**
 * ClassName:AgeAnalysisServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月14日 下午4:00:26 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
@Service
public class AgeAnalysisServiceImpl extends MBBaseService implements AgeAnalysisService {

	@Override
	public List<Double> getAgeData(Map<String, Object> map) throws Exception {
		AgeVo ageVo = (AgeVo) this.mbDao.selectOne("ageDistribution.detailData", map);
		List<Double> list = new ArrayList<>();
		log.info(ageVo);
		if (ageVo == null) {
			return null;
		}
		double range1 = ageVo.getRange1();
		double range2 = ageVo.getRange2();
		double range3 = ageVo.getRange3();
		double range4 = ageVo.getRange4();
		double range5 = ageVo.getRange5();
		double range6 = ageVo.getRange6();
		double range7 = ageVo.getRange7();

		list.add(range1);
		list.add(range2);
		list.add(range3);
		list.add(range4);
		list.add(range5);
		list.add(range6);
		list.add(range7);

		return list;
	}

}
