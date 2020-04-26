package com.wd.tech.net;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 13:44
 * @classname :EncryptionUtil
 */
public class EncryptionUtil {
    //MD5加密
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
