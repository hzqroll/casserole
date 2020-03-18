package com.roll.casserole.sync;

/**
 * @author zongqiang.hao
 * created on 2020-03-12 21:07.
 */
public class SimpleSyncTest {
    public synchronized void print() {
        System.out.println("1");
    }

    public void get() {
        synchronized (this) {
            System.out.println("2");
        }
    }
}
