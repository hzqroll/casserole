package com.roll.casserole.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * 扩展类加载器
 *
 * @author roll
 * created on 2019-11-17 21:09
 */
public class MyTest19 {
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
        //sun.misc.Launcher$ExtClassLoader@7a07c5b4
        //sun.misc.Launcher$AppClassLoader@18b4aac2
    }
}
