package com.clps.tmp.tech.plan.action;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.TechPaperService;
import com.clps.tmp.tech.plan.vo.AnswerInfoVo;
import com.clps.tmp.tech.plan.vo.AnswerSheetVo;
import com.clps.tmp.tech.plan.vo.QuestionVo;
import com.clps.tmp.tech.plan.vo.SectionVo;


/**
 * @ClassName PaperUIAction
 * @Description TODO(阅卷页面)
 * @author liuchen
 * @Date 2016年6月28日 下午6:03:55
 * @version 1.0.0
 */
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("paperUIAction")
@Results({
		@Result(name = "toCorrectPaper", location = "plan/personalPlan/paper/correctPaper.jsp"),
		@Result(name = "toReadPaper", location = "plan/personalPlan/paper/readPaper.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class PaperUIAction extends BaseAction {
	
	private static final long serialVersionUID = -2758158126993641176L;
	private HashMap<String, Object> resultMap;// json返回数据map
	private QuestionVo questionVo;
	private AnswerInfoVo answerInfoVo;
	private AnswerSheetVo answerSheetVo;
	private SectionVo sectionVo;
	@Resource
	private TechPaperService techPaperService;
	

	public SectionVo getSectionVo() {
		return sectionVo;
	}
	public void setSectionVo(SectionVo sectionVo) {
		this.sectionVo = sectionVo;
	}
	public QuestionVo getQuestionVo() {
		return questionVo;
	}
	public void setQuestionVo(QuestionVo questionVo) {
		this.questionVo = questionVo;
	}
	public AnswerInfoVo getAnswerInfoVo() {
		return answerInfoVo;
	}
	public void setAnswerInfoVo(AnswerInfoVo answerInfoVo) {
		this.answerInfoVo = answerInfoVo;
	}
	public AnswerSheetVo getAnswerSheetVo() {
		return answerSheetVo;
	}
	public void setAnswerSheetVo(AnswerSheetVo answerSheetVo) {
		this.answerSheetVo = answerSheetVo;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	/**
	 * @Description (TODO跳转批阅试卷的页面)
	 * @return
	 * @throws Exception 
	 */
	public String toCorrectPaper() throws Exception{
		String paperId = answerInfoVo.getPaper_id();
		String userId = answerInfoVo.getUser_id();
		String plan_paper_id = answerInfoVo.getPlan_paper_id();
		//获取概要信息
		answerInfoVo = techPaperService.getAnswerInfoVo(paperId,userId);
		answerInfoVo.setPaper_id(paperId);
		answerInfoVo.setUser_id(userId);
		answerInfoVo.setStart_time(DateTimeUtil.databaseToSystem(answerInfoVo.getStart_time()));
		answerInfoVo.setEnd_time(DateTimeUtil.databaseToSystem(answerInfoVo.getEnd_time()));
		answerInfoVo.setScore(answerInfoVo.getScore()==null?"0.00":answerInfoVo.getScore());
		answerInfoVo.setPlan_paper_id(plan_paper_id);
		return "toCorrectPaper";
	}
	
	/**
	 * @Description (TODO跳转查看已批阅试卷)
	 * @return
	 * @throws Exception
	 */
	public String toReadPaper() throws Exception{
		String paperId = answerInfoVo.getPaper_id();
		String userId = answerInfoVo.getUser_id();
		//获取概要信息
		answerInfoVo = techPaperService.getAnswerInfoVo(paperId,userId);
		answerInfoVo.setPaper_id(paperId);
		answerInfoVo.setUser_id(userId);
		answerInfoVo.setStart_time(DateTimeUtil.databaseToSystem(answerInfoVo.getStart_time()));
		answerInfoVo.setEnd_time(DateTimeUtil.databaseToSystem(answerInfoVo.getEnd_time()));
		answerInfoVo.setScore(answerInfoVo.getScore()==null?"0.00":answerInfoVo.getScore());
		return "toReadPaper";
	}
	
	/**
	 * @Description (TODO获取题块信息)
	 * @return
	 * @throws Exception
	 */
	public String getSectionInfo() throws Exception {
		String paperId = answerInfoVo.getPaper_id();
		String userId = answerInfoVo.getUser_id();
		//获取题块信息
		List<SectionVo> sections = techPaperService.getSectionInfo(paperId, userId);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("data", sections);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取批改进度)
	 * @return
	 * @throws Exception
	 */
	public String getProgress() throws Exception {
		String paperId = answerInfoVo.getPaper_id();
		String userId = answerInfoVo.getUser_id();
		//获取题块信息
		answerInfoVo = techPaperService.getProgress(paperId, userId);
		int count = Integer.parseInt(answerInfoVo.getCount());
		int allCount = Integer.parseInt(answerInfoVo.getCount_all());
		int pro = (count*100)/allCount;
		HashMap<String,String> data = new HashMap<String,String>();
		//已批改条数
		data.put("count", count+"");
		//总条数
		data.put("allCount", allCount+"");
		//进度
		data.put("pro", pro+"");
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("data", data);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO获取答卷内容信息)
	 * @return
	 * @throws Exception
	 */
	public String getAnswerSheet() throws Exception {
		String paperId = answerInfoVo.getPaper_id();
		String userId = answerInfoVo.getUser_id();
		//获取题块信息
		List<AnswerSheetVo> answerSheet = techPaperService.getAnswerSheet(paperId, userId);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("data", answerSheet);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO打分)
	 * @return
	 * @throws Exception
	 */
	public String setScore() throws Exception {
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		int re = techPaperService.setScore(answerSheetVo, teacher);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if(re>0){
			//success
			rtn = AjaxReturnInfo.success("0");
		}else{
			rtn = AjaxReturnInfo.success("1");
		}	
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO打备注)
	 * @return
	 * @throws Exception
	 */
	public String setRemark() throws Exception {
		int re = techPaperService.setRemark(answerSheetVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if(re>0){
			//success
			rtn = AjaxReturnInfo.success("0");
		}else{
			rtn = AjaxReturnInfo.success("1");
		}	
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO批改试卷完成更新批改结果)
	 * @return
	 * @throws Exception
	 */
	public String setCheckPaper() throws Exception {
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		int re = techPaperService.setPaperScore(answerInfoVo, teacher);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if(re>0){
			//success
			rtn = AjaxReturnInfo.success("0");
		}else{
			rtn = AjaxReturnInfo.success("1");
		}	
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO自动阅卷客观题)
	 * @return
	 * @throws Exception
	 */
	public String setAutoCorrectPaper() throws Exception {
		String paperId = answerInfoVo.getPaper_id();
		String userId = answerInfoVo.getUser_id();
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		//获取题块信息
		List<AnswerSheetVo> li = techPaperService.setAutoCorrectPaper(paperId, userId ,teacher);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("data", li);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
}
