package com.roll.casserole.buffer;

import sun.misc.Unsafe;
import sun.misc.VM;

import java.lang.reflect.Field;

/**
 * @author roll
 * created on 2019-08-15 17:18
 */
public class DirectBufferTest1 {
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
        int cap = 10;
        boolean pa = true;
        int ps = unsafe.pageSize();
        long size = Math.max(1L, (long)cap + (pa ? ps : 0));
        System.out.println(size);
    }
}
