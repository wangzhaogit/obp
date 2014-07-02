package com.obp.system.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.obp.system.common.constants.SystemConstants;

public class DateUtils {
	
	/**
	 * @Description:获取当前时间
	 * @author: wangzhao
	 * @date: 2014年4月25日下午1:05:32
	 * @mail: wangzhao@huateng.com
	 * @return
	 */
	public static String getCurrentDateTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(SystemConstants.FORMAT_DATE_TIME_24);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	/**
	 * @Description:获取当前参数日期
	 * @author: wangzhao
	 * @date: 2014年4月25日下午7:34:40
	 * @mail: wangzhao@huateng.com
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	/**
	 * @Description:获取当前日期10位样式
	 * @author: wangzhao
	 * @date: 2014年4月25日下午1:05:45
	 * @mail: wangzhao@huateng.com
	 * @return
	 */
	public static String getCurrentDate_10() {
		return getCurrentDateTime(SystemConstants.FORMAT_DATE_10);
	}
	
	public static String getCurrentDate_8() {
		return getCurrentDateTime(SystemConstants.FORMAT_DATE_8);
	}
	
	/**
	 * @Description:获取当前周
	 * @author: wangzhao
	 * @date: 2014年4月25日下午1:05:56
	 * @mail: wangzhao@huateng.com
	 * @param strdate
	 * @return
	 */
	public static String getWeekDayByDate(String strdate) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		SimpleDateFormat sdfInput = new SimpleDateFormat(SystemConstants.FORMAT_DATE_10);
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		try {
			date = sdfInput.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}

}
