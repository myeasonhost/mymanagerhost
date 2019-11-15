package com.eason.transfer.openapi.user;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTransferOpenApiApplicationTests {

    @Test
    public void contextLoads1() throws Exception {
        Long timestamp=System.currentTimeMillis();
        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
        String orderId="91208"+date+"eason";
        String params="s=0&account=eason&money=0&orderid="+orderId+"&ip=127.0.0.1&lineCode=1000&KindID=0";
        String DESKey="273859B4ADE935A2";
        String MD5Key="C0BA1D7016A6CFA1";
        System.out.println("timestamp="+timestamp);
        System.out.println("params="+Encrypt.AESEncrypt(params,DESKey));
        System.out.println("key="+Encrypt.MD5("91208"+timestamp+ MD5Key));
    }

    @Test
    public void contextLoads2() throws Exception {
        Long timestamp=System.currentTimeMillis();
        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
        String orderId="91208"+date+"eason";
        String params="s=2&account=eason&money=100&orderid="+orderId;
        String DESKey="273859B4ADE935A2";
        String MD5Key="C0BA1D7016A6CFA1";
        System.out.println("timestamp="+timestamp);
        System.out.println("params="+Encrypt.AESEncrypt(params,DESKey));
        System.out.println("key="+Encrypt.MD5("91208"+timestamp+ MD5Key));
    }

    @Test
    public void contextLoads3() throws Exception {
        Long timestamp=System.currentTimeMillis();
        String date= DateFormatUtils.format(new Date(timestamp),"yyyyMMddHHmmssSSS");
        String orderId="91208"+date+"eason";
        String params="s=6&startTime=1573459220000&endTime=1573461620000";
        String DESKey="273859B4ADE935A2";
        String MD5Key="C0BA1D7016A6CFA1";
        System.out.println("timestamp="+timestamp);
        System.out.println("params="+Encrypt.AESEncrypt(params,DESKey));
        System.out.println("key="+Encrypt.MD5("91208"+timestamp+ MD5Key));
    }

}
