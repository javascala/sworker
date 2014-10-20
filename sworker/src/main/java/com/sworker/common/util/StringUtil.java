/*
 * Copyright (c) 2010-2020 Founder International Co., Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Founder. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the agreements you entered into with Founder.
 */

package com.sworker.common.util;

import java.math.BigInteger;
import java.util.*;

// import java.util.Properties;
public abstract class StringUtil {

	public static final String WHITESPACE = " \n\r\f\t";

	public static boolean isNotEmpty(String string) {
		return (string != null) && (string.length() > 0);
	}

	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of
	 * length 0. Note: Will return <code>true</code> for a CharSequence that
	 * purely consists of whitespace.
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean isNotEmpty(CharSequence str) {
		return ((str != null) && (str.length() > 0));
	}

	public static boolean isNullOrEmpty(String string) {
		return (string == null) || (string.length() == 0);
	}

	/**
	 * 判断字符串是否是Boolean类型 所有非true/false的字符串或null都返回false
	 * 
	 * @param bStr
	 *            字符串类型的boolean,大小写不敏感
	 * @return Boolean
	 */
	public static Boolean isBoolean(String bStr) {
		if (bStr == null) {
			return false;
		}
		String t = bStr.trim();
		if (t.length() <= 0) {
			return false;
		}
		if (t.equalsIgnoreCase("true") || t.equalsIgnoreCase("false")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否是"false"
	 * 
	 * @param bStr
	 *            大小写不敏感
	 * @return Boolean
	 */
	public static Boolean isFalse(String bStr) {
		if (!isBoolean(bStr)) {
			return false;
		}
		if (bStr.trim().equalsIgnoreCase("false")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断给定字符串是否是"true"
	 * 
	 * @param bStr
	 *            大小写不敏感
	 * @return Boolean
	 */
	public static Boolean isTrue(String bStr) {
		if (!isBoolean(bStr)) {
			return false;
		}
		if (bStr.trim().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	/**
	 * Check whether the given CharSequence contains any whitespace characters.
	 * 
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not empty and contains
	 *         at least 1 whitespace character
	 * @see Character#isWhitespace
	 */
	public static boolean isWhitespace(CharSequence str) {
		if (!isNotEmpty(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String contains any whitespace characters.
	 *
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not empty and contains at
	 *         least 1 whitespace character
	 * @see #isWhitespace(CharSequence)
	 */
	public static boolean isWhitespace(String str) {
		return isWhitespace((CharSequence) str);
	}

	/**
	 * Check whether the given CharSequence has actual text. More specifically,
	 * returns <code>true</code> if the string not <code>null</code>, its length
	 * is greater than 0, and it contains at least one non-whitespace character.
	 * <p>
	 *
	 * <pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 *
	 * @param str
	 *            the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 *         its length is greater than 0, and it does not contain whitespace
	 *         only
	 * @see Character#isWhitespace
	 */
	public static boolean hasText(CharSequence str) {
		if (!isNotEmpty(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String has actual text. More specifically,
	 * returns <code>true</code> if the string not <code>null</code>, its length
	 * is greater than 0, and it contains at least one non-whitespace character.
	 *
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not <code>null</code>, its
	 *         length is greater than 0, and it does not contain whitespace only
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	/**
	 * 判断字符串第一字母是否是小写
	 */
	public static Boolean isFirstLetterLower(String str) {
		return Character.isLowerCase(str.trim().subSequence(0, 1).charAt(0));
	}

	// ---------------------------------------------------------------------
	// Convenience methods for working with formatted Strings
	// ---------------------------------------------------------------------
	/**
	 * 判断字符串第一字母是否是大写
	 */
	public static Boolean isFirstLetterUpper(String str) {
		return Character.isUpperCase(str.trim().subSequence(0, 1).charAt(0));
	}

	/**
	 * Test if the given String ends with the specified suffix, ignoring
	 * upper/lower case.
	 *
	 * @param str
	 *            the String to check
	 * @param suffix
	 *            the suffix to look for
	 * @see String#endsWith
	 */
	public static boolean isEndsWithIgnoreCase(String str, String suffix) {
		if ((str == null) || (suffix == null)) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}
		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	// ---------------------------------------------------------------------
	// Convenience methods for working with String arrays
	// ---------------------------------------------------------------------
	/**
	 * Test if the given String starts with the specified prefix, ignoring
	 * upper/lower case.
	 *
	 * @param str
	 *            the String to check
	 * @param prefix
	 *            the prefix to look for
	 * @see String#startsWith
	 */
	public static boolean isStartsWithIgnoreCase(String str, String prefix) {
		if ((str == null) || (prefix == null)) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	public static int lastIndexOfLetter(String string) {
		for (int i = string.length() - 1; i > 0; i--) {
			char character = string.charAt(i);
			if (Character.isLetter(character) /* && !('_'==character) */) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Count the occurrences of the substring in string s.
	 *
	 * @param str
	 *            string to search in. Return 0 if this is null.
	 * @param sub
	 *            string to search for. Return 0 if this is null.
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if ((str == null) || (sub == null) || (str.length() == 0) || (sub.length() == 0)) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	public static int countUnquoted(String string, char character) {
		if ('\'' == character) {
			throw new IllegalArgumentException("Unquoted count of quotes is invalid");
		}
		if (string == null) {
			return 0;
		}
		// Impl note: takes advantage of the fact that an escpaed single quote
		// embedded within a quote-block can really be handled as two seperate
		// quote-blocks for the purposes of this method...
		int count = 0;
		int stringLength = string.length();
		boolean inQuote = false;
		for (int indx = 0; indx < stringLength; indx++) {
			char c = string.charAt(indx);
			if (inQuote) {
				if ('\'' == c) {
					inQuote = false;
				}
			} else if ('\'' == c) {
				inQuote = true;
			} else if (c == character) {
				count++;
			}
		}
		return count;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static int[] locateUnquoted(String string, char character) {
		if ('\'' == character) {
			throw new IllegalArgumentException("Unquoted count of quotes is invalid");
		}
		if (string == null) {
			return new int[0];
		}
		ArrayList locations = new ArrayList(20);
		// Impl note: takes advantage of the fact that an escpaed single quote
		// embedded within a quote-block can really be handled as two seperate
		// quote-blocks for the purposes of this method...
		int stringLength = string.length();
		boolean inQuote = false;
		for (int indx = 0; indx < stringLength; indx++) {
			char c = string.charAt(indx);
			if (inQuote) {
				if ('\'' == c) {
					inQuote = false;
				}
			} else if ('\'' == c) {
				inQuote = true;
			} else if (c == character) {
				locations.add(new Integer(indx));
			}
		}
		return CollectionUtil.toIntArray(locations);
	}

	public static String[] join(String[] x, String sep, String[] y) {
		String[] result = new String[x.length];
		for (int i = 0; i < x.length; i++) {
			result[i] = x[i] + sep + y[i];
		}
		return result;
	}

	/**
	 * Concatenate the given String arrays into one, with overlapping array
	 * elements included twice.
	 * <p>
	 * The order of elements in the original arrays is preserved.
	 *
	 * @param array1
	 *            the first array (can be <code>null</code>)
	 * @param array2
	 *            the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were
	 *         <code>null</code>)
	 */
	public static String[] join(String[] array1, String[] array2) {
		if (ObjectUtil.isNullOrEmpty(array1)) {
			return array2;
		}
		if (ObjectUtil.isNullOrEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * Convenience method to return a String array as a CSV String. E.g. useful
	 * for <code>toString()</code> implementations.
	 *
	 * @param arr
	 *            the array to display
	 * @return the delimited String
	 */
	public static String join(Object[] arr) {
		return join(arr, ",");
	}

	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 *
	 * @param arr
	 *            the array to display
	 * @param delim
	 *            the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String join(Object[] arr, String delim) {
		if (ObjectUtil.isNullOrEmpty(arr)) {
			return "";
		}
		if (arr.length == 1) {
			return ArrayUtil.toString(arr[0]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String join(String seperator, Iterator objects) {
		StringBuffer buf = new StringBuffer();
		if (objects.hasNext()) {
			buf.append(objects.next());
		}
		while (objects.hasNext()) {
			buf.append(seperator).append(objects.next());
		}
		return buf.toString();
	}

	public static String join(String seperator, String[] strings) {
		int length = strings.length;
		if (length == 0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
		for (int i = 1; i < length; i++) {
			buf.append(seperator).append(strings[i]);
		}
		return buf.toString();
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 *
	 * @param coll
	 *            the Collection to display
	 * @param delim
	 *            the delimiter to use (probably a ",")
	 * @param prefix
	 *            the String to start each element with
	 * @param suffix
	 *            the String to end each element with
	 * @return the delimited String
	 */
	public static String join(Collection<?> coll, String delim, String prefix, String suffix) {
		if (ObjectUtil.isNullOrEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 *
	 * @param coll
	 *            the Collection to display
	 * @param delim
	 *            the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String join(Collection<?> coll, String delim) {
		return join(coll, delim, "", "");
	}

	/**
	 * Convenience method to return a Collection as a CSV String. E.g. useful
	 * for <code>toString()</code> implementations.
	 *
	 * @param coll
	 *            the Collection to display
	 * @return the delimited String
	 */
	public static String join(Collection<?> coll) {
		return join(coll, ",");
	}

	/**
	 * Append the given String to the given String array, returning a new array
	 * consisting of the input array contents plus the given String.
	 *
	 * @param array
	 *            the array to append to (can be <code>null</code>)
	 * @param str
	 *            the String to append
	 * @return the new array (never <code>null</code>)
	 */
	public static String[] append(String[] array, String str) {
		if (ObjectUtil.isNullOrEmpty(array)) {
			return new String[] { str };
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * Merge the given String arrays into one, with overlapping array elements
	 * only included once.
	 * <p>
	 * The order of elements in the original arrays is preserved (with the
	 * exception of overlapping elements, which are only included on their first
	 * occurrence).
	 *
	 * @param array1
	 *            the first array (can be <code>null</code>)
	 * @param array2
	 *            the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were
	 *         <code>null</code>)
	 */
	public static String[] merge(String[] array1, String[] array2) {
		if (ObjectUtil.isNullOrEmpty(array1)) {
			return array2;
		}
		if (ObjectUtil.isNullOrEmpty(array2)) {
			return array1;
		}
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>
	 * A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of
	 * potential delimiter characters - in contrast to
	 * <code>tokenizeToStringArray</code>.
	 *
	 * @param str
	 *            the input String
	 * @param delimiter
	 *            the delimiter between elements (this is a single delimiter,
	 *            rather than a bunch individual delimiter characters)
	 * @return an array of the tokens in the list
	 * @see StringUtilBf#tokenizeToStringArray
	 */
	public static String[] split(String str, String delimiter) {
		if (StringUtil.isNullOrEmpty(str)) {
			return null;
		}
		return str.split(delimiter);
	}

	/**
	 * Split a String at the first occurrence of the delimiter. Does not include
	 * the delimiter in the result.
	 *
	 * @param toSplit
	 *            the string to split
	 * @param delimiter
	 *            to split the string up with
	 * @return a two element array with index 0 being before the delimiter, and
	 *         index 1 being after the delimiter (neither element includes the
	 *         delimiter); or <code>null</code> if the delimiter wasn't found in
	 *         the given input String
	 */
	/*
	 * public static String[] split(String toSplit, String delimiter) { if
	 * (!hasLength(toSplit) || !hasLength(delimiter)) { return null; } int
	 * offset = toSplit.indexOf(delimiter); if (offset < 0) { return null; }
	 * String beforeDelimiter = toSplit.substring(0, offset); String
	 * afterDelimiter = toSplit.substring(offset + delimiter.length()); return
	 * new String[] { beforeDelimiter, afterDelimiter }; }
	 *
	 * public static String[] split(String seperators, String list) { return
	 * StringUtilBf.split(seperators, list, false); }
	 */
	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>
	 * A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of
	 * potential delimiter characters - in contrast to
	 * <code>tokenizeToStringArray</code>.
	 *
	 * @param str
	 *            the input String
	 * @param delimiter
	 *            the delimiter between elements (this is a single delimiter,
	 *            rather than a bunch individual delimiter characters)
	 * @param charsToDelete
	 *            a set of characters to delete. Useful for deleting unwanted
	 *            line breaks: e.g. "\r\n\f" will delete all new lines and line
	 *            feeds in a String.
	 * @return an array of the tokens in the list
	 * @see StringUtilBf#tokenizeToStringArray
	 */
	public static String[] split(String str, String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}
		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
			}
		} else {
			int pos = 0;
			int delPos;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
				pos = delPos + delimiter.length();
			}
			if ((str.length() > 0) && (pos <= str.length())) {
				// Add rest of String, but not in case of empty input.
				result.add(deleteAny(str.substring(pos), charsToDelete));
			}
		}
		return toStringArray(result);
	}

	/*	*//**
	 * 根据指定正则表达式，分隔字符串
	 *
	 * @param targetString
	 *            目标字符串
	 * @param regex
	 *            正则
	 * @return 分隔后的字符串数组
	 */
	/*
	 * public static List<String> regexSplit(String targetString, String regex)
	 * { String[] rs = targetString.split(regex); if (rs == null) { rs = new
	 * String[] {}; } return Arrays.asList(rs); }
	 */
	/*	*//**
	 * Delete all occurrences of the given substring.
	 *
	 * @param inString
	 *            the original String
	 * @param pattern
	 *            the pattern to delete all occurrences of
	 * @return the resulting String
	 */
	/*
	 * public static String delete(String inString, String pattern) { return
	 * replace(inString, pattern, ""); }
	 */
	/**
	 * Delete any character in a given String.
	 *
	 * @param inString
	 *            the original String
	 * @param charsToDelete
	 *            a set of characters to delete. E.g. "az\n" will delete 'a's,
	 *            'z's and new lines.
	 * @return the resulting String
	 */
	static String deleteAny(String inString, String charsToDelete) {
		if (isNullOrEmpty(inString) || isNullOrEmpty(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String repeat(char character, int times) {
		if (times <= 1) {
			return Character.toString(character);
		}
		char[] buffer = new char[times];
		Arrays.fill(buffer, character);
		return new String(buffer);
	}

	public static String repeat(String string, int times) {
		if (isNullOrEmpty(string) || times <= 1) {
			return string;
		}
		StringBuffer buf = new StringBuffer(string.length() * times);
		for (int i = 0; i < times; i++) {
			buf.append(string);
		}
		return buf.toString();
	}

	/**
	 * 替换字串,指定替换次数.
	 *
	 * @param oldStr
	 *            要进行替换的原字串
	 * @param findStr
	 *            查找的字串
	 * @param replStr
	 *            替换为的字串
	 * @param times
	 *            次数
	 * @return 替换后的字串
	 */
	/*
	 * public static String replace(String oldStr, String findStr, String
	 * replStr, int times) { if ((oldStr == null) || (oldStr.length() < 1) ||
	 * (findStr == null) || (findStr.length() < 1) || (replStr == null) ||
	 * findStr.equals(replStr)) { return oldStr; } StringBuffer strBuff = new
	 * StringBuffer(); if (times < 1) { // 替换所有 if ((findStr.length() == 1) &&
	 * (replStr.length() == 1)) { // 单字符替换 return
	 * oldStr.replace(findStr.charAt(0), replStr.charAt(0)); } else { // 多字符替换
	 * for (int i = 0, len = oldStr.length(); i < len;) { int j =
	 * oldStr.indexOf(findStr, i); if (j >= 0) {// 找到要替换的字串 strBuff =
	 * strBuff.append(oldStr.substring(i, j)); strBuff =
	 * strBuff.append(replStr); i = j + findStr.length(); } else {// 找不到要替换的字串
	 * strBuff = strBuff.append(oldStr.substring(i)); break; } } return new
	 * String(strBuff); } } else { // 替换指定次数 int i = 0; int len =
	 * oldStr.length(); for (int k = 0; (i < len) && (k < times);) { int j =
	 * oldStr.indexOf(findStr, i); if (j >= 0) {// 找到要替换的字串 strBuff =
	 * strBuff.append(oldStr.substring(i, j)); strBuff =
	 * strBuff.append(replStr); i = j + findStr.length(); k++; } else {//
	 * 找不到要替换的字串 strBuff = strBuff.append(oldStr.substring(i)); i = len; } } if
	 * (i < len) { // 完成替换次数，但串中还有可替换的字串 strBuff =
	 * strBuff.append(oldStr.substring(i)); } return new String(strBuff); } }
	 */
	/**
	 * 替换所有字串
	 *
	 * @param oldStr
	 *            要进行替换的原字串
	 * @param findStr
	 *            查找的字串
	 * @param replStr
	 *            替换为的字串
	 * @return 替换后的字串
	 */
	/*
	 * public static String replace(String oldStr, String findStr, String
	 * replStr) { return repl(oldStr, findStr, replStr, 0); }
	 */
	/**
	 * Replace all occurences of a substring within a string with another
	 * string.
	 *
	 * @param inString
	 *            String to examine
	 * @param oldPattern
	 *            String to replace
	 * @param newPattern
	 *            String to insert
	 * @return a String with the replacements
	 */
	/*
	 * public static String replace(String inString, String oldPattern, String
	 * newPattern) { if (!hasLength(inString) || !hasLength(oldPattern) ||
	 * (newPattern == null)) { return inString; } StringBuilder sb = new
	 * StringBuilder(); int pos = 0; // our position in the old string int index
	 * = inString.indexOf(oldPattern); // the index of an occurrence we've
	 * found, or -1 int patLen = oldPattern.length(); while (index >= 0) {
	 * sb.append(inString.substring(pos, index)); sb.append(newPattern); pos =
	 * index + patLen; index = inString.indexOf(oldPattern, pos); }
	 * sb.append(inString.substring(pos)); // remember to append any characters
	 * to the right of a match return sb.toString(); }
	 */
	public static String replace(String template, String placeholder, String replacement) {
		return replace(template, placeholder, replacement, false);
	}

	/*
	 * public static boolean containsDigits(String string) { for ( int i=0;
	 * i<string.length(); i++ ) { if ( Character.isDigit( string.charAt(i) ) )
	 * return true; } return false; }
	 */
	public static String[] replace(String templates[], String placeholder, String replacement) {
		String[] result = new String[templates.length];
		for (int i = 0; i < templates.length; i++) {
			result[i] = replace(templates[i], placeholder, replacement);
		}
		return result;
	}

	public static String replace(String template, String placeholder, String replacement,
			boolean wholeWords) {
		if (template == null) {
			return template; // returnign null!
		}
		int loc = template.indexOf(placeholder);
		if (loc < 0) {
			return template;
		} else {
			final boolean actuallyReplace = !wholeWords
					|| ((loc + placeholder.length()) == template.length())
					|| !Character.isJavaIdentifierPart(template.charAt(loc + placeholder.length()));
			String actualReplacement = actuallyReplace ? replacement : placeholder;
			return new StringBuffer(template.substring(0, loc))
					.append(actualReplacement)
					.append(replace(template.substring(loc + placeholder.length()), placeholder,
							replacement, wholeWords)).toString();
		}
	}

	public static String replaceOnce(String template, String placeholder, String replacement) {
		if (template == null) {
			return template; // returnign null!
		}
		int loc = template.indexOf(placeholder);
		if (loc < 0) {
			return template;
		} else {
			return new StringBuffer(template.substring(0, loc)).append(replacement)
					.append(template.substring(loc + placeholder.length())).toString();
		}
	}

	/**
	 * Test whether the given string matches the given substring at the given
	 * index.
	 *
	 * @param str
	 *            the original string (or StringBuilder)
	 * @param index
	 *            the index in the original string to start matching against
	 * @param substring
	 *            the substring to match at the given index
	 */
	public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if ((i >= str.length()) || (str.charAt(i) != substring.charAt(j))) {
				return false;
			}
		}
		return true;
	}

	private static String suffix(String name, String suffix) {
		return (suffix == null) ? name : name + suffix;
	}

	public static String[] suffix(String[] columns, String suffix) {
		if (suffix == null) {
			return columns;
		}
		String[] qualified = new String[columns.length];
		for (int i = 0; i < columns.length; i++) {
			qualified[i] = suffix(columns[i], suffix);
		}
		return qualified;
	}

	/**
	 * Parse the given <code>localeString</code> value into a {@link java.util.Locale}.
	 * <p>
	 * This is the inverse operation of {@link java.util.Locale#toString Locale's
	 * toString}.
	 *
	 * @param localeString
	 *            the locale string, following <code>Locale's</code>
	 *            <code>toString()</code> format ("en", "en_UK", etc); also
	 *            accepts spaces as separators, as an alternative to underscores
	 * @return a corresponding <code>Locale</code> instance
	 */
	public static Locale parseLocaleString(String localeString) {
		for (int i = 0; i < localeString.length(); i++) {
			char ch = localeString.charAt(i);
			if ((ch != '_') && (ch != ' ') && !Character.isLetterOrDigit(ch)) {
				throw new IllegalArgumentException("Locale value \"" + localeString
						+ "\" contains invalid characters");
			}
		}
		String[] parts = localeString.split("_");
		String language = (parts.length > 0 ? parts[0] : "");
		String country = (parts.length > 1 ? parts[1] : "");
		String variant = "";
		if (parts.length >= 2) {
			// There is definitely a variant, and it is everything after the
			// country
			// code sans the separator between the country code and the variant.
			int endIndexOfCountryCode = localeString.indexOf(country) + country.length();
			// Strip off any leading '_' and whitespace, what's left is the
			// variant.
			variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
			if (variant.startsWith("_")) {
				variant = trimLeadingCharacter(variant, '_');
			}
		}
		return (language.length() > 0 ? new Locale(language, country, variant) : null);
	}

	/**
	 * Determine the RFC 3066 compliant language tag, as used for the HTTP
	 * "Accept-Language" header.
	 *
	 * @param locale
	 *            the Locale to transform to a language tag
	 * @return the RFC 3066 compliant language tag as String
	 */
	public static String toLanguageTag(Locale locale) {
		return locale.getLanguage()
				+ (hasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
	}

	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	/**
	 * 把字符串第一个字母小写
	 */
	public static String toLowerFirstLetter(String targetStr) {
		String firstLetter = targetStr.substring(0, 1);
		return targetStr.replaceFirst(firstLetter, firstLetter.toLowerCase());
	}

	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	/**
	 * 把字符串第一个字母大写
	 */
	public static String toUpperFirstLetter(String targetStr) {
		String firstLetter = targetStr.substring(0, 1);
		return targetStr.replaceFirst(firstLetter, firstLetter.toUpperCase());
	}

	/**
	 * Copy the given Collection into a String array. The Collection must
	 * contain String elements only.
	 *
	 * @param collection
	 *            the Collection to copy
	 * @return the String array (<code>null</code> if the passed-in Collection
	 *         was <code>null</code>)
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * Copy the given Enumeration into a String array. The Enumeration must
	 * contain String elements only.
	 *
	 * @param enumeration
	 *            the Enumeration to copy
	 * @return the String array (<code>null</code> if the passed-in Enumeration
	 *         was <code>null</code>)
	 */
	public static String[] toStringArray(Enumeration<String> enumeration) {
		if (enumeration == null) {
			return null;
		}
		List<String> list = Collections.list(enumeration);
		return list.toArray(new String[list.size()]);
	}

	/**
	 * Trim <i>all</i> whitespace from the given String: leading, trailing, and
	 * inbetween characters.
	 *
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * Trim the elements of the given String array, calling
	 * <code>String.trim()</code> on each of them.
	 *
	 * @param array
	 *            the original String array
	 * @return the resulting array (of the same size) with trimmed elements
	 */
	public static String[] trimArrayElements(String[] array) {
		if (ObjectUtil.isNullOrEmpty(array)) {
			return new String[0];
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}

	/**
	 * Trim all occurences of the supplied leading character from the given
	 * String.
	 *
	 * @param str
	 *            the String to check
	 * @param leadingCharacter
	 *            the leading character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && (sb.charAt(0) == leadingCharacter)) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Trim leading whitespace from the given String.
	 *
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * Trim all occurences of the supplied trailing character from the given
	 * String.
	 *
	 * @param str
	 *            the String to check
	 * @param trailingCharacter
	 *            the trailing character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimTrailingCharacter(String str, char trailingCharacter) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && (sb.charAt(sb.length() - 1) == trailingCharacter)) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Trim trailing whitespace from the given String.
	 *
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Trim leading and trailing whitespace from the given String.
	 *
	 * @param str
	 *            the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimWhitespace(String str) {
		if (isNullOrEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while ((sb.length() > 0) && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String truncate(String string, int length) {
		if (string.length() <= length) {
			return string;
		} else {
			return string.substring(0, length);
		}
	}

	/**
	 * Capitalize a <code>String</code>, changing the first letter to upper case
	 * as per {@link Character#toUpperCase(char)}. No other letters are changed.
	 *
	 * @param str
	 *            the String to capitalize, may be <code>null</code>
	 * @return the capitalized String, <code>null</code> if null
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		} else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * Uncapitalize a <code>String</code>, changing the first letter to lower
	 * case as per {@link Character#toLowerCase(char)}. No other letters are
	 * changed.
	 *
	 * @param str
	 *            the String to uncapitalize, may be <code>null</code>
	 * @return the uncapitalized String, <code>null</code> if null
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/**
	 * Decode a {@link java.math.BigInteger} from a {@link String} value.
	 * Supports decimal, hex and octal notation.
	 *
	 * @see java.math.BigInteger#BigInteger(String, int)
	 */
	static BigInteger decodeBigInteger(String value) {
		int radix = 10;
		int index = 0;
		boolean negative = false;
		// Handle minus sign, if present.
		if (value.startsWith("-")) {
			negative = true;
			index++;
		}
		// Handle radix specifier, if present.
		if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
			index += 2;
			radix = 16;
		} else if (value.startsWith("#", index)) {
			index++;
			radix = 16;
		} else if (value.startsWith("0", index) && (value.length() > (1 + index))) {
			index++;
			radix = 8;
		}
		BigInteger result = new BigInteger(value.substring(index), radix);
		return (negative ? result.negate() : result);
	}

	public static String unqualify(String qualifiedName) {
		int loc = qualifiedName.lastIndexOf(".");
		return (loc < 0) ? qualifiedName : qualifiedName
				.substring(qualifiedName.lastIndexOf(".") + 1);
	}

	public static String[] qualify(String prefix, String[] names) {
		if (prefix == null)
			return names;
		int len = names.length;
		String[] qualified = new String[len];
		for (int i = 0; i < len; i++) {
			qualified[i] = qualify(prefix, names[i]);
		}
		return qualified;
	}

	public static String qualify(String prefix, String name) {
		if (name == null || prefix == null) {
			throw new NullPointerException();
		}
		return new StringBuilder(prefix.length() + name.length() + 1).append(prefix).append('.')
				.append(name).toString();
	}

	public static String qualifier(String qualifiedName) {
		int loc = qualifiedName.lastIndexOf(".");
		return (loc < 0) ? "" : qualifiedName.substring(0, loc);
	}

	/**
	 * make person_id to personId
	 * 
	 * @param entity
	 * @param propertyNames
	 * @return
	 */
	public static String formatKey(String key) {
		int length = key.length();
		if (key.indexOf("_") < 0) {
			return key;
		}
		for (int i = 0; i < length; i++) {
			char c = key.charAt(i);
			int m = key.indexOf("_");
			if (String.valueOf(c).equals("_")) {
				String oldString = key.substring(m, m + 2);
				key = key.replaceFirst(oldString,String.valueOf(Character.toUpperCase(key.charAt(m + 1))));
				length--;
			}
		}
		return key;
	}
	
    public static boolean isEmpty(String str) {
        return null == str || "" == str || str.equals("") || str.length() == 0 ? true : false;

    }

    public static String[] split(String str) {
        if (isEmpty(str))
            return null;

        String strOne = str;
        String[] strArray = new String[str.length()];
        int i = 0;
        while (strOne.length() > 0) {
            strArray[i] = strOne.substring(0, 1);
            strOne = str.substring(i + 1);
            i++;
        }

        return strArray;
    }

}
