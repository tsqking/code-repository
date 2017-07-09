package com.clps.tmp.common.vo;

import java.io.File;

/**
 * @author Seven
 *
 * 2015年11月6日
 */
public class FileUploadInfoVo {
	private File fileTarget;//拷贝的目标文件
	private File file;		//所上传的文件
	private String module;			//模块	如：教学模块--Tech   临时文件--Temp
	private String inModulePath;	//在模块下的具体路径 	如：a/b/c
	private String fileFileName;	//上传文件名称
	private String fileContentType;	//上传文件类型
	private long fileSize;		//上传文件大小
	private long maxSize;			//限制大小
	private String filePath;		//文件存放路径
	private String otherParam;	//存放其他参数
	public String getOtherParam() {
		return otherParam;
	}
	public void setOtherParam(String otherParam) {
		this.otherParam = otherParam;
	}
	public File getFileTarget() {
		return fileTarget;
	}
	public void setFileTarget(File fileTarget) {
		this.fileTarget = fileTarget;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getInModulePath() {
		return inModulePath;
	}
	public void setInModulePath(String inModulePath) {
		this.inModulePath = inModulePath;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
