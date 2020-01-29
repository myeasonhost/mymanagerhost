package com.ds.money.service;


import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GetKey {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
    //获取当天美东时间
    public static String updateTime() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT-4:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date());
    }

    public static String toMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();

            StringBuilder buf = new StringBuilder("");
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

    @Test //钱包key
    public void getMoneyKey() {
        String fromKey = "ds_money_key";
        String userName = "eason";
        String key = toMD5(fromKey+userName);
        key = "12345"+key+"123456";
        System.out.println(key);
    }
    @Test //API key
    public void getAPIKey() throws Exception{
        String userName = "lipan11";
        //AG
 String keyB = "df943b52c6182761bf75e7cbfc1ad85d";
        //bbin的keyB
        //String keyB = "f211019df3fce51014fb41c7c80238a3";
        String key = userName+keyB+updateTime();
        key = "1234"+toMD5(key)+"0";
        System.out.println(key);

        System.out.println(toMD5("eB4a2xkiFmAt8"));
    }


}
