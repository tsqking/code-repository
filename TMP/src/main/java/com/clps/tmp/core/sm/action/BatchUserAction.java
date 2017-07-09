package com.clps.tmp.core.sm.action;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.OptionService;
import com.clps.tmp.core.sm.service.UserService;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * 批量用户管理action
  * @ClassName: BatchUserAction
  * @Description: TODO
  * @author Seven
  * @date 2015年12月2日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/system")
@Controller
@Scope("prototype")
@Action("userbatch")
@Results({ @Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class BatchUserAction extends BaseAction {
	//上传文件
	private FileUploadInfoVo file;
	// json返回数据map
	private HashMap<String, Object> resultMap;
	//业务处理Service
	@Resource
	private UserService userService;
	@Resource
	private OptionService optionService;
	
	public FileUploadInfoVo getFile() {
		return file;
	}
	public void setFile(FileUploadInfoVo file) {
		this.file = file;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
	 * 下载批量添加人员模板(用户管理)
	 * 2015年11月27日 Seven
	 * @throws Exception 
	 */
	public void downTemplate() throws Exception{
		DownloadFileUtil downLoad=new DownloadFileUtil();
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"System"+File.separator+"Template"+File.separator;
		String filePath= baseDir+"User Info Template.xls";
		File file = new File(filePath);
		log.info("下载User Info Template.xls");
		if (file.exists()) {
			downLoad.downLoad("User Info Template.xls", filePath, "application/vnd.ms-excel", this.httpRequest, this.httpResponse);
		}else{
			log.info("User Info Template.xls不存在");
			filePath= baseDir+"User Info Template.xlsx";
			File file1 = new File(filePath);
			log.info("尝试下载User Info Template.xlsx");
			if (file1.exists()) {
				downLoad.downLoad("User Info Template.xlsx", filePath, "application/x-7z-compressed,application/x-ppt,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", this.httpRequest, this.httpResponse);
			}else{
				log.info("User Info Template.xlsx也不存在");
			}
		}
	}
	/**
	 * 批量添加用户(用户管理)
	 * 2015年12月2日 Seven
	 */
	public String batchAddUser() throws Exception{//判断是否空传
		AjaxReturnInfo rtn=null;
		boolean upSuccess=true;
		UploadFileUtil uploadUtil=new UploadFileUtil();
		String lang=(String) session.get(SystemConstant.LANG);
		if(file==null){//判断是否空传
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"请至少选择一个文件上传!":"Please choose a file!");
			upSuccess=false;
		}else{
			log.info(file.getFileFileName()+" "+file.getFileContentType());
			if(!file.getFileFileName().endsWith(".xls") && !file.getFileFileName().endsWith(".xlsx")){//判断是否传的excel
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"请确认上传文件!":"Please confirm the type of file!");
				upSuccess=false;
			}else{
				HashMap<String,Object> fileInfoResultMap=new HashMap<String,Object>();
				//获取上传文件信息
				file=uploadUtil.getUploadFileInfo(file, this.httpRequest);
				fileInfoResultMap=uploadUtil.getReturnMap(file,this.httpRequest);
				//判断上传文件是否符合规则
				if("false".equals((String)fileInfoResultMap.get("success"))){
					rtn=AjaxReturnInfo.failed((String)fileInfoResultMap.get("message"));
					upSuccess=false;
				}else{//上传到服务器指定路径下
					FileUtils.copyFile(file.getFile(), file.getFileTarget());
				}
			}
		}
		//上传成功，业务处理
		if(upSuccess){
			ExcelUtil excel=new ExcelUtil(2);
			String[] columnName=new String[]{"cardtype","cardno","name","en_name","gender","mobile","phone","exam_num","email","age","birthday",
					"education_background","degree","university","college","major","cet4","cet6","gpa","contact_address","contact_postcode",
					"home_address","home_postcode"};
			//读取excel进入list中
			List<Map<String,Object>> list=excel.readExcel(file.getFilePath(), "UserList", columnName);
			
			boolean illegal=false;
			int i=0;
			HashSet<String> mobileSet=new HashSet<String>();//手机号不能重复检测
			//遍历excel记录，进行基本验证
			for(Map<String,Object> map:list){
				mobileSet.add((String)map.get("mobile"));
				//人员其他信息填写
				map.put("exist", false);//初始化存在标志位
				map.put("username", (String)map.get("mobile"));//暂先手机号
				map.put("role", "3");//设定角色
				map.put("enable", "T");//设定生效标识
				map.put("create_time", DateTimeUtil.nowToDatabase());//创建时间
				map.put("create_person", ((UserVo)session.get(SystemConstant.USER)).getUsername());//创建人
				map.put("update_time", DateTimeUtil.nowToDatabase());//更新时间
				map.put("update_person", ((UserVo)session.get(SystemConstant.USER)).getUsername());//更新人
				i++;
				if("".equals(map.get("cardtype")) || "".equals(map.get("cardno")) || "".equals(map.get("name")) || "".equals(map.get("en_name"))
						|| "".equals(map.get("gender")) || "".equals(map.get("mobile")) || "".equals(map.get("email")) ){
					rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("第"+(i+2)+"行有背景色的列头必须填写内容~"):("Please input the info in column which have the background color title on the "+(i+2)+" row~"));
					illegal=true;
					break;
				}
			}
			if(i>mobileSet.size()){
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"手机号码不能重复~":"Mobile duplicate~");
				illegal=true;
			}
			if(!illegal){
				//枚举数据调整
				//获取枚举数据
				List<Map<String,Object>> cardTypeListMap=optionService.getOptionMapByGPVal("CARD_TYPE");
				List<Map<String,Object>> genderListMap=optionService.getOptionMapByGPVal("SEX");
				List<Map<String,Object>> eduListMap=optionService.getOptionMapByGPVal("EDU_BAK");
				List<Map<String,Object>> degreeListMap=optionService.getOptionMapByGPVal("DEGREE");
				//遍历人员调整枚举数据
				for(Map<String,Object> map:list){
					for(Map<String,Object> cardTypeMap:cardTypeListMap){
						if(cardTypeMap.get("text").equals(map.get("cardtype"))){
							map.put("cardtype", cardTypeMap.get("id"));
							break;
						}
					}
					for(Map<String,Object> genderMap:genderListMap){
						if(genderMap.get("text").equals(map.get("gender"))){
							map.put("gender", genderMap.get("id"));
							break;
						}
					}
					for(Map<String,Object> eduMap:eduListMap){
						if(eduMap.get("text").equals(map.get("education_background"))){
							map.put("education_background", eduMap.get("id"));
							break;
						}
					}
					for(Map<String,Object> degreeMap:degreeListMap){
						if(degreeMap.get("text").equals(map.get("degree"))){
							map.put("degree", degreeMap.get("id"));
							break;
						}
					}
				}
				//调用业务批量添加操作
				HashMap<String,Object> batchAddRtn=userService.batchAddUser(list);
				rtn=AjaxReturnInfo.success("");
				String rtnMsg=null;
				if(!"".equals((String)batchAddRtn.get("existMobile"))){
					if("zh_CN".equals(lang)){
						rtnMsg="账号建立成功！以下手机用户已存在系统账号:<br>"+batchAddRtn.get("existMobile")
								+ (("".equals((String)batchAddRtn.get("userNameList")))?"":("<br>请谨记以下新添的用户名称，方便为新用户添加角色(基本角色已添加)：<br>"+(String)batchAddRtn.get("userNameList")));
					}else{
						rtnMsg="Account set up success! Bellow mobile exist system account:<br>"+batchAddRtn.get("existMobile")
								+ (("".equals((String)batchAddRtn.get("userNameList")))?"":("<br>Please record the account name bellow, then use this to link the role(Already have base role):<br>"+(String)batchAddRtn.get("userNameList")));
					}
					log.info(rtnMsg);
					rtn.add("message", rtnMsg);
				}else{
					if("zh_CN".equals(lang)){
						rtnMsg="账号建立成功！<br>"
								+ "<br>请谨记以下新添的用户名称，方便为新用户添加角色(基本角色已添加)：<br>"+(String)batchAddRtn.get("userNameList");
						
					}else{
						rtnMsg="Account set up success!<br>"
								+ "<br>Please record the account name bellow, then use this to link the role(Already have base role):<br>"+(String)batchAddRtn.get("userNameList");;
					}
					log.info(rtnMsg);
					rtn.add("message",rtnMsg);
				}
			}
			//删除临时附件
			uploadUtil.delFile(file.getFilePath());
		}
		//json返回
		resultMap=rtn.getReturnMap();
		return "json";
	}
}
