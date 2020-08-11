package com.roll.casserole.jvm.gc;

/**
 * <p>@author roll
 * <p>created on 2020/8/9 2:08 下午
 */
public class DeadLockDemo {
    private final Object a = 0;
    private final Object b = 1;

    public void lock(Object o, Object o1) {
        synchronized (o) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1) {
                System.out.println("lock1");
            }
        }
    }

    public Object getA() {
        return a;
    }

    public Object getB() {
        return b;
    }

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        new Thread(() -> {
            deadLockDemo.lock(deadLockDemo.getA(), deadLockDemo.getB());
        }).start();
        new Thread(() -> {
            deadLockDemo.lock(deadLockDemo.getB(), deadLockDemo.getA());
        }).start();
    }
}
