package com.roll.casserole.buffer;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author roll
 * created on 2019-08-15 17:18
 */
public class DirectBufferTest {
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
        List<Long> memoryAddressList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long memoryAddress = unsafe.allocateMemory(1024 * 1024 * 200);
            unsafe.putAddress(memoryAddress, 1);
            System.out.println(memoryAddress);
            memoryAddressList.add(memoryAddress);
            System.out.println(unsafe.getAddress(memoryAddress));
            unsafe.freeMemory(memoryAddress);
        }

        System.gc();
        Unsafe finalUnsafe = unsafe;
        memoryAddressList.forEach(address -> System.out.println(finalUnsafe.getAddress(address)));
    }
}
