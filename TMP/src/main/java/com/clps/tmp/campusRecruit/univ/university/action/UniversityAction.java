package com.clps.tmp.campusRecruit.univ.university.action;

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

import com.clps.tmp.campusRecruit.univ.college.service.CollegeService;
import com.clps.tmp.campusRecruit.univ.college.vo.CollegeVo;
import com.clps.tmp.campusRecruit.univ.university.service.UniversityService;
import com.clps.tmp.campusRecruit.univ.university.vo.UniversityVo;
import com.clps.tmp.common.region.service.ChinaRegionService;
import com.clps.tmp.common.region.vo.ChinaRegionVo;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.util.DateTimeUtil;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.ExcelUtil;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.sm.constant.SystemConstant;
import com.clps.tmp.core.sm.service.OptionService;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.core.sm.vo.UserVo;

/**
 * 学校信息管理
 * 
 * @ClassName: UniversityAction
 * @Description: TODO
 * @author Wellen
 * @date 2016年3月14日
 */
@SuppressWarnings({ "serial"})
@ParentPackage("publicPackage")
@Namespace("/univ")
@Controller
@Scope("prototype")  
@Action("university")
@Results({ @Result(name = "toUniversityManagePage", location = "../campusRecruit/univ/university/universityManage.jsp"),
		@Result(name = "toAddUniversityPage", location = "../campusRecruit/univ/university/addUniversity.jsp"),
		@Result(name = "toEditUniversityPage", location = "../campusRecruit/univ/university/editUniversity.jsp"),
		@Result(name = "tobBatchAddUniversityPage", location = "../campusRecruit/univ/university/batchAddUniversity.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class UniversityAction extends BaseAction {
	// 页面属性 页面通过'xx.属性'方式
    private UniversityVo universityVo;
    private CollegeVo collegeVo;
    private ChinaRegionVo chinaRegionVo;
    @Resource
    UniversityService universityService;
    @Resource
    CollegeService collegeService;
    @Resource
    ChinaRegionService chinaRegionService;
    // json返回数据map
    private HashMap<String, Object> resultMap;
	
	public UniversityVo getUniversityVo() {
		return universityVo;
	}
	public void setUniversityVo(UniversityVo universityVo) {
		this.universityVo = universityVo;
	}
	public CollegeVo getCollegeVo() {
		return collegeVo;
	}
	public void setCollegeVo(CollegeVo collegeVo) {
		this.collegeVo = collegeVo;
	}
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public ChinaRegionVo getChinaRegionVo() {
		return chinaRegionVo;
	}
	public void setChinaRegionVo(ChinaRegionVo chinaRegionVo) {
		this.chinaRegionVo = chinaRegionVo;
	}
	/**
     * 跳转学校信息管理界面
     */
    public String toUniversityManagePage() {
        return "toUniversityManagePage";
    }
    /**
     * 跳转添加学校信息管理界面
     */
    public String toAddUniversityPage(){
    	return "toAddUniversityPage";
    }
    /**
     * 添加学校信息
     */
    public String addUniversity() throws Exception{
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        universityVo.setCreate_time(DateTimeUtil.nowToDatabase());
        universityVo.setCreate_user(user.getName());
        universityVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        universityVo.setUpdate_user(user.getName());
        universityVo.setParent_id(0);
    	// 调用service方法，操作数据库
        int resData = universityService.addUniversity(universityVo);
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
     * 查找需要更新的学校
     */
    public String toEditUniversityPage() throws Exception {
        // 获取记录序列号id
        String id = String.valueOf(universityVo.getId());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        universityVo=universityService.selectEditUniv(paramMap);
        return "toEditUniversityPage";
    }
    
    /**
     * 更新学校信息
     */
    public String editUniversity() throws Exception {
    	//接收数据
    	UserVo user=(UserVo)this.session.get(SystemConstant.USER);
        universityVo.setUpdate_time(DateTimeUtil.nowToDatabase());
        universityVo.setUpdate_user(user.getUpdate_person());
        /*String type= universityVo.getType().substring(0,1);
        String quality= universityVo.getQuality().substring(0,1);
        universityVo.setType(type);
        universityVo.setQuality(quality);*/
    	// 调用service方法，操作数据库
        int resData = universityService.editUniversity(universityVo);
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
     * 查询学校信息
     */
    public String selectUniversity() throws Exception {
    	BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<UniversityVo> bootStrapPageVo= universityService.selectUniversity(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
    	return "json";
    }
    /**
     * 删除学校信息
     */
    public String deleteUniversity() throws Exception {
    	 BtTableUtil bootStrapTable = new BtTableUtil();
    	 Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
    	 String[] ids=((String)dataMap.get("id")).split(",");
         int deleteRtn = universityService.deleteUniversity(ids);
         int deleteColRtn =collegeService.deleteCollege(ids);
         // 封装返回数据
         AjaxReturnInfo rtn = null;
         if (deleteRtn >= 1 && deleteColRtn>=0) {
             rtn = AjaxReturnInfo.success("0");
         } else {
             rtn = AjaxReturnInfo.success("1");
         }
         resultMap = rtn.getReturnMap();
    	return "json";
    }
    /**
     * 初始化学校名称下拉框
     */
    public String selectMainUniversity() throws Exception {
    	// 获取父级id
        String parent_id = String.valueOf(universityVo.getParent_id());
        // 封装表单数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parent_id", parent_id);
        List<SelectVo> universityList;
        universityList =universityService.selectMainUniversity(paramMap);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    rtn = AjaxReturnInfo.success("");
	    rtn.add("options", universityList);
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
    /**
     * 检查输入的学校名称是否重复
     */
    public String checkUnivName() throws Exception {
    	// 获取父级id
//        String parent_id = String.valueOf(universityVo.getParent_id());
//        // 封装表单数据
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("parent_id", parent_id);
//        List<SelectVo> universityList;
    	String univName=universityVo.getName();
        int b =universityService.checkUnivName(univName);
		// 返回json数据   data.datas.options
	    AjaxReturnInfo rtn = null;
	    if(b==0){
	    	rtn = AjaxReturnInfo.success("false");
	    }
	    else{
	    	rtn = AjaxReturnInfo.success("true");
	    }
	    resultMap = rtn.getReturnMap();
	    return "json";
    }
    /**
     * 上传文件的属性
     * @return
     */
	private FileUploadInfoVo file;
	
	public OptionService getOptionService() {
		return optionService;
	}
	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}
	private OptionService optionService;
    public FileUploadInfoVo getFile() {
		return file;
	}
	public void setFile(FileUploadInfoVo file) {
		this.file = file;
	}
	/**
     * 跳转批量添加学校信息界面
     */
    public String tobBatchAddUniversityPage() throws Exception {
    	 return "tobBatchAddUniversityPage";
    }
    /**
	 * 批量上传学校信息
	 */
	public String batchAddUniversity() throws Exception{
		AjaxReturnInfo rtn=null;
		boolean upSuccess=true;
		UploadFileUtil uploadUtil=new UploadFileUtil();
		String lang=(String) session.get(SystemConstant.LANG);
		//判断文件是否空传
		System.out.println("file + "+file);
		if(file==null){//判断是否空传
			rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"请至少选择一个文件上传!":"Please choose one file!");////请至少选择一个文件上传!
			upSuccess=false;
		}else{
			log.info(file.getFileFileName()+" "+file.getFileContentType());
			if(!file.getFileFileName().endsWith(".xlsx")){//判断是否传的excel
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"请上传模板文件类型!":"Please confirm the type of file!");////请确认上传文件!
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
		if(upSuccess){
			ExcelUtil excel=new ExcelUtil(2);
			//表中数据对应的字段信息
			String[] columnName=new String[]{"name","type","quality","country","province","city","detail_addr","website","phone","email"};
			//读取excel学校信息进入list中
			List<Map<String,Object>> list=excel.readExcel(file.getFilePath(), "学校信息", columnName);
			boolean illegal=false;
			int i=0;
			HashSet<String> nameSet=new HashSet<String>();//学校名不能重复检测
			//遍历excel记录，进行基本验证
			for(Map<String,Object> map:list){
				nameSet.add((String)map.get("name"));
				//学校其他信息填写
				map.put("create_time", DateTimeUtil.nowToDatabase());//创建时间
				map.put("create_user", ((UserVo)session.get(SystemConstant.USER)).getName());//创建人
				map.put("update_time", DateTimeUtil.nowToDatabase());//更新时间
				map.put("update_user", ((UserVo)session.get(SystemConstant.USER)).getName());//更新人
				i++;
				//必填数据的验证
				if("".equals(map.get("name")) || "".equals(map.get("type")) || "".equals(map.get("quality")) || "".equals(map.get("country"))
						|| "".equals(map.get("province")) || "".equals(map.get("detail_addr")) ){
					rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("第"+(i+2)+"行列头有背景色的为必填~"):("Please input the info in column which have the background color title on the "+(i+2)+" row~"));
					illegal=true;
					break;
				}
			}
			if(i>nameSet.size()){
				rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?"学校名称不能重复~":"University Name duplicate~");//学校名称不能重复~
				illegal=true;
			}
			if(!illegal){
				//枚举数据调整  下拉框数据的key的获取
				//获取枚举数据
				List<Map<String,Object>> TypeListMap=optionService.getOptionMapByGPVal("UNIV_LEVEL");
				List<Map<String,Object>> QualityListMap=optionService.getOptionMapByGPVal("UNIV_PROP");
				List<Map<String,Object>> RigionListMap=chinaRegionService.getAllCodeName();
				boolean dataIniRight=true;//标志数据加上选择框数据与区域数据后是否合法
				i=0;
				//遍历人员调整枚举数据
				for(Map<String,Object> map:list){
					String prov=null;
					String city=null;
					String type=null;
					String quality=null;
					i++;
					for(Map<String,Object> TypeMap:TypeListMap){
						if(TypeMap.get("text").equals(map.get("type"))){
							type=(String) TypeMap.get("id");
							break;
						}
					}
					for(Map<String,Object> QualityMap:QualityListMap){
						if(QualityMap.get("text").equals(map.get("quality"))){
							quality=(String) QualityMap.get("id");
							break;
						}
					}
					boolean b1=false,b2=false;
					for(Map<String,Object> RigionMap:RigionListMap){
						if(!b1)
							if(RigionMap.get("name").equals(map.get("province"))){
								prov=(String) RigionMap.get("code");
								b1=true;
							}
						if(!b2)
							if(RigionMap.get("name").equals(map.get("city"))){
								city=(String) RigionMap.get("code");
								b2=true;
							}
						if(b1&&b2)
							break;
					}
					
					if(prov==null || city==null || type==null || quality==null){
						dataIniRight=false;
					}else{
						map.put("type", type);
						map.put("quality", quality);
						map.put("province", prov);//universityService.getRegionscode((String)map.get("province"))
						map.put("city", city);//universityService.getRegionscode((String)map.get("city"))
					}
					if(!dataIniRight){
						break;
					}
				}
				//数据加上选择框数据与区域数据后不合法
				if(!dataIniRight){
					rtn=AjaxReturnInfo.failed("zh_CN".equals(lang)?("第"+(i+2)+"行有非法数据~"):("Please correct the drop-down data on the "+(i+2)+" row~"));
				}else{
					//调用业务批量添加操作
					HashMap<String,Object> batchAddRtn=universityService.batchAddUniversity(list);
					rtn=AjaxReturnInfo.success("");
					String rtnMsg=null;
					if(!"".equals((String)batchAddRtn.get("existname"))){
						if("zh_CN".equals(lang)){
							rtnMsg="批量添加成功！以下学校已存在系统中:<br>"+batchAddRtn.get("existname");
						}else{
							rtnMsg="Unversity add success! Bellow university exist system :<br>"+batchAddRtn.get("existname");
						}
						log.info(rtnMsg);
						rtn.add("message", rtnMsg);
					}
				}
			}
			//删除临时附件
			uploadUtil.delFile(file.getFilePath());
		}
		resultMap=rtn.getReturnMap();
		System.out.println(resultMap);
		return "json";
	}
	/**
	 * 下载批量模板
	 * 2016年6月21日 Seven
	 */
	public void downloadmodel() throws Exception{
		DownloadFileUtil downLoad=new DownloadFileUtil();
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"System"+File.separator+"Template"+File.separator;
		String filePath= baseDir+"University Info Template.xlsx";
		File file = new File(filePath);
		log.info("下载University Info Template.xlsx");
		if (file.exists()) {
			downLoad.downLoad("University Info Template.xlsx", filePath, "application/x-7z-compressed,application/x-ppt,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", this.httpRequest, this.httpResponse);
		}else{
			log.info("University Info Template.xlsx不存在");
		}
	}
}

