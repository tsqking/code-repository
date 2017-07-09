package com.clps.tmp.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * @ClassName ImgBase64Util
 * @Description TODO(图片与Base64转化工具类)
 * @author liuchen
 * @Date 2016年7月6日 下午2:25:28
 * @version 1.0.0
 */
public class ImgBase64Util {
	
	/**
	 * @Description (TODO测试)
	 * @param args
	 */
	public static void main(String[] args) {
		// 测试从Base64编码转换为图片文件
		//String strImg = "这里放64位编码";
		//GenerateImage(strImg, "D:/wangyc.jpg");
		// 测试从图片文件转换为Base64编码
		//System.out.println(GetImageStr(new File("C:/Users/liuchen/Desktop/屏幕快照 2016-07-06 下午2.20.19.png")));
	}

	/**
	 * @Description (TODO将图片文件转化为字节数组字符串，并对其进行Base64编码处理)
	 * @param imgFilePath
	 * @return
	 */
	public static String GetImageStr(File imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			//默认图片
			return "";
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * @Description (TODO对字节数组字符串进行Base64解码并生成图片)
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 */
	public static boolean GenerateImage(String imgStr, File imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
