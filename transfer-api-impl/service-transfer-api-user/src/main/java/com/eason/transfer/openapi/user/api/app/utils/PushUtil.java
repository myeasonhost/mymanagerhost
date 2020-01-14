package com.eason.transfer.openapi.user.api.app.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
		LinkedMultiValueMap map = new LinkedMultiValueMap<>();
		map.add("user", "MMYZM");
		map.add("passwd", "B2D11B6121865F36421DE8549EB62258");
		map.add("msg","【悦可】验证码为："+code);
		map.add("mobs",phone);
		try {
			String str = doPost(url, map);
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
	}

	public static String doPost(String url, LinkedMultiValueMap params) {
		RestTemplate restTemplate = new RestTemplate();
		String  message =null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity requestBody = new HttpEntity(params, headers);
			ResponseEntity<String> result = restTemplate.postForEntity(url,requestBody,String.class);
			message = result.getBody();
		} catch (Exception e) {
			log.error("执行HTTP Post请求" + url + "时，发生异常！");
		}
		return message;
	}
}
