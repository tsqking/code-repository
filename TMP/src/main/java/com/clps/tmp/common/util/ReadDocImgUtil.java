/**
 * Project Name:TMP
 * File Name:ReadDocImgUtil.java
 * Package Name:com.clps.tmp.common.util
 * Date:2017年3月31日上午10:34:19
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.tmp.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
//import javafx.scene.chart.PieChart.Data;

/**
 * ClassName:ReadDocImgUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月31日 上午10:34:19 <br/>
 * 
 * @author Charles
 * @version
 * @since JDK 1.8
 * @see
 */
@SuppressWarnings("all")
public class ReadDocImgUtil {
	private static boolean flag;
	private static Logger log = Logger.getRootLogger();

	public static boolean readImg(List<String> docpath, List<String> imgSavePath,String readName) {

		for (int i=0;i<docpath.size();i++) {
			FileInputStream inputStream;
			try {
				inputStream = new FileInputStream(docpath.get(i));
				XWPFDocument xDocument = new XWPFDocument(inputStream);
				List<XWPFPictureData> pictures = xDocument.getAllPictures();
				for (XWPFPictureData picture : pictures) {
					String rawName = picture.getFileName();
					String fileExt = rawName.substring(rawName.lastIndexOf("."));
					FileOutputStream fos = new FileOutputStream(
							new File(imgSavePath.get(i) + fileExt));
					 fos.write(picture.getData());
					log.info("图片保存成功,保存的路径为" + imgSavePath.get(i) 
							+ fileExt);
					flag=true;
				}
			} catch (Exception e) {
				flag=false;
				e.printStackTrace();
			}
		}
		return flag;
	}

}
