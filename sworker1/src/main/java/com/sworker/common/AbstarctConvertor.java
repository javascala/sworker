
package com.sworker.common;

import com.sworker.common.util.CollectionUtil;
import com.sworker.common.util.StringUtil;

import java.util.Collection;

public abstract class AbstarctConvertor implements IConvertor {

	public abstract Object getValue(Object value);

	public Object[] getArray(Object[] value) {
		if (value == null) {
			return null;
		}
		Object[] result = new Object[value.length];
		int j = 0;
		for (int i = 0; i < value.length; i++) {
			if (value[i] != null && StringUtil.isNotEmpty(value[i].toString())) {
				Object v = getValue(value[i]);
				if (v != null && StringUtil.isNotEmpty(v.toString())) {
					result[i] = v;
					j++;
				}
			}
		}
		if (j < 1) {
			return null;
		} else {
			Object[] rResult = new Object[j];
			for (int i = 0; i < j; i++) {
				rResult[i] = result[i];
			}
			return rResult;
		}
	}

	@SuppressWarnings("rawtypes")
	public Collection getCollection(Object[] value) {
		return CollectionUtil.toList(getArray(value));
	}
}
