package com.clps.tmp.tech.point.action;
import java.io.File;
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

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DataTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;
import com.clps.tmp.tech.point.service.PointService;
import com.clps.tmp.tech.point.vo.DetailVo;
import com.clps.tmp.tech.point.vo.PointVo;

/**
 * 知识点管理
 * @author Seven
 *
 * 2015年11月3日
 */
@SuppressWarnings({ "serial"})
@ParentPackage("publicPackage")
@Namespace("/tech")
@Controller
@Scope("prototype")   
@Action("point")
@Results({ @Result(name = "toPointManagePage", location = "point/pointManage.jsp"),
		@Result(name = "toAddNewPointPage", location = "point/addNewPoint.jsp"),
		@Result(name = "toViewPointPage", location = "point/viewPoint.jsp"),
		@Result(name = "toEditPointPage", location = "point/editPoint.jsp"),
		@Result(name = "toCopyPointPage", location = "point/copyPoint.jsp"),
        @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class PointAction extends BaseAction{
	private PointVo pointVo;//页面数据封装
	private DetailVo detailVo;//页面数据封装
	private FileUploadInfoVo file1,file2,file3,file4;//文件上传封类
    private HashMap<String, Object> resultMap;// json返回数据map
    @Resource
    private PointService pointService;
	public PointVo getPointVo() {
		return pointVo;
	}
	public void setPointVo(PointVo pointVo) {
		this.pointVo = pointVo;
	}
	public DetailVo getDetailVo() {
		return detailVo;
	}
	public void setDetailVo(DetailVo detailVo) {
		this.detailVo = detailVo;
	}
	public FileUploadInfoVo getFile1() {
		return file1;
	}
	public void setFile1(FileUploadInfoVo file1) {
		this.file1 = file1;
	}
	public FileUploadInfoVo getFile2() {
		return file2;
	}
	public void setFile2(FileUploadInfoVo file2) {
		this.file2 = file2;
	}
	public FileUploadInfoVo getFile3() {
		return file3;
	}
	public void setFile3(FileUploadInfoVo file3) {
		this.file3 = file3;
	}
	public FileUploadInfoVo getFile4() {
		return file4;
	}
	public void setFile4(FileUploadInfoVo file4) {
		this.file4 = file4;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
    /**
     * 跳转到知识点管理界面
     * @return
     */
	public String toPointManagePage() throws Exception{
		return "toPointManagePage";
	}
	/**
	 * 获取知识点分页内容
	 * @throws Exception 
	 */
	public String getPointPage() throws Exception{
		DataTableUtil<PointVo> dataTableUtil=new DataTableUtil<PointVo>();
		PageVo<PointVo> pageVo=dataTableUtil.getTableData(this.httpRequest);
		if(pointVo==null) pointVo=new PointVo();//若为modeldriven方式 可省略
		//1.搜索条件
		//1.1.精确查询
		HashMap<String, String> where1 = new HashMap<String, String>();
		if(pointVo.getEnable()!=null && !pointVo.getEnable().equals("")){
			where1.put("f.`value`", pointVo.getEnable());
		}
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
		//1.2.模糊查询
		HashMap<String, String> where2 = new HashMap<String, String>();
		if(pointVo.getName()!=null && !pointVo.getName().equals("")){
			where2.put("a.`name[lang]`", pointVo.getName());
		}
		pageVo.setWhere2(where2);
		//3.获取数据
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);//用于判断是否具有管理知识点的权限
		pageVo = pointService.queryPointPage(pageVo,user.getId(),user.getRole());
		//4.返回值
		resultMap = dataTableUtil.getReturnMap(pageVo);
		return "json";
	}
	/**
	 * 跳转知识点添加界面
	 */
	public String toAddNewPointPage() throws Exception{
		return "toAddNewPointPage";
	}
	/**
	 * 增加知识点
	 */
	public String addPoint() throws Exception{
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		pointVo.setCreate_person(user.getName());
		pointVo.setCreate_time(DateTimeUtil.nowToDatabase());
		pointVo.setUpdate_person(user.getName());
		pointVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		log.info("添加知识点（简要信息）:"+pointVo.toString());
		HashMap<String,Object> result=pointService.addPoint(pointVo);
		
		detailVo.setCreate_person(user.getName());
		detailVo.setCreate_time(DateTimeUtil.nowToDatabase());
		detailVo.setUpdate_person(user.getName());
		detailVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		detailVo.setPoint_id((String)result.get("point_id"));
		log.info("添加知识点（资源详细）:"+detailVo.toString());
		pointService.addPointDetail(detailVo);
		
		String role=user.getRole();
		if("2".equals(role)){//讲师添加知识点，则其被授权管理此知识点
			pointService.grantManageToTeacher(user.getId(),detailVo.getPoint_id(),"Y");
		}
		
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("point_id", result.get("point_id"));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 上传
	 */
	public String upload() throws Exception{
		AjaxReturnInfo rtn=null;
		UploadFileUtil uploadUtil=new UploadFileUtil();
		//判断是否空传
		if(file1==null && file2==null && file3==null && file4==null){
			rtn=AjaxReturnInfo.failed("need_file");//请至少选择一个文件上传！
		}else{
			boolean typeSuccess=true;
			if(file1!=null){
				if(!file1.getFileFileName().endsWith(".pdf")){
					rtn=AjaxReturnInfo.failed("only_pdf");//知识点附件只支持pdf文件！请转换为pdf重新上传！
					typeSuccess=false;
				}
			}
			if(file2!=null){
				if(!file2.getFileFileName().endsWith(".pdf")){
					rtn=AjaxReturnInfo.failed("only_pdf");
					typeSuccess=false;
				}
			}
			if(file3!=null){
				if(!file3.getFileFileName().endsWith(".pdf")){
					rtn=AjaxReturnInfo.failed("only_pdf");
					typeSuccess=false;
				}
			}
			if(file4!=null){
				if(!file4.getFileFileName().endsWith(".pdf")){
					rtn=AjaxReturnInfo.failed("only_pdf");
					typeSuccess=false;
				}
			}
			if(typeSuccess){
				boolean success1=true,success2=true,success3=true,success4=true;
				HashMap<String,Object> map1,map2,map3,map4;
				StringBuffer failMessage=new StringBuffer();
				//如果有文件,获取其上传文件信息,并判断是否符合系统要求,符合的则上传
				if(file1!=null){
					log.info("Upload File Info(File1):"+file1.getFileFileName()+" size:"+file1.getFile().length()/1024+"KB type:"+file1.getFileContentType());
					file1=uploadUtil.getUploadFileInfo(file1, this.httpRequest);
					map1=uploadUtil.getReturnMap(file1,this.httpRequest);
					if("false".equals((String)map1.get("success"))){ success1=false;failMessage.append((String)map1.get("message"));}
					else{FileUtils.copyFile(file1.getFile(), file1.getFileTarget());}
				}
				if(file2!=null){
					log.info("Upload File Info(File2):"+file2.getFileFileName()+" size:"+file2.getFile().length()/1024+"KB type:"+file2.getFileContentType());
					file2=uploadUtil.getUploadFileInfo(file2, this.httpRequest);
					map2=uploadUtil.getReturnMap(file2,this.httpRequest);
					if("false".equals((String)map2.get("success"))){ success2=false;failMessage.append((String)map2.get("message"));}
					else{FileUtils.copyFile(file2.getFile(), file2.getFileTarget());}
				}
				if(file3!=null){
					log.info("Upload File Info(File3):"+file3.getFileFileName()+" size:"+file3.getFile().length()/1024+"KB type:"+file3.getFileContentType());
					file3=uploadUtil.getUploadFileInfo(file3, this.httpRequest);
					map3=uploadUtil.getReturnMap(file3,this.httpRequest);
					if("false".equals((String)map3.get("success"))){ success3=false;failMessage.append((String)map3.get("message"));}
					else{FileUtils.copyFile(file3.getFile(), file3.getFileTarget());}
				}
				if(file4!=null){
					log.info("Upload File Info(File4):"+file4.getFileFileName()+" size:"+file4.getFile().length()/1024+"KB type:"+file4.getFileContentType());
					file4=uploadUtil.getUploadFileInfo(file4, this.httpRequest);
					map4=uploadUtil.getReturnMap(file4,this.httpRequest);
					if("false".equals((String)map4.get("success"))){ success4=false;failMessage.append((String)map4.get("message"));}
					else{FileUtils.copyFile(file4.getFile(), file4.getFileTarget());}
				}
				//如果上传文件信息符合要求,记录信息至数据库
				if(success1 && success2 && success3 && success4){
					//记录文件信息至数据库
					log.info("知识点ID:"+file1.getOtherParam());
					String pointId=file1.getOtherParam();
					if(file1!=null)pointService.updateMaterialInfoByID(pointId,((UserVo)this.session.get(SystemConstant.USER)).getName(),file1);
					if(file2!=null)pointService.updateTHandBookInfoByID(pointId,((UserVo)this.session.get(SystemConstant.USER)).getName(),file2);
					if(file3!=null)pointService.updateSHandBookInfoByID(pointId,((UserVo)this.session.get(SystemConstant.USER)).getName(),file3);
					if(file4!=null)pointService.updateReferenceInfoByID(pointId,((UserVo)this.session.get(SystemConstant.USER)).getName(),file4);
					rtn=AjaxReturnInfo.success("");
					log.info("上传成功!");
				}else{//否则返回失败信息,删除已经上传的文件
					if(file1!=null && success1)uploadUtil.delFile(file1.getFilePath());
					if(file2!=null && success2)uploadUtil.delFile(file2.getFilePath());
					if(file3!=null && success3)uploadUtil.delFile(file3.getFilePath());
					if(file4!=null && success4)uploadUtil.delFile(file4.getFilePath());
					rtn=AjaxReturnInfo.failed("上传失败!"+failMessage.toString());
					log.info("上传失败!"+failMessage.toString());
				}
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 查看知识点
	 */
	public String toViewPointPage() throws Exception{
		log.info("查看详细的知识点ID:"+pointVo.getId());
		pointVo=pointService.getPointInfoByID(pointVo.getId());
		detailVo=pointService.getDetailInfoByID(pointVo.getId());
		return "toViewPointPage";
	}
	/**
	 * 下载
	 * @throws Exception 
	 */
	public void getDown() throws Exception{
		String type=detailVo.getDescription();
		log.info("下载知识点ID:"+detailVo.getPoint_id()+"的"+type);
		detailVo=pointService.getDetailInfoByID(detailVo.getPoint_id());
		if(type.equals("material")){
			String fileName=detailVo.getMaterial_file_name();
			String filePath=detailVo.getMaterial_dir();
			String fileType=detailVo.getMaterial_content_type();
			DownloadFileUtil downLoad=new DownloadFileUtil();
			downLoad.downLoad(fileName, filePath, fileType, this.httpRequest, this.httpResponse);
		}
		if(type.equals("t_handbook")){
			String fileName=detailVo.getT_handbook_file_name();
			String filePath=detailVo.getT_handbook_dir();
			String fileType=detailVo.getT_handbook_content_type();
			DownloadFileUtil downLoad=new DownloadFileUtil();
			downLoad.downLoad(fileName, filePath, fileType, this.httpRequest, this.httpResponse);
		}
		if(type.equals("s_handbook")){
			String fileName=detailVo.getS_handbook_file_name();
			String filePath=detailVo.getS_handbook_dir();
			String fileType=detailVo.getS_handbook_content_type();
			DownloadFileUtil downLoad=new DownloadFileUtil();
			downLoad.downLoad(fileName, filePath, fileType, this.httpRequest, this.httpResponse);
		}
		if(type.equals("reference")){
			String fileName=detailVo.getReference_file_name();
			String filePath=detailVo.getReference_dir();
			String fileType=detailVo.getReference_content_type();
			DownloadFileUtil downLoad=new DownloadFileUtil();
			downLoad.downLoad(fileName, filePath, fileType, this.httpRequest, this.httpResponse);
		}
	}
	/**
	 * 编辑知识点
	 */
	public String toEditPointPage() throws Exception{
		log.info("查看详细的知识点ID:"+pointVo.getId());
		pointVo=pointService.getPointInfoByID(pointVo.getId());
		detailVo=pointService.getDetailInfoByID(pointVo.getId());
		return "toEditPointPage";
	}
	/**
	 * 更新附件
	 * @throws Exception 
	 */
	public String updateAttachFile() throws Exception{
		AjaxReturnInfo rtn=null;
		HashMap<String,Object> map;
		UploadFileUtil uploadUtil=new UploadFileUtil();//文件上传工具类
		//判断是否空传
		if(file1==null){
			rtn=AjaxReturnInfo.failed("need_file");
		}else{
			boolean typeSuccess=true;
			if(file1!=null){
				if(!file1.getFileFileName().endsWith(".pdf")){
					rtn=AjaxReturnInfo.failed("only_pdf");//知识点附件只支持pdf文件！请转换为pdf重新上传！
					typeSuccess=false;
				}
			}
			if(typeSuccess){
				file1=uploadUtil.getUploadFileInfo(file1, this.httpRequest);//获取上传文件信息
				
				map=uploadUtil.getReturnMap(file1,this.httpRequest);//获取上传文件系统判定结果
				String[] paramInfo=file1.getOtherParam().split("\\*\\*and\\*\\*");//获取参数
				String pointId=paramInfo[0];
				String updateTime=paramInfo[1];
				String type=paramInfo[2];
				DetailVo detail=pointService.getDetailInfoByID(pointId);//查询数据库中此ID号的DetailVo信息
				log.info("更新附件信息：知识点-"+pointId+" 更新"+type+"文件");
				
				if(!detail.getUpdate_time().equals(updateTime)){//数据库更新时间不符，说明已经被更改了
					rtn=AjaxReturnInfo.failed("been_update_when_upload");
					log.info("上传失败，信息已经被更改，请查看重试~");
				}else{
					if("false".equals((String)map.get("success"))){//不符合规则
						rtn=AjaxReturnInfo.failed((String)map.get("message"));
					}else{
						FileUtils.copyFile(file1.getFile(), file1.getFileTarget());//上传
						rtn=AjaxReturnInfo.success((String)map.get("message"));
						Map<String,Object> updateRtnMap=null;
						if("material".equals(type)){
							uploadUtil.delFile(detail.getMaterial_dir());//删除原文件
							updateRtnMap=pointService.updateMaterialInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), file1);//更新数据库信息
						}else if("t_handbook".equals(type)){
							uploadUtil.delFile(detail.getT_handbook_dir());//删除原文件
							updateRtnMap=pointService.updateTHandBookInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), file1);//更新数据库信息
						}else if("s_handbook".equals(type)){
							uploadUtil.delFile(detail.getS_handbook_dir());//删除原文件
							updateRtnMap=pointService.updateSHandBookInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), file1);//更新数据库信息
						}else if("reference".equals(type)){
							uploadUtil.delFile(detail.getReference_dir());//删除原文件
							updateRtnMap=pointService.updateReferenceInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), file1);//更新数据库信息
						}
						//detail=pointService.getDetailInfoByID(pointId);
						rtn.add("update_time", updateRtnMap.get("update_time"));
						rtn.add("update_person", updateRtnMap.get("update_person"));
						rtn.add("file_name", file1.getFileFileName());
						rtn.add("file_size", file1.getFileSize());
					}
				}
			}
			
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 删除附件
	 * @throws Exception 
	 */
	public String deleteAttachFile() throws Exception{
		String pointId=detailVo.getPoint_id();
		String type=detailVo.getDescription();
		log.info("传来删除"+type+"附件的知识点ID:"+pointId);
		DetailVo detail=pointService.getDetailInfoByID(pointId);
		UploadFileUtil uploadUtil=new UploadFileUtil();
		Map<String,Object> updateRtnMap=null;
		if("material".equals(type)){
			updateRtnMap=pointService.updateMaterialInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), null);//更新数据库记录
			uploadUtil.delFile(detail.getMaterial_dir());
		}else if("t_handbook".equals(type)){
			updateRtnMap=pointService.updateTHandBookInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), null);//更新数据库记录
			uploadUtil.delFile(detail.getT_handbook_dir());
		}else if("s_handbook".equals(type)){
			updateRtnMap=pointService.updateSHandBookInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), null);//更新数据库记录
			uploadUtil.delFile(detail.getS_handbook_dir());
		}else if("reference".equals(type)){
			updateRtnMap=pointService.updateReferenceInfoByID(pointId, ((UserVo)this.session.get(SystemConstant.USER)).getName(), null);//更新数据库记录
			uploadUtil.delFile(detail.getReference_dir());
		}
		//detail=pointService.getDetailInfoByID(pointId);
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("update_time", updateRtnMap.get("update_time"));
		rtn.add("update_person", updateRtnMap.get("update_person"));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 更新知识点
	 * @throws Exception 
	 */
	public String updatePoint() throws Exception{
		log.info("更新知识点信息");
		log.info(pointVo.toString()+"\n"+detailVo.toString());
		pointVo.setLast_update_time(pointVo.getUpdate_time());
		pointVo.setLast_update_person(pointVo.getUpdate_person());
		pointVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		pointVo.setUpdate_person(((UserVo)this.session.get(SystemConstant.USER)).getName());
		detailVo.setLast_update_time(detailVo.getUpdate_time());
		detailVo.setLast_update_person(detailVo.getUpdate_person());
		detailVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		detailVo.setUpdate_person(((UserVo)this.session.get(SystemConstant.USER)).getName());
		int effect1=pointService.updatePointInfo(pointVo);
		int effect2=pointService.updateDetailInfo(detailVo);
		AjaxReturnInfo rtn=AjaxReturnInfo.success(String.valueOf(effect1)+String.valueOf(effect2));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 删除知识点
	 * @throws Exception 
	 */
	public String deletePoint() throws Exception{
		log.info("删除的知识点ID:"+pointVo.getId());
		DetailVo detail=pointService.getDetailInfoByID(pointVo.getId());
		AjaxReturnInfo rtn=null;
		Map<String,Object> deleteRtnMap=pointService.deletePoint(pointVo.getId());
		if("0".equals(deleteRtnMap.get("code"))){//可以删除，进一步删除附件
			//删除附件
			UploadFileUtil uploadUtil=new UploadFileUtil();
			uploadUtil.delFile(detail.getMaterial_dir());
			uploadUtil.delFile(detail.getT_handbook_dir());
			uploadUtil.delFile(detail.getS_handbook_dir());
			uploadUtil.delFile(detail.getReference_dir());
			rtn=AjaxReturnInfo.success("");
		}else{//不能删除
			rtn=AjaxReturnInfo.failed((String) deleteRtnMap.get("code"));
			if("1".equals(deleteRtnMap.get("code"))) rtn.add("inUseCourse", deleteRtnMap.get("inUseCourse"));
			if("2".equals(deleteRtnMap.get("code"))) rtn.add("inUsePlan", deleteRtnMap.get("inUsePlan"));
			if("3".equals(deleteRtnMap.get("code"))){
				rtn.add("inUseCourse", deleteRtnMap.get("inUseCourse"));
				rtn.add("inUsePlan", deleteRtnMap.get("inUsePlan"));
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 跳转复制知识点界面
	 */
	public String toCopyPointPage(){
		return "toCopyPointPage";
	}
	/**
	 * 复制知识点
	 * @throws Exception 
	 */
	public String copyPoint() throws Exception{
		log.info("被复制的知识点ID:"+pointVo.getId());
		log.info("复制目的技能: 1-"+pointVo.getFirst_skill_id()+" 2-"+pointVo.getSecond_skill_id()+" 3-"+pointVo.getThird_skill_id());
		DetailVo detail=pointService.getDetailInfoByID(pointVo.getId());
		UserVo user=(UserVo)this.session.get(SystemConstant.USER);
		pointVo.setCreate_person(user.getName());
		pointVo.setCreate_time(DateTimeUtil.nowToDatabase());
		pointVo.setUpdate_person(user.getName());
		pointVo.setUpdate_time(DateTimeUtil.nowToDatabase());
		detail.setCreate_person(user.getName());
		detail.setCreate_time(DateTimeUtil.nowToDatabase());
		detail.setUpdate_person(user.getName());
		detail.setUpdate_time(DateTimeUtil.nowToDatabase());
		String pointId=String.valueOf(pointService.copyPoint(pointVo,detail));
		if("2".equals(user.getRole())){//教师符复制了，就把权限赋予他自己
			pointService.grantManageToTeacher(user.getId(), pointId, "Y");
		}
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		resultMap=rtn.getReturnMap();
		return "json";
	}
    /**
     * 根据技能号获取知识点下拉框
     */
    public String getPointOptionBySkillIds() throws Exception {
    	List<SelectVo> pointList = pointService.getPointOptionBySkillIds(pointVo.getFirst_skill_id(), pointVo.getSecond_skill_id(), pointVo.getThird_skill_id());
    	// 返回json数据
        AjaxReturnInfo rtn = null;
        rtn = AjaxReturnInfo.success("");
        rtn.add("options", pointList);
        resultMap = rtn.getReturnMap();
        return "json";
    }
    /**
     * 下载
     * 2016年4月8日 Seven
     */
    public void downTopicData() throws Exception{
    	String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String filePath= baseDir+"Topic Data-"+DateTimeUtil.getNow()+".xls";//临时存储位置
		log.info("下载知识点文件：文件临时存放\n"+filePath);
		List<Map<String,Object>> dateList=getTopicData();//获取数据
		File file = new File(filePath);
		//生成Excel
		ExcelUtil excelUtil=new ExcelUtil();
		String [] columnName={"name","skill_type","first_skill","second_skill","third_skill","material","t_handbook","s_handbook","reference"};
		String lang=(String) this.session.get(SystemConstant.LANG);
		HSSFWorkbook wb;
		if(lang==null || "zh_CN".equals(lang)){
			String [] columnAlignName={"知识点名称","所属技能类型","所属一级技能","所属二级技能","所属三级技能","教材讲义","教师手册","学生手册","参考资料"};
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "Topic Data");
		}else{
			String [] columnAlignName={"Topic","Skill Type","First Skill","Second Skill","Third Skill","Material","Teacher Handbook","Student Handbook","Reference"};
			wb=excelUtil.getHSSFWookbook(dateList, columnName, columnAlignName, "Topic Data");
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
			downLoad.downLoad("Topic Data.xls", filePath, "application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("下载失败：文件不存在");
		}
    }
    
	//获取课程数据
  	@SuppressWarnings("unused")
  	private List<Map<String,Object>> getTopicData() throws Exception{
  		BtTableUtil util=new BtTableUtil();
  		Map<String,Object> paramMap=util.getParamers(httpRequest);
  		List<Map<String,Object>> list=this.pointService.getTopicData(paramMap);
  		return list;
  	}
}
