package com.clps.tmp.tech.util.vo;

public class PDFVo {
	private String pointId;//知识点ID
	private String pointFileType;//查看知识点附件类型  1-教材资料(material) 2-教师手册(t_handbok) 3-学生手册(s_handbook) 4-参考资料(reference)
	private String fileName;//文件名称，显示在页面Title
	private String filePath;//文件路径
	private String fileContentType;//文件类型
	//****
	private String method;//常规查阅/知识点查阅
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPointId() {
		return pointId;
	}
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	public String getPointFileType() {
		return pointFileType;
	}
	public void setPointFileType(String pointFileType) {
		this.pointFileType = pointFileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
}
