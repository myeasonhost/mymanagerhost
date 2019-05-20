package com.eason.report.pull.h8.utils;

import org.onetwo.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	static SimpleDateFormat sdfdate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
	public static Date getGMT4date(String datestr) throws Exception{
		Date d = sdfdate.parse(datestr);
		return DateUtil.addHours(d, -12);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getGMT4date("2015-11-11 13:47:56"));
	}
	
}
