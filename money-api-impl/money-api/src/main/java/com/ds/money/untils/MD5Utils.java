package com.ds.money.untils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Utils {
            /**
             * md5加密
             * @param old
             * @return
             *
             */
            public static String Md5String(String old) {
                try {
                    //java自带工具包MessageDigest
                    MessageDigest md5 = MessageDigest.getInstance("md5");
                    //实现Base64的编码
                    BASE64Encoder base64 = new BASE64Encoder();
                    //进行加密
                    String newStr = base64.encode(md5.digest(old.getBytes("utf-8")));
                    return newStr;//返回加密后的字符
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
}
