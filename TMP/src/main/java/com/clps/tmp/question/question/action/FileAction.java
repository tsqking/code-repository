package com.clps.tmp.question.question.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.UploadFileUtil;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.question.question.vo.QuestionVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName FileAction
 * @Description TODO(提交文件的action-主要是考试图片)
 * @author liuchen
 * @Date 2016年7月4日 下午3:01:12
 * @version 1.0.0
 */
@SuppressWarnings({ "serial", "rawtypes" })
@ParentPackage("publicPackage")
@Namespace("/question")
@Controller
@Scope("prototype")
@Action("fileAction")
@Results({ @Result(name = "toUploadImage", location="question/uploadImage.jsp"),
		@Result(name = "toFindImage", location="question/findImage.jsp"),
		@Result(name = "toFindImageSim", location="question/findImageSim.jsp"),
		@Result(name = "json", type = "json", params = { "root", "resultMap" }) })
public class FileAction extends BaseAction implements ModelDriven {

	private HashMap<String, Object> resultMap;// json返回数据map
	// 多个文件上传
	private List<File> files;
	// 文件UUID,可以使用-分割
	private String fileNames;
	// 文件UUID
	private String fileName;
	private String usename;
	private QuestionVo questionVo;
	private List<String> file1FileName;
	private List<String> file1ContentType;

	
	public QuestionVo getQuestionVo() {
		return questionVo;
	}

	public void setQuestionVo(QuestionVo questionVo) {
		this.questionVo = questionVo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(List<String> file1FileName) {
		this.file1FileName = file1FileName;
	}

	public List<String> getFile1ContentType() {
		return file1ContentType;
	}

	public void setFile1ContentType(List<String> file1ContentType) {
		this.file1ContentType = file1ContentType;
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @Description (TODO保存图片)
	 * @return
	 * @throws Exception
	 */
	public String toUploadImage() throws Exception{
		return "toUploadImage";
	}
	
	/**
	 * @Description (TODO查看图片)
	 * @return
	 * @throws Exception
	 */
	public String toFindImage() throws Exception{
		return "toFindImage";
	}
	
	/**
	 * @Description (TODO查看图片简单功能)
	 * @return
	 * @throws Exception
	 */
	public String toFindImageSim() throws Exception{
		return "toFindImageSim";
	}

	/**
	 * @Description (TODO提交文件)
	 * @return
	 * @throws Exception
	 */
	public String filesUpload() throws Exception {
		try {
			String baseDir = new UploadFileUtil().getBaseDir() + File.separator + "Question"+File.separator+"images" + File.separator;
			File dir = new File(baseDir);
			if(!dir.exists()){
				dir.mkdirs();
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < files.size(); i++) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
				OutputStream os = new FileOutputStream(new File(baseDir, uuid + ".jpg"));// file1FileName.get(i)
				InputStream is = new FileInputStream(files.get(i));
				byte[] buf = new byte[1024];
				int length = 0;
				while (-1 != (length = is.read(buf))) {
					os.write(buf, 0, length);
				}
				is.close();
				os.close();
				sb.append(uuid).append("-");
			}
			// 封装返回数据
			AjaxReturnInfo rtn = null;
			rtn = AjaxReturnInfo.success("0");
			rtn.add("data", sb.toString());
			resultMap = rtn.getReturnMap();
			return "json";
		} catch (Exception e) {
			// 封装返回数据
			AjaxReturnInfo rtn = null;
			rtn = AjaxReturnInfo.success("1");
			resultMap = rtn.getReturnMap();
			return "json";
		}
	}

	/**
	 * @Description (TODO删除文件)
	 * @return
	 * @throws Exception
	 */
	public String deleteFiles() throws Exception {
		String baseDir = new UploadFileUtil().getBaseDir() + File.separator + "Question"+File.separator+"images" + File.separator;
		String[] names = fileNames.split("-");
		for (String name : names) {
			File f = new File(baseDir, name + ".jpg");
			if (f.exists()) {
				f.delete();
			}
		}
		// 封装返回数据
		AjaxReturnInfo rtn = null;
		rtn = AjaxReturnInfo.success("0");
		resultMap = rtn.getReturnMap();
		return "json";
	}
	
	/**
	 * @Description (TODO获取图片)
	 * @throws Exception
	 */
	public void getImage() throws Exception {
		String baseDir = new UploadFileUtil().getBaseDir() + File.separator + "Question"+File.separator+"images" + File.separator;
		File f = new File(baseDir, fileName + ".jpg");
		InputStream is = new FileInputStream(f);
		OutputStream os = this.httpResponse.getOutputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		while (-1 != (length = is.read(buf))) {
			os.write(buf, 0, length);
		}
		is.close();
		os.close();	
	}


}
