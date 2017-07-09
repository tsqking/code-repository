package com.clps.tmp;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @author Seven 2017年1月4日
 */
public class POIReadDocToXML {
	private static Logger log = LoggerFactory.getLogger(POIReadDocToXML.class);

	public static void main(String[] args) {
		//read03ByWordExtractor("D:\\CLPS\\Work\\FU\\系统\\金融IT人才测评报名表-v2.docx.doc");
		//read07ByWordExtractor("D:\\CLPS\\Work\\FU\\系统\\金融IT人才测评报名表-v2.docx.docx");
		try {
			String [] wordName=new String[]{"姓  名","性  别","出生日期(xxxx-xx-xx)","身份证号码","手  机","电子邮箱","学  校","院  系","专  业",
					"联系地址","邮政编码","是否参加考前培训","参加测评科目"};
			String [] alignName=new String[]{"name","sex","birth","cardno","mobile","email","school","college","major",
					"contact_address","contact_post","train","subject"};
			read07TableByXWPF("D:\\CLPS\\Work\\FU\\系统\\金融IT人才测评报名表-v2.docx.docx",wordName,alignName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description 通过WordExtractor读取word内容 2003
	 * @author Seven 2017年1月5日
	 */
	public static void read03ByWordExtractor(String filePath) {
		InputStream is = null;
		WordExtractor ex = null;
		try {
			is = new FileInputStream(new File(filePath));
			ex = new WordExtractor(is);
			log.info(ex.getText());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (ex != null)
				try {
					ex.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * @Description 通过WordExtractor读取word内容 2007
	 * @author Seven 2017年1月5日
	 */
	public static void read07ByWordExtractor(String filePath) {
		POIXMLTextExtractor extractor = null;
		try {
			OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
			extractor = new XWPFWordExtractor(opcPackage);
			log.info(extractor.getText());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (extractor != null)
				try {
					extractor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * @Description 通过XWPF读取word(读取word表格数据) 2007
	 * @author Seven
	 * 2017年1月9日
	 */
	public static void read07ByXWPF(String filePath){
		InputStream is = null;
		XWPFDocument doc = null;
		try {
			is = new FileInputStream(filePath);
			doc = new XWPFDocument(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<XWPFTable> tableIt = doc.getTablesIterator();
		while (tableIt.hasNext()) {
			XWPFTable table = tableIt.next();
			for(int j = 0; j < table.getRows().size(); j ++){
				List<XWPFTableCell> cells = table.getRow(j).getTableCells(); // 获得所有列
				for (int k = 0; k < cells.size(); k++) {
					//rowInfo += cells.get(k).getText().trim() + ";";
					System.out.print(cells.get(k).getText().trim()+"#");
				}
				System.out.println();
			}
		}
	}
	
	//读取07及以上表格内容
	public static void read07TableByXWPF(String filePath, String [] names, String[] aligns){
		InputStream is = null;
		XWPFDocument doc = null;
		try {
			is = new FileInputStream(filePath);
			doc = new XWPFDocument(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<XWPFTable> tableIt = doc.getTablesIterator();
		Map<String,String> result=new HashMap<String,String>();
		int nameSize=names.length;
		for(int i=0;i<nameSize;i++){
			result.put(aligns[i], "");
		}
		while (tableIt.hasNext()) {
			XWPFTable table = tableIt.next();
			int rowSize=table.getRows().size();
			for(int i = 0; i < rowSize; i ++){
				List<XWPFTableCell> cells = table.getRow(i).getTableCells(); // 获得所有列
				int cellSize=cells.size();
				String temp=null;
				String key=null;
				for (int j = 0; j < cellSize; j++) {
					temp=cells.get(j).getText().trim();
					if(j%2==1){//奇数
						if(!"".equals(temp) && !"单击此处输入文字。".equals(temp)){
							if(!"".equals(key)){
								for(int k=0;k<nameSize;k++){
									if(names[k].equals(key))
										result.put(aligns[k], temp);
								}
							}
						}
					}else{//偶数
						key=temp;
					}
						
				}
			}
		}
		System.out.println("解析的word表格信息为：");
		for(Map.Entry<String, String> entry:result.entrySet()){
			System.out.println("	"+entry.getKey()+":"+entry.getValue());
		}
	}
	//关于两个key："是否参加靠前培训","参加测评科目"   如何获取对应的所需的信息----解决思路：使用split拆分字符串":",取出数组中第二个值即可
//	String[] strs = str.split(":");
//	String s=strs[1];
	
	
	
}
