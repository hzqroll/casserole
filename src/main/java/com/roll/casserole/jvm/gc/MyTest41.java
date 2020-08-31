package com.roll.casserole.jvm.gc;

/**
 * -verbose:gc
 * -Xmx200M
 * -Xmn50M
 * -XX:TargetSurvivorRatio=60
 * -XX:+PrintGCDataStamps
 * -XX:+UserConcMarkSweepGC
 * -XX:+UseParNewGC
 * -XX:MaxTenuringThreshold=3
 * <p>
 * * -XX:TargetSurvivorRatio=60 如果占据了survivor60%，会重新计算晋升阈值
 * <p>@author roll
 * <p>created on 2020/8/13 5:36 下午
 */
public class MyTest41 {
    public static void main(String[] args) throws InterruptedException {
        byte[] byte_1 = new byte[521 * 1024];
        byte[] byte_2 = new byte[521 * 1024];
        myGc();
        Thread.sleep(1000);
        System.out.println(11111);
    }

    private static void myGc() {
        for (int i = 0; i < 40; i++) {
            byte[] byteArray = new byte[1024 * 1024];
        }
    }
}
