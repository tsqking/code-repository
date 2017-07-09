package com.clps.tmp.question.question.service.impl;

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
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.question.question.service.QuestionService;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.clps.tmp.question.tag.vo.TagVo;
import com.clps.tmp.tech.point.vo.PointVo;

/**
 * 题库Service
 * @author jevon
 *
 */
@Service("questionService")
public class QuestionServiceImpl extends MBBaseService implements QuestionService {

	@Override
	public int changeQuestionStateDisable(QuestionVo questionVo) throws Exception {
		int count = this.mbDao.update("question.updateQuestionStatusToDisable", questionVo);
		return count;
	}
	
	@Override
	public int changeQuestionStateEnable(QuestionVo questionVo) throws Exception {
		int count = this.mbDao.update("question.updateQuestionStatusToEnable", questionVo);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BtTableVo<QuestionVo> findQuestionList(Map<String, Object> map) throws Exception {
		getLangParam(map);//获取国际化语言参数
		//题目数
		List<QuestionVo> questionList = (List<QuestionVo>) mbDao.selectList("question.queryQuestionCount", map);
        //题目信息
		List<QuestionVo> questionVoList = (List<QuestionVo>) mbDao.selectList("question.queryQuestion_Map", map);
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
	public Map<String,Object> findQuestion(QuestionVo questionVo) throws Exception {
		QuestionVo question = (QuestionVo) this.mbDao.selectOne("question.findQuestion", questionVo);
		List<PointVo> point_List = (List<PointVo>) this.mbDao.selectList("question.findPointByQuestionId", question);
		List<SelectVo> tagId_List = (List<SelectVo>) this.mbDao.selectList("question.findTag", question);
		question.setCreate_time(DateTimeUtil.databaseToSystem(question.getCreate_time()));
		question.setUpdate_time(DateTimeUtil.databaseToSystem(question.getUpdate_time()));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("question", question);
		map.put("point_List", point_List);
		map.put("tagId_List", tagId_List);
		return map;
	}

	@Override
	public int insertQuestion(QuestionVo questionVo,String pointIds,String tagIds) throws Exception {
		int count = this.mbDao.insert("question.insertNewQuestion", questionVo);
		//生成redis中题库map中的题目key
		String questionId="Q_"+questionVo.getId();
		RedisUtil redis = (RedisUtil) SpringContextUtil.getBean("redisUtil");
		//将新增的题目维护到reids数据库中
		redis.hSet("QUESTION", questionId, ObjectUtil.serialize(questionVo));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("question_id", questionVo.getId());
		if (pointIds != "" && pointIds != null) {
			String[] pointId = pointIds.split(",");
			for (int i = 0; i < pointId.length; i++) {
				map.put("point_id", Integer.parseInt(pointId[i]));
				this.mbDao.insert("question.insertPoint", map);
			}
		}
		if (tagIds != "" && tagIds != null) {
			String[] tagId = tagIds.split(",");
			for (int i = 0; i < tagId.length; i++) {
				map.put("tag_id", Integer.parseInt(tagId[i]));
				this.mbDao.insert("question.insertTag", map);
				// 标签使用次数加一
				this.mbDao.update("tag.updateUse_countUp", map);
			}
		}
		//插入图片
		List<String> imgs = questionVo.getQueImgs();
		if(imgs!=null){
			HashMap<String,Object> param = new HashMap<String,Object>();
			//插图片排序
			param.put("question_id", questionVo.getId());
			param.put("type", 0);
			String order = (String)this.mbDao.selectOne("question.getMaxOder", param);
			int i = 0;
			if(order!=null&&!order.equals("")){
				i = Integer.parseInt(order);
			}
			param.put("opt", 0);		
			param.put("flag", 1);
			param.put("del", "N");
			param.put("create_time", questionVo.getCreate_time());
			param.put("create_person", questionVo.getCreate_person());
			param.put("update_time", questionVo.getUpdate_time());
			param.put("update_person", questionVo.getUpdate_person());
			for(String id : imgs){
				i++;
				//插入试题图片
				param.put("image_id", id);
				param.put("order",i);
				this.mbDao.insert("question.addImgs", param);
			}
		}
		imgs = questionVo.getOptImgs();
		if(imgs!=null){
			HashMap<String,Object> param = new HashMap<String,Object>();
			//插图片排序
			param.put("question_id", questionVo.getId());
			param.put("type", 1);	
			param.put("flag", 1);
			param.put("del", "N");
			param.put("create_time", questionVo.getCreate_time());
			param.put("create_person", questionVo.getCreate_person());
			param.put("update_time", questionVo.getUpdate_time());
			param.put("update_person", questionVo.getUpdate_person());
			int i = 0;
			for(String id : imgs){
				i++;
				//插入试题图片
				param.put("image_id", id);
				param.put("order",0);
				param.put("opt", i);	
				this.mbDao.insert("question.addImgs", param);
			}	
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int updateQuestion(QuestionVo questionVo,String tagIds)  throws Exception {
		RedisUtil redis = (RedisUtil) SpringContextUtil.getBean("redisUtil");
		//查询该question下的tag和point
		List<TagVo> tag_list = (List<TagVo>) this.mbDao.selectList("question.findTagListByQuestion", questionVo);
		for(TagVo tagVo : tag_list){
			this.mbDao.update("tag.updateUse_countDown", tagVo);
		}
		//先删除该question关联的Tag
		this.mbDao.delete("question.deleteTag", questionVo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("question_id", questionVo.getId());
		if(tagIds!="" && tagIds!=null){
			String[] tagId = tagIds.split(",");
			for(int i=0; i<tagId.length; i++){
				map.put("tag_id", Integer.parseInt(tagId[i]));
				this.mbDao.insert("question.insertTag",map);
				//标签使用次数加一
				this.mbDao.update("tag.updateUse_countUp", map);
			}
		}
		//更新question内容
		int count = this.mbDao.update("question.updateQuestion", questionVo);
		QuestionVo qv=(QuestionVo) this.mbDao.selectOne("question.findQuestion", questionVo);
		qv.setQueImgs(questionVo.getQueImgs());
		qv.setQueImgsStr(questionVo.getQueImgsStr());
		qv.setOptImgs(questionVo.getOptImgs());
		qv.setOptImgsStr(questionVo.getOptImgsStr());
		// 生成redis中题库map中的题目key
		String questionId = "Q_" + qv.getId();
		// 将新增的题目维护到reids数据库中
		redis.hSet("QUESTION", questionId, ObjectUtil.serialize(qv));
		//维护图片
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("question_id", questionVo.getId());
		this.mbDao.delete("question.clearImges", param);
		// 插入图片
		List<String> imgs = questionVo.getQueImgs();
		if (imgs != null) {
			param = new HashMap<String, Object>();
			// 插图片排序
			param.put("question_id", questionVo.getId());
			param.put("type", 0);
			String order = (String) this.mbDao.selectOne("question.getMaxOder", param);
			int i = 0;
			if (order != null && !order.equals("")) {
				i = Integer.parseInt(order);
			}
			param.put("opt", 0);
			param.put("flag", 1);
			param.put("del", "N");
			param.put("create_time", DateTimeUtil.systemToDatabase(questionVo.getCreate_time()));
			param.put("create_person", questionVo.getCreate_person());
			param.put("update_time", DateTimeUtil.systemToDatabase(questionVo.getUpdate_time()));
			param.put("update_person", questionVo.getUpdate_person());
			for (String id : imgs) {
				i++;
				// 插入试题图片
				param.put("image_id", id);
				param.put("order", i);
				this.mbDao.insert("question.addImgs", param);
			}
		}
		imgs = questionVo.getOptImgs();
		if (imgs != null) {
			param = new HashMap<String, Object>();
			// 插图片排序
			param.put("question_id", questionVo.getId());
			param.put("type", 1);
			param.put("flag", 1);
			param.put("del", "N");
			param.put("create_time", DateTimeUtil.systemToDatabase(questionVo.getCreate_time()));
			param.put("create_person", questionVo.getCreate_person());
			param.put("update_time", DateTimeUtil.systemToDatabase(questionVo.getUpdate_time()));
			param.put("update_person", questionVo.getUpdate_person());
			int i = 0;
			for (String id : imgs) {
				i++;
				// 插入试题图片
				param.put("image_id", id);
				param.put("order", 0);
				param.put("opt", i);
				this.mbDao.insert("question.addImgs", param);
			}
		}
		return count;
	}

	@Override
	public int insertPoint(Map<String, Object> map) throws Exception {
		String pointIds = (String) map.get("pointIds");
		int count = -1;
		if(pointIds!="" && pointIds!=null){
			String[] pointId = pointIds.split(",");
			this.mbDao.delete("question.deletePoint", map);
			for(int i=0; i<pointId.length; i++){
				map.put("point_id", Integer.parseInt(pointId[i]));
				count = this.mbDao.insert("question.insertPoint", map);
			}
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findPoints(Map<String, Object> map) throws Exception {
	    String skillId=	("".equals((String)map.get("third_skill_id")) || null==(String)map.get("third_skill_id"))?
				(("".equals((String)map.get("second_skill_id")) || null==(String)map.get("second_skill_id"))?
				(String)map.get("first_skill_id"):
					(String)map.get("second_skill_id")):
						(String)map.get("third_skill_id");
		map.put("skill_id", skillId);
		List<Map<String,Object>> pointList = (List<Map<String, Object>>) this.mbDao.selectList("question.findPoints", map);
		return pointList;
	}

	@Override
	public int deletePoint(Map<String, Object> map) throws Exception {
		int count = this.mbDao.delete("question.deleteOnePoint", map);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuestionVo getImgs(QuestionVo questionVo) throws Exception {
		// TODO Auto-generated method stub
		//redis查询
		RedisUtil redis = (RedisUtil) SpringContextUtil.getBean("redisUtil");
		String que = redis.hGet("QUESTION", "Q_"+questionVo.getId());
		QuestionVo redisQuestionVo = null;
		if(que!=null){
			redisQuestionVo = (QuestionVo)ObjectUtil.unserialize(que);
			if(redisQuestionVo.getQueImgs()!=null&&redisQuestionVo.getQueImgsStr()!=null&&
					redisQuestionVo.getOptImgs()!=null&&redisQuestionVo.getOptImgsStr()!=null){
				return redisQuestionVo;
			}
		}
		//redis中没有查询数据库
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("question_id", questionVo.getId());
		map.put("type", "0");
		List<String> list = (List<String>)this.mbDao.selectList("question.getImges", map);
		if(list!=null){
			questionVo.setQueImgs(list);
			questionVo.setQueImgsStr(listToString(list));
		}
		map.put("type", "1");
		List<String> list1 = (List<String>)this.mbDao.selectList("question.getImges", map);
		if(list!=null){
			questionVo.setOptImgs(list1);
			questionVo.setOptImgsStr(listToString(list1));
		}
		//反写redis
		if(redisQuestionVo!=null){
			redisQuestionVo.setQueImgs(list);
			redisQuestionVo.setQueImgsStr(listToString(list));
			redisQuestionVo.setOptImgs(list1);
			redisQuestionVo.setOptImgsStr(listToString(list1));
			redis.hSet("QUESTION", "Q_"+redisQuestionVo.getId(), ObjectUtil.serialize(redisQuestionVo));
		}
		questionVo.setCreate_time(DateTimeUtil.databaseToSystem(questionVo.getCreate_time()));
		questionVo.setUpdate_time(DateTimeUtil.databaseToSystem(questionVo.getUpdate_time()));
		return questionVo;
	}
	private String listToString(List<String> list){
		StringBuilder sb = new StringBuilder();
		for(String s : list){
			sb.append(s).append("-");
		}
		return sb.toString();
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SelectVo> getAllPoint() throws Exception {
//		List<SelectVo> selectList = (List<SelectVo>) this.mbDao.selectList("question.queryAllSkill");
//		return selectList;
//	}
}
