package com.clps.tmp.core.common.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author seven 
 * MD5加密 & DES加密解密
 */
public class SecurityHelper
{
	/**
	 * md5加密
	 * @param data 待加密数据
	 * @param len 加密数据长度
	 * @return
	 */
	public static String MD5(String data, int len)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (len == 16)
				return buf.toString().substring(8, 24);
			else
				return buf.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 默认MD5加密(32位)
	 * @param src 待加密数据
	 * @return String
	 */
	public static String MD5(String src)
	{
		return MD5(src, 32);
	}

	
	private static final String PASSWORD_CRYPT_KEY = "clps2015";
	/**
	 * DES 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();//DES算法要求有一个可信任的随机数源
		DESKeySpec dks = new DESKeySpec(key);//从原始密匙数据创建DESKeySpec对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");//Cipher对象实际完成加密操作
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);//用密匙初始化Cipher对象
		return cipher.doFinal(src);//对数据加密
	}

	/**
	 * 解密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		SecureRandom sr = new SecureRandom();//DES算法要求有一个可信任的随机数源
		DESKeySpec dks = new DESKeySpec(key);//从原始密匙数据创建一个DESKeySpec对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");//Cipher对象实际完成解密操作
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);//用密匙初始化Cipher对象
		return cipher.doFinal(src);//对数据解密
	}

	/**
	 * 密码解密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String DESDecrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 密码加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String DESEncrypt(String data) {
		try {
			return byte2hex(encrypt(data.getBytes(),PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 二行制转字符串
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));//32λ
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	 
	public static void main(String[] args){//测试用例，不需要传递任何参数，直接执行即可。
		String str = "cl279052";//最多15位，DES加密后才不超过32位
		String enStr = DESEncrypt(str);
		String deStr = DESDecrypt(str);
		System.out.println("str : "+str);
		System.out.println("加密 str：" + enStr + "  长度："+enStr.length());
		System.out.println("解密 str：" + deStr);
	}
	
}