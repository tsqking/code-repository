package com.clps.tmp.exam.onlineTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.common.util.ObjectUtil;
import com.clps.tmp.core.common.util.RedisUtil;
import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.exam.onlineTest.service.OnlineTestService;
import com.clps.tmp.exam.onlineTest.vo.OnlineTestVo;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;

@Service("ontestService")
public class OnlineTestServiceImpl extends MBBaseService implements
		OnlineTestService {
	
	/**
	 * 获取试卷相关信息
	 */
	@Override
	public HashMap<String, Object> findPaperById(Map<String, Object> paramMap)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//获取试卷相关方式
		OnlineTestVo ov = (OnlineTestVo) mbDao.selectOne("onlineTest.getPaperInfo", paramMap);
		map.put("totalTime",ov.getPv().getTotal_time());
		map.put("instruction", ov.getPv().getInstruction());
		map.put("paperName", ov.getPv().getName());
		map.put("totalItem", ov.getPv().getTotal_item());
		map.put("paperId", ov.getPv().getId());
		map.put("leaveLimitTotal", ov.getPv().getLeave_limit());
		map.put("property", ov.getPv().getProperty());
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public HashMap<String, Object> findQuestion(Map<String, Object> paramMap) throws Exception {
		String paperNo = "";
		List l = null;
		int questionId = 0;
		HashMap<String, Object> map = null;
		@SuppressWarnings("unchecked")
		List<HashMap<String, Object>> qustList = new ArrayList();
		RedisUtil redis = (RedisUtil) SpringContextUtil.getBean("redisUtil");
		String paperNbr=paramMap.get("paperNo")+"";
		int page=0;
		paperNo = "P_" + paperNbr;
		// redis中获取试题信息
		if (redis.hExists("PAPER", paperNo)) {
			l = (List) ObjectUtil.unserialize(redis.hGet("PAPER_QUST", paperNo));
		} else {
			// redis中没有数据是，查询db并将数据存入redis中
			l = mbDao.selectList("onlineTest.getQusetionIdList", paperNbr);
			redis.hSet("PAPER_QUST", paperNo, ObjectUtil.serialize(l));
		}
		if(paramMap.containsKey("leaveQustNo")&&(String) paramMap.get("leaveQustNo")!=null){
			page=Integer.parseInt(paramMap.get("leaveQustNo").toString())-1;
		}else{
			page=Integer.parseInt(paramMap.get("currentPage").toString())-1;
		}
		if (page < l.size()) {
			map = new HashMap<String, Object>();
			questionId = (Integer) l.get(page);
			QuestionVo qv = (QuestionVo) ObjectUtil.unserialize(redis.hGet("QUESTION", "Q_"+ questionId));
			map.put(questionId + "", qv);
			qustList.add(map);
		}
		HashMap<String,Object> reMap=new HashMap<String,Object>();
		reMap.put("qustList", qustList);
		//判断该试卷是否完成创建（完成则允许考试）
		//flag=(String)mbDao.selectOne("onlineTest.getFinishFlag",paramMap);
		reMap.put("flag", "1");
		reMap.put("count",String.valueOf(l.size()));
		reMap.put("qustPage", page+1);
		return reMap;
		}


	@Override
	public void addQustAnswer(List<Map<String,Object>> list) throws Exception {
		mbDao.insert("onlineTest.addQustAnswer", list);		
	}

	@Override
	public int findAnswerSheet(Map<String, Object> paramMap) throws Exception {
		int count=(Integer) mbDao.selectOne("onlineTest.findAnswerSheet",paramMap);
		return count;
	}

	@Override
	public int deleteAnswerSheet(Map<String, Object> paramMap) throws Exception {
		return mbDao.delete("onlineTest.deleteAnswerSheet", paramMap);
	}

	@Override
	public void addPaperInfo(Map<String, Object> paperMap) throws Exception {
		 mbDao.insert("onlineTest.addPaperInfo", paperMap);
	}

	@Override
	public int checkPaperInfo(Map<String, Object> paperMap) throws Exception {
		int num=(Integer) mbDao.selectOne("onlineTest.checkPaperInfo",paperMap);
		return num;
	}

	@Override
	public int deletePaperInfo(Map<String, Object> paperMap) throws Exception {
		return mbDao.delete("onlineTest.deletePaperInfo", paperMap);
		
	}

	@Override
	public String allowExam(Map<String, Object> paramMap) throws Exception {
		return (String) mbDao.selectOne("onlineTest.allowExam", paramMap);
	}

	@Override
	public String findPno(String paperId) throws Exception {
		return (String) mbDao.selectOne("onlineTest.getPapernoById", paperId);
	}

	@Override
	public String judgeWhetherTest(Map<String, Object> paramMap) throws Exception {
		return (String) mbDao.selectOne("onlineTest.getCountForExam",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> findPlanPaperInfo(
			Map<String, Object> paramMap) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//获取试卷相关方式
		List<PlanPaperVo>  list= (List<PlanPaperVo>) mbDao.selectList("onlineTest.getPlanPaperInfo", paramMap);
		PlanPaperVo ppv=list.get(0);
		map.put("paperType",ppv.getPaper_type());
		String pstartTime=DateTimeUtil.databaseToSystem(ppv.getPaper_start_time());
		map.put("id",  ppv.getId());
		map.put("paperStartTime",ppv.getPaper_start_time());
		map.put("paperEndTime", ppv.getPaper_end_time());
		String LpaperLongTime= ppv.getPaper_long_time();
		String a[]=LpaperLongTime.split("\\.");
		String paperLongTime=a[0];
		map.put("paperLongTime", paperLongTime);
		map.put("pstartTime", pstartTime);
		return map;
	}

	@Override
	public PaperVo findPaperInfoByNo(String no) throws Exception {
		return (PaperVo) this.mbDao.selectOne("paper.selectOneByNo", no);
	}

	@Override
	public int editPlanPaperHis(Map<String, Object> paramMap) throws Exception {
		return mbDao.update("onlineTest.editPlanPaperHis", paramMap);
	}
	@SuppressWarnings("unchecked")
	@Override
	public QuestionVo getImgs(Integer questionId) throws Exception {
		// TODO Auto-generated method stub
		QuestionVo qv=new QuestionVo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("question_id", questionId);
		map.put("type", "0");
		List<String> list = (List<String>)this.mbDao.selectList("question.getImges", map);
		if(list!=null){
			qv.setQueImgs(list);
			qv.setQueImgsStr(listToString(list));
		}
		map.put("type", "1");
		list = (List<String>)this.mbDao.selectList("question.getImges", map);
		if(list!=null){
			qv.setOptImgs(list);
			qv.setOptImgsStr(listToString(list));
		}
		return qv;
	}
	private String listToString(List<String> list){
		StringBuilder sb = new StringBuilder();
		for(String s : list){
			sb.append(s).append("-");
		}
		return sb.toString();
	}

}
