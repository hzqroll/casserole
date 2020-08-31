package com.roll.casserole.jvm.gc;

/**
 * -verbose:gc
 * <p>
 * -Xms20M
 * <p>
 * -Xmx20M
 * <p>
 * -Xmn10M
 * <p>
 * -XX:+PrintGCDetails
 * <p>
 * -XX:SurvivorRatio=8
 * <p>
 * -XX:+UseConcMarkSweepGC
 * [GC (Allocation Failure) [ParNew: 4790K->1023K(9216K), 0.0013700 secs] 4790K->1118K(19456K), 0.0014071 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * 1111
 * [GC (Allocation Failure) [ParNew: 5360K->271K(9216K), 0.0080141 secs] 5454K->5241K(19456K), 0.0080464 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
 * 2222
 * [GC (Allocation Failure) [ParNew: 4579K->135K(9216K), 0.0033001 secs] 9549K->9202K(19456K), 0.0033281 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * [GC (CMS Initial Mark) [1 CMS-initial-mark: 9066K(10240K)] 13333K(19456K), 0.0001800 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark-start]
 * 3333
 * [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-preclean-start]
 * 4444
 * [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-abortable-preclean-start]
 * [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (CMS Final Remark) [YG occupancy: 6554 K (9216 K)][Rescan (parallel) , 0.0006500 secs][weak refs processing, 0.0000145 secs][class unloading, 0.0003310 secs][scrub symbol table, 0.0008358 secs][scrub string table, 0.0001861 secs][1 CMS-remark: 9066K(10240K)] 15621K(19456K), 0.0020756 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
 * [CMS-concurrent-sweep-start]
 * Heap
 * par new generation   total 9216K, used 6718K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
 * eden space 8192K,  80% used [0x00000007bec00000, 0x00000007bf26daf8, 0x00000007bf400000)
 * from space 1024K,  13% used [0x00000007bf500000, 0x00000007bf521e40, 0x00000007bf600000)
 * to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
 * concurrent mark-sweep generation total 10240K, used 8973K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
 * Metaspace       used 3049K, capacity 4496K, committed 4864K, reserved 1056768K
 * class space    used 335K, capacity 388K, committed 512K, reserved 1048576K
 * <p>@author roll
 * <p>created on 2020/8/16 5:13 下午
 */
public class MyTest5 {
    public static void main(String[] args) {
        int size = 1024 * 1024;// 1M
        byte[] myAlloc1 = new byte[4 * size];
        System.out.println(1111);

        byte[] myAlloc2 = new byte[4 * size];
        System.out.println(2222);

        byte[] myAlloc3 = new byte[4 * size];
        System.out.println(3333);

        byte[] myAlloc4 = new byte[2 * size];
        System.out.println(4444);
    }
}
