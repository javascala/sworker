package com.sworker.common.util;

import java.io.UnsupportedEncodingException;

/**
 * BASE64编解码工具
 * 
 * @author liuk
 * 
 */
public class Base64Util extends BaseUtil {
	/**
	 * BASE64编码
	 * 
	 * @param stringbyte
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String encrypt(byte[] stringbyte) {
		String result = null;
		if (null!=stringbyte) {
			result = new sun.misc.BASE64Encoder().encode(stringbyte);
		}
		return result;
	}

	/**
	 * BASE64编码
	 * 
	 * @param string
	 *            要编码的字符串
	 * @param charsetName
	 *            字符串编码
	 * @return
	 */
	public static String encrypt(String string, String charsetName) {
		String result = null;
		if (ObjectUtil.isNotEmpty(string)) {
			byte[] stringbyte = null;
			try {
				stringbyte = string.getBytes(charsetName);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("请使用正确的编码");
			}
			result = encrypt(stringbyte);
		}
		return result;
	}

	/**
	 * BASE64解码
	 * 
	 * @param base64
	 *            待解码字符串
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static byte[] decrypt(String base64) {
		byte[] result = null;
		if (ObjectUtil.isNotEmpty(base64)) {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			try {
				result = decoder.decodeBuffer(base64);
			} catch (Exception e) {
				throw new RuntimeException("解码失败");
			}
		}
		return result;
	}

	/**
	 * BASE64解码
	 * 
	 * @param base64
	 *            待解码字符串
	 * @param charsetName
	 *            解码后字符串编码方式
	 * @return
	 */
	public static String decrypt(String base64, String charsetName) {
		String result = null;
		try {
			result = new String(decrypt(base64), charsetName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("请使用正确的编码");
		}
		return result;
	}

}
