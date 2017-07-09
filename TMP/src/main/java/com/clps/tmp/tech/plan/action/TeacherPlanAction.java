package com.clps.tmp.tech.plan.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.velocity.VelocityContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DocUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.question.paper.vo.PaperVo;
import com.clps.tmp.tech.plan.service.TeaStuPlanService;
import com.clps.tmp.tech.plan.vo.EvalVo;
import com.clps.tmp.tech.plan.vo.PlanPaperStuHisVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;
import com.clps.tmp.tech.plan.vo.PlanVo;

@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("teacherPlan")
@Results({ @Result(name = "toPaperManagePage", location = "plan/personalPlan/teacherPlan/paperManage.jsp"),
		@Result(name = "toAddPaper", location = "plan/personalPlan/teacherPlan/addPaper.jsp"),
		@Result(name = "toAddPaper2", location = "plan/personalPlan/teacherPlan/addPaper2.jsp"),
		@Result(name = "toEditPaper", location = "plan/personalPlan/teacherPlan/editPaper.jsp"),
		@Result(name = "toReadPaperManage", location = "plan/personalPlan/teacherPlan/readPaperManage.jsp"),
		@Result(name = "toFeedbackManage", location = "plan/personalPlan/teacherPlan/feedbackManage.jsp"),
		@Result(name = "toOnlineTestPage", location = "../onlineTest/examLogin.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class TeacherPlanAction extends BaseAction {

	private static final long serialVersionUID = -2758158769903641176L;
	private HashMap<String, Object> resultMap;// json返回数据map
	private PlanPaperVo planPaperVo;// 页面数据封装
	private PlanPaperStuHisVo planPaperStuHisVo;
	private PaperVo paperVo;
	@Resource
	private TeaStuPlanService teaStuPlanService;

	public PaperVo getPaperVo() {
		return paperVo;
	}

	public void setPaperVo(PaperVo paperVo) {
		this.paperVo = paperVo;
	}

	public PlanPaperVo getPlanPaperVo() {
		return planPaperVo;
	}

	public void setPlanPaperVo(PlanPaperVo planPaperVo) {
		this.planPaperVo = planPaperVo;
	}

	public PlanPaperStuHisVo getPlanPaperStuHisVo() {
		return planPaperStuHisVo;
	}

	public void setPlanPaperStuHisVo(PlanPaperStuHisVo planPaperStuHisVo) {
		this.planPaperStuHisVo = planPaperStuHisVo;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * @Description (TODO跳转管理界面)
	 * @return
	 */
	public String toPaperManagePage() {
		return "toPaperManagePage";
	}

	/**
	 * @Description (TODO跳转试卷管理界面)
	 * @return
	 */
	public String toAddPaper() {
		return "toAddPaper";
	}

	/**
	 * @Description (TODO添加额外信息)
	 * @return
	 */
	public String toAddPaper2() {
		return "toAddPaper2";
	}

	/**
	 * @Description (TODO编辑试卷)
	 * @return
	 */
	public String toEditPaper() {
		return "toEditPaper";
	}
	
	/**
	 * @Description (TODO阅卷)
	 * @return
	 */
	public String toReadPaper() {
		return "toReadPaperManage";
	}
	
	/**
	 * @Description (TODO跳转学生反馈界面)
	 * @return
	 */
	public String toFeedbackManage() {
		return "toFeedbackManage";
	}
	
	/**
	 * @Description (TODO获取测试&作业列表)
	 * @return
	 * @throws Exception
	 */
	public String selectPaperInfo() throws Exception {
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		dataMap.put("teacher_id", teacher.getId());
		BtTableVo<PlanPaperVo> bootStrapPageVo = teaStuPlanService.selectPaperInfo(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO获取试卷列表)
	 * @return
	 * @throws Exception
	 */
	public String selectPaperList() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<PaperVo> bootStrapPageVo = teaStuPlanService.selectPaperList(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO添加测试&作业)
	 * @return
	 * @throws Exception
	 */
	public String addPaperTest() throws Exception {
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		String url=this.httpRequest.getContextPath()+"/"+planPaperVo.getPlan_id()+"/"+planPaperVo.getPaper_number()+".html";
		planPaperVo.setUrl(url);
		int resData = teaStuPlanService.addPaperTest(planPaperVo, teacher);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO删除作业/测试)
	 * @return
	 * @throws Exception
	 */
	public String deletePaperTest() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		String[] ids = ((String) dataMap.get("id")).split(",");
		int re = teaStuPlanService.deletePaperTest(ids, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (re >= 1 || re >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO获取试卷信息)
	 * @return
	 * @throws Exception
	 */
	public String getPaperInfo() throws Exception {
		paperVo = teaStuPlanService.getPaperInfo(paperVo.getId() + "");
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("data", paperVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO获取测试联系信息)
	 * @return
	 * @throws Exception
	 */
	public String getPaperTestInfo() throws Exception {
		planPaperVo = teaStuPlanService.getPaperTestInfo(planPaperVo.getId());
		planPaperVo.setPaper_start_time(DateTimeUtil.databaseToSystem(planPaperVo.getPaper_start_time()));
		planPaperVo.setPaper_end_time(DateTimeUtil.databaseToSystem(planPaperVo.getPaper_end_time()));
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		rtn.add("data", planPaperVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}

	/**
	 * @Description (TODO更新信息)
	 * @return
	 * @throws Exception
	 */
	public String editPaperTest() throws Exception {
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		int resData = teaStuPlanService.editPaperTest(planPaperVo, teacher);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO布置作业)
	 * @return
	 * @throws Exception
	 */
	public String changePaperTest() throws Exception {
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		int resData = teaStuPlanService.updatePaperStatus(planPaperVo, teacher);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData == 1) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取班级作业列表)
	 * @return
	 * @throws Exception
	 */
	public String selectClassPaperList() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<PlanPaperStuHisVo> bootStrapPageVo = teaStuPlanService.selectClassPaperList(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO学生反馈列表)
	 * @return
	 * @throws Exception
	 */
	public String selectFeedBackList() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		UserVo teacher = (UserVo) this.session.get(SystemConstant.USER);
		dataMap.put("teacher_id",teacher.getId());
		BtTableVo<EvalVo> bootStrapPageVo = teaStuPlanService.selectFeedbackList(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO获取教学计划信息)
	 * @return
	 * @throws Exception
	 */
	public String getPlanInfo() throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("class_id", planPaperVo.getClass_id());
		dataMap.put("course_id", planPaperVo.getCourse_id());
		PlanVo plan = teaStuPlanService.getPlanInfo(dataMap);
		AjaxReturnInfo rtn = null;
		if (plan != null) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		rtn.add("data", plan);
		resultMap = rtn.getReturnMap();
		return "json";
	} 
	
	/**
	 * 下载文件
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @throws Exception
	 */
	public void downScoreData() throws Exception{
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String filePath= baseDir+planPaperVo.getClass_name()+"-ScoreData-"+DateTimeUtil.getNow()+".xls";//临时存储位置
		log.info("下载总分文件：文件临时存放\n"+filePath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plan_paper_id", planPaperVo.getId());
		map.put("paper_id", planPaperVo.getPaper_id());
		map = teaStuPlanService.downScoreData(map);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dateList=(List<Map<String,Object>>)map.get("data");
		int l = (int)map.get("length");
		int l2 = (int)map.get("qlength");
		File file = new File(filePath);
		//生成Excel
		ExcelUtil excelUtil=new ExcelUtil();
		String [] columnName={"student_name","student_no","teacher_name","paper_name","paper_status",
				"long_time","score"};
		columnName = addStrings(columnName,l,null);
		columnName = addStrings2(columnName,l2,null);
		String lang=(String) this.session.get(SystemConstant.LANG);
		HSSFWorkbook wb;
		if(lang==null || "zh_CN".equals(lang)){
			String [] columnAlignName={"学生姓名","学生编号","改卷教师","试卷名称","试卷状态","答题耗时(分钟)","试卷总分"};
			columnAlignName = addStrings(columnAlignName,l,"zh_CN");
			columnAlignName = addStrings2(columnAlignName,l2,"zh_CN");
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "试卷分数");
		}else{
			String [] columnAlignName={"Student Name","Student Number","Teacher Name","Paper Name",
					"Paper Status","Long Time(minute)","Total Score"};	
			columnAlignName = addStrings(columnAlignName,l,"en_US");
			columnAlignName = addStrings2(columnAlignName,l2,"en_US");
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "Score Data");
		}
		OutputStream out = null;
		try{
			out = new FileOutputStream(file);
			wb.write(out);
			out.flush();
		} catch (Exception e) {
			log.info("生成文件出错...");
			e.printStackTrace();
		}finally{
			out.close();
		}
		//下载
		DownloadFileUtil downLoad=new DownloadFileUtil();
		if (file.exists()) {
			log.info("文件下载中...");
			downLoad.downLoad(planPaperVo.getClass_name()+"-ScoreData.xls", filePath, 
					"application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
	}
	public String[] addStrings(String[] strs,int l,String type){
		String[] re = new String[strs.length+(l*3)];
		int init = 1;
		for(int i = 0;i<re.length;i++){
			if(i<strs.length){
				re[i]=strs[i];
			}else{
				if(type==null){
					re[i]="section_name_"+(init);
					re[++i]="question_number_"+(init);
					re[++i]="get_score_"+(init);
				}else if(type.equals("zh_CN")){
					re[i]="试卷题块_"+(init);
					re[++i]="题块题数_"+(init);
					re[++i]="题块得分/总分_"+(init);
				}else if(type.equals("en_US")){
					re[i]="section_name_"+(init);
					re[++i]="question_number_"+(init);
					re[++i]="get_score_"+(init);
				}
				init++;
			}
		}
		return re;
	}
	public String[] addStrings2(String[] strs,int l2,String type){
		String[] re = new String[strs.length+(l2)];
		int init = 1;
		for(int i = 0;i<re.length;i++){
			if(i<strs.length){
				re[i]=strs[i];
			}else{
				if(type==null){
					re[i]="question_"+(init);
				}else if(type.equals("zh_CN")){
					re[i]="题目_"+(init);
				}else if(type.equals("en_US")){
					re[i]="question_"+(init);
				}
				init++;
			}
		}
		return re;
	}
	public String[] addStrings3(String[] strs,int l,String type){
		String[] re = new String[strs.length+(l*1)];
		int init = 1;
		for(int i = 0;i<re.length;i++){
			if(i<strs.length){
				re[i]=strs[i];
			}else{
				if(type==null){
					re[i]="question_answer_"+(init);
				}else if(type.equals("zh_CN")){
					re[i]="题目答案_"+(init);
				}else if(type.equals("en_US")){
					re[i]="question_answer_"+(init);
				}
				init++;
			}
		}
		return re;
	}
	public String[] addStrings4(String[] strs,int l,List<Map<String,Object>> dataList,String type){
		Map<String,Object> map = new HashMap<String,Object>();
		if(l>=1){
			map = dataList.get(0);
		}
		String[] re = new String[strs.length+(l*1)];
		int init = 1;
		for(int i = 0;i<re.length;i++){
			if(i<strs.length){
				re[i]=strs[i];
			}else{
				if(type==null){
					re[i]="第"+init+"题("+map.get("question_type_"+(init))+")";
				}else if(type.equals("zh_CN")){
					re[i]="第"+init+"题("+map.get("question_type_"+(init))+")";
				}else if(type.equals("en_US")){
					re[i]="NO."+init+"("+map.get("question_type_"+(init))+")";
				}
				init++;
			}
		}
		return re;
	}
	
	/**
	 * 导出答案
	 * @throws Exception
	 */
	public void downAnswerData() throws Exception{
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String filePath= baseDir+planPaperVo.getClass_name()+"-AnswerData-"+DateTimeUtil.getNow()+".xls";//临时存储位置
		log.info("下载总分文件：文件临时存放\n"+filePath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("plan_paper_id", planPaperVo.getId());
		map.put("paper_id", planPaperVo.getPaper_id());
		map = teaStuPlanService.downAnswerData(map);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> dateList=(List<Map<String,Object>>)map.get("data");
		int l = (int)map.get("length");
		File file = new File(filePath);
		//生成Excel
		ExcelUtil excelUtil=new ExcelUtil();
		String [] columnName={"student_name","student_no","mobile","teacher_name","paper_name","paper_status",
				"long_time","total_score"};
		columnName = addStrings3(columnName,l,null);
		String lang=(String) this.session.get(SystemConstant.LANG);
		HSSFWorkbook wb;
		if(lang==null || "zh_CN".equals(lang)){
			String [] columnAlignName={"学生姓名","学生编号","手机号码","改卷教师","试卷名称","试卷状态","答题耗时(分钟)","试卷总分"};
			columnAlignName = addStrings4(columnAlignName,l,dateList,"zh_CN");
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "试卷答案");
		}else{
			String [] columnAlignName={"Student Name","Student Number","mobile","Teacher Name","Paper Name",
					"Paper Status","Long Time(minute)","Total Score"};	
			columnAlignName = addStrings4(columnAlignName,l,dateList,"en_US");
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "Answer Data");
		}
		OutputStream out = null;
		try{
			out = new FileOutputStream(file);
			wb.write(out);
			out.flush();
		} catch (Exception e) {
			log.info("生成文件出错...");
			e.printStackTrace();
		}finally{
			out.close();
		}
		//下载
		DownloadFileUtil downLoad=new DownloadFileUtil();
		if (file.exists()) {
			log.info("文件下载中...");
			downLoad.downLoad(planPaperVo.getClass_name()+"-AnswerData.xls", filePath, 
					"application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
	}
	
	
	/**
	 * @Description (TODO一键签到)
	 * @return
	 * @throws Exception
	 */
	public String oneKeyPro() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		int re = teaStuPlanService.oneKeyPro(dataMap, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (re >= 1 || re >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO一键打分)
	 * @return
	 * @throws Exception
	 */
	public String oneKeyScore() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		int re = teaStuPlanService.oneKeyScore(dataMap, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (re >= 1 || re >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * （教学模块）跳转在线测试登录界面
	 * 2016年6月28日 Seven
	 */
	public String toOnlineTestPage()  throws Exception {
		System.out.println(planPaperVo.toString());
		this.httpRequest.setAttribute("plan_id", planPaperVo.getPlan_id());
		this.httpRequest.setAttribute("paper_no", planPaperVo.getPaper_number());
		return "toOnlineTestPage";
	}
	
	/**
	 * 导出考试成绩单
	 * 
	 */
	public void downLoadTranscript() throws Exception{
		String plan_paper_id=planPaperVo.getPlan_paper_id();
		String[] student_ids=planPaperVo.getStudent_ids().split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		// 封装返回数据
		VelocityContext velocityContext = null;
		map.put("plan_paper_id", plan_paper_id);
		map.put("student_ids", student_ids);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list=teaStuPlanService.selectTranscript(map);
		/////
		String check_user_id="";
		String check_user_name="";
		String subj=null;
		List<String> fileList=new ArrayList<String>();
		for(Map<String,Object> temp:list){
			subj=(String) temp.get("subject");
			//查询历史总成绩分布
			Map<String,Object> rateMap=teaStuPlanService.selectRateData(subj);
			  
			
			if(!String.valueOf(temp.get("user_id")).equals(check_user_id)){//下一人
				if(velocityContext!=null){
					if("java".equalsIgnoreCase(subj)){
						fileList.add(DocUtil.createDoc("templete_Achieve_java.vm", velocityContext, check_user_name+"成绩单"));
					}else if("mainframe".equalsIgnoreCase(subj)){
						fileList.add(DocUtil.createDoc("templete_Achieve_mf.vm", velocityContext, check_user_name+"成绩单"));
					}else if("testing".equalsIgnoreCase(subj)){
						fileList.add(DocUtil.createDoc("templete_Achieve_testing.vm", velocityContext, check_user_name+"成绩单"));
					}
				}
				velocityContext = new VelocityContext();
				for (Map.Entry<String, Object> entry : rateMap.entrySet()) {  
					 velocityContext.put(entry.getKey(), entry.getValue());
					 velocityContext.put(entry.getKey()+"_en", entry.getValue());
				  
				}  
				velocityContext.put("name", temp.get("name"));
				velocityContext.put("name_en", temp.get("name"));
				velocityContext.put("idNumber", temp.get("cardno"));
				velocityContext.put("idNumber_en", temp.get("cardno"));
				velocityContext.put("examNbr", temp.get("exam_num"));
				velocityContext.put("examNbr_en", temp.get("exam_num"));
				velocityContext.put("examDate", temp.get("exam_date"));
				velocityContext.put("examDate_en", temp.get("exam_date"));
				velocityContext.put("score",temp.get("total"));
				velocityContext.put("score_en",temp.get("total"));
				if("mainframe".equalsIgnoreCase(subj)){
					velocityContext.put("subject","Mainframe");
					velocityContext.put("subject_en","Mainframe");
				}else{
					velocityContext.put("subject",subj);
					velocityContext.put("subject_en",subj);
				}
				velocityContext.put("reportNbr","No. FITTCN"+temp.get("exam_num"));
				velocityContext.put("reportNbr_en","No. FITTEN"+temp.get("exam_num"));
				check_user_id=String.valueOf(temp.get("user_id"));
				check_user_name=String.valueOf(temp.get("name"));
			}
			String section_name=temp.get("section_name").toString().trim();
			//Multiple Choice.||Multiple Choice-选择题  		TRUE or FALSE||TRUE or FALSE.-是非题	 Fill in the Blanks.||Fill in the Blanks-填空题 		Q&amp;A||Q&amp;A.-简答题   	Programming||Programming.-编程  
			switch(section_name){
			case "Multiple Choice":
					velocityContext.put("choiceQst",String.valueOf(temp.get("part")));
					velocityContext.put("choiceQst_en",String.valueOf(temp.get("part")));
				break;
			case "Multiple Choice.":
					velocityContext.put("choiceQst",String.valueOf(temp.get("part")));
					velocityContext.put("choiceQst_en",String.valueOf(temp.get("part")));
				break;
			case "TRUE or FALSE":
				velocityContext.put("judge",String.valueOf(temp.get("part")));
				velocityContext.put("judge_en",String.valueOf(temp.get("part")));
				break;
			case "TRUE or FALSE.":
				velocityContext.put("judge",String.valueOf(temp.get("part")));
				velocityContext.put("judge_en",String.valueOf(temp.get("part")));
				break;
			case "Fill in the Blanks":
				velocityContext.put("blank",String.valueOf(temp.get("part")));
				velocityContext.put("blank_en",String.valueOf(temp.get("part")));
				break;
			case "Fill in the Blanks.":
				velocityContext.put("blank",String.valueOf(temp.get("part")));
				velocityContext.put("blank_en",String.valueOf(temp.get("part")));
				break;
			case "Q&amp;A":
				velocityContext.put("singleAnswer",String.valueOf(temp.get("part")));
				velocityContext.put("singleAnswer_en",String.valueOf(temp.get("part")));
				break;
			case "Q&amp;A.":
				velocityContext.put("singleAnswer",String.valueOf(temp.get("part")));
				velocityContext.put("singleAnswer_en",String.valueOf(temp.get("part")));
				break;
			case "Programming":
				velocityContext.put("program",String.valueOf(temp.get("part")));
				velocityContext.put("program_en",String.valueOf(temp.get("part")));
				break;
			case "Programming.":
				velocityContext.put("program",String.valueOf(temp.get("part")));
				velocityContext.put("program_en",String.valueOf(temp.get("part")));
				break;
			default:
				break;
			}
		}
		if(velocityContext!=null){
			if("java".equalsIgnoreCase(subj)){
				fileList.add(DocUtil.createDoc("templete_Achieve_java.vm", velocityContext, list.get(list.size()-1).get("name")+"成绩单"));
			}else if("mainframe".equalsIgnoreCase(subj)){
				fileList.add(DocUtil.createDoc("templete_Achieve_mf.vm", velocityContext, list.get(list.size()-1).get("name")+"成绩单"));
			}else if("testing".equalsIgnoreCase(subj)){
				fileList.add(DocUtil.createDoc("templete_Achieve_testing.vm", velocityContext, list.get(list.size()-1).get("name")+"成绩单"));
			}
		}
		DownloadFileUtil.downLoadZipFiless(fileList, this.httpRequest, this.httpResponse);
	}
}
