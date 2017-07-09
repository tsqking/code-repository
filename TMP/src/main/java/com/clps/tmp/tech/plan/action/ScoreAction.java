package com.clps.tmp.tech.plan.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.plan.service.ScoreService;
import com.clps.tmp.tech.plan.vo.ExerciseScoreRatioVo;
import com.clps.tmp.tech.plan.vo.ExerciseScoreVo;
import com.clps.tmp.tech.plan.vo.PlanPaperVo;
import com.clps.tmp.tech.plan.vo.TotalScoreRatioVo;
import com.clps.tmp.tech.plan.vo.TotalScoreVo;


@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")
@Action("scoreAction")
@Results({
		@Result(name = "toScoreManagePage", location = "plan/personalPlan/score/scoreManage.jsp"),
		@Result(name = "toComputePage", location = "plan/personalPlan/score/computePage.jsp"),
		@Result(name = "toSetRatio", location = "plan/personalPlan/score/setRatio.jsp"),
		@Result(name = "toSetAtt", location = "plan/personalPlan/score/setAtt.jsp"),
		@Result(name = "toDetailNormal", location = "plan/personalPlan/score/detailNormal.jsp"),
		@Result(name = "toDetailAttendance", location = "plan/personalPlan/score/detailAttendance.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class ScoreAction extends BaseAction {
	
	private static final long serialVersionUID = -2751581269912641176L;
	private HashMap<String, Object> resultMap;// json返回数据map
	private ExerciseScoreVo exerciseScoreVo;
	private ExerciseScoreRatioVo exerciseScoreRatioVo;
	private TotalScoreVo totalScoreVo;
	private TotalScoreRatioVo totalScoreRatioVo;
	private PlanPaperVo planPaperVo;
	@Resource
	private ScoreService scoreService;
	
	public PlanPaperVo getPlanPaperVo() {
		return planPaperVo;
	}
	public void setPlanPaperVo(PlanPaperVo planPaperVo) {
		this.planPaperVo = planPaperVo;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public ExerciseScoreRatioVo getExerciseScoreRatioVo() {
		return exerciseScoreRatioVo;
	}
	public void setExerciseScoreRatioVo(ExerciseScoreRatioVo exerciseScoreRatioVo) {
		this.exerciseScoreRatioVo = exerciseScoreRatioVo;
	}
	public TotalScoreRatioVo getTotalScoreRatioVo() {
		return totalScoreRatioVo;
	}
	public void setTotalScoreRatioVo(TotalScoreRatioVo totalScoreRatioVo) {
		this.totalScoreRatioVo = totalScoreRatioVo;
	}
	public ExerciseScoreVo getExerciseScoreVo() {
		return exerciseScoreVo;
	}
	public void setExerciseScoreVo(ExerciseScoreVo exerciseScoreVo) {
		this.exerciseScoreVo = exerciseScoreVo;
	}
	public TotalScoreVo getTotalScoreVo() {
		return totalScoreVo;
	}
	public void setTotalScoreVo(TotalScoreVo totalScoreVo) {
		this.totalScoreVo = totalScoreVo;
	}
	
	/**
	 * @Description (TODO跳转成绩管理页面)
	 * @return
	 */
	public String toScoreManagePage() {
		return "toScoreManagePage";
	}
	
	/**
	 * @Description (TODO跳转计算成绩的页面)
	 * @return
	 * @throws Exception 
	 */
	public String toComputePage() throws Exception {
		//初始化数据
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		scoreService.initScoreData(planPaperVo.getPlan_id(), user);
		return "toComputePage";
	}
	
	/**
	 * @Description (TODO设置试卷分数比例)
	 * @return
	 */
	public String toSetRatio() {
		return "toSetRatio";
	}
	
	/**
	 * @Description (TODO设置态度分)
	 * @return
	 */
	public String toSetAtt() {
		return "toSetAtt";
	}
	
	/**
	 * @Description (TODO平时成绩明细)
	 * @return
	 */
	public String toDetailNormal() {
		return "toDetailNormal";
	}
	
	/**
	 * @Description (TODO查看考勤)
	 * @return
	 */
	public String toDetailAttendance() {
		return "toDetailAttendance";
	}
	
	
	/**
	 * @Description (TODO获取成绩总列表)
	 * @return
	 * @throws Exception
	 */
	public String selectTotalScoreInfo() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<TotalScoreVo> bootStrapPageVo = scoreService.selectTotalScoreInfo(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取试卷列表-设置期末卷)
	 * @return
	 * @throws Exception
	 */
	public String selectPlanPaperInfo() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<PlanPaperVo> bootStrapPageVo = scoreService.selectPlanPaperInfo(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取试卷列表-设置分数比例)
	 * @return
	 * @throws Exception
	 */
	public String selectPlanPaperInfo1() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<PlanPaperVo> bootStrapPageVo = scoreService.selectPlanPaperInfo1(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO设置期末卷)
	 * @return
	 * @throws Exception
	 */
	public String setLastPaper() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		int resData = scoreService.setLastPaper(exerciseScoreRatioVo, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO取消期末卷)
	 * @return
	 * @throws Exception
	 */
	public String unsetLastPaper() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		int resData = scoreService.unsetLastPaper(exerciseScoreRatioVo, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO默认加权)
	 * @return
	 * @throws Exception
	 */
	public String setDeRatio() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		int resData = scoreService.setDeRatio(exerciseScoreRatioVo, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO设置加权比例)
	 * @return
	 * @throws Exception
	 */
	public String setRatio() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		int resData = scoreService.setRatio(exerciseScoreRatioVo, user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO计算平时成绩)
	 * @return
	 * @throws Exception
	 */
	public String saveNormalScore() throws Exception {
		//计算平时分
		int resData = scoreService.saveNormalScore(exerciseScoreRatioVo);
		//计算考勤分,态度分,期末考试分
		resData = scoreService.saveStudentOtherScore(exerciseScoreRatioVo,false);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO推荐打默认态度分)
	 * @return
	 * @throws Exception
	 */
	public String setDeAtt() throws Exception {
		//计算考勤分,态度分,期末考试分
		int resData = scoreService.saveStudentOtherScore(exerciseScoreRatioVo,true);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO打态度分)
	 * @return
	 * @throws Exception
	 */
	public String setAtt() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		//计算考勤分,态度分,期末考试分
		int resData = scoreService.setAtt(totalScoreVo,user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取总分比例数据)
	 * @return
	 * @throws Exception
	 */
	public String getTotalRatio() throws Exception {
		//获取比例
		totalScoreRatioVo = scoreService.getTotalRatio(totalScoreRatioVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (totalScoreRatioVo != null) {
			rtn = AjaxReturnInfo.success("0");
			rtn.add("data", totalScoreRatioVo);
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO完成计算)
	 * @return
	 * @throws Exception
	 */
	public String finish() throws Exception {
		UserVo user = (UserVo) this.session.get(SystemConstant.USER);
		//保存比例
		int resData = scoreService.saveTotalRatio(totalScoreRatioVo,user);
		//计算总分
		resData = scoreService.finish(totalScoreRatioVo,user);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (resData >= 0) {
			rtn = AjaxReturnInfo.success("0");
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
		
	/**
	 * @Description (TODO平时成绩)
	 * @return
	 * @throws Exception
	 */
	public String selectDetailNormal() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<ExerciseScoreVo> bootStrapPageVo = scoreService.selectDetailNormal(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO考勤成绩查看)
	 * @return
	 * @throws Exception
	 */
	public String selectDetailAttendance() throws Exception {
		BtTableUtil bootStrapTable = new BtTableUtil();
		Map<String, Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
		BtTableVo<ExerciseScoreVo> bootStrapPageVo = scoreService.selectDetailAttendance(dataMap);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.add("data", bootStrapPageVo);
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取总分比例)
	 * @return
	 * @throws Exception
	 */
	public String getTotalScoreRatio() throws Exception {
		totalScoreRatioVo = scoreService.getTotalScoreRatio(planPaperVo);
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		if (totalScoreRatioVo != null) {
			rtn = AjaxReturnInfo.success("0");
			rtn.add("data", totalScoreRatioVo);
		} else {
			rtn = AjaxReturnInfo.success("1");
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * @Description (TODO下载总分数据)
	 * @throws Exception
	 */
	public void downTotalScoreData() throws Exception{
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String filePath= baseDir+planPaperVo.getClass_name()+"-TotalScoreData-"+DateTimeUtil.getNow()+".xls";//临时存储位置
		log.info("下载总分文件：文件临时存放\n"+filePath);
		List<Map<String,Object>> dateList=scoreService.getTotalScoreToExport(totalScoreVo.getPlan_id()+"");
		File file = new File(filePath);
		//生成Excel
		ExcelUtil excelUtil=new ExcelUtil();
		String [] columnName={"student_name","student_no","normal_score","attendance_score","attitude_score",
				"exam_score","total_score"};
		String lang=(String) this.session.get(SystemConstant.LANG);
		HSSFWorkbook wb;
		if(lang==null || "zh_CN".equals(lang)){
			String [] columnAlignName={"学生姓名","学生编号","平时分数","考勤分数","态度分数","期末试卷分数","总分成绩"};
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "总分数据");
		}else{
			String [] columnAlignName={"Student Name","Student Number","Usually Scores","Attendance Score",
					"Attitude Score","Exam Score","Total Score"};			
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
			downLoad.downLoad(planPaperVo.getClass_name()+"-TotalScoreData.xls", filePath, 
					"application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
	}
	
	
}
