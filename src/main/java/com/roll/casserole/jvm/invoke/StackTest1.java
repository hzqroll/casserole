package com.roll.casserole.jvm.invoke;

/**
 * @author roll
 * created on 2019-10-05 19:03
 */
public class StackTest1 {

    public static void main(String[] args) {
        StackTest1 stackTest1 = new StackTest1();
        stackTest1.plus(1L);
    }

    private int plus(long i) {
        int[] a = new int[1024 * 1024];
        i++;
        System.out.println(i);
        return plus(i);
    }

}
