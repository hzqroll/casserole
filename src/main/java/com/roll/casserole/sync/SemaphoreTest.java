package com.roll.casserole.sync;

import java.util.concurrent.Semaphore;

/**
 * @author roll
 * created on 2020/2/27 3:42 下午
 */
public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                semaphore.acquireUninterruptibly();
                Thread.sleep(10000);
                System.out.println("1 thread get premit");
            } catch (InterruptedException e) {
                System.out.println("1 thread exception");
                e.printStackTrace();
            }
            semaphore.release();
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            try {
                for (; ; ) {
                    semaphore.acquireUninterruptibly();
                    System.out.println("2 thread get premit");
                }
            } catch (Exception e) {
                System.out.println("2 thread");
                e.printStackTrace();
            }
        });
        thread2.start();

        new Thread(() -> {
            thread2.interrupt();
        }).start();
    }
}
