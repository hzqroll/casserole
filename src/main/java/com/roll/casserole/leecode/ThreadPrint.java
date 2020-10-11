package com.roll.casserole.leecode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9....,15]，要求遍历数组，
 * 遇到可以同时被3和5整除的数字，打印C；
 * 遇到仅能被5整除的数字，打印B；
 * 遇到仅能被3整除的数字，打印A；
 * 其他打印数字本身；
 * <p>
 * 要求四个线程，每一个线程执行一个打印方法。
 * <p>@author roll
 * <p>created on 2020/9/22 7:47 下午
 */
public class ThreadPrint {
    public static volatile AtomicInteger i = new AtomicInteger(0);

    public static final Semaphore lock = new Semaphore(1);

    static int[] arr = new int[100];

    static int thread;

    public static void main(String[] args) {
        for (int j = 0; j < 100; j++) {
            arr[j] = j;
        }

        new Thread(new print(0)).start();
        new Thread(new print(1)).start();
        new Thread(new print(2)).start();
        new Thread(new print(3)).start();
    }

    static class print implements Runnable {

        private final int threadNum;

        public print(int threadNum) {
            this.threadNum = threadNum;
        }

        @Override
        public void run() {
            while (i.get() < arr.length) {
                // 从0 开始判断
                if (threadNum == 0) {
                    if (arr[i.get()] % 3 == 0 && arr[i.get()] % 5 == 0) {
                        try {
                            lock.acquire();
                            System.out.println("C");
                            i.incrementAndGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.release();
                        }

                    }
                }
                if (threadNum == 1) {
                    if (arr[i.get()] % 3 == 0 && arr[i.get()] % 5 != 0) {

                        try {
                            lock.acquire();
                            System.out.println("A");
                            i.incrementAndGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.release();
                        }
                    }
                }
                if (threadNum == 2) {
                    if (arr[i.get()] % 3 != 0 && arr[i.get()] % 5 != 0) {
                        try {
                            lock.acquire();
                            System.out.println("B");
                            i.incrementAndGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.release();
                        }
                    }
                }
                if (threadNum == 3) {
                    if (arr[i.get()] % 3 != 0 && arr[i.get()] % 5 != 0) {
                        try {
                            lock.acquire();
                            System.out.println(arr[i.get()]);
                            i.incrementAndGet();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.release();
                        }
                    }
                }
            }
        }
    }
}

