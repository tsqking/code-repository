package com.clps.tmp.tech.util.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.StringUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.tech.util.vo.PDFVo;
import com.clps.tmp.tech.point.service.PointService;
import com.clps.tmp.tech.point.vo.DetailVo;
import com.opensymphony.xwork2.ModelDriven;
/**
 * PDF阅读
 * @author Seven
 *
 * 2015年10月30日
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/util")
@Controller
@Scope("prototype")
@Action("pdf")
@Results({ 
		@Result(name = "toPDFViewPage", location = "pdf/viewPDF.jsp"),
		@Result(name = "toSuggestReviewWayPage", location = "pdf/suggestion.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class PDFView extends BaseAction implements ModelDriven{
	//页面数据封装
	private PDFVo pdfVo;
	public PDFVo getPdfVo() {
		return pdfVo;
	}
	public void setPdfVo(PDFVo pdfVo) {
		this.pdfVo = pdfVo;
	}
	@Override
	public Object getModel() {
		if (pdfVo == null) {
			pdfVo = new PDFVo();
		}
		return pdfVo;
	}
	//json数据封装
	private HashMap<String, Object> resultMap;
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	@Resource
	PointService pointService;
	/**
	 * 跳转PDF在线阅读界面
	 * 2015年12月16日 Seven
	 */
	public String toPDFViewPage(){
		return "toPDFViewPage";
	}
	/**
	 * 获取知识点PDF文件流,给出知识点ID与查阅的知识点附件类型
	 * 2015年12月16日 Seven
	 * @throws Exception 
	 */
	public String getPointPDFFile() throws Exception{
		log.info("请求PDF在线浏览的知识点ID："+pdfVo.getPointId()+" 知识点附件类型为："+pdfVo.getPointFileType()+"\n1-教材资料(material)\n2-教师手册(t_handbok)\n3-学生手册(s_handbook)\n4-参考资料(reference)");
		AjaxReturnInfo rtn=null;
		if("".equals(pdfVo.getPointId()) || null==pdfVo.getPointId() || "".equals(pdfVo.getPointFileType()) || null==pdfVo.getPointFileType())
			rtn=AjaxReturnInfo.failed("fail_001");//知识点或者查阅的知识点附件类型为空
		else{
			DetailVo pointDetail=pointService.getDetailInfoByID(pdfVo.getPointId());
			if("1".equals(pdfVo.getPointFileType())){//教材资料
				rtn=getPDFAjaxRTN(pointDetail.getMaterial_dir());
				rtn.add("titleName", generateFileName(pointDetail.getMaterial_dir(),pdfVo.getFileName()));
			}else if("2".equals(pdfVo.getPointFileType())){//教师手册
				rtn=getPDFAjaxRTN(pointDetail.getT_handbook_dir());
				rtn.add("titleName", generateFileName(pointDetail.getT_handbook_dir(),pdfVo.getFileName()));
			}else if("3".equals(pdfVo.getPointFileType())){//学生手册
				rtn=getPDFAjaxRTN(pointDetail.getS_handbook_dir());
				rtn.add("titleName", generateFileName(pointDetail.getS_handbook_dir(),pdfVo.getFileName()));
			}else if("4".equals(pdfVo.getPointFileType())){//参考资料
				rtn=getPDFAjaxRTN(pointDetail.getReference_dir());
				rtn.add("titleName", generateFileName(pointDetail.getReference_dir(),pdfVo.getFileName()));
			}else{
				rtn=AjaxReturnInfo.failed("fail_002");//未知的查阅知识点附件类型
			}
		}
		resultMap=rtn.getReturnMap();
		return "json";
	}
	/**
	 * 获取PDF文件流,给出pdf的路径地址即可
	 * 2015年12月16日 Seven
	 * @throws IOException 
	 */
	public String getPDFFile() throws IOException{
		log.info("请求PDF在线浏览的文件地址："+pdfVo.getFilePath());
		AjaxReturnInfo rtn=getPDFAjaxRTN(pdfVo.getFilePath());
		rtn.add("titleName", generateFileName(pdfVo.getFilePath(),pdfVo.getFileName()));
		resultMap=rtn.getReturnMap();
		return "json";
	}
	//根据路径产生pdf文件名
	private String generateFileName(String filePath, String fileName){
		if("".equals(fileName) || null==fileName){//文件名为空
			if(!"".equals(filePath) && null!=filePath){//文件路径不为空，则根据路径截取名称
				filePath=filePath.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
				filePath=filePath.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
				String name=filePath.substring(filePath.lastIndexOf(File.separator)+1);
				return name.substring(0, name.lastIndexOf(".pdf"));
			}else{//文件路径也为空，返回""
				return "";
			}
		}else{//文件名不为空，返回原来名
			return fileName;
		}
	}
	//根据文件路径得到AjaxReturnInfo
	@SuppressWarnings("restriction")
	private AjaxReturnInfo getPDFAjaxRTN(String pdfFilePath) throws IOException{
		AjaxReturnInfo rtn=null;
		if("".equals(pdfFilePath) || null==pdfFilePath){//路径为空
			rtn=AjaxReturnInfo.failed("fail_01");
		}else{
			pdfFilePath=pdfFilePath.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
			pdfFilePath=pdfFilePath.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
			if(pdfFilePath.endsWith(".pdf")){//是否为pdf文件
				InputStream in = null;
		        byte[] data = null;
		        try{
		            in = new FileInputStream(pdfFilePath);
		            data = new byte[in.available()];
		            in.read(data);
		            //对字节数组Base64编码
		            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		            rtn=AjaxReturnInfo.success("");
		            rtn.add("file", encoder.encode(data));
		            in.close();
		        }catch (IOException e){
		            log.info(StringUtil.getStackTrace(e));
		            rtn=AjaxReturnInfo.failed("fail_02");
		        }finally{
		        	if(in!=null)
		        		in.close();
		        }
			}else{
				rtn=AjaxReturnInfo.failed("fail_03");
			}
		}
		return rtn;
	}
	
	public String toSuggestReviewWayPage(){
		return "toSuggestReviewWayPage";
	}
}
