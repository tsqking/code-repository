package com.clps.tmp.campusRecruit.univ.workhist.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.clps.tmp.campusRecruit.univ.workhist.service.WorkHistService;
import com.clps.tmp.campusRecruit.univ.workhist.vo.WorkHistVo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
/**
 * @author Wellen
 *
 * 2016年3月15日
 */
@Service("workHistService")
public class WorkHistServiceImpl  extends MBBaseService implements WorkHistService {

	@Override
	public int addWorkHist(WorkHistVo workHistVo) throws Exception {
		return mbDao.insert("workHist.addWorkHist", workHistVo);
	}

	@Override
	public int editWorkHist(WorkHistVo workHistVo) throws Exception {
		return mbDao.update("workHist.editWorkHist", workHistVo);
	}

	@Override
	public int deleteWorkHist(String[] ids) throws Exception {
		return mbDao.update("workHist.updateWorkHistDel", ids);
	}

	@Override
	public BtTableVo<WorkHistVo> selectWorkHist(Map<String, Object> paramMap) throws Exception {
		 long total = (Long) mbDao.selectOne("workHist.getAllWorkHist", paramMap);
		 getLangParam(paramMap);//获取国际化语言参数
		 @SuppressWarnings("unchecked")
		List<WorkHistVo> workHistVoList = (List<WorkHistVo>) mbDao.selectList("workHist.selectworkHist", paramMap);
		 int length=workHistVoList.size();
        for(int i=0;i<length;i++){
        	WorkHistVo temp=workHistVoList.get(i);
        	temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
        	temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
        	
        }
		 BtTableVo<WorkHistVo> bootStrapPageVo = new BtTableVo<WorkHistVo>();
	     bootStrapPageVo.setTotal((int)total);
	     bootStrapPageVo.setRows(workHistVoList);
	     return bootStrapPageVo;
	}

	@Override
	public WorkHistVo selectEditWorkHist(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		WorkHistVo temp = (WorkHistVo) mbDao.selectOne("workHist.getEditWorkHist", paramMap);
		temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
		temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		return temp;
	}
	@Override
	public WorkHistVo selectViewWorkHist(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);//获取国际化语言参数
		WorkHistVo temp = (WorkHistVo) mbDao.selectOne("workHist.getViewWorkHist", paramMap);
		temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
		temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		return temp;
	}
}
