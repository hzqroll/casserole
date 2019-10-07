package com.roll.casserole.jvm.gc;

/**
 * 参数： -verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 打印结果：
 * <pre>
 * [GC (Allocation Failure) [PSYoungGen: 8010K->1017K(9216K)] 8010K->5212K(19456K), 0.0040061 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 * Heap
 * PSYoungGen      total 9216K, used 7485K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 * eden space 8192K, 78% used [0x00000007bf600000,0x00000007bfc51298,0x00000007bfe00000)
 * from space 1024K, 99% used [0x00000007bfe00000,0x00000007bfefe480,0x00000007bff00000)
 * to   space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 * ParOldGen       total 10240K, used 4194K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 * object space 10240K, 40% used [0x00000007bec00000,0x00000007bf018bb8,0x00000007bf600000)
 * Metaspace       used 2972K, capacity 4496K, committed 4864K, reserved 1056768K
 * class space    used 325K, capacity 388K, committed 512K, reserved 1048576K
 * created on 2019-10-07 11:17
 * </pre>
 */
public class MinorGcTest1 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] buffer1, buffer2, buffer3, buffer4;
        buffer1 = new byte[4 * _1MB];
        buffer2 = new byte[4 * _1MB];
        buffer3 = new byte[4 * _1MB];
        buffer4 = new byte[4 * _1MB];

    }
}
