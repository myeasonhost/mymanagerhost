package com.ds.money.untils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class DateUtils {
	public static boolean isValidDate(String str) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = (Date) formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean isValidDate(String str,String dateformat) {
		DateFormat formatter = new SimpleDateFormat(dateformat);
		try {
			Date date = (Date) formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}
//	public static Date getGMT4Date(Date date) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		format.setTimeZone(TimeZone.getTimeZone("GMT-4"));////美国中部分时间
//		return Timestamp.valueOf(format.format(date.getTime()));
//	}
	public static Date getGMT4Date(String dateStr,String patterns) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(patterns);
		format.setTimeZone(TimeZone.getTimeZone("GMT-4"));
		return format.parse(dateStr);
	}
	public static void main(String[] args) {
		System.out.println(isValidDate("2015-07-07 23:24:23"));
	}
}
