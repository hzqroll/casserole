package com.roll.casserole.sync;

import java.util.concurrent.locks.LockSupport;

/**
 * @author roll
 * created on 2020/3/4 5:55 下午
 */
public class ThreadParkTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread 1 start");
            LockSupport.park();
            System.out.println("thread 1 stop");
        });

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread 1 unpark");
        LockSupport.unpark(thread1);
    }
}
