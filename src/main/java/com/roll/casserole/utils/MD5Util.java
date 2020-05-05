package com.roll.casserole.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author roll
 * created on 2020/5/5 2:33 下午
 */
public class MD5Util {
    public static String encode(String s) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        byte[] b = md.digest();

        for (int value : b) {
            int i = value;
            if (i < 0) {
                i += 256;
            }

            if (i < 16) {
                sb.append("0");
            }

            sb.append(Integer.toHexString(i));
        }

        return sb.toString();
    }
}
