/*
 * Copyright (c) 2010-2020 Founder International Co., Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Founder. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the agreements you entered into with Founder.
 */

package com.sworker.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Miscellaneous collection utility methods. Mainly for internal use within the
 * framework.
 */
public abstract class CollectionUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final List EMPTY_LIST = Collections.unmodifiableList(new ArrayList(0));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Collection EMPTY_COLLECTION = Collections.unmodifiableCollection(new ArrayList(0));

	/**
	 * Iterator wrapping an Enumeration.
	 */
	private static class EnumerationIterator<E> implements Iterator<E> {

		private Enumeration<E> enumeration;

		public EnumerationIterator(Enumeration<E> enumeration) {
			this.enumeration = enumeration;
		}

		@Override
		public boolean hasNext() {
			return this.enumeration.hasMoreElements();
		}

		@Override
		public E next() {
			return this.enumeration.nextElement();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported");
		}
	}

	/**
	 * Adapts an enumeration to an iterator.
	 * 
	 * @param enumeration
	 *            the enumeration
	 * @return the iterator
	 */
	public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
		return new EnumerationIterator<E>(enumeration);
	}

	/**
	 * Check whether the given Collection contains the given element instance.
	 * <p>
	 * Enforces the given instance to be present, rather than returning
	 * <code>true</code> for an equal element as well.
	 * 
	 * @param collection
	 *            the Collection to check
	 * @param element
	 *            the element to look for
	 * @return <code>true</code> if found, <code>false</code> else
	 */
	@SuppressWarnings("rawtypes")
	public static boolean contains(Collection collection, Object element) {
		if (collection != null) {
			for (Object candidate : collection) {
				if (candidate == element) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check whether the given Enumeration contains the given element.
	 * 
	 * @param enumeration
	 *            the Enumeration to check
	 * @param element
	 *            the element to look for
	 * @return <code>true</code> if found, <code>false</code> else
	 */
	@SuppressWarnings("rawtypes")
	public static boolean contains(Enumeration enumeration, Object element) {
		if (enumeration != null) {
			while (enumeration.hasMoreElements()) {
				Object candidate = enumeration.nextElement();
				if (ObjectUtil.equals(candidate, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check whether the given Iterator contains the given element.
	 * 
	 * @param iterator
	 *            the Iterator to check
	 * @param element
	 *            the element to look for
	 * @return <code>true</code> if found, <code>false</code> else
	 */
	@SuppressWarnings("rawtypes")
	public static boolean contains(Iterator iterator, Object element) {
		if (iterator != null) {
			while (iterator.hasNext()) {
				Object candidate = iterator.next();
				if (ObjectUtil.equals(candidate, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return <code>true</code> if any element in '<code>candidates</code>' is
	 * contained in '<code>source</code>'; otherwise returns <code>false</code>.
	 * 
	 * @param source
	 *            the source Collection
	 * @param candidates
	 *            the candidates to search for
	 * @return whether any of the candidates has been found
	 */
	@SuppressWarnings("rawtypes")
	public static boolean contains(Collection source, Collection candidates) {
		if (ObjectUtil.isNullOrEmpty(source) || ObjectUtil.isNullOrEmpty(candidates)) {
			return false;
		}
		for (Object candidate : candidates) {
			if (source.contains(candidate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find the common element type of the given Collection, if any.
	 * 
	 * @param collection
	 *            the Collection to check
	 * @return the common element type, or <code>null</code> if no clear common
	 *         type has been found (or the collection was empty)
	 */
	@SuppressWarnings("rawtypes")
	public static Class<?> findCommonElementType(Collection collection) {
		if (ObjectUtil.isNullOrEmpty(collection)) {
			return null;
		}
		Class<?> candidate = null;
		for (Object val : collection) {
			if (val != null) {
				if (candidate == null) {
					candidate = val.getClass();
				} else if (candidate != val.getClass()) {
					return null;
				}
			}
		}
		return candidate;
	}

	/**
	 * Return the first element in '<code>candidates</code>' that is contained
	 * in '<code>source</code>'. If no element in '<code>candidates</code>' is
	 * present in '<code>source</code>' returns <code>null</code>. Iteration
	 * order is {@link java.util.Collection} implementation specific.
	 *
	 * @param source
	 *            the source Collection
	 * @param candidates
	 *            the candidates to search for
	 * @return the first present object, or <code>null</code> if not found
	 */
	@SuppressWarnings("rawtypes")
	public static Object findFirstMatch(Collection source, Collection candidates) {
		if (ObjectUtil.isNullOrEmpty(source) || ObjectUtil.isNullOrEmpty(candidates)) {
			return null;
		}
		for (Object candidate : candidates) {
			if (source.contains(candidate)) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * Find a single value of the given type in the given Collection.
	 *
	 * @param collection
	 *            the Collection to search
	 * @param type
	 *            the type to look for
	 * @return a value of the given type found if there is a clear match, or
	 *         <code>null</code> if none or more than one such value found
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findValueOfType(Collection<?> collection, Class<T> type) {
		if (ObjectUtil.isNullOrEmpty(collection)) {
			return null;
		}
		T value = null;
		for (Object element : collection) {
			if ((type == null) || type.isInstance(element)) {
				if (value != null) {
					// More than one value found... no clear single value.
					return null;
				}
				value = (T) element;
			}
		}
		return value;
	}

	/**
	 * Find a single value of one of the given types in the given Collection:
	 * searching the Collection for a value of the first type, then searching
	 * for a value of the second type, etc.
	 *
	 * @param collection
	 *            the collection to search
	 * @param types
	 *            the types to look for, in prioritized order
	 * @return a value of one of the given types found if there is a clear
	 *         match, or <code>null</code> if none or more than one such value
	 *         found
	 */
	public static Object findValueOfType(Collection<?> collection, Class<?>[] types) {
		if (ObjectUtil.isNullOrEmpty(collection) || ObjectUtil.isNullOrEmpty(types)) {
			return null;
		}
		for (Class<?> type : types) {
			Object value = findValueOfType(collection, type);
			if (value != null) {
				return value;
			}
		}
		return null;
	}

	/**
	 * Determine whether the given Collection only contains a single unique
	 * object.
	 *
	 * @param collection
	 *            the Collection to check
	 * @return <code>true</code> if the collection contains a single reference
	 *         or multiple references to the same instance, <code>false</code>
	 *         else
	 */
	@SuppressWarnings("rawtypes")
	public static boolean hasUniqueObject(Collection collection) {
		if (ObjectUtil.isNullOrEmpty(collection)) {
			return false;
		}
		boolean hasCandidate = false;
		Object candidate = null;
		for (Object elem : collection) {
			if (!hasCandidate) {
				hasCandidate = true;
				candidate = elem;
			} else if (candidate != elem) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Merge the given array into the given Collection.
	 *
	 * @param array
	 *            the array to merge (may be <code>null</code>)
	 * @param collection
	 *            the target Collection to merge the array into
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void append(Collection collection, Object[] array) {
		if (collection == null) {
			throw new IllegalArgumentException("Collection must not be null");
		}
		collection.addAll(Arrays.asList(array));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(Iterator iter) {
		ArrayList list = new ArrayList();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		list.trimToSize();
		return list;
	}

	// Arrays.asList doesn't do primitive arrays
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(Object array) {
		if (array instanceof Object[]) {
			return Arrays.asList((Object[]) array); // faster?
		}
		int size = Array.getLength(array);
		ArrayList list = new ArrayList(size);
		for (int i = 0; i < size; i++) {
			list.add(Array.get(array, i));
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int[][] to2DIntArray(Collection coll) {
		return (int[][]) coll.toArray(new int[coll.size()][]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[][] to2DStringArray(Collection coll) {
		return (String[][]) coll.toArray(new String[coll.size()][]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T[] toArray(Collection<T> c) {
		List<T> rs = new ArrayList();
		Iterator it = c.iterator();
		while (it.hasNext()) {
			rs.add((T) it.next());
		}
		return (T[]) rs.toArray();
	}

	@SuppressWarnings("rawtypes")
	public static boolean[] toBooleanArray(Collection coll) {
		Iterator iter = coll.iterator();
		boolean[] arr = new boolean[coll.size()];
		int i = 0;
		while (iter.hasNext()) {
			arr[i++] = ((Boolean) iter.next()).booleanValue();
		}
		return arr;
	}

	@SuppressWarnings("rawtypes")
	public static int[] toIntArray(Collection coll) {
		Iterator iter = coll.iterator();
		int[] arr = new int[coll.size()];
		int i = 0;
		while (iter.hasNext()) {
			arr[i++] = ((Integer) iter.next()).intValue();
		}
		return arr;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Type[] toTypeArray(Collection coll) {
		return (Type[]) coll.toArray(new Type[coll.size()]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] toStringArray(Collection coll) {
		return (String[]) coll.toArray(new String[coll.size()]);
	}

	/**
	 * Given a set, determine the proper initial size for a new set to hold the
	 * same number of values. Specifically we want to account for load size and
	 * load factor to prevent immediate resizing.
	 *
	 * @param original
	 *            The original set
	 * @return The proper size.
	 */
	@SuppressWarnings("rawtypes")
	public static int determineProperSizing(Set original) {
		return determineProperSizing(original.size());
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Map EMPTY_MAP = Collections.unmodifiableMap(new HashMap(0));

	public static final float LOAD_FACTOR = 0.75f;

	public static final int MINIMUM_INITIAL_CAPACITY = 16;

	/**
	 * Create a properly sized {@link java.util.concurrent.ConcurrentHashMap} based on the given
	 * expected number of elements.
	 *
	 * @param expectedNumberOfElements
	 *            The expected number of elements for the created map
	 * @param <K>
	 *            The map key type
	 * @param <V>
	 *            The map value type
	 *
	 * @return The created map.
	 */
	public static <K, V> ConcurrentHashMap<K, V> concurrentMap(int expectedNumberOfElements) {
		return concurrentMap(expectedNumberOfElements, LOAD_FACTOR);
	}

	/**
	 * Create a properly sized {@link java.util.concurrent.ConcurrentHashMap} based on the given
	 * expected number of elements and an explicit load factor
	 * 
	 * @param expectedNumberOfElements
	 *            The expected number of elements for the created map
	 * @param loadFactor
	 *            The collection load factor
	 * @param <K>
	 *            The map key type
	 * @param <V>
	 *            The map value type
	 * 
	 * @return The created map.
	 */
	public static <K, V> ConcurrentHashMap<K, V> concurrentMap(int expectedNumberOfElements, float loadFactor) {
		final int size = expectedNumberOfElements + 1 + (int) (expectedNumberOfElements * loadFactor);
		return new ConcurrentHashMap<K, V>(size, loadFactor);
	}

	/**
	 * Given a map, determine the proper initial size for a new Map to hold the
	 * same number of values. Specifically we want to account for load size and
	 * load factor to prevent immediate resizing.
	 * 
	 * @param original
	 *            The original map
	 * @return The proper size.
	 */
	@SuppressWarnings("rawtypes")
	public static int determineProperSizing(Map original) {
		return determineProperSizing(original.size());
	}

	/**
	 * Determine the proper initial size for a new collection in order for it to
	 * hold the given a number of elements. Specifically we want to account for
	 * load size and load factor to prevent immediate resizing.
	 * 
	 * @param numberOfElements
	 *            The number of elements to be stored.
	 * @return The proper size.
	 */
	public static int determineProperSizing(int numberOfElements) {
		int actual = (int) (numberOfElements / LOAD_FACTOR) + 1;
		return Math.max(actual, MINIMUM_INITIAL_CAPACITY);
	}

	/**
	 * Build a properly sized map, especially handling load size and load factor
	 * to prevent immediate resizing.
	 * <p/>
	 * Especially helpful for copy map contents.
	 * 
	 * @param size
	 *            The size to make the map.
	 * @return The sized map.
	 */
	@SuppressWarnings("rawtypes")
	public static Map mapOfSize(int size) {
		return new HashMap(determineProperSizing(size),LOAD_FACTOR);
	}
}
