package com.clps.tmp.question.paper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.service.MBBaseService;
import com.clps.tmp.core.common.util.ObjectUtil;
import com.clps.tmp.core.common.util.RedisUtil;
import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.question.paper.service.PaperService;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.question.paper.vo.SectionQuestionVo;
import com.clps.tmp.question.paper.vo.SectionVo;
import com.clps.tmp.question.question.vo.QuestionVo;

@Service("paperService")
public class PaperServiceImpl extends MBBaseService implements PaperService{
	@SuppressWarnings("unchecked")
	@Override
	public BtTableVo<PaperVo> findPaperList(Map<String, Object> map) throws Exception {
		getLangParam(map);//获取国际化语言参数
		//试卷数
		List<PaperVo> paperList = (List<PaperVo>) mbDao.selectList("paper.queryPaperCount", map);
        //试卷信息
		List<PaperVo> paperVoList = (List<PaperVo>) mbDao.selectList("paper.queryPaperMap", map);
        for(PaperVo paperVo : paperVoList){
        	paperVo.setCreate_time(DateTimeUtil.databaseToSystem(paperVo.getCreate_time()));
        	paperVo.setUpdate_time(DateTimeUtil.databaseToSystem(paperVo.getUpdate_time()));
        }
        BtTableVo<PaperVo> bootStrapPageVo = new BtTableVo<PaperVo>();
        bootStrapPageVo.setTotal(paperList.size());
        bootStrapPageVo.setRows(paperVoList);
        return bootStrapPageVo;
	}

	@Override
	public int insertPaper(PaperVo paperVo) throws Exception {
		this.mbDao.insert("paper.InsertOrUpdatePaper", paperVo);
		return paperVo.getId();
	}
	
	@Override
	public int updatePaperStatus(PaperVo paperVo) throws Exception {
		int count = this.mbDao.update("paper.updatePaperStatus", paperVo);
		return count;
	}
	
	@Override
	public int validatePaperStatus(PaperVo paperVo) throws Exception {
		String finish_flag = (String) this.mbDao.selectOne("paper.validatePaperStatus", paperVo);
		int count = Integer.parseInt(finish_flag);
		return count;
	}

	@Override
	public Map<String,Object> findPaperById(PaperVo paperVo) throws Exception {
		PaperVo PaperVo = (PaperVo) this.mbDao.selectOne("paper.selectOne", paperVo);
		long section_count = (Long) this.mbDao.selectOne("paper.findSectionCount", PaperVo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paperVo", PaperVo);
		map.put("section_count", section_count);
		return map;
	}

	@Override
	public int insertSection(SectionVo sectionVo,PaperVo paperVo,Map<String,Object> map) throws Exception {
		this.mbDao.insert("paper.insertSection", sectionVo);
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("paper_id", paperVo.getId());
		paraMap.put("section_id", sectionVo.getId());
		paraMap.put("order", map.get("order"));
		long record = (Long) this.mbDao.selectOne("paper.selectRecord", paraMap);
		if(record <= 0){//未找到该记录，表示这张试卷没有这个部分
			this.mbDao.insert("paper.insertPaperSection", paraMap);
		}
		if(map.get("questionIds")!=null && map.get("questionPoints")!=null){
			String questionIds = (String) map.get("questionIds");
			String[] questionId = questionIds.split(",");
			String questionPoints = (String) map.get("questionPoints");
			String[] questionPoint = questionPoints.split(",");
			if(!questionIds.equals(",") && !questionIds.equals("")){//该部分含有题目
				if(record > 0){
					//删除该部分对应的题目内容
					this.mbDao.delete("paper.deleteSectionQuestion", sectionVo);
				}
				for(int i=0; i<questionId.length; i++){
					paraMap.put("question_id", questionId[i]);
					if(!questionPoints.equals(",") && !questionPoints.equals("")){
						if(i<questionPoint.length){//该部分含有多个题目，部分题目未设置分数(当最后的题目未设置分数时，分数数组的长度要比ID的数组长度小)
							if(questionPoint[i].equals("") || questionPoint[i]==null){//(当前面有未设置的分数，而不是最后一个，那么两数组长度相同，分数内容没有)
								paraMap.put("question_score", "0");
							}else{
								paraMap.put("question_score", questionPoint[i]);
							}
						}else{
							paraMap.put("question_score", "0");
						}
					}else{//该部分所有的题目都为设置分数
						paraMap.put("question_score", "0");
					}
					paraMap.put("order", i);
					this.mbDao.insert("paper.insertSectionQuestion", paraMap);
				}
			}else{
				//删除该部分对应的题目内容
				this.mbDao.delete("paper.deleteSectionQuestion", sectionVo);
			}
		}
		return paperVo.getId();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int deleteSection(Map<String, Object> paraMap) throws Exception {
		SectionVo sectionVo = (SectionVo) this.mbDao.selectOne("paper.selectSection", paraMap);
		int count = 0;
		//修改部分排序
		List<HashMap<String,Object>> listOrder = (List<HashMap<String,Object>>) this.mbDao.selectList("paper.selectPaperSection", paraMap);
		for(HashMap<String,Object> map : listOrder){
			int order = (Integer) map.get("order");
			int section_order = (Integer) paraMap.get("order");
			if(order > section_order){
				this.mbDao.update("paper.updateSectionOrder", map);
			}
		}
		if(sectionVo != null){//表示该部分保存过
			//删除该部分
			this.mbDao.delete("paper.deletePaperSection", sectionVo);
			//删除该部分对应的题目
			this.mbDao.delete("paper.deleteSectionQuestion", sectionVo);
			count = this.mbDao.delete("paper.deleteSection",sectionVo);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> findSection(Map<String,Object> paraMap) throws Exception {
		getLangParam(paraMap);//获取国际化语言参数
		SectionVo sectionVo = (SectionVo) this.mbDao.selectOne("paper.selectSection", paraMap);
		if(sectionVo != null){//该部分为原来已有的部分
			paraMap.put("sectionVo", sectionVo);
			paraMap.put("section_id", sectionVo.getId());
			List<SectionQuestionVo> list = (List<SectionQuestionVo>) this.mbDao.selectList("paper.selectQuestion", paraMap);
			paraMap.put("questionList", list);
		}else{//该部分为新增的部分
			paraMap.put("sectionVo", null);
			paraMap.put("questionList", null);
		}
		return paraMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findPaperInfo(Map<String, Object> map) throws Exception {
		List<Map<String,Object>> paperList = (List<Map<String,Object>>) this.mbDao.selectList("paper.findPaperInfo",map);
		int total_point = 0;
		int i = 0;
		for(Map<String,Object> paperMap : paperList){
			map.put("total_time", paperMap.get("total_time"));
			if(paperMap.get("question_score") == null){//表示有部分不包含题目
				paperMap.put("question_score", 0.00f);
				i++;
			}
			float point = (Float) paperMap.get("question_score");
			total_point += (int)point;
			map.put("total_item", paperList.size()-i);
		}
		map.put("total_point", total_point);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findAllPaperInfo(Map<String, Object> map) throws Exception {
		List<Map<String,Object>> paperList = (List<Map<String,Object>>) this.mbDao.selectList("paper.findPaperInfo",map);
		int total_point = 0;
		int i = 0;
		int listSize=paperList.size();
		for(Map<String,Object> paperMap : paperList){
			map.put("total_time", paperMap.get("total_time"));
			if(paperMap.get("question_score") == null){
				paperMap.put("question_score", 0.00f);
				i++;
			}
			float point = (Float) paperMap.get("question_score");
			total_point += (int)point;
			map.put("total_item", listSize-i);
			map.put("paper_name", paperMap.get("paper_name"));
			map.put("paper_instruction", paperMap.get("paper_instruction"));
			map.put("question_id", paperMap.get("question_id"));	
		}
		map.put("total_point", total_point);
		map.put("questionList", paperList);
		return map;
	}
	
	@Override
	public int deleteSection(PaperVo paperVo) throws Exception {
		int count = this.mbDao.delete("paper.deletePaper", paperVo);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BtTableVo<QuestionVo> findQuestionList(Map<String, Object> map) throws Exception {
		getLangParam(map);//获取国际化语言参数
		String Ids = (String) map.get("Ids");
		List<Integer> list_id = new ArrayList<Integer>();
		if(!Ids.equals("") && Ids!=null){
			String[] arr_id = Ids.split(",");
			for(int i=0; i<arr_id.length; i++){
				if(arr_id[i]!="" && arr_id[i]!=null){
					list_id.add(Integer.parseInt(arr_id[i]));
				}
			}
			map.put("Ids", list_id);
		}else{
			list_id.add(-1);
			map.put("Ids", list_id);
		}
		//题目数
		List<QuestionVo> questionList = (List<QuestionVo>) mbDao.selectList("paper.queryQuestionCount", map);
        //题目信息
		List<QuestionVo> questionVoList = (List<QuestionVo>) mbDao.selectList("paper.queryQuestion_Map", map);
        for(QuestionVo questionVo : questionVoList){
        	questionVo.setCreate_time(DateTimeUtil.databaseToSystem(questionVo.getCreate_time()));
        	questionVo.setUpdate_time(DateTimeUtil.databaseToSystem(questionVo.getUpdate_time()));
        }
        BtTableVo<QuestionVo> bootStrapPageVo = new BtTableVo<QuestionVo>();
        bootStrapPageVo.setTotal(questionList.size());
        bootStrapPageVo.setRows(questionVoList);
        return bootStrapPageVo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String findPaperQuestion(PaperVo paperVo) throws Exception {
		List<Map<String,Object>> Id_List = (List<Map<String, Object>>) this.mbDao.selectList("paper.findPaperQuestion", paperVo);
		StringBuilder sb = new StringBuilder();
		for(Map<String,Object> IdMap : Id_List){
			int question_id = (Integer) IdMap.get("question_id");
			sb.append(String.valueOf(question_id)+",");
		}
		String Ids = sb.toString();
		return Ids;
	}

	@Override
	public int saveQuestion(Map<String, Object> map) throws Exception {
		SectionVo sectionVo = (SectionVo) this.mbDao.selectOne("paper.selectSection", map);
		String questionIds = (String) map.get("questionIds");
		String[] questionId = questionIds.split(",");
		int count = -1;
		for(int i=0; i<questionId.length; i++){
			map.put("question_id", questionId[i]);
			map.put("section_id", sectionVo.getId());
			map.put("question_score", 0);
			map.put("order", i);
			count = this.mbDao.insert("paper.insertSectionQuestion", map);
		}
		List l = null;
		String paperId=map.get("paper_id")+"";
		String paperNbr=(String) this.mbDao.selectOne("onlineTest.getPapernoById", paperId);
		String paperNo = "P_" + paperNbr;
		RedisUtil redis = (RedisUtil) SpringContextUtil.getBean("redisUtil");
		l = mbDao.selectList("onlineTest.getQusetionIdList", paperNbr);
		redis.hSet("PAPER_QUST", paperNo, ObjectUtil.serialize(l));
		return count;
	}

	@Override
	public int deleteQuestion(Map<String, Object> map) throws Exception {
		SectionVo sectionVo = (SectionVo) this.mbDao.selectOne("paper.selectSection", map);
		map.put("section_id", sectionVo.getId());
		int count = this.mbDao.delete("paper.deleteQuestion", map);
		return count;
	}

	@Override
	public void saveTotalInfoForPaper(PaperVo paperVo) throws Exception {
		this.mbDao.update("paper.updateTotalInfo", paperVo);
	}
	
	@Override
	public void savePaperInfo(PaperVo paperVo) throws Exception {
		this.mbDao.update("paper.updatePaperInfo", paperVo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperVo> findPaperProperty(PaperVo paperVo) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		getLangParam(map);//获取国际化语言参数
		map.put("id", paperVo.getId());
		List<PaperVo> list_paper = (List<PaperVo>) this.mbDao.selectList("paper.findPaperProperty", map);
		return list_paper;
	}

	@Override
	public int changeEnableState(PaperVo paperVo) throws Exception {
		return mbDao.update("paper.changeEnableState", paperVo);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean copyPaper(String old_paper_id, PaperVo paperVo,String user) throws Exception {
		// TODO Auto-generated method stub
		//添加试卷
		paperVo.setId(0);
		int re = mbDao.insert("paper.InsertOrUpdatePaper", paperVo);
		if(re == 1){
			int new_paper_id = paperVo.getId();
			//查询题块关联
			List<Map<String,Object>> li = (List<Map<String, Object>>) mbDao.selectList("paper.selectOldSection", old_paper_id);
			for(Map<String,Object> old_section : li){
				String old_section_id = old_section.get("section_id").toString();
				//查询题块信息
				Map<String,Object> old_section_info = (Map<String, Object>) mbDao.selectOne("paper.selectOldSectionInfo", old_section_id);
				//添加题块记录
				old_section_info.put("create_time", DateTimeUtil.nowToDatabase());
				old_section_info.put("create_person", user);
				old_section_info.put("update_time", DateTimeUtil.nowToDatabase());
				old_section_info.put("update_person", user);
				re = mbDao.insert("paper.insertNewSectionInfo", old_section_info);
				if(re == 1){
					String new_section_id = old_section_info.get("id").toString();
					//增加题块关联
					old_section.put("paper_id", new_paper_id);
					old_section.put("section_id", new_section_id);
					re = mbDao.insert("paper.insertNewSection", old_section);
					if(re == 1){
						//查询原来的题目关联
						List<Map<String,Object>> li2 = (List<Map<String,Object>>) mbDao.selectList("paper.selectOldQuestion", old_section_id);				
						for(Map<String,Object> old_question : li2){
							//插入新的关联
							old_question.put("section_id", new_section_id);
							re = mbDao.insert("paper.insertNewQuestion", old_question);
							if(re == 1){
								//nothing to do
							}else{
								return false;
							}	
						}						
					}else{
						return false;
					}			
				}else{
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}

}
