package com.clps.tmp.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFileUtil {
	private static Logger log=LoggerFactory.getLogger(ZipFileUtil.class);
	/**
     * 把多个文件打成压缩包 
     * @param List<File>;  
     * @param org.apache.tools.zip.ZipOutputStream  
     */
    @SuppressWarnings("rawtypes")
	public static void zipFile(List files, ZipOutputStream outputStream) throws IOException {
    	outputStream.setEncoding("GBK");
        int size = files.size();
        log.info("打包多个文件");
        for(int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
        log.info("打包多个文件完成");
    }
    /**  
     * 把单个文件打成压缩包 
     * @param File
     * @param org.apache.tools.zip.ZipOutputStream
     */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) throws IOException {
    	FileInputStream IN=null;
    	BufferedInputStream bins=null;
    	try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    IN = new FileInputStream(inputFile);
                    bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());//ISO8859-1
                    ouputStream.setEncoding("GBK");
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                log.info("...打包[{}]文件完成",inputFile.getName());
            }
        }catch(Exception e) {
        	StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			log.info(sw.toString());
			pw.close();
			sw.close();
        }finally{
        	if(IN!=null)
        		IN.close();
        	if(bins!=null)
        		bins.close();
        }
    }
    

}
