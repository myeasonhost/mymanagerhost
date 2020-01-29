package com.ds.money.untils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: 
 * @createTime: 
 * @version: 1.0
 * @desc :
 */
public class ParseData {
	
	public  final  static String CDouble ="[0-9]+(.[0-9]+)?";
	public  final  static String CInt ="0*(0|[1-9][0-9]{0,9})";
	public  final  static String CLong ="0*(0|[1-9][0-9]{0,17})";
	
	public static boolean CheckInt(Object obj) throws Exception {
		try {
			Pattern p = Pattern.compile(CInt);
			Matcher m = p.matcher(obj.toString());
			return m.matches();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public static boolean CheckLong(Object obj) throws Exception {
		try {
			Pattern p = Pattern.compile(CLong);
			Matcher m = p.matcher(obj.toString());
			return m.matches();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public static boolean CheckIsDouble(Object obj) {
		try {
			Pattern p = Pattern.compile(CDouble);
			Matcher m = p.matcher(obj.toString());
			return m.matches();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

}
