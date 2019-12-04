package com.eason.transfer.openapi.user.api.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.URLEncoder;

public class PushUtil {
	private static Logger log = LoggerFactory.getLogger(PushUtil.class);
    /**
     * 发送文本验证码
     */
    public static boolean sendTextCode(String code, String phone){
		String address = "120.76.25.160";//远程地址：不带http://
		int port = 7788;//远程端口
		String account = "xxll";//账户
		String token = "520620";//token
		String mobile = phone;//发送手机号
		String body = "【短信测试】验证码："+code;//短信内容
		int userId=186;//用户Id
		KXTSmsSDK kxtsms = new KXTSmsSDK();
		kxtsms.init(address, port, account, token,userId);
		try{
			body = URLEncoder.encode(body,"UTF-8");//URL编码 UTF-8方式
			String result = kxtsms.send(mobile,body);
			JAXBContext context = JAXBContext.newInstance(KXTSMSVo.class);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        KXTSMSVo vo = (KXTSMSVo)unmarshaller.unmarshal(new StringReader(result));
	        System.out.println(vo);
	        if("Success".equals(vo.getReturnstatus())){
	        	return true;
	        }else{
	        	log.error("错误码=" + vo.getReturnstatus() +" 错误信息= "+vo.getMessage());
	        	return false;
	        }
		}catch (Exception e) {
			log.error("发送验证码异常",e);
		}
        return false;
    }
    public static void main(String[] args){
    	PushUtil.sendTextCode("1234","18971487022"); //18971487022
	}
}
