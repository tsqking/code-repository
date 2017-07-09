package com.clps.tmp.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Doc文档生成工具类<br>
 * <br>
 * @author Seven
 * 2016年6月23日
 */
public class DocUtil {
	static String classPath=DocUtil.class.getClassLoader().getResource("/").getPath();
	/**
	 * 生成Doc
	 * @param templeteName 模板名称 如"test.vm"
	 * @param velocityContext 模板中参数内容
	 * @param fileName 生成的文件名称，如："测试卷",可传""
	 * 2016年6月23日 Seven
	 */
	
	
	public static String createDoc(String templeteName,VelocityContext velocityContext,String fileName) throws Exception{
		Properties ps = new Properties();
		String templateLoc=classPath+File.separator+"velocity"+File.separator;
		System.out.println("DOC模板所在路径："+templateLoc);
		ps.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templateLoc);
		VelocityEngine ve = new VelocityEngine();
		ve.init(ps);
		//ve.init();
		Template template = ve.getTemplate(templeteName, "utf-8");
		
		String baseDir=new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator;
		String loc= baseDir+fileName+"-"+DateTimeUtil.getNow()+".docx";//临时存储位置
		System.out.println("生成临时文件所在路径："+templateLoc);
		
		File srcFile = new File(loc);// 输出路径
		FileOutputStream fos = new FileOutputStream(srcFile);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos,
				"utf-8"));
		template.merge(velocityContext, writer);
		writer.flush();
		writer.close();
		fos.close();
		return loc;
	}
}
