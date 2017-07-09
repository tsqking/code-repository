package com.clps.tmp.tech.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.TechPaperService;
import com.clps.tmp.tech.plan.vo.AnswerInfoVo;
import com.clps.tmp.tech.plan.vo.AnswerSheetVo;
import com.clps.tmp.tech.plan.vo.PlanVo;
import com.clps.tmp.tech.plan.vo.SectionVo;


@Service("techPaperService")
public class TechPaperServiceImpl extends MBBaseService implements TechPaperService {

	@Override
	public AnswerInfoVo getAnswerInfoVo(String paperId, String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.getLangParam(paramMap);
		paramMap.put("paper_id", paperId);
		paramMap.put("user_id", userId);
		return (AnswerInfoVo)mbDao.selectOne("paperUi.getAnswerInfo", paramMap);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SectionVo> getSectionInfo(String paperId, String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.getLangParam(paramMap);
		paramMap.put("paper_id", paperId);
		paramMap.put("user_id", userId);
		return (List<SectionVo>)mbDao.selectList("paperUi.getSection", paramMap);	
	}

	@Override
	public AnswerInfoVo getProgress(String paperId, String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.getLangParam(paramMap);
		paramMap.put("paper_id", paperId);
		paramMap.put("user_id", userId);
		return (AnswerInfoVo)mbDao.selectOne("paperUi.getCheckCount", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnswerSheetVo> getAnswerSheet(String paperId, String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.getLangParam(paramMap);
		paramMap.put("paper_id", paperId);
		paramMap.put("user_id", userId);
		return (List<AnswerSheetVo>)mbDao.selectList("paperUi.getSectionQuestionList", paramMap);
	}

	@Override
	public int setScore(AnswerSheetVo answerSheetVo,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		answerSheetVo.setJudge_person(user.getName());
		answerSheetVo.setJudge_time(DateTimeUtil.nowToDatabase());
		return mbDao.update("paperUi.updateAnswerSheet", answerSheetVo);
	}

	@Override
	public int setRemark(AnswerSheetVo answerSheetVo) throws Exception {
		// TODO Auto-generated method stub
		return mbDao.update("paperUi.updateAnswerSheet2", answerSheetVo);
	}

	@Override
	public int setPaperScore(AnswerInfoVo answerInfoVo, UserVo user) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.getLangParam(paramMap);
		paramMap.put("paper_id", answerInfoVo.getPaper_id());
		paramMap.put("user_id", answerInfoVo.getUser_id());
		paramMap.put("judge_person", user.getName());
		paramMap.put("judge_time", DateTimeUtil.nowToDatabase());
		paramMap.put("student_id", answerInfoVo.getUser_id());
		paramMap.put("teacher_id", user.getId());
		paramMap.put("check_time", DateTimeUtil.nowToDatabase());
		paramMap.put("id", answerInfoVo.getPlan_paper_id());
		int i = mbDao.update("paperUi.updateAnswerInfo", paramMap);
		i = i + mbDao.update("paperUi.updatePlanPaperHis", paramMap);
		return i;
	}

	@Override
	public List<AnswerSheetVo> setAutoCorrectPaper(String paperId, String userId,UserVo user) throws Exception {
		// TODO Auto-generated method stub
		List<AnswerSheetVo> li = new ArrayList<AnswerSheetVo>();
		//1.查阅题目和答案(学生答案+标准答案)
		List<AnswerSheetVo> list = this.getAnswerSheet(paperId, userId);
		//2.判断答案
		for(AnswerSheetVo asv : list){
			String score = "0.00";
			boolean remark = true;
			//主客观 s-Subject主观题，o-Object客观题
			String so_flag = asv.getSo_flag();
			if(so_flag.equals("o")){
				//客观
				String ansewr = asv.getAnswer().replaceAll(" ", "").replaceAll("#\\$#", "");
				String true_ansewr = asv.getTrue_answer().replaceAll(" ", "").replaceAll("#\\$#", "");
				if(ansewr.equals(true_ansewr)){
					score = asv.getAll_score();
				}
				if(asv.getRemark()==null||asv.getRemark().equals("")){
					remark = true;
				}else{
					remark = false;
				}
			}else{
				//主观
				continue;
			}
			//3.记录分数
			asv.setScore(Float.parseFloat(score));
			asv.setUser_id(userId);
			asv.setPaper_id(paperId);
			asv.setRemark("Computer Scoring");
			this.setScore(asv, user);
			if(remark){
				this.setRemark(asv);
			}
			li.add(asv);
		}
		return li;
	}
	
	
	@Override
	public boolean checkCourseClassOnly(PlanVo vo) throws Exception {
		// TODO Auto-generated method stub
		String count = (String)mbDao.selectOne("personPlan.checkCourseClassOnly", vo);	
		if(count==null){
			return true;
		}else{
			if(count.equals("0")){
				return true;
			}else{
				return false;
			}
		}
	}
}
