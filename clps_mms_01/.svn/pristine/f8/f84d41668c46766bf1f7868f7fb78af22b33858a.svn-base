package com.clps.mms.util.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * ClassName: StringUtil.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2016年11月29日 下午6:00:57 
 *
 * @author tony.tan
 * @version 
 *
 */
public class StringUtil {
	/**
	 * . 日期格式
	 */
	private static SimpleDateFormat dateFormat;
	/**
	 * . 数字格式
	 */
	private static DecimalFormat numberFormat;

	static {
		dateFormat = new SimpleDateFormat();

		numberFormat = new DecimalFormat();
	}

	/**
	 * 将string数组A跟B合并成string数组array(A在B之前)
	 * 
	 * @param arrayA
	 * @param arrayB
	 * @return array
	 */
	public static String[] combineArray(String[] arrayA, String[] arrayB) {
		if (arrayA == null) {
			return arrayB;
		}
		if (arrayB == null) {
			return arrayA;
		}
		String[] array = new String[arrayA.length + arrayB.length];
		System.arraycopy(arrayA, 0, array, 0, arrayA.length);
		System.arraycopy(arrayB, 0, array, arrayA.length, arrayB.length);
		return array;
	}

	/**
	 * isBufLen是正数（isBufLen小于sBufData的长度，截取sBufData，长度为isBufLen，<br>
	 * isBufLen大于sBufData的长度，取sBufData，长度不够isBufLen，用iChar的第一个字母在sBufData之前填充）<br>
	 * isBufLen是负数（isBufLen大于sBufData的长度的相反数，截取sBufData，长度为isBufLen，<br>
	 * isBufLen小于sBufData的长度的相反数，取sBufData，长度不够isBufLen，
	 * 用iChar的第一个字母在sBufData之后填充）<br>
	 * 
	 * @param sBufData
	 * @param iChar
	 * @param isBufLen
	 * @return
	 */
	public static String fixFill(String sBufData, String iChar, int isBufLen) {
		assert (sBufData != null);
		assert (iChar != null);
		byte[] bBufData;
		try {
			bBufData = sBufData.getBytes("GBK");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException1) {
			bBufData = sBufData.getBytes();
		}
		byte[] bCharData;
		try {
			bCharData = iChar.getBytes("GBK");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException2) {
			bCharData = iChar.getBytes();
		}

		int sBufDataLen = bBufData.length;
		int iLen;
		byte[] bObjData;
		if (isBufLen < 0) {
			iLen = 0 - isBufLen;
			bObjData = new byte[iLen];
			if (sBufDataLen > iLen)
				sBufDataLen = iLen;
		} else {
			iLen = isBufLen;
			bObjData = new byte[isBufLen];
			if (sBufDataLen > iLen) {
				int iStart = sBufDataLen - iLen;
				for (int i = 0; i < iLen; i++) {
					bBufData[i] = bBufData[(i + iStart)];
				}
				sBufDataLen = iLen;
			}
		}
		if (isBufLen < 0) {
			for (int i = 0; i < sBufDataLen; i++) {
				bObjData[i] = bBufData[i];
			}
			for (int i = sBufDataLen; i < iLen; i++)
				bObjData[i] = bCharData[0];
		} else {
			int iStart = isBufLen - sBufDataLen;
			for (int i = 0; i < iStart; i++) {
				bObjData[i] = bCharData[0];
			}
			for (int i = 0; i < sBufDataLen; i++) {
				bObjData[(iStart + i)] = bBufData[i];
			}

		}

		String sRetMsg = new String(bObjData);
		return sRetMsg;
	}

	/**
	 * getDate(根据给定的时间和对应的时间格式化的格式，返回字符串类型的给定的时间格式化的时间，如：给定时间类型的时间“2014-12-09”
	 * 和时间格式化形式“yyyy-MM-dd”，则返回字符串类型的时间“2014-12-09”。<br>
	 * 若参数都为null,默认的时间为系统时间，默认的时间格式为 "yyyy-MM-dd hh:mm:ss")
	 * 
	 * @param date
	 * @param dateFormatPattern
	 * @return
	 */
	public static String getDate(Date date, String dateFormatPattern)
			throws ParseException {
		if (date == null) {
			Date sysdate = new Date();
			date = sysdate;
		}
		if (dateFormatPattern == null) {
			//小时改成了24小时制-Leo
			dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
		}
		dateFormat = new SimpleDateFormat(dateFormatPattern);
		return dateFormat.format(date);
	}

	/**
	 * 将给定的字符串类型的时间和时间格式化的格式转换成时间类型返回，默认时间格式化的格式为"yyyy-MM-dd hh:mm:ss",
	 * 返回的时间格式为系统默认的格式
	 * 
	 * @param dateString
	 * @param dateFormatPattern
	 * @return
	 */

	public static Date toDate(String dateString, String dateFormatPattern)
			throws ParseException {
		assert (dateString != null);
		Date date = null;
		if (dateFormatPattern == null) {
			dateFormatPattern = "yyyy-MM-dd hh:mm:ss";
		}
		synchronized (dateFormat) {
			dateFormat.applyPattern(dateFormatPattern);
			dateFormat.setLenient(false);
			date = dateFormat.parse(dateString);
		}
		return date;
	}

	/**
	 * getSysDate(根据给定时间格式，返回系统的时间。默认格式为"yyyy-MM-dd hh:mm:ss")
	 * 
	 * @param dateFormatPattern
	 * @return
	 */
	public static String getSysDate(String dateFormatPattern)
			throws ParseException {
		Date sysdate = new Date();
		if (dateFormatPattern == null) {
			dateFormatPattern = "yyyy-MM-dd hh:mm:ss";
		}
		dateFormat = new SimpleDateFormat(dateFormatPattern);
		return dateFormat.format(sysdate);
	}

	/**
	 * isValidDate(判断给定的字符串类型的时间和给定时间格式化的形式是否匹配)
	 * 
	 * @param dateString
	 * @param dateFormatPattern
	 * @return
	 */
	public static boolean isValidDate(String dateString,
			String dateFormatPattern) {
		assert (dateString != null);
		assert (dateFormatPattern != null);
		Date validDate = null;
		synchronized (dateFormat) {
			try {
				dateFormat.applyPattern(dateFormatPattern);
				dateFormat.setLenient(false);
				validDate = dateFormat.parse(dateString);
			} catch (ParseException localParseException) {
			}
		}
		return validDate != null;
	}

	/**
	 * 判断邮箱地址是否正确(字母开头)
	 * 
	 * @param emailAddrString
	 * @return
	 */
	public static boolean isValidEmailAddr(String emailAddrString) {
		boolean isValid = false;
		// p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
		// w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
		// [a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
		// [.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
		// p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
		if (emailAddrString
				.matches("\\p{Alpha}\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}")) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * 判断数字字符串转化成long类型，是否在min和max之间
	 * 
	 * @param numberString
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isValidInteger(String numberString, long min, long max) {
		assert (numberString != null);
		Long validLong = null;
		try {
			Number aNumber = numberFormat.parse(numberString);
			long aLong = aNumber.longValue();
			if ((aLong >= min) && (aLong <= max))
				validLong = new Long(aLong);
		} catch (ParseException localParseException) {
		}
		return validLong != null;
	}

	/**
	 * 判断数字字符串转化成double类型，是否在min和max之间
	 * 
	 * @param numberString
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isValidNumber(String numberString, double min,
			double max) {
		assert (numberString != null);

		boolean validNumber = false;
		try {
			Number aNumber = toNumber(numberString);
			double anDouble = aNumber.doubleValue();
			if ((anDouble >= min) && (anDouble <= max))
				validNumber = true;
		} catch (ParseException localParseException) {
		}
		return validNumber;
	}

	/**
	 * 把numberString，min，max都转换成double类型， 判断转换后numberString是否在转换后的min和max之间
	 * 
	 * @param numberString
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isValidNumber(String numberString, String min,
			String max) {
		assert (numberString != null);
		assert (min != null);
		assert (max != null);
		boolean validNumber = false;
		try {
			Number aNumber = toNumber(numberString);
			Number nMin = toNumber(min);
			Number nMax = toNumber(max);
			double anDouble = aNumber.doubleValue();
			double dMin = nMin.doubleValue();
			double dMax = nMax.doubleValue();
			if ((anDouble >= dMin) && (anDouble <= dMax))
				validNumber = true;
		} catch (ParseException localParseException) {
		}
		return validNumber;
	}

	/**
	 * 判断validStrings字符串数组中是否包含字符串value
	 * 
	 * @param value
	 * @param validStrings
	 * @param ignoreCase
	 * @return
	 */
	public static boolean isValidString(String value, String[] validStrings,
			boolean ignoreCase) {
		if (validStrings == null)
			return false;
		boolean isValid = false;
		for (int i = 0; i < validStrings.length; i++) {
			if (ignoreCase) {
				if (validStrings[i].equalsIgnoreCase(value)) {
					isValid = true;
					break;
				}
			} else if (validStrings[i].equals(value)) {
				isValid = true;
				break;
			}
		}

		return isValid;
	}

	/**
	 * 如果str包含inStr字符串，就把相同部分用repStr替换(为嘛不直接用replace自带) 不包含的话，就返回str
	 * 
	 * @param str
	 * @param inStr
	 * @param repStr
	 * @return
	 */
	public static String replaceString(String str, String inStr, String repStr) {
		if ((str == null) || (inStr == null) || (repStr == null)) {
			return str;
		}
		StringBuffer newValue = new StringBuffer();
		char[] strChars = str.toCharArray();
		int strLen = strChars.length;
		char[] inChars = inStr.toCharArray();
		int inLen = inChars.length;

		for (int i = 0; i < strLen; i++) {
			if ((strChars[i] == inChars[0]) && (i + inLen <= strLen)) {
				boolean isEqual = true;
				for (int j = 1; j < inLen; j++) {
					if (strChars[(i + j)] != inChars[j]) {
						isEqual = false;
						break;
					}
				}
				if (isEqual) {
					newValue.append(repStr);
					i += inLen - 1;
				} else {
					newValue.append(strChars[i]);
				}
			} else {
				newValue.append(strChars[i]);
			}
		}
		return newValue.toString();
	}

	/**
	 * 在字节数组bObjData中从nStart开始，用sSrcData中的值替换
	 * 
	 * @param bObjData
	 * @param nStart
	 * @param sSrcData
	 * @return
	 */
	public static byte[] setBytesData(byte[] bObjData, int nStart,
			byte[] sSrcData) {
		assert (bObjData != null);
		assert (sSrcData != null);
		int nCount = sSrcData.length;
		for (int i = 0; i < nCount; i++)
			bObjData[(nStart + i)] = sSrcData[i];
		return bObjData;
	}

	/**
	 * 将byte数组值按照参数长度分行
	 * 
	 * @param byteSrc
	 * @param lengthOfLine
	 * @return
	 */
	public static String toHexTable(byte[] byteSrc, int lengthOfLine) {
		return toHexTable(byteSrc, lengthOfLine, 7);
	}

	/**
	 * @param byteSrc
	 * @param lengthOfLine
	 * @param flag
	 * @return
	 */
	public static String toHexTable(byte[] byteSrc, int lengthOfLine, int flag) {
		StringBuffer hexTableBuffer = new StringBuffer(256);
		int lineCount = byteSrc.length / lengthOfLine;
		int totalLen = byteSrc.length;
		if (byteSrc.length % lengthOfLine != 0)
			lineCount++;
		for (int lineNumber = 0; lineNumber < lineCount; lineNumber++) {
			int startPos = lineNumber * lengthOfLine;
			byte[] lineByte = new byte[Math.min(lengthOfLine, totalLen
					- startPos)];
			System.arraycopy(byteSrc, startPos, lineByte, 0, lineByte.length);
			int flagA = flag & 0x4;
			if (4 == flagA) {
				int count = lengthOfLine * lineNumber;
				String addrStr = Integer.toString(count, 16);
				int len = addrStr.length();
				for (int i = 0; i < 8 - len; i++) {
					hexTableBuffer.append('0');
				}
				hexTableBuffer.append(addrStr);
				hexTableBuffer.append("h: ");
			}
			int flagB = flag & 0x2;
			if (2 == flagB) {
				StringBuffer byteStrBuf = new StringBuffer();
				for (int i = 0; i < lineByte.length; i++) {
					String num = Integer.toHexString(lineByte[i] & 0xFF);
					if (num.length() < 2)
						byteStrBuf.append('0');
					byteStrBuf.append(num);
					byteStrBuf.append(' ');
				}
				hexTableBuffer.append(fixFill(byteStrBuf.toString(), " ", 48));
				hexTableBuffer.append("; ");
			}
			int flagC = flag & 0x1;
			if (1 == flagC) {
				for (int i = 0; i < lineByte.length; i++) {
					char c = (char) lineByte[i];
					if (c < '!')
						c = '.';
					try {
						if ((c >= '?') && (i < lineByte.length - 1)) {
							char c2 = (char) lineByte[(i + 1)];
							if (c2 >= '?') {
								String str = new String(lineByte, i, 2);
								hexTableBuffer.append(str);
								i++;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();

						hexTableBuffer.append("");
						hexTableBuffer.append(c);
					}
				}
			}
			if (lineNumber >= lineCount - 1)
				break;
			hexTableBuffer.append('\n');
		}
		return hexTableBuffer.toString();
	}

	/**
	 * 将字符串在 byteWidth + width > bytePosition时charCursor的值为分割点，分开 返回一个字符串数组
	 * 
	 * @param source
	 * @param bytePosition
	 * @return
	 */
	public static String[] splitAtBytePosition(String source, int bytePosition) {
		int byteWidth = 0;
		int charCursor = 0;
		for (; charCursor < source.length(); charCursor++) {
			char c = source.charAt(charCursor);
			int width = c > '?' ? 2 : 1;
			if (byteWidth + width > bytePosition) {
				break;
			}
			byteWidth += width;
		}
		String prefix = source.substring(0, charCursor);
		String suffix = source.substring(charCursor);
		return new String[] { prefix, suffix };
	}

	/**
	 *
	 * @param numString
	 * @return
	 * @throws ParseException
	 */
	public static Number toNumber(String numString) throws ParseException {
		assert (numString != null);
		Number number = null;
		String numFormatPattern = "############.##";
		synchronized (numberFormat) {
			numberFormat.applyPattern(numFormatPattern);
			number = numberFormat.parse(numString);
		}
		return number;
	}

	/**
	 * 将数字字符串的格式转换成“###,###,###,###.##”，返回出来
	 * 
	 * @param numString
	 * @return
	 */
	public static String toNumberString(String numString) throws ParseException {
		String number = "0";
		Number num = toNumber(numString);
		String numFormatPattern = "###,###,###,###.##";
		synchronized (numberFormat) {
			numberFormat.applyPattern(numFormatPattern);
			number = numberFormat.format(num);
		}
		return number;
	}

	/**
	 * 返回特定数字格式的数字字符串
	 * 
	 * @param numString
	 *            数字字符串
	 * @param numFormatPattern
	 *            格式formate.若为null,默认为"###,###,###,###.##"
	 * @return
	 */
	public static String toNumberString(String numString,
			String numFormatPattern) throws ParseException {
		String number = "0";
		Number num = toNumber(numString);
		if (numFormatPattern == null) {
			numFormatPattern = "###,###,###,###.##";
		}
		synchronized (numberFormat) {
			numberFormat.applyPattern(numFormatPattern);
			number = numberFormat.format(num);
		}
		return number;
	}

	/**
	 * 查找出字符串或者字符出现的所有索引
	 * 
	 * @param matchStr
	 * @param searchStr
	 * @return
	 */
	public static ArrayList<Integer> findAllString(String matchStr,
			String searchStr) {
		int i = 0;
		ArrayList<Integer> array = new ArrayList<Integer>();
		while (i <= matchStr.length()) {
			int result = matchStr.indexOf(searchStr, i);
			if (result == -1) {
				break;
			} else {
				array.add(result);
				i = result + searchStr.length();
			}
		}
		return array;

	}

	/**
	 * 先判断是否存在该字符串，如果存在，就移除字符串中的一个指定字符串 否则就提示不存在
	 * 
	 * @param str
	 * @param appoint
	 * @return
	 */
	public static String removeString(String str, String appoint) {
		if (str.contains(appoint)) {
			return str.replaceAll(appoint, "");
		} else {
			System.out.println("没有该字符串");
		}
		return null;
	}

	/**
	 * 给一个字符串以特定字符补全
	 * 
	 * @author seven
	 * @param str
	 *            需要补全的字符串
	 * @param size
	 *            需要得到的最终长度
	 * @param c
	 *            替补的字符
	 * @param before
	 *            是否从头补齐
	 * @return 返回补好的字符串
	 */
	public static String completeString(String str, int size, char c,
			boolean before) {
		StringBuffer sb = new StringBuffer();
		int length = str.length();
		if (length >= size)
			return str;
		if (before) {// 从头补齐
			for (int i = 0; i < size - length; i++) {
				sb.append(c);
			}
			sb.append(str);
		} else {
			sb.append(str);
			for (int i = length; i < size; i++) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 给一个字符串以特定字符trim()
	 * 
	 * @author seven
	 * @param str
	 *            需要处理的字符串
	 * @param c
	 *            trim的字符
	 * @param before
	 *            是否从头trim return 返回处理后的字符串
	 */
	public static String trimString(String str, char c, boolean before) {
		int length = str.length();
		int count = 0;
		if (before) {// 从头trim
			for (int i = 0; i < length; i++) {
				if (str.charAt(i) == c) {
					count++;
				} else {
					break;
				}
			}
			if (count == length) {
				return String.valueOf(c);
			} else {
				return str.substring(count);
			}
		} else {
			for (int i = length - 1; i >= 0; i--) {
				if (str.charAt(i) == c) {
					count++;
				} else {
					break;
				}
			}
			if (count == length) {
				return String.valueOf(c);
			} else {
				return str.substring(0, length - count);
			}
		}
	}

	/**
	 * 根据ASCII的String，返回中文的String<br>
	 * 
	 * @param realVal
	 *            新的值 <br>
	 * @param fixLengthVal
	 *            定长原值 <br>
	 */
	public static String getString(String str) {
		str = str.trim();
		// ASCII-->中文
		int start = 0;
		int end = str.length();
		char tem;
		StringBuilder sb = new StringBuilder();
		StringBuilder sbTem = new StringBuilder();
		for (; start < end; start++) {
			tem = str.charAt(start);
			if ((int) tem == 92) {
				sbTem = new StringBuilder();
				for(int i = 1;i<=4;i++){
					int num = start+i;
					if(num>=end){
						break;
					}else{
						sbTem.append(str.charAt(num));
					}						
				}
				//取值,解码,报错处理
				try{
					sb.append(deUnicode(sbTem.toString()));
					start=start+4;
				}catch(Exception e){
					//不兼容的编码
					sb.append(tem);				
				}			
			}else{
				sb.append(tem);
			}	
		}
		return sb.toString();
	}

	/**
	 * 根据定长的原值，返回同等长度的新值(处理中文为ASCII码)<br>
	 * 注意：不够长，返回新值转换后的值
	 * 
	 * @param realVal
	 *            新的值 <br>
	 * @param fixLengthVal
	 *            定长原值 <br>
	 */
	public static String setString(Object realVal, String fixLengthVal) {
		String re = null;
		// 判断字符串或者,数字类型
		if (realVal instanceof Integer || realVal instanceof BigDecimal) {
			// 数字类型
			StringBuilder number = new StringBuilder();
			// 加空格
			String value = realVal.toString();
			int leng = fixLengthVal.length() - value.length();
			if (leng < 0) {
				String value1 = value;
				value = value.substring(0, fixLengthVal.length());
				System.out.println(value1 + "数值超长将被截取--> " + value);
			}
			int i = 0;
			for (; i < leng; i++) {
				number.append("0");
			}
			number.append(value);
			re = number.toString();
		} else {
			// 字符串类型
			String value = (String) realVal;
			// 中文-->ASCII
			StringBuilder sb = new StringBuilder();
			value = value.trim();
			char tem;
			Pattern p = Pattern.compile("([\u4E00-\u9FA5]|[\uFE30-\uFFA0])+");
			int start = 0;
			int end = value.length();
			for (; start < end; start++) {
				tem = value.charAt(start);
				if ((int) tem == 92){
					//由于\是特殊标记符,所以要替换掉
					sb.append("-");
				}else{
					Matcher m = p.matcher(tem + "");
					if (m.find()) {
						sb.append("\\");
						sb.append(enUnicode(tem+""));
					} else {
						sb.append(tem);
					}
				}			
			}
			value = sb.toString();
			// 加空格
			int leng = fixLengthVal.length() - value.length();
			if (leng < 0) {
				String value1 = value;
				value = value.substring(0, fixLengthVal.length());
				System.out.println(value1 + "值超长将被截取--> " + value);
			}
			sb = new StringBuilder();
			sb.append(value);
			int i = 0;
			for (; i < leng; i++) {
				sb.append(" ");
			}
			re = sb.toString();
		}
		return re;
	}
	/* ----------------------------------------------------------------------- */	
	private static String enUnicode(String content) {
		// 将汉字转换为16进制数
		String enUnicode = null;
		for (int i = 0; i < content.length(); i++) {
			if (i == 0) {
				enUnicode = getHexString(Integer.toHexString(content.charAt(i))
						.toUpperCase());
			} else {
				enUnicode = enUnicode
						+ getHexString(Integer.toHexString(content.charAt(i))
								.toUpperCase());
			}
		}
		return enUnicode;
	}
	private static String deUnicode(String content) throws Exception{
		// 将16进制数转换为汉字
		String enUnicode = null;
		String deUnicode = null;
		for (int i = 0; i < content.length(); i++) {
			if (enUnicode == null) {
				enUnicode = String.valueOf(content.charAt(i));
			} else {
				enUnicode = enUnicode + content.charAt(i);
			}
			if (i % 4 == 3) {
				if (enUnicode != null) {
					if (deUnicode == null) {
						deUnicode = String.valueOf((char) Integer.valueOf(
								enUnicode, 16).intValue());
					} else {
						deUnicode = deUnicode
								+ String.valueOf((char) Integer.valueOf(
										enUnicode, 16).intValue());
					}
				}
				enUnicode = null;
			}
		}
		return deUnicode;
	}
	private static String getHexString(String hexString) {
		String hexStr = "";
		for (int i = hexString.length(); i < 4; i++) {
			if (i == hexString.length())
				hexStr = "0";
			else
				hexStr = hexStr + "0";
		}
		return hexStr + hexString;
	}
	/* ----------------------------------------------------------------------- */

	/**
	 * 判断一个字符串是否为 NULL 或为空
	 * 
	 * @param inStr
	 * @return 为空或null返回true
	 */
	public static boolean isValid(String inStr) {
		if (inStr == null) {
			return true;
		} else if (inStr.equals("")) {
			return true;
		} else if (inStr.equalsIgnoreCase("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 以String类型返回错误抛出的堆栈信息
	 * 
	 * @param t
	 *            Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t) {
		if (t == null)
			return "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	

	/**
	 * 驼峰名字 liuchen 作用: 将XML字段转为驼峰名字
	 * 
	 * @Title: changeName
	 * @Description: TODO
	 * @param name
	 * @return
	 * @return: String
	 */
	public Map<String, String> changeMapName(Map<String, String> map) {
		Map<String, String> reMap = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			reMap.put(this.changeName(entry.getKey()), entry.getValue());
		}
		return reMap;
	}

	private String changeName(String name) {
		String[] names = name.split("_");
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (String tem : names) {
			tem = tem.toLowerCase();
			if (flag) {
				tem = tem.replaceFirst(tem.substring(0, 1), tem.substring(0, 1)
						.toUpperCase());
			} else {
				flag = true;
			}
			sb.append(tem);
		}
		return sb.toString();
	}
}
