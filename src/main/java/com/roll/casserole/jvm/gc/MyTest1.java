package com.roll.casserole.jvm.gc;

/**
 * <p>@author roll
 * <p>created on 2020/8/12 8:48 上午
 */
public class MyTest1 {
    public static void main(String[] args) {
        int size = 1024 * 1024;// 1M
        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        //byte[] myAlloc4 = new byte[2 * size];

        System.out.println("end");
    }
}
