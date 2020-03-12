package com.roll.casserole.sync;

/**
 * @author roll
 * created on 2020/3/12 7:00 下午
 */
public class SyncTest {
    static class ClassA {
        public synchronized void printA() {
            System.out.println("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }

    static class ClassB extends ClassA {
        @Override
        public void printA() {
            super.printA();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                ClassB classA = new ClassB();
                classA.printA();
            }).start();
        }
    }

}
