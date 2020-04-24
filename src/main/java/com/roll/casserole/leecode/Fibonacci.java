package com.roll.casserole.leecode;

/**
 * @author roll
 * created on 2020/4/23 5:36 下午
 */
public class Fibonacci {
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n -1) + fibonacci(n -2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
    }
}
