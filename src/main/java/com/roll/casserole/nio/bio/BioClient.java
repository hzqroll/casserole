package com.roll.casserole.nio.bio;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

/**
 * <p>@author roll
 * <p>created on 2020/9/22 11:25 上午
 */
public class BioClient {
    public static final String KEY = "V8pMn5E7rsqCv5KG";

    public static final String IV = "1ci5crnda6ojzgtr";

    private static String content = "7IfkivojEsJ8qDlQ/xZ+rhHLfgnIxE6Xgy7vvPKxUqhcwm5Y9m+xCoBG0GWUc05jCArJ6pDd6Ve4fb49+9jLkMuWNRwVqou/90wdVX1CvB0LInqYRBoaU/lsCidVV+SPLVYMvMk0eoeNZ9jCRMgQaHIuVE/fy1EkEMAiJ5D0fB8DNmwEAEYRp2LdDBUCknA3";

    public static void main(String[] args) {
        System.out.println(decryptAES(""));
    }

    public static String decryptAES(String content) {
        try {
            byte[] raw = KEY.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ips);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
