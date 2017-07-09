package com.clps.tmp.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 文件下载工具类
 * @author Seven
 *
 * 2015年11月10日
 */
public class DownloadFileUtil {
	private static Logger log=Logger.getLogger(DownloadFileUtil.class);
	/**
	 * 下载文件
	 * @param fileName 文件名
	 * @param filePath 文件存放路径
	 * @param fileType 文件类型
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * 2015年11月27日 Seven
	 */
	public void downLoad(String fileName, String filePath, String fileType, HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream outputStream =null;
		InputStream inputStream =null;
		try{
			File file = new File(filePath);
			if (file.exists()) {
				if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
				response.setContentType(fileType);
				response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				
				outputStream = response.getOutputStream();
				inputStream = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
			}
		}catch(Exception e){
			StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			log.info(sw.toString());
			pw.close();
			sw.close();
		}finally{
			if(outputStream!=null)
				outputStream.close();
			if(inputStream!=null)
				inputStream.close();
		}
	}
	/**
	 * 自动判断文件类型并下载
	 * 2016年4月11日 Seven
	 */
	public void downLoad(String fileName, String filePath, HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream outputStream =null;
		InputStream inputStream =null;
		try{
			File file = new File(filePath);
			if (file.exists()) {
				if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
				String suffix=filePath.substring(filePath.lastIndexOf(".")+1);//根据后缀名判断文件类型
				Map<String,Object> type=new HashMap<String,Object>();//类型map
				type.put("jpg", "application/x-jpg");type.put("jpeg", "image/jpeg");type.put("gif", "image/gif");type.put("png", "image/png");
				type.put("zip", "application/zip");type.put("7z", "application/x-7z-compressed");type.put("rar", "application/octet-stream");
				type.put("ppt", "application/x-ppt");type.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
				type.put("doc", "application/msword");type.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				type.put("xls", "application/vnd.ms-excel");type.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				type.put("txt", "text/plain");type.put("pdf", "application/pdf");type.put("exe", "application/x-msdownload");
				String fileType=(String) type.get(suffix.toLowerCase());
				if(fileType==null || "".equals(fileType)){
					log.info("下载错误：文件类型未知");
					return;
				}
				response.setContentType(fileType);
				response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				outputStream = response.getOutputStream();
				inputStream = new FileInputStream(filePath);
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
			}
		}catch(Exception e){
			StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			log.info(sw.toString());
			pw.close();
			sw.close();
		}finally{
			if(outputStream!=null)
				outputStream.close();
			if(inputStream!=null)
				inputStream.close();
		}
	}
	/**
	 * 打包多个文件并下载压缩包
	 * @param files 文件列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downLoadZipFiles(List<File> files, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			/**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件*/
            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip*/
            File file = new File(new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator+DateTimeUtil.getNow()+".rar");
            if (!file.exists()){   
                file.createNewFile();   
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);   
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
            ZipFileUtil.zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            downloadZipFile(file,response);
        }catch (Exception e) {
        	StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			log.info(sw.toString());
			pw.close();
			sw.close();
        }
    }
	/**
	 * 打包多个文件并下载压缩包
	 * @param files 文件路径列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void downLoadZipFiless(List<String> files, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			/**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件*/
            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip*/
            File file = new File(new UploadFileUtil().getBaseDir()+File.separator+"Temp"+File.separator+DateTimeUtil.getNow()+".rar");
            if (!file.exists()){   
                file.createNewFile();   
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);   
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
            List<File> fileList=new ArrayList<File>();
            for(String temp:files){
            	 File tmp = new File(temp);
            	 if (tmp.exists()){   
            		 fileList.add(tmp);
                 }
            }
            ZipFileUtil.zipFile(fileList, zipOut);
            zipOut.close();
            fous.close();
            downloadZipFile(file,response);
        }catch (Exception e) {
        	StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			log.info(sw.toString());
			pw.close();
			sw.close();
        }
    }
	/**
	 * 下载压缩文件
	 * @param file 文件
	 * @param response
	 */
	public static void downloadZipFile(File file, HttpServletResponse response) {
		try {
			//以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			//清空response
			response.reset();
			
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			
			//如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				File f = new File(file.getPath());
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
