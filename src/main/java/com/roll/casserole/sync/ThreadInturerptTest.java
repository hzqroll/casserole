package com.roll.casserole.sync;

/**
 * @author roll
 * created on 2020/2/29 3:46 下午
 */
public class ThreadInturerptTest implements Runnable {
    @Override
    public void run() {
        System.out.println("running start!");
//        do {
//            if (Thread.currentThread().isInterrupted()) {
//                System.out.println("current thread is interrupted!");
//            }
//        } while (true);
        //System.out.println("running stop!");
    }

    public static void main(String[] args) {
        ThreadInturerptTest threadInturerptTest = new ThreadInturerptTest();
        Thread thread = new Thread(threadInturerptTest);
        thread.start();

        thread.interrupt();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.notify();
    }
}
