package com.ds.money.untils;

import org.apache.commons.lang.StringUtils;


public class MoneyConfigUtils {
	public static final String SINGLE = "single";
	public static final String NOT_SINGLE = "";
	//e10adc3949ba59abbe56e057f20f883e
	//key=A+B+C（验证码组合方式）A无意义字符串长度5码 B MD5（website+username）C 无意义字符串长度6码
	public static String getKeyMd5(String key){
		if(StringUtils.isBlank(key)||key.length()!=43){
			System.out.println(">>>>>>>>>>>");
			return "";
		}
		return StringUtils.substring(key, 5, 37);
	}
	/**
	 * 获取用户所在的表名
	 * @param username
	 * @return
	 */
	public static String getTableName(String username){
//		String str = MD5.hashToBase64String(username);
//		String index = str.substring(0, 1);
//		Character c = index.charAt(0);
//		return "ds_member_money_"+Integer.valueOf(c);
		return "ds_member_money";
	}
	public static String getCheckTableName(String username,String typeNum){
//		String str = MD5.hashToBase64String(username);
//		String index = str.substring(0, 1);
//		Character c = index.charAt(0);
//		if(typeNum.equals(MoneyConfigUtils.SINGLE)){
//			return "ds_check_id_single_"+Integer.valueOf(c);
//		}
//		return "ds_check_id_"+Integer.valueOf(c);
		return "ds_check_id";
	}
	
	
	//f14a3b8ae43b688b76f89144a4b11655
	
	//66666f14a3b8ae43b688b76f89144a4b11655666666
	public static void main(String[] args) {
		String str = EncryptUtils.toMD5("asa"+"wewe"+"20000");
		System.out.println(str);
		System.out.println("66666"+str+"666666");
		String key = "kaowjwza807c2f53282ce73e67d7868827ddcechwnvyx";
		System.out.println(key.length());
		
	}
	
	
	
}
