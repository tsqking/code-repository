/**
 * Project Name:clps_mms_copyright_201610
 * File Name:Md5.java
 * Package Name:com.clps.mms.util
 * Date:2016年10月17日下午3:55:01
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.util;

import java.security.MessageDigest;

/**
 * ClassName:Md5 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月17日 下午3:55:01 
 * @author   tony.tan  
 * 	 
 */
public class Encryption {
	private static String s;
	// MD5加码。32位
		public static String MD5(String inStr) {
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
				return "";
			}
			char[] charArray = inStr.toCharArray();
			byte[] byteArray = new byte[charArray.length];

			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];

			byte[] md5Bytes = md5.digest(byteArray);

			StringBuffer hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}

			return hexValue.toString();
		}

		// 可逆的加密算法
		public static String makeRandom(String inStr) {
			// String s = new String(inStr);
			char[] a = inStr.toCharArray();
			for (int i = 0; i < a.length; i++) {
				a[i] = (char) (a[i] ^ 't');
			}
			 s = new String(a);
			return s;
		}

		// 加密后解密
		public static String makeOrdered(String inStr) {
			char[] a = inStr.toCharArray();
			for (int i = 0; i < a.length; i++) {
				a[i] = (char) (a[i] ^ 't');
			}
			s = new String(a);
			return s;
		}

		// 测试主函数
		public static void main(String args[]) {
			String s = new String("pwd1234");
			System.out.println("原始：" + s);
			System.out.println("MD5后：" + MD5(s));
			System.out.println("MD5后再加密：" + makeRandom(MD5(s)));
			System.out.println("解密为MD5后的：" + makeOrdered(makeRandom(MD5(s))));
			System.out.println(makeRandom("anchao"));
			System.out.println(makeOrdered(makeRandom("anchao")));
		}
}

