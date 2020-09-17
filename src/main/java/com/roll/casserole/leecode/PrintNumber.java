package com.roll.casserole.leecode;

import java.util.concurrent.Semaphore;

/**
 * 三个线程，顺序打印数字
 * 第一个线程打印1，2，3，第二个打印4，5，6，第三个打印7，8，9 一次类推，不能乱序
 * <p>@author roll
 * <p>created on 2020/9/17 11:32 上午
 */
public class PrintNumber {
    static volatile int count = 1;

    // 当前需要打印的顺序
    static int threadN = 1;

    private final static Semaphore lock = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(new printThread(1)).start();
        new Thread(new printThread(2)).start();
        new Thread(new printThread(3)).start();
    }

    static class printThread implements Runnable {
        // 线程编号
        int thread;

        public printThread(int thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            while (count <= 100) {
                while (threadN == thread) {
                    try {
                        lock.acquire();
                        int index = 0;
                        while (index < 3) {
                            index++;
                            System.out.println("线程：" + thread + ", 打印：" + (count++));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        lock.release();
                    } finally {
                        lock.release();
                        if (threadN % 3 == 0) {
                            threadN = 1;
                        } else {
                            threadN++;
                        }
                    }
                }
            }
        }
    }
}
