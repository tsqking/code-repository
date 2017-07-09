package com.clps.tmp.question.question.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.question.question.service.QuestionService;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.clps.tmp.tech.point.vo.PointVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 题库Action
 * @author jevon
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/question")
@Controller
@Scope("prototype")
@Action("question")
@Results({@Result(name = "toQuestionManagerPage", location="question/questionManager.jsp"),
		@Result(name = "addNewQuestionPage", location="question/addQuestion.jsp"),
		@Result(name = "toEditQuestionPage", location="question/editQuestion.jsp"),
		@Result(name = "toPointManagePage", location="question/pointManage.jsp"),
		@Result(name = "toQuestionDetailPage", location="question/detailQuestion.jsp"),
		//各题目分页面
		@Result(name = "toShortAnswerPage", location="question/shortAnswer.jsp"),
		@Result(name = "toTrueOrFalsePage", location="question/trueOrFalse.jsp"),
		@Result(name = "toSingleChoisePage", location="question/singleChoise.jsp"),
		@Result(name = "toMultipleChoisePage", location="question/multipleChoise.jsp"),
		@Result(name = "toFillingPage", location="question/filling.jsp"),
		//各题目编辑分页面
		@Result(name = "toEditShortAnswerPage", location="question/editShortAnswer.jsp"),
		@Result(name = "toEditTrueOrFalsePage", location="question/editTrueOrFalse.jsp"),
		@Result(name = "toEditSingleChoisePage", location="question/editSingleChoise.jsp"),
		@Result(name = "toEditMultipleChoisePage", location="question/editMultipleChoise.jsp"),
		@Result(name = "toEditFillingPage", location="question/editFilling.jsp"),
		//各题目详情分页面
		@Result(name = "toDetailShortAnswerPage", location="question/detailShortAnswer.jsp"),
		@Result(name = "toDetailTrueOrFalsePage", location="question/detailTrueOrFalse.jsp"),
		@Result(name = "toDetailSingleChoisePage", location="question/detailSingleChoise.jsp"),
		@Result(name = "toDetailMultipleChoisePage", location="question/detailMultipleChoise.jsp"),
		@Result(name = "toDetailFillingPage", location="question/detailFilling.jsp"),
		@Result(name = "json", type = "json", params = { "root", "questionMap" })})
public class QuestionAction extends BaseAction implements ModelDriven{

	private QuestionVo questionVo;
	
	private HashMap<String,Object> questionMap;
	
	@Resource
	private QuestionService questionService;
	//知识点技能
	private String pointSkill;
	//知识点Names
	private String pointNames;
	//知识点IDs
	private String pointIds;
	//标签IDs
	private String tagIds;
	/**
	 * 前往题库管理界面
	 * @return
	 * @throws Exception
	 */
	public String toQuestionManagerPage() throws Exception{
		return "toQuestionManagerPage";
	}
	
	/**
	 * 前往知识点管理界面
	 * @return
	 * @throws Exception
	 */
	public String toPointManagePage() throws Exception{
		return "toPointManagePage";
	}
	
	/**
	 * 获取题库中的题目
	 * @return
	 * @throws Exception
	 */
	public String findQuestionData() throws Exception{
		BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<QuestionVo> bootStrapPageVo= questionService.findQuestionList(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    questionMap=rtn.getReturnMap();
	    return "json";
	}
	
	/**
	 * 前往添加题目页面
	 * @return
	 * @throws Exception
	 */
	public String addNewQuestionPage() throws Exception{
		return "addNewQuestionPage";
	}
	
	/**
	 * 前往题目编辑页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String toEditQuestionPage() throws Exception{
		questionMap = (HashMap<String, Object>) questionService.findQuestion(questionVo);
		questionVo = (QuestionVo) questionMap.get("question");
		//知识点
		List<PointVo> pointList = (List<PointVo>) questionMap.get("point_List");
		if(pointList.size()>0){
			StringBuilder pointName_builder = new StringBuilder();
			for(PointVo pointVo : pointList){
				pointName_builder.append("【"+String.valueOf(pointVo.getName())+"】");
			}
			pointNames = new String(pointName_builder).substring(0, pointName_builder.length());
		}
		//标签
		List<SelectVo> tagList = (List<SelectVo>) questionMap.get("tagId_List");
		if(tagList.size()>0){
			StringBuilder tag_builder = new StringBuilder();
			for(SelectVo selectVo : tagList){
				tag_builder.append(String.valueOf(selectVo.getId())+",");
			}
			tagIds = new String(tag_builder).substring(0, tag_builder.length()-1);
		}
		//获取图片
		questionVo = questionService.getImgs(questionVo);
		return "toEditQuestionPage";
	}
	
	/**
	 * 查询该题目下包含的知识点
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findPointsByQuestion() throws Exception{
		Map<String,Object> map = (HashMap<String, Object>) questionService.findQuestion(questionVo);
		List<PointVo> pointList = (List<PointVo>) map.get("point_List");
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (pointList.size() > 0) {
            rtn = AjaxReturnInfo.success("true");
            rtn.add("pointList", pointList);
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        questionMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 前往题目详情页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String toQuestionDetailPage() throws Exception{
		questionMap = (HashMap<String, Object>) questionService.findQuestion(questionVo);
		questionVo = (QuestionVo) questionMap.get("question");
		//知识点
		List<PointVo> pointList = (List<PointVo>) questionMap.get("point_List");
		if(pointList.size()>0){
			Map<String,Object> pointSkillMap = new HashMap<String,Object>();
			StringBuilder point_builder = new StringBuilder();
			StringBuilder pointName_builder = new StringBuilder();
			for(PointVo pointVo : pointList){
				pointSkillMap.put("id", pointVo.getId());
				pointSkillMap.put("third_skill", pointVo.getThird_skill());
				pointSkillMap.put("second_skill", pointVo.getSecond_skill());
				pointSkillMap.put("first_skill", pointVo.getFirst_skill());
				pointSkillMap.put("name", pointVo.getName());
				pointSkillMap.put("name_en_US", pointVo.getName_en_US());
				point_builder.append(pointSkillMap.get("id")+","+pointSkillMap.get("name")+","+pointSkillMap.get("name_en_US")+","+
									pointSkillMap.get("first_skill")+","+pointSkillMap.get("second_skill")+","+pointSkillMap.get("third_skill")+",");
				pointName_builder.append("【"+String.valueOf(pointVo.getName())+"】【");
			}
			pointSkill = new String(point_builder).substring(0, point_builder.length()-1);
			pointNames = new String(pointName_builder).substring(0, pointName_builder.length()-1);
		}
		//标签
		List<SelectVo> tagList = (List<SelectVo>) questionMap.get("tagId_List");
		if(tagList.size()>0){
			StringBuilder tag_builder = new StringBuilder();
			for(SelectVo selectVo : tagList){
				tag_builder.append(String.valueOf(selectVo.getId())+",");
			}
			tagIds = new String(tag_builder).substring(0, tag_builder.length()-1);
		}
		//获取图片
		questionVo = questionService.getImgs(questionVo);
		return "toQuestionDetailPage";
	}
	
	/**
	 * 添加知识点
	 * @return
	 * @throws Exception
	 */
	public String savePoint() throws Exception{
		//知识点IDs
		String pointIds = this.httpRequest.getParameter("pointIds");
		String questionId = this.httpRequest.getParameter("questionId");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("question_id", questionId);
		map.put("pointIds", pointIds);
		int count = -1;
		if(questionId != null){//表示这个题目已经生成
			count = this.questionService.insertPoint(map);
		}
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (count > 0) {
            rtn = AjaxReturnInfo.success("true");
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        questionMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 查找技能下的知识点
	 * @return
	 * @throws Exception
	 */
	public String findPoints() throws Exception{
		String first_skill_id = this.httpRequest.getParameter("first_skill_id");
		String second_skill_id = this.httpRequest.getParameter("second_skill_id");
		String third_skill_id = this.httpRequest.getParameter("third_skill_id");
		String point_id = this.httpRequest.getParameter("point_id");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("first_skill_id", first_skill_id);
		map.put("second_skill_id", second_skill_id);
		map.put("third_skill_id", third_skill_id);
		map.put("point_id", point_id);
		List<Map<String,Object>> pointList = this.questionService.findPoints(map);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (pointList.size() > 0) {
            rtn = AjaxReturnInfo.success("true");
            rtn.add("pointList", pointList);
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        questionMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除知识点
	 * @return
	 * @throws Exception
	 */
	public String deletePoint() throws Exception{
		String questionId = this.httpRequest.getParameter("questionId");
		String point_id = this.httpRequest.getParameter("point_id");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("point_id", point_id);
		int count = -1;
		if(questionId != null){
			map.put("question_id", questionId);
			count = this.questionService.deletePoint(map);
		}
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (count > 0) {
            rtn = AjaxReturnInfo.success("true");
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        questionMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 添加题目
	 * @return
	 * @throws Exception
	 */
	public String addNewQuestion() throws Exception{
		//选项内容
		String selectItems = this.httpRequest.getParameter("selectItems");
		//知识点IDs
		pointIds = this.httpRequest.getParameter("pointIds");
		//标签IDs
		tagIds = this.httpRequest.getParameter("tagIds");
		//问题图片
		String queImgIds = this.httpRequest.getParameter("queImgIds");
		if(queImgIds!=null&&!queImgIds.equals("")){
			String[] ids = queImgIds.split("-");
			List<String> re = new ArrayList<String>();
			for(String tem : ids){
				if(tem==null||tem.equals("")){
					break;
				}
				re.add(tem);
			}
			questionVo.setQueImgs(re);
		}
		//选项图片
		String optImgIds = this.httpRequest.getParameter("optImgIds");
		if(optImgIds!=null&&!optImgIds.equals("")){
			String[] ids = optImgIds.split("-");
			List<String> re = new ArrayList<String>();
			for(String tem : ids){
				if(tem==null||tem.equals("")){
					break;
				}
				re.add(tem);
			}
			questionVo.setOptImgs(re);
		}
		//
		String[] content = selectItems.split("\\%\\^\\*\\^\\%");
		if("4".equals(questionVo.getType())){//填空题
			StringBuffer fillingAnswer=new StringBuffer();
			for(int i=0;i<content.length;i++)
				fillingAnswer.append(content[i]+"#$#");
			fillingAnswer.setLength(fillingAnswer.length()-3);
			questionVo.setAnswer(fillingAnswer.toString());
		}else{
			if(content.length>=1){
				questionVo.setOpt1(content[0]);
			}
			if(content.length>=2){
				questionVo.setOpt2(content[1]);
			}
			if(content.length>=3){
				questionVo.setOpt3(content[2]);
			}
			if(content.length>=4){
				questionVo.setOpt4(content[3]);
			}
			if(content.length>=5){
				questionVo.setOpt5(content[4]);
			}
			if(content.length==6){
				questionVo.setOpt6(content[5]);
			}
		}
		questionVo.setCreate_time(DateTimeUtil.nowToDatabase());
		questionVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		questionVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		questionVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		
		int count = questionService.insertQuestion(questionVo,pointIds,tagIds);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (count == 1) {
            rtn = AjaxReturnInfo.success("0000");
        } else {
            rtn = AjaxReturnInfo.success("1111");
        }
        questionMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 更新题目
	 * @return
	 * @throws Exception
	 */
	public String updateQuestion() throws Exception{
		String selectItems = this.httpRequest.getParameter("selectItems");
		// 问题图片
		String queImgIds = this.httpRequest.getParameter("queImgIds");
		if (queImgIds != null && !queImgIds.equals("")) {
			String[] ids = queImgIds.split("-");
			List<String> re = new ArrayList<String>();
			for (String tem : ids) {
				if (tem == null || tem.equals("")) {
					break;
				}
				re.add(tem);
			}
			questionVo.setQueImgs(re);
		}
		// 选项图片
		String optImgIds = this.httpRequest.getParameter("optImgIds");
		if (optImgIds != null && !optImgIds.equals("")) {
			String[] ids = optImgIds.split("-");
			List<String> re = new ArrayList<String>();
			for (String tem : ids) {
				if (tem == null || tem.equals("")) {
					break;
				}
				re.add(tem);
			}
			questionVo.setOptImgs(re);
		}
		// 标签IDs
		String tagIds = this.httpRequest.getParameter("tagIds");
		String[] content = selectItems.split("\\%\\^\\*\\^\\%");
		if("4".equals(questionVo.getType())){//填空题
			StringBuffer fillingAnswer=new StringBuffer();
			for(int i=0;i<content.length;i++)
				fillingAnswer.append(content[i]+"#$#");
			fillingAnswer.setLength(fillingAnswer.length()-3);
			questionVo.setAnswer(fillingAnswer.toString());
		}else{
			if(content.length>=1){
				questionVo.setOpt1(content[0]);
			}
			if(content.length>=2){
				questionVo.setOpt2(content[1]);
			}
			if(content.length>=3){
				questionVo.setOpt3(content[2]);
			}
			if(content.length>=4){
				questionVo.setOpt4(content[3]);
			}
			if(content.length>=5){
				questionVo.setOpt5(content[4]);
			}
			if(content.length==6){
				questionVo.setOpt6(content[5]);
			}
		}
		questionVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		questionVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		
		int count = questionService.updateQuestion(questionVo,tagIds);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (count == 1) {
            rtn = AjaxReturnInfo.success("0000");
        } else {
            rtn = AjaxReturnInfo.success("1111");
        }
        questionMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 禁用题目
	 * @return
	 * @throws Exception
	 */
	public String changeQuestionStateDisable() throws Exception{
		AjaxReturnInfo rtn = null;
        int effect = questionService.changeQuestionStateDisable(questionVo);
        if (effect<0)// -1-使用中，不能删除
            rtn = AjaxReturnInfo.failed(String.valueOf(effect));
        else
            rtn = AjaxReturnInfo.success("true");
        questionMap = rtn.getReturnMap();
        return "json";
	}
	
	/**
	 * 启用题目
	 * @return
	 * @throws Exception
	 */
	public String changeQuestionStateEnable() throws Exception{
		AjaxReturnInfo rtn = null;
        int effect = questionService.changeQuestionStateEnable(questionVo);
        if (effect<=0)//更改失败
            rtn = AjaxReturnInfo.failed(String.valueOf(effect));
        else
            rtn = AjaxReturnInfo.success("true");
        questionMap = rtn.getReturnMap();
        return "json";
	}
	
	/**
	 * 前往简答题编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditShortAnswerPage() throws Exception{
		return "toEditShortAnswerPage";
	}
	
	/**
	 * 前往判断题编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditTrueOrFalsePage() throws Exception{
		return "toEditTrueOrFalsePage";
	}
	
	/**
	 * 前往单选题编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditSingleChoisePage() throws Exception{
		return "toEditSingleChoisePage";
	}
	
	/**
	 * 前往多选题编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditMultipleChoisePage() throws Exception{
		return "toEditMultipleChoisePage";
	}
	
	/**
	 * 前往填空题编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditFillingPage() throws Exception{
		return "toEditFillingPage";
	}
	
	
	/**
	 * 前往简答题详情页面
	 * @return
	 * @throws Exception
	 */
	public String toDetailShortAnswerPage() throws Exception{
		return "toDetailShortAnswerPage";
	}
	
	/**
	 * 前往判断题详情页面
	 * @return
	 * @throws Exception
	 */
	public String toDetailTrueOrFalsePage() throws Exception{
		return "toDetailTrueOrFalsePage";
	}
	
	/**
	 * 前往单选题详情页面
	 * @return
	 * @throws Exception
	 */
	public String toDetailSingleChoisePage() throws Exception{
		return "toDetailSingleChoisePage";
	}
	
	/**
	 * 前往多选题详情页面
	 * @return
	 * @throws Exception
	 */
	public String toDetailMultipleChoisePage() throws Exception{
		return "toDetailMultipleChoisePage";
	}
	
	/**
	 * 前往填空题详情页面
	 * @return
	 * @throws Exception
	 */
	public String toDetailFillingPage() throws Exception{
		return "toDetailFillingPage";
	}
	
	/**
	 * 前往简答题页面
	 * @return
	 * @throws Exception
	 */
	public String toShortAnswerPage() throws Exception{
		return "toShortAnswerPage";
	}
	
	/**
	 * 前往判断题页面
	 * @return
	 * @throws Exception
	 */
	public String toTrueOrFalsePage() throws Exception{
		return "toTrueOrFalsePage";
	}
	
	/**
	 * 前往单选题页面
	 * @return
	 * @throws Exception
	 */
	public String toSingleChoisePage() throws Exception{
		return "toSingleChoisePage";
	}
	
	/**
	 * 前往多选题页面
	 * @return
	 * @throws Exception
	 */
	public String toMultipleChoisePage() throws Exception{
		return "toMultipleChoisePage";
	}
	
	/**
	 * 前往填空题页面
	 * @return
	 * @throws Exception
	 */
	public String toFillingPage() throws Exception{
		return "toFillingPage";
	}
	
	/**
	 * @Description (TODO获取图片id)
	 * @return
	 * @throws Exception
	 */
	public String getImageList() throws Exception {
		questionVo = questionService.getImgs(questionVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("que", questionVo.getQueImgs());
		rtn.add("queStr", questionVo.getQueImgsStr());
		rtn.add("opt", questionVo.getOptImgs());
		rtn.add("optStr", questionVo.getOptImgsStr());
		questionMap = rtn.getReturnMap();
		return "json";
	}
	
	
	
//	/**
//	 * 获取所有的知识点（用于下拉框初始化）
//	 */
//	public String getAllPoint() throws Exception{
//		AjaxReturnInfo rtn=null;
//		List<SelectVo> list=questionService.getAllPoint();
//		rtn=AjaxReturnInfo.success("");
//		rtn.add("options", list);
//		questionMap=rtn.getReturnMap();
// 		return "json";
//	}
	
	public HashMap<String, Object> getQuestionMap() {
		return questionMap;
	}

	public void setQuestionMap(HashMap<String, Object> questionMap) {
		this.questionMap = questionMap;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public QuestionVo getQuestionVo() {
		return questionVo;
	}

	public void setQuestionVo(QuestionVo questionVo) {
		this.questionVo = questionVo;
	}

	public String getPointSkill() {
		return pointSkill;
	}

	public void setPointSkill(String pointSkill) {
		this.pointSkill = pointSkill;
	}

	public String getPointNames() {
		return pointNames;
	}

	public void setPointNames(String pointNames) {
		this.pointNames = pointNames;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}
	
	public String getPointIds() {
		return pointIds;
	}

	public void setPointIds(String pointIds) {
		this.pointIds = pointIds;
	}

	@Override
	public Object getModel() {
		if (questionVo == null) {
			questionVo = new QuestionVo();
		}
		return questionVo;
	}

}
