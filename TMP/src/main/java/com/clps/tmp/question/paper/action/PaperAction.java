package com.clps.tmp.question.paper.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.velocity.VelocityContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DocUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ImgBase64Util;
import com.clps.tmp.common.util.StringUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.question.paper.service.PaperService;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.question.paper.vo.SectionQuestionVo;
import com.clps.tmp.question.paper.vo.SectionVo;
import com.clps.tmp.question.question.service.QuestionService;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/question")
@Controller
@Scope("prototype")
@Action("paper")
@Results({@Result(name = "toPaperManagePage", location="paper/paperManage.jsp"),
		@Result(name = "toAddNewPaperPage", location="paper/addPaper.jsp"),
		@Result(name = "toCopyNewPaperPage", location="paper/copyPaper.jsp"),
		@Result(name = "toNextStep", location="paper/addPaper2.jsp"),
		@Result(name = "toSelectQuestionPage", location="paper/selectQuestion.jsp"),
		@Result(name = "toPaperEditPage", location="paper/editPaper.jsp"),
		@Result(name = "toPaperDetailPage", location="paper/detailPaper.jsp"),
		@Result(name = "toQuestionIframePage", location="paper/questionIframe.jsp"),
		@Result(name = "json", type = "json", params = { "root", "paperMap" })})
public class PaperAction extends BaseAction implements ModelDriven{

	private PaperVo paperVo;
	
	private SectionVo sectionVo;
	
	private Map<String,Object> paperMap;
	
	@Resource
	private PaperService paperService;
	@Resource
	private QuestionService questionService;
	
	//试卷的部分数
	private long section_count;
	
	//部分对应的题目列表
	List<SectionQuestionVo> questionList;
	
	//文字形式的属性
	private String paper_property;
	
	//文字形式的用途
	private String paper_useFlag;
	
	//doc中的图片链接
	private StringBuilder img_link;
	//doc中的图片数据
	private StringBuilder img_data;
	//计数器
	private int num;

	/**
	 * 前往试卷管理页面
	 * @return
	 * @throws Exception
	 */
	public String toPaperManagePage() throws Exception{
		return "toPaperManagePage";
	}
	
	/**
	 * 获取所有试卷信息
	 * @return
	 * @throws Exception
	 */
	public String findPaperData() throws Exception{
		BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<PaperVo> bootStrapPageVo= paperService.findPaperList(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    paperMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 前往部分Iframe页面
	 * @return
	 * @throws Exception
	 */
	public String toQuestionIframe() throws Exception{
		return "toQuestionIframePage";
	}
	
	/**
	 * iframe页面初始化
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findQuestionBySection() throws Exception{
		//order
		String order = this.httpRequest.getParameter("order");
		String paper_id = this.httpRequest.getParameter("paper_id");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("paper_id", paper_id);
		paraMap.put("order",order);
		Map<String,Object> map = paperService.findSection(paraMap);
		sectionVo = (SectionVo) map.get("sectionVo");
		questionList = (List<SectionQuestionVo>) map.get("questionList");
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        rtn = AjaxReturnInfo.success("true");
        rtn.add("sectionVo", sectionVo);
        rtn.add("questionList", questionList);
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 前往选择题目页面
	 * @return
	 * @throws Exception
	 */
	public String toSelectQuestionPage() throws Exception{
		return "toSelectQuestionPage";
	}
	
	/**
	 * 	前往试卷添加页面
	 * @return
	 * @throws Exception
	 */
	public String toAddNewPaperPage() throws Exception{
		return "toAddNewPaperPage";
	}
	
	/**
	 * 前往拷贝试卷添加页面
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @return
	 * @throws Exception
	 */
	public String toCopyNewPaperPage() throws Exception{
		return "toCopyNewPaperPage";
	}
	
	/**
	 * 创建试卷（未完成）
	 * @return
	 * @throws Exception
	 */
	public String addPaper() throws Exception{
		String property = paperVo.getProperty();
		paperVo.setNo(StringUtil.getNo());
		paperVo.setTotal_item(0);
		paperVo.setTotal_time(-1);
		paperVo.setFinish_flag("0");
		paperVo.setEnable("T");
		paperVo.setCreate_time(DateTimeUtil.nowToDatabase());
		paperVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		AjaxReturnInfo rtn = null;
		if(paperVo.getName()==null || paperVo.getName()==""){
			rtn = AjaxReturnInfo.success("1111");
		}else{
			int count = paperService.insertPaper(paperVo);
			paperVo.setId(count);
			List<PaperVo> paper = paperService.findPaperProperty(paperVo);
			
			// 封装返回数据
			if (count > 0) {
				rtn = AjaxReturnInfo.success("0000");
				rtn.add("paper_id", count);
				rtn.add("paper_name", paperVo.getName());
				//文字形式的属性
				rtn.add("paper_property", paper.get(0).getProperty());
				//数字形式的属性
				rtn.add("paperProperty", property);
				//文字形式的用途标识
				rtn.add("paper_useFlag", paper.get(0).getUse_flag());
				//数字形式的用途表示
				rtn.add("paperUseFlag", paperVo.getUse_flag());
			} else {
				rtn = AjaxReturnInfo.success("1111");
			}
		}
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 拷贝试卷
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @return
	 * @throws Exception
	 */
	public String copyPaper() throws Exception{
		paperVo.setNo(StringUtil.getNo());
		paperVo.setTotal_item(0);
		paperVo.setTotal_time(-1);
		paperVo.setFinish_flag("0");
		paperVo.setEnable("T");
		paperVo.setCreate_time(DateTimeUtil.nowToDatabase());
		paperVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		boolean re = paperService.copyPaper(paperVo.getId()+"",paperVo,(String)(this.getSession().get(SystemConstant.USERNAME)));
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (re) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 创建试卷的下一步
	 * @return
	 * @throws Exception
	 */
	public String nextStep() throws Exception{
		String paper_id = this.httpRequest.getParameter("paper_id");
		//文字形式的属性
		paper_property = this.httpRequest.getParameter("paper_property");
		//数字形式的属性
		String paperProperty = this.httpRequest.getParameter("paperProperty");
		paper_useFlag = this.httpRequest.getParameter("paper_useFlag");
		
		paperVo.setId(Integer.parseInt(paper_id));
		paperMap = paperService.findPaperById(paperVo);
		paperVo = (PaperVo) paperMap.get("paperVo");
		paperVo.setProperty(paperProperty);
		return "toNextStep";
	}
	
	/**
	 * 完成试卷创建
	 * @return
	 * @throws Exception
	 */
	public String finishCreatePaper() throws Exception{
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		int count = this.paperService.updatePaperStatus(paperVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (count > 0) {
			rtn = AjaxReturnInfo.success("0000");
		} else {
			rtn = AjaxReturnInfo.success("1111");
		}
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 验证试卷是否创建完成
	 * @return
	 * @throws Exception
	 */
	public String validatePaperStatus() throws Exception{
		String paper_id = this.httpRequest.getParameter("paper_id");
		paperVo.setId(Integer.parseInt(paper_id));
		int count = this.paperService.validatePaperStatus(paperVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (count == 0) {//未创建完成
			rtn = AjaxReturnInfo.success("0000");
		} else {
			rtn = AjaxReturnInfo.success("1111");
		}
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 获取题库中的题目
	 * @return
	 * @throws Exception
	 */
	public String findQuestionData() throws Exception{
		BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    String paper_id = (String) dataMap.get("paper_id");
	    paperVo.setId(Integer.parseInt(paper_id));
	    //获取该试卷含有的题目信息
	    String Ids = this.paperService.findPaperQuestion(paperVo);
	    dataMap.put("Ids", Ids);
	    BtTableVo<QuestionVo> bootStrapPageVo= paperService.findQuestionList(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    paperMap=rtn.getReturnMap();
	    return "json";
	}
	
	/**
	 * 创建section
	 * @return
	 * @throws Exception
	 */
	public String createSection() throws Exception{
		String order = this.httpRequest.getParameter("order");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("order", order);
		paraMap.put("paper_id", paperVo.getId());
		Map<String,Object> map =  this.paperService.findSection(paraMap);
		int paper_id = -1;
		if(map.get("sectionVo")==null){//找不到该section，在这张试卷中
			sectionVo = new SectionVo();
			sectionVo.setName("部分名称");
			sectionVo.setCreate_time(DateTimeUtil.nowToDatabase());
			sectionVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
			sectionVo.setUpdate_time(DateTimeUtil.nowToDatabase());
			sectionVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
			Map<String,Object> paper_map =  this.paperService.findPaperById(paperVo);
			paperVo = (PaperVo) paper_map.get("paperVo");
			paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
			paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
			paper_id = paperService.insertSection(sectionVo,paperVo,paraMap);
		}
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (paper_id > 0) {
            rtn = AjaxReturnInfo.success("true");
            rtn.add("paper_id", paper_id);
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 保存题目信息
	 * @return
	 * @throws Exception
	 */
	public String saveQuestion() throws Exception{
		String order = this.httpRequest.getParameter("order");
		String paper_id = this.httpRequest.getParameter("paper_id");
		String questionIds = this.httpRequest.getParameter("questionIds");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paper_id", paper_id);
		map.put("order", order);
		map.put("questionIds", questionIds);
		int count = this.paperService.saveQuestion(map);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (count > 0) {
            rtn = AjaxReturnInfo.success("true");
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除题目信息
	 * @return
	 * @throws Exception
	 */
	public String deleteQuestion() throws Exception{
		String order = this.httpRequest.getParameter("order");
		String paper_id = this.httpRequest.getParameter("paper_id");
		String question_id = this.httpRequest.getParameter("question_id");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paper_id", paper_id);
		map.put("order", order);
		map.put("question_id", question_id);
		int count = this.paperService.deleteQuestion(map);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (count > 0) {
            rtn = AjaxReturnInfo.success("true");
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        paperMap = rtn.getReturnMap();
		return "json";
	}
	/**
	 * 保存总时间
	 * 2016年4月26日 Seven
	 */
	public String saveTotalInfoForPaper() throws Exception{
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		paperService.saveTotalInfoForPaper(paperVo);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	public String savePaperInfo() throws Exception{
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		paperService.savePaperInfo(paperVo);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 保存该部分
	 * @return
	 * @throws Exception
	 */
	public String saveSection() throws Exception{
		String questionIds = this.httpRequest.getParameter("questionIds");
		String questionPoints = this.httpRequest.getParameter("questionPoints");
		String order = this.httpRequest.getParameter("order");
		sectionVo.setCreate_time(DateTimeUtil.nowToDatabase());
		sectionVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		sectionVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		sectionVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("questionIds", questionIds);
		paraMap.put("questionPoints", questionPoints);
		paraMap.put("order", order);
		paperVo.setNo(StringUtil.getNo());
		if(paperVo.getFinish_flag()==null || paperVo.getFinish_flag()==""){
			paperVo.setFinish_flag("0");
		}
		if(paperVo.getTotal_time()<=0){
			paperVo.setTotal_time(-1);
		}
		paperVo.setEnable("T");
		paperVo.setCreate_time(DateTimeUtil.nowToDatabase());
		paperVo.setCreate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		int paper_id = paperService.insertSection(sectionVo,paperVo,paraMap);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        if (paper_id > 0) {
            rtn = AjaxReturnInfo.success("true");
            rtn.add("paper_id", paper_id);
        } else {
            rtn = AjaxReturnInfo.success("false");
        }
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除该部分
	 * @return
	 * @throws Exception
	 */
	public String deleteSection() throws Exception{
		String paper_id = this.httpRequest.getParameter("paper_id");
		String order = this.httpRequest.getParameter("order");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paper_id", paper_id);
		map.put("order", Integer.parseInt(order));
		int count = this.paperService.deleteSection(map);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (count > 0) {
			rtn = AjaxReturnInfo.success("0000");
		} else {
			rtn = AjaxReturnInfo.success("1111");
		}
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 删除试卷
	 * @return
	 * @throws Exception
	 */
	public String deletePaper() throws Exception{
		int count = this.paperService.deleteSection(paperVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (count > 0) {
			rtn = AjaxReturnInfo.success("0000");
		} else {
			rtn = AjaxReturnInfo.success("1111");
		}
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 前往试卷编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toPaperEditPage() throws Exception{
		paperMap = paperService.findPaperById(paperVo);
		paperVo = (PaperVo) paperMap.get("paperVo");
		section_count = (Long) paperMap.get("section_count");
		return "toPaperEditPage";
	}
	
	/**
	 * 获取试卷信息（考试时间，试卷题目数，试卷总分数）
	 * @return
	 * @throws Exception
	 */
	public String selectPaperInfo() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PaperVo pv = paperVo;
		//获取试卷信息
		map = this.paperService.findPaperById(pv);
		paperVo = (PaperVo) map.get("paperVo");
		//查询考试时间、试卷题数、试卷总分
		map.put("paper_id", paperVo.getId());
		map = this.paperService.findPaperInfo(map);
		// 封装返回数据
        AjaxReturnInfo rtn = null;
        rtn = AjaxReturnInfo.success("true");
        rtn.add("total_time", map.get("total_time"));
        rtn.add("total_point", map.get("total_point"));
        rtn.add("total_item", map.get("total_item"));
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 前往试卷详情页面
	 * @return
	 * @throws Exception
	 */
	public String toPaperDetailPage() throws Exception{
		paperMap = paperService.findPaperById(paperVo);
		paperVo = (PaperVo) paperMap.get("paperVo");
		return "toPaperDetailPage";
	}
	
	/**
	 * 试卷详情
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String detailPaper() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paper_id", paperVo.getId());
		map = this.paperService.findAllPaperInfo(map);
		AjaxReturnInfo rtn = null;
		// 封装返回数据
		rtn = AjaxReturnInfo.success("true");
        rtn.add("total_time", map.get("total_time"));
        rtn.add("total_point", map.get("total_point"));
        rtn.add("total_item", map.get("total_item"));
        rtn.add("questionList", map.get("questionList"));
        int current_order = 1;
        float section_point = 0;
        //表示第几个section(角标从一开始)
        int i = 1;
        float[] section_arr= new float[15];
        List<Map<String,Object>> questionList = (List<Map<String, Object>>) map.get("questionList");
        for(Map<String,Object> questionMap : questionList){
        	if(questionMap.get("section_order")==null)break;
        	
        	if(current_order == (Integer)questionMap.get("section_order")){
        		section_point = section_point + (Float)questionMap.get("question_score");
        	}else{
        		section_arr[i] = section_point;
        		section_point = (Float)questionMap.get("question_score");
        		i++;
        		current_order = (Integer)questionMap.get("section_order");
        	}
        }
        //最后一个section的分数
        section_arr[i] = section_point;
        rtn.add("section_arr", section_arr);
        paperMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 传入试卷ID，下载试卷Doc文档
	 * 2016年6月23日 Seven
	 */
	@SuppressWarnings("unchecked")
	public void downLoadPaperDoc() throws Exception{
		num = 0; 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paper_id", paperVo.getId());
		map = this.paperService.findAllPaperInfo(map);
		String paper_name=(String) map.get("paper_name");
		String regEx="[`~!@#$\"%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
        Pattern p = Pattern.compile(regEx);     
        Matcher m = p.matcher(paper_name);     
		paper_name=m.replaceAll("").replaceAll("\\\\","").trim();
		int paper_total_time=(Integer) map.get("total_time");
		// 封装返回数据
		VelocityContext velocityContext = new VelocityContext();
		//试卷信息
		velocityContext.put("paper_name", paper_name);
		velocityContext.put("paper_total_time", (paper_total_time==-1)?"不限":paper_total_time);
		velocityContext.put("paper_total_item", map.get("total_item"));
		velocityContext.put("paper_total_score", map.get("total_point"));
		StringBuilder tem = new StringBuilder();
		tem.append("<w:p w:rsidR=\"00DF3B23\" w:rsidRDefault=\"00C440D8\"><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/></w:rPr><w:t>paper_instruction_context</w:t></w:r></w:p>");
		String paper_instruction = returnCharactor(tem,"paper_instruction_context",(String)map.get("paper_instruction"));
		velocityContext.put("paper_instruction", paper_instruction);
		List<Map<String,Object>> questionList = (List<Map<String, Object>>) map.get("questionList");
		//section的总题数与分值
		int i = 1;
		int current_order = 1;
		float score = 0;//部分分数
		int item = 0;//部分题树
		float[] section_score= new float[15];
		int[] section_item= new int[15];
        for(Map<String,Object> questionMap : questionList){
        	if(questionMap.get("section_order")==null)break;
        	if(current_order == (Integer)questionMap.get("section_order")){
        		score = score + (Float)questionMap.get("question_score");
        		item++;
        	}else{
        		section_score[i] = score;
        		section_item[i] = item;
        		score = (Float)questionMap.get("question_score");
        		item=1;
        		i++;
        		current_order = (Integer)questionMap.get("section_order");
        	}
        }
        section_score[i] = score;
        section_item[i] = item;
		//生成试卷DOC文档内容
        String docContent=generatePaperDocCnt(questionList,section_score,section_item);
        velocityContext.put("paper_content", docContent);
        //存入图片内容
        velocityContext.put("img_link", img_link==null?"":img_link.toString());
        velocityContext.put("img_data", img_data==null?"":img_data.toString());
        //TODO 调用DocUtil生成DOC
        String fileLoc=DocUtil.createDoc("template_PaperDoc.vm", velocityContext, paper_name);
        //TODO 下载DOC文档
        DownloadFileUtil downLoad=new DownloadFileUtil();
        File file = new File(fileLoc);
        if (file.exists()) {
			log.info("文件下载中...");
			downLoad.downLoad(paper_name+".doc", fileLoc, this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
	}
	/**
	 * 生成试卷DOC文档内容
	 * 2016年6月24日 Seven
	 * @throws Exception 
	 */
	private String generatePaperDocCnt(List<Map<String, Object>> questionList,float[] section_score,int[] section_item) throws Exception {
		StringBuilder paperContent=new StringBuilder();
		String[] section_order_name={"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五"};
		//section的总题数与分值
		int current_order = 0;
		for(Map<String,Object> questionMap : questionList){
        	if(questionMap.get("section_order")==null)break;
        	int section_order=(Integer)questionMap.get("section_order");
        	if(current_order == section_order){
        		//插入题目
        		insertQustionToDoc(paperContent,questionMap);
        	}else{//新部分
        		current_order = section_order;
        		//空行
        		paperContent.append("<w:p w:rsidR=\"00005D56\" w:rsidRDefault=\"00005D56\" w:rsidP=\"00974846\"> <w:pPr> <w:jc w:val=\"left\"/> <w:rPr> <w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/> </w:rPr> </w:pPr> </w:p>");
        		//插入部分
        		String section_name=(String) questionMap.get("section_name");        		
        		paperContent.append("<w:p w:rsidR=\"00B241E7\" w:rsidRPr=\"003F7785\" w:rsidRDefault=\"005F67BC\" w:rsidP=\"00944FA1\"> <w:pPr> <w:jc w:val=\"left\"/> <w:rPr> <w:b/> <w:sz w:val=\"24\"/> <w:szCs w:val=\"24\"/> </w:rPr> </w:pPr> <w:r w:rsidRPr=\"003F7785\"> <w:rPr> <w:rFonts w:hint=\"eastAsia\"/> <w:b/> <w:sz w:val=\"24\"/> <w:szCs w:val=\"24\"/> </w:rPr> ")
        			.append("<w:t><![CDATA[")
        			.append(section_order_name[current_order-1])
        			.append("]]>、<![CDATA[")
        			.append(section_name)
        			.append("]]>(共<![CDATA[")
        			.append(section_item[current_order])
        			.append("]]>题,<![CDATA[")
        			.append(section_score[current_order])
        			.append("]]>分)</w:t> </w:r> </w:p>");
        		//插入题目
        		insertQustionToDoc(paperContent,questionMap); 		
        	}
        }
		return paperContent.toString();
	}
	/**
	 * 在Doc的StringBuilder中插入相应试题
	 * 2016年6月27日 Seven
	 * @throws Exception 
	 */
	private void insertQustionToDoc(StringBuilder paperContent, Map<String, Object> questionMap) throws Exception {
		String question_type=(String) questionMap.get("question_type");
		if(question_type==null) return;//无试题信息
		int question_order=(Integer) questionMap.get("question_order");
		float question_score=(float) questionMap.get("question_score");
		String question_content=(String) questionMap.get("content");
		//获取图片id
		QuestionVo questionVo = new QuestionVo();
		String question_id = questionMap.get("question_id").toString();
		questionVo.setId(Integer.parseInt(question_id));
		questionVo = questionService.getImgs(questionVo);
		if("1".equals(question_type) || "2".equals(question_type)){//选择题
 			question_content=new StringBuilder().append((question_order+1)).append("．(").append(question_score).append("分)").append(question_content).toString();
			StringBuilder sb = new StringBuilder();
			sb.append("<w:p w:rsidR=\"00AC01DF\" w:rsidRDefault=\"005F67BC\" w:rsidP=\"00944FA1\">")
					.append("<w:pPr>spaces_context")
					.append("<w:jc w:val=\"left\"/>")
					.append("<w:rPr>")
					.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/>")
					.append("</w:rPr>")
					.append("</w:pPr>")
					.append("<w:r w:rsidR=\"00B81044\">")
					.append("<w:rPr>")
					.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:hint=\"eastAsia\"/>")
					.append("</w:rPr>")
					.append("<w:t>question_content</w:t>")
					.append("</w:r>")
					.append("</w:p>");
			
			paperContent.append(this.returnCharactor(sb, "question_content", question_content));
			//添加试题图片
			paperContent.append(getImgContext(questionVo.getQueImgs()));
			//选项
			String[] option_order_name={"A","B","C","D","E","F"};
			for(int i=1;i<=6;i++){
				if(questionMap.get("opt"+i)!=null){
					String option_content=(String) questionMap.get("opt"+i);
					option_content = new StringBuilder().append(option_order_name[i-1]).append(".").append(option_content).toString();
					StringBuilder sb1 = new StringBuilder();
					sb1.append("<w:p w:rsidR=\"00AC01DF\" w:rsidRPr=\"00AC01DF\" w:rsidRDefault=\"008D1F27\" w:rsidP=\"00AC01DF\">")
						.append("<w:pPr>")
						.append("spaces_context")
						.append( "<w:jc w:val=\"left\"/>")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/>")
						.append("</w:rPr>")
						.append("</w:pPr>")
						.append("<w:r>")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/>")
						.append("</w:rPr>")
						.append("<w:t>option_content</w:t>")
						.append("</w:r>")
						.append("</w:p>");
					paperContent.append(this.returnCharactor(sb1, "option_content", option_content));
					//添加选项图片
					paperContent.append(getImgContext(questionVo.getOptImgs(),i));
				}
			}
		}
		if("3".equals(question_type)){//判断题
			question_content = new StringBuilder().append((question_order+1)).append(".(").append(question_score).append("分)").append(question_content).toString();
			StringBuilder sb = new StringBuilder();
			sb.append("<w:p w:rsidR=\"00A47350\" w:rsidRDefault=\"00A47350\" w:rsidP=\"00A47350\">")
				.append("<w:pPr>spaces_context")
				.append("<w:jc w:val=\"left\"/>")
				.append("<w:rPr>")
				.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/>")
				.append("</w:rPr>")
				.append("</w:pPr>")
				.append("<w:r>")
				.append("<w:rPr>")
				.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:hint=\"eastAsia\"/>")
				.append("</w:rPr>")
				.append("<w:t>question_content</w:t>")
				.append("</w:r>")
				.append("</w:p>");
			paperContent.append(this.returnCharactor(sb, "question_content", question_content));
			//添加试题图片
			paperContent.append(getImgContext(questionVo.getQueImgs()));
		}
		if("4".equals(question_type)){//填空题
			question_content = new StringBuilder().append((question_order+1)).append(".(").append(question_score).append("分)").append(question_content).toString();
			StringBuilder sb = new StringBuilder();
			sb.append("<w:p w:rsidR=\"005D4D7D\" w:rsidRPr=\"009D3D2D\" w:rsidRDefault=\"009D3D2D\" w:rsidP=\"008D1F27\">")
				.append("<w:pPr>spaces_context")
				.append("<w:jc w:val=\"left\"/>")
				.append("<w:rPr>")
				.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\"/>")
				.append("<w:kern w:val=\"0\"/>")
				.append("<w:szCs w:val=\"21\"/>")
				.append("</w:rPr>")
				.append("</w:pPr>")
				.append("<w:r w:rsidR=\"00FB7D72\" w:rsidRPr=\"009D3D2D\">")
				.append("<w:rPr>")
				.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\" w:hint=\"eastAsia\"/>")
				.append("<w:kern w:val=\"0\"/>")
				.append("<w:szCs w:val=\"21\"/>")
				.append("</w:rPr>")
				.append("<w:t>question_content</w:t>")
				.append("</w:r>")
				.append("</w:p>");
			paperContent.append(this.returnCharactor(sb, "question_content", question_content));
			//添加试题图片
			paperContent.append(getImgContext(questionVo.getQueImgs()));
			//空
			String[] blank_order_name={"空一","空二","空三","空四","空五","空六","空七","空八","空九","空十"};
			String [] answer=((String)questionMap.get("answer")).split("#\\$#");
			int blank_length=answer.length;
			for(int i=0;i<blank_length;i++){
				paperContent.append("<w:p w:rsidR=\"005D4D7D\" w:rsidRDefault=\"009D3D2D\" w:rsidP=\"009D3D2D\">")
						.append("<w:pPr>")
						.append("spaces_context")
						.append("<w:jc w:val=\"left\"/>")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\"/>")
						.append("<w:kern w:val=\"0\"/>")
						.append("<w:szCs w:val=\"21\"/>")
						.append("</w:rPr>")
						.append("</w:pPr>")
						.append("<w:r w:rsidRPr=\"009D3D2D\">")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\" w:hint=\"eastAsia\"/>")
						.append("<w:kern w:val=\"0\"/>")
						.append("<w:szCs w:val=\"21\"/>")
						.append("</w:rPr>")
						.append("<w:t>")
						.append(blank_order_name[i])
						.append(":</w:t>")
						.append("</w:r>")
						.append("<w:r w:rsidRPr=\"009D3D2D\">")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\" w:hint=\"eastAsia\"/>")
						.append("<w:kern w:val=\"0\"/>")
						.append("<w:szCs w:val=\"21\"/>")
						.append("<w:u w:val=\"single\"/>")
						.append("</w:rPr>")
						.append("<w:t xml:space=\"preserve\"> </w:t>")
						.append("</w:r>")
						.append("<w:r w:rsidRPr=\"009D3D2D\">")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\"/>")
						.append("<w:kern w:val=\"0\"/>")
						.append("<w:szCs w:val=\"21\"/>")
						.append("<w:u w:val=\"single\"/>")
						.append("</w:rPr>")
						.append("<w:t xml:space=\"preserve\">                   </w:t>")
						.append("</w:r>")
						.append("<w:bookmarkStart w:id=\"0\" w:name=\"_GoBack\"/>")
						.append("<w:bookmarkEnd w:id=\"0\"/>")
						.append("<w:r w:rsidRPr=\"009D3D2D\">")
						.append("<w:rPr>")
						.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:cs=\"宋体\" w:hint=\"eastAsia\"/>")
						.append("<w:kern w:val=\"0\"/>")
						.append("<w:szCs w:val=\"21\"/>")
						.append("</w:rPr>")
						.append("<w:t xml:space=\"preserve\"> </w:t>")
						.append("</w:r>")
						.append("</w:p>\n");
			}
		}
		if("5".equals(question_type) || "6".equals(question_type) || "7".equals(question_type)){//简答题
			question_content = new StringBuilder().append(question_order+1).append(".(").append(question_score).append("分)").append(question_content).toString();
			StringBuilder sb = new StringBuilder();
			sb.append("<w:p w:rsidR=\"00AC01DF\" w:rsidRDefault=\"00974846\" w:rsidP=\"00974846\">")
				.append("<w:pPr>spaces_context")
				.append("<w:jc w:val=\"left\"/>")
				.append("<w:rPr>")
				.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/>")
				.append("</w:rPr>")
				.append("</w:pPr>")
				.append("<w:r>")
				.append("<w:rPr>")
				.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\" w:hint=\"eastAsia\"/>")
				.append("</w:rPr>")
				.append("<w:t>question_content</w:t>")
				.append("</w:r>")
				.append("</w:p>");
			paperContent.append(this.returnCharactor(sb, "question_content", question_content));
			//添加试题图片
			paperContent.append(getImgContext(questionVo.getQueImgs()));
			//空N行
			for(int i=0;i<8;i++){
				paperContent.append("<w:p w:rsidR=\"00005D56\" w:rsidRDefault=\"00005D56\" w:rsidP=\"00974846\"> <w:pPr> <w:jc w:val=\"left\"/> <w:rPr> <w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/> </w:rPr> </w:pPr> </w:p>");
			}
		}
		
	}
	
	/**
	 * @Description (TODO图片信息的生成)
	 * @param imgUUID
	 * @return
	 */
	private String getImgContext(List<String> imgUUID,int num){
		if(imgUUID==null||imgUUID.size()<=0){
			return "";
		}
		//选项图片使用
		List<String> imgUUIDs = new ArrayList<String>();
		try{
			imgUUIDs.add(imgUUID.get((num-1)));
		}catch(Exception e){
			return "";
		}
		return getImgContext(imgUUIDs);
	}
	private String getImgContext(List<String> imgUUID){
		if(img_link==null){
			img_link = new StringBuilder();
		}
		if(img_data==null){
			img_data = new StringBuilder();
		}
		if(imgUUID==null||imgUUID.size()<=0){
			return "";
		}
		StringBuilder re = new StringBuilder();
		re.append("<w:p w:rsidR=\"00AC01DF\" w:rsidRDefault=\"00AC01DF\" w:rsidP=\"008D1F27\"> <w:pPr> <w:ind w:leftChars=\"150\" w:left=\"315\"/> <w:jc w:val=\"left\"/> <w:rPr> <w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/> </w:rPr> </w:pPr>\n");
		for(String uuid : imgUUID){
			num++;
			String id = "imgid"+num;
			String name = "image"+num;
			//链接
			img_link.append("<Relationship Id=\"").append(id).append("\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/image\" Target=\"media/").append(name).append(".jpg\"/>\n");
			//base64数据
			String baseDir = new UploadFileUtil().getBaseDir() + File.separator + "Question/images" + File.separator;
			File f = new File(baseDir, uuid + ".jpg");
			img_data.append("<pkg:part pkg:name=\"/word/media/").append(name).append(".jpg\" pkg:contentType=\"image/jpg\" pkg:compression=\"store\"><pkg:binaryData>").append(ImgBase64Util.GetImageStr(f)).append("</pkg:binaryData></pkg:part>\n");
			//题目内容
			re.append("<w:r>")
			.append("<w:rPr>")
			.append("<w:rFonts w:ascii=\"宋体\" w:hAnsi=\"宋体\"/>")
			.append("<w:noProof/>")
			.append("</w:rPr>")
			.append("<w:drawing>")
			.append("<wp:inline distT=\"0\" distB=\"0\" distL=\"0\" distR=\"0\">")
			.append("<wp:extent cx=\"3200000\" cy=\"1800000\"/>")
			.append("<wp:effectExtent l=\"0\" t=\"0\" r=\"9525\" b=\"9525\"/>")
			.append("<wp:docPr id=\"1\" name=\"图片 1\"/>")
			.append("<wp:cNvGraphicFramePr>")
			.append("<a:graphicFrameLocks noChangeAspect=\"1\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\"/>")
			.append("</wp:cNvGraphicFramePr>")
			.append("<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">")
			.append("<a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">")
			.append("<pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">")
			.append("<pic:nvPicPr>")
			.append("<pic:cNvPr id=\"1\" name=\"1.jpg\"/>")
			.append("<pic:cNvPicPr/>")
			.append("</pic:nvPicPr>")
			.append("<pic:blipFill>")
			.append("<a:blip r:embed=\"")
			.append(id)
			.append("\" cstate=\"print\">")
			.append("<a:extLst>")
			.append("<a:ext uri=\"{28A0092B-C50C-407E-A947-70E740481C1C}\">")
			.append("<a14:useLocalDpi val=\"0\" xmlns:a14=\"http://schemas.microsoft.com/office/drawing/2010/main\"/>")
			.append("</a:ext>")
			.append("</a:extLst>")
			.append("</a:blip>")
			.append("<a:stretch>")
			.append("<a:fillRect/>")
			.append("</a:stretch>")
			.append("</pic:blipFill>")
			.append("<pic:spPr>")
			.append("<a:xfrm>")
			.append("<a:off x=\"0\" y=\"0\"/>")
			.append("<a:ext cx=\"1057275\" cy=\"942975\"/>")
			.append("</a:xfrm>")
			.append("<a:prstGeom prst=\"rect\">")
			.append("<a:avLst/>")
			.append("</a:prstGeom>")
			.append("</pic:spPr>")
			.append("</pic:pic>")
			.append("</a:graphicData>")
			.append("</a:graphic>")
			.append("</wp:inline>")
			.append("</w:drawing>")
			.append("</w:r>");	
		}	
		re.append("</w:p>\n");
		return re.toString();
	}
	
	//处理多行
	private String returnCharactor(StringBuilder sb,String key,String content){
		//<![CDATA[]]>
		StringBuilder re = new StringBuilder();
		String[] contents = content.split("\n");
		for(String tem : contents){
			tem = tem.replaceAll("&quot;", "\"")
				.replaceAll("&apos;", "\'")
				.replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">")
				.replaceAll("&amp;", "&");
			re.append(sb.toString()
						//换行信息
						.replaceAll(java.util.regex.Matcher.quoteReplacement(key), java.util.regex.Matcher.quoteReplacement(new StringBuilder().append("<![CDATA[").append(tem).append("]]>\n").toString()))
						//开头空格
						.replaceAll("spaces_context", returnSpaces(tem)));
		}
		return re.toString();
	}
	//处理空格(开头的)
	private String returnSpaces(String str){
		int num = 0; 
		int firstLineChars = 100;
		int firstLine = 210;
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(c=='\t'){
				num=num+2;
			}else if(c==' '){
				num++;
			}else{
				break;
			}
		}
		if(num==0){
			return "";
		}else{
			return new StringBuilder().append("<w:ind w:firstLineChars=\"")
					.append(firstLineChars*num)
					.append("\" w:firstLine=\"")
					.append(firstLine*num)
					.append("\"/>").toString();
		}
	}
	/**
	 * 设置试卷的启用禁用
	 * 2016年5月25日 Seven
	 */
	public String changeEnableState() throws Exception{
		paperVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		paperVo.setUpdate_person((String)(this.getSession().get(SystemConstant.USERNAME)));
		int ct=paperService.changeEnableState(paperVo);
		AjaxReturnInfo rtn =null;
		if(ct==1){
			rtn = AjaxReturnInfo.success("");
		}else{
			rtn = AjaxReturnInfo.failed("-1");
		}
		paperMap = rtn.getReturnMap();
		return "json";
	}
	
	public SectionVo getSectionVo() {
		return sectionVo;
	}

	public void setSectionVo(SectionVo sectionVo) {
		this.sectionVo = sectionVo;
	}

	public PaperVo getPaperVo() {
		return paperVo;
	}

	public void setPaperVo(PaperVo paperVo) {
		this.paperVo = paperVo;
	}

	public Map<String, Object> getPaperMap() {
		return paperMap;
	}

	public void setPaperMap(Map<String, Object> paperMap) {
		this.paperMap = paperMap;
	}

	public PaperService getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}
	
	 public long getSection_count() {
		return section_count;
	}

	public void setSection_count(long section_count) {
		this.section_count = section_count;
	}

	public List<SectionQuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<SectionQuestionVo> questionList) {
		this.questionList = questionList;
	}

	public String getPaper_property() {
		return paper_property;
	}

	public void setPaper_property(String paper_property) {
		this.paper_property = paper_property;
	}

	public String getPaper_useFlag() {
		return paper_useFlag;
	}

	public void setPaper_useFlag(String paper_useFlag) {
		this.paper_useFlag = paper_useFlag;
	}

	@Override
	public Object getModel() {
		if (paperVo == null) {
			paperVo = new PaperVo();
		}
		return paperVo;
	}
	
}
