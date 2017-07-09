package com.clps.tmp.core.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class SqlLoader {
	static String classPath=SqlLoader.class.getClassLoader().getResource("/").getPath();
	/**
	 * 根据文件名与节点路径获取sql内容
	 * @param sqlFileName sqlFileName 在sql目录下的路径文件名   例如：DemoSQL.xml,Demo/DemoSQL.xml
	 * @param xpath 节点路径  例如：//SQL/queryDemo
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static String loadSqlFromXml(String sqlFileName, String xpath) throws FileNotFoundException, DocumentException {
		InputStream input = loadSqlDoc(getConfigAbsAddress("sql",sqlFileName));
		SAXReader saxReader = new SAXReader();
		Document sqlDoc = saxReader.read(input);
		Node node = sqlDoc.selectSingleNode(xpath);
		return node.getText();
	}

	private static InputStream loadSqlDoc(String fileName) throws DocumentException, FileNotFoundException {
		InputStream input = SqlLoader.class.getClassLoader().getResourceAsStream(fileName);
		if (input == null) {
			input = new FileInputStream(fileName);
		}
		return input;
	}
	/**
	 * 获取配置信息文件在服务器的绝对地址
	 * @param configFileName 在WEB-INF下的文件名称
	 * @param path 在configFileName下的具体路径
	 */
	public static String getConfigAbsAddress(String configFileName,String path){
		String configFile = "";
		if("\\".equals(File.separator)){//windows
			System.out.println("服务器系统：windows");
			configFile = classPath.substring(1,classPath.indexOf("classes"));
			configFile+=(configFileName+"/"+path);
			configFile = configFile.replace("/", "\\");
		}
		if("/".equals(File.separator)){//linux
			System.out.println("服务器系统：linux");
			configFile = classPath.substring(0,classPath.indexOf("classes"));
			configFile+=(configFileName+"/"+path);
			configFile = configFile.replace("\\", "/");
		}
		System.out.println("File："+configFile);
		return configFile;
	}
}