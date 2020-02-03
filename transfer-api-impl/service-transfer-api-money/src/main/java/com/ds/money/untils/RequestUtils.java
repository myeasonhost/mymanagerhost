package com.ds.money.untils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author 光光
 *
 */
public class RequestUtils {

	protected static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

	
	public static void showParams(HttpServletRequest request) {
		// 解析参数.
				Enumeration map = request.getParameterNames();
				StringBuffer br = new StringBuffer(">>>客户端(").append(
						request.getRemoteAddr()).append("),请求参数:\r\n");
				while (map.hasMoreElements()) {
					Object o = map.nextElement();
					String key = (o == null ? "" : o.toString());
					br.append(key + "=" + request.getParameter(key)).append("\r\n");
				}
				String param = br.toString();
				logger.info(param);
    }
	/**
	 * 接收json请求参数
	 * @param request
	 * @return
	 */
	public static String getParams(HttpServletRequest request) {
		// 解析参数.
		Enumeration map = request.getParameterNames();
		String param =null;
		while (map.hasMoreElements()) {
			Object o = map.nextElement();
			param = (o == null ? "" : o.toString());
		}
		return param;
    }

	/**
	 * 接收json请求参数
	 * @param request
	 * @return
	 */
	public static Map getParamsToMap(HttpServletRequest request) {
		// 解析参数.
		Enumeration map = request.getParameterNames();
		Map<String,Object> paramMap = new HashMap();
		while (map.hasMoreElements()) {
			Object o = map.nextElement();
			if(null != o && StringUtils.isNotBlank(o.toString())){
				paramMap.put(o.toString(),request.getParameter(o.toString()));
			}
		}
		return paramMap;
	}

	public static String getClientIp(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        //String ip = request.getHeader("X-real-ip");

        //logger.debug("x-forwarded-for = {}", ip);
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP"); 
            logger.debug("Proxy-Client-IP = {}", ip); 
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.debug("WL-Proxy-Client-IP = {}", ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.debug("RemoteAddr-IP = {}", ip); 
        }
        if(StringUtils.isNotBlank(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;

    }

	/**
	 * 参数非空验证
	 * @param data
	 * @param keys
	 * @return
	 */
	public static String valiParamNotNull(Map data,Object[] keys ){
		StringBuffer str= new StringBuffer();
		for(Object key:keys){
			if(key.toString().indexOf(":")!=-1){
				String[] strs = key.toString().split(":");
				if(strs[0].length()>Integer.parseInt(strs[1])){
					str.append(strs[0]+"的长度限制为"+strs[1]+",");
				}
			}else if(isNull(data.get(key)))
				str.append(key.toString()+",");
		}
		if(StringUtils.isNotBlank(str.toString())){
			return str.substring(0,str.length()-1);
		}
		return str.toString();
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param str
	 * @return 空返回 true,非空返回false
	 */
	public static boolean isNull(Object str) {
		if (str == null) {
			return true;
		} else if (str.toString().trim().length() == 0) {
			return true;
		} else if (str.toString().trim() == "null") {
			return true;
		}
		return false;
	}

}
