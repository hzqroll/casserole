package com.roll.casserole.sync;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author roll
 * created on 2020/3/1 7:22 下午
 */
public class CasTest {
    volatile int a = 10;

    public int getA() {
        return a;
    }

    public void setA(int arg) {
        a = a + arg;
    }

    public void increase() {
        a++;
    }

    public static void main(String[] args) {
        Field field;
        Unsafe unsafe = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        CasTest casTest = new CasTest();
        int b = casTest.getA();
        int c = b - 3;
        //casTest.increase();
        System.out.println(casTest.getA());
        long offset = 0;
        try {
            offset = unsafe.objectFieldOffset
                    (CasTest.class.getDeclaredField("a"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println(unsafe.compareAndSwapInt(casTest, offset, b, 2));
        System.out.println(casTest.getA());
        System.out.println(b);
    }
}
