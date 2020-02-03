package com.ds.money.service;

import com.alibaba.druid.filter.config.ConfigTools;
import com.yooyo.util.MD5;
import hprose.client.HproseTcpClient;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class Test1 {
	public static void main(String[] args) throws Exception {//XEY6JTUod/V8f4ENPLG9sQ==
		test5();

	}
	public static void test1() throws Exception{
		System.out.println(MD5.hashToBase64String("aaaaa-12280938470.00"));//yQ329nq6V4wCVVcG7EsgZw==
		System.out.println(MD5.hashToBase64String("aaaaa-12280938470.00"));//yQ329nq6V4wCVVcG7EsgZw==
		System.out.println(new BigDecimal("0").setScale(2));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = sdf.parse("2008-08-08 12:10:12");
		//	sdf.
		System.out.println("EASON".substring(0, 2));


		System.out.println(ConfigTools.decrypt("H5g9f7EiTew7lxG1dTGYPQrFP87fHk4HmCI3lMwVP4NJXYUTVJ73D6eTv8RP3feYkQG3e+Jggspy7woX6oBLmQ=="));

		System.out.println(ConfigTools.encrypt("YjZmYzc1M2UyYmE1ZjgxNGRm"));

		System.out.println("game_center="+ConfigTools.encrypt("NjY2YTI5ZGVlNzIyM2JiNDZm"));
		System.out.println("game_pic="+ConfigTools.encrypt("MWM5OTVhNjA2NmVkMDNmMjEz"));
		System.out.println("game_report="+ConfigTools.encrypt("NTZhNzM5Zjk4NTMzODAxZWRj"));
		System.out.println("game_money="+ConfigTools.encrypt("YWFkOTM2MDlmMjNhYjk3MzE1"));
		System.out.println("game_service="+ConfigTools.encrypt("ZWNjZWNlMDYzZmQ5MGUyZmE1"));
	}

	public static void test5() throws Exception{
		HproseTcpClient client = new HproseTcpClient("tcp://172.19.0.4:20005/");
		String json= "{\n" +
				"\"username\": \"test130\",\n" +
				"\"siteId\": \"5000\",\n" +
				"\"agents\": \"agents\",\n" +
				"\"world\": \"parker\",\n" +
				"\"corprator\": \"bparker\",\n" +
				"\"superior\": \"1\",\n" +
				"\"company\": \"1102932216\"\n" +
				"}";
		JSONObject request = JSONObject.fromObject(json);
		// System.out.println(request.toString());
		Object obj= client.invoke("setMemberData", new Object[]{request.toString()});
		System.out.println( obj);
	}
}
