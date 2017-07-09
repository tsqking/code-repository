package com.clps.tmp.tech.plan.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.ScoreService;
import com.clps.tmp.tech.plan.vo.ExerciseScoreRatioVo;
import com.clps.tmp.tech.plan.vo.ExerciseScoreVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;
import com.clps.tmp.tech.plan.vo.TotalScoreRatioVo;
import com.clps.tmp.tech.plan.vo.TotalScoreVo;

@Service("scoreService")
public class ScoreServiceImpl extends MBBaseService implements ScoreService {

	@Override
	public BtTableVo<TotalScoreVo> selectTotalScoreInfo(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		//nothing 
		//查询
		long total = (Long) mbDao.selectOne("scoreManager.getTotalScoreCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<TotalScoreVo> list = (List<TotalScoreVo>) mbDao.selectList("scoreManager.getTotalScore", paramMap);
		for (TotalScoreVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<TotalScoreVo> bootStrapPageVo = new BtTableVo<TotalScoreVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}

	@Override
	public BtTableVo<PlanPaperVo> selectPlanPaperInfo(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		//nothing 
		paramMap.put("flag", null);
		//查询
		long total = (Long) mbDao.selectOne("scoreManager.getPlanPaperCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<PlanPaperVo> list = (List<PlanPaperVo>) mbDao.selectList("scoreManager.getPlanPaper", paramMap);
		for (PlanPaperVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
			temp.setPaper_start_time(DateTimeUtil.databaseToSystem(temp.getPaper_start_time()));
			temp.setPaper_end_time(DateTimeUtil.databaseToSystem(temp.getPaper_end_time()));
		}
		BtTableVo<PlanPaperVo> bootStrapPageVo = new BtTableVo<PlanPaperVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	@Override
	public BtTableVo<PlanPaperVo> selectPlanPaperInfo1(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		//nothing 
		paramMap.put("flag", "0");
		//查询
		long total = (Long) mbDao.selectOne("scoreManager.getPlanPaperCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<PlanPaperVo> list = (List<PlanPaperVo>) mbDao.selectList("scoreManager.getPlanPaper", paramMap);
		for (PlanPaperVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
			temp.setPaper_start_time(DateTimeUtil.databaseToSystem(temp.getPaper_start_time()));
			temp.setPaper_end_time(DateTimeUtil.databaseToSystem(temp.getPaper_end_time()));
		}
		BtTableVo<PlanPaperVo> bootStrapPageVo = new BtTableVo<PlanPaperVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	@Override
	public BtTableVo<ExerciseScoreVo> selectDetailNormal(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//查询
		long total = (Long) mbDao.selectOne("scoreManager.getDetailNormalCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<ExerciseScoreVo> list = (List<ExerciseScoreVo>) mbDao.selectList("scoreManager.getDetailNormal", paramMap);
		for (ExerciseScoreVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<ExerciseScoreVo> bootStrapPageVo = new BtTableVo<ExerciseScoreVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	@Override
	public BtTableVo<ExerciseScoreVo> selectDetailAttendance(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//查询
		long total = (Long) mbDao.selectOne("scoreManager.getDetailAttendanceCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<ExerciseScoreVo> list = (List<ExerciseScoreVo>) mbDao.selectList("scoreManager.getDetailAttendance", paramMap);
		for (ExerciseScoreVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<ExerciseScoreVo> bootStrapPageVo = new BtTableVo<ExerciseScoreVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}

	@Override
	public int setLastPaper(ExerciseScoreRatioVo exerciseScoreRatioVo,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", exerciseScoreRatioVo.getPlan_id());
		paramMap.put("paper_id", exerciseScoreRatioVo.getPaper_id());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", user.getName());
		//先取消
		mbDao.update("scoreManager.unsetLastPaper", paramMap);
		//再添加
		return mbDao.update("scoreManager.setLastPaper", paramMap);
	}

	@Override
	public int unsetLastPaper(ExerciseScoreRatioVo exerciseScoreRatioVo,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", exerciseScoreRatioVo.getPlan_id());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", user.getName());
		return mbDao.update("scoreManager.unsetLastPaper", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean initScoreData(String plan_id,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", plan_id);
		//查询已有的历史数据
		//试卷加权比例和期末卷历史信息
		Map<String, ExerciseScoreRatioVo> exerciseScoreRatioHis = new HashMap<String, ExerciseScoreRatioVo>();
		List<ExerciseScoreRatioVo> listExerciseScoreRatio = (List<ExerciseScoreRatioVo>) mbDao.selectList("scoreManager.getExerciseScoreRatioHis", paramMap);
		for(ExerciseScoreRatioVo esrv : listExerciseScoreRatio){
			exerciseScoreRatioHis.put(esrv.getPaper_id()+"", esrv);
		}
		//学生态度分设置历史
		Map<String, TotalScoreVo> totalScoreHis = new HashMap<String, TotalScoreVo>();
		List<TotalScoreVo> listTotalScoreVo = (List<TotalScoreVo>) mbDao.selectList("scoreManager.getTotalScoreHis", paramMap);
		for(TotalScoreVo tsv : listTotalScoreVo){
			totalScoreHis.put(tsv.getStudent_id()+"", tsv);
		}
		//旧的总分比例记录记录
		TotalScoreRatioVo totalScoreRatioHis = (TotalScoreRatioVo)mbDao.selectOne("scoreManager.getTotalScoreRatioHis", paramMap);
		//删除原来的库表信息
		mbDao.delete("scoreManager.deleteExerciseScore", paramMap);
		mbDao.delete("scoreManager.deleteExerciseScoreRatio", paramMap);
		mbDao.delete("scoreManager.deleteTotalScore", paramMap);
		//查询学生信息和试卷信息
		List<PlanPaperVo> list = (List<PlanPaperVo>) mbDao.selectList("scoreManager.getStudentPaperInfo", paramMap);
		//添加学生试卷分数明细空信息
		paramMap.put("plan_id", plan_id);
		paramMap.put("del", "N");
		paramMap.put("create_time", DateTimeUtil.nowToDatabase());
		paramMap.put("create_person", user.getName());	
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", user.getName());
		List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
		Set<String> paperSet = new HashSet<String>();
		Set<String> studentSet = new HashSet<String>();
		DecimalFormat df = new DecimalFormat("0.00");
		for(PlanPaperVo ppv : list){
			paperSet.add(ppv.getPaper_id());
			studentSet.add(ppv.getStudent_id());
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("student_id", ppv.getStudent_id());
			item.put("paper_id", ppv.getPaper_id());
			item.put("true_score", ppv.getScore());
			double switch_score = (Double.parseDouble(ppv.getScore())*100)/Double.parseDouble(ppv.getTotal_score()); 
			String re = df.format(switch_score);
			item.put("switch_score", re);
			listData.add(item);
		}
		paramMap.put("listData", listData);
		if(listData.size()>=1){
			mbDao.insert("scoreManager.addExerciseScore", paramMap);
		}
		//添加试卷加权比例
		listData = new ArrayList<Map<String,Object>>();
		for(String paper_id : paperSet){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("paper_id", paper_id);
			//历史用户操作数据
			ExerciseScoreRatioVo esrv = exerciseScoreRatioHis.get(paper_id);
			if(esrv==null){
				//新数据
				item.put("ratio", "1");
				item.put("flag", "0");			
			}else{
				//历史数据
				item.put("ratio", esrv.getRatio());
				item.put("flag", esrv.getFlag());
			}
			listData.add(item);
		}	
		paramMap.put("listData", listData);
		if(listData.size()>=1){
			mbDao.insert("scoreManager.addExerciseScoreRatio", paramMap);
		}	
		//添加总分空信息
		listData = new ArrayList<Map<String,Object>>();
		for(String student_id : studentSet){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("student_id", student_id);
			//历史用户操作数据
			TotalScoreVo tsv = totalScoreHis.get(student_id);
			if(tsv==null){
				//新数据
				item.put("attitude_score", "0.00");
			}else{
				//历史数据
				item.put("attitude_score", tsv.getAttitude_score());
			}
			listData.add(item);
		}	
		paramMap.put("listData", listData);
		if(listData.size()>=1){
			mbDao.insert("scoreManager.addTotalScore", paramMap);
		}
		//添加总分比例信息
		if(totalScoreRatioHis==null){
			//新数据
			paramMap.put("normal_ratio", "3");
			paramMap.put("attendance_ratio", "1");
			paramMap.put("attitude_ratio", "1");
			paramMap.put("exam_ratio", "5");
			//添加
			mbDao.insert("scoreManager.addTotalScoreRatio", paramMap);
		}else{
			//历史数据
			//paramMap.put("normal_ratio", totalScoreRatioHis.getNormal_ratio());
			//paramMap.put("attendance_ratio", totalScoreRatioHis.getAttendance_ratio());
			//paramMap.put("attitude_ratio", totalScoreRatioHis.getAttitude_ratio());
			//paramMap.put("exam_ratio", totalScoreRatioHis.getExam_ratio());
			//nothing to do
		}
		return true;
	}

	@Override
	public int setDeRatio(ExerciseScoreRatioVo exerciseScoreRatioVo, UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", exerciseScoreRatioVo.getPlan_id());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", user.getName());
		return mbDao.update("scoreManager.setDeRatio", paramMap);
	}
	
	@Override
	public int setRatio(ExerciseScoreRatioVo exerciseScoreRatioVo, UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", exerciseScoreRatioVo.getPlan_id());
		paramMap.put("ratio", exerciseScoreRatioVo.getRatio());
		paramMap.put("paper_id", exerciseScoreRatioVo.getPaper_id());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("update_person", user.getName());
		return mbDao.update("scoreManager.setRatio", paramMap);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public int saveNormalScore(ExerciseScoreRatioVo exerciseScoreRatioVo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", exerciseScoreRatioVo.getPlan_id());
		List<TotalScoreVo> list = (List<TotalScoreVo>) mbDao.selectList("scoreManager.getStudentNormalScore", paramMap);
		for(TotalScoreVo tsv : list){
			mbDao.update("scoreManager.setStudentNormalScore", tsv);
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int saveStudentOtherScore(ExerciseScoreRatioVo exerciseScoreRatioVo,boolean flag) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plan_id", exerciseScoreRatioVo.getPlan_id());
		List<TotalScoreVo> list = (List<TotalScoreVo>) mbDao.selectList("scoreManager.getStudentOtherScore", paramMap);
		for(TotalScoreVo tsv : list){
			if(!flag){
				if(tsv.getAttitude_score_old()==0.00){
					//没有历史记录
					//nothing to do
				}else{
					//有历史记录
					tsv.setAttitude_score(tsv.getAttitude_score_old());
				}
			}
			mbDao.update("scoreManager.setStudentOtherScore", tsv);
		}
		return 1;
	}

	@Override
	public int setAtt(TotalScoreVo totalScoreVo, UserVo user) throws Exception {
		// TODO Auto-generated method stub
		totalScoreVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		totalScoreVo.setUpdate_person(user.getName());
		return mbDao.update("scoreManager.setAtt", totalScoreVo);
	}

	@Override
	public TotalScoreRatioVo getTotalRatio(TotalScoreRatioVo totalScoreVo) throws Exception {
		// TODO Auto-generated method stub
		return (TotalScoreRatioVo)mbDao.selectOne("scoreManager.getTotalRatio", totalScoreVo);
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public int finish(TotalScoreRatioVo totalScoreRatioVo, UserVo user) throws Exception {
		// TODO Auto-generated method stub
		List<TotalScoreRatioVo> list = (List<TotalScoreRatioVo>) mbDao.selectList("scoreManager.getTotal", totalScoreRatioVo);
		for(TotalScoreRatioVo tsrv : list){
			tsrv.setUpdate_time(DateTimeUtil.nowToDatabase());
			tsrv.setUpdate_person(user.getName());
			mbDao.update("scoreManager.saveTotal", tsrv);
		}
		return 1;
	}

	@Override
	public int saveTotalRatio(TotalScoreRatioVo totalScoreRatioVo, UserVo user) throws Exception {
		// TODO Auto-generated method stub
		totalScoreRatioVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		totalScoreRatioVo.setUpdate_person(user.getName());
		return mbDao.update("scoreManager.saveTotalRatio", totalScoreRatioVo);
	}

	@Override
	public TotalScoreRatioVo getTotalScoreRatio(PlanPaperVo planPaperVo) throws Exception {
		// TODO Auto-generated method stub
		return (TotalScoreRatioVo)mbDao.selectOne("scoreManager.getTotalScoreRatio", planPaperVo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getTotalScoreToExport(String planId) throws Exception {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)mbDao.selectList("scoreManager.getTotalScoreToExport", planId);
	}
		
}


