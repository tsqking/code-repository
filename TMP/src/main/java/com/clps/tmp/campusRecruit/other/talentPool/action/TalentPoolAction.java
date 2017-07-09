package com.clps.tmp.campusRecruit.other.talentPool.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.campusRecruit.other.talentPool.service.TalentPoolService;
import com.clps.tmp.campusRecruit.other.talentPool.vo.TalentVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.StringUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("publicPackage")
@Namespace("/campusRC")
@Controller
@Scope("prototype")
@Action("talent")
@Results({
	@Result(name="toTalentManagePage",location="../campusRecruit/other/talentPool/talentPoolManage.jsp"),
	@Result(name="toAddTalentPage",location="../campusRecruit/other/talentPool/addTalent.jsp"),
	@Result(name="toAddTalentsPage",location="../campusRecruit/other/talentPool/addTalents.jsp"),
	@Result(name="toViewTalentPage",location="../campusRecruit/other/talentPool/viewTalent.jsp"),
	@Result(name="toEditTalentPage",location="../campusRecruit/other/talentPool/editTalent.jsp"),
	@Result(name="json",type="json",params={"root","resultMap"})
})
public class TalentPoolAction extends BaseAction implements ModelDriven<TalentVo> {
	private static final long serialVersionUID = 1L;
	private TalentVo talentVo;
	private FileUploadInfoVo photo,resume;
	@Override
	public TalentVo getModel() {
		if (talentVo == null) {
			talentVo = new TalentVo();
		}
		return talentVo;
	}
	
	public TalentVo getTalentVo() {
		return talentVo;
	}

	public void setTalentVo(TalentVo talentVo) {
		this.talentVo = talentVo;
	}

	public FileUploadInfoVo getPhoto() {
		return photo;
	}
	public void setPhoto(FileUploadInfoVo photo) {
		this.photo = photo;
	}
	public FileUploadInfoVo getResume() {
		return resume;
	}
	public void setResume(FileUploadInfoVo resume) {
		this.resume = resume;
	}

	@Resource
	private TalentPoolService talentPoolService;
	private HashMap<String, Object> resultMap;
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
	 * ==========================================================================
	 */
	/**
	 * 跳转人才管理界面
	 * 2016年3月24日 Seven
	 */
	public String toTalentManagePage(){
		return "toTalentManagePage";
	}
	/**
	 * 分页查找人才资源
	 * 2016年3月24日 Seven
	 * @throws Exception 
	 */
	public String findTalentsPage() throws Exception{
	    BtTableUtil btTableUtil = new BtTableUtil();
	    Map<String,Object> dataMap = btTableUtil.getParamers(this.httpRequest);
	    BtTableVo<TalentVo> btTableVo= talentPoolService.findTalentList(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", btTableVo);
	    resultMap=rtn.getReturnMap();
	    return "json";
	}
	/**
	 * 获取大学下拉列表
	 * 2016年3月25日 Seven
	 */
	public String getUnivSelect()throws Exception{
		List<SelectVo> list=talentPoolService.getUnivSelect();
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("options", list);
	    resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 获取大学下学院下拉列表
	 * 2016年3月25日 Seven
	 */
	public String getCollegeSelect()throws Exception{
		Map<String,Object> reqParam = new HashMap<String,Object>();
		reqParam.put("univ_id", this.httpRequest.getParameter("univ_id"));
		List<SelectVo> list=talentPoolService.getCollegeSelect(reqParam);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("options", list);
	    resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 根据id获取人才信息
	 * 2016年3月28日 Seven
	 */
	public String getTalentById()throws Exception{
		System.out.println("查找的Talent ID为："+talentVo.getId());
		talentVo=talentPoolService.getTalentById(talentVo.getId());
		return "json";
	}
	/**
	 * 跳转新增人才界面
	 * 2016年3月28日 Seven
	 */
	public String toAddTalentPage()throws Exception{
		return "toAddTalentPage";
	}
	/**
	 * 跳转新增人才界面(单独菜单)
	 * 2016年4月21日 Seven
	 */
	public String toAddTalentsPage()throws Exception{
		return "toAddTalentsPage";
	}
	/**
	 * 添加人才信息(除简历&头像url)
	 * 2016年3月28日 Seven
	 */
	public String addTalentInfo()throws Exception{
		System.out.println(talentVo.toString());
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		talentVo.setUsername(talentVo.getMobile());
		talentVo.setPassword(talentVo.getMobile().substring(5, 11));
		talentVo.setCreate_time(DateTimeUtil.nowToDatabase());
		talentVo.setCreate_person(user.getName());
		talentVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		talentVo.setUpdate_person(user.getName());
		talentVo.setBirthday(talentVo.getBirthday().replaceAll("-", ""));
		talentPoolService.addTalentInfo(talentVo);
		log.info("添加人才成功!其ID："+talentVo.getId());
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("id", talentVo.getId());
	    resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 上传配置头像
	 * 2016年3月28日 Seven
	 */
	public String uploadPhoto() throws Exception{
		AjaxReturnInfo rtn=null;
		UploadFileUtil uploadUtil=new UploadFileUtil();
		//判断是否空传
		if(photo==null){
			rtn=AjaxReturnInfo.failed("need_file");//请选择一张图片上传！
		}else{
			if(photo.getFile().length()/1024>500){
				rtn=AjaxReturnInfo.failed("large_file");//文件过大，请不要超过500KB
			}else{
				boolean success=true;
				HashMap<String,Object> fileResultmap;
				StringBuffer failMessage=new StringBuffer();
				
				log.info("Upload File Info(Photo):"+photo.getFileFileName()+" size:"+photo.getFile().length()/1024+"KB type:"+photo.getFileContentType());
				photo=uploadUtil.getUploadFileInfo(photo, this.httpRequest);
				fileResultmap=uploadUtil.getReturnMap(photo,this.httpRequest);
				if("false".equals((String)fileResultmap.get("success"))){ 
					success=false;
					failMessage.append((String)fileResultmap.get("message"));
				}else{
					FileUtils.copyFile(photo.getFile(), photo.getFileTarget());
				}
				if(success){
					log.info("人才ID:"+photo.getOtherParam());
					String talentId=photo.getOtherParam();
					//删除原来头像--可以改成异步来做
					talentVo=talentPoolService.getTalentById(Integer.parseInt(talentId));
					UploadFileUtil delUtil=new UploadFileUtil();
					delUtil.delFile(talentVo.getPhoto_url());
					log.info("原来头像文件删除成功!");
					//记录头像url至数据库
					talentPoolService.updatePhotoInfoByID(talentId,((UserVo)this.session.get(SystemConstant.USER)).getName(),photo);
					rtn=AjaxReturnInfo.success("");
					log.info("头像配置成功!");
				}else{//否则返回失败信息
					rtn=AjaxReturnInfo.failed(""+failMessage.toString());
					log.info("上传失败!"+failMessage.toString());
				}
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 上传配置简历
	 * 2016年3月28日 Seven
	 */
	public String uploadResume() throws Exception{
		AjaxReturnInfo rtn=null;
		UploadFileUtil uploadUtil=new UploadFileUtil();
		//判断是否空传
		if(resume==null){
			rtn=AjaxReturnInfo.failed("need_file");//请选择简历附件上传！
		}else{
			if(resume.getFile().length()/1024>8000){
				rtn=AjaxReturnInfo.failed("large_file");//文件过大，请不要超过8MB！
			}else{
				boolean success=true;
				HashMap<String,Object> fileResultmap;
				StringBuffer failMessage=new StringBuffer();
				
				log.info("Upload File Info(resume):"+resume.getFileFileName()+" size:"+resume.getFile().length()/1024+"KB type:"+resume.getFileContentType());
				resume=uploadUtil.getUploadFileInfo(resume, this.httpRequest);
				fileResultmap=uploadUtil.getReturnMap(resume,this.httpRequest);
				if("false".equals((String)fileResultmap.get("success"))){ 
					success=false;
					failMessage.append((String)fileResultmap.get("message"));
				}else{
					FileUtils.copyFile(resume.getFile(), resume.getFileTarget());
				}
				if(success){
					log.info("人才ID:"+resume.getOtherParam());
					String talentId=resume.getOtherParam();
					//删除原来简历附件--可以改成异步来做
					talentVo=talentPoolService.getTalentById(Integer.parseInt(talentId));
					UploadFileUtil delUtil=new UploadFileUtil();
					delUtil.delFile(talentVo.getResume_url());
					log.info("原来简历文件删除成功!");
					//记录简历url至数据库
					talentPoolService.updateResumeInfoByID(talentId,((UserVo)this.session.get(SystemConstant.USER)).getName(),resume);
					rtn=AjaxReturnInfo.success("");
					log.info("简历配置成功!");
				}else{//否则返回失败信息
					rtn=AjaxReturnInfo.failed(""+failMessage.toString());
					log.info("上传失败!"+failMessage.toString());
				}
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转查看人才信息界面
	 * 2016年4月11日 Seven
	 * @throws Exception 
	 */
	public String toViewTalentPage() throws Exception{
		System.out.println("查看的Talent ID为："+talentVo.getId());
		talentVo=talentPoolService.viewTalentById(talentVo.getId());
		String resume=talentVo.getResume_url();
		if(resume!=null && !"".equals(resume)){
			resume=resume.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
			resume=resume.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
			talentVo.setResume_url(resume.substring(resume.lastIndexOf(File.separator)+1));
		}
		return "toViewTalentPage";
	}
	/**
	 * 根据用户ID读取用户头像
	 * 2016年4月11日 Seven
	 */
	public void readTalentPhoto() throws Exception{
		System.out.println("读取头像的Talent ID为："+talentVo.getId());
		talentVo=talentPoolService.viewTalentById(talentVo.getId());
		String photo=talentVo.getPhoto_url();
		if(photo==null || "".equals(photo)){//无头像
			print("${pageContext.request.contextPath}/common/image/user2-160x160.jpg");
		}else{
			FileInputStream inputStream =null;
			OutputStream toClient = null;
			try{
				inputStream = new FileInputStream(photo);//以byte流的方式打开文件
				this.httpResponse.setContentType("image/*"); // 设置返回的文件类型
				toClient = this.httpResponse.getOutputStream(); // 得到向客户端输出二进制数据的对象
				int len;
				byte[] bs = new byte[1024];
				while ((len = inputStream.read(bs)) != -1) {
					toClient.write(bs, 0, len);
				}
				toClient.flush();
			}catch(Exception e){
				e.printStackTrace();
				log.info("读取头像文件出错\n"+StringUtil.getStackTrace(e));
				print("${pageContext.request.contextPath}/common/image/user2-160x160.jpg");
			}finally{
				inputStream.close();
				toClient.close();
			}
		}
	}
	/**
	 * 根据ID，下载此人的简历
	 * 2016年4月11日 Seven
	 */
	public void downTalentResume() throws Exception{
		System.out.println("下载简历的Talent ID为："+talentVo.getId());
		talentVo=talentPoolService.viewTalentById(talentVo.getId());
		String resume=talentVo.getResume_url();
		if(resume!=null && !"".equals(resume)){//存在简历
			resume=resume.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
			resume=resume.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
			String fileName=resume.substring(resume.lastIndexOf(File.separator)+1);
			File file = new File(resume);
			//下载
			DownloadFileUtil downLoad=new DownloadFileUtil();
			if (file.exists()) {
				log.info("文件下载中...");
				downLoad.downLoad(fileName, resume, this.httpRequest, this.httpResponse);
				log.info("文件下载成功");
			}else{
				log.info("下载失败：文件不存在");
			}
			
		}
	}
	/**
	 * 移除简历附件
	 * 2016年7月7日 Seven
	 */
	public String removeResume() throws Exception{
		AjaxReturnInfo rtn=null;
		//删除原来简历附件--可以改成异步来做
		talentVo=talentPoolService.getTalentById(talentVo.getId());
		UploadFileUtil delUtil=new UploadFileUtil();
		delUtil.delFile(talentVo.getResume_url());
		log.info("简历文件删除成功!");
		//记录简历url至数据库
		talentPoolService.updateResumeInfoByID(talentVo.getId(), ((UserVo)this.session.get(SystemConstant.USER)).getName(), "");
		rtn=AjaxReturnInfo.success("");
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转修改人才信息界面
	 * 2016年4月11日 Seven
	 */
	public String toEditTalentPage() throws Exception{
		System.out.println("修改的Talent ID为："+talentVo.getId());
		talentVo=talentPoolService.getTalentById(talentVo.getId());
		String resume=talentVo.getResume_url();
		if(resume!=null && !"".equals(resume)){
			resume=resume.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
			resume=resume.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
			talentVo.setResume_url(resume.substring(resume.lastIndexOf(File.separator)+1));
		}
		return "toEditTalentPage";
	}
	/**
	 * 更新人才信息
	 * 2016年4月13日 Seven
	 */
	public String updateTalentInfo() throws Exception{
		System.out.println("更新人才信息："+talentVo.toString());
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		talentVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		talentVo.setUpdate_person(user.getName());
		talentVo.setBirthday(talentVo.getBirthday().replaceAll("-", ""));
		int t=talentPoolService.updateTalentInfo(talentVo);
		AjaxReturnInfo rtn;
		if(t==1){
			rtn=AjaxReturnInfo.success("1");
			log.info("修改人才信息成功!");
		}else{
			rtn=AjaxReturnInfo.success("0");
			log.info("修改人才信息失败!");
		}
	    resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 删除人才信息	
	 * 2016年4月14日 Seven
	 */
	public String deleteTalentInfo() throws Exception{
		//使用name来接收前端传来的id（String）
		System.out.println("删除人才信息ID："+talentVo.getName());
		String [] ids=talentVo.getName().split(",");
		AjaxReturnInfo rtn;
		if(ids.length==0){
			rtn=AjaxReturnInfo.success("-1");//未选择删除人员
		}else{
			Map<String,Object> param=new HashMap<String,Object>();
			UserVo user=(UserVo)this.session.get(SystemConstant.USER);
			param.put("ids", ids);
			param.put("update_person", user.getName());
			param.put("update_time", DateTimeUtil.nowToDatabase());
			int t=talentPoolService.deleteTalentInfo(param);
			if(t<=0){
				rtn=AjaxReturnInfo.success("0");//删除失败
			}else{
				rtn=AjaxReturnInfo.success("1");//删除成功
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * 下载人才相关
	 * @throws Exception 
	 */
	public void downTalentData() throws Exception{
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String filePath= baseDir+"Talent Data-"+DateTimeUtil.getNow()+".xls";//临时存储位置
		log.info("下载人才信息：文件临时存放\n"+filePath);
		List<Map<String,Object>> dataList=getTalentData();//获取数据
		File file = new File(filePath);
		//生成Excel
		ExcelUtil excelUtil=new ExcelUtil();
		String [] columnName={"name","spell_name","gender","age","birthday","cardno",
				"mobile","email","degree","graduate_month","work_location","work_loc_comment",
				"proj_group","proj_group_comment","univ_name","univ_comment","college_name","college_comment","major","major_comment",
				"english_level","talent_source","recruit_state","state_comment","position","position_comment","native_place_prov",
				"native_place_city","native_place_comment","in_company_time","in_proj_time","leave_time","employmt_agreemt","remark"};
		String lang=(String) this.session.get(SystemConstant.LANG);
		HSSFWorkbook wb;
		if(lang==null || "zh_CN".equals(lang)){
			String [] columnAlignName={"中文名","姓名拼音","性别","年龄","出生日期","身份证号",
					"手机号","电子邮件","学历","毕业时间","工作地点","工作地点备注","项目组","项目组备注","学校名称",
					"学校备注","学院名称","学院备注","专业","专业备注","英语等级","人才来源","招聘状态","招聘状态说明","岗位","岗位备注","户籍省份",
					"户籍城市","户籍备注","入职时间","入职项目组时间","离职时间","就业协议","备注"};
			wb=excelUtil.getHSSFWookbook(dataList, columnName, columnAlignName, "Talent Info");
		}
		else{
			String [] columnAlignName={"Name","Spell Name","Gender","Age","Birthday","Card No",
					"Mobile","Email","Degree","Graduate Month","Work Location",
					"Work Loc Comment","Proj Group","Proj Group Comment","Univ Name","Univ Comment","College Name",
					"College Comment","Major","Major Comment","English Level","Talent Source","Recruit State","State Comment",
					"Position","Position Comment","Native Place Prov","Native Place City","Native Place Comment","In Company Time",
					"In Proj Time","Leave Time","Employmt Agreemt","Remark"};
			wb=excelUtil.getHSSFWookbook(dataList, columnName, columnAlignName, "Talent Info");
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
			downLoad.downLoad("Talent Info.xls", filePath, "application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
	}
	//
	public List<Map<String,Object>> getTalentData() throws Exception{
		BtTableUtil util=new BtTableUtil();
		Map<String,Object> paramMap=util.getParamers(httpRequest);
		List<Map<String,Object>> list=this.talentPoolService.getTalentData(paramMap);
		return list;
	} 
}
