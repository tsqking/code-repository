/**
 * Project Name:TMP
 * File Name:ReadMultiDocUtil.java
 * Package Name:com.clps.tmp.common.util
 * Date:2017年3月31日上午9:46:47
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

/**
 * ClassName:ReadMultiDocUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月31日 上午9:46:47 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
public class ReadMultiDocUtil {
	private static Logger log = Logger.getRootLogger();

	// 读取07及以上表格内容
	public static List<Map<String, Object>> read07TableByXWPF(List<String> filePath, String[] names, String[] aligns) {
		List<Map<String, Object>> list = new ArrayList<>();
		InputStream is = null;
		XWPFDocument doc = null;
		for (int x = 0; x < filePath.size(); x++) {
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				is = new FileInputStream(filePath.get(x));
				doc = new XWPFDocument(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Iterator<XWPFTable> tableIt = doc.getTablesIterator();
			int nameSize = names.length;
			for (int i = 0; i < nameSize; i++) {
				result.put(aligns[i], "");
			}
			while (tableIt.hasNext()) {
				XWPFTable table = tableIt.next();
				int rowSize = table.getRows().size();
				for (int i = 0; i < rowSize; i++) {
					List<XWPFTableCell> cells = table.getRow(i).getTableCells(); // 获得所有列
					int cellSize = cells.size();
					String temp = null;
					String key = null;
					for (int j = 0; j < cellSize; j++) {
						temp = cells.get(j).getText().trim();
						if (j % 2 == 1) {// 奇数
							if (!"".equals(temp) && !"单击此处输入文字。".equals(temp)) {
								if (!"".equals(key)) {
									for (int k = 0; k < nameSize; k++) {
										if (names[k].equals(key))
											result.put(aligns[k], temp);
									}
								}
							}
						} else {// 偶数
							key = temp;
						}

					}
				}
			}
			log.info("解析的word表格信息为：");
			for (Entry<String, Object> entry : result.entrySet()) {
				log.info("	" + entry.getKey() + ":" + entry.getValue());
			}
			list.add(result);
		}
		return list;
	}
}
