package com.eason.transfer.openapi.user.api.app.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class PushUtil {
	private static Logger log = LoggerFactory.getLogger(PushUtil.class);
    /**
     * 发送文本验证码
     */
    public static boolean sendTextCode(String code, String phone){
		String url = "http://www.shizhicom.cn:8080/SendMessage/SendSms_API";
		Map<String, String> json = new HashMap<>();
		json.put("user", "MMYZM");
		json.put("passwd", "B2D11B6121865F36421DE8549EB62258");
		json.put("msg","【悦可】验证码为："+code);
		json.put("mobs",phone);
		try {
			String str = doPost(url, json, "UTF-8", true);
			if(str==null ||!str.substring(0,1).equals("0")){
				log.error(" 错误信息= " + str);
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			log.error("发送验证码异常", e);
		}
		return false;
    }
    public static void main(String[] args){
    	PushUtil.sendTextCode("1234","18971487022"); //18971487022
	}

	public static String doPost(String url, Map<String, String> _params, String charset, boolean pretty) throws Exception {

		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);

		// 设置Http Post数据
		if (_params != null) {
			for (Map.Entry<String, String> entry : _params.entrySet()) {
				method.setParameter(entry.getKey(),String.valueOf(entry.getValue()));
			}
		}
		try {
			method.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				// 读取为 InputStream，在网页内容数据量大时候推荐使用
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
						charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}
}
