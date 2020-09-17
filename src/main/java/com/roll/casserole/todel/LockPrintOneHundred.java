package com.roll.casserole.todel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>@author roll
 * <p>created on 2020/9/16 5:28 下午
 */
public class LockPrintOneHundred {
    //通过序号来保证线程顺序
    //下一个将要打印的线程序号
    private static volatile int nextPrintThreadSeq = 0;
    //每个线程起始打印的数字
    private static volatile int eachStartNumber = 1;

    private static final Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        int nThread = 3;

        List<Thread> threads = new ArrayList<>();
        Thread thread;
        for (int i = 0; i < nThread; i++) {
            thread = new Thread(new PrintRunnable(i, nThread));
            threads.add(thread);
            thread.start();
        }

        //等待线程结束
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    static class PrintRunnable implements Runnable {
        private final int seq;//当前线程序号
        private final int nThread;//线程总数

        public PrintRunnable(int seq, int nThread) {
            this.seq = seq;
            this.nThread = nThread;
        }

        @Override
        public void run() {

            while (eachStartNumber <= 100) {

                while (nextPrintThreadSeq != seq) {
                    LockSupport.parkNanos(100);//停顿等待
                }

                lock.lock();
                if (nextPrintThreadSeq != seq) {//再次判断
                    lock.unlock();
                    continue;
                }

                int n = eachStartNumber;
                for (int i = 0; i < 3 & n <= 100; i++, n++) {
                    System.out.println("threadSeq: " + seq + ", number: " + n);
                }

                //修改状态
                eachStartNumber += 3;
                nextPrintThreadSeq = (seq + 1) % nThread;

                lock.unlock();
            }

        }
    }
}
