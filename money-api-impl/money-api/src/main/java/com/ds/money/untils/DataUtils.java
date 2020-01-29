package com.ds.money.untils;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试数据生成工具类.
 * 
 * @author 
 */
public class DataUtils {
	private static Random random = new Random();

	/**
	 * 返回随机ID.
	 */
	public static long randomId() {
		return random.nextLong();
	}

	/**
	 * 返回随机名称, prefix字符串+5位随机数字.
	 */
	public static String randomName(String prefix) {
		return prefix + random.nextInt(10000);
	}

	/**
	 * 从输入list中随机返回一个对象.
	 */
	public static <T> T randomOne(List<T> list) {
		return randomSome(list, 1).get(0);
	}

	/**
	 * 从输入list中随机返回随机个对象.
	 */
	public static <T> List<T> randomSome(List<T> list) {
		return randomSome(list, random.nextInt(list.size()));
	}

	/**
	 * 从输入list中随机返回count个对象.
	 */
	public static <T> List<T> randomSome(List<T> list, int count) {
		Collections.shuffle(list);
		return list.subList(0, count);
	}

	/**
	 * 校验是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkNum(String str) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}
	public static String getStringNumber(String remit){
		String result = null;
		try{
			BigDecimal b = new BigDecimal(remit);
			result = b.toPlainString();
		}catch(Exception e){
			result = null;
		}
		return result;
	}
	/**
	 * 代码混淆器
	 * @return
	 */
	public static int get() {
		int i = 67675;
		int j = 43343;
		try {
			i = i + j;
		} catch (Exception e) {
			i = i + j;
			try {
				i = i + j;
			} catch (Exception err) {
				i += j;
			} finally {
				i *= j;
			}
		} finally {
			try {
				i *= j;
			} catch (Exception e) {
				i *= j;
			} finally {
				try {
					i -= j;
				} catch (Exception e) {
					i += j;
				} finally {
					i = i + j + i;
				}
			}
			i = i + j + i;
		}
		return i;
	}
	public static void main(String[] args) {
		//System.out.println(MD5.hashToBase64String("shiwan11fa"+DataUtils.get()+""+new BigDecimal("100000").setScale(5)));

//			HproseTcpClient client = new HproseTcpClient("tcp://10.200.201.17:20005");
//			try {
//				String str = (String) client.invoke("getMoney", new Object[]{"hprose"});
//				System.out.println(str);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

	}

}
