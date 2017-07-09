
package com.clps.tmp.analysisReport.gradeAnalysis.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.analysisReport.gradeAnalysis.service.GradeAnalysisService;
import com.clps.tmp.analysisReport.gradeAnalysis.vo.GradeVo;
import com.clps.tmp.common.util.DecimalUntil;
import com.clps.tmp.core.common.service.MBBaseService;
@Service("gradeAnalysisService")
public class GradeAnalysisServiceImpl extends MBBaseService implements GradeAnalysisService{


	@Override
	public List<Double> getStuGradeData(Map<String,Object> map) throws Exception {
		GradeVo gradeVo=(GradeVo) this.mbDao.selectOne("grade.allData", map);
		System.out.println("gradeVo:"+gradeVo);
		if(gradeVo==null){
			return null;
		}
		Double range1=this.getRange(gradeVo.getRange1(), gradeVo.getTotal());
		Double range2=this.getRange(gradeVo.getRange2(), gradeVo.getTotal());
		Double range3=this.getRange(gradeVo.getRange3(), gradeVo.getTotal());
		Double range4=this.getRange(gradeVo.getRange4(), gradeVo.getTotal());
		Double range5=this.getRange(gradeVo.getRange5(), gradeVo.getTotal());
		Double range6=this.getRange(gradeVo.getRange6(), gradeVo.getTotal());
		Double num=range1+range2+range3+range4+range5+range6;
		System.out.println("num:"+num);
		//GradeVo gradeVo=new GradeVo(range1, range2, range3, range4, range5, range6, range7, range8, range9, range10, range11, range12);
		System.out.println("gradeVo:"+gradeVo);
		List<Double> dataList=new ArrayList<>();
		dataList.add(range1);
		dataList.add(range2);
		dataList.add(range3);
		dataList.add(range4);
		dataList.add(range5);
		dataList.add(range6);
		System.out.println("dataList:"+dataList);
		return dataList;
	}
	public Double getRange(Long range,Long total){
		if(range==0){
			return 0D;
		}
		Double rate=DecimalUntil.mul(DecimalUntil.div(range, total), 100D, 1);
		return rate;
	}

}

