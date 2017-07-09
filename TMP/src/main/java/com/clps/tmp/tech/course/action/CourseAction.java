package com.clps.tmp.tech.course.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

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
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.OptionService;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.course.service.CourseService;
import com.clps.tmp.tech.course.vo.CourseVo;
import com.clps.tmp.tech.point.vo.PointVo;
/**
 * 课程管理
 * @author Seven
 * 2015年11月16日
 */
@SuppressWarnings({ "serial"})
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")   
@Action("course")
@Results({ @Result(name = "toCourseManagePage", location = "course/courseManage.jsp"),
		@Result(name = "toAddNewCoursePage", location = "course/addCourse.jsp"),
		@Result(name = "toViewCoursePage", location = "course/viewCourse.jsp"),
		@Result(name = "toEditCoursePage", location = "course/editCourse.jsp"),
		@Result(name = "toLinkSkillPage", location = "course/linkSkill.jsp"),
		@Result(name = "toLinkSkill_AddSkillPage", location = "course/linkSkill_addSkill.jsp"),
		@Result(name = "toLinkSkill_PointPage", location = "course/linkSkill_pointList.jsp"),
		@Result(name = "toLinkSkill_ReviewCoursePage", location = "course/reviewCourse.jsp"),
		@Result(name = "toCourseSearchAndCustPage", location = "course/courseSearchAndCust.jsp"),
        @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class CourseAction extends BaseAction {
	private CourseVo courseVo;//课程数据封装
	private PointVo pointVo;//知识点数据封装
	private HashMap<String, Object> resultMap;// json返回数据map
	@Resource
	private CourseService courseService; //业务处理service
	@Resource
	private OptionService optionService;
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public CourseVo getCourseVo() {
		return courseVo;
	}
	public void setCourseVo(CourseVo courseVo) {
		this.courseVo = courseVo;
	}
	public PointVo getPointVo() {
		return pointVo;
	}
	public void setPointVo(PointVo pointVo) {
		this.pointVo = pointVo;
	}
	/**
	 * 跳转到课程管理界面
	 * 2015年11月16日 Seven
	 */
	public String toCourseManagePage() throws Exception{
		return "toCourseManagePage";
	}
	/**
	 * 获取课程管理表格记录
	 * 2015年11月17日 Seven
	 * @throws Exception 
	 */
	public String getCoursePage() throws Exception{
		DataTableUtil<CourseVo> dataTableUtil=new DataTableUtil<CourseVo>();
		PageVo<CourseVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(courseVo==null) courseVo=new CourseVo();//若为modeldriven方式 可省略
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(courseVo.getSearchEnable()!=null && !courseVo.getSearchEnable().equals("")){
			where1.put("b.`value`", courseVo.getSearchEnable());
		}
		if(courseVo.getSearchDirection()!=null && !courseVo.getSearchDirection().equals("")){
			where1.put("c.`value`", courseVo.getSearchDirection());
		}
		if(courseVo.getSearchProperty()!=null && !courseVo.getSearchProperty().equals("")){
			where1.put("e.`value`", courseVo.getSearchProperty());
		}
		if(courseVo.getSearchCategory()!=null && !courseVo.getSearchCategory().equals("")){
			where1.put("a.`category`", courseVo.getSearchCategory());
		}
		pageVo.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(courseVo.getSearchName()!=null && !courseVo.getSearchName().equals("")){
			where2.put("a.`name[lang]`", courseVo.getSearchName());
		}
		if(courseVo.getSearchNo()!=null && !courseVo.getSearchNo().equals("")){
			where2.put("a.`no`", courseVo.getSearchNo());
		}
		pageVo.setWhere2(where2);
		//1.3其他条件
		HashMap<String, String> other = new HashMap<String, String>();
		if(courseVo.getSearchSubCategory()!=null && !courseVo.getSearchSubCategory().equals("")){
			other.put("ee.`id`", courseVo.getSearchSubCategory());
		}
		if(courseVo.getSearchSubSubCategory()!=null && !courseVo.getSearchSubSubCategory().equals("")){
			other.put("dd.`id`", courseVo.getSearchSubSubCategory());
		}
		pageVo.setOther(other);
		//3.获取数据
		pageVo = courseService.queryCoursePage(pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 获取子类别（即第一级技能）
	 * 2016年4月7日 Seven
	 * @throws Exception 
	 */
	public String getSubCategory() throws Exception{
		List<SelectVo> list=this.courseService.getSubCategory();
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 获取子子类别（即第二级技能）
	 * 2016年4月7日 Seven
	 */
	public String getSubSubCategory() throws Exception{
		List<SelectVo> list=this.courseService.getSubSubCategory();
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("options", list);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 跳转添加新课程界面
	 */
	public String toAddNewCoursePage(){
		return "toAddNewCoursePage";
	}
	/**
	 * 添加课程
	 * 2015年11月17日 Seven
	 * @throws Exception 
	 */
	public String addCourse() throws Exception{
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		courseVo.setCreate_person(user.getName());
		courseVo.setCreate_time(DateTimeUtil.nowToDatabase());
		courseVo.setUpdate_person(user.getName());
		courseVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		courseVo.setNo(new GenerateNextNo().getNextCourseNo());
		int effect=courseService.addCourse(courseVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转查看课程简要信息界面
	 * 2015年11月17日 Seven
	 */
	public String toViewCoursePage() throws Exception{
		log.info("查看课程："+courseVo.getId());
		courseVo=courseService.getCourseById(courseVo.getId());
		return "toViewCoursePage";
	}
	/**
	 * 去修改课程界面
	 * 2015年11月17日 Seven
	 * @throws Exception 
	 */
	public String toEditCoursePage() throws Exception{
		log.info("编辑课程："+courseVo.getId());
		courseVo=courseService.getCourseById(courseVo.getId());
		return "toEditCoursePage";
	}
	/**
	 * 修改课程信息
	 * 2015年11月17日 Seven
	 */
	public String editCourse()throws Exception{
		log.info("编辑课程："+courseVo.getId());
		courseVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		courseVo.setUpdate_person(((UserVo)this.session.get(SystemConstant.USER)).getName());
		System.out.println("修改的Course："+courseVo.toString());
		int effect=courseService.editCourse(courseVo);
		AjaxReturnInfo rtn=null;
		if(effect==0){
			rtn=AjaxReturnInfo.success("0");
		}else{
			rtn=AjaxReturnInfo.success("1");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 删除课程
	 * 2015年11月18日 Seven
	 */
	public String deleteCourse() throws Exception{
		log.info("删除课程："+courseVo.getId());
		int effect=courseService.deleteCourse(courseVo.getId());
		AjaxReturnInfo rtn=null;
		if(effect<0){
			rtn=AjaxReturnInfo.failed(String.valueOf(effect));
		}else{
			rtn=AjaxReturnInfo.success(String.valueOf(effect));
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转课程技能知识点管理界面
	 * 2015年11月18日 Seven
	 */
	public String toLinkSkillPage() throws Exception{
		return "toLinkSkillPage";
	}
	/**
	 * 获取课程技能列表
	 * 2015年11月18日 Seven
	 */
	public String getSkillPage() throws Exception{
		DataTableUtil<PointVo> dataTableUtil=new DataTableUtil<PointVo>();
		PageVo<PointVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(courseVo==null) courseVo=new CourseVo();//若为modeldriven方式 可省略
		if(pointVo==null) pointVo=new PointVo();//若为modeldriven方式 可省略
		log.info("课程id:"+courseVo.getId()+" 一级技能id:"+pointVo.getFirst_skill()+" 二级技能id:"+pointVo.getSecond_skill()+" 三级技能id:"+pointVo.getThird_skill());
		//1.搜索条件
		//1.1其他自定义条件
		HashMap<String, String> other = new HashMap<String, String>();
		if(courseVo.getId()!=null && !courseVo.getId().equals("")){
			other.put("course_id", courseVo.getId());
		}
		pageVo.setOther(other);
		//1.2.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(pointVo.getFirst_skill()!=null && !pointVo.getFirst_skill().equals("")){
			where1.put("e.`id`", pointVo.getFirst_skill());
		}
		if(pointVo.getSecond_skill()!=null && !pointVo.getSecond_skill().equals("")){
			where1.put("d.`id`", pointVo.getSecond_skill());
		}
		if(pointVo.getThird_skill()!=null && !pointVo.getThird_skill().equals("")){
			where1.put("c.`id`", pointVo.getThird_skill());
		}
		pageVo.setWhere1(where1);
		//3.获取数据
		pageVo = courseService.querySkillPage(pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 跳转课程添加技能界面
	 */
	public String toLinkSkill_AddSkillPage(){
		return "toLinkSkill_AddSkillPage";
	}
	/**
	 * 为课程添加技能知识点
	 */
	public String addSkillPoint()throws Exception{
		log.info("为课程添加新的知识点：课程ID-"+courseVo.getId()+" 一级技能ID-"+pointVo.getFirst_skill_id()+" 二级技能ID-"+pointVo.getSecond_skill_id()
				+" 三级技能ID-"+pointVo.getThird_skill_id()+" 知识点ID-"+pointVo.getId());
		String skillId=	("".equals(pointVo.getThird_skill_id()) || null==pointVo.getThird_skill_id())?
						(("".equals(pointVo.getSecond_skill_id()) || null==pointVo.getSecond_skill_id())?pointVo.getFirst_skill_id():pointVo.getSecond_skill_id()):
						pointVo.getThird_skill_id();
		int effect=courseService.addSkillPoint(courseVo.getId(),pointVo.getId(),skillId);
		AjaxReturnInfo rtn=null;
		if(effect==0){
			rtn=AjaxReturnInfo.success("1");//添加成功，但是技能下没知识点，无效操作
		}else{
			rtn=AjaxReturnInfo.success("0");//添加成功
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 移除技能
	 * 2015年11月19日 Seven
	 */
	public String removeCourseSkill() throws Exception{
		log.info("从课程移除技能：课程ID-"+courseVo.getId()+" 一级技能ID-"+pointVo.getFirst_skill_id()+" 二级技能ID-"+pointVo.getSecond_skill_id()
				+" 三级技能ID-"+pointVo.getThird_skill_id());
		String skillId=	("".equals(pointVo.getThird_skill_id()) || null==pointVo.getThird_skill_id())?
				(("".equals(pointVo.getSecond_skill_id()) || null==pointVo.getSecond_skill_id())?pointVo.getFirst_skill_id():pointVo.getSecond_skill_id()):
				pointVo.getThird_skill_id();
		int effect=courseService.removeCourseSkill(courseVo.getId(),skillId);
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();		
		return "json";
	}
	/**
	 * 某课程下某技能的知识点列表
	 * 2015年11月20日 Seven
	 */
	public String getPointPage() throws Exception{
		log.info("获取某课程某技能下的知识点：课程ID-"+courseVo.getId()+" 一级技能ID-"+pointVo.getFirst_skill_id()+" 二级技能ID-"+pointVo.getSecond_skill_id()
				+" 三级技能ID-"+pointVo.getThird_skill_id());
		String skillId=	("".equals(pointVo.getThird_skill_id()) || null==pointVo.getThird_skill_id())?
				(("".equals(pointVo.getSecond_skill_id()) || null==pointVo.getSecond_skill_id())?pointVo.getFirst_skill_id():pointVo.getSecond_skill_id()):
				pointVo.getThird_skill_id();
		
		DataTableUtil<PointVo> dataTableUtil=new DataTableUtil<PointVo>();
		PageVo<PointVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		//获取数据
		pageVo = courseService.queryPointPage(pageVo,courseVo.getId(),skillId);
		//返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 跳转课程的技能下知识点的管理界面
	 * 2015年11月20日 Seven
	 */
	public String toLinkSkill_PointPage(){
		log.info("跳转课程某技能下知识点列表界面：课程ID-"+courseVo.getId()+"一级技能ID-"+pointVo.getFirst_skill_id()+" 二级技能ID-"+pointVo.getSecond_skill_id()
				+" 三级技能ID-"+pointVo.getThird_skill_id());
		return "toLinkSkill_PointPage";
	}
	/**
	 * 管理课程下知识点
	 * 2015年11月20日 Seven
	 * @throws Exception 
	 */
	public String managePoint() throws Exception{
		log.info("管理课程下知识点：课程号-"+courseVo.getId()+" 知识点ID-"+pointVo.getId()+" 操作-"+courseVo.getDescription());
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("course_id", courseVo.getId());
		paramMap.put("point_id", pointVo.getId());
		int effect=courseService.managePoint(paramMap,courseVo.getDescription());
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();		
		return "json";
	}
	/**
	 * 跳转课程一览界面
	 * 2015年11月23日 Seven
	 */
	public String toLinkSkill_ReviewCoursePage(){
		return "toLinkSkill_ReviewCoursePage";
	}
	/**
	 * 课程一览
	 * 2015年11月24日 Seven
	 */
	public String reviewCourse() throws Exception{
		log.info("课程知识点一览：课程ID-"+courseVo.getId());
		LinkedList<HashMap<String,Object>> list=courseService.reviewCourse(courseVo.getId());
		log.info(list.toString());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("courseList", JSONArray.fromObject(list).toString());
		resultMap=rtn.getReturnMap();	
		return "json";
	}
	/**
	 * 跳转课程查询与定制界面
	 * 2015年11月24日 Seven
	 */
	public String toCourseSearchAndCustPage(){
		return "toCourseSearchAndCustPage";
	}
	/**
	 * 获取课程下拉框
	 * <br>参数description：<br>&nbsp;&nbsp;&nbsp;yes/no(是否只选取锁定的课程lock)<br>&nbsp;&nbsp;&nbsp;0/1/-1(选取的课程类型，0-系统 1-定制 -1-不限) 
	 * <br>2015年11月24日 Seven
	 * @throws Exception 
	 */
	public String getCourseOption() throws Exception{
		String [] param=courseVo.getDescription().split("AND");
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("lock", "yes".equalsIgnoreCase(param[0])?"Y":"");
		paramMap.put("property", "-1".equalsIgnoreCase(param[1])?"":param[1]);
		List<SelectVo> courseList =  courseService.getCourseOption(paramMap);
		// 返回json数据
        AjaxReturnInfo rtn = null;
        rtn = AjaxReturnInfo.success("");
        rtn.add("options", courseList);
        resultMap = rtn.getReturnMap();
        return "json";
	}
	/**
	 * 根据课程号或者ID跳转 课程知识点一览界面
	 * 2015年11月24日 Seven
	 */
	public String toReviewCoursePage() throws Exception{
		if(!"".equals(courseVo.getNo()) && null!=courseVo.getNo()){
			courseVo=courseService.queryCourseByNo(courseVo.getNo());
		}else{
			courseVo=courseService.queryCourseById(courseVo.getId());
		}
		return "toLinkSkill_ReviewCoursePage";
	}
	/**
	 * 判断当前课程号是否被允许进行课程定制业务
	 * 2015年11月24日 Seven
	 */
	public String judgeToLinkSkillPage()throws Exception{
		courseVo=courseService.queryCourseByNo(courseVo.getNo());
		AjaxReturnInfo rtn = null;
		if(courseVo==null){
			rtn=AjaxReturnInfo.failed("1111");//课程不存在
		}else{
			if("Y".equalsIgnoreCase(courseVo.getLock())){
				rtn=AjaxReturnInfo.failed("2222");//课程被锁定
			}else{
				rtn=AjaxReturnInfo.success("");
				rtn.add("course", courseVo);
			}
		}
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 下载批量添加人员模板(用户管理)
	 * 2015年11月27日 Seven
	 * @throws Exception 
	 */
	public void downCourseData() throws Exception{
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String filePath= baseDir+"Course Data-"+DateTimeUtil.getNow()+".xls";//临时存储位置
		log.info("下载课程文件：文件临时存放\n"+filePath);
		List<Map<String,Object>> dateList=getCourseData();//获取数据
		File file = new File(filePath);
		//生成Excel
		ExcelUtil excelUtil=new ExcelUtil();
		String [] columnName={"category","sub_category","ssub_category","course_no","course_name","course_objectives",
				"duration","level","trainer","presentation","student_manual",
				"active_book","quiz","exercise_platform","target_client"};
		String lang=(String) this.session.get(SystemConstant.LANG);
		HSSFWorkbook wb;
		if(lang==null || "zh_CN".equals(lang)){
			String [] columnAlignName={"课程类别","涵盖技能","涵盖子技能","课程号","课程名称","课程描述",
					"课程耗时","难易程度","课程教员","教材讲义","学生手册",
					"实验手册","课程测试","练习平台","目标客户","是否需要"};
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "Course Data");
		}else{
			String [] columnAlignName={"Category","Sub Category","Sub-Sub-Category","Course No","Course Name","Course Objectives",
					"Duration","Level","Trainer","Presentation","Student Manual",
					"Active Book","Quiz","Exercise Platform","Target Client","Required"};
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "Course Data");
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
			downLoad.downLoad("Course Data.xls", filePath, "application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
	}
	//获取课程数据
	@SuppressWarnings("unused")
	private List<Map<String,Object>> getCourseData() throws Exception{
		BtTableUtil util=new BtTableUtil();
		Map<String,Object> paramMap=util.getParamers(httpRequest);
		List<Map<String,Object>> list=this.courseService.getCourseData(paramMap);
		list=setClient(list);
		return list;
	}
	//转换客户Clients
	@SuppressWarnings("unused")
	private List<Map<String,Object>> setClient(List<Map<String,Object>> list) throws Exception{
		List<Map<String,Object>> clientMapList=optionService.getOptionMapByGPVal("CLIENTS");
		for(Map<String,Object> map:list){
			String client=(String) map.get("target_client");
			if(client!=null){
				StringBuilder targetClient=new StringBuilder();
				String [] clients=client.split(",");
				for(int i=0;i<clients.length;i++){
					for(Map<String,Object> clientMap:clientMapList){
						if(clientMap.get("id").equals(clients[i].trim())){
							targetClient.append(clientMap.get("text")+", ");
							break;
						}
					}
				}
				targetClient.setLength(targetClient.length()-2);
				map.put("target_client", targetClient.toString());
			}
		}
		return list;
	}
}
