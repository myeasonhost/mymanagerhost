package com.eason.report.pull.platform.bbin.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptUtil {
    /**
     * 返回数组加密后的md5值
     * @param strArr
     * @return
     */
    private static String encrypt(String[] strArr,String userkey){
        if(strArr.length == 0 || userkey == null){
            return null;
        }
        Arrays.sort(strArr);
        StringBuffer sb = new StringBuffer();
        for (String str : strArr) {
            sb.append(StringUtils.substringAfter(str, "="));
        }
        sb.append(userkey);
        return getMD5(sb.toString());
    }

    /**
     * 加密
     * @param param
     * @return
     */
    public static String encrypt(String param,String userkey) {
        String [] strArr = param.split("\\&");
        if(strArr.length == 0 || userkey == null){
            return null;
        }

        return encrypt(strArr,userkey);
    }

    public static String getMD5(String str){
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();

            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < byteDigest.length; offset++) {
                int i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
