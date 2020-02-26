package com.roll.casserole.jvm.classloader;

import java.sql.Timestamp;

/**
 * @author roll
 * created on 2019-12-26 08:47
 */
public class MyTest26 {
    public static void main(String[] args) {
        Object a = "123";
        int b = Integer.valueOf(a.toString());
        System.out.println(b);
    }
}
