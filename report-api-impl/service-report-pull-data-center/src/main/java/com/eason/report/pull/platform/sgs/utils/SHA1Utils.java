package com.eason.report.pull.platform.sgs.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.SignatureException;

/**
 * HMAC_SHA1 Sign生成器.
 *
 * 需要apache.commons.codec包
 *
 */
public class SHA1Utils {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public static String genHMAC2(String key, String idString) throws SignatureException {

        try {
            Charset charset = Charset.forName("utf-8");
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            return new String(Base64.encodeBase64(mac.doFinal(idString.getBytes(charset))), charset);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }

    /**
     * 测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String genHMAC = genHMAC2("FrFBfKlUHvaK9jFMhM6YfEyclWSVljAnc5019KaxaMEi",
                "FrFBfKlUHvaK9jFMhM6YfEyclWSVljAnc5019KaxaMEi2018-05-06T01:39:14.083Z");
        System.out.println(genHMAC.length()); // 28
        System.out.println(genHMAC); // O5fviq3DGCB5NrHcl/JP6+xxF6s=
    }
}