package com.clps.tmp.tech.teacherpool.action;

import java.util.HashMap;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.SecurityHelper;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.util.GenerateNextNo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.point.vo.PointVo;
import com.clps.tmp.tech.teacherpool.service.TeacherpoolService;
import com.clps.tmp.tech.teacherpool.vo.HistoryVo;
import com.clps.tmp.tech.teacherpool.vo.TeacherpoolVo;
/***
 * 教师资源库管理
 * @author david.zhang
 *
 */
@SuppressWarnings({ "serial"})
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")   
@Action("teacherpool")
@Results({ @Result(name = "toTeacherpoolManagePage", location = "teacherpool/teacherpoolManage.jsp"),
		@Result(name = "toTeacherUpdate", location = "teacherpool/teacherUpdate.jsp"),
		@Result(name = "toTeacherAdd", location = "teacherpool/addNewTeacher.jsp"),
		@Result(name = "toLinkSkill_addSkillPage", location = "teacherpool/teacLinkSkill_addSkillPage.jsp"),
		@Result(name = "toLinkSkillPage", location = "teacherpool/teacLinkSkillPage.jsp"),
		@Result(name = "toLinkSkill_ReviewTeacherPage", location = "teacherpool/reviewTeacher.jsp"),
		@Result(name = "toTeacherHistory", location = "teacherpool/teacherHistory.jsp"),
		@Result(name = "toEditTeacherPage", location = "teacherpool/teacherUpdate.jsp"),
		@Result(name = "toTeacherDetailPage", location = "teacherpool/teacherDetailPage.jsp"),
		@Result(name = "toTeacLinkSkill_Point", location = "teacherpool/teacLinkSkill_Point.jsp"),
		@Result(name = "toPlanToPoolPage", location = "teacherpool/tecplanToPool/planToPool.jsp"),
        @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class TeacherpoolAction extends BaseAction{
	private TeacherpoolVo tepVo;
	private HistoryVo histVo;
	private PointVo ptVo;
	private HashMap<String,Object> resultMap;
	private String USER_PASSWORD_INFO = "userPwdInfo";
	
	public HistoryVo getHistVo() {
		return histVo;
	}
	public void setHistVo(HistoryVo histVo) {
		this.histVo = histVo;
	}
	/**
	 * @return the ptVo
	 */
	public PointVo getPtVo() {
		return ptVo;
	}
	/**
	 * @param ptVo the ptVo to set
	 */
	public void setPtVo(PointVo ptVo) {
		this.ptVo = ptVo;
	}
	@Resource
	
	private TeacherpoolService teapoolService;
	/**
	 * @return the tepVo
	 */
	public TeacherpoolVo getTepVo() {
		return tepVo;
	}
	/**
	 * @param tepVo the tepVo to set
	 */
	public void setTepVo(TeacherpoolVo tepVo) {
		this.tepVo = tepVo;
	}
	/**
	 * @return the resultMap
	 */
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	/**
	 * @param resultMap the resultMap to set
	 */
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
	 * 跳转到教师资源库管理页面
	 */
	public String toTeacherpoolManagePage(){
		return "toTeacherpoolManagePage";
	}
	/**
	 * 跳转到教师添加页面
	 */
	public String toTeacherAdd(){
		return "toTeacherAdd";
	}
	/***
	 * 
	 */
	public String toTeacherUpdate(){
		return "toTeacherUpdate";
	}
	/**
	 * 获取教师分页内容
	 * @throws Exception 
	 */
	public String getTeacherpoolPage() throws Exception{
		DataTableUtil<TeacherpoolVo> dataTableUtil=new DataTableUtil<TeacherpoolVo>();
		PageVo<TeacherpoolVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(tepVo==null) tepVo=new TeacherpoolVo();//若为modeldriven方式 可省略
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
	
		if(tepVo.getFirst_skill()!=null && !tepVo.getFirst_skill().equals("")){
			where1.put("g.`id`", tepVo.getFirst_skill());
		}
		if(tepVo.getSecond_skill()!=null && !tepVo.getSecond_skill().equals("")){
			where1.put("f.`id`", tepVo.getSecond_skill());
		}
		if(tepVo.getThird_skill()!=null && !tepVo.getThird_skill().equals("")){
			where1.put("e.`id`", tepVo.getThird_skill());
		}
		if(tepVo.getKnowledge()!=null && !tepVo.getKnowledge().equals("")){
			where1.put("c.`id`", tepVo.getKnowledge());
		}
		pageVo.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(tepVo.getName()!=null && !tepVo.getName().equals("")){
			where2.put("a.`name`", tepVo.getName());
		}
		if(tepVo.getNo()!=null &&!tepVo.getNo().equals("")){
			where2.put("a.`no`", tepVo.getNo());
		}
		pageVo.setWhere2(where2);
		//3.获取数据
		pageVo = teapoolService.queryTeacherpoolPage(pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	

	/**
	 * 添加教师
	 * @return
	 * @throws Exception
	 */
	public String addTeacherInfo() throws Exception{
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
	/*	int maxRole = Integer.parseInt(userNow.getRole());
		int target = Integer.parseInt(tepVo.getRole());
		if(target<maxRole){
			//目标权限>自己权限
			tepVo.setRole(maxRole+"");
		}*/
		tepVo.setRole("2");
		boolean re = teapoolService.addTeacher(tepVo, userNow);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
		/***
	 * 添加教师
	 * @return
	 * @throws Exception
	 */
	/*public String addTeacherInfo() throws Exception{
		UserVo useNow = (UserVo)session.get(SystemConstant.USER);
		int maxRole = Integer.parseInt(userNow.getRole());
		int target = Integer.parseInt(user.getRole());
		if(target<maxRole){
			//目标权限>自己权限
			user.setRole(maxRole+"");
		}
		SecurityHelper sh = new SecurityHelper();
		tepVo.setCreate_person(useNow.getName());
		tepVo.setCreate_time(DateTimeUtil.nowToDatabase());
		tepVo.setUpdate_person(useNow.getName());
		tepVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		tepVo.setNo(new GenerateNextNo().getNextCourseNo());
		tepVo.setPassword(sh.DESEncrypt(tepVo.getPassword()));
		int effect=teapoolService.addTeacher(tepVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();
		return "json";
	}*/
	/***
	 * 删除教师
	 * @return
	 * @throws Exception
	 */
	public String deleteTeacher() throws Exception{
		log.info("删除教师："+tepVo.getNbr());
		int effect=teapoolService.deleteTeacher(tepVo.getNbr());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		if(effect==1){//删除成功
			rtn.setMessage("1");
		}else{//有关联，不能删除
			rtn.setMessage("-1");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	

	
	/**
	 * 查看教师信息
	 * @return
	 * @throws Exception
	 */
	public String toTeacherDetailPage() throws Exception{
		log.info("查询教师："+tepVo.getNbr());
		tepVo = teapoolService.detailTeacehr(tepVo.getNbr());
		return "toTeacherDetailPage";
	}
	/**
	 * 去修改教师界面
	 * @return
	 * @throws Exception
	 */
	public String toEditTeacherPage() throws Exception{
		log.info("编辑教师："+tepVo.getNbr());
		
		tepVo=teapoolService.detailTeacehr(tepVo.getNbr());
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("0", tepVo.getNbr());//0为id
		map.put("1", tepVo.getPassword());//1为旧密码
		System.out.println("++++++++++++++"+tepVo.getPassword());
		map.put("2", "false");//2为标识
		session.put(this.USER_PASSWORD_INFO, map);
		tepVo.setPassword("******");
		return "toEditTeacherPage";
	}
	
	
	/**
	 * @throws Exception 
	 * 获取账户头像
	  * findPhoto(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: findPhoto
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public String findPhoto() throws Exception {
	    HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("image/jpeg");
		response.setCharacterEncoding("UTF-8");
		String photo = teapoolService.queryTeacherPhoto(tepVo.getNbr());
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(photo!=null){
			rtn.setMessage("success");
			photo = photo.replaceAll(" ", "+");
			rtn.add("photo", photo);
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 修改教师信息
	 * @return
	 * @throws Exception
	 */
	/*public String updateTeacher() throws Exception{	
		log.info("编辑教师："+tepVo.getNbr());
	tepVo.setLast_update_time(tepVo.getUpdate_time());
	tepVo.setLast_update_person(tepVo.getUpdate_person());
	tepVo.setUpdate_time(DateTimeUtil.nowToDatabase());
	tepVo.setUpdate_person(((UserVo)this.session.get(SystemConstant.USER)).getName());
	int effect=teapoolService.updateTeacher(tepVo);
	AjaxReturnInfo rtn=null;
	if(effect==0){
		rtn=AjaxReturnInfo.success("0");
	}else{
		rtn=AjaxReturnInfo.success("1");
	}
	resultMap=rtn.getReturnMap();
	return "json";
	}*/
	public String updateTeacher() throws Exception{
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		String role = userNow.getRole();
		boolean flag = false;
		if(role.equals("0")){
			flag = true;
		}
		tepVo.setUpdate_person(userNow.getName());
		tepVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		tepVo.setBirthday(tepVo.getBirthday().replaceAll("-", ""));
		//对密码的处理
		@SuppressWarnings("unchecked")
		HashMap<String,String> map= (HashMap<String,String>)session.get(this.USER_PASSWORD_INFO);
		if(map.get("0").equals(tepVo.getNbr())){
			if(map.get("2").equals("true")){
				SecurityHelper sh = new SecurityHelper();		
				tepVo.setPassword(sh.DESEncrypt(tepVo.getPassword()));	
			}else{
				tepVo.setPassword(map.get("1"));	
			}
			boolean re = teapoolService.updateTeacher(tepVo, flag);
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			if(re){
				rtn.setMessage("success");
			}else{
				rtn.setMessage("error");
			}
			resultMap=rtn.getReturnMap();
			return "json";
		}else{
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setMessage("error");
			resultMap=rtn.getReturnMap();
			return "json";
		}	
	}
	/**
	 * 判断是否为超级管理员
	  * judgeSuperAdmin(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: judge
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String judgeSuperAdmin(){
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		//tepVo.setRole(userNow.getRole());
		//System.out.println("++++++++++++++++++++"+tepVo.getRole());
		String role = userNow.getRole();
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.setMessage(role);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	
	/**
	 * 更新图片
	  * updateTeacherPhoto
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: updateTeacherPhoto
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String updateTeacherPhoto() throws Exception{
		String photo = tepVo.getPhoto();
		String nbr = tepVo.getNbr();
		boolean re = teapoolService.updateTeacherPhoto(photo, nbr);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 确认密码
	  * checkTeacherNow(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: checkTeacherNow
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String checkTeacherNow(){
		UserVo userNow = (UserVo)session.get(SystemConstant.USER);
		SecurityHelper sh = new SecurityHelper();
		/*tepVo.setPwd1(userNow.getPwd1());
		tepVo.setPwd2(userNow.getPwd1());*/
		//tepVo.setPassword(userNow.getPassword());
		@SuppressWarnings("static-access")
		String p1 = sh.DESEncrypt(tepVo.getPwd1());
		String pwd1 = userNow.getPassword();
		@SuppressWarnings("static-access")
		String p2 = sh.DESEncrypt(tepVo.getPwd2());
		@SuppressWarnings("unchecked")
		String pwd2 = ((HashMap<String,String>)session.get(this.USER_PASSWORD_INFO)).get("1");
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		//1.校验当前用户密码
		if(p1.equals(pwd1)){
			//判断二次校验
			boolean f = false;
			String role = userNow.getRole();
			if(!role.equals("0")){
				if(p2.equals(pwd2)){
					rtn.setMessage("success");
					f = true;
				}else{
					rtn.setMessage("error");
				}	
			}else{
				rtn.setMessage("success");
				f = true;
			}
			//写入判断值
			if(f){
				@SuppressWarnings("unchecked")
				HashMap<String,String> map = (HashMap<String,String>)session.get(this.USER_PASSWORD_INFO);
				map.put("2", "true");
				session.put(this.USER_PASSWORD_INFO, map);
			}	
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";	
	}
	/***
	 * 跳转到教学历史查询页面
	 */
	public String toTeacherHistory(){
		return "toTeacherHistory";
	}
	/**
	 * 获取教学历史分页内容
	 * @throws Exception 
	 */
	public String getHistoryPage() throws Exception{
		DataTableUtil<TeacherpoolVo> dataTableUtil=new DataTableUtil<TeacherpoolVo>();
		PageVo<TeacherpoolVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(tepVo==null) tepVo=new TeacherpoolVo();//若为modeldriven方式 可省略
		//1.搜索条件
		//1.1.精确查询
		//1.2.模糊查询
		//3.获取数据
		pageVo = teapoolService.queryHistory(pageVo,tepVo.getNbr());
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	
	/**
	 * 跳转课程技能知识点管理界面
	 */
	public String toLinkSkillPage() throws Exception{
		return "toLinkSkillPage";
	}
	/**
	 * 获取教师技能列表
	 */
	public String getSkillPage() throws Exception{
		DataTableUtil<PointVo> dataTableUtil=new DataTableUtil<PointVo>();
		PageVo<PointVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(tepVo==null) tepVo=new TeacherpoolVo();//若为modeldriven方式 可省略
		if(ptVo==null) ptVo=new PointVo();//若为modeldriven方式 可省略
		log.info("教师nbr:"+tepVo.getNbr()+" 一级技能id:"+ptVo.getFirst_skill()+" 二级技能id:"+ptVo.getSecond_skill()+" 三级技能id:"+ptVo.getThird_skill());
		//1.搜索条件
		//1.1其他自定义条件
		HashMap<String, String> other = new HashMap<String, String>();
		if(tepVo.getNbr()!=null && !tepVo.getNbr().equals("")){
			other.put("nbr", tepVo.getNbr());
		}
		pageVo.setOther(other);
		//1.2.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(ptVo.getFirst_skill()!=null && !ptVo.getFirst_skill().equals("")){
			where1.put("e.`id`", ptVo.getFirst_skill());
		}
		if(ptVo.getSecond_skill()!=null && !ptVo.getSecond_skill().equals("")){
			where1.put("d.`id`", ptVo.getSecond_skill());
		}
		if(ptVo.getThird_skill()!=null && !ptVo.getThird_skill().equals("")){
			where1.put("c.`id`", ptVo.getThird_skill());
		}
		pageVo.setWhere1(where1);
		//3.获取数据
		pageVo = teapoolService.querySkillPage(pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	
	/***
	 * 跳转到教师技能知识点管理页面
	 */
	public String toLinkSkill_addSkillPage() throws Exception{
		return "toLinkSkill_addSkillPage";
	}
	/**
	 * 撤销教师知识点管理权限
	 */
	public String revokeToTeacher() throws Exception{
		String nbr=tepVo.getNbr();
		String pointId=ptVo.getId();
		boolean re = teapoolService.reVokeTeacher(nbr,pointId);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 添加教师知识点管理权限
	 */
	public String addManage() throws Exception{
		String nbr=tepVo.getNbr();
		String pointId=ptVo.getId();
		boolean re = teapoolService.addManage(nbr,pointId);
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		if(re){
			rtn.setMessage("success");
		}else{
			rtn.setMessage("error");
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 为教师添加技能知识点
	 */
	public String addTeacSkillPoint()throws Exception{
		log.info("为教师添加新的知识点：教师ID-"+tepVo.getNbr()+" 一级技能ID-"+ptVo.getFirst_skill_id()+" 二级技能ID-"+ptVo.getSecond_skill_id()
				+" 三级技能ID-"+ptVo.getThird_skill_id()+" 知识点ID-"+ptVo.getId());
		String skillId=	("".equals(ptVo.getThird_skill_id()) || null==ptVo.getThird_skill_id())?
						(("".equals(ptVo.getSecond_skill_id()) || null==ptVo.getSecond_skill_id())?ptVo.getFirst_skill_id():ptVo.getSecond_skill_id()):
							ptVo.getThird_skill_id();
		int effect=teapoolService.addTeacSkillPoint(tepVo.getNbr(),ptVo.getId(),skillId,ptVo.getDescription());
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
	 */
	public String removeTeacherSkill() throws Exception{
		log.info("从教师移除技能：课程ID-"+tepVo.getNbr()+" 一级技能ID-"+ptVo.getFirst_skill_id()+" 二级技能ID-"+ptVo.getSecond_skill_id()
				+" 三级技能ID-"+ptVo.getThird_skill_id());
		String skillId=	("".equals(ptVo.getThird_skill_id()) || null==ptVo.getThird_skill_id())?
				(("".equals(ptVo.getSecond_skill_id()) || null==ptVo.getSecond_skill_id())?ptVo.getFirst_skill_id():ptVo.getSecond_skill_id()):
					ptVo.getThird_skill_id();
		int effect=teapoolService.removeTeacherSkill(tepVo.getNbr(),skillId);
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();		
		return "json";
	}
	
	/**
	 * 跳转教师的技能下知识点的管理界面
	 */
	public String toTeacLinkSkill_Point(){
		log.info("跳转教师某技能下知识点列表界面：课程ID-"+tepVo.getNbr()+"一级技能ID-"+ptVo.getFirst_skill_id()+" 二级技能ID-"+ptVo.getSecond_skill_id()
				+" 三级技能ID-"+ptVo.getThird_skill_id());
		return "toTeacLinkSkill_Point";
	}
	
	/**
	 * 某教师下某技能的知识点列表
	 */
	public String getPointPage() throws Exception{
		log.info("获取某教师某技能下的知识点：教师ID-"+tepVo.getNbr()+" 一级技能ID-"+ptVo.getFirst_skill_id()+" 二级技能ID-"+ptVo.getSecond_skill_id()
				+" 三级技能ID-"+ptVo.getThird_skill_id());
		String skillId=	("".equals(ptVo.getThird_skill_id()) || null==ptVo.getThird_skill_id())?
				(("".equals(ptVo.getSecond_skill_id()) || null==ptVo.getSecond_skill_id())?ptVo.getFirst_skill_id():ptVo.getSecond_skill_id()):
					ptVo.getThird_skill_id();
		
		DataTableUtil<PointVo> dataTableUtil=new DataTableUtil<PointVo>();
		PageVo<PointVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		//获取数据
		pageVo = teapoolService.queryPointPage(pageVo,tepVo.getNbr(),skillId);
		//返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	
	/**
	 * 管理教师下知识点
	 * @throws Exception 
	 */
	public String managePoint() throws Exception{
		log.info("管理教师下知识点：教师号-"+tepVo.getNbr()+" 知识点ID-"+ptVo.getId()+" 操作-"+tepVo.getDescription());
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("nbr", tepVo.getNbr());
		paramMap.put("point_id", ptVo.getId());
		int effect=teapoolService.managePoint(paramMap,tepVo.getDescription());
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect));
		resultMap=rtn.getReturnMap();		
		return "json";
	}
	
	/***
	 * 跳转到教师技能知识体系页面
	 */
	public String toLinkSkill_ReviewTeacherPage(){
		return "toLinkSkill_ReviewTeacherPage";
	}
	/****
	 * 教师知识体系一览
	 */
	public String reviewTeacher() throws Exception{
			log.info("教师知识点一览：教师ID-"+tepVo.getNbr());
			LinkedList<HashMap<String,Object>> list=teapoolService.reviewTeacher(tepVo.getNbr());
			log.info(list.toString());
			AjaxReturnInfo rtn=AjaxReturnInfo.success("");
			rtn.add("teacherList", JSONArray.fromObject(list).toString());
			resultMap=rtn.getReturnMap();	
			return "json";
		}
	/**
	 * 检查数据的唯一
	  * checkData(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * @Title: checkData
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public String checkData()throws Exception{
		String name = tepVo.getUsername();
		String mobile = tepVo.getMobile();
		//登录名
		if(name!=null&&!name.equals("")){
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setSuccess(true);
			if(name.toUpperCase().startsWith("TMPUSER")){
				//名称前缀不合法
				rtn.add("result", "name");
			}else{
				int r = teapoolService.checkData(name, null);
				if(r==0){
					//查询失败
					rtn.add("result", "error");
				}else if(r==1){
					//无重复
					rtn.add("result", "success");
				}else if(r==2){
					//重复
					rtn.add("result", "repeat");
				}else{
					//查询失败
					rtn.add("result", "error");
				}
			}		
			//返回			
			resultMap=rtn.getReturnMap();
			return "json";
		}
		//手机号
		if(mobile!=null&&!mobile.equals("")){
			int r = teapoolService.checkData(null, mobile);
			AjaxReturnInfo rtn = AjaxReturnInfo.success("");
			rtn.setSuccess(true);
			if(r==0){
				//查询失败
				rtn.add("result", "error");
			}else if(r==1){
				//无重复
				rtn.add("result", "success");
			}else if(r==2){
				//重复
				rtn.add("result", "repeat");
			}else{
				//查询失败
				rtn.add("result", "error");
			}
			//返回			
			resultMap=rtn.getReturnMap();
			return "json";
		}
		//返回	
		AjaxReturnInfo rtn = AjaxReturnInfo.success("");
		rtn.setSuccess(false);
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转教学计划中选取教师界面
	 * 2016年1月5日 Seven
	 */
	public String toPlanToPoolPage(){
		return "toPlanToPoolPage";
	}
	/**
	 * 获取能教授特定知识点的教师信息(分页)
	 * @throws Exception 
	 */
	public String getTeachersBySpecialPoint() throws Exception{
		DataTableUtil<TeacherpoolVo> dataTableUtil=new DataTableUtil<TeacherpoolVo>();
		PageVo<TeacherpoolVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(tepVo==null) tepVo=new TeacherpoolVo();//若为modeldriven方式 可省略
		if(ptVo==null) ptVo=new PointVo();//若为modeldriven方式 可省略
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(tepVo.getDescription()!=null && !tepVo.getDescription().equals("")){
			where1.put("b.`description`", tepVo.getDescription());
		}
		pageVo.setWhere1(where1);
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(tepVo.getNbr()!=null && !tepVo.getNbr().equals("")){
			where2.put("b.`no`", tepVo.getNbr());
		}
		if(tepVo.getName()!=null &&!tepVo.getName().equals("")){
			where2.put("b.`name`", tepVo.getName());
			where2.put("b.`en_name`", tepVo.getName());
		}
		pageVo.setWhere2(where2);
		//3.获取数据
		pageVo = teapoolService.queryTeachersBySpecialPoint(ptVo.getId(),DateTimeUtil.systemToDatabase(tepVo.getStart_time()),DateTimeUtil.systemToDatabase(tepVo.getEnd_time()),pageVo);
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 记录教学历史信息<br>
	 * 需要参数： <br>histVo.teacher_id 教师号 <br>histVo.class_id 班级号<br>histVo.point_id 授课知识点ID号<br>histVo.starttime 开始时间<br>histVo.endtime 结束时间<br>histVo.address 地点<br><br>
	 * 2016年1月7日 Seven
	 */
	public String recordTechHistory() throws Exception{
		log.info("记录授课历史");
		histVo.setConfirmperson(((UserVo)session.get(SystemConstant.USER)).getName());
		histVo.setConfirmtime(DateTimeUtil.nowToDatabase());
		histVo.setStarttime(DateTimeUtil.systemToDatabase(histVo.getStarttime()));
		histVo.setEndtime(DateTimeUtil.systemToDatabase(histVo.getEndtime()));
		teapoolService.recordTechHistory(histVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		resultMap=rtn.getReturnMap();
//		return Json(new { "status":"302", "location": "/oauth/respond" });
		return "json";
	}
}