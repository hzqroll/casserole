package com.roll.casserole.buffer;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * @author roll
 * created on 2019-08-23 17:39
 */
public class MemoryTest {
    public static void main(String[] args) {
        Unsafe unsafe = null;
        Field field;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            long offset = unsafe.allocateMemory(1024 * 1024);
            System.out.println(offset);
            unsafe.setMemory(offset, 112, (byte) 1);
            byte a = unsafe.getByte(offset);
            System.out.println(a);
        }
    }
}
