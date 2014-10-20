/*
 * Copyright (c) 2010-2020 Founder International Co., Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Founder. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the agreements you entered into with Founder.
 */

package com.sworker.common.util;

import com.sworker.common.CoreConstants;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.NamedThreadLocal;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public class DateUtil extends BaseUtil {//private static Logger log = Logger.getLogger(DateUtil.class);
	
	private static final String FORMATER = "yyyy/MM/dd";

	private static final String TIME_PATTERN = "HH:mm";

	private static final ThreadLocal<Locale> localeContextHolder = new NamedThreadLocal<Locale>("Locale context");

	private static final ThreadLocal<Locale> inheritableLocaleContextHolder = new NamedInheritableThreadLocal<Locale>("Locale context");

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws java.text.ParseException
	 *             when String doesn't match the expected format
	 */
	public static Calendar getToday() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDateFormat());
		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convert(todayAsString));
		return cal;
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws java.text.ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convert(String strDate) {
		Date aDate = null;

		aDate = convert(getDateFormat(), strDate);
		return aDate;
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws java.text.ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convert(String aMask, String strDate) {
		SimpleDateFormat df;
		Date date = null;
		/*if (strDate.indexOf("-") > 0) {
			strDate = strDate.replace("-","/");
		}*/
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return date;
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDateFormat() {
		Locale locale = localeContextHolder.get();
		if (locale == null) {
			locale = inheritableLocaleContextHolder.get();
		}
		
		if(locale == null){
			locale = Locale.CHINA;	
		}
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(CoreConstants.BUNDLE_KEY, locale).getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		} catch (NullPointerException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}
		return defaultDatePattern;
	}

	public static String getDateTimeFormat() {
		return DateUtil.getDateFormat() + " HH:mm:ss.S";
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String toFormatString(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String toDateTimeString(Date theTime) {
		return toFormatString(TIME_PATTERN, theTime);
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static String toDateString(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat(getDateFormat());
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}
	
	/**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
    	return getDateTime(getDatePattern(), aDate);
    }
    
    public static String convertDateToStringForDB(Date aDate) {
    	return convertDateToString(aDate);
    }
    
    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    
    /**
     * Return default datePattern (MM/dd/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
           defaultDatePattern = ResourceBundle.getBundle(CoreConstants.BUNDLE_KEY, locale).getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "yyyy/MM/dd";
        }

        return defaultDatePattern;
    }
    
    /**
     * return age
     * @param birthday
     * @param specifiedDate
     * @return
     */
    public static String getAge(Date birthday, Date specifiedDate) {
    	int year = 0;
    	int month = 0;
    	int day = 0;
    	if (birthday != null) {
            Calendar date = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            if (specifiedDate != null) {
                if (birthday.compareTo(specifiedDate) <= 0) {
                    now.setTime(specifiedDate);
                }
            }
            date.setTime(birthday);
            day = now.get(Calendar.DAY_OF_MONTH) - date.get(Calendar.DAY_OF_MONTH);
            month = now.get(Calendar.MONTH) - date.get(Calendar.MONTH);
            year = now.get(Calendar.YEAR) - date.get(Calendar.YEAR);
            if(day<0){
                month -= 1;
                now.add(Calendar.MONTH, -1);
                day = day + now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if(month<0){
                month = (month+12)%12;
                year--;
            }
    	}
    	
    	if(year == 0 && month != 0) {
    		return String.valueOf(month) + "月";
    	} else if ((year == 0 && month == 0)) {
    		return String.valueOf(day) + "天";
    	} else {
    		return String.valueOf(year) + "岁";
    	}
    }

    
    /**
	 * 根据日期获取时星期几
	 * @param date
	 * @return
	 */
	public static String getWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();//获得一个日历
	    calendar.setTime(date);//设置当前时间,月份是从0月开始计算
	    int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，   
	    String [] str = {"","日","一","二","三","四","五","六",};
	    
	    return str[number];
	}
	
	
	/**
	 * 获取day天后日期类型
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getAfterDay(Date date, int day) {
		if (null == date && day <= 0) return date;
		return new Date(date.getTime()+ day * 24 * 60 * 60 * 1000);
	}
	
	/**
	 * 获取day天后日期类型
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date convertDate(String fmt,Date aDate) {
		return convert(fmt,getDateTime(fmt,aDate));
	}
	
	public static Date parseSimpleDate(String dateStr, String formater) {
		if (null == dateStr || dateStr.trim().length() < 1) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formater);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDateString(String dateStr) {
		return parseSimpleDate(dateStr, FORMATER);
	}

	public static String getStringByDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMATER);
		return format.format(date);
	}

	/**
	 * 根据年龄计算出生日期
	 * 
	 * @param age
	 * @param type
	 * @return
	 */
	public static Date getBirthdayByAge(int age) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 0 - age);
		return cal.getTime();
	}

	/**
	 * 根据年龄计算出生年份
	 * 
	 * @param age
	 * @param type
	 * @return
	 */
	public static int getBirthYearByAge(int age) {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) - age;
	}

	/**
	 * 根据生日获得年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirthday(Date birthday) {
		if (null == birthday) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);
		return Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	}

	public static Date getDateByYearMonthDay(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		return c.getTime();
	}

	/**
	 * 获取两个日期间隔天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int getBetweenDays(Date before, Date after) {
		Calendar start = Calendar.getInstance();
		start.setTime(before);
		Calendar end = Calendar.getInstance();
		end.setTime(after);
		return getDaysBetween(start, end);
	}

	/**
	 * 获取两个日期间隔天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(Calendar start, Calendar end) {
		if (start.after(end)) {
			Calendar swap = start;
			start = end;
			end = swap;
		}
		int days = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
		int y2 = end.get(Calendar.YEAR);
		if (start.get(Calendar.YEAR) != y2) {
			start = (Calendar) start.clone();
			do {
				days += start.getActualMaximum(Calendar.DAY_OF_YEAR);
				start.add(Calendar.YEAR, 1);
			} while (start.get(Calendar.YEAR) != y2);
		}
		return days + 1;
	}

	/**
	 * 将Data中时间部分变为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date makeTimeToZero(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		makeTimeToZero(calendar);
		return calendar.getTime();
	}

	public static void makeTimeToZero(Calendar calendar) {
		calendar.set(Calendar.HOUR,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		calendar.set(Calendar.AM_PM,0);
	}
	
	/**
	 * 将Data中时间部分变为最大
	 * 
	 * @param date
	 * @return
	 */
	public static Date makeDateToZero(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		makeDateToZero(calendar);
		return calendar.getTime();
	}

	public static void makeDateToZero(Calendar calendar) {
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		makeTimeToZero(calendar);
	}
	
	/**
	 * 将Data中时间部分变为最大
	 * 
	 * @param date
	 * @return
	 */
	public static Date makeDateToMax(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		makeDateToMax(calendar);
		return calendar.getTime();
	}

	public static void makeDateToMax(Calendar calendar) {
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		makeTimeToZero(calendar);
	}

	/**
	 * 将Data中时间部分变为最大
	 * 
	 * @param date
	 * @return
	 */
	public static Date makeTimeToMax(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		makeTimeToMax(calendar);
		return calendar.getTime();
	}

	public static void makeTimeToMax(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public static Date getCurrentWithoutTime() {
		Date current = new Date();
		return makeTimeToZero(current);
	}

	/**
	 * 生成日期字符串列表
	 * 
	 * @param fromDate
	 * @param days
	 * @return
	 */
	public static List<String> makeDayList(Date fromDate, int days) {
		return makeDayList(fromDate, "yyyy-MM-dd", days);
	}

	/**
	 * 生成日期字符串列表
	 * 
	 * @param fromDate
	 * @param pattern
	 * @param days
	 * @return
	 */
	public static List<String> makeDayList(Date fromDate, String pattern, int days) {
		List<String> weeks = new ArrayList<String>();
		for (int i = 0; i < days; i++) {
			weeks.add(DateUtil.toFormatString(pattern, add(fromDate, Calendar.DAY_OF_MONTH, i)));
		}
		return weeks;
	}

	/**
	 * 计算周数
	 * 
	 * @param fromDate
	 * @param pattern
	 * @param days
	 * @return
	 */
	public static int getWeeks(int days) {
		return days % 7 == 0 ? days / 7 : days / 7 + 1;
	}


	/**
	 * 获取份的日期范围
	 * 
	 * @param month
	 * @return
	 */
	public static Date[] getDateRangeByMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		makeTimeToZero(calendar);
		Date startDate = calendar.getTime();
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxDay, 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date endDate = calendar.getTime();
		return new Date[] { startDate, endDate };
	}

	/**
	 * 返回季节范围
	 * @param season
	 * @return
	 */
	public static Date[] getDateRangeBySeason(int season) {

		// 一：11号，331号
		// 二：41号，630号
		// 三：71号，930号
		// 四：101号，1231号

		Date startDate = null;
		Date endDate = null;

		int month = 0;
		int monthEnd = 0;
		int day = 1;
		switch (season) {
		case 1:
			month = Calendar.JANUARY;
			monthEnd = Calendar.MARCH;
			day = 31;
			break;
		case 2:
			month = Calendar.APRIL;
			monthEnd = Calendar.JUNE;
			day = 30;
			break;
		case 3:
			month = Calendar.JULY;
			monthEnd = Calendar.SEPTEMBER;
			day = 30;
			break;

		default:
			month = Calendar.OCTOBER;
			monthEnd = Calendar.DECEMBER;
			day = 31;
			break;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		makeTimeToZero(calendar);
		startDate = calendar.getTime();
		calendar.set(Calendar.MONTH, monthEnd);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		makeTimeToMax(calendar);
		endDate = calendar.getTime();
		return new Date[] { startDate, endDate };
	}

	/**
	 * 返回年日期范围
	 * @param year
	 * @return
	 */
	public static Date[] getDateRangeByYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		makeTimeToZero(calendar);
		Date startDate = calendar.getTime();
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		makeTimeToMax(calendar);
		Date endDate = calendar.getTime();
		return new Date[] { startDate, endDate };
	}

	/**
	 * 返回统计年范围
	 * @param year
	 * @return
	 */
	public static Date[] getDateRangeByYearStatistics(int year) {
		// 统计年
		// 10 月 1 日到次年9月 30日
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year - 1);
		calendar.set(Calendar.MONTH, Calendar.OCTOBER);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		makeTimeToZero(calendar);
		Date startDate = calendar.getTime();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		makeTimeToMax(calendar);
		Date endDate = calendar.getTime();
		return new Date[] { startDate, endDate };
	}

	/**
	 * 返回日期范围
	 * @param start
	 * @param end
	 * @return
	 */
	public static Date[] getDateRange(String start, String end) {
		Date startDate = parseDateString(start);
		if (null != startDate) {
			startDate = makeTimeToZero(startDate);
		}
		Date endDate = parseDateString(end);
		if (null != endDate) {
			endDate = makeTimeToMax(endDate);
		}
		return new Date[] { startDate, endDate };
	}

	/**
	 * 返回年龄日期范围
	 * @param start
	 * @param end
	 * @return
	 */
	public static Date[] getDateRangeByAge(String start, String end) {
		int startAge = 0;
		try {
			startAge = Integer.parseInt(start);
		} catch (Exception e) {
			startAge = -1;
		}

		int endAge = 0;
		try {
			endAge = Integer.parseInt(end);
		} catch (Exception e) {
			endAge = -1;
		}

		Date startDate = null;
		Date endDate = null;
		if (-1 != startAge) {
			endDate = makeDateToMax(getBirthdayByAge(startAge));
		}

		if (-1 != endAge) {
			startDate = makeDateToZero(getBirthdayByAge(endAge));
		}
		return new Date[] { startDate, endDate };
	}

	/**
	 * 季节大写
	 * @return
	 */
	public static Map<Integer, String> getSeasonMap() {
		Map<Integer, String> month = new HashMap<Integer, String>();
		month.put(1, "一");
		month.put(2, "二");
		month.put(3, "三");
		month.put(4, "四");
		return month;
	}

	/**
	 * 月份大写
	 * @return
	 */
	public static Map<Integer, String> getMonthMap() {
		Map<Integer, String> month = new HashMap<Integer, String>();
		month.put(Calendar.JANUARY, "一");
		month.put(Calendar.FEBRUARY, "二");
		month.put(Calendar.MARCH, "三");
		month.put(Calendar.APRIL, "四");
		month.put(Calendar.MAY, "五");
		month.put(Calendar.JUNE, "六");
		month.put(Calendar.JULY, "七");
		month.put(Calendar.AUGUST, "八");
		month.put(Calendar.SEPTEMBER, "九");
		month.put(Calendar.OCTOBER, "十");
		month.put(Calendar.NOVEMBER, "十一");
		month.put(Calendar.DECEMBER, "十二");
		return month;
	}

	/**
	 * 获取指定之前年列表
	 * @param count
	 * @return
	 */
	public static List<Integer> getBeforeYears(int count) {
		List<Integer> years = new ArrayList<Integer>();
		int year = getCurrentYear();
		for (int i = 0; i <= 8; i++) {
			years.add(year--);
		}
		return years;
	}

	/**
	 * 当前年
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 日期计算
	 * @param date
	 * @param calendarField
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date getDateByYear(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
//		c.set(Calendar.MONTH, month);
//		c.set(Calendar.DAY_OF_MONTH, day);

		return c.getTime();
	}

    /**
     * datetoTimeStamp
     * @param date
     * @return
     */
    public static Timestamp dateToTimeStamp(Date date){
        return new Timestamp(date.getTime());
    }

    /**
     * timeStampToDate
     * @param timestamp
     * @return
     */
    public static Date timeStampToDate(Timestamp timestamp){
        Date date = new Date();
        try {
            date = timestamp;
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}
