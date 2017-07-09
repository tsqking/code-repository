package com.clps.tmp.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.core.common.util.SqlLoader;
import com.clps.tmp.core.sm.constant.SystemConstant;
/**
 * 上传下载工具类;文件删除复制工具类<br>
 * 使用方法：<br>
 * 1.后端<br>
 * UploadFileUtil uploadUtil=new UploadFileUtil();<br>
 * file=uploadUtil.getUploadFileInfo(file, this.httpRequest);<br>
 * HashMap<String,Object> map=uploadUtil.getReturnMap(file);<br>
 * if("true".equals(map.get("success"))){<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;FileUtils.copyFile(file.getFile(), file.getFileTarget());<br>
 * }<br>
 * log.info(map.get("message"));<br>
 * 2.前端<br>
 * 使用ajaxfileupload，可参考其使用说明<br>
 * @author Seven
 *
 * 2015年11月5日
 */
public class UploadFileUtil {
	private Logger log=Logger.getLogger(UploadFileUtil.class);
	//upload.xml路径
	private final static String UPLOAD_CONFIG = SqlLoader.getConfigAbsAddress("config","upload.xml");
	private final static String MODULE="module";
	private final static String IN_MODULE_PATH="inModulePath";
	private final static String ALLOW_SIZE="allowSize";
	private final static String IS_RECOVER="isRecover";
	private final static String FILE_PATH="filePath";
	private final static String IS_MODULE="isModule";
	private final static String IS_FUNCTION="isFunction";
	private final static String IS_YEAR="isYear";
	private final static String IS_MONTH="isMonth";
	private final static String IS_DAY="isDay";
	private final static String ALLOW_FILE_TYPE="allowFileType";
	private final static String OTHER_PARAM="otherParam";
	private Map<String,String> uploadParam;
	
	public FileUploadInfoVo getUploadFileInfo(FileUploadInfoVo fileUploadInfo,HttpServletRequest request){
		fileUploadInfo.setModule(request.getParameter(MODULE));
		fileUploadInfo.setInModulePath(request.getParameter(IN_MODULE_PATH));
		fileUploadInfo.setOtherParam(request.getParameter(OTHER_PARAM));
		fileUploadInfo.setFileSize(fileUploadInfo.getFile().length());
		long maxSize=(Integer.parseInt((String) uploadParam.get(ALLOW_SIZE)) * 1024 * 1024);// 设置上传大小
		fileUploadInfo.setMaxSize(maxSize);
		String uploadPath = getSaveDir(fileUploadInfo);
		String name = fileUploadInfo.getFileFileName();
		File currentFile = new File(uploadPath + File.separator + name);
		if (currentFile.exists()) {
			if (!Boolean.parseBoolean(uploadParam.get(IS_RECOVER))) {
				Calendar calendar = Calendar.getInstance();
				String extens = ""
						+ calendar.get(Calendar.YEAR)
						+ calendar.get(Calendar.MONTH)
						+ calendar.get(Calendar.DATE)
						+ calendar.get(Calendar.HOUR)
						+ calendar.get(Calendar.MINUTE)
						+ calendar.get(Calendar.SECOND);
				name = FilenameUtils.getBaseName(name).concat("_" + extens + ".").concat(FilenameUtils.getExtension(name));
			}
		}
		File file=new File(uploadPath + File.separator + name);
		log.info("Target File:"+uploadPath + File.separator + name);
		fileUploadInfo.setFilePath(uploadPath + File.separator + name);
		fileUploadInfo.setFileTarget(file);
		return fileUploadInfo;
	}
	
	public UploadFileUtil(){
		uploadParam = getUploadParams();
	}
	/**
	 * 返回Map <br>
	 * {"success": "true"或者"false",
	 * <br>"message":"失败时候的消息    如:文件过大;类型不符"}
	 * @param fileName	文件名称
	 * @return
	 */
	public HashMap<String,Object> getReturnMap(FileUploadInfoVo fileUploadInfo,HttpServletRequest request){
		String lang=(String) request.getSession(false).getAttribute(SystemConstant.LANG);
		HashMap<String ,Object> returnMap=new HashMap<String ,Object>();
		if(fileUploadInfo.getMaxSize()<fileUploadInfo.getFileSize()){
			returnMap.put("success", "false");
			returnMap.put("message", "zh_CN".equals(lang)?("文件["+fileUploadInfo.getFileFileName()+"]过大！"):("The file["+fileUploadInfo.getFileFileName()+"] is too large!"));
			return returnMap;
		}
		if(uploadParam.get(ALLOW_FILE_TYPE).indexOf(fileUploadInfo.getFileContentType())==-1){
			returnMap.put("success", "false");
			returnMap.put("message", "zh_CN".equals(lang)?("文件类型["+fileUploadInfo.getFileFileName()+"]被禁止上传！"):("The file type["+fileUploadInfo.getFileFileName()+"] have been forbidden to upload!"));
			return returnMap;
		}
		returnMap.put("success", "true");
		returnMap.put("message", "zh_CN".equals(lang)?"上传成功！":"Upload Success!");
		return returnMap;
	}
	/**
	 * @return 键值对形式储存的配置文件参数
	 * <p>解析upload.xml配置文件为键值对形式</p>
	 * */
	private Map<String,String> getUploadParams(){
		Map<String,String> uploadParams = new HashMap<String, String>();
		DocumentBuilder builder = null;
		Document uploadDoc = null;
		try{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
			uploadDoc = builder.parse(new File(UPLOAD_CONFIG));
			uploadParams.put(ALLOW_FILE_TYPE, getContextByTag(uploadDoc, ALLOW_FILE_TYPE));
			uploadParams.put(ALLOW_SIZE, getContextByTag(uploadDoc, ALLOW_SIZE));
			uploadParams.put(FILE_PATH, getContextByTag(uploadDoc, FILE_PATH));
			uploadParams.put(IS_MODULE, getContextByTag(uploadDoc, IS_MODULE));
			uploadParams.put(IS_FUNCTION, getContextByTag(uploadDoc, IS_FUNCTION));
			uploadParams.put(IS_YEAR, getContextByTag(uploadDoc, IS_YEAR));
			uploadParams.put(IS_MONTH, getContextByTag(uploadDoc, IS_MONTH));
			uploadParams.put(IS_DAY, getContextByTag(uploadDoc, IS_DAY));
			uploadParams.put(IS_RECOVER, getContextByTag(uploadDoc, IS_RECOVER));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return uploadParams;
	}
	/**
	 * @param uploadDoc 文档
	 * @param tagName 标签名
	 * @return 标签值
	 * <p>根据给定的标签名获取标签的值</p>
	 */
	private String getContextByTag(Document uploadDoc,String tagName){
		return ((Element)(uploadDoc.getElementsByTagName(tagName).item(0))).getTextContent();
	}
	/**
	 * 用来检查文件存放路径是否存在，不存在则创立
	 * @return 目录路径
	 */
	private String getSaveDir(FileUploadInfoVo fileUploadInfo) {
		String moudle= fileUploadInfo.getModule();//必须项： 教学--tech 临时--temp 校招--Campus
		String inModulePath = fileUploadInfo.getInModulePath();
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DATE));
		String filePath = (String) uploadParam.get(FILE_PATH);
		filePath=filePath.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
		filePath=filePath.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
		String dirPath = filePath;
		// 目录结构
		if (Boolean.parseBoolean(uploadParam.get(IS_MODULE))) {
			dirPath += File.separator + moudle;
		}
		if (Boolean.parseBoolean(uploadParam.get(IS_FUNCTION))) {
			String[] inPath=inModulePath.split("/");
			for(int i=0;i<inPath.length;i++){
				if(!"".equals(inPath[i]))
					dirPath += File.separator + inPath[i];
			}
		}
		if(!"tech".equalsIgnoreCase(moudle)){//教学模块 知识点附件特殊存放，不按照年月日来存放；其他模块的参考配置来存放
			if (Boolean.parseBoolean(uploadParam.get(IS_YEAR))) {
				dirPath += File.separator + year;
			}
			if (Boolean.parseBoolean(uploadParam.get(IS_MONTH))) {
				dirPath += File.separator + month;
			}
			if (Boolean.parseBoolean(uploadParam.get(IS_DAY))) {
				dirPath += File.separator + day;
			}
		}
		checkDirExist(dirPath);
		return dirPath;
	}
	/**
	 * 用于检查目录是否存在，没有时创建
	 * @param dirPath 文件目录
	 */
	private void checkDirExist(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	/**
	 * 删除指定文件
	 */
	public boolean delFile(String filePath){
		if("".equals(filePath) || null==filePath){
			log.info("文件删除失败!因为路径是"+filePath);
			return false;
		}
		File file=new File(filePath);
		if(file.exists()){
			log.info("文件删除成功!"+filePath);
			return file.delete();
		}else{
			log.info("文件删除失败!因为文件不存在!");
			return false;
		}
	}
	/**
	 * 复制制定文件到指定目录
	 */
	public boolean copyFile(String sourceFilePath,String destFilePath){
		if(!"".equals(sourceFilePath) && null!=sourceFilePath
				&& !"".equals(destFilePath) && null!=destFilePath){
			File sourceFile=new File(sourceFilePath);
			File destFile=new File(destFilePath);
			FileInputStream fi = null;
	        FileOutputStream fo = null;
	        FileChannel in = null;
	        FileChannel out = null;
	        boolean success=true;
	        try {
	        	String filePath=destFilePath.substring(0, destFilePath.lastIndexOf(File.separator));
	        	checkDirExist(filePath);
	        	if(!destFile.exists())
	        		destFile.createNewFile();
	            fi = new FileInputStream(sourceFile);
	            fo = new FileOutputStream(destFile);
	            in = fi.getChannel();//得到对应的文件通道
	            out = fo.getChannel();//得到对应的文件通道
	            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
	        } catch (IOException e) {
	        	success=false;
	        	StringWriter sw=new StringWriter();
	        	PrintWriter pw=new PrintWriter(sw);
	        	e.printStackTrace(pw);
	        	log.info(sw.toString());
	        } finally {
	            try {
	                fi.close();
	                in.close();
	                fo.close();
	                out.close();
	            } catch (IOException e){
	            }
	        }
	        return success;
		}else{
			return false;
		}
    }
	/**
	 * 获取文件存放的配置目录
	 * 2015年11月27日 Seven
	 */
	public String getBaseDir(){
		String filePath = (String) uploadParam.get(FILE_PATH);
		filePath=filePath.replaceAll("/", "\\".equals(File.separator)?"\\\\":"/");
		filePath=filePath.replaceAll("\\\\", "\\".equals(File.separator)?"\\\\":"/");
		return filePath;
	}
}
