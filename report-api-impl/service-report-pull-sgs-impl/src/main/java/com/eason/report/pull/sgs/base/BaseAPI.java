package com.eason.report.pull.sgs.base;

import com.eason.report.pull.ag.exception.AgException;

import java.security.MessageDigest;
import java.util.Base64;

public class BaseAPI {
    private static final String pidtoken="691E87938EB3A6BD774CA98D5497B081"; //明码

    protected String getKey(String params) throws AgException {
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder base64Encoder = Base64.getEncoder();
            // 加密字符串
            String key = base64Encoder.encodeToString(md5.digest((params+pidtoken).getBytes("utf-8")));
            return key;
        }catch (Exception e){
            throw new AgException(e);
        }
    }
}
