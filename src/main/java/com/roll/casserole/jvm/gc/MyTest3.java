package com.roll.casserole.jvm.gc;

/**
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:+PrintCommandLIneFlags
 * -XX:+SurvivorRatio=8
 * -XX:MaxTenuringThreshold=5
 * -XX:+PrintTenuringDistribution
 *
 * -XX:maxTenuringThresholds作用：在可以自动调节对象晋升（Promote）到老年代阈值的GC中，设置该阈值的最大值。
 * 该参数的默认值是15，CMS中默认值是6，G1默认是15（在JVM中，该数值是由4个bit来表示，最大值是1111，也就是15）
 * 经历了多次GC后，存活的对象会在From Survivor和To Survivor之间来回存放，一个前提是这两个空间有足够的大小来存放这些数据，在GC算法
 * 中，会计算每个对象年龄的大小，如果达到某个年龄后发现总大小已经大雨了Survivor空间的50%，这时候，就需要调整阈值，不能再继续等到
 * 默认的15GC后才完成GC，因为这样会导致Survivor空间不足，所以需要调整阈值，让这些对象那个尽快完成晋升。
 *
 * <p>@author roll
 * <p>created on 2020/8/13 5:12 下午
 */
public class MyTest3 {
    public static void main(String[] args) {
        int size = 1024 * 1024;// 1M
        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        byte[] myAlloc4 = new byte[2 * size];

        System.out.println("end");
    }
}
