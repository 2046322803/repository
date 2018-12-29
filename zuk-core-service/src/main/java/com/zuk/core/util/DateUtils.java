package com.zuk.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtils {

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static SimpleDateFormat sdfDatetimeyMdHm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static SimpleDateFormat sdfDatetimeyMdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * yyyyMMddHHmmss
	 */
	public static SimpleDateFormat sdfDatetimeyMdHmsNo = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * yyyy-MM-dd
	 */
	public static SimpleDateFormat sdfDateyMd = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * MM-dd HH:mm
	 */
	public static SimpleDateFormat sdfDateyMdHm = new SimpleDateFormat("MM-dd HH:mm");

	/**
	 * HH:mm:ss
	 */
	public static SimpleDateFormat sdfTimeHms = new SimpleDateFormat("HH:mm:ss");

	/**
	 * HH:mm
	 */
	public static SimpleDateFormat sdfTimeHm = new SimpleDateFormat("HH:mm");

	public static Date parse(SimpleDateFormat sdf, String source) {
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取某年的第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public static Date getFirstDayOfYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某年的最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public static Date getLastDayOfYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取某年某月的第一天日期
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		// 设置年份
		calendar.set(Calendar.YEAR, year);
		// 设置月份（月份是从0开始的）
		calendar.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int minDays = calendar.getMinimum(Calendar.DATE);
		// 设置日历中月份的最小天数
		calendar.set(Calendar.DAY_OF_MONTH, minDays);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某年某月的最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		// 设置年份
		calendar.set(Calendar.YEAR, year);
		// 设置月份（月份是从0开始的）
		calendar.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		calendar.set(Calendar.DAY_OF_MONTH, maxDays);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 获取某年某月的最大天数
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @return
	 */
	public static int getMaxDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		// 设置年份
		calendar.set(Calendar.YEAR, year);
		// 设置月份（月份是从0开始的）
		calendar.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取某年某月某天的星期
	 * 
	 * DAY_OF_WEEK：1/日, 2/一, 3/二, 4/三, 5/四, 6/五, 7/六
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @param day
	 *            天数
	 * @return
	 */
	public static int getDayOfWeek(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		// 设置年份
		calendar.set(Calendar.YEAR, year);
		// 设置月份（月份是从0开始的）
		calendar.set(Calendar.MONTH, month - 1);
		// 设置日历中月份的天数
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某年某月某天的星期
	 * 
	 * DAY_OF_WEEK：1/日, 2/一, 3/二, 4/三, 5/四, 6/五, 7/六
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @param day
	 *            天数
	 * @return
	 */
	public static String getDayOfWeekForString(int year, int month, int day) {
		String week = "";
		switch (DateUtils.getDayOfWeek(year, month, day)) {
		case 1:
			week = "日";
			break;
		case 2:
			week = "一";
			break;
		case 3:
			week = "二";
			break;
		case 4:
			week = "三";
			break;
		case 5:
			week = "四";
			break;
		case 6:
			week = "五";
			break;
		case 7:
			week = "六";
			break;
		default:
		}
		return week;
	}

	/**
	 * 获取某日期的最小时刻
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getMinOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getMinOfDay(String dateStr) {
		Date date = parse(sdfDatetimeyMdHms, dateStr);
		return getMinOfDay(date);
	}

	/**
	 * 获取某日期的最大时刻
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getMaxOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getMaxOfDay(String dateStr) {
		Date date = parse(sdfDatetimeyMdHms, dateStr);
		return getMaxOfDay(date);
	}

	/**
	 * 获取上周周一的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastMonDayOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		// 前一周的周一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	/**
	 * 获取上周周日的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastSunDayOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, 0);
		// 前一周的周日
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}

	/**
	 * 获取上月第一天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastMonthOfFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取上月最后一天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastMonthOfLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		// 获取上个月的最后一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取下个月第一天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getNextMonthOfFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取下个月最后一天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getNextMonthOfLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		// 获取下个月的最后一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取上个月当天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getLastMonthOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		// 设置为当前时间
		calendar.setTime(date);
		// 设置为上一个月
		calendar.set(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取本月第一天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getMonthOfFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取本月最后一天的时间
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static Date getMonthOfLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		// 获取上个月的最后一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 取得日期是某周的第几天
	 */
	public static int getDayOfWeek(Long dateLong) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateLong);
		int dayOfYear = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfYear;
	}

	/**
	 * 取得日期是某月的第几天
	 */
	public static int getDayOfMonth(Long dateLong) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateLong);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth;
	}

	/**
	 * 取得日期是某年的第几周
	 */
	public static int getWeekOfYear(Long dateLong) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateLong);
		int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
		return weekOfYear;
	}

	/**
	 * 取得2个日期相差几月
	 */
	public static int getMonth(Long startDateLong, Long endDateLong) {
		int monthday = 0;
		Calendar starCal = Calendar.getInstance();
		starCal.setTimeInMillis(startDateLong);

		int sYear = starCal.get(Calendar.YEAR);
		int sMonth = starCal.get(Calendar.MONTH);

		Calendar endCal = Calendar.getInstance();
		endCal.setTimeInMillis(endDateLong);
		int eYear = endCal.get(Calendar.YEAR);
		int eMonth = endCal.get(Calendar.MONTH);

		monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

		return monthday;
	}

	/**
	 * 获取两个时间差<br>
	 * 
	 * @param str0
	 *            ，str1
	 * @return 分钟数
	 */
	public static int getDiffTime(Object str1, Object str2) {
		long minutes = 0;
		Date date1 = parse(sdfDatetimeyMdHm, str1.toString());
		Date date2 = parse(sdfDatetimeyMdHm, str2.toString());
		long diff = date2.getTime() - date1.getTime();
		if (diff > 0) {
			minutes = diff / (1000 * 60);
		}
		return (int) minutes;
	}

	/**
	 * 取得前一天
	 */
	public static String getBeforeDay(String today) {
		Calendar calendar = new GregorianCalendar();
		Date date = parse(sdfDateyMd, today);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return sdfDateyMd.format(calendar.getTime());
	}

	/**
	 * 取得下一天
	 */
	public static String getNextDay(String today) {
		Calendar calendar = new GregorianCalendar();
		Date date = parse(sdfDateyMd, today);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return sdfDateyMd.format(calendar.getTime());
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

}
