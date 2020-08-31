package com.roll.casserole.jvm.gc;

/**
 * java -XX:+PrintCommandLineFlags 打印出jvm启动的默认参数
 * -XX:PretenureThreshold=1024 超过1k，直接在老年代分配
 *
 * <p>@author roll
 * <p>created on 2020/8/13 8:18 上午
 */
public class MyTest2 {
    public static void main(String[] args) throws InterruptedException {
        int size = 1024 * 1024;
        byte[] alloc = new byte[5 * size];
        Thread.sleep(10000);
    }
}
