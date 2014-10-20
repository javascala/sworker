package com.sworker.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class RegexUtil {
	//check if the source string contain the pattern string 
	public static boolean checkContain(String sourceStr,String patternString)
	{
		if (patternString == null || sourceStr == null) {
			return patternString == sourceStr;
		}
		Pattern p=Pattern.compile(patternString);
		Matcher matcher=p.matcher(sourceStr);
		if(matcher.find())
		{
			return true;
		}
		return false;
	}
	
	public static boolean matches(String sourceStr,String patternString)
	{
		if (patternString == null || sourceStr == null) {
			return patternString == sourceStr;
		}
		return Pattern.matches(patternString, sourceStr);
	}
}
