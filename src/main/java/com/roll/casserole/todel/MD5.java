package com.roll.casserole.todel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zongqiang.hao
 * created on 2018/10/11 上午9:04.
 */
public class MD5 {
    public MD5() {
    }

    public static String encode(String s) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        byte[] b = md.digest();

        for(int offset = 0; offset < b.length; ++offset) {
            int i = b[offset];
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
