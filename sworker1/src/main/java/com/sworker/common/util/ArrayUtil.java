/*
 * Copyright (c) 2010-2020 Founder International Co., Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Founder. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the agreements you entered into with Founder.
 */

package com.sworker.common.util;

import java.lang.reflect.Array;
import java.util.*;

public final class ArrayUtil {

	private ArrayUtil() {
	}

	public static final boolean[] TRUE = { true };

	public static final boolean[] FALSE = { false };

	public static final String[] EMPTY_STRING_ARRAY = {};

	public static final int[] EMPTY_INT_ARRAY = {};

	public static final boolean[] EMPTY_BOOLEAN_ARRAY = {};

	@SuppressWarnings("rawtypes")
	public static final Class[] EMPTY_CLASS_ARRAY = {};

	public static final Object[] EMPTY_OBJECT_ARRAY = {};

	// public static final Type[] EMPTY_TYPE_ARRAY = {};
	public static final byte[] EMPTY_BYTE_ARRAY = {};

	@SuppressWarnings("unchecked")
	public static <T> T[] fill(T value, int length) {
		T[] result = (T[]) Array.newInstance(value.getClass(), length);
		Arrays.fill(result, value);
		return result;
	}

	private static int SEED = 23;

	private static int PRIME_NUMER = 37;

	private static int hash(int seed, int i) {
		return PRIME_NUMER * seed + i;
	}

	public static String[] slice(String[] strings, int begin, int length) {
		String[] result = new String[length];
		System.arraycopy(strings, begin, result, 0, length);
		return result;
	}

	/**
	 * calculate the array hash (only the first level)
	 */
	public static int hash(Object[] array) {
		int length = array.length;
		int seed = SEED;
		for (int index = 0; index < length; index++) {
			seed = hash(seed, array[index] == null ? 0 : array[index].hashCode());
		}
		return seed;
	}

	/**
	 * calculate the array hash (only the first level)
	 */
	public static int hash(byte[] bytes) {
		int length = bytes.length;
		int seed = SEED;
		for (int index = 0; index < length; index++) {
			seed = hash(seed, bytes[index]);
		}
		return seed;
	}

	public static char[] fill(char value, int length) {
		char[] result = new char[length];
		Arrays.fill(result, value);
		return result;
	}

	public static boolean[] fill(boolean value, int length) {
		boolean[] result = new boolean[length];
		Arrays.fill(result, value);
		return result;
	}

	public static byte[] fill(byte value, int length) {
		byte[] result = new byte[length];
		Arrays.fill(result, value);
		return result;
	}

	public static double[] fill(double value, int length) {
		double[] result = new double[length];
		Arrays.fill(result, value);
		return result;
	}

	public static float[] fill(float value, int length) {
		float[] result = new float[length];
		Arrays.fill(result, value);
		return result;
	}

	public static long[] fill(long value, int length) {
		long[] result = new long[length];
		Arrays.fill(result, value);
		return result;
	}

	public static int[] fill(int value, int length) {
		int[] result = new int[length];
		Arrays.fill(result, value);
		return result;
	}

	public static short[] fill(short value, int length) {
		short[] result = new short[length];
		Arrays.fill(result, value);
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> T[] join(T[] x, T[] y) {
		T[] result = (T[]) Array.newInstance(x.getClass().getComponentType(), x.length + y.length);
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static boolean[] join(boolean[] x, boolean[] y) {
		boolean[] result = new boolean[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static byte[] join(byte[] x, byte[] y) {
		byte[] result = new byte[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static char[] join(char[] x, char[] y) {
		char[] result = new char[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static double[] join(double[] x, double[] y) {
		double[] result = new double[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static float[] join(float[] x, float[] y) {
		float[] result = new float[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static long[] join(long[] x, long[] y) {
		long[] result = new long[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static int[] join(int[] x, int[] y) {
		int[] result = new int[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static short[] join(short[] x, short[] y) {
		short[] result = new short[x.length + y.length];
		System.arraycopy(x, 0, result, 0, x.length);
		System.arraycopy(y, 0, result, x.length, y.length);
		return result;
	}

	public static String[] join(String[] x, String[] y, boolean[] use) {
		String[] result = new String[x.length + countTrue(use)];
		System.arraycopy(x, 0, result, 0, x.length);
		int k = x.length;
		for (int i = 0; i < y.length; i++) {
			if (use[i]) {
				result[k++] = y[i];
			}
		}
		return result;
	}
	
	public static String[] add(String[] x, String y) {
		if(StringUtil.isNullOrEmpty(y)) return x;
		int size=x.length;
		String[] result = new String[size+1];
		System.arraycopy(x, 0, result, 0, size);
		result[size]=y;		
		return result;
	}

	/**
	 * Check whether the given array contains the given element.
	 * 
	 * @param array
	 *            the array to check (may be <code>null</code>, in which case
	 *            the return value will always be <code>false</code>)
	 * @param element
	 *            the element to check for
	 * @return whether the element has been found in the given array
	 */
	public static <T> boolean contains(T[] array, T element) {
		if (array == null)return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(element)) {return true;}
		}
		return false;
	}

	public static boolean contains(boolean[] array, boolean element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(byte[] array, byte element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(char[] array, char element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(double[] array, double element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(float[] array, float element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(long[] array, long element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(int[] array, int element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(short[] array, short element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public static <T> int indexOf(T[] array, T element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(boolean[] array, boolean element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(byte[] array, byte element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(char[] array, char element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(double[] array, double element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(float[] array, float element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(long[] array, long element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(int[] array, int element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int indexOf(short[] array, short element) {
		if (array == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Return a String representation of the specified Object.
	 * <p>
	 * Builds a String representation of the contents in case of an array.
	 * Returns <code>"null"</code> if <code>obj</code> is <code>null</code>.
	 * 
	 * @param obj
	 *            the object to build a String representation for
	 * @return a String representation of <code>obj</code>
	 */
	public static String toString(Object obj) {
		if (obj == null) {
			return "null";
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		if (obj instanceof Object[]) {
			return Arrays.toString((Object[]) obj);
		}
		if (obj instanceof boolean[]) {
			return Arrays.toString((boolean[]) obj);
		}
		if (obj instanceof byte[]) {
			return Arrays.toString((byte[]) obj);
		}
		if (obj instanceof char[]) {
			return Arrays.toString((char[]) obj);
		}
		if (obj instanceof double[]) {
			return Arrays.toString((double[]) obj);
		}
		if (obj instanceof float[]) {
			return Arrays.toString((float[]) obj);
		}
		if (obj instanceof int[]) {
			return Arrays.toString((int[]) obj);
		}
		if (obj instanceof long[]) {
			return Arrays.toString((long[]) obj);
		}
		if (obj instanceof short[]) {
			return Arrays.toString((short[]) obj);
		}
		String str = obj.toString();
		return (str != null ? str : "");
	}

	public static boolean isAllTrue(boolean[] array) {
		for (int i = 0; i < array.length; i++) {
			if (!array[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAllFalse(boolean[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAllNegative(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= 0) {
				return false;
			}
		}
		return true;
	}

	public static int countTrue(boolean[] array) {
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				result++;
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Object[] copy(Class elementClass, Object[] array) {
		Object[] result = (Object[]) Array.newInstance(elementClass, array.length);
		System.arraycopy(array, 0, result, 0, array.length);
		return result;
	}

	public static <T> T[] copy(T[] array) {
		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Convert the given array (which may be a primitive array) to an object
	 * array (if necessary of primitive wrapper objects).
	 * <p>
	 * A <code>null</code> source value will be converted to an empty Object
	 * array.
	 * 
	 * @param source
	 *            the (potentially primitive) array
	 * @return the corresponding object array (never <code>null</code>)
	 * @throws IllegalArgumentException
	 *             if the parameter is not an array
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] toObjectArray(Object source) {
		if (source instanceof Object[]) {
			return (Object[]) source;
		}
		if (source == null) {
			return new Object[0];
		}
		if (!source.getClass().isArray()) {
			throw new IllegalArgumentException("Source is not an array: " + source);
		}
		int length = Array.getLength(source);
		if (length == 0) {
			return new Object[0];
		}
		Class wrapperType = Array.get(source, 0).getClass();
		Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
		for (int i = 0; i < length; i++) {
			newArray[i] = Array.get(source, i);
		}
		return newArray;
	}

	public static String[] toStringArray(Object[] objects) {
		int length = objects.length;
		String[] result = new String[length];
		for (int i = 0; i < length; i++) {
			result[i] = objects[i].toString();
		}
		return result;
	}

	public static Object[] typecast(Object[] array, Object[] to) {
		return Arrays.asList(array).toArray(to);
	}

	/**
	 * Convert the supplied array into a List. A primitive array gets converted
	 * into a List of the appropriate wrapper type.
	 * <p>
	 * A <code>null</code> source value will be converted to an empty List.
	 * 
	 * @param source
	 *            the (potentially primitive) array
	 * @return the converted List result
	 * @see ObjectUtil#toObjectArray(Object)
	 */
	@SuppressWarnings("rawtypes")
	public static List toList(Object source) {
		return Arrays.asList(toObjectArray(source));
	}
	
	@SafeVarargs
	public static <T> List<T> toList(T...source) {
		return Arrays.asList(source);
	}

	public static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = ((result << 8) - Byte.MIN_VALUE) + bytes[i];
		}
		return result;
	}

	public static String toString(Object[] array) {
		int len = array.length;
		if (len == 0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(len * 12);
		for (int i = 0; i < (len - 1); i++) {
			buf.append(array[i]).append(", ");
		}
		return buf.append(array[len - 1]).toString();
	}

	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}
	public static String[] remove(String[] array,String y) {
		if (ObjectUtil.isNullOrEmpty(array)||StringUtil.isNullOrEmpty(y)) {
			return array;
		}
		Set<String> set = new TreeSet<String>();
		for (String element : array) {
			if(!element.equals(y)){set.add(element);};
		}
		return StringUtil.toStringArray(set);
	}

	/**
	 * Remove duplicate Strings from the given array. Also sorts the array, as
	 * it uses a TreeSet.
	 * 
	 * @param array
	 *            the String array
	 * @return an array without duplicates, in natural sort order
	 */
	public static String[] removeDuplicateStrings(String[] array) {
		if (ObjectUtil.isNullOrEmpty(array)) {
			return array;
		}
		Set<String> set = new TreeSet<String>();
		for (String element : array) {
			set.add(element);
		}
		return StringUtil.toStringArray(set);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object getMaxValue(List<Object> valueList) {
		if (ObjectUtil.isNullOrEmpty(valueList)) {
			return null;
		}
		Map<String, Integer> countMap = new Hashtable();
		Map<String, Object> objectMap = new Hashtable();
		String key = null;
		Integer count = 0;
		for (Object o : valueList) {
			key = o.toString();
			if (!objectMap.containsKey(key)) {
				objectMap.put(key, o);
				countMap.put(key, 1);
			} else {
				count = countMap.get(key);
				countMap.put(key, count + 1);
			}
		}
		count = 0;
		for (String k : countMap.keySet()) {
			if (countMap.get(k) > count) {
				count = countMap.get(k);
				key = k;
			}
		}
		return objectMap.get(key);
	}

	/**
	 * Compare 2 arrays only at the first level
	 */
	public static boolean isEquals(Object[] o1, Object[] o2) {
		if (o1 == o2)
			return true;
		if (o1 == null || o2 == null)
			return false;
		int length = o1.length;
		if (length != o2.length)
			return false;
		for (int index = 0; index < length; index++) {
			if (!o1[index].equals(o2[index]))
				return false;
		}
		return true;
	}

	/**
	 * Compare 2 arrays only at the first level
	 */
	public static boolean isEquals(char[] o1, char[] o2) {
		if (o1 == o2)
			return true;
		if (o1 == null || o2 == null)
			return false;
		int length = o1.length;
		if (length != o2.length)
			return false;
		for (int index = 0; index < length; index++) {
			if (!(o1[index] == o2[index]))
				return false;
		}
		return true;
	}

	/**
	 * Compare 2 arrays only at the first level
	 */
	public static boolean isEquals(byte[] b1, byte[] b2) {
		if (b1 == b2)
			return true;
		if (b1 == null || b2 == null)
			return false;
		int length = b1.length;
		if (length != b2.length)
			return false;
		for (int index = 0; index < length; index++) {
			if (!(b1[index] == b2[index]))
				return false;
		}
		return true;
	}
}
