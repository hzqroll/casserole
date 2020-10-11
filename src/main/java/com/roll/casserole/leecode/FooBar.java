package com.roll.casserole.leecode;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 交替打印FooBar
 * <p>@author roll
 * <p>created on 2020/10/10 11:39 上午
 */
public class FooBar {

    private int n;

    Semaphore foosemaphore = new Semaphore(1);

    Semaphore barsemaphore = new Semaphore(1);

    volatile boolean flag = true;

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (!flag) {
            }
            printFoo.run();
            flag = false;

            try {
                cyclicBarrier.await();
                flag = false;
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            flag = true;
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        FooBar fooBar = new FooBar(10);
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
