package com.clps.tmp.tech.plan.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.tech.plan.service.TeaStuPlanService;
import com.clps.tmp.tech.plan.vo.EvalVo;
import com.clps.tmp.tech.plan.vo.PlanPaperStuHisVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;
import com.clps.tmp.tech.plan.vo.PlanVo;


@Service("teaStuPlanService")
public class TeaStuPlanServiceImpl extends MBBaseService implements TeaStuPlanService {

	@Override
	public BtTableVo<PlanPaperVo> selectPaperInfo(Map<String, Object> paramMap) throws Exception{
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		Object paper_start_time = paramMap.get("paper_start_time");
		if(paper_start_time!=null && !"".equals(paper_start_time)){
			String[] info = paper_start_time.toString().split("~");
			paramMap.put("paper_start_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("paper_start_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		Object paper_end_time = paramMap.get("paper_end_time");
		if(paper_end_time!=null  && !"".equals(paper_end_time)){
			String[] info = paper_end_time.toString().split("~");
			paramMap.put("paper_end_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("paper_end_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		//查询
		long total = (Long) mbDao.selectOne("personPlan.getPaperCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<PlanPaperVo> list = (List<PlanPaperVo>) mbDao.selectList("personPlan.getPaper", paramMap);
		for (PlanPaperVo temp : list) {
			temp.setPaper_start_time(DateTimeUtil.databaseToSystem(temp.getPaper_start_time()));
			temp.setPaper_end_time(DateTimeUtil.databaseToSystem(temp.getPaper_end_time()));
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<PlanPaperVo> bootStrapPageVo = new BtTableVo<PlanPaperVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	@Override
	public BtTableVo<PaperVo> selectPaperList(Map<String, Object> paramMap) throws Exception{
		getLangParam(paramMap);// 获取国际化语言参数
		//查询
		long total = (Long) mbDao.selectOne("personPlan.getPaperListCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<PaperVo> list = (List<PaperVo>) mbDao.selectList("personPlan.getPaperList", paramMap);
		for (PaperVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
			temp.setTotal_time_name(temp.getTotal_time()<=0?null:(temp.getTotal_time()+"mins"));
			temp.setLeave_limit(temp.getLeave_limit().equals("-1")?null:temp.getLeave_limit());
		}
		BtTableVo<PaperVo> bootStrapPageVo = new BtTableVo<PaperVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}

	@Override
	public int addPaperTest(PlanPaperVo planPaperVo,UserVo teacher) throws Exception {
		// TODO Auto-generated method stub
		planPaperVo.setCreate_time(DateTimeUtil.nowToDatabase());
		planPaperVo.setCreate_person(teacher.getName());
		planPaperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		planPaperVo.setUpdate_person(teacher.getName());
		planPaperVo.setDel("N");
		planPaperVo.setTeacher_id(teacher.getId());
		planPaperVo.setStatus("0");
		planPaperVo.setPaper_start_time(DateTimeUtil.systemToDatabase(planPaperVo.getPaper_start_time()));
		planPaperVo.setPaper_end_time(DateTimeUtil.systemToDatabase(planPaperVo.getPaper_end_time()));	
		planPaperVo.setPaper_long_time(DateTimeUtil.getMin(planPaperVo.getPaper_start_time(),planPaperVo.getPaper_end_time()));
		return mbDao.insert("personPlan.addPaperTest", planPaperVo);
	}

	@Override
	public int deletePaperTest(String[] ids,UserVo user) throws Exception {
		// TODO Auto-generated method stub	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idList", ids);
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		paramMap.put("create_person", user.getName());
		return mbDao.update("personPlan.updatePaperTestDel", paramMap);
	}

	@Override
	public PaperVo getPaperInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		getLangParam(paramMap);// 获取国际化语言参数
		paramMap.put("id", id);
		return (PaperVo)mbDao.selectOne("personPlan.findPaperInfo", paramMap);
	}

	@Override
	public PlanPaperVo getPaperTestInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		getLangParam(paramMap);// 获取国际化语言参数
		paramMap.put("id", id);
		return (PlanPaperVo)mbDao.selectOne("personPlan.findPaperTestInfo", paramMap);
	}
	
	@Override
	public int editPaperTest(PlanPaperVo planPaperVo,UserVo teacher) throws Exception {
		// TODO Auto-generated method stub
		planPaperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		planPaperVo.setUpdate_person(teacher.getName());
		planPaperVo.setPaper_long_time(DateTimeUtil.getMin(planPaperVo.getPaper_start_time(),planPaperVo.getPaper_end_time()));
		planPaperVo.setPaper_start_time(DateTimeUtil.systemToDatabase(planPaperVo.getPaper_start_time()));
		planPaperVo.setPaper_end_time(DateTimeUtil.systemToDatabase(planPaperVo.getPaper_end_time()));
		return mbDao.insert("personPlan.editPaperTest", planPaperVo);
	}

	@Override
	public int updatePaperStatus(PlanPaperVo planPaperVo, UserVo teacher) throws Exception {
		// TODO Auto-generated method stub
		//查询出班级中的所有同学
		@SuppressWarnings("unchecked")
		List<String> al = (List<String>)mbDao.selectList("personPlan.findAllStuIds",planPaperVo);
		if(al==null||al.size()<=0){
			return 0;
		}else{
			//遍历循环添加作业记录
			for(String stuId : al){
				PlanPaperStuHisVo ph = new PlanPaperStuHisVo();
				ph.setCreate_time(DateTimeUtil.nowToDatabase());
				ph.setCreate_person(teacher.getName());
				ph.setUpdate_time(DateTimeUtil.nowToDatabase());
				ph.setUpdate_person(teacher.getName());
				ph.setDel("N");
				ph.setPlan_paper_id(planPaperVo.getId());
				ph.setStudent_id(stuId);
				ph.setStatus("0");
				mbDao.insert("personPlan.addPaperHis", ph);	
			}
			//修改作业布置状态
			planPaperVo.setStatus("1");
			return mbDao.update("personPlan.updatePaperTestStatus", planPaperVo);
		}
	}

	@Override
	public BtTableVo<PlanPaperStuHisVo> selectClassPaperList(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		Object start_time = paramMap.get("start_time");
		if(start_time!=null){
			String[] info = start_time.toString().split("~");
			paramMap.put("start_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("start_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		Object end_time = paramMap.get("end_time");
		if(end_time!=null){
			String[] info = end_time.toString().split("~");
			paramMap.put("end_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("end_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		//查询
		long total = (Long) mbDao.selectOne("personPlan.getPaperHisCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<PlanPaperStuHisVo> list = (List<PlanPaperStuHisVo>) mbDao.selectList("personPlan.getPaperHis", paramMap);
		for (PlanPaperStuHisVo temp : list) {
			temp.setStart_time(DateTimeUtil.databaseToSystem(temp.getStart_time()));
			temp.setEnd_time(DateTimeUtil.databaseToSystem(temp.getEnd_time()));
			temp.setCheck_time(DateTimeUtil.databaseToSystem(temp.getCheck_time()));
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<PlanPaperStuHisVo> bootStrapPageVo = new BtTableVo<PlanPaperStuHisVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	
	@Override
	public BtTableVo<PlanPaperStuHisVo> selectStuPaperHisList(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		Object start_time = paramMap.get("start_time");
		if(start_time!=null){
			String[] info = start_time.toString().split("~");
			paramMap.put("start_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("start_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		Object end_time = paramMap.get("end_time");
		if(end_time!=null){
			String[] info = end_time.toString().split("~");
			paramMap.put("end_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("end_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		//查询
		long total = (Long) mbDao.selectOne("personPlan.getStuPaperHisCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<PlanPaperStuHisVo> list = (List<PlanPaperStuHisVo>) mbDao.selectList("personPlan.getStuPaperHis", paramMap);
		for (PlanPaperStuHisVo temp : list) {
			temp.setStart_time(DateTimeUtil.databaseToSystem(temp.getStart_time()));
			temp.setEnd_time(DateTimeUtil.databaseToSystem(temp.getEnd_time()));
			temp.setCheck_time(DateTimeUtil.databaseToSystem(temp.getCheck_time()));
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
			temp.setPaper_start_time(DateTimeUtil.databaseToSystem(temp.getPaper_start_time()));
			temp.setPaper_end_time(DateTimeUtil.databaseToSystem(temp.getPaper_end_time()));
		}
		BtTableVo<PlanPaperStuHisVo> bootStrapPageVo = new BtTableVo<PlanPaperStuHisVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	@Override
	public BtTableVo<EvalVo> selectFeedbackList(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		Object start_time = paramMap.get("update_time");
		if(start_time!=null){
			String[] info = start_time.toString().split("~");
			paramMap.put("update_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("update_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		//查询
		long total = (Long) mbDao.selectOne("personPlan.getTeaFeedBackCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<EvalVo> list = (List<EvalVo>) mbDao.selectList("personPlan.getTeaFeedBack", paramMap);
		for (EvalVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<EvalVo> bootStrapPageVo = new BtTableVo<EvalVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}

	@Override
	public BtTableVo<EvalVo> selectFeedbackList2(Map<String, Object> paramMap) throws Exception {
		getLangParam(paramMap);// 获取国际化语言参数
		//处理搜索条件
		Object start_time = paramMap.get("update_time");
		if(start_time!=null){
			String[] info = start_time.toString().split("~");
			paramMap.put("update_time_1", DateTimeUtil.systemToDatabase(info[0].trim()));
			paramMap.put("update_time_2", DateTimeUtil.systemToDatabase(info[1].trim()));
		}
		//查询
		long total = (Long) mbDao.selectOne("personPlan.getStuFeedBackCount", paramMap);	
		@SuppressWarnings("unchecked")
		List<EvalVo> list = (List<EvalVo>) mbDao.selectList("personPlan.getStuFeedBack", paramMap);
		for (EvalVo temp : list) {
			temp.setCreate_time(DateTimeUtil.databaseToSystem(temp.getCreate_time()));
			temp.setUpdate_time(DateTimeUtil.databaseToSystem(temp.getUpdate_time()));
		}
		BtTableVo<EvalVo> bootStrapPageVo = new BtTableVo<EvalVo>();
		bootStrapPageVo.setTotal((int) total);
		bootStrapPageVo.setRows(list);
		return bootStrapPageVo;
	}
	
	@Override
	public PlanVo getPlanInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		PlanVo pv = (PlanVo)mbDao.selectOne("personPlan.getPlanDetail", paramMap);
		pv.setStart_time(DateTimeUtil.databaseToSystem(pv.getStart_time()));
		pv.setEnd_time(DateTimeUtil.databaseToSystem(pv.getEnd_time()));
		pv.setCreate_time(DateTimeUtil.databaseToSystem(pv.getCreate_time()));
		pv.setUpdate_time(DateTimeUtil.databaseToSystem(pv.getUpdate_time()));
		return pv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> downScoreData(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> info = new HashMap<String, Object>();
		List<Map<String, Object>> data = (List<Map<String, Object>>)mbDao.selectList("personPlan.exportPaperScore", paramMap);
		List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map : data){
			List<Map<String, Object>> section = (List<Map<String, Object>>)mbDao.selectList("personPlan.exportPaperSectionScore", map);
			int i = 1;
			info.put("length", section.size());
			for(Map<String, Object> tmp : section){
				map.put("section_name_"+i, tmp.get("section_name"));
				map.put("question_number_"+i, tmp.get("question_number"));
				map.put("get_score_"+i, tmp.get("get_score"));
				i++;
			}
			float score = 0.00f;
			float all_score = 0.00f;
			//每道题目
			List<Map<String, Object>> question = (List<Map<String, Object>>)mbDao.selectList("personPlan.exportPaperQuestionScore", map);
			info.put("qlength", question.size());
			i = 1;
			for(Map<String, Object> tmp : question){
				map.put("question_"+i, (tmp.get("score")==null?"0":tmp.get("score"))+"/"+tmp.get("question_score"));
				i++;
				//加分
				score = score + Float.parseFloat((tmp.get("score")==null?"0":tmp.get("score").toString()));
				all_score = all_score + Float.parseFloat(tmp.get("question_score").toString());
			}
			map.put("score", new DecimalFormat("#.00").format(score)+"/"+new DecimalFormat("#.00").format(all_score));
			re.add(map);		
		}
		info.put("data", re);
		return info;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> downAnswerData(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> info = new HashMap<String, Object>();
		List<Map<String, Object>> data = (List<Map<String, Object>>)mbDao.selectList("personPlan.exportPaperScore2", paramMap);
		List<Map<String, Object>> re = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> map : data){
			List<Map<String, Object>> answer = (List<Map<String, Object>>)mbDao.selectList("personPlan.exportPaperAnswer", map);
			int i = 1;
			info.put("length", answer.size());
			for(Map<String, Object> tmp : answer){
				map.put("question_type_"+i, tmp.get("type_name"));
				map.put("question_answer_"+i, tmp.get("answer"));
				i++;
			}
			re.add(map);		
		}
		info.put("data", re);
		return info;
	}

	@Override
	public int oneKeyPro(Map<String, Object> paramMap, UserVo teacher) throws Exception {
		// TODO Auto-generated method stub
		paramMap = checkAndAddNewStudent(paramMap,teacher);
		return mbDao.update("personPlan.oneKeyPro", paramMap);
	}

	@Override
	public int oneKeyScore(Map<String, Object> paramMap, UserVo teacher) throws Exception {
		// TODO Auto-generated method stub
		paramMap = checkAndAddNewStudent(paramMap,teacher);
		return mbDao.update("personPlan.oneKeyScore", paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> checkAndAddNewStudent(Map<String, Object> paramMap, UserVo teacher) throws Exception{
		paramMap.put("update_person", teacher.getName());
		paramMap.put("update_time", DateTimeUtil.nowToDatabase());
		//核对人数
		List<Map<String,Object>> list = (List<Map<String,Object>>)mbDao.selectList("personPlan.checkClassStudent", paramMap);
		for(Map<String,Object> map : list){
			map.putAll(paramMap);
			map.put("teacher_id", teacher.getId());
			map.put("create_time", DateTimeUtil.nowToDatabase());
			mbDao.insert("personPlan.insertNewPro", map);
		}
		return paramMap;
	}

	@Override
	public List selectTranscript(Map<String, Object> map) throws Exception {
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = (List<Map<String,Object>>)mbDao.selectList("personPlan.exportTranscript", map);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectRateData(String subj) throws Exception {
		if("MainFrame".equals(subj)){
			subj="MF";
		}else if("Testing".equals(subj)){
			subj="5";
		}
		return (Map<String, Object>) mbDao.selectOne("personPlan.selectRateData", subj);
	}
}
